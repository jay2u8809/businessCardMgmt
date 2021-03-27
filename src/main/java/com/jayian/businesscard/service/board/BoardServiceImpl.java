package com.jayian.businesscard.service.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Transactional
@Service
public class BoardServiceImpl implements BoardService {
    @Inject
    private SqlSession sqlSession;

    private static final Logger logger = LoggerFactory.getLogger(BoardDAO.class);


    /*
     * @comment		:	게시글 삽입 시 쓰이는 메소드
     * @param		:	BoardVO
     * @return		:	BoardVO 객체를 insert하고 int값 돌려줌(성공시 1, 실패시 0)
     * @author		:	여지원
     */
    public int insertBoard(BoardVO board) {

        logger.info("inserBoard start");

        int result = 0;

        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);

        try {

            result = mapper.insertBoard(board);
        } catch (Exception e) {

            e.printStackTrace();
        }

        logger.info("inserBoard end");

        return result;
    }


    /*
     * @comment		:	contact 게시판 전체 출력  메소드
     * @param		: 	findList, findText, startRecord, countPerPage
     * @return		:	list 츨력
     * @author		:	정현수
     */
    public ArrayList<BoardVO> selectBoardAll(String findText, String findList, int startRecord, int countPerPage) {

        logger.info("selectBoardAll start");

        ArrayList<BoardVO> list = null;
        RowBounds rb = new RowBounds(startRecord, countPerPage);

        HashMap<String, Object> map = new HashMap<>();
        map.put("findList", findList);
        map.put("findText", findText);

        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        System.out.println("맵 findList 값 : " + map.get("findList") );
        System.out.println("맵 findText 값 : " + map.get("findText") );

        try {

            list = mapper.selectBoardAll(map, rb);
        } catch (Exception e) {

            e.printStackTrace();
        }

        logger.info("selectBoardAll end");

        return list;
    }



    /*
     * @comment		:	selectNoticeAll 공지 게시글 전부 출력 메소드
     * @param		: 	check_notice
     * @return		:	noticeList 츨력
     * @author		:	정현수
     */
    public ArrayList<BoardVO> selectNoticeAll() {

        logger.info("selectBoardAll start");

        ArrayList<BoardVO> noticeList = null;
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);

        try {

            noticeList = mapper.selectNoticeAll();
        } catch (Exception e) {

            e.printStackTrace();
        }

        logger.info("selectBoardAll start");

        return noticeList;
    }



    /*
     * @comment		:	getTotal 게시글 갯수
     * @param		: 	findList, findText
     * @return		:	total 츨력
     * @author		:	정현수
     */
    public int getTotal(String findList, String findText) {

        logger.info("boardGetTotal Start");

        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);

        int total = 0;
        HashMap<String, Object> map = new HashMap<>();
        map.put("findList", findList);
        map.put("findText", findText);

        try {

            total = mapper.getTotal(map);
        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();
        }

        logger.info("boardGetTotal End");

        return total;
    }



    /*
     * @comment		:	contact 게시판 전체 출력  메소드
     * @return		:	list 츨력
     * @author		:	정현수
     */
    public BoardVO selectBoardOne(int boardnum) {

        logger.info("selectBoardOne start");

        BoardVO board = null;

        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);

        try {

            board = mapper.selectBoardOne(boardnum);
        } catch (Exception e) {

            e.printStackTrace();
        }

        logger.info("selectBoardOne end");

        return board;
    }



    /*
     * @comment		:	게시글 올리기
     * @return		:	-
     * @author		:	여지원
     */
    public void updateHits(int boardnum){

        logger.info("updateHits start");

        BoardVO board = null;

        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);

        try {

            mapper.updateHits(boardnum);
        } catch (Exception e) {

            e.printStackTrace();
        }

        logger.info("updateHits end");

    }


    /*
     * @comment		:	contact 게시판 수정하기
     * @return		:	int값 돌려줌(성공시 1, 실패시 0)
     * @author		:	여지원
     */
    public int updateBoard(BoardVO board){

        logger.info("updateBoard start");

        int result = 0;

        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);

        try {

            result = mapper.updateBoard(board);

        } catch (Exception e) {

            e.printStackTrace();
        }

        logger.info("updateBoard end");

        return result;
    }



    /*
     * @comment		:	contact 게시판 삭제
     * @return		:	int값 돌려줌(성공시 1, 실패시 0)
     * @author		:	여지원
     */
    public int deleteBoard(int boardnum){

        logger.info("deleteBoard start");

        int result = 0;

        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);

        try {

            result = mapper.deleteBoard(boardnum);
        } catch (Exception e) {

            e.printStackTrace();
        }

        logger.info("deleteBoard end");

        return result;
    }



    /*
     * @comment		:	댓글 등록
     * @return		:	int값 돌려줌(성공시 1, 실패시 0)
     * @author		:	여지원
     */
    public int insertComment(CommentVO comment){

        logger.info("insertComment start");

        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        int result = 0;

        try {

            result = mapper.insertComment(comment);

        } catch (Exception e) {

            e.printStackTrace();
        }

        logger.info("insertComment end");

        return result;
    };



    /*
     * @comment		:	댓글 리스트 불러오기
     * @return		:	ArrayList<CommentVO>
     * @author		:	여지원
     */
    public ArrayList<CommentVO> commentList(int boardnum){

        logger.info("commentList start");

        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);

        ArrayList<CommentVO> list = null;

        try {

            list = mapper.commentList(boardnum);
        } catch (Exception e) {

            e.printStackTrace();
        }

        logger.info("commentList end");

        return list;
    }



    /*
     * @comment		:	댓글 수정
     * @return		:	int값 돌려줌(성공시 1, 실패시 0)
     * @author		:	여지원
     */
    public int updateComment(CommentVO comment){

        logger.info("updateComment start");

        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);

        int result = 0;

        try {

            result = mapper.updateComment(comment);

        } catch (Exception e) {

            e.printStackTrace();
        }

        logger.info("updateComment end");

        return result;
    }



    /*
     * @comment		:	댓글 삭제
     * @return		:	int값 돌려줌(성공시 1, 실패시 0)
     * @author		:	여지원
     */
    public int deleteComment(int commentnum){

        logger.info("deleteComment start");

        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);

        int result = 0;

        try {

            result = mapper.deleteComment(commentnum);
        } catch (Exception e) {

            e.printStackTrace();
        }

        logger.info("deleteComment end");

        return result;

    }
}
