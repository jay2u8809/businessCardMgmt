package com.jayian.businesscard.web.dto;

/*
 * @comment 제작한 명함의 레이아웃VO 객체
 * @author	정보승
 */

/*
CREATE TABLE tbl_cardlayout (

	layoutnum		NUMBER			PRIMARY KEY					-- 의미없는 구분 번호 Sequence
	, mycardnum		VARCHAR2(100)								-- 내 명함의 등록 번호 : 본인 명함 관리 TABLE의 mycardnum을 참조
	, xposition		NUMBER			NOT NULL					-- x위치
	, yposition		NUMBER			NOT NULL					-- y위치
	, zposition		NUMBER			DEFAULT 1					-- z위치 : 레이아웃의 겹쳐지는 높이를 의미. 배경이미지 값이 0
	, font			VARCHAR2(50)								-- 글자체
	, fontsize		NUMBER										-- 글차크기
	, fontcolor		VARCHAR2(100)								-- 레이아웃 객체의 색 : 글자의 경우 폰트의 색, 이미지의 경우 배경의 색
	, rotation		NUMBER			DEFAULT 0					-- 객체의 회전각도
	, backgrimg		VARCHAR2(300)								-- 이미지, 템플릿 경로
	, memo			VARCHAR2(100)								-- 텍스트 박스의 경우 텍스트 내용을 저장. 이 값이 null이면 이미지, 템플릿
	
);
 */

public class CardLayoutVO {

	/*
	 * Field
	 */
	private int layoutnum;				// 레이아웃 번호
	private String mycardnum;			// 내 명함 등록 번호
	private int xposition;				// 레이아웃의 x위치
	private int yposition;				// 레이아웃의 y위치
	private int zposition;				// 레이아웃의 z위치
	private String font;				// 글자체
	private int fontsize;				// 글자크기
	private String fontcolor;			// 레이아웃 객체의 색
	private int rotation;				// 객체의 회전각도
	private String backgrimg;			// 이미지, 템플릿 경로
	private String memo;				// 객체의 내용
	
	
	/*
	 * Constructor
	 */
	public CardLayoutVO() {
		super();
	}
	
	
	/*
	 * Getter and Setter
	 */
	public int  getLayoutnum() 				{	return layoutnum;			}
	public void setLayoutnum(int layoutnum) {	this.layoutnum = layoutnum;	}
	
	public String getMycardnum() 					{	return mycardnum;			}
	public void   setMycardnum(String mycardnum) 	{	this.mycardnum = mycardnum;	}
	
	public int  getXposition() 				{	return xposition;			}
	public void setXposition(int xposition) {	this.xposition = xposition;	}
	
	public int  getYposition() 				{	return yposition;			}
	public void setYposition(int yposition) {	this.yposition = yposition;	}
	
	public int  getZposition() 				{	return zposition;			}
	public void setZposition(int zposition) {	this.zposition = zposition;	}
	
	public String getFont() 			{	return font;		}
	public void   setFont(String font) 	{	this.font = font;	}
	
	public int  getFontsize() 				{	return fontsize;			}
	public void setFontsize(int fontsize) 	{	this.fontsize = fontsize;	}
	
	public String getFontcolor() 					{	return fontcolor;			}
	public void   setFontcolor(String fontcolor) 	{	this.fontcolor = fontcolor;	}
	
	public int  getRotation() 				{	return rotation;			}
	public void setRotation(int rotation) 	{	this.rotation = rotation;	}
	
	public String getBackgrimg() 					{	return backgrimg;			}
	public void   setBackgrimg(String backgrimg) 	{	this.backgrimg = backgrimg;	}
	
	public String getMemo() 			{	return memo;		}
	public void   setMemo(String memo) 	{	this.memo = memo;	}
	
	
	/*
	 * toString()
	 */
	@Override
	public String toString() {
		return "CardLayoutVO [layoutnum=" + layoutnum + ", mycardnum=" + mycardnum + ", xposition=" + xposition
				+ ", yposition=" + yposition + ", zposition=" + zposition + ", font=" + font + ", fontsize=" + fontsize
				+ ", fontcolor=" + fontcolor + ", rotation=" + rotation + ", backgrimg=" + backgrimg + ", memo=" + memo
				+ "]";
	}
	
}
