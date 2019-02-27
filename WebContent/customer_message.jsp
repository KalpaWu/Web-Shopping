<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.bean.CustomerInfo"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Message</title>
</head>
<body>
<%
	String info=(String)request.getAttribute("info");
    if(info!=null){
    	out.println(info);
    }
    
    CustomerInfo customer=(CustomerInfo)session.getAttribute("customer");
    //判断用户是否登录
    if(customer!=null){
%>
<table align="center" width="350" border="0" height="200" bordercolor="#E8F4CC">
	<tr>
		<td align="center" colspan="2">
		<span style="font-weight:bold; font-size:18px;"><%=customer.getUserName() %></span>
		登录成功！
	</tr>
</table>
<a href="http://localhost:8080/Web-Shopping/product_message.jsp">浏览商品</a>
<form action="customer_show_cart">
		<input type="submit" value="查看我的购物车" />
</form>
<form action="customer_show_order">
		<input type="submit" value="查看我的订单" />
</form>
<%
    }
%>
</body>
</html>