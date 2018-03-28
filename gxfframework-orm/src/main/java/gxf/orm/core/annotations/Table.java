package gxf.orm.core.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 
 * 数据库表名
 * @author Chen_9g
 * @date 2018年1月14日下午12:50:22
 * @Copyright Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 *
 */
@Retention(RUNTIME)
@Target(TYPE)
@Inherited
public @interface Table {
	String name() default "";

}
