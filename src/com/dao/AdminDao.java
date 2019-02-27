package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.AdminInfo;
import com.util.DBUtil;

public class AdminDao {
	public boolean adminIsExist(String admin_userName) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from tb_admin where admin_userName=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1,admin_userName);
			rs=ps.executeQuery();
			
			if(!rs.next()) {
				return false;  //用户名可用
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				DBUtil.closeAll(conn, ps, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return true;
	}
	
	public void saveAdmin(AdminInfo admin) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn=DBUtil.getConnection();
			String sql="insert into tb_admin(admin_userName,admin_password) values(?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1,admin.getAUserName());
			ps.setString(2,admin.getAPassword());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				DBUtil.closeAll(conn, ps, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public AdminInfo login(String admin_userName, String admin_password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		AdminInfo admin = null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from tb_admin where admin_userName = ? and admin_password = ?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, admin_userName);
			ps.setString(2, admin_password);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				admin = new AdminInfo();
				admin.setAId(rs.getInt("admin_id"));
				admin.setAUserName(rs.getString("admin_userName"));
				admin.setAPassword(rs.getString("admin_password"));
			}
	    } catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				DBUtil.closeAll(conn, ps, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return admin;
	}
}
