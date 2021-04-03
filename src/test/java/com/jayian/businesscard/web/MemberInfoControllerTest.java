package com.jayian.businesscard.web;

import com.jayian.businesscard.JpaTestConfiguration;
import com.jayian.businesscard.common.dto.CommonExtends;
import com.jayian.businesscard.domain.member.Member;
import com.jayian.businesscard.domain.member.MemberRepository;
import com.jayian.businesscard.service.member.MemberService;
import com.jayian.businesscard.service.member.MemberServiceImpl;
import com.jayian.businesscard.web.dto.MemberDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Import(JpaTestConfiguration.class)
@RunWith(SpringRunner.class)
//@WebMvcTest(controllers = MemberInfoController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberInfoControllerTest extends CommonExtends {

    @LocalServerPort
    private int port;

    @Autowired
    private JPAQueryFactory factory;
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MemberRepository memberRepository;
    @Autowired(required = false)
    private MemberService memberService;

    @BeforeEach
    public void preProcess() {
        memberService = new MemberServiceImpl(memberRepository, factory);
    }

    @Test
    public void registerMemberTest() {

        // given
        String memberId = "TEST_ID";
        String memberPw = "TEST_PW";

        MemberDto memberDto = new MemberDto();
        memberDto.setMemberId(memberId);
        memberDto.setMemberPassword(memberPw);

        String url = "http://localhost:" + port + "/api/v1/member/register/";

        // when
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(url, memberDto, Map.class);

        //then
        Map<String, Long> ret = responseEntity.getBody();
        Long memberSn = 0L;
        if (ret.get("memberSn") instanceof Long) {
            logger.debug("LONG TYPE");
        }
        if (ret.get("memberSn") instanceof Number) {
            logger.debug("NUMBER TYPE");
            memberSn = Long.valueOf(String.valueOf(ret.get("memberSn")));
        }

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(memberSn).isGreaterThan(0L);

        Member member = memberService.getNormalMember(memberSn);
        assertThat(member.getMemberId()).isEqualTo(memberId);
        assertThat(member.getMemberPassword()).isEqualTo(memberPw);
    }
}