package com.jayian.businesscard.service.member;

import com.jayian.businesscard.common.code.UseYN;
import com.jayian.businesscard.common.dto.CommonExtends;
import com.jayian.businesscard.domain.member.Member;
import com.jayian.businesscard.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberServiceImpl extends CommonExtends implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member saveMember(Member member) {

        return memberRepository.save(member);
    }

    @Override
    public boolean withdrawMember(Long memberSn) {
        Member withdrawalMember = memberRepository.findById(memberSn)
                                                .orElse(null);

        if (withdrawalMember == null)   return false;

        withdrawalMember.setWithdrawalYn(UseYN.Y);
        memberRepository.save(withdrawalMember);

        return true;
    }

}
