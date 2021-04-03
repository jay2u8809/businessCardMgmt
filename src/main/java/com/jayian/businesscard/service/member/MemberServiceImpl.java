package com.jayian.businesscard.service.member;

import com.jayian.businesscard.common.code.UseYN;
import com.jayian.businesscard.common.dto.CommonExtends;
import com.jayian.businesscard.domain.member.Member;
import com.jayian.businesscard.domain.member.MemberRepository;
import com.jayian.businesscard.domain.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberServiceImpl extends CommonExtends implements MemberService {

    private final MemberRepository memberRepository;

    private final JPAQueryFactory factory;

    @Override
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Long withdrawMember(Long memberSn) {
        Member withdrawalMember = memberRepository.findById(memberSn)
                                                .orElse(null);

        if (withdrawalMember == null)   return 0L;

        withdrawalMember.setWithdrawalYn(UseYN.Y);
        return memberRepository.save(withdrawalMember).getMemberSn();
    }

    @Override
    public Member getNormalMember(Long memberSn) {
        QMember qMember = QMember.member;
        Member member = factory.query()
                        .select(qMember)
                        .from(qMember)
                        .where(qMember.memberSn.eq(memberSn)
                                .and(qMember.withdrawalYn.eq(UseYN.N))
//                                .and(qMember.emailVerifyYn.eq(YN.Y))
                        ).fetchFirst();
        return member;
    }

}
