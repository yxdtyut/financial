package com.yxdtyut.annotation.constraintValidator;

import com.yxdtyut.annotation.RewardRateScope;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

/**
 * @Author : yangxudong
 * @Description :   收益率(0-30)校验
 * @Date : 上午11:02 2018/6/6
 */
@Slf4j
public class RewardRateConstraintValidator implements ConstraintValidator<RewardRateScope, BigDecimal> {

    @Override
    public void initialize(RewardRateScope rewardRateScope) {

    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        if (BigDecimal.ZERO.compareTo(value) < 0 && (BigDecimal.valueOf(30).compareTo(value)) >=0) {
            log.debug("收益率验证通过: " + value);
            return true;
        }
        log.debug("收益率验证失败: " + value);
        return false;
    }
}
