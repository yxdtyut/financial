package com.yxdtyut.annotation;

import com.yxdtyut.annotation.constraintValidator.RewardRateConstraintValidator;

/**
 * @Author : yangxudong
 * @Description :   收益率范围(0-30)
 * @Date : 上午10:57 2018/6/6
 */
@java.lang.annotation.Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.CONSTRUCTOR, java.lang.annotation.ElementType.PARAMETER})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Documented
@javax.validation.Constraint(validatedBy = {RewardRateConstraintValidator.class})
public @interface RewardRateScope {
    java.lang.String message() default "{收益率范围有误，需在0-30之内}";

    java.lang.Class<?>[] groups() default {};

    java.lang.Class<? extends javax.validation.Payload>[] payload() default {};
}
