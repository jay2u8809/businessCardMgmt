package com.jayian.businesscard.web;

/*
 * @comment
 */

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("users")
@SessionAttributes("user")
public class UserInfoController {

    @Inject
    private UserInfoDAO userDao;

//	@Inject
//		private YourCardInfoDAO yourCardInfoDAO;

    @Inject
    private JavaMailSender mailSender;

    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
    private final String admin = "arisol342@gmail.com";	// 관리자 계정
    private final String serverAddress = "http://203.233.199.165:8888/www/";	// Server Address

    //	final String uploadPath = "/BusinessCardProject/management/temp/";
    /*
     * @comment				: 회원 가입 페이지 이동
     * @param	joinUser	:
     * @author				: 정보승
     */
    @RequestMapping(value="join", method=RequestMethod.GET)
    public String userJoinForm(UserInfoVO joinUser, Model model) {

        logger.info("Move Join Form");

        return "sign/sign";
    }

    /*
     * @comment				: 회원 가입 처리 (인증을 위한 E-mail 발송 기능 포함)
     * @param	joinUser	: View에서 받은 회원의 개인정보를 저장한 객체
     * @author				: 정보승
     */
    @RequestMapping(value="join", method=RequestMethod.POST)
    public String userJoin(UserInfoVO joinUser, HttpSession session, Model model) throws MessagingException, UnsupportedEncodingException {

        logger.info("User Join Start");

        boolean joinIs = userDao.joinUser(joinUser);

        if(joinIs) {

            // 인증을 위한 E-mail을 보내는 부분
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper
                    = new MimeMessageHelper(message, true, "UTF-8");

            messageHelper.setFrom(admin);  					// 보내는사람 (생략 시 정상작동을 안함)
            messageHelper.setTo(joinUser.getUserid());     	// 받는사람 이메일
            messageHelper.setSubject("[이메일 인증]"); 		// 메일제목(생략 가능)
            messageHelper.setText(							// 메일 내용
                    new StringBuffer().append("메일인증 \n")
                            .append("Arisol에 가입해주셔서 감사합니다. \n"
                                    + serverAddress + "users/verify?userid="
                                    + joinUser.getUserid())
                            .append("\n이메일 인증 확인").toString());
            try {

                mailSender.send(message);					// 메일 보내기
            }
            catch(MailException e){

                e.printStackTrace();
            }

            model.addAttribute("verifyMsg", "emailVerify");
            logger.info("User Join Success");
        }
        else {

            logger.info("User Join Fail");
        }

//		return "redirect:../";
        return "loginForm";

    }


    /*
     * @comment			: 인증 받은 회원의 EmailVerify 속성을 'Y'로 변경한다.
     * @param	userid	: E-mail 인증을 받은 회원의 ID
     * @author			: 정보승
     */
    @RequestMapping(value = "verify", method = RequestMethod.GET)
    public String verifySuccess(@RequestParam String userid) {

        logger.info("E-mail Verify");
        logger.debug("userID : {}", userid);

        boolean verifyIs = userDao.verifyUser(userid);

        if(verifyIs) {

            logger.info("E-mail Verify Success");
        }
        else {

            logger.info("E-mail Verify Fail");
        }

        return "redirect:../";
    }



