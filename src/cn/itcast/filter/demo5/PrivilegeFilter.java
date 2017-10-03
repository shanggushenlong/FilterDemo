package cn.itcast.filter.demo5;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.User;
import cn.itcast.exception.PrivilegeException;

/**
 * 权限控制(过滤)
 * @author wcq
 *
 */
public class PrivilegeFilter implements Filter{

	/**
	 * 权限控制的原理:
	 * 	可以做一个权限的Filter,在Filter中判断用户是否登录了,如果登录了,可以访问资源,如果没有登录,不能访问资源
	 * 
	 * 
	 * 问题;1.需要判断当前的资源是否需要权限控制
	 * 问题:2.根据不同role,判定admin user 权限
	 * 
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		
		//1.强制转换
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		
		//2.操作
		
		//判断当前的资源是否需要权限控制
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = uri.substring(contextPath.length());
		
//		System.out.println(uri);
//		System.out.println(contextPath);
//		System.out.println(path);
		
		//判断当前用户访问的资源是否需要权限
		if(path.equals("/book_add") || path.equals("/book_delete") || path.equals("/book_update") || path.equals("/book_search")){
			//判断用户是否登录了
			User user = (User) request.getSession().getAttribute("user");
			//如果为null,表明没有登录,报错权限不足,如果为空,则往下继续执行
			if(user == null){
				throw new PrivilegeException();
			}
			
			//判断登录的是什么用户,相应的用户访问有访问相应资源的权限
			if("admin".equals(user.getRole())){
				//这是admin角色(拥有访问 /book_add /book_delete /book_update 权限)
				if(!(path.equals("/book_add") || path.equals("/book_delete") || path.equals("/book_update"))){
					throw new PrivilegeException();
				}
				
			}else{
				//这是user角色
				if(!(path.equals("/book_search"))){
					throw new PrivilegeException();
				}
			}
		}
	
		//3.放行
		arg2.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	
	}

}
