package com.jayian.businesscard.web.dto;

/*
 * @comment 받은 명함관리 VO 객체
 * @author	정보승
 */

/*
CREATE TABLE tbl_yourcardinfo (

	yourcardnum			VARCHAR2(100)	PRIMARY KEY		-- 다른 사람에게 받은 명함의 등록번호 : 받은 사람의 명함 등록 번호를 따로 부여 (ex.  c01: 받은 명함)
	, userid			VARCHAR2(50)					-- 회원 ID : 회원정보 TABLE의 useid를 참조
	, yournum			VARCHAR2(50)					-- 명함을 준 사람들의 번호 : 받은 사람의 번호를 부여해서 명함을 그룹화(동명이인의 경우를 대비) (ex. you01)
	, mycardnum			VARCHAR2(100)					-- 내 명함의 등록 번호 : 본인 명함 관리 TABLE의 mycardnum을 참조
	, memo				VARCHAR2(500)					-- 간단한 메모를 저장
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
	, otherinfo			VARCHAR2(200)					-- 기타 정보
	, frontimgsaved		VARCHAR2(300)	NOT NULL		-- 명함 앞면의 이미지 파일명 : OCR로 판독하기 위한 사진의 앞쪽 명함 이미지 경로
	, backimgsaved		VARCHAR2(300)					-- 명함 뒷면의 이미지 파일명 : OCR로 판독하기 위한 사진의 뒤쪽 명함 이미지 경로
	, cardorder			NUMBER			DEFAULT 0		-- 명함의 순서(현재 사용하고 있는 명함의 숫자가 가장 크다)
	, sex				CHAR(1)			DEFAULT 'M'		-- 명함 주인의 성별
	, frontimgoriginal	VARCHAR2(300)					-- 명함 앞면의 이미지 원본 파일명
	, backimgoriginal	VARCHAR2(300)					-- 명함 뒷면의 이미지 원본 파일명
	
	
);
 */
public class YourCardInfoVO {
	
	/*
	 * Field
	 */
	private String yourcardnum;			// 다른 사람에게 받은 명함의 등록번호
	private String userid;				// 회원 ID
	private String yournum;				// 명함을 준 사람들의 번호
	private String mycardnum;			// 명함 등록 번호
	private String memo;				// 간단한 메모를 저장
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
	private String frontimgsaved;		// 명함 앞면의 저장된 이름
	private String backimgsaved;		// 명함 뒷면의 저장된 이름
	private int cardorder;				// 명함의 순서
	private char sex;					// 명함 주인의 성별
	private String frontimgoriginal;	// 명함 앞면의 업로드(원본)이름
	private String backimgoriginal;		// 명함 뒷면의 업로드(원본)이름

	
	/*
	 * Constructor
	 */
	public YourCardInfoVO() {
		super();
	}
	
	
	/*
	 * Getter and Setter
	 */
	public String getYourcardnum() 						{	return yourcardnum;				}
	public void   setYourcardnum(String yourcardnum) 	{	this.yourcardnum = yourcardnum;	}
	
	public String getUserid() 				{	return userid;			}
	public void   setUserid(String userid) 	{	this.userid = userid;	}
	
	public String  	getYournum() 				{	return yournum;			}
	public void 	setYournum(String yournum) 	{	this.yournum = yournum;	}
	
	public String getMycardnum() 					{	return mycardnum;			}
	public void   setMycardnum(String mycardnum) 	{	this.mycardnum = mycardnum;	}
	
	public String getMemo() 			{	return memo;		}
	public void   setMemo(String memo) 	{	this.memo = memo;	}
	
	public String getCompany() 					{	return company;			}
	public void   setCompany(String company) 	{	this.company = company;	}
	
	public String getName1() 				{	return name1;		}
	public void   setName1(String name1) 	{	this.name1 = name1;	}

	public String getName2() 				{	return name2;		}
	public void   setName2(String name2) 	{	this.name2 = name2;	}

	public String getName3() 				{	return name3;		}
	public void   setName3(String name3) 	{	this.name3 = name3;	}

