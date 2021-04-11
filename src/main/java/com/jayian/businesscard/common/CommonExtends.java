package com.jayian.businesscard.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class CommonExtends implements CommonConst {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Get Error Messages
     *
     * @param bindingResult
     * @param result
     */
    protected void bindErrorResult(BindingResult bindingResult, Map<String, Object> result) {
        for (FieldError error : bindingResult.getFieldErrors()) {
            result.put(error.getField(), (error.getCode() != null && !error.getCode().isEmpty() ? error.getCode() : error.getDefaultMessage()));
        }
    }

    protected boolean doubleSubmitCheck(HttpServletRequest req, String checkParameterKey) {
        // セッションにform情報登録.
        HttpSession session = getSession(req);
        boolean doubleCheckFlg = Boolean.TRUE;
        String csrf = null;
        if (req.getParameter(CSRF_PARAMETER_NAME) != null) {
            csrf = req.getParameter(CSRF_PARAMETER_NAME);
            if (session.getAttribute(checkParameterKey) != null) {
                if (csrf.equals(session.getAttribute(checkParameterKey))) {
                    doubleCheckFlg = Boolean.FALSE;
                }
            }
        }
        if (doubleCheckFlg) {
            if (csrf != null) {
                session.setAttribute(checkParameterKey, csrf);
            }
        }
        return doubleCheckFlg;
    }

    /**
     * 確認画面でのダブルクリックチェック処理が行われたかを確認する(コントロールでのチェック)
     *
     * @param req HttpServletRequest
     * @return boolean ダブルクリックチェック処理済み(true)、ダブルクリックチェック未処理(false)
     */
    protected boolean doubleSubmitCompleteCheck(HttpServletRequest req) {
        // セッションにform情報登録.
        HttpSession session = getSession(req);
        boolean doubleCheckFlg = Boolean.FALSE;
        if (session.getAttribute(COMPLETE_DOUBLE_SUBMIT_CHECK_PARAMETER) != null) {
            if (req.getParameter(CSRF_PARAMETER_NAME) != null) {
                String csrf = req.getParameter(CSRF_PARAMETER_NAME);
                if (csrf.equals(session.getAttribute(COMPLETE_DOUBLE_SUBMIT_CHECK_PARAMETER))) {
                    doubleCheckFlg = Boolean.TRUE;
                }
            }
        }
        return doubleCheckFlg;
    }

    protected static HttpSession getSession(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session == null){
            session = req.getSession(true);
        }
        return session;
    }
}
