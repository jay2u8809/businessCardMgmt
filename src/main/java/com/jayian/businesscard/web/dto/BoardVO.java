package com.jayian.businesscard.web.dto;
/*
 * 	
@comment Contact 화면에서 게시글 올리기 위해 만든 BoardVO
@author 여지원

create table tbl_board (
	    boardnum           number              primary key                --- 보드고유넘버 (primary key)
	    , board_writer     varchar2(100)       not null                   --- 글쓴이(id) , session값에서 값 가져오는 값
	    , board_title      varchar2(100)       not null                   --- 게시글제목
	    , board_content    varchar2(3000)      not null                   --- 게시글내용 
	    , board_hits       number			   default 0                  --- 조회수
	    , board_inputdate  date                default sysdate            --- 게시한 날짜
	    , check_notice     char(1)			   default 'N'                --- 게시글 중 공지글인 것은 가장 위로 올리기 위해, 'N'이면 일반 게시글/ 'Y'이면 공지글
	);
*/

public class BoardVO {

	private int boardnum;
	private String board_writer;
	private String board_title;
	private String board_content;
	private int board_hits;
	private String board_inputdate;
	private String check_notice;

	public BoardVO() {
		super();
	}

	public BoardVO(int boardnum, String board_writer, String board_title, String board_content, int board_hits,
			String board_inputdate, String check_notice) {
		super();
		this.boardnum = boardnum;
		this.board_writer = board_writer;
		this.board_title = board_title;
		this.board_content = board_content;
		this.board_hits = board_hits;
		this.board_inputdate = board_inputdate;
		this.check_notice = check_notice;
	}

	public int getBoardnum() {
		return boardnum;
	}

	public void setBoardnum(int boardnum) {
		this.boardnum = boardnum;
	}

	public String getBoard_writer() {
		return board_writer;
	}

	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public int getBoard_hits() {
		return board_hits;
	}

	public void setBoard_hits(int board_hits) {
		this.board_hits = board_hits;
	}

	public String getBoard_inputdate() {
		return board_inputdate;
	}

	public void setBoard_inputdate(String board_inputdate) {
		this.board_inputdate = board_inputdate;
	}

	public String getCheck_notice() {
		return check_notice;
	}

	public void setCheck_notice(String check_notice) {
		this.check_notice = check_notice;
	}

	@Override
	public String toString() {
		return "BoardVO [boardnum=" + boardnum + ", board_writer=" + board_writer + ", board_title=" + board_title
				+ ", board_content=" + board_content + ", board_hits=" + board_hits + ", board_inputdate="
				+ board_inputdate + ", check_notice=" + check_notice + "]";
	}

}
