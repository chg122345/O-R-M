package gxf.orm.config;

import gxf.orm.constans.Const;

/**
 * 
 *
 * @author Chen_9g
 * @date 2018年1月15日下午9:35:39
 * @Copyright Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 *
 */
final public class ConfigConstans {
	private String version=Const.GXF_VERSION;
	private String entitypackage=Const.GXF_ENTITY_PACKAGE;
	private String path=Const.GXF_FILE_PATH;
	private String driver=Const.GXF_JDBC_DRIVER;
	private String url=Const.GXF_JDBC_URL;
	private String user=Const.GXF_JDBC_USER;
	private String password=Const.GXF_JDBC_PASSWORD;
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}	
	public String getEntitypackage() {
		return entitypackage;
	}
	public void setEntitypackage(String entitypackage) {
		this.entitypackage = entitypackage;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
