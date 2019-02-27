<%@page import="java.util.List" %>
<%@page import="com.bean.OrderInfo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>全部订单</title>
</head>
<body>
<a href="http://localhost:8080/Web-Shopping/admin_message.jsp">功能选择</a>
<%
	String info=(String)request.getAttribute("info");
	if(info!=null){
		out.println(info);
	}
	List<OrderInfo> order_list=(List<OrderInfo>)session.getAttribute("all_order_list");
	if(order_list!=null&&!order_list.isEmpty()){
%>
<table border="1" align="center" width="500">
	<%for(int i=0;i<order_list.size();i++){
		OrderInfo order =(OrderInfo)order_list.get(i);
	%>
	<tr>
		<td align="right" width="30%">用户名称：</td>
        <td><%=order.getUserName() %></td>
	</tr>
	<tr>
		<td align="right" width="30%">用户地址：</td>
        <td><%=order.getAddress() %></td>
	</tr>
	<tr>
		<td align="right" width="30%">联系方式：</td>
        <td><%=order.getMobile() %></td>
	</tr>
	<tr>
		<td align="right" width="30%">商品名称：</td>
        <td><%=order.getProduct_name() %></td>
	</tr>
	<tr>
		<td align="right" width="30%">商品价格：</td>
        <td><%=order.getProduct_price() %></td>
	</tr>
	<tr>
		<td align="right" width="30%">购买数量：</td>
        <td><%=order.getProduct_num() %></td>
	</tr>
	<tr>
	  <td align="right" width="30%">商品样式：</td>
	  <td>
	  	<img src="<%=order.getProduct_img()%>" height="150" width="150"/>
	  </td>
	</tr>
	<tr>
		<td align="right" width="30%">订单价格：</td>
        <td><%=order.getTotal_price()%></td>
	</tr>
	<tr>
		<td align="right" width="30%">下单时间：</td>
        <td><%=order.getOrder_time()%></td>
	</tr>
	<%
		}
	}%>
	
</table>
</body>
</html>