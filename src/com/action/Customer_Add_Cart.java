package com.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;

import com.bean.CartInfo;
import com.bean.CustomerInfo;
import com.bean.ProductInfo;
import com.dao.CartDao;
import com.dao.PhotoDao;
import com.dao.ProductDao;

/**
 * Servlet implementation class Customer_Add_Cart
 */
@WebServlet("/Customer_Add_Cart")
public class Customer_Add_Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Customer_Add_Cart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");
		
		HttpSession session=request.getSession();
		CustomerInfo customer=(CustomerInfo)session.getAttribute("customer");
		
		if(customer==null) {
			request.setAttribute("info", "错误，您还未登录！<br>");
		}else {
			String userName = customer.getUserName();
			int product_id=Integer.parseInt(request.getParameter("product_id"));
			int product_num=Integer.parseInt(request.getParameter("product_num"));
			//System.out.println("hello"+product_num);
			ProductDao productDao = new ProductDao();
			ProductInfo product=new ProductInfo();
			List<ProductInfo> productList=productDao.getProductbyId(product_id);
			if(productList==null||productList.isEmpty()) {
				request.setAttribute("info", "错误，输入的商品id不存在");
			}else {
				product=(ProductInfo)productList.get(0);
				float product_price=product.getProduct_price();
				String product_name=product.getProduct_name();
				String product_img=product.getProduct_img();
				
				CartDao cartDao=new CartDao();
				CartInfo cart=new CartInfo();
				cart.setProduct_id(product_id);
				cart.setProduct_img(product_img);
				cart.setProduct_name(product_name);
				cart.setProduct_price(product_price);
				cart.setProduct_num(product_num);
				cart.setUserName(userName);
				cartDao.saveCart(cart);
				request.setAttribute("info", product_name+"已成功添加到购物车！<br>");
			}
		}
		request.getRequestDispatcher("product_message.jsp").forward(request, response);
	}

}
