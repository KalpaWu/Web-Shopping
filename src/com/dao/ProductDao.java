package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.ProductInfo;
import com.util.DBUtil;

public class ProductDao {
	//添加商品
	public void saveProduct(ProductInfo product) { 
		Connection conn =null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn=DBUtil.getConnection();
			String sql="insert into tb_product(product_name,product_description,product_price,product_category,product_img) values(?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			
//			Upload_File upload_file=new Upload_File();
//			byte[] content=upload_file.transferImg(fileName);
			
			ps.setString(1, product.getProduct_name());
			ps.setString(2, product.getProduct_description());
			ps.setFloat(3, product.getProduct_price());
			ps.setString(4, product.getProduct_category());
			ps.setString(5, product.getProduct_img());
			ps.executeUpdate();

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
	//查询所有商品
	public List<ProductInfo> getAllProduct(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductInfo> productList = new ArrayList<ProductInfo>();
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from tb_product";
			ps=conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				ProductInfo product=new ProductInfo();
				product.setProduct_img(rs.getString("product_img"));
				product.setProduct_category(rs.getString("product_category"));
				product.setProduct_description(rs.getString("product_description"));
				product.setProduct_id(rs.getInt("product_id"));
				product.setProduct_name(rs.getString("product_name"));
				product.setProduct_price(rs.getFloat("product_price"));
				productList.add(product);
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
		return productList;
	}
	//根据名字查询商品
	public List<ProductInfo> getProductbyName(String product_name){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductInfo> productList = new ArrayList<ProductInfo>();
		try {
			conn=DBUtil.getConnection();
			//模糊查询
			String sql = "select * from tb_product where product_name like ? ";
			ps=conn.prepareStatement(sql);
			ps.setString(1, "%"+product_name+"%");
			rs = ps.executeQuery();
			System.out.println(product_name);
			while(rs.next()) {
				ProductInfo product=new ProductInfo();
				product.setProduct_img(rs.getString("product_img"));
				product.setProduct_category(rs.getString("product_category"));
				product.setProduct_description(rs.getString("product_description"));
				product.setProduct_id(rs.getInt("product_id"));
				product.setProduct_name(rs.getString("product_name"));
				product.setProduct_price(rs.getFloat("product_price"));
				productList.add(product);
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
		return productList;
	}
	
	//根据类别查询商品
	public List<ProductInfo> getProductbyCategory(String product_category){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductInfo> productList = new ArrayList<ProductInfo>();
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from tb_product where product_category = ?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, product_category);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ProductInfo product=new ProductInfo();
				product.setProduct_img(rs.getString("product_img"));
				product.setProduct_category(rs.getString("product_category"));					product.setProduct_description(rs.getString("product_description"));
				product.setProduct_id(rs.getInt("product_id"));
				product.setProduct_name(rs.getString("product_name"));
				product.setProduct_price(rs.getFloat("product_price"));
				productList.add(product);
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
		return productList;
	}
	
	//根据id查询商品
	public List<ProductInfo> getProductbyId(int product_id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ProductInfo product=null;
		List<ProductInfo> productList = new ArrayList<ProductInfo>();
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from tb_product where product_id = ?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, product_id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				product=new ProductInfo();
				product.setProduct_img(rs.getString("product_img"));
				product.setProduct_category(rs.getString("product_category"));					product.setProduct_description(rs.getString("product_description"));
				product.setProduct_id(rs.getInt("product_id"));
				product.setProduct_name(rs.getString("product_name"));
				product.setProduct_price(rs.getFloat("product_price"));
				productList.add(product);
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
		return productList;
	}
		
	//删除商品
	public boolean deleteProduct(int product_id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from tb_product where product_id = ?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, product_id);
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
	
	//修改商品信息
	public boolean editProduct(ProductInfo product) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn=DBUtil.getConnection();
			String sql="update tb_product set product_name = ?, product_description=?,product_price=?,product_category=?, product_img=? where product_id=?";
			ps=conn.prepareStatement(sql);		
			ps.setString(1, product.getProduct_name());
			ps.setString(2, product.getProduct_description());
			ps.setFloat(3, product.getProduct_price());
			ps.setString(4, product.getProduct_category());
			ps.setString(5, product.getProduct_img());
			ps.setInt(6, product.getProduct_id());
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
