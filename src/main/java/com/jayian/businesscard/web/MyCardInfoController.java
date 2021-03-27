package com.jayian.businesscard.web;

/*

 * @comment	:	내가 만든 명함과 보유하고 있는 명함을 조회

 */


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller

@RequestMapping("mycard")

public class MyCardInfoController {

    @Inject
    private MyCardInfoDAO myCardInfoDAO;
    @Inject
    private UserInfoDAO userInfoDAO;


    private static final Logger logger = LoggerFactory.getLogger(MyCardInfoController.class);

//	final String myCarduploadPath = "/BusinessCardProject/management/myCardInfo/";		// 내 명함 파일 업로드 경로

    String myCarduploadPath = YourCardInfoController.myCarduploadPath;







    /*

     * @comment		: 내 명함 등록 페이지로 이동

     * @author		: 정보승

     */

    @RequestMapping(value="insertMyCard", method=RequestMethod.POST)
    public String insertCard(MyCardInfoVO mycard, HttpSession session) {
        logger.info("Insert My Card Start");
        String userid = (String) session.getAttribute("userid");
        mycard.setUserid(userid);

        boolean updateIs = myCardInfoDAO.updateAddMyCard(mycard);

        if(updateIs) {

            logger.info("Update Add Info Success");
        }
        else {
            logger.info("Update Add Info Fail");
        }
        return "redirect:../cardList";

    }

    /*
     * @comment	:	내 명함을 볼 수 있는 페이지로 이동
     * @author	:	정보승
     */

    @RequestMapping(value="myCardInfo", method=RequestMethod.GET)
    public String myCardInfo(HttpSession session, Model model) {

        logger.info("Move My Card Info Page");

        String userid = (String) session.getAttribute("userid");

        // 내 명함 전체 리스트 가져오기
        ArrayList<MyCardInfoVO> allMyCardList = myCardInfoDAO.selectAllMyCard(userid);

        if(allMyCardList != null) {
            logger.info("Move My Card Info Page Success");

            // 내 명함을 내 명함 보기 페이지에 전달
            model.addAttribute("allMyCardList", allMyCardList);

            // 내 명함 보기 페이지로 이동
            return "myInfo/cardList";
        }
        else {

            logger.info("Move My Card Info Page Fail");
            // 메인 페이지로 이동
            return "redirect:../home";
        }
    }


    /*

     * @comment				:	내 카드 정보(1개)를 보는 페이지(cardInfo.jsp)로 이동
     * @param	mycardnum	:	내가 조회하고 싶은 명함의 번호
     * @author				:	정보승
     */

    @RequestMapping(value="myCardOneInfo", method=RequestMethod.GET)

    public String myCardOneInfo(String mycardnum, HttpSession session, Model model) {

        logger.info("Move MyCardOne");
        String userid = (String) session.getAttribute("userid");

		/*여지원. 다음은 updateMyCard함수에서 myCardOneInfo로 redirect 하는 경우를 위해 쓴 코드.
		myCardNum을  session에 넣었기 때문에 세션에서 값을 뺀다.*/

        String mycardnumFromSession = (String)session.getAttribute("myCardNum");
        if(mycardnumFromSession!=null){
            mycardnum = mycardnumFromSession;
        }


        // 1개의 내 명함 정보를 가져오기 위한 HashMap 생성, 회원 ID와 내 명함 번호
        HashMap<String, Object> myCard = new HashMap<>();
        myCard.put("userid", userid);
        myCard.put("mycardnum", mycardnum);

        /*여지원. 보안성을위해 session에서 mycardnum 값을 삭제시켜버린다.*/
        if(mycardnumFromSession!=null){
            session.removeAttribute("myCardNum");
        }
        // 1개의 내 명함 정보 가져오기
        MyCardInfoVO selectMyCard = myCardInfoDAO.selectOneMyCard(myCard);

        if(selectMyCard != null) {
            logger.info("Get MyCardnum Success");
            selectMyCard.setMycardnum(mycardnum);
            model.addAttribute("selectMyCard", selectMyCard);
        }
        else {
            logger.info("Get MyCardnum Fail");
        }
        return "myInfo/cardInfo";
    }





    /*

     * @comment	:	나의 카드정보를 담은 리스트
     * @author	:	여지원
     */

    @ResponseBody

    @RequestMapping(value="getMyCards", method=RequestMethod.GET, produces="text/plain; charset=UTF-8")
    public String getMyCards(HttpSession session) {

        logger.info("getMyCards Success 시작");
        String userid = (String) session.getAttribute("userid");

        String result = "";
        ArrayList<MyCardInfoVO> list = null;
        list = myCardInfoDAO.selectAllMyCard(userid);
        Gson gson = new Gson();


        result = gson.toJson(list);

        logger.info("getMyCards Success 종료!");
        return result;

    }

    /**
     * @comment				:	파일 다운로드 및 화면에 이미지를 표현하기 위한 메소드
     * @param mycardnum 	:	내 명함 번호
     * @author 				:	정보승
     */

