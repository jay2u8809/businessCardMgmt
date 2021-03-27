package com.jayian.businesscard.web.dto;

/*
 * @comment 내 명함 관리VO 객체
 * @author	정보승
 */

/*
CREATE TABLE tbl_mycardinfo (

	mycardnum			VARCHAR2(100)	PRIMARY KEY		-- 명함 등록 번호 : 식별자를 통해 제작 명함과 등록 명함을 구분 (ex. m01 : 제작, m11:등록, c01: 받은 명함)
	, userid			VARCHAR2(50)					-- 회원 ID : 회원정보 TABLE의 useid를 참조.
	, company			VARCHAR2(100)					-- 회사명
	, name1				VARCHAR2(100)					-- 성명
	, name2				VARCHAR2(100)					-- 성명
	, name3				VARCHAR2(100)					-- 성명
	, phone				VARCHAR2(100)					-- 연락처
	, tel				VARCHAR2(100)					-- 회사 전화번호
	, fax				VARCHAR2(100)					-- 팩스 번호
	, email				VARCHAR2(100)					-- E-mail 주소
	, address			VARCHAR2(200)					-- 회사 주소
	, job				VARCHAR2(100)					-- 직책, 직위, 직급
	, department		VARCHAR2(100)					-- 부서명
	, otherinfo			LONG							-- 기타 정보
	, otherinfo2		VARCHAR2(1000)					-- 기타 정보
	, frontimgsaved		VARCHAR2(300)	NOT NULL		-- 명함 앞면의 이미지 경로
	, backimgsaved		VARCHAR2(300)					-- 명함 뒷면의 이미지 경로
	, cardorder			NUMBER			DEFAULT 0		-- 명함의 순서(현재 사용하고 있는 명함의 숫자가 가장 크다)
	, frontimgoriginal	VARCHAR2(300)					-- 명함 앞면의 이미지 원본 파일명
	, backimgoriginal	VARCHAR2(300)					-- 명함 뒷면의 이미지 원본 파일명
);
 */

/**
 * @author SCITMASTER
 *
 */
public class MyCardInfoVO {

	/*
	 * Field
	 */
	private String mycardnum;			// 명함 등록 번호
	private String userid;				// 회원 ID
	private String company;				// 회사명
	private String name1;				// 성명
	private String name2;				// 성명
	private String name3;				// 성명
	private String phone;				// 연락처
	private String tel;					// 회사 전화번호
	private String fax;					// 회사 팩스 번호
	private String email;				// E-mail
	private String address;				// 회사 주소
	private String job;					// 직책, 직위, 직급
	private String department;			// 부서명
	private String otherinfo;			// 기타 정보
	private String otherinfo2;			// 제작한 명함의 DIV 코드 저장
	private String frontimgsaved;		// 명함 앞면의 이미지 경로
	private String backimgsaved;		// 명함 뒷면의 이미지 경로
	private int cardorder;				// 명함의 순서
	private String frontimgoriginal;	//
	private String backimgoriginal;		// 
	
	/*
	 * Constructor
	 */
	public MyCardInfoVO() {
		super();
	}
	
	
	/*
	 * Getter and Setter
	 */
	public String getMycardnum() 					{	return mycardnum;			}
	public void   setMycardnum(String mycardnum) 	{	this.mycardnum = mycardnum;	}
	
	public String getUserid() 				{	return userid;			}
	public void   setUserid(String userid) 	{	this.userid = userid;	}
	
	public String getCompany() 					{	return company;			}
	public void   setCompany(String company) 	{	this.company = company;	}
	
	public String getName1() 				{	return name1;		}
	public void   setName1(String name1) 	{	this.name1 = name1;	}

	public String getName2() 				{	return name2;		}
	public void   setName2(String name2) 	{	this.name2 = name2;	}

	public String getName3() 				{	return name3;		}
	public void   setName3(String name3) 	{	this.name3 = name3;	}
	
	public String getPhone() 				{	return phone;		}
	public void   setPhone(String phone) 	{	this.phone = phone;	}
	
	public String getTel() 				{	return tel;		}
	public void   setTel(String tel) 	{	this.tel = tel;	}
	
	public String getFax() 				{	return fax;		}
	public void   setFax(String fax) 	{	this.fax = fax;	}
	
	public String getEmail() 				{	return email;		}
	public void   setEmail(String email) 	{	this.email = email;	}
	
	public String getAddress() 					{	return address;			}
	public void   setAddress(String address) 	{	this.address = address;	}
	
	public String getJob() 				{	return job;		}
	public void   setJob(String job) 	{	this.job = job;	}
	
	public String getDepartment() 					{	return department;				}
	public void   setDepartment(String department) 	{	this.department = department;	}
	
	public String getOtherinfo() 					{	return otherinfo;			}
	public void   setOtherinfo(String otherinfo) 	{	this.otherinfo = otherinfo;	}
	
	public String getOtherinfo2() 					{	return otherinfo2;	}
	public void   setOtherinfo2(String otherinfo2) 	{	this.otherinfo2 = otherinfo2;	}

	public String getFrontimgsaved() 						{	return frontimgsaved;				}
	public void   setFrontimgsaved(String frontimgsaved) 	{	this.frontimgsaved = frontimgsaved;	}

	public String getBackimgsaved() {	return backimgsaved;	}
	public void   setBackimgsaved(String backimgsaved) {	this.backimgsaved = backimgsaved;	}

	public int  getCardorder() 				{	return cardorder;			}
	public void setCardorder(int cardorder) {	this.cardorder = cardorder;	}
	
	public String getFrontimgoriginal() 						{	return frontimgoriginal;					}
	public void   setFrontimgoriginal(String frontimgoriginal) 	{	this.frontimgoriginal = frontimgoriginal;	}

	public String getBackimgoriginal() 							{	return backimgoriginal;					}
	public void   setBackimgoriginal(String backimgoriginal) 	{	this.backimgoriginal = backimgoriginal;	}


	/*
	 * toString()
	 */
	@Override
	public String toString() {
		return "MyCardInfoVO [mycardnum=" + mycardnum + ", userid=" + userid + ", company=" + company + ", name1="
				+ name1 + ", name2=" + name2 + ", name3=" + name3 + ", phone=" + phone + ", tel=" + tel + ", fax=" + fax
				+ ", email=" + email + ", address=" + address + ", job=" + job + ", department=" + department
				+ ", otherinfo=" + otherinfo + ", otherinfo2=" + otherinfo2 + ", frontimgsaved=" + frontimgsaved
				+ ", backimgsaved=" + backimgsaved + ", cardorder=" + cardorder + ", frontimgoriginal="
				+ frontimgoriginal + ", backimgoriginal=" + backimgoriginal + "]";
	}


	
	
	
}
