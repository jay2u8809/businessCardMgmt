package com.jayian.businesscard.web.dto.member;

import com.jayian.businesscard.common.dto.EmbeddedAddress;
import com.jayian.businesscard.common.dto.EmbeddedName;
import com.jayian.businesscard.common.dto.EmbeddedTel;
import com.jayian.businesscard.common.utils.EncryptUtils;
import com.jayian.businesscard.common.validator.CommonValidator;
import com.jayian.businesscard.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class MemberDto implements Serializable {

	private Long memberSn;
	private String memberId;
	private String memberPassword;
	private String memberName1;
	private String memberName2;
	private String memberName3;
	private String memberName4;
	private String memberEmail;
	private String zipCode;
	private String address1;
	private String address2;
	private String address3;
	private String address4;
	private String phoneNo;

	public Member saveMember () {
		Member member = new Member();
		member.setMemberId(this.memberId);
		member.setMemberPassword(ObjectUtils.isEmpty(this.memberPassword) ? "" : EncryptUtils.encryptSha256(this.memberPassword));
		member.setMemberName(new EmbeddedName(this.memberName1, this.memberName2, this.memberName3, this.memberName4));
		member.setMemberEmail(ObjectUtils.isEmpty(this.memberEmail) ? "" : this.memberEmail);
		member.setMemberAddress(new EmbeddedAddress(null, this.zipCode, this.address1, this.address2, this.address3, this.address4));
		member.setMemberTel(new EmbeddedTel(this.phoneNo));
		return member;
	}

	@Component
	public static class Validator extends CommonValidator {

		@Override
		public boolean supports(Class<?> clazz) {
			return MemberDto.class.isAssignableFrom(clazz);
		}

		@Override
		public void validate(Object target, Errors errors) {
			MemberDto memberDto = (MemberDto) target;

			checkMemberName(errors, memberDto.getMemberName1());
		}
	}
}