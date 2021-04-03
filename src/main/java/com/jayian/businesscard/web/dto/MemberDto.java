package com.jayian.businesscard.web.dto;

import com.jayian.businesscard.common.dto.EmbeddedAddress;
import com.jayian.businesscard.common.dto.EmbeddedName;
import com.jayian.businesscard.common.dto.EmbeddedTel;
import com.jayian.businesscard.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MemberDto {

	private Long memberSn;
	private String memberId;
	private String memberPassword;
	private String memberName1;
	private String memberName2;
	private String memberName3;
	private String memberName4;
	private String zipCode;
	private String address1;
	private String address2;
	private String address3;
	private String address4;
	private String phoneNo;

	public Member saveMember () {
		Member member = new Member();
		member.setMemberId(this.memberId);
		member.setMemberPassword(this.memberPassword);
		member.setMemberName(new EmbeddedName(this.memberName1, this.memberName2, this.memberName3, this.memberName4));
		member.setMemberAddress(new EmbeddedAddress(null, this.zipCode, this.address1, this.address2, this.address3, this.address4));
		member.setMemberTel(new EmbeddedTel(this.phoneNo));
		return member;
	}
}