<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员注册</title>
</head>
<body>
    <form action="admin_reg" >
        <br><br><br><br><br><br>
        <table border="0" align="center" width="500">
            <tr>
                <td align="right" width="30%">用户名</td>
                <td><input type="text" name="admin_userName" class="box"></td>
            </tr>
            <tr>
                <td align="right" width="30%">密码</td>
                <td><input type="password" name="admin_password" class="box"></td>   
            </tr>
            <tr>
                <td align="right" width="30%">确认密码</td>
                <td><input type="password" name="admin_repassword" class="box"></td>   
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="提交" />
                    <input type="reset" value="重置" />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <a href="http://localhost:8080/Web-Shopping/admin_login.jsp">返回登录</a>
                </td>
            </tr>
    
        </table>
    </form>
</body>
</html>