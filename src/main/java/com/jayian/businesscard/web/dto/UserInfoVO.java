package com.jayian.businesscard.web.dto;

 

/*

 * @comment 회원VO 객체

 * @author	정보승

 */

 

/*

CREATE TABLE tbl_userinfo (

 

    userid          VARCHAR2(50)    PRIMARY KEY     -- 회원 ID

    , userpw        VARCHAR2(100)   NOT NULL        -- 회원 비밀번호

    , username      VARCHAR2(100)   NOT NULL        -- 회원 이름

    , emailverify	CHAR(1)			DEFAULT	'N'		-- E-mail 인증 여부

    

);

 */

 

 

public class UserInfoVO {

	

	/*

	 * Field

	 */

	private String userid;			// 회원 ID

	private String userpw;			// 회원 비밀번호

	private String username;		// 회원 이름

	private char emailverify;		// E-mail 인증 여부

	private String profilecard;		// 대표프로필사진

	

	/*

	 * Constructor

	 */

	public UserInfoVO() {

		super();

	}

 

	public UserInfoVO(String userid, String userpw, String username, char emailverify, String profilecard) {

		super();

		this.userid = userid;

		this.userpw = userpw;

		this.username = username;

		this.emailverify = emailverify;

		this.profilecard = profilecard;

	}

	

	/*

	 * Getter and Setter

	 */

	public String getUserid() 				{	return userid;			}
	public void   setUserid(String userid) 	{	this.userid = userid;	}

	public String getUserpw() 				{	return userpw;			}
	public void   setUserpw(String userpw) 	{	this.userpw = userpw;	}

	public String getUsername() 				{	return username;			}
	public void   setUsername(String username) 	{	this.username = username;	}

	public char getEmailverify() 					{	return emailverify;				}
	public void setEmailverify(char emailverify) 	{	this.emailverify = emailverify;	}
	
	public String getProfilecard() 				   { return profilecard; }
	public void setProfilecard(String profilecard) { this.profilecard = profilecard; }


	@Override

	public String toString() {

		return "UserInfoVO [userid=" + userid + ", userpw=" + userpw + ", username=" + username + ", emailverify="
				+ emailverify + ", profilecard=" + profilecard + "]";

	}

 

	

}