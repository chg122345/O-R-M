package gxf.orm.exception;

/**
 * 
 * 字段丢失/没有找到异常
 * @author Chen_9g
 * @date 2018年1月14日下午3:05:11
 * @Copyright Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 *
 */
public class NotfoundFieldException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 public NotfoundFieldException() {
	        super();
	    }

	    /**
	     * 
	     * 	字段丢失/没有找到异常
	     * @param   s   the detail message.
	     */
	    public NotfoundFieldException(String s) {
	        super(s);
	    }

}
