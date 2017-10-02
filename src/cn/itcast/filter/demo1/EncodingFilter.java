package cn.itcast.filter.demo1;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * filter小案例
 * @author wcq
 *
 */
   
//首先实现filter过滤功能,实现Filter接口,重写相关的方法
public class EncodingFilter  implements Filter{
	
	/**
	 * 测试在整个web中,统一字符编码过滤器
	 * 编写jsp 输入用户名，在Servlet中获取用户名，将用户名输出到端口控制台上
	 */
	
	//定义一个私有的属性encode
	private String encode;
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		
		//1.首先强制转换
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		
		//2.操作
		request.setCharacterEncoding(encode);   //该方法针对post请求,设置编码
		
		//3.过滤,放行
		arg2.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		//通过FilterConfig获取web.xml文件中的初始化参数,给encode赋值
		this.encode = arg0.getInitParameter("encode");
	}	
}





