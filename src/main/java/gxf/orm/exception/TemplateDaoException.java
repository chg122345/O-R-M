package gxf.orm.exception;

/**
 * 
 * 模板运行时异常
 * @author Chen_9g
 * @date 2018年1月14日下午4:35:09
 * @Copyright Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 *
 */
public class TemplateDaoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TemplateDaoException() {
		super();
	}
	
	/**
	 * 
	 * @param message 错误信息
	 */
	public TemplateDaoException(String message) {
        super(message);
    }

}
