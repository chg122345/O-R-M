package gxf.orm.c3p0;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import gxf.orm.config.ConfigConstans;
import gxf.orm.utils.PropsUtil;
/**
 * 
 *
 * @author Chen_9g
 * @date 2018年1月15日下午8:16:04
 * @Copyright Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 *
 */
public class C3p0Connection {
//	private static final ConfigConstans conf=new ConfigConstans();	
	
	
	public static Connection getConnection(ConfigConstans conf) {		
	Connection conn=null;
	 Properties pros=PropsUtil.loadProps(conf.getPath());
	 String driver=pros.getProperty(conf.getDriver());
	 String url=pros.getProperty(conf.getUrl());
	 String username=pros.getProperty(conf.getUser());
	 String password=pros.getProperty(conf.getPassword());
	 C3p0Plugin c3p0=new C3p0Plugin(url,username,password,driver);
	 c3p0.start();
			try {
				conn = c3p0.getDataSource().getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	 return conn;
	}
	
	public static Connection getConnection() {
		ConfigConstans conf=new ConfigConstans();
//		conf.setPath("gxf/jdbc.properties");
		return getConnection(conf);
	}
}
