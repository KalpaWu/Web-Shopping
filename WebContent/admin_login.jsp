<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录</title>
</head>
<body>
	<form action="admin_login">
		<br><br><br><br><br><br>
       	<table border="0" align="center" width="500">
       		<tr>
                <td align="right" width="30%">管理员用户名</td>
                <td><input type="text" name="admin_userName" class="box"></td>
            </tr>
            <tr>
                <td align="right" width="30%">密码</td>
                <td><input type="password" name="admin_password" class="box"></td>   
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="登录" />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <a href="http://localhost:8080/Web-Shopping/admin_reg.jsp">注册</a>
                </td>
            </tr>
       	</table>
	</form>
	
	<form action="admin_exit">
	<table border="0" align="center" width="500">
		<tr>
        	<td colspan="2" align="center">
            	<input type="submit" value="退出登录" />
            </td>
       </tr>
	</table>
	</form>
	<a href="http://localhost:8080/Web-Shopping/product_message.jsp">浏览商品</a>
</body>
</html>