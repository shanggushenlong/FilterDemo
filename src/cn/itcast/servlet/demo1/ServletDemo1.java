package cn.itcast.servlet.demo1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletDemo1")
public class ServletDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//一般对于form表单的提交,如果是post请求,通过设置 
		// request.setCharacterEncoding("utf-8");
		//现在通过filter过滤,设置编码
		
		//获取jsp页面的初始化参数
		String username = request.getParameter("username");
		String msg = request.getParameter("msg");
		
		//显示
		System.out.println(username);
		System.out.println(msg);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
