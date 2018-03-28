package gxf.orm.core;

import gxf.orm.config.GXFConfig;
import gxf.orm.utils.ClassUtil;

public final class HelperLoader {

    public static void init() {
        // 定义需要加载的 Helper 类
        Class<?>[] classList = {
        GXFConfig.class
        };
        // 按照顺序加载类
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }
}
