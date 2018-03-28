package gxf.orm.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import gxf.orm.config.ConfigConstans;

/**
 * 
 * 数据库连接信息配置
 * @author Chen_9g
 * @date 2018年1月16日下午1:33:39
 * @Copyright Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 *
 */
public class DBPlugin {
	
	private String driver="com.mysql.jdbc.Driver";
	private String url;
	private String username;
	private String password;
	private Connection conn;

	public DBPlugin(String Driver,String Url,String Username,String Password) {
		this.driver = Driver != null ? Driver : this.driver;
		this.url=Url;
		this.username=Username;
		this.password=Password;
	}
	public void initDBPlugin(String Driver,String Url,String Username,String Password) {
		this.driver = Driver != null ? Driver : this.driver;
		this.url=Url;
		this.username=Username;
		this.password=Password;
	}
	public DBPlugin(Properties properties) {
		ConfigConstans conf=new ConfigConstans();
		Properties ps = properties;
		initDBPlugin(ps.getProperty(conf.getDriver()),ps.getProperty(conf.getUrl()), ps.getProperty(conf.getUser()), ps.getProperty(conf.getPassword()));
	}
	public DBPlugin(String Url,String Username,String Password) {
		this.url=Url;
		this.username=Username;
		this.password=Password;
	}
	
	public Connection getConn() {
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public boolean stop() {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	  }
		return true;
	}
	
}
