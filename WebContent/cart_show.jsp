<%@page import="java.io.*"%>
<%@page import="java.util.List" %>
<%@page import="com.bean.CartInfo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的购物车</title>
</head>
<body>
<a href="http://localhost:8080/Web-Shopping/customer_message.jsp">功能选择</a><br>
<a href="http://localhost:8080/Web-Shopping/product_message.jsp">返回商品主页</a><br>
<%
	String info=(String)request.getAttribute("info");
	if(info!=null){
		out.println(info);
	}
	List<CartInfo> cart_list=(List<CartInfo>)session.getAttribute("cart_list");
	float totalPrice=0;
	if(cart_list!=null&&!cart_list.isEmpty()){
%>

<table border="1" align="center" width="500">
	<%for(int j=0;j<cart_list.size();j++){
		CartInfo cart =(CartInfo)cart_list.get(j);
		totalPrice+=cart.getProduct_price()*cart.getProduct_num();
	}
	 for(int i=0;i<cart_list.size();i++){
		CartInfo cart =(CartInfo)cart_list.get(i);
	%>
	<tr>
		<td align="right" width="30%">商品名称：</td>
        <td><%=cart.getProduct_name() %></td>
	</tr>
	<tr>
		<td align="right" width="30%">商品价格：</td>
        <td><%=cart.getProduct_price() %></td>
	</tr>
	<tr>
		<td align="right" width="30%">加购数量：</td>
        <td><%=cart.getProduct_num() %></td>
	</tr>
	<tr>
	  <td align="right" width="30%">商品样式：</td>
	  <td>
	  	<img src="<%=cart.getProduct_img()%>" height="150" width="150"/>
	  </td>
	</tr>
	<tr>
		<td>
			<form action="customer_delete_cart">
				<input type="hidden" name="cart_id" value=<%=cart.getCart_id() %>>
				<input type="submit"  value="删除" >
			</form>
		</td>
		<td> 
			<form action="customer_pay_one">
				<input type="submit"  value="立即购买" >
	            <input type="hidden" name="product_id" value=<%=cart.getProduct_id() %>>
	            <input type="hidden" name="product_num" value=<%=cart.getProduct_num() %>>
	            <input type="hidden" name="cart_id" value=<%=cart.getCart_id() %>>
			</form>
		</td>
	</tr>
	<%}%>
	<tr>
		<td align="right" width="30%">购物车商品总价：</td>
        <td><%=totalPrice %>￥</td>
	</tr>
	<tr>
		<td colspan=2>
			<form action="customer_pay_all">
				<input type="submit"  value="全部购买" >
			</form>
		</td>
	</tr>
	<%} %>
</table>

</body>
</html>