<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	 
	<%--编写登录页面 --%>
	${ requestScope["login.message"] }
	<form action="${pageContext.request.contextPath}/LoginServlet" method="post">
		username:<input type="text" name="username" /><br>
		password:<input type="password" name="password" /><br>
		<input type="checkbox" name="autologin" value="ok">自动登录<br>
		<input type="submit" value="提交">
	</form>
	
</body>
</html>