    @RequestMapping(value = "download", method = RequestMethod.GET)
    public String fileDownload(String mycardnum, Model model,
                               HttpServletResponse response, HttpSession session) {

        String userid = (String) session.getAttribute("userid");
        HashMap<String, Object> myCard = new HashMap<>();
        myCard.put("userid", userid);
        myCard.put("mycardnum", mycardnum);



        MyCardInfoVO myOneCard = myCardInfoDAO.selectOneMyCard(myCard);
        //원래의 파일명
        String originalfile = myOneCard.getFrontimgoriginal();

        try {
            response.setHeader("Content-Disposition", " attachment;filename="+ URLEncoder.encode(originalfile, "UTF-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }



        //저장된 파일 경로

        String filepath = new StringBuffer().append(myCarduploadPath).append(userid).append("/").toString();
        String fullPath = new StringBuffer().append(filepath).append(myOneCard.getFrontimgsaved()).toString();

        //서버의 파일을 읽을 입력 스트림과 클라이언트에게 전달할 출력스트림
        FileInputStream filein = null;
        ServletOutputStream fileout = null;



        try {
            filein = new FileInputStream(fullPath);
            fileout = response.getOutputStream();


            //Spring의 파일 관련 유틸
            FileCopyUtils.copy(filein, fileout);
            filein.close();
            fileout.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }





    /**
     * @comment				:	MyCard 수정하기
     * @param mycardnum 	:	내 명함 번호
     * @author 				:	여지원
     */

    @RequestMapping(value = "updateMyCard", method = RequestMethod.POST)
    public String updateMyCard(MyCardInfoVO mycard, HttpSession session, Model model) {
        logger.info("updateMyCard 시작");
        String userid = (String)session.getAttribute("userid");
        mycard.setUserid(userid);

        boolean flag = myCardInfoDAO.updateAddMyCard(mycard);
        if(flag){
            session.setAttribute("myCardNum",mycard.getMycardnum());
        }

        logger.info("updateMyCard 종료");
        return "redirect:myCardOneInfo";

    }



    /**
     * @comment				:	MyCard 삭제하기
     * @param mycardnum 	:	내 명함 번호
     * @author 				:	여지원
     */

    @RequestMapping(value = "deleteMyCard", method = RequestMethod.GET)
    public String deleteMyCard(String mycardnum, HttpSession session) {
        logger.info("deleteMyCard 시작");
        String userid = (String)session.getAttribute("userid");
        String username = (String)session.getAttribute("username");
        HashMap<String, Object> deleteMyCard = new HashMap<>();
        deleteMyCard.put("mycardnum", mycardnum);
        deleteMyCard.put("userid", userid);

        myCardInfoDAO.deleteMyCard(deleteMyCard);

        /*userInfo에서도 mycardnum값을 빼줘야한다.(basicImg로 바뀜)*/
        UserInfoVO updateUser = new UserInfoVO();
        updateUser.setUserid(userid);
        updateUser.setUsername(username);
        updateUser.setProfilecard("basicImg");

        userInfoDAO.updateUserInfo(updateUser);


        logger.info("deleteMyCard 종료");
        return "redirect:../cardList";

    }


    /**
     * @comment				:	insertProfile 대표 명함 넣기 ( profilecard에 mycardnum값을 넣는다-update)
     * @param mycardnum 	:	내 명함 번호
     * @author 				:	여지원
     */

    @RequestMapping(value = "insertProfileImage", method = RequestMethod.GET)
    public String insertProfileImage(String mycardnum, HttpSession session) {

        logger.info("insertProfileImage 시작");

        String profilecard = mycardnum;
        UserInfoVO updateUser = new UserInfoVO();

        String userid = (String)session.getAttribute("userid");
        String username = (String)session.getAttribute("username");
        updateUser.setUserid(userid);
        updateUser.setProfilecard(profilecard);
        updateUser.setUsername(username);

//		System.out.println(updateUser);
//		System.out.println("__________________________");
        userInfoDAO.updateUserInfo(updateUser);
        logger.info("insertProfileImage 종료");

        return "redirect:../cardList";

    }


    /*
     * @comment	:	대표 명함 제거 후 userinfo의 profileImg 열을 basicImg로 변경
     * @author	:	정보승
     */
    @ResponseBody
    @RequestMapping(value = "deleteProfileImg", method = RequestMethod.GET)
    public String deleteProfileImg(HttpSession session) {

        String userid = (String) session.getAttribute("userid");
        String username = (String) session.getAttribute("username");

        UserInfoVO updateUser = new UserInfoVO();
        updateUser.setUserid(userid);
        updateUser.setUsername(username);
        updateUser.setProfilecard("basicImg");

        boolean profiileImgDelIs = userInfoDAO.updateUserInfo(updateUser);

        if(profiileImgDelIs) {

            logger.info("Delete Profile Img Success");

            return "true";
        }
        else {

            logger.info("Delete Profile Img Fail");

            return "false";
        }
    }


}