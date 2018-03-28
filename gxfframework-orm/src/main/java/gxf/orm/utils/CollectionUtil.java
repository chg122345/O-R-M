package gxf.orm.utils;

import java.util.Collection;
import java.util.Map;


/**
 * 
 * 集合判断工具
 * @author Chen_9g
 * @date 2018年1月16日下午1:49:49
 * @Copyright Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 *
 */
public class CollectionUtil {

    /**
     * 判断集合是否非空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断集合是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return (collection==null||collection.isEmpty()||collection.size()==0);
    }
    
    /**
	 * 判断map集合是否为空
	 * @param map
	 * @return
	 */
	public static boolean isEmpty(Map<?,?> map) {
		return (map==null||map.isEmpty());
	}
	public static boolean isNotEmpty(Map<?,?> map) {
		return !isEmpty(map);
	}
}
