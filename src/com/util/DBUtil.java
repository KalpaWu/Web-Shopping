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

	//��Ϊ��ȡ�ļ�������Ϣ�ͼ�������ֻ��Ҫִ��һ��,���Խ����������ܷ��뾲̬�������,������ļ��ض�����,����ߴ����Ч��

	static {
		//��ȡ�����ļ���Ϣ
		ResourceBundle bundle = ResourceBundle.getBundle("DBConfig");
		
		url = bundle.getString("url");
		user = bundle.getString("user");
		pwd = bundle.getString("password");

		//���÷���,��������
		try {
			Class.forName(bundle.getString("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	//��ȡ����ͨ��
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,user,pwd);
	}

	//�ر�������Դ
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
