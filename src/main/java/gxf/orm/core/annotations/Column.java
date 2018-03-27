package gxf.orm.core.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 
 *  数据库字段配置
 * @author Chen_9g
 * @date 2018年1月14日下午12:48:10
 * @Copyright Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 *
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface Column {
	String ColName() default "";
	String FieldType() default "";
	boolean isPK()default false;

}
