package gxf.orm.utils;

/**
 * 
 * 判断数组是否为空
 * @author Chen_9g
 * @date 2018年1月16日下午1:42:25
 * @Copyright Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 *
 */
public class ArrayUtil {

    /**
     * 判断数组是否非空
     */
    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

    /**
     * 判断数组是否为空
     */
    public static boolean isEmpty(Object[] array) {
    	
        return (array.length==0||array==null);
    }

    /**
     * 连接数组
     *//*
    public static Object[] concat(Object[] array1, Object[] array2) {
        return ArrayUtils.addAll(array1, array2);
    }

    *//**
     * 判断对象是否在数组中
     *//*
    public static <T> boolean contains(T[] array, T obj) {
        return ArrayUtils.contains(array, obj);
    }*/
}
