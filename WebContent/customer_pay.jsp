<%@page import="com.bean.OrderInfo"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单付款</title>
</head>
<body>
<%
	String info=(String)request.getAttribute("info");
	if(info!=null){
		out.println(info);
	}
	List<OrderInfo> order_list=(List<OrderInfo>)session.getAttribute("order_list");
	if(order_list!=null&&!order_list.isEmpty()){
		float total_order_price=0;
%>
<table border="1" align="center" width="500">
	<%for(int j=0; j<order_list.size();j++){
		OrderInfo order=(OrderInfo)order_list.get(j);
		total_order_price+=order.getTotal_price();
	}
	for(int i=0;i<order_list.size();i++){
		response.setContentType("text/html");
		OrderInfo order=(OrderInfo)order_list.get(i);
	%>
	<tr>
	    <%if(i==0){ %>
		<td align="right" width="30%">收件人：</td>
        <td><%=order.getUserName() %></td>
	</tr>
	<tr>
		<td align="right" width="30%">收件地址：</td>
        <td><%=order.getAddress() %></td>
	</tr>
	<tr>
		<td align="right" width="30%">联系方式：</td>
        <td><%=order.getMobile() %></td>
	</tr>
	<%} %>
	<tr>
		<td align="right" width="30%">商品名称：</td>
        <td><%=order.getProduct_name()%></td>
	</tr>
	<tr>
		<td align="right" width="30%">商品单价：</td>
        <td><%=order.getProduct_price()%></td>
	</tr>
	<tr>
		<td align="right" width="30%">商品件数：</td>
        <td><%=order.getProduct_num()%></td>
	</tr>
	<tr>
		<td align="right" width="30%">商品图片：</td>
        <td><img src="<%=order.getProduct_img()%>" height="150" width="150"/></td>
	</tr>
	<tr>
		<td align="right" width="30%">单笔共计：</td>
        <td><%=order.getTotal_price()%></td>
	</tr>
	<%if(i>=1&&i==order_list.size()-1){ %>
	<tr>
		<td align="right" width="30%">共计：</td>
        <td><%=total_order_price%></td>
	</tr>
	<%		}
		}
	}%>
	<tr>
		<td align="center" colspan="2">请扫描二维码支付</td>
	</tr>
	<tr>
		<td><img src="<%="/Web-Shopping/image/Alipay.jpg"%>" height="150" width="150"/></td>
		<td><img src="<%="/Web-Shopping/image/wechat.jpg"%>" height="150" width="150"/></td>
	</tr>
	<tr>
		<td align="right" colspan=2> 
			<form action="customer_submit_order">
				<input type="submit"  value="确认支付" >
			</form>
		</td>
	</tr>
</table>
</body>
</html>