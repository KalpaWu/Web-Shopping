<%@page import="com.bean.CustomerInfo" %>
<%@page import="com.bean.AdminInfo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Message</title>
</head>
<body>
<a href="http://localhost:8080/Web-Shopping/customer_reg.jsp">用户注册</a><br>
<a href="http://localhost:8080/Web-Shopping/customer_login.jsp">用户登录</a><br>
<a href="http://localhost:8080/Web-Shopping/admin_reg.jsp">管理员注册</a><br>
<a href="http://localhost:8080/Web-Shopping/admin_login.jsp">管理员登录</a><br>
	<%
		String info=(String)request.getAttribute("info");
   	 	if(info!=null){
    		out.println(info);
    	}
   	 	
   	 	CustomerInfo customer=(CustomerInfo)session.getAttribute("customer");
   	 	if(customer!=null){
	%>
	<form action="customer_show_cart">
		<input type="submit" value="查看我的购物车" />
	</form>
	<form action="customer_show_order">
		<input type="submit" value="查看我的订单" />
	</form>
	<%} %>
	<form action="getAllProduct">
	<table border="0" align="center" width="500">
		<tr>
        	<td colspan="2" align="center">
            	<input type="submit" value="查看所有商品" />
            </td>
       </tr>
     </table>
	</form>
	<br><br>
	<form action="getProductByName">
	<table border="0" align="center" width="500">
		<tr>
        	<td align="right" width="30%">输入名称搜索</td>
        	<td><input type="text" name="product_name" class="box"></td>          
       </tr>
       <tr>
       		<td align="right"><input type="submit" value="确定" /></td>
       </tr>
     </table>
	</form>
	
	<br><br>
	<form action="getProductByCategory">
	<table border="0" align="center" width="500">
		<tr>
        	<td align="right" width="30%">输入类别搜索</td>
        	<td><input type="text" name="product_category" class="box"></td>           
       </tr>
       <tr>
       		<td align="right"><input type="submit" value="确定" /></td>
       </tr>
     </table>
	</form>
	<%AdminInfo admin=(AdminInfo)session.getAttribute("admin");
	  if(admin!=null){
	%>
	<br><br>
	<form action="admin_GetProductById">
	<table border="0" align="center" width="500">
		<tr>
        	<td align="right" width="30%">输入商品ID搜索</td>
        	<td><input type="text" name="product_id" class="box"></td>
       </tr>
       <tr>
       		<td align="right"><input type="submit" value="确定" /></td>
       </tr>
     </table>
	</form>
	
	<br><br>
	<form action="admin_delete_product">
	<table border="0" align="center" width="500">
		<tr>
        	<td align="right" width="30%">输入商品ID删除</td>
        	<td><input type="text" name="product_id" class="box"></td>
       </tr>
       <tr>
       		<td align="right"><input type="submit" value="确定" /></td>
       </tr>
     </table>
	</form>
	<a href="http://localhost:8080/Web-Shopping/admin_message.jsp">返回功能界面</a><br>
	<%} %>
</body>
</html>