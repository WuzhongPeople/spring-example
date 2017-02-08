/**
 * spring-example
 * io.vilya.common.annotation
 * 2017年1月27日 下午11:07:11
 */
package io.vilya.example.common.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * @author iamaprin
 * 2017年1月27日 下午11:07:11
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface ClearInterceptor {

}
