package cn.itcast.exception;

/**
 * 继承与RuntimeException
 * @author wcq
 *
 */
public class PrivilegeException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*自定义异常,需要在web.xml文件中配置
	 * 
	 */
	public PrivilegeException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PrivilegeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public PrivilegeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PrivilegeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PrivilegeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
