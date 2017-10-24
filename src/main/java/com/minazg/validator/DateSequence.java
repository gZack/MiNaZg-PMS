//package com.minazg.validator;
//
//import javax.validation.Constraint;
//import javax.validation.Payload;
//import java.lang.annotation.Documented;
//import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
//import static java.lang.annotation.ElementType.TYPE;
//import java.lang.annotation.Retention;
//import static java.lang.annotation.RetentionPolicy.RUNTIME;
//import java.lang.annotation.Target;
//
//@Target({ TYPE, ANNOTATION_TYPE })
//@Retention(RUNTIME)
//@Constraint(validatedBy = DateSequenceValidator.class)
//@Documented
//public @interface DateSequence {
//
//    String message() default "{com.packt.webstore.validator.stockmaximum.message}";
//
//    Class<?>[] groups() default {};
//
//    Class<? extends Payload>[] payload() default {};
//
//
//    int maximum();
//
//}
//
