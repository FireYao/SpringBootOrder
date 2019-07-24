package com.simpletour.baseQuery;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface QueryWord {

    // 数据库中字段名,默认为空字符串湿哒哒所大多王企鹅群,则Query类中的字段要与数据库中字段一致
    String column() default "";

    // equal, like, gt, lt...
    MatchType func() default MatchType.equal;

    // object是否可以为null
    boolean nullable() default false;

    // 字符串是否可为空
    boolean emptiable() default false;

    // parse the annotated field to the parser returned type
    String parser() default "";

    // validate the annotated field and put the error string into _errors field
    String validator() default "";

    // validate error message when validate fail
    String errorMsg() default "";

    // parser and validator, if parser or validator is null, then check this
    String pv() default "";
}
