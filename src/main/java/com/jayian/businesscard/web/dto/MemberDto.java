package com.jayian.businesscard.web.dto;

import com.jayian.businesscard.common.dto.EmbeddedName;
import com.jayian.businesscard.domain.member.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class MemberDto {

	private Long memberSn;
	private String memberId;
	private String memberPassword;
	private String memberName1;
	private String memberName2;
	private String memberName3;
	private String memberName4;

	public Member saveMember () {
		Member member = new Member();
		member.setMemberId(this.memberId);
		member.setMemberName(new EmbeddedName(this.memberName1, this.memberName2, this.memberName3, this.memberName4));
		return member;
	}
}