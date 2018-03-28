package gxf.orm.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import gxf.orm.core.annotations.Column;
import gxf.orm.core.annotations.Table;
import gxf.orm.exception.NotfoundFieldException;
import gxf.orm.utils.ArrayUtil;
import gxf.orm.utils.StringUtil;
/**
 * 
 * 核心部分  拼接SQL模型
 * @author Chen_9g
 * @date 2018年1月14日下午3:35:11
 * @Copyright Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 *
 */
public final class DBModel {

	/**
	 * 生成建表sql语句
	 * @param obj 实体对象
	 * @return  sql语句
	 */
	public static String CreatTable(Class<?> cls) {
		StringBuffer SQL=new StringBuffer();
		boolean exist=cls.isAnnotationPresent(Table.class);
		if(!exist) {
			return null;
		}
		Table table=(Table) cls.getAnnotation(Table.class);
		String tablename=table.name();
		SQL.append("create table").append(" ").append(tablename).append("\n").append("(").append(" ");
		Field[] fields=cls.getDeclaredFields();
		if(ArrayUtil.isEmpty(fields)/*fields==null||fields.length==0*/) {
			throw new NotfoundFieldException(cls+"暂无该对象字段...");
		}
		for(Field field:fields) {
			boolean fexist=field.isAnnotationPresent(Column.class);
			if(!fexist) {
				continue;
			}
			Column col=field.getAnnotation(Column.class);
			String colname=null;
			String coltype=col.FieldType();
			String fieldName=field.getName();
			if(col!=null) {
				colname=col.ColName();//字段名
				if(colname==null||colname.equals("")) {
					colname=fieldName;
				}
			}	
			if(col.isPK()) {
				SQL.append(colname).append(" ").append(coltype).append(" ").append("primary key,").append("\n");
			}else {
			SQL.append(colname).append(" ").append(coltype).append(",").append("\n");
		  }
		}
		SQL.deleteCharAt(SQL.length()-2);
		SQL.append(")");
		return SQL.toString();
		
	}
	/**
	 * 生成查询sql语句
	 * @param obj 实体对象
	 * @return  sql语句
	 */
	public static String Query(Object obj) {
		StringBuffer SQL=new StringBuffer();
		Class<?> cls=obj.getClass();
		boolean exist=cls.isAnnotationPresent(Table.class);
		if(!exist) {
			return null;
		}
		Table table=(Table) cls.getAnnotation(Table.class);
		String tablename=table.name();
		SQL.append("select * from").append(" ").append(tablename).append(" ").append("where 1=1 ");
		Field[] fields=cls.getDeclaredFields();
		if(ArrayUtil.isEmpty(fields)/*fields==null||fields.length==0*/) {
			throw new NotfoundFieldException(obj+"暂无该对象字段.");
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
				if(StringUtil.isEmpty(colname)/*colname==null||colname.equals("")*/) {
					colname=fieldName;
				}
			}	
		//	System.out.println(fieldName);
			String GetMethodName="get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
		//	System.out.println(GetMethodName);
			Object fieldValue=null;
			try {
				Method getMethod=cls.getMethod(GetMethodName);
				fieldValue=getMethod.invoke(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(fieldValue==null||"".equals(fieldValue)||(fieldValue instanceof Integer &&(Integer)fieldValue==0)
					||(fieldValue instanceof Long &&(Long)fieldValue==0)||(fieldValue instanceof Double &&(Double)fieldValue==0.0)) {
				continue;
			}
			SQL.append("and").append(" ").append(colname).append("=");
			if(fieldValue instanceof String) {
				SQL.append("'").append(fieldValue).append("'").append(" ");
			}else if(fieldValue instanceof Integer) {
				SQL.append(fieldValue).append(" ");
			}else if(fieldValue instanceof Long) {
				SQL.append(fieldValue).append(" ");
			}else if(fieldValue instanceof Float) {
				SQL.append(fieldValue).append(" ");
			}else if(fieldValue instanceof Double) {
				SQL.append(fieldValue).append(" ");
			}else if(fieldValue instanceof Date) {
				 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			        String dateString = formatter.format(fieldValue);
			        SQL.append("'").append(dateString).append("'").append(" ");
			}
		}
		return SQL.toString();
		
	}
	
	/**
	 * 生成插入sql语句
	 * @param obj 实体对象
	 * @return  sql语句
	 */
	public static String Save(Object obj) {
		StringBuffer SQL=new StringBuffer();
		List<Object> COLS=new ArrayList<Object>();
		Class<?> cls=obj.getClass();
		boolean exist=cls.isAnnotationPresent(Table.class);
		if(!exist) {
			return null;
		}
		Table table=(Table) cls.getAnnotation(Table.class);
		String tablename=table.name();
		SQL.append("insert into").append(" ").append(tablename).append("(");
		Field[] fields=cls.getDeclaredFields();
		if(fields==null||fields.length==0) {
			throw new NotfoundFieldException(obj+"暂无该对象字段.");
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
				if(StringUtil.isEmpty(colname)/*colname==null||colname.equals("")*/) {
					colname=fieldName;
				}
			}	
			//SQL.append(colname).append(",");			
			String GetMethodName="get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
			Object fieldValue=null;
			Object fieldvalue=null;
			
			try {
				Method getMethod=cls.getMethod(GetMethodName);
				fieldValue=getMethod.invoke(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(fieldValue==null||"".equals(fieldValue)) {
				fieldvalue="null";
			}else {
				fieldvalue=fieldValue;
				SQL.append(colname).append(",");
				if(fieldvalue instanceof String) {
					fieldvalue="'"+fieldvalue+"'";
				}if(fieldvalue instanceof Date) {
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				        String dateString = formatter.format(fieldvalue);		       
					  fieldvalue="'"+dateString+"'";			  
				}
				COLS.add(fieldvalue);
			}				
		}
		SQL.deleteCharAt(SQL.length()-1);
		SQL.append(")");
		SQL.append(" ").append("values").append("(");
		for(Object value:COLS) {
			SQL.append(value).append(",");
			
		}
		SQL.deleteCharAt(SQL.length()-1);
		SQL.append(")");
		return SQL.toString();
		
	}
	/**
	 * 生成删除sql语句
	 * @param cls 实体对象类
	 * @param pk 唯一主键
	 * @return sql语句
	 */
	public static String Delete(Class<?> cls,Object pk) {
		if(pk instanceof String) {
			pk="'"+pk+"'";
		}
		StringBuffer SQL=new StringBuffer();
	//	Class<?> cls=obj.getClass();
		boolean exist=cls.isAnnotationPresent(Table.class);
		if(!exist) {
			return null;
		}
		Table table=(Table) cls.getAnnotation(Table.class);
		String tablename=table.name();
		SQL.append("delete from ").append(tablename).append(" ").append("where").append(" ");
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
			if(col.isPK()) {			
			if(col!=null) {
				colname=col.ColName();//字段名
				if(colname==null||colname.equals("")) {
					colname=fieldName;
				}
			}	
			SQL.append(colname).append("=").append(pk);	
			}
		}
		return SQL.toString();
		
	}
	/**
	 *  
	 * @param cls 实体对象类
	 * @return
	 */
	public static String Query(Class<?> cls) {
		StringBuffer SQL=new StringBuffer();
		boolean exist=cls.isAnnotationPresent(Table.class);
		if(!exist) {
			return null;
		}
		Table table=(Table) cls.getAnnotation(Table.class);
		String tablename=table.name();
		SQL.append("select * from").append(" ").append(tablename);		
		return SQL.toString();
		
	}
	/**
	 * 
	 * @param cls  实体类
	 * @param pk   唯一主键字段
	 * @return
	 */
	public static String Query(Class<?> cls,Object pk) {
		if(pk instanceof String) {
			pk="'"+pk+"'";
		}
		StringBuffer SQL=new StringBuffer();
		boolean exist=cls.isAnnotationPresent(Table.class);
		if(!exist) {
			return null;
		}
		Table table=(Table) cls.getAnnotation(Table.class);
		String tablename=table.name();
		SQL.append("select * from").append(" ").append(tablename).append(" ").append("where ");
		Field[] fields=cls.getDeclaredFields();
		if(fields==null||fields.length==0) {
			throw new NotfoundFieldException(cls+"暂无该对象字段...");
		}
		for(Field field:fields) {
			boolean fexist=field.isAnnotationPresent(Column.class);
			if(!fexist) {
				continue;
			}
			Column col=field.getAnnotation(Column.class);
			String colname=null;
			String fieldName=field.getName();
			if(col.isPK()) {			
			if(col!=null) {
				colname=col.ColName();//字段名
				if(colname==null||colname.equals("")) {
					colname=fieldName;
				}
			}	
			SQL.append(colname).append("=").append(pk);	
			}
		}
		return SQL.toString();
		
	}
	/**
	 *  生成update sql语句
	 * @param obj 更新后的对象 pk 唯一主键
	 * @return sql
	 */
	public static String Update(Object obj,Object pk) {
		StringBuffer SQL=new StringBuffer();
		Class<?> cls=obj.getClass();
		boolean exist=cls.isAnnotationPresent(Table.class);
		if(!exist) {
			return null;
		}
		Table table=(Table) cls.getAnnotation(Table.class);
		String tablename=table.name();
		SQL.append("update").append(" ").append(tablename).append(" ").append("set ");
		Field[] fields=cls.getDeclaredFields();
		if(fields==null||fields.length==0) {
			throw new NotfoundFieldException(obj+"暂无该对象字段...");
		}
		String pkname=null;
		String GetMethodName=null;
		for(Field field:fields) {
			boolean fexist=field.isAnnotationPresent(Column.class);
			if(!fexist) {
				continue;
			}
			Column col=field.getAnnotation(Column.class);
			String colname=null;
			String fieldName=null;
			if(col!=null) {	
				if(col.isPK()) { //主键不能更改
					pkname=col.ColName();
					if(pkname==null||pkname.equals("")) {
						pkname=field.getName();
					}
				}else {
					fieldName=field.getName();//对象字段名
				colname=col.ColName();//数据库字段名
				if(colname==null||colname.equals("")) {
					colname=fieldName;
				}	
				GetMethodName="get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
				Object fieldValue=null;
				Object fieldvalue=null;			
				try {
					Method getMethod=cls.getMethod(GetMethodName);
					fieldValue=getMethod.invoke(obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(fieldValue==null||fieldValue.equals("")) {
					continue; //null 空值不作处理
				}else {
					SQL.append(colname).append("=");
					fieldvalue=fieldValue;
				}
				if(fieldvalue instanceof String) {
					fieldvalue="'"+fieldvalue+"'";
				}if(fieldvalue instanceof Date) {
					 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				        String dateString = formatter.format(fieldvalue);
					fieldvalue="'"+dateString+"'";
				}
				SQL.append(fieldvalue).append(",");	
			  }
			}							
		}
		if(pk instanceof String) {
			pk="'"+pk+"'";
		}
		SQL.deleteCharAt(SQL.length()-1);
		SQL.append(" ").append("where ").append(pkname).append("=").append(pk);
		return SQL.toString();		
	}
	
	/*@Test
	public void Tesss() {
		User u=new User();
		u.setPassword("gxf");
		u.setId(1001);
		u.setCreatedate(new Date());
		u.setAddress("China");
		String sql0=Query(u);
		String sql=Save(u);
		String sql1=Delete(User.class,100);
		String sql2=Update(u,1011);
		String sql3=Query(User.class,100);
		String sql4=CreatTable(User.class);
		
		System.out.println(sql0+"\n");
		System.out.println(sql+"\n");
		System.out.println(sql1+"\n");
		System.out.println(sql2+"\n");
		System.out.println(sql3+"\n");
		System.out.println(sql4);
	}*/
}
