<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<title>添加商品</title>
</head>
<body>
<a href="http://localhost:8080/Web-Shopping/admin_message.jsp">返回功能界面</a><br>
	<form  action="admin_add_product" enctype="multipart/form-data" method="post">
		<br><br><br><br><br><br>
       	<table border="0" align="center" width="500">
       	    <tr>
                <td align="right" width="30%">商品名称</td>
                <td><input type="text" name="product_name" class="box"></td>
            </tr>
            
            <tr>
                <td align="right" width="30%">商品价格</td>
                <td><input type="text" name="product_price" class="box"></td>
            </tr>
            
            <tr>
                <td align="right" width="30%">商品分类</td>
                <td><input type="text" name="product_category" class="box"></td>
            </tr>
            
            <tr>
                <td align="right" width="30%">商品描述</td>
                <td><input type="text" name="product_description" class="box"></td>
            </tr>
            
            <tr>
            	<td align="right" width="30%">请选择图片：</td>
				<td><input type="file" name="image"></td>
			<tr>
			
			<tr>
				<td colspan="2" align="center">
                    <input type="submit" value="提交" />
                </td>
			</tr>
		</table>
	</form>
	
</body>
</html>