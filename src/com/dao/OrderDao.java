package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.OrderInfo;
import com.util.DBUtil;

public class OrderDao {
	public void saveOrder(OrderInfo order) {
		Connection conn =null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn=DBUtil.getConnection();
			String sql="insert into tb_order(userName,address,mobile,product_id,product_name,product_img,product_price,product_num,cart_id,total_price,order_time) values(?,?,?,?,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			
			ps.setString(1, order.getUserName());
			ps.setString(2, order.getAddress());
			ps.setString(3, order.getMobile());
			ps.setInt(4, order.getProduct_id());
			ps.setString(5, order.getProduct_name());
			ps.setString(6,order.getProduct_img());
			ps.setFloat(7, order.getProduct_price());
			ps.setInt(8, order.getProduct_num());
			ps.setInt(9, order.getCart_id());
			ps.setFloat(10, order.getTotal_price());
			ps.setString(11, order.getOrder_time());
			
			ps.executeUpdate();
			
			CartDao cartDao=new CartDao();
			if(cartDao.deleteCart(order.getUserName(), order.getCart_id())) {
				System.out.println("已从购物车成功删除此记录");
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
	}
	public List<OrderInfo> getOrderByName(String userName){
		Connection conn =null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderInfo> orderList=new ArrayList();
		try {
			conn=DBUtil.getConnection();
			String sql="select * from tb_order where userName = ?";
			ps=conn.prepareStatement(sql);
			
			ps.setString(1, userName);
			rs=ps.executeQuery();
			while(rs.next()) {
				OrderInfo order=new OrderInfo();
				order.setOrder_id(rs.getInt("order_id"));
				order.setAddress(rs.getString("address"));
				order.setCart_id(rs.getInt("cart_id"));
				order.setMobile(rs.getString("mobile"));
				order.setOrder_time(rs.getString("order_time"));
				order.setProduct_id(rs.getInt("product_id"));
				order.setProduct_img(rs.getString("product_img"));
				order.setProduct_name(rs.getString("product_name"));
				order.setProduct_num(rs.getInt("product_num"));
				order.setProduct_price(rs.getFloat("product_price"));
				order.setTotal_price(rs.getFloat("total_price"));
				order.setUserName(rs.getString("userName"));
				
				orderList.add(order);
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
		return orderList;
	}
	
	public List<OrderInfo> getAllOrder(){
		Connection conn =null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrderInfo> orderList=new ArrayList();
		try {
			conn=DBUtil.getConnection();
			String sql="select * from tb_order";
			ps=conn.prepareStatement(sql);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				OrderInfo order=new OrderInfo();
				order.setOrder_id(rs.getInt("order_id"));
				order.setAddress(rs.getString("address"));
				order.setCart_id(rs.getInt("cart_id"));
				order.setMobile(rs.getString("mobile"));
				order.setOrder_time(rs.getString("order_time"));
				order.setProduct_id(rs.getInt("product_id"));
				order.setProduct_img(rs.getString("product_img"));
				order.setProduct_name(rs.getString("product_name"));
				order.setProduct_num(rs.getInt("product_num"));
				order.setProduct_price(rs.getFloat("product_price"));
				order.setTotal_price(rs.getFloat("total_price"));
				order.setUserName(rs.getString("userName"));
				
				orderList.add(order);
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
		return orderList;
	}
}
