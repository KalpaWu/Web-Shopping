package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.CartInfo;
import com.bean.ProductInfo;
import com.util.DBUtil;

public class CartDao {
	//添加购物车记录
	public void saveCart(CartInfo cart) {
		Connection conn =null;
		PreparedStatement ps0 = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean add=true;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from tb_cart where userName = ? and product_id = ?";
			ps0=conn.prepareStatement(sql);
			
			ps0.setString(1, cart.getUserName());
			ps0.setInt(2, cart.getProduct_id());
			rs = ps0.executeQuery();
			int cart_id=0;
			int product_num=0;
			if(rs.next()) {
				add=false;
				cart_id=rs.getInt("cart_id");
				product_num=rs.getInt("product_num");
				//System.out.println("hi"+cart_id);
			}
			ps0.close();	
			if(add) {
				sql="insert into tb_cart(userName,product_id,product_price,product_name,product_img,product_num) values(?,?,?,?,?,?)";
				ps=conn.prepareStatement(sql);
				
				ps.setString(1, cart.getUserName());
				ps.setInt(2, cart.getProduct_id());
				ps.setFloat(3, cart.getProduct_price());
				ps.setString(4, cart.getProduct_name());
				ps.setString(5, cart.getProduct_img());
				ps.setInt(6, cart.getProduct_num());
				ps.executeUpdate();
			}else {
				sql="update tb_cart set userName = ?, product_id=?,product_price=?,product_name=?, product_img=?, product_num=? where cart_id=? ";
				ps=conn.prepareStatement(sql);
				
				ps.setString(1, cart.getUserName());
				ps.setInt(2, cart.getProduct_id());
				ps.setFloat(3, cart.getProduct_price());
				ps.setString(4, cart.getProduct_name());
				ps.setString(5, cart.getProduct_img());
				int total_num=cart.getProduct_num()+product_num;
				ps.setInt(6, total_num);
				ps.setInt(7, cart_id);
				int result = ps.executeUpdate();
//				if(result==1)
//					System.out.println("更新成功");
//				else
//					System.out.println("更新失败");
			}

		}  catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				DBUtil.closeAll(conn, ps, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}	
	}
	
	//用户查看购物车所有商品
	public List<CartInfo> getAllcart(String userName){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CartInfo> cartList = new ArrayList<CartInfo>();
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from tb_cart where userName = ?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, userName);
			rs = ps.executeQuery();
			while(rs.next()) {
				CartInfo cart = new CartInfo();
				cart.setCart_id(rs.getInt("cart_id"));
				cart.setUserName(rs.getString("userName"));
				cart.setProduct_id(rs.getInt("product_id"));
				cart.setProduct_name(rs.getString("product_name"));
				cart.setProduct_price(rs.getFloat("product_price"));
				cart.setProduct_img(rs.getString("product_img"));
				cart.setProduct_num(rs.getInt("product_num"));
				cartList.add(cart);
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
		return cartList;
	}
	
	//用户删除购物车商品
	public boolean deleteCart(String userName, int cart_id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from tb_cart where cart_id = ? and userName = ?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cart_id);
			ps.setString(2, userName);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				DBUtil.closeAll(conn, ps, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}	
		if(result==1)
			return true;
		else
			return false;
	}
}
