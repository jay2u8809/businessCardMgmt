package com.jayian.businesscard.service.member;

import com.jayian.businesscard.domain.member.Member;

public interface MemberService {

    /**
     * register or update Member Info
     * @param member
     * @return
     */
    default Member saveMember(Member member) {
        return new Member();
    };

    /**
     * withdraw Member
     * @param memberSn
     * @return
     */
    default Long withdrawMember(Long memberSn) {
        return -1L;
    }

}
