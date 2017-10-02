package cn.itcast.filter.demo2;

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
 * filter案例2
 * @author wcq
 *
 */
public class CacheFilter implements Filter{

	/**
	 * 此次案例:禁用所有JSP页面缓存   根据题目要求,所以设置的过滤路径url应该是  *.jsp  ,这样结果使得所以jsp页面都将会被过滤
	 */
	@Override
	public void destroy() {
		  
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		
		//1.首先强制转换
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		
		//2.操作设置浏览器端禁止缓存页面内容	
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setDateHeader("expires", 0);
		
		//3.放行
		arg2.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
}
