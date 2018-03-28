package gxf.orm.core.model;

import java.util.Date;

import gxf.orm.core.annotations.Column;
import gxf.orm.core.annotations.Table;
/**
 * 
 *
 * @author Chen_9g
 * @date 2018年1月14日下午12:50:10
 *  Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 *
 */
@Table(name="gxf_user")
public class User {

	@Column(ColName="id",FieldType="int(11)" ,isPK=true)
	private Integer id;
	@Column(ColName="password",FieldType="nvarchar(45)")
	private String password;
	
	@Column(ColName="address",FieldType="nvarchar(45)")
	private String address;
	@Column(ColName="created",FieldType="datetime")
	private Date createdate;
	public User() {
		super();
	}
	
	public User(Integer id, String password, String address) {
		super();
		this.id = id;
		this.password = password;
		this.address = address;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
	
	
}
