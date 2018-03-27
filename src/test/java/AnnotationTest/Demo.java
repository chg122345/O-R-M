package AnnotationTest;

import org.junit.Test;

import gxf.orm.config.ConfigConstans;
import gxf.orm.config.GXFConfig;

public class Demo extends GXFConfig {

	@Override
	public void Config(ConfigConstans conf) {
		conf.setPath("gxf");
		
	}
	
	@Test
	public void save() {
		ConfigConstans conf=new ConfigConstans();
		conf.setPath("gxf");
	System.out.println(conf.getPath());
	}
}
