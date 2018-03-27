package gxf.orm.dao;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gxf.orm.c3p0.C3p0Plugin;
import gxf.orm.config.CreateTable;
import gxf.orm.core.DBModel;
import gxf.orm.exception.TemplateDaoException;
import gxf.orm.utils.TableUtil;

/**
 * 
 * JDBC模板
 * @author Chen_9g
 * @date 2018年1月15日下午8:10:09
 * @Copyright Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 *
 */
public abstract class JTemplateDao {

	private Connection conn;
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public void setConn(C3p0Plugin c3p0) {
		try {
			c3p0.start();
			this.conn = c3p0.getDataSource().getConnection();
		} catch (SQLException e) {			
		}
	}
	/**
	  * 添加进数据库	
	  * @param obj
	  */
	 public JTemplateDao Save(Object obj) {	
		 CreateTable.create(conn);
		  Statement stm;	  			
			try {
				stm=conn.createStatement();
					stm.executeUpdate(DBModel.Save(obj));
					System.out.println(DBModel.Save(obj));			
				} catch (SQLException e) {			
					throw new TemplateDaoException(obj+"存入数据库失败..."+e);
			      }	
			return this;
	    }
	 
	 /**
	  * 根据唯一主键删除对象
	  * @param obj
	  * @param pk
	  */
	 public JTemplateDao Delete(Class<?> cls,Object pk) {
		 CreateTable.create(conn);
		 Statement stm;	  			
		 try {
			 stm=conn.createStatement();
			 stm.executeUpdate(DBModel.Delete(cls, pk));
			 System.out.println(DBModel.Delete(cls,pk));
   		 } catch (SQLException e) {			
   			throw new TemplateDaoException(pk+"删除操作数据库失败..."+e);
		 }	
		 return this;
	 }
	 /**
	  * 通过执行sql语句删除对象
	  * @param sql
	  * @return
	  */
	 public JTemplateDao Delete(String sql) {
		 CreateTable.create(conn);
		 Statement stm;	  			
		 try {
			 stm=conn.createStatement();
			 stm.executeUpdate(sql);
			 System.out.println(sql);
		 } catch (SQLException e) {			
			 throw new TemplateDaoException(sql+"操作数据库失败..."+e);
		 }	
		 return this;
	 }
	 /**
	  * 查询单个对象 ,如果不传参数，则返回最后一条记录.
	  * @param obj 实体对象
	  * (例如要查 id=100 password="123" 的User对象时，先new一个对象  
	  * User user=new User();
	  * user.setId(100);  user.setPassword("123");
	  * 再把user传进去)
	  * 
	  * @return
	  */
	 public Object Get(Object obj) {
		 CreateTable.create(conn);
		 Class<?> cls=obj.getClass();
		 Map<String,String> cols=TableUtil.getColumnName(cls);
		 Statement stm;	
		 Object entity=null;
		 try {
			 stm=conn.createStatement();
			 ResultSet res=stm.executeQuery(DBModel.Query(obj));
			 System.out.println(DBModel.Query(obj));
			 while(res.next()) {
				 entity=cls.newInstance();
					for(Map.Entry<String,String> setMethod:cols.entrySet()) {	
						PropertyDescriptor pd = new PropertyDescriptor(setMethod.getValue(), cls);
					Method m =pd.getWriteMethod();
					m.invoke(entity,res.getObject(setMethod.getKey()));													
					}									
				}	
		 } catch (Exception e) {			
			 throw new TemplateDaoException("argument type mismatch 或许数据库中值的类型与实体类的字段类型不匹配，无法赋值..."+e);
		 }	
		 return entity;
	 }
	 /**
	  * 根据主键查找
	  * @param cls 实体类
	  * @param pk  主键标识
	  * @return   查找对象
	  */
	 public Object Get(Class<?> cls,Object pk) {
		 CreateTable.create(conn);
		 Map<String,String> cols=TableUtil.getColumnName(cls);
		 Statement stm;	
		 Object entity=null;
		 try {
			 stm=conn.createStatement();
			 ResultSet res=stm.executeQuery(DBModel.Query(cls,pk));
			 System.out.println(DBModel.Query(cls,pk));
			 while(res.next()) {
				 entity=cls.newInstance();
				 for(Map.Entry<String,String> setMethod:cols.entrySet()) {	
					 PropertyDescriptor pd = new PropertyDescriptor(setMethod.getValue(), cls);
					 Method m =pd.getWriteMethod();
					 m.invoke(entity,res.getObject(setMethod.getKey()));													
				 }									
			 }	
		 } catch (Exception e) {			
			throw new TemplateDaoException("argument type mismatch 或许数据库中值的类型与实体类的字段类型不匹配，无法赋值..."+e);
		 }	
		 return entity;
	 }

