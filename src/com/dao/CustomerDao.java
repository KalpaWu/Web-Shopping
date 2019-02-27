package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.CustomerInfo;
import com.util.DBUtil;

public class CustomerDao {
	public boolean customerIsExist(String userName) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from tb_customer where userName=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1,userName);
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
	
	public void saveCustomer(CustomerInfo customer) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn=DBUtil.getConnection();
			String sql="insert into tb_customer(userName,password,realName,address,email,mobile) values(?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1,customer.getUserName());
			ps.setString(2,customer.getPassword());
			ps.setString(3,customer.getRealName());
			ps.setString(4,customer.getAddress());
			ps.setString(5,customer.getEmail());
			ps.setString(6,customer.getMobile());
			
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
	
	public CustomerInfo login(String userName, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CustomerInfo customer=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from tb_customer where userName = ? and password = ?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				customer=new CustomerInfo();
				customer.setId(rs.getInt("id"));
				customer.setUserName(rs.getString("userName"));
				customer.setAddress(rs.getString("address"));
				customer.setEmail(rs.getString("email"));
				customer.setMobile(rs.getString("mobile"));
				customer.setPassword(rs.getString("password"));
				customer.setRealName(rs.getString("realName"));
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
		return customer;
	}
}
