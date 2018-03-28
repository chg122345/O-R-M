package gxf.orm.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ReflectionUtil {

	private static final Logger LOGGER=LoggerFactory.getLogger(ReflectionUtil.class);
	
	public static Object newInstance(Class<?> cls) {
		Object obj;
		try {
			obj=cls.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			LOGGER.error("实例化失败！",e);
			throw new RuntimeException(e); 
		}
		return obj;
	} 
	
	public static Object invokeMethod(Object obj,Method method,Object args) {
		Object mth;
		
		try {
			method.setAccessible(true);
			mth=method.invoke(obj, args);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			LOGGER.error("调用方法失败�?",e);
			throw new RuntimeException(e); 
		}
		return mth;
	}
	
	public static void setField(Object obj,Field field,Object value) {
		
		try {
			field.setAccessible(true);
			field.set(obj, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			LOGGER.error("设�?�失败！�?",e);
			throw new RuntimeException(e); 
		}
		
	}
}
