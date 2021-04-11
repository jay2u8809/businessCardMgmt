package com.jayian.businesscard.web.controller.member;

import com.jayian.businesscard.common.CommonConst;
import com.jayian.businesscard.common.CommonExtends;
import com.jayian.businesscard.common.utils.CookieUtils;
import com.jayian.businesscard.domain.member.Member;
import com.jayian.businesscard.service.member.MemberService;
import com.jayian.businesscard.web.dto.member.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class MemberInfoController extends CommonExtends {

    private final MemberService memberService;

    private final MemberDto.Validator memberDtoValidator;

    private final String REGISTER_DATA = "registerData";

    @GetMapping("/api/v1/guest/login/")
    public String moveLogin(Model model) {

        logger.debug("Move Login Page");
        model.addAttribute("loginPageYn", true);

        return "/guest/login_form";
    }


    @PostMapping("/api/v1/guest/login/")
    public String loginProcess(Model model) {

        return "";
    }


    /**
     * move member registration page
     * @param model
     * @return
     */
    @GetMapping("/api/v1/member/register/")
    public String registerMember(Model model) {

        logger.debug("Move Register Member Page");
        model.addAttribute("loginPageYn", false);

        return "/guest/login_form";
    }


    @PostMapping(value = "/api/v1/member/register/check/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public ResponseEntity<?> registerDataCheck(HttpServletRequest req, @Valid MemberDto memberDto, BindingResult bindingResult) {

        // validating check
        Map<String, Object> result = this.memberDataValidate(memberDto, bindingResult);

        // validating error
        if (bindingResult.hasErrors()) {

            super.bindErrorResult(bindingResult, result);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(result);
        }
        // validating OK
        else {

            // double click check
            boolean doubleCheckFlg = super.doubleSubmitCheck(req, CONFIRM_DOUBLE_SUBMIT_CHECK_PARAMETER);
            if (!doubleCheckFlg) {
                return ResponseEntity.status(HttpStatus.OK).body(null);
            }

            HttpSession session = getSession(req);
            session.setAttribute(REGISTER_DATA, memberDto);

            // Home Url
            String nextUrl = "/api/v1/member/register/";
            result.put("nextUrl", nextUrl);

            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }


    /**
     * register member data
     * @param req
     * @return
     */
    @PostMapping("/api/v1/member/register/")
    public String registerMember(HttpServletRequest req, Model model) {

        HttpSession session = getSession(req);
        MemberDto registerMemberInfo = (MemberDto) session.getAttribute(REGISTER_DATA);
        if (registerMemberInfo == null) {
            model.addAttribute("registerResult", false);
            model.addAttribute("loginPageYn", false);
            return "/guest/login_form";
        }

        Member save = registerMemberInfo.saveMember();
        model.addAttribute("registerResult", memberService.saveMember(save).getMemberSn() != null);
        model.addAttribute("loginPageYn", true);
        return "/";
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


    @PatchMapping("/api/v1/member/reset_pw/")
    public ResponseEntity<?> resetPasswordProcess() {

        Map<String, Object> result = new HashMap<>();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    /**
     * Signup Form Validation
     * @return
     */
    private Map<String, Object> memberDataValidate(MemberDto memberDto, BindingResult bindingResult) {

        LinkedHashMap<String, Object> result = new LinkedHashMap<>();

        this.validate(memberDto, bindingResult, memberDtoValidator);
        if (bindingResult.hasErrors()) {
            super.bindErrorResult(bindingResult, result);
        }

        return result;
    }


    /**
     * Validate.
     * @param target the target
     * @param bindingResult the binding result
     * @param validators the _validators
     * @return the binding result
     */
    private BindingResult validate(MemberDto target, BindingResult bindingResult, Validator... validators) {

        for (Validator validator : validators) {
            validator.validate(target, bindingResult);
        }

        return bindingResult;
    }


    /**
     * save memberId at cookie
     * @param memberSnStr
     * @param res
     */
    private void memberIdCookie(String memberSnStr, HttpServletResponse res) {

        // save cookie data
        CookieUtils.addCookie(CookieUtils.class, res, CommonConst.COOKIE_MEMBER_ID, memberSnStr);
    }

    // Logout

    // update member data

    // get member data

    // withdraw

}
