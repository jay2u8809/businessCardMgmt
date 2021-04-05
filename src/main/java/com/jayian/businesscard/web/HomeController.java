package com.jayian.businesscard.web;

import com.jayian.businesscard.common.dto.CommonExtends;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class HomeController extends CommonExtends {

    @GetMapping("/")
    public String moveHomePage(Model model) {

        logger.debug("Move Home Page");

        // Login Flag
        boolean isLogin = false;
        model.addAttribute("loginFlag", isLogin);
        logger.debug("Login Flag : {}", isLogin);

        if(isLogin) {
            // member info
            String memberName = "";
            model.addAttribute("memberName", memberName);
        }

        return "/index";
    }
}