	public String getPhone() 				{	return phone;		}
	public void   setPhone(String phone) 	{	
		
		if(phone == null) 	{	this.phone = "";	}
		else 				{	this.phone = phone;	}
	}
	
	public String getTel() 				{	return tel;		}
	public void   setTel(String tel) 	{	
		
		if(tel == null) 	{	this.tel = "";		}
		else 				{	this.tel = tel;		}
	}
	
	public String getFax() 				{	return fax;		}
	public void   setFax(String fax) 	{	
		
		if(fax == null) 	{	this.fax = "";		}
		else 				{	this.fax = fax;		}
	}
	
	public String getEmail() 				{	return email;		}
	public void   setEmail(String email) 	{	
		
		if(email == null) 	{	this.email = "";	}
		else 				{	this.email = email;	}
	}
	
	public String getAddress() 					{	return address;			}
	public void   setAddress(String address) 	{	
		
		if(address == null) {	this.address = "";		}
		else 				{	this.address = address;	}
	}
	
	public String getJob() 				{	return job;		}
	public void   setJob(String job) 	{	
		
		if(job == null) 	{	this.job = "";		}
		else 				{	this.job = job;		}
	}
	
	public String getDepartment() 					{	return department;				}
	public void   setDepartment(String department) 	{	
		
		if(department == null) 	{	this.department = "";			}
		else 					{	this.department = department;	}
	}
	
	public String getOtherinfo() 					{	return otherinfo;			}
	public void   setOtherinfo(String otherinfo) 	{	
		
		if(otherinfo == null) 	{	this.otherinfo = "";		}
		else 					{	this.otherinfo = otherinfo;	}
	}
	
	public String getFrontimgsaved() 						{	return frontimgsaved;				}
	public void   setFrontimgsaved(String frontimgsaved) 	{	
		
		// 명함 이미지가 없을 경우 샘플 명함 이미지로 대체
		if(frontimgsaved == null) {
			
			frontimgsaved = "sample5_F.png";
		}
		else {
			
			this.frontimgsaved = frontimgsaved;
		}
		
	}

	public String getBackimgsaved() 					{	return backimgsaved;				}
	public void   setBackimgsaved(String backimgsaved) 	{	
		
		if(backimgsaved == null) 	{	this.backimgsaved = "";				}
		else 						{	this.backimgsaved = backimgsaved;	}
	}
	
	public int  getCardorder() 				{	return cardorder;			}
	public void setCardorder(int cardorder) {	this.cardorder = cardorder;	}

	public char getSex() 			{	return sex;		}
	public void setSex(char sex) 	{	this.sex = sex;	}

	public String getFrontimgoriginal() 						{	return frontimgoriginal;					}
	public void   setFrontimgoriginal(String frontimgoriginal) 	{	
		
		if(frontimgoriginal == null) 	{	this.frontimgoriginal = "";					}
		else 							{	this.frontimgoriginal = frontimgoriginal;	}
	}

	public String getBackimgoriginal() 							{	return backimgoriginal;					}
	public void   setBackimgoriginal(String backimgoriginal) 	{	
		
		if(backimgoriginal == null) 	{	this.backimgoriginal = "";				}
		else 							{	this.backimgoriginal = backimgoriginal;	}
	}


	/*
	 * toString()
	 */
	@Override
	public String toString() {
		return "YourCardInfoVO [yourcardnum=" + yourcardnum + ", userid=" + userid + ", yournum=" + yournum
				+ ", mycardnum=" + mycardnum + ", memo=" + memo + ", company=" + company + ", name1=" + name1
				+ ", name2=" + name2 + ", name3=" + name3 + ", phone=" + phone + ", tel=" + tel + ", fax=" + fax
				+ ", email=" + email + ", address=" + address + ", job=" + job + ", department=" + department
				+ ", otherinfo=" + otherinfo + ", frontimgsaved=" + frontimgsaved + ", backimgsaved=" + backimgsaved
				+ ", cardorder=" + cardorder + ", sex=" + sex + ", frontimgoriginal=" + frontimgoriginal
				+ ", backimgoriginal=" + backimgoriginal + "]";
	}


	
	
	
}
