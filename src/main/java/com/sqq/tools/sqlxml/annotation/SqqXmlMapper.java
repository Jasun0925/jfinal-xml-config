package com.sqq.tools.sqlxml.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Jasun 2017年5月26日 上午11:03:31
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface SqqXmlMapper {
	/**
	 * 服务接口类
	 */
	//Class<?> value();

	/**
	 * 服务版本号
	 */
	//String version() default "";
}