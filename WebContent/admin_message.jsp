<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.bean.AdminInfo"%>
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
    
    AdminInfo admin=(AdminInfo)session.getAttribute("admin");
    
    if(admin!=null){
%>
<table align="center" width="350" border="0" height="200" bordercolor="#E8F4CC">
	<tr>
		<td align="center" colspan="2">
		<span style="font-weight:bold; font-size:18px;"><%=admin.getAUserName() %></span>
		登录成功！
	</tr>
</table>
<a href="http://localhost:8080/Web-Shopping/product_message.jsp">浏览商品信息</a>
<a href="http://localhost:8080/Web-Shopping/admin_add_product.jsp">增加商品信息</a>
<a href="http://localhost:8080/Web-Shopping/admin_edit_product.jsp">修改商品信息</a>
<a href="http://localhost:8080/Web-Shopping/product_message.jsp">查找及删除商品</a>
<br>
<form action="admin_show_all_order">
		<input type="submit" value="查看所有订单" >
</form>
<%
    }
%>

</body>
</html>