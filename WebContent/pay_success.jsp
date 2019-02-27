<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>支付成功</title>
</head>
<body>
<%
	String info=(String)request.getAttribute("info");
	if(info!=null){
		out.println(info);
	}
%>
<a href="http://localhost:8080/Web-Shopping/product_message.jsp">再逛逛</a>
</body>
</html>