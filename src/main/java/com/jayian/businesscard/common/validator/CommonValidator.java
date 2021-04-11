package com.jayian.businesscard.common.validator;

import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public abstract class CommonValidator implements Validator {

    protected Errors checkMemberName(Errors errors, String memberName) {
        Object[] arg = {"Member Name"};

        if (ObjectUtils.isEmpty(memberName)) {
//            errors.rejectValue("memberName", "Member Name is Empty", arg, "error");
            errors.rejectValue("memberName", "Member Name is Empty");
        }

        return errors;
    }
}
