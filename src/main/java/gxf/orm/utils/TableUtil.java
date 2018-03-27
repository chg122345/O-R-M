package gxf.orm.utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import gxf.orm.c3p0.C3p0Plugin;
import gxf.orm.core.annotations.Column;
import gxf.orm.core.annotations.Table;
import gxf.orm.exception.NotfoundFieldException;

/**
 * 
 * 获取实体类 数据库表字段
 * @author Chen_9g
 * @date 2018年1月14日下午8:44:59
 * @Copyright Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 *
 */
public class TableUtil {

	/**
	 * 获取实体对象对应的数据库表名
	 * @param cls 实体对象类
	 * @return
	 */
	public static String getTableName(Class<?> cls){		
			boolean exist=cls.isAnnotationPresent(Table.class);
			if(!exist) {
				return null;
			}
			Table table=(Table) cls.getAnnotation(Table.class);
			String tname=table.name();					
		return tname;
	}
	/**
	 * 获取对象的字段名(已经用getColumnName代替)
	 * @param cls
	 * @return
	 */
	public static List<String> getfieldName(Class<?> cls){
		List<String> colnames=new ArrayList<String>();
		Field[] fields=cls.getDeclaredFields();
		if(fields==null||fields.length==0) {
			throw new NotfoundFieldException(cls+"暂无该对象字段.");
		}
		for(Field field:fields) {
			boolean fexist=field.isAnnotationPresent(Column.class);
			if(!fexist) {
				continue;
			}	
			String fieldName=field.getName();			
			colnames.add(fieldName);				
		}
		return colnames;
	}
	/**
	 * Map  key=数据库的字段名(@Column注解上的的值)，value=实体对象的字段名
	 * @param cls 实体对象类
	 * @return
	 */
	public static Map<String,String> getColumnName(Class<?> cls){
		Map<String,String> colnames=new HashMap<String,String>();
		Field[] fields=cls.getDeclaredFields();
		if(fields==null||fields.length==0) {
			throw new NotfoundFieldException(cls+"暂无该对象字段.");
		}
		for(Field field:fields) {
			boolean fexist=field.isAnnotationPresent(Column.class);
			if(!fexist) {
				continue;
			}
			Column col=field.getAnnotation(Column.class);
			String colname=null;
			String fieldName=field.getName();
			if(col!=null) {
				colname=col.ColName();//字段名
				if(colname==null||colname.equals("")) {
					colname=fieldName;
				}
			}				
			colnames.put(colname,fieldName);
				
		}
		return colnames;
	}
	/**
	 * 获取实体对象上@Table注解的值
	 * @param packagename  实体对象所在的包名 如:(gxf.orm.model)
	 * @return
	 */
	public static List<String> getTableName(String packagename){
		List<String> tablename=new ArrayList<String>();
		Set<Class<?>> scls=ClassUtil.getClassSetByPackagename(packagename);
		for(Class<?> cls:scls) {
			boolean exist=cls.isAnnotationPresent(Table.class);
			if(!exist) {
				return null;
			}
			Table table=(Table) cls.getAnnotation(Table.class);
			String tname=table.name();
			tablename.add(tname);
		}
		return tablename;
	}
	/**
	 * 读取数据库已存在的表名  
	 * @param conn 连接对象
	 * @return
	 */
	 
	public static List<String> showTableName(Connection conn){
		List<String> tablename=new ArrayList<String>();	
		if(conn==null) {
			throw new RuntimeException("获取数据库连接失败..");
		}else {
			try {
				 DatabaseMetaData dmd=(DatabaseMetaData) conn.getMetaData();
				  ResultSet res=dmd.getTables(null, null, "%", null);
				while(res.next()) {
					tablename.add(res.getString("TABLE_NAME"));
				}
			} catch (SQLException e) {				
				e.printStackTrace();
				return null;
			}
		}
		return tablename;
	}
	public static List<String> showTableName(C3p0Plugin c3p0){
		if(!c3p0.start()) {
			c3p0.start();
		}
		List<String> tablename=new ArrayList<String>();		
		try {
			Connection conn=c3p0.getDataSource().getConnection();
			if(conn==null) {
				throw new RuntimeException("获取数据库连接失败...");				
			}else {
			DatabaseMetaData dmd=(DatabaseMetaData) conn.getMetaData();
			ResultSet res=dmd.getTables(null, null, "%", null);
			while(res.next()) {
				tablename.add(res.getString("TABLE_NAME"));
			 }
		 }
		} catch (SQLException e) {				
			e.printStackTrace();
			return null;
		}
		return tablename;
	}
}