    /*
     * @comment				: 회원 로그인 처리
     * @param	loginInfo	: View에서 입력받은 User ID와 User Pw를 저장하고 있는 객체
     * 			session		: 로그인 성공 시 User ID를 저장
     * 			model		: 로그인 실패 시 실패 메시지를 User에게 알리기 위한 메시지 저장
     * @author				: 정보승
     */
    @RequestMapping(value="login", method=RequestMethod.POST)
    public String userLogin(UserInfoVO loginInfo, HttpSession session, Model model) {

        logger.info("User Loing Start");
        session.removeAttribute("verifyMsg");
        // View에서 입력받은 ID와 PW를 각각 HashMap에 저장
        HashMap<String, Object> userAccount = new HashMap<>();
        userAccount.put("userid", loginInfo.getUserid());
        userAccount.put("userpw", loginInfo.getUserpw());

        // DAO를 통해 받은 User의 정보를 user 객체에 저장
        UserInfoVO user = userDao.selectUser(userAccount);
        char userVerified = (user != null) ? user.getEmailverify() : 'N';	// E-mail 인증 여부

        // E-mail 인증까지 모두 마친 로그인
        if(user != null && userVerified == 'Y') {

            logger.info("User Login Success");

            session.setAttribute("userid", user.getUserid());	// 로그인 성공시 User ID를 Session에 저장
            session.setAttribute("username", user.getUsername());

            //저장 폴더가 없으면 생성
            File myUploadPath = new File(YourCardInfoController.myCarduploadPath + "/" + user.getUserid());
            File yourUploadPath = new File(YourCardInfoController.yourCarduploadPath + "/" + user.getUserid());

            if (!myUploadPath.isDirectory()) {

                myUploadPath.mkdirs();
            }
            if(!yourUploadPath.isDirectory()) {

                yourUploadPath.mkdirs();
            }

            return "redirect:../home";
        }

        // E-mail 인증이 되지 않은 로그인
        else if(user != null && userVerified == 'N') {

            logger.info("User Login Fail - Email Verify Fail");

//			model.addAttribute("msg", "아직 E-mail인증이 완료되지 않았습니다");

            // login 실패 시 alert를 띄어줄 속성
            model.addAttribute("loginMsg", "emailFail");
//			return "redirect:../";
            return	"loginForm";
        }
        else {

            logger.info("User Login Fail");
//			model.addAttribute("msg", "로그인 정보가 일치하지 않습니다.");
//			session.setAttribute("loginMsg", "passwordFail");
//			return "redirect:../";
            model.addAttribute("loginMsg", "passwordFail");
            return	"loginForm";
        }


    }



    /*
     * @comment	:	Password 찾기
     * @author	:	정보승
     */
    @RequestMapping(value="findPw", method=RequestMethod.POST)
    public String findPassword(String userid, String username, Model model) throws MessagingException {

        logger.info("Find Password Start");

        HashMap<String, Object> findTemp = new HashMap<>();
        findTemp.put("userid", userid);
        findTemp.put("findPW", userid + username);

        UserInfoVO findPwResult = userDao.selectUser(findTemp);


        if(findPwResult.getUsername().equals(username)) {

            // 인증을 위한 E-mail을 보내는 부분
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper
                    = new MimeMessageHelper(message, true, "UTF-8");

            messageHelper.setFrom(admin);  					// 보내는사람 (생략 시 정상작동을 안함)
            messageHelper.setTo(findPwResult.getUserid());     	// 받는사람 이메일
            messageHelper.setSubject("[Password 변경]"); 		// 메일제목(생략 가능)
            messageHelper.setText(							// 메일 내용
                    new StringBuffer().append("메일인증 \n")
                            .append("Arisol에 가입해주셔서 감사합니다. \n"
                                    + serverAddress + "users/verify?userid="
                                    + findPwResult.getUserid())
                            .append("\n이메일 인증 확인")
                            .append("\n pw : ")
                            .append(findPwResult.getUserpw())
                            .toString());

            try {
                mailSender.send(message);					// 메일 보내기
            }

            catch(Exception e){

                e.printStackTrace();
            }

            logger.info("Find User Password Success");
            model.addAttribute("msg", "E-mail이 발송되었습니다.");

        }
        else {

            model.addAttribute("msg", "입력한 정보가 일치하지 않습니다.");
        }
        return "redirect:../";

    }


    /*
     * @comment		: 명함 등록 화면(InsertCard.jsp)로 이동
     * @author		: 정보승
     */
    @RequestMapping(value = "insertCardPage", method = RequestMethod.GET)
    public String insertCardPage(Model model) {

        logger.info("Move InsertCard Page");

        return "myInfo/insertCard";
    }


    /*
     * @comment		: 내 명함 등록
     * @author		: 정보승
     */
    @RequestMapping(value = "myCardInsert", method = RequestMethod.POST)
    public String myCardInsert(MyCardInfoVO myCard, String division, Model model) {

        logger.info("myCardInsert");



        return "redirect:../management";
    }


    /*
     * @comment		: 회원 로그아웃
     * @author		: 정보승
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String myCardInsert(HttpSession session) {

        logger.info("Logout Start");

        String userid = (String) session.getAttribute("userid");

        String deleteFile = new StringBuffer().append(YourCardInfoController.uploadPath).append(userid).toString();

        // temp 폴더의 회원 ID 폴더의 모든 이미지를 삭제
        File files[] = new File(deleteFile).listFiles();

        if(files != null ) {
            for(int i=0; i<files.length; i++) {

                files[i].delete();
                logger.info("Image Delete Success");
            }
        }

        session.invalidate();

        return "redirect:/";
    }


}
