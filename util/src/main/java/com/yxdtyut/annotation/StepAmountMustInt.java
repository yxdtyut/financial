package com.yxdtyut.annotation;

import com.yxdtyut.annotation.constraintValidator.RewardRateConstraintValidator;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午1:55 2018/6/6
 */
@java.lang.annotation.Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.CONSTRUCTOR, java.lang.annotation.ElementType.PARAMETER})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Documented
@javax.validation.Constraint(validatedBy = {RewardRateConstraintValidator.class})
public @interface StepAmountMustInt {
    java.lang.String message() default "{投资步长有误，必须为整数}";

    java.lang.Class<?>[] groups() default {};

    java.lang.Class<? extends javax.validation.Payload>[] payload() default {};
}
