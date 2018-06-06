package com.yxdtyut.annotation.constraintValidator;

import com.yxdtyut.annotation.StepAmountMustInt;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午1:56 2018/6/6
 */
@Slf4j
public class StepAmountMustIntConstraintValidator implements ConstraintValidator<StepAmountMustInt,BigDecimal> {
    @Override
    public void initialize(StepAmountMustInt stepAmountMustInt) {
    }

    @Override
    public boolean isValid(BigDecimal bigDecimal, ConstraintValidatorContext constraintValidatorContext) {
        if (bigDecimal == null) {
            return true;
        }
        if ((BigDecimal.valueOf(bigDecimal.longValue()).compareTo(bigDecimal)) == 0) {
            log.debug("投资步长验证成功: " + bigDecimal);
            return true;
        }
        log.debug("投资步长验证失败: " + bigDecimal);
        return false;
    }
}