	 /**
	  *   查询数据库的对象集合
	  * @param cls 实体对象类
	  * @return 对象集
	  */
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	public List Get(Class<?> cls) {	
		 CreateTable.create(conn);
		 Map<String,String> cols=TableUtil.getColumnName(cls);
		  Statement stm;
		  Object entity;		
		  List object=new ArrayList<>();
			try {				
				stm=conn.createStatement();
				ResultSet res=stm.executeQuery(DBModel.Query(cls));	
				System.out.println(DBModel.Query(cls));
				while(res.next()) {
					entity = cls.newInstance();
					for(Map.Entry<String,String> setMethod:cols.entrySet()) {	
						PropertyDescriptor pd = new PropertyDescriptor(setMethod.getValue(), cls);
					Method m =pd.getWriteMethod();  //set方法
					m.invoke(entity,res.getObject(setMethod.getKey()));		//写入参数											
					}
					object.add(entity);
				 }	
				} catch (Exception e) {			
					throw new TemplateDaoException("argument type mismatch 数据库中值的类型与实体类的字段类型不匹配，无法赋值..."+e);
			      }		
			return object;
	    }
	 
	 /**
	  * 
	  * @param cls 查询的对象
	  * @param sql  对应的sql语句
	  * @return
	  */
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	public List Get(Class<?> cls,String sql) {	
		 CreateTable.create(conn);
		 Map<String,String> cols=TableUtil.getColumnName(cls);
		 Statement stm;
		 Object entity;		
		 List object=new ArrayList<>();
		 try {				
			 stm=conn.createStatement();
			 ResultSet res=stm.executeQuery(sql);	
			 System.out.println(sql);
			 while(res.next()) {
				 entity = cls.newInstance();
				 for(Map.Entry<String,String> setMethod:cols.entrySet()) {	
					 PropertyDescriptor pd = new PropertyDescriptor(setMethod.getValue(), cls);
					 Method m =pd.getWriteMethod();  //set方法
					 m.invoke(entity,res.getObject(setMethod.getKey()));		//写入参数											
				 }
				 object.add(entity);
			 }	
		 } catch (Exception e) {			
			 throw new TemplateDaoException(sql+"操作数据库失败了..."+e);
		 }		
		 return object;
	 }
	 
	 /**
	  * 更新对象
	  * @param obj  更新后的对象
	  * @param pk   唯一主键
	  * @return
	  */
	 public JTemplateDao Update(Object obj,Object pk) {
		 CreateTable.create(conn);
		 Statement stm;
		 try {				
			 stm=conn.createStatement();
			 String sql=DBModel.Update(obj, pk);
			 stm.executeUpdate(sql);	
			 System.out.println(sql);			 
		 } catch (Exception e) {			
			 throw new TemplateDaoException(obj+"存入数据库失败了..."+e);
		 }				 
		 return this;
	 }
	 /**
	  * 更新数据库
	  * @param sql 自定义sql语句
	  * @return
	  */
	 public JTemplateDao Update(String sql) {
		 CreateTable.create(conn);
		 Statement stm;
		 try {				
			 stm=conn.createStatement();
			 stm.executeUpdate(sql);	
			 System.out.println(sql);			 
		 } catch (Exception e) {			
			 throw new TemplateDaoException(sql+"操作数据库失败了..."+e);
		 }				 
		 return this;
	 }
}
