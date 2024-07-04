package org.moment.tyche.tyche.query.information;

import org.moment.tyche.tyche.query.QueryType;
import org.moment.tyche.tyche.query.VoidQueryResolver;
import org.moment.tyche.tyche.query.resolver.QueryResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Queryable {
    QueryType type();

    Class<? extends QueryResolver> customResolver() default VoidQueryResolver.class;

    String field() default "";

    boolean nonNull() default false;

    boolean required() default false;
}
