package com.jayian.businesscard.web;

//Board 와 Comment 모두 관리하는 컨트롤러이다

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("board")
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Inject
    private BoardDAO boardDAO;

    final int countPerPage = 7; //현재 페이지 게시글 갯수, 하나의 페이지에 몇개의 게시글 출력 여부
    final int pagePerGroup = 5; //현재 페이지 그룹 갯수, 현재 보이는 페이지에 몇개의 페이지번호를 보여줄 것인가


    /*
     * @comment			: ContactForm 화면으로 이동한다(글쓰기 화면)
     * @param			:
     * @author			: 정현수
     */
    @RequestMapping(value="contactForm", method=RequestMethod.GET)
    public String goContact (){

        logger.info("ContactForm Start");

        logger.info("ContactForm End");

        return "contact/contactForm";
    }


    /*
     * @comment			: 게시판쓰기
     * @param			: BoardVO
     * @author			: 여지원
     */
    @RequestMapping(value="writeBoard", method=RequestMethod.POST)
    public String writeBoard(BoardVO board, Model model){

        logger.info("writeBoard Start");

        int result = boardDAO.insertBoard(board);

        if(result ==0){

            logger.info("insert 실패");
            model.addAttribute("errorMsg", "오류가 발생했습니다.");
            return "contact/contactForm";
        }

        logger.info("writeBoard End");

        return "redirect:../contact";
    }


    /*
     * @comment			: contact 게시판 전체 출력
     * @author			: 정현수
     * @return 			: list에 데이터를 저장
     * @memo			: Ajax 방식, 요청한 곳으로 되돌아간다.
     */
    @ResponseBody
    @RequestMapping(value="list", method=RequestMethod.POST)
    public HashMap<String, Object> boardList (
            @RequestParam(value="findText", defaultValue="") String findText,
            @RequestParam(value="findList", defaultValue="") String findList,
            @RequestParam(value="page", defaultValue="1") int page) {

        logger.info("selectBoardAll Start");

        int total = boardDAO.getTotal(findList, findText);
        logger.info("total값 : "+total);
        logger.info("findText값 :" + findText);
        logger.info("findList값 :" + findList);

        ArrayList<BoardVO> list = null;
        ArrayList<BoardVO> noticeList = null;

        PageNavigator navi = new PageNavigator(countPerPage, pagePerGroup, page, total);

        list = boardDAO.selectBoardAll(findText, findList, navi.getStartRecord(), navi.getCountPerPage());
        noticeList = boardDAO.selectNoticeAll();
        logger.info("게시글 리스트값 : "+noticeList);

        java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat("yyyy-MM-dd");

        String now = sf.format(new java.util.Date()); //오늘 날짜

        HashMap<String, Object> map = new HashMap<>();

        map.put("list", list);
        map.put("navi", navi);
        map.put("findList", findList);
        map.put("findText", findText);
        map.put("total", total);
        map.put("now", now);
        map.put("noticeList", noticeList);
        logger.info("selectBoardAll end");

        return map;
    }


    /*
     * @comment			: contact 게시판 글 하나 읽기 출력
     * @author			: 정현수
     * @param			: int boardnum
     * @return 			: board
     * @memo			: contactRead로 이동한다.(게시글 불러오기)
     */
    @RequestMapping (value="contactRead", method=RequestMethod.GET)
    public String readBoard (int boardnum, Model model) {

        logger.info("selectBoardOne Start");

        BoardVO board = null;

        board = boardDAO.selectBoardOne(boardnum);
        boardDAO.updateHits(boardnum);

        model.addAttribute("board", board);

        logger.info("selectBoardOne end");

        return "contact/contactRead";
    }

    /*
     * @comment			: contact 게시판 글 하나 수정하기
     * @author			: 여지원
     * @param			: 수정된 board 데이터값
     * @return 			: 결과 int (성공시 1, 실패시 0)
     */
    @RequestMapping(value="updateBoard", method=RequestMethod.POST)
    public String updateBoard(BoardVO board){

        logger.info("updateBoard Start");

        int result = boardDAO.updateBoard(board);
        logger.info("updateBoard end");

        return "redirect:../board/contactRead?boardnum="+board.getBoardnum();
    }


    /*
     * @comment			: contact 게시판 글 삭제
     * @author			: 여지원
     * @param			: boardnum
     * @return 			: 결과 int (성공시 1, 실패시 0)
     */
    @ResponseBody
    @RequestMapping(value="deleteBoard", method=RequestMethod.POST)
    public int deleteBoard(int boardnum){

        logger.info("게시글 번호 : " + boardnum);
        int result = 0;

        result = boardDAO.deleteBoard(boardnum);
        return result;
    }


    /*
     * @comment			: 댓글 등록
     * @author			: 여지원
     * @param			: CommentVO
     * @return 			: 결과 int (성공시 1, 실패시 0)
     */
    @ResponseBody
    @RequestMapping(value="insertComment", method=RequestMethod.POST)
    public int insertComment(CommentVO comment){

        logger.info("insertComment Start");

        int result =0;
        result = boardDAO.insertComment(comment);

        logger.info("insertComment End");
        return result;

    }



    /*
     * @comment			: 댓글 리스트 출력하기
     * @author			: 여지원
     * @param			: 게시판 넘버와 session값
     * @return 			: hashmap으로 엮어서 리턴
     */
    @ResponseBody
    @RequestMapping(value="commentList", method=RequestMethod.GET)
    public HashMap<String, Object> commentList(int boardnum, HttpSession session){

        String loginid = (String)session.getAttribute("userid");
        System.out.println("id 값 : " + loginid);
        ArrayList<CommentVO> list = null;
        list = boardDAO.commentList(boardnum);

        HashMap<String, Object> hashmap = new HashMap<>();
        hashmap.put("loginid", loginid);
        hashmap.put("list", list);


        System.out.println(hashmap);
        return hashmap;
    }




    /*
     * @comment			: 댓글 수정
     * @author			: 여지원
     * @param			: CommentVO
     * @return 			: 결과 int (성공시 1, 실패시 0)
     */
    @ResponseBody
    @RequestMapping(value="updateComment", method= RequestMethod.POST)
    public int updateComment(int commentnum,String com_content){

        logger.info("댓글 번호 : " + commentnum);
        logger.info("댓글 내용 : " + com_content);

        CommentVO comment = new CommentVO();
        comment.setCom_content(com_content);
        comment.setCommentnum(commentnum);

        int result = 0;

        result = boardDAO.updateComment(comment);

        logger.info("댓글 수정 성공 여부 : " + result);

        return result;
    }


    /*
     * @comment			: 댓글 삭제
     * @author			: 여지원
     * @param			: commentnum
     * @return 			: 결과 int (성공시 1, 실패시 0)
     */
    @ResponseBody
    @RequestMapping(value="deleteComment", method=RequestMethod.POST)
    public int deleteComment(int commentnum){

        logger.info("댓글 번호 : " + commentnum);

        int result = 0;

        result = boardDAO.deleteComment(commentnum);
        logger.info("댓글 삭제 여부 : " + result);
        return result;
    }

}

