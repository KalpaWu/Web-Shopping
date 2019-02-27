<%@page import="java.io.*"%>
<%@page import="com.bean.AdminInfo" %>
<%@page import="java.util.List" %>
<%@page import="com.bean.ProductInfo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String info=(String)request.getAttribute("info");
	if(info!=null){
		out.println(info);
	}
	List<ProductInfo> product_list=(List<ProductInfo>)session.getAttribute("product_list");
	if(product_list!=null&&!product_list.isEmpty()){
%>
<a href="http://localhost:8080/Web-Shopping/product_message.jsp">返回</a>
<table border="1" align="center" width="500">
	<%for(int i=0;i<product_list.size();i++){
		response.setContentType("text/html");
		ProductInfo product=(ProductInfo)product_list.get(i);
		AdminInfo admin=(AdminInfo)session.getAttribute("admin");
		if(admin!=null){
	%>
	<tr>
		<td align="right" width="30%">商品ID：</td>
        <td><%=product.getProduct_id() %></td>
	</tr>
	<%} %>
	<tr>
		<td align="right" width="30%">商品名称：</td>
        <td><%=product.getProduct_name() %></td>
	</tr>
	<tr>
		<td align="right" width="30%">商品价格：</td>
        <td><%=product.getProduct_price() %></td>
	</tr>
	<tr>
		<td align="right" width="30%">商品分类：</td>
        <td><%=product.getProduct_category() %></td>
	</tr>
	<tr>
		<td align="right" width="30%">商品详情：</td>
        <td><%=product.getProduct_description() %></td>
	</tr>
	<tr>
	  <td align="right" width="30%">商品样式：</td>
	  <td>
	  	<img src="<%=product.getProduct_img()%>" height="150" width="150"/>
	  </td>
	</tr>
	<tr>
		<td align="right" colspan=2> 
			<form action="customer_add_cart">
				商品添加数量：<input type="text" name="product_num" class="box">
				<input type="submit"  value="加入购物车" >
	            <input type="hidden" name="product_id" value=<%=product.getProduct_id() %>>
			</form>
		</td>
	</tr>
	<tr>
		<td align="right" colspan=2> 
			<form action="customer_pay_one">
				商品购买数量：<input type="text" name="product_num" class="box">
				<input type="submit"  value="立即购买" >
	            <input type="hidden" name="product_id" value=<%=product.getProduct_id() %>>
			</form>
		</td>
	</tr>
	<%	
		}
	}
	%>
</table>

</body>
</html>