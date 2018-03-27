package AnnotationTest;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;

import gxf.orm.core.model.User;
import gxf.orm.dao.JTemplateDao;
import gxf.orm.dbutil.DBConnection;

public class Test01 extends JTemplateDao{

	Connection conn=DBConnection.getConnection();
	@SuppressWarnings({ "unused" })
	@Test
	public void save() {
		User u=new User();
		setConn(conn);
		u.setId(1889);
     	u.setAddress("China");
		u.setPassword("gxfii");
//		User ur=(User)Get(User.class,18888);
//    	System.out.println(ur.getId()+" tsst"+ur.getPassword()+" "+ur.getAddress()+" "+ur.getCreatedate());
//		u.setId(10087);
//		u.setPassword("igxf");
//		u.setAddress("中国江西");
	//	u.setCreatedate(new Date());
//		Save(u);
//		Delete(User.class, 10087);
		List<User> user=Get(User.class);
		for(User ur:user) {
			System.out.println(ur.getId()+" "+ur.getPassword()+" "+ur.getAddress()+" "+ur.getCreatedate());
		}
	}
}
