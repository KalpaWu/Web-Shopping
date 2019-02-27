package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtil {
	private static String url;
	private static String user;
	private static String pwd;

	//因为获取文件配置信息和加载驱动只需要执行一次,所以将这两个功能放入静态代码块中,随着类的加载而加载,能提高代码的效率

	static {
		//获取配置文件信息
		ResourceBundle bundle = ResourceBundle.getBundle("DBConfig");
		
		url = bundle.getString("url");
		user = bundle.getString("user");
		pwd = bundle.getString("password");

		//利用反射,加载驱动
		try {
			Class.forName(bundle.getString("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	//获取连接通道
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,user,pwd);
	}

	//关闭所有资源
	public static void closeAll(Connection connection,PreparedStatement pStmt, ResultSet resultSet) throws SQLException{
		if(connection != null){
			connection.close();
		}

		if(pStmt != null){
			pStmt.close();
		}

		if(resultSet != null){
			resultSet.close();
		}
	}
}
