package cn.itcast.filter.demo4;

import java.io.IOException;
import java.net.URLDecoder;
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

import com.sun.media.sound.SoftSynthesizer;

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
		
		/*关于自动有两个问题:
		 * 1.如果用户已经登录,还需要自动登录吗
		 * 		解决:先判断用户是否登录了,就是从session中获取user
		 * 2.如果用户想要进行 例如:注册,登录操作,就不需要自动登录
		 * 		解决:判断 如果用户访问的是登录操作,不进行自动登录
		 * */
		//2.
		
		String uri = request.getRequestURI();          //   /FilterDemo/demo4/login.jsp
		String contextPath = request.getContextPath(); //   /FilterDemo
//返回一个新的字符串，它是此字符串的一个子字符串。该子字符串从指定索引处的字符开始，直到此字符串末尾。
		String path = uri.substring(contextPath.length()); //   /demo4/login.jsp
		
//		System.out.println(uri);
//		System.out.println(contextPath);
//		System.out.println(path);
		
		if(!(path.equals("/demo4/login.jsp"))){
		
			//判断用户没有登录,才进行自动登录
			User u = (User) request.getSession().getAttribute("user");
			//如果 u 为 null,表明用户没有登录,
			if(u == null){
				//(1)得到cookie中username,password
				Cookie cookie = CookieUtils.findCookieByName(request.getCookies(), "autologin"); // request.getCookies()得到关于cookie参数的数组
				if(cookie != null){
					//找到了,.进行自动登录
					String username = URLDecoder.decode(cookie.getValue().split("::")[0], "utf-8");
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
			}
		}
		
		//3.放行
		arg2.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
