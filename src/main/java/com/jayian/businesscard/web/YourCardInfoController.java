package com.jayian.businesscard.web;

/*
 * @comment	받은 명함 관리 처리 Controller
 */

import com.jayian.businesscard.common.utils.FileService;
import com.jayian.businesscard.service.ocr.GoogleVisionApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

@Controller
@RequestMapping("yourcard")
public class YourCardInfoController {

    @Inject
    private YourCardInfoDAO yourCardInfoDAO;

    private static final Logger logger = LoggerFactory.getLogger(YourCardInfoController.class);

    static final String yourCarduploadPath = "/BusinessCardProject/management/yourCardInfo/";	// 받은 명함 파일 업로드 경로
    static final String myCarduploadPath = "/BusinessCardProject/management/myCardInfo/";		// 내 명함 파일 업로드 경로
    static final String detectFilePath = "BusinessCardProject\\management\\temp\\";			// OCR 처리할 파일이 있는 경로
    static final String uploadPath = "/BusinessCardProject/management/temp/";					// OCR 처리할 파일을 만드는 경로

    public static String uploadSavedImg;			// 업로드를 위해 temp에서 회원 폴더로 이동할 파일의 이름

    /*
     * @comment		: 다른 사람 명함 등록 페이지로 이동
     * @author		: 정보승
     */
    @RequestMapping(value="insertYourCard", method=RequestMethod.GET)
    public String insertCardPage() {

        logger.info("Move Insert Your Card Page");

        return "myInfo/insertCard";

    }


    /*
     * @comment				:	다른 사람 명함 등록 (사진 이외의 정보)
     * @param	uploadImg	:	이미지의 실제 파일명
     * @author				:	정보승
     */
    @ResponseBody
    @RequestMapping(value = "insertYourCard", method = RequestMethod.POST, produces="text/plain; charset=UTF-8")
    public String yourCardInsert(YourCardInfoVO yourCard
            , String uploadImg
            , HttpSession session
            , Model model) {

        logger.info("yourCard Insert Start");

        // 세션을 통해 회원 ID를 가져와서 저장.
        String userid = (String) session.getAttribute("userid");
        yourCard.setUserid(userid);

        // temp폴더의 파일목록을 생성
        ArrayList<File> dirList = FileService.getDirFileList(new StringBuffer().append(uploadPath).append(userid).append("/").toString());

        // temp 폴더의 파일 수
        int dirListLen = dirList.size();

        // temp 폴더의 파일을 회원의 yourcard 폴더로 이동
        for (int i=0; i<dirListLen; i++)
        {

            if(uploadSavedImg.equals(dirList.get(i).getName())){

                FileService.fileCopy(
                        new StringBuffer().append(uploadPath).append(userid).append("/").append(uploadSavedImg).toString()
                        , new StringBuffer().append(yourCarduploadPath).append(userid).append("/").append(uploadSavedImg).toString()
                );

                break;
            }

        }

        yourCard.setFrontimgsaved(uploadSavedImg);		// 서버에 저장될 이미지 파일명
        yourCard.setFrontimgoriginal(uploadImg);		// 회원이 올린 실제 이미지 파일명

//		yourCardInfoDAO
        boolean insertIs = yourCardInfoDAO.yourCardInsert(yourCard);

        String resultMsg = "";	// 화면으로 명함 등록의 결과를 알려줄 변수

        if(insertIs) {

            logger.info("Insert Your Card Success" + yourCard.toString());
//			model.addAttribute("resultMsg", "정상 등록 되었습니다.");

            uploadSavedImg = "";
            resultMsg = "true";

        }
        else {

            logger.info("Insert Your Card Fail");
//			model.addAttribute("failMsg", "등록 실패하였습니다.");
            resultMsg = "false";
        }
        return resultMsg;
    }



    /*
     * @comment		:	업로드한 이미지를 분석하기 위한 메소드
     * @param		:	OCR 분석을 위해 업로드한 이미지의 파일명
     * @return		:	OCR 분석 결과
     * @author		:	정보승
     */
    @ResponseBody
    @RequestMapping(value="detectTextFromImage", method=RequestMethod.GET, produces="text/plain; charset=UTF-8")
    public String detectTextFromImage(String ocrImage, HttpSession session) {

        logger.info("detectTextFromImage Start");

        String detectResult = "";	// OCR 분석 결과를 담을 변수

        String userid = (String) session.getAttribute("userid");

        // OCR 분석을 위한 이미지를 불러올 경로
        String filepath = new StringBuffer().append("c:\\").append(detectFilePath).append(userid).append("\\").append(ocrImage).toString();

        try {

            // OCR분석 결과
            detectResult = GoogleVisionApi.detectText(filepath);
            uploadSavedImg = ocrImage;		// 분석 완료된 이미지의 파일명을 전역변수에 담음.(서버 저장을 위해)
//			logger.info("분석 결과 : " + detectResult);
        }
        catch (Exception e) {

            logger.info("detectTextFromImage Fail");
        }

        return detectResult;

    }



