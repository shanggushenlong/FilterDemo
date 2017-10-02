package cn.itcast.filter.demo4;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.utils.CookieUtils;

public class AutologinFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		
		//1.强制转换
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		
		//2.
		//(1)得到cookie中username,password
		Cookie cookie = CookieUtils.findCookieByName(request.getCookies(), "autologin");
		if(cookie != null){
			//找到了,.进行自动登录
			String username = cookie.getValue().split("::")[0];
			String password = cookie.getValue().split("::")[1];
			
			UserService service = new UserService();
			try {
				User user = service.login(username, password);
				if(user != null){
					//查找到了用户,进行自动登录
					request.getSession().setAttribute("user", user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//3.放行
		arg2.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
