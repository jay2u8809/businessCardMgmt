package com.jayian.businesscard.web;

import com.jayian.businesscard.common.dto.CommonExtends;
import com.jayian.businesscard.domain.member.Member;
import com.jayian.businesscard.service.member.MemberService;
import com.jayian.businesscard.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class MemberInfoController extends CommonExtends {

    private final MemberService memberService;


    /**
     * move member registration page
     * @param model
     * @return
     */
    @GetMapping("/api/v1/member/register/")
    public String registerMember(Model model) {

        logger.debug("Move Register Member Page");

        return "register_member";
    }

    /**
     * register member data
     * @param memberDto
     * @return
     */
    @PostMapping("/api/v1/member/register/")
    @ResponseBody
    public ResponseEntity<?> registerMember(@RequestBody MemberDto memberDto) {

        logger.debug("Register Member ID : {}", memberDto.getMemberId());

        Member save = memberDto.saveMember();
        // TODO Password Process
        Map<String, Long> result = new HashMap<>();
        result.put("memberSn", memberService.saveMember(save).getMemberSn());

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    /**
     * verifying Email
     * @param memberId
     * @return
     */
    @PatchMapping("/api/vi/member/verify/")
    public ResponseEntity<?> verifyMember(@RequestParam String memberId) {

        logger.debug("Member Verified : {}", memberId);

        return ResponseEntity.ok(memberId);
    }

    // Login

    // Logout

    // update member data

    // get member data

    // withdraw

}
