package com.jayian.businesscard.service.board;

import org.springframework.stereotype.Service;

import java.util.HashMap;

public interface BoardService {

    public int insertBoard(BoardVO board);

    public ArrayList<BoardVO> selectBoardAll(HashMap<String, Object> map, RowBounds rb); // 2018-03-28-1123 : 정현수 작성, contact 게시글 전부 출력

    public ArrayList<BoardVO> selectNoticeAll(); //2018-04-23-1849 : selectNoticeAll 공지 게시글 전부 출력

    public int getTotal(HashMap<String, Object> map);// 2018-03-31-1024 : 정현수 작성, contact 게시글 전체 수 출력

    public BoardVO selectBoardOne(int boardnum);  // 2018-03-28-1923 : 정현수 작성, contact 게시글 하나만 읽기

    public void updateHits(int boardnum); //2018-04-03 : 여지원 작성, 조회수 올리기

    public int updateBoard(BoardVO board); //2018-03-30-0920 : 여지원 작성, 게시글 수정하기

    public int deleteBoard(int boardnum); //2018-04-02 : 여지원 작성, 게시글 삭제하기

    public int insertComment(CommentVO comment); //2018-03-30 1042 : 여지원 작성, 댓글 입력하기

    public ArrayList<CommentVO> commentList(int boardnum); //2018-03-30 1056 : 여지원 작성, 해당 게시글의 댓글 리스트 불러오기

    public int updateComment(CommentVO comment);//2018-04-03  여지원 작성, 댓글 수정

    public int deleteComment(int commentnum);//2018-04-03  여지원 작성, 댓글 삭제
}
