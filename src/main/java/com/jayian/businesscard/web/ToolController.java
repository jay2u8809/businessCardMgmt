package com.jayian.businesscard.web;

//Tool 페이지와 관련된 것을 담당하는 컨트롤러

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value="tool")
public class ToolController {



    private static final Logger logger = LoggerFactory.getLogger(ToolController.class);

    @Inject
    private MyCardInfoDAO myCardDAO;

    final String myCarduploadPath = "/BusinessCardProject/management/myCardInfo/";		// 내 명함 파일 업로드 경로

    /*
     * @comment				:	이미지 캡처
     * @param	uploadImg	:	myCard
     * @author				:	정현수
     */
    @RequestMapping(value ="download", method = RequestMethod.POST)
    public String download(HttpServletRequest request, MyCardInfoVO myCard, HttpServletResponse response, HttpSession session, Model model)throws Exception {

        logger.info("Image Capture Start ");

        //세션에 있는 아이디 값을 가져옴
        String userid = (String) session.getAttribute("userid");

        //이미지 데이터 소스를 가져옴
        String imgData = (String)request.getParameter("imgData");

        //div태그 통째로 가져옴
        String html = (String)request.getParameter("html");

        //파일 출력 아웃풋 스트림 선언
        FileOutputStream stream = null;

        logger.info(html);

        try {

            System.out.println("imgData :::::::: "  + imgData);
            if(imgData == null || imgData=="") {
                throw new Exception();
            }

            //이미지 파라미터를 꺼냄
            //필요없는 단어들 제거
            imgData = imgData.replaceAll("data:image/png;base64,", "");

            /* Base64 로 decoding 해서 byte[] 타입으로 변환
            commons-codec 라이브러리가 필요함 ( import org.apache.commons.codec.binary.Base64; )*/
            byte[] file = Base64.decodeBase64(imgData);

            //byte[] 을 ByteArrayInputStream 으로 변환
            ByteArrayInputStream is = new ByteArrayInputStream(file);

//            System.out.println("file  :::::::: " + file + " || " + file.length);

            String fileName=  UUID.randomUUID().toString();

//            stream = new FileOutputStream("c:\\test1\\"+fileName+".png");

            //파일 경로 설정   (  myCarduploadPath + fileName +".png")
            stream = new FileOutputStream( new StringBuffer()
                    .append(myCarduploadPath)
                    .append(userid)
                    .append("\\")
                    .append(fileName)
                    .append(".png")
                    .toString());
            stream.write(file);
            stream.close();

//            System.out.println("fileName  :::::::: " + fileName);

            //contentType, header 정보 셋팅. header 마지막에 filename 는 다운로드되는 파일명
//            response.setContentType("image/png");
//            response.setHeader("Content-Disposition", "attachment; filename=report.png");
//
//            /*IOUtils 을 이용해서 다운로드
//			commons-io 라이브러리가 필요함 ( import org.apache.commons.io.IOUtils; )*/
//            IOUtils.copy(is, response.getOutputStream());
//
//            response.flushBuffer();


            //myCard에 세팅
            myCard.setFrontimgsaved(fileName+".png");
            myCard.setFrontimgoriginal(fileName+".png");
            myCard.setUserid(userid);
            myCard.setOtherinfo(html); //div코드
//          myCard.setBackimgsaved(backimgsaved);
            myCardDAO.insertMyCard(myCard);



        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        logger.info("Image Capture End ");

        ArrayList<MyCardInfoVO> mycards = myCardDAO.selectAllMyCard(userid);

        int myCardLen = mycards.size();
        String temp = myCard.getFrontimgsaved();

        for(int i =0;i<myCardLen;i++){

            if(mycards.get(i).getFrontimgsaved().equals(temp)) {

                session.setAttribute("myCardNum", mycards.get(i).getMycardnum());
                String getNum =(String)session.getAttribute("myCardNum");
                System.out.println(getNum);
                break;
            }
        }

        return "redirect:addInfo";

    }


    /*
     * @comment	:	커스터마이징 이미지 추가 정보 입력란 이동
     * @
     */
    @RequestMapping(value="addInfo", method= RequestMethod.GET)
    public String addMyCardInfo(HttpSession session, Model model) {

        logger.info("Move addMyCardInfo");

//		String userid = (String) session.getAttribute("userid");

        //session에서 myCardNum을 가져와서 바로 모델에 넣고 session의 값은 바로 지워버린다.
        String myCardNum=(String)session.getAttribute("myCardNum");

        model.addAttribute("myCardNum", myCardNum);
        session.removeAttribute("myCardNum");

//		HashMap<String, Object> myCard = new HashMap<>();
//		myCard.put("userid", userid);
//		myCard.put("myCardNum", myCardNum);
//
//		MyCardInfoVO updateVo = myCardDAO.selectOneMyCard(myCard);
//
//		// 수정을 위한 내 명함 정보를 보낸다.
//		model.addAttribute("updateVo", updateVo);



        return "myInfo/addMyCardInfo";
    }


    /*
     * @comment				:	커스터마이징 이미지 가져오기
     * @param	uploadImg	:	myCard
     * @author				:	정현수
     */
    @RequestMapping(value ="myTool", method = RequestMethod.GET)
    public String readTool(HttpServletResponse response, HttpSession session, Model model)throws Exception {

        logger.info("myTool Start ");

        ArrayList<MyCardInfoVO> list = null;

        //세션에 있는 아이디 값을 가져옴
        String userid = (String) session.getAttribute("userid");
        logger.info("userid 값 : " + userid);
        list = myCardDAO.selectAllMyCard(userid);

        logger.info("list 값 : " + list);

        model.addAttribute("list", list);
        logger.info("myTool End ");

        return "tool/myTool";
    }







}

