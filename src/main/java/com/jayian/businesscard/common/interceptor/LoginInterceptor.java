package com.jayian.businesscard.common.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    //콘트롤러의 메서드 실행 전에 처리
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        logger.debug("LoginInterceptor Run");

        //세션의 로그인 정보 읽기
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute("userid");

        //로그인되지 않은 경우 로그인 페이지로 이동
        if (userid == null) {
            //request.getContextPath()로 루트 경로를 구하여 절대 경로로 처리
//			response.sendRedirect(request.getContextPath() + "/members/login");
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }

        //로그인 된 경우 요청한 경로로 진행
        return super.preHandle(request, response, handler);
    }

}