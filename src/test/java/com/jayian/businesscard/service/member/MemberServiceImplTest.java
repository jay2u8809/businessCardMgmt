package com.jayian.businesscard.service.member;

import com.jayian.businesscard.JpaTestConfiguration;
import com.jayian.businesscard.common.dto.CommonExtends;
import com.jayian.businesscard.common.dto.EmbeddedName;
import com.jayian.businesscard.domain.businesscard.BusinessCardRepository;
import com.jayian.businesscard.domain.member.Member;
import com.jayian.businesscard.domain.member.MemberRepository;
import com.jayian.businesscard.service.businesscard.BusinessCardService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

@Import(JpaTestConfiguration.class)
@DataJpaTest
class MemberServiceImplTest extends CommonExtends {

    @Autowired
    private JPAQueryFactory factory;

    @Autowired
    private BusinessCardRepository businessCardRepository;
    @Autowired(required = false)
    private BusinessCardService businessCardService;

    @Autowired
    private MemberRepository memberRepository;
    @Autowired(required = false)
    private MemberService memberService;

    private List<Member> dummyMembers;

    @BeforeEach
    public void preProcess() {
        memberService = new MemberServiceImpl(memberRepository, factory);
        this.dummyMembers = this.generateMembersDummy();
    }

    @Test
    public void saveMemberTest() {

        Member member = new Member();
        member.setMemberId("TESTID");
        member.setMemberPassword("TESTPASSWORD");
        EmbeddedName name = new EmbeddedName();
        name.setSurName("TEST SUR");
        name.setGivenName("TEST GIVEN");
        member.setMemberName(name);
        Member saved = memberService.saveMember(member);

        logger.info("SAVED MEMBER TEST : {}", saved.getMemberSn());
    }

    @Test
    public void withdrawMemberTest() {

        Long successMemberSn = memberService.withdrawMember(5L);
        Long failMemberSn = memberService.withdrawMember(50L);

        logger.info("WITHDRAW MEMBER TEST SUCCESS : {}", successMemberSn);
        logger.info("WITHDRAW MEMBER TEST FAIL : {}", failMemberSn);
    }

    /**
     * Generate Dummy Data
     * @return
     */
    private List<Member> generateMembersDummy() {

        List<Member> result = new ArrayList<>();

        int idx = 0;
        while(idx < 10) {
            Member member = new Member();
            member.setMemberId("TEST_ID" + idx);
            member.setMemberPassword("TESTPW");
            Member savedMember = memberService.saveMember(member);
            result.add(savedMember);
            idx++;
        }

        return result;
    }

}