    /*
     * @comment		:	분석하기 위한 이미지를 임시로 업로드하는 메소드
     * @param		:	AJAX로 미리보기 이미지를 받는 인터페이스
     * @author		:	정보승
     */
    @ResponseBody
    @RequestMapping(value="tempUpload", method=RequestMethod.POST, produces="text/plain; charset=UTF-8")
    public String tempUpload(MultipartHttpServletRequest multi, HttpSession session) {


        logger.info("tempUpload Start");

        // fileObj라는 key를 가진 파일을 가져오기
        MultipartFile testSample = multi.getFile("fileObj");
        String ocrImage = "";

        String userid = (String) session.getAttribute("userid");

        logger.info(testSample.getOriginalFilename());

        // fileObj의 파일명 생성
        if (!testSample.isEmpty()) {

            ocrImage = FileService.saveFile(testSample, new StringBuffer().append(uploadPath).append("/").append(userid).toString());
        }

        return ocrImage;

    }



    /*
     * @comment		: 명함 삭제
     * @author		: 여지원
     */
    @ResponseBody
    @RequestMapping(value="yourCardDel", method=RequestMethod.POST, produces="text/plain; charset=UTF-8")
    public String yourCardInfoDel(String yourcardnum, HttpSession session) {

        logger.info("Delete One YourCardInfo");

        String userid = (String) session.getAttribute("userid");

        // 회원 ID와 받은 명함 번호를 이용해 삭제
        HashMap<String, String> deleteYourCard = new HashMap<>();
        deleteYourCard.put("userid", userid);
        deleteYourCard.put("yourcardnum", yourcardnum);

        boolean deleteIs = yourCardInfoDAO.deleteYourCardOne(deleteYourCard);

        if(deleteIs) {

            logger.info("Delete One YourCardInfo Success");
            return "정상 삭제 되었습니다.";

        }
        else {

            logger.info("Delete One YourCardInfo Fail");
            return "삭제 실패하였습니다.";
        }


    }



    /*
     * @comment				:	수정하고자 하는 받은 명함의 정보를 받아 페이지 이동하는 메소드.
     * @param	yourcardnum	:	받은 명함의 등록번호
     * 			session		:	회원의 ID를 받기 위함.
     * @author				:	정보승
     */
    @RequestMapping(value="yourCardUpdatePage", method = RequestMethod.GET)
    public String registerSession (String yourcardnum, HttpSession session, Model model) {

        logger.info("Move Update Your Card Page Start");

        String userid = (String) session.getAttribute("userid");

        HashMap<String, String> updateYourCardInfo = new HashMap<>();
        updateYourCardInfo.put("userid", userid);
        updateYourCardInfo.put("yourcardnum", yourcardnum);

        // 수정하고자 하는 명함 정보를 세션에 등록하기 위해 DB에서 가져온다.
        YourCardInfoVO originalYourCardInfo = yourCardInfoDAO.selectYourCardOne(updateYourCardInfo);

        if(originalYourCardInfo != null) {

            logger.info("Get Update Your Card Info Success");

            // Model에 담아 페이지 이동
            model.addAttribute("originalYourCardInfo", originalYourCardInfo);
        }
        else {

            logger.info("Get Update Your Card Info Fail");
        }


        return "management/updateYourCard";
    }



    /*
     * @comment		: 명함 정보수정
     * @author		: 여지원
     */
    @RequestMapping(value="yourCardUpdate", method=RequestMethod.POST)
    public String yourCardUpdate(YourCardInfoVO udpateYourCard, HttpSession session) {

        logger.info("Update One YourCardInfo");

        // 회원의 ID와 기존의 수정되기 전의 받은 명함정보를 세션에서 가져온다.
        String userid = (String) session.getAttribute("userid");

        udpateYourCard.setUserid(userid);
        boolean updateIs = yourCardInfoDAO.updaetYourCardOne(udpateYourCard);

        if(updateIs) {

            logger.info("Update One YourCardInfo Success");
        }
        else {

            logger.info("Update One YourCardInfo Fail");
        }

        return "redirect:../management";
    }



    /**
     * 파일 다운로드
     * @param boardnum 파일이 첨부된 글 번호
     */
    @RequestMapping(value = "download", method = RequestMethod.GET)
    public String fileDownload(String yourcardnum, Model model,
                               HttpServletResponse response, HttpSession session) {

        String userid = (String) session.getAttribute("userid");
        HashMap<String, String> temp = new HashMap<>();
        temp.put("yourcardnum", yourcardnum);
        temp.put("userid", userid);

        YourCardInfoVO oneYourCard = yourCardInfoDAO.selectYourCardOne(temp);

        //원래의 파일명
        String originalfile = new String(oneYourCard.getFrontimgoriginal());
        try {

            response.setHeader("Content-Disposition", " attachment;filename="+ URLEncoder.encode(originalfile, "UTF-8"));

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }

        //저장된 파일 경로
        String filepath = new StringBuffer().append(yourCarduploadPath).append(oneYourCard.getUserid()).append("/").toString();
        String fullPath = filepath + oneYourCard.getFrontimgsaved();

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



}
