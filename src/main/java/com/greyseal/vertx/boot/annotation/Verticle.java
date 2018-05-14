package com.greyseal.vertx.hoot.annotation;

import com.greyseal.vertx.hoot.Constant.VerticleType;
import io.vertx.core.http.HttpMethod;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Verticle {
	VerticleType type() default VerticleType.STANDARD;
	String configuration() default "";
}