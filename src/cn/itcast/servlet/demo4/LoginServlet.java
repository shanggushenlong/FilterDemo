package cn.itcast.servlet.demo4;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.User;
import cn.itcast.service.UserService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.首先得到请求的用户信息
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//2.调用service层方法
		UserService service = new UserService();
		try {
			User user = service.login(username,password);
			if(user != null){
			
				//判断是否勾选了自动登录
				String autologin = request.getParameter("autologin");
				if("ok".equals(autologin)){
					//如果相等,表明勾选了,将其存取到cookie中
					Cookie cookie = new Cookie("autologin", username+"::"+password);
					cookie.setMaxAge(60*60*24*10);   //设置cookie的生存时间
					cookie.setPath("/");
					response.addCookie(cookie);   //在相应中添加cookie对象
				}
				
				//登录成功,就将其存储到cookie中,页面重定向到success.jsp页面中
				request.getSession().setAttribute("user", user);
				response.sendRedirect(request.getContextPath()+"/demo4/success.jsp");
				
			}else{
				//登录失败
				request.setAttribute("login.message", "用户名或密码错误");
				request.getRequestDispatcher("/demo4/login.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("login.message", "登录失败");
			request.getRequestDispatcher("/demo4/login.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
