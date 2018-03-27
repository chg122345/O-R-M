package gxf.orm.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import gxf.orm.c3p0.C3p0Plugin;
import gxf.orm.core.DBModel;
import gxf.orm.utils.ClassUtil;
import gxf.orm.utils.PropsUtil;
import gxf.orm.utils.TableUtil;
/**
 * 
 * 自动建表
 * @author Chen_9g
 * @date 2018年1月15日下午10:02:16
 * @Copyright Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 *
 */
public class CreateTable { 
	
	/**
	 * 创建数据库表.
	 * @param conn  数据库连接.
	 */
	public static void create(Connection conn) {	
		 ConfigConstans conf=new ConfigConstans();
		 Properties pros=PropsUtil.loadProps(conf.getPath());
		Set<Class<?>> scls=ClassUtil.getClassSetByPackagename(pros.getProperty(conf.getEntitypackage()));
		List<String> tbnames=TableUtil.showTableName(conn);
		for(Class<?> cls:scls) {
			boolean exist=false;
			for(String tname:tbnames) {
				if(tname==TableUtil.getTableName(cls)||TableUtil.getTableName(cls).equals(tname)) {
					exist=true;
				//	System.out.println(tname);
				//	System.out.println(TableUtil.getTable(cls));
				} 
			}	
			if(!exist) {
				String sql=DBModel.CreatTable(cls);
				System.out.println(sql);								
				try {
					Statement stm=conn.createStatement();
					stm.executeUpdate(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	}
	 }
	public static void create(C3p0Plugin c3p0) {
		if(!c3p0.start()) {
			c3p0.start();
		}
		ConfigConstans conf=new ConfigConstans();
		Properties pros=PropsUtil.loadProps(conf.getPath());
		Set<Class<?>> scls=ClassUtil.getClassSetByPackagename(pros.getProperty(conf.getEntitypackage()));
		List<String> tbnames=TableUtil.showTableName(c3p0);
		for(Class<?> cls:scls) {
			boolean exist=false;
			for(String tname:tbnames) {
				if(tname==TableUtil.getTableName(cls)||TableUtil.getTableName(cls).equals(tname)) {
					exist=true;
					//	System.out.println(tname);
					//	System.out.println(TableUtil.getTable(cls));
				} 
			}	
			if(!exist) {
				String sql=DBModel.CreatTable(cls);
				System.out.println(sql);								
				try {
					Connection conn=c3p0.getDataSource().getConnection();
					Statement stm=conn.createStatement();
					stm.executeUpdate(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
