package com.jayian.businesscard.service.businesscard;

import com.jayian.businesscard.JpaTestConfiguration;
import com.jayian.businesscard.common.CommonExtends;
import com.jayian.businesscard.domain.businesscard.BusinessCard;
import com.jayian.businesscard.domain.businesscard.BusinessCardRepository;
import com.jayian.businesscard.domain.member.Member;
import com.jayian.businesscard.domain.member.MemberRepository;
import com.jayian.businesscard.service.member.MemberService;
import com.jayian.businesscard.service.member.MemberServiceImpl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@ActiveProfiles("")
//@EnableJpaRepositories("a.b.c")
//@EntityScan("a.b.c")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JpaTestConfiguration.class)
@DataJpaTest
public class BusinessCardServiceImplTest extends CommonExtends {

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

    // Dummy Data List
    private List<BusinessCard> dummy;

    @BeforeEach
    public void preProcess() {
        businessCardService = new BusinessCardServiceImpl(businessCardRepository, factory);
        memberService = new MemberServiceImpl(memberRepository, factory);
        dummy = this.generateBusinessCardsDummy();
    }

    /**
     * Save a Business Card Test
     */
    @Test
    public void saveBusinessCard() {

        Member member = new Member();
        member.setMemberId("TEST_ID");
        member.setMemberPassword("TESTPW");
        Member savedMember = memberService.saveMember(member);

        logger.info("SAVED MEMBER : {}", savedMember.getMemberId());

        BusinessCard businessCard = new BusinessCard();
        businessCard.setCompanyName("TEST COMPANY");
        businessCard.setEmailAddress("TESTID@TEST.com");
        businessCard.setMember(savedMember);
        BusinessCard saved = businessCardService.saveBusinessCard(businessCard);

        logger.info("SAVED BUSINESS : {}", saved.getBusinessCardSn());
    }

    /**
     * Remove a Business Card Test
     */
    @Test
    public void deleteBusinessCardTest() {

        boolean successResult = businessCardService.deleteBusinessCard(8L);
        boolean failResult = businessCardService.deleteBusinessCard(100L);

        logger.info("DELETE BUSINESS CARD SUCCESS : {}", successResult);
        logger.info("DELETE BUSINESS CARD FAIL {}", failResult);
    }

    /**
     * Remove Business Card List Test
     */
    @Test
    void deleteBusinessCardsTest() {

        List<Long> cardSnList1 = this.dummy.stream()
                                    .map(BusinessCard::getBusinessCardSn)
                                    .filter(businessCardSn -> businessCardSn > 5L)
                                    .collect(Collectors.toList());
        Integer successCnt1 = businessCardService.deleteBusinessCards(1L, cardSnList1);
        logger.info("DELETE BUSINESS CARDS ALL : {}", successCnt1);

        List<Long> cardSnList2 = this.dummy.stream()
                                    .map(BusinessCard::getBusinessCardSn)
                                    .filter(businessCardSn -> businessCardSn > 2L)
                                    .collect(Collectors.toList());
        Integer successCnt2 = businessCardService.deleteBusinessCards(1L, cardSnList2);
        logger.info("DELETE BUSINESS CARDS ALL : {}", successCnt2);

//        List<Long> cardSnList1 = this.dummy.stream()
//                                    .map(BusinessCard::getBusinessCardSn)
//                                    .filter(businessCardSn -> businessCardSn > 5L)
//                                    .collect(Collectors.toList());
//        Integer successCnt1 = businessCardService.deleteBusinessCards(3L, cardSnList1);
//        logger.info("DELETE BUSINESS CARDS FAIL : {}", successCnt1);
    }

    /**
     * Generate Dummy Data
     * @return
     */
    private List<BusinessCard> generateBusinessCardsDummy() {

        Member member = new Member();
        member.setMemberId("TEST_ID");
        member.setMemberPassword("TESTPW");
        Member savedMember = memberService.saveMember(member);

        List<BusinessCard> result = new ArrayList<>();

        int idx = 0;
        while(idx < 10) {
            BusinessCard businessCard = new BusinessCard();
            businessCard.setCompanyName("TEST COMPANY" + idx);
            businessCard.setEmailAddress("TESTID@TEST.com" + idx);
            businessCard.setMember(savedMember);
            BusinessCard saved = businessCardService.saveBusinessCard(businessCard);
            result.add(saved);
            idx++;
        }

        return result;
    }

}