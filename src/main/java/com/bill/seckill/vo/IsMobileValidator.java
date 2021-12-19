package com.bill.seckill.vo;

import com.bill.seckill.utils.ValidatorUtil;
import com.bill.seckill.validator.IsMobile;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName IsMobileValidator
 * @Description TODO
 * @Author bill
 * @Date 2021/12/19 21:08
 * @Version 1.0
 **/
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

    private boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(required){
            return ValidatorUtil.isMobile(value);
        } else {
            if(StringUtils.isEmpty(value)){
                return true;
            } else {
                return ValidatorUtil.isMobile(value);
            }
        }
    }
}
