package com.jayian.businesscard.web.dto;

/*
 * @comment : 문의 게시판의 댓글 vo
   @author :  여지원
*/
public class CommentVO {

	private int boardnum;
	private int commentnum;
	private String com_writer;
	private String com_content;
	private String com_date;

	public CommentVO() {
		super();
	}

	public CommentVO(int boardnum, int commentnum, String com_writer, String com_content, String com_date) {
		super();
		this.boardnum = boardnum;
		this.commentnum = commentnum;
		this.com_writer = com_writer;
		this.com_content = com_content;
		this.com_date = com_date;
	}

	public int getBoardnum() {
		return boardnum;
	}

	public void setBoardnum(int boardnum) {
		this.boardnum = boardnum;
	}

	public int getCommentnum() {
		return commentnum;
	}

	public void setCommentnum(int commentnum) {
		this.commentnum = commentnum;
	}

	public String getCom_writer() {
		return com_writer;
	}

	public void setCom_writer(String com_writer) {
		this.com_writer = com_writer;
	}

	public String getCom_content() {
		return com_content;
	}

	public void setCom_content(String com_content) {
		this.com_content = com_content;
	}

	public String getCom_date() {
		return com_date;
	}

	public void setCom_date(String com_date) {
		this.com_date = com_date;
	}

	@Override
	public String toString() {
		return "CommentVO [boardnum=" + boardnum + ", commentnum=" + commentnum + ", com_writer=" + com_writer
				+ ", com_content=" + com_content + ", com_date=" + com_date + "]";
	}

}
