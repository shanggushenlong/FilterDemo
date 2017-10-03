<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	${ user.username }登录成功<br>
	<a href="${ pageContext.request.contextPath }/book_add">book add</a><br>
	<a href="${ pageContext.request.contextPath }/book_delete">book delete</a><br>
	<a href="${ pageContext.request.contextPath }/book_update">book update</a><br>
	<a href="${ pageContext.request.contextPath }/book_search">book search</a><br>
	
</body>
</html> 