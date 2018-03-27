package gxf.orm.core.model;

import gxf.orm.core.annotations.Column;
import gxf.orm.core.annotations.Table;

@Table(name="gxf_admin")
public class Admins {
	@Column(ColName="id",FieldType="int",isPK=true)
	private int adminid;
	@Column(ColName="name",FieldType="nvarchar(45)")
	private String name;
	
	
	public Admins() {
		super();
	}
	public Admins(int id, String name) {
		super();
		this.adminid = id;
		this.name = name;
	}
	public int getAdminid() {
		return adminid;
	}
	public void setAdminid(int id) {
		this.adminid = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
