package gxf.orm.dbutil;

import java.sql.Connection;
import java.util.Properties;

import gxf.orm.config.ConfigConstans;
import gxf.orm.utils.PropsUtil;

/**
 * 获取数据库连接
 * @author Chen_9g
 * @date 2018年1月15日下午8:30:09
 * @Copyright Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 *
 */
public class DBConnection {

	public static Connection getConnection(ConfigConstans conf) {		
		 Properties pros=PropsUtil.loadProps(conf.getPath());
		 String driver=pros.getProperty(conf.getDriver());
		 String url=pros.getProperty(conf.getUrl());
		 String username=pros.getProperty(conf.getUser());
		 String password=pros.getProperty(conf.getPassword());
		 DBPlugin db=new DBPlugin(driver,url,username,password);
		 return db.getConn();
		}
	public static Connection getConnection() {
		ConfigConstans conf=new ConfigConstans();
		return getConnection(conf);
	}
}
