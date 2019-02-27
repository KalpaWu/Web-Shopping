package com.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.CartInfo;
import com.bean.CustomerInfo;
import com.bean.OrderInfo;
import com.bean.ProductInfo;
import com.dao.CartDao;
import com.dao.ProductDao;

/**
 * Servlet implementation class Customer_Payment
 */
@WebServlet("/Customer_Payment")
public class Customer_Pay_One extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Customer_Pay_One() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			String address=customer.getAddress();
			String mobile=customer.getMobile();
			int product_id=Integer.parseInt(request.getParameter("product_id"));
			int product_num=Integer.parseInt(request.getParameter("product_num"));
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
				float total_price=product_price*product_num;
				int cart_id=0;
				OrderInfo order = new OrderInfo();
				if(request.getParameter("cart_id")!=null) {
					cart_id=Integer.parseInt(request.getParameter("cart_id"));
					order.setCart_id(cart_id);
				}
				else
					order.setCart_id(cart_id);
				
				Date dNow = new Date( );
				SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
				String order_time=ft.format(dNow);
				order.setOrder_time(order_time);
				order.setAddress(address);
				order.setMobile(mobile);
				order.setProduct_id(product_id);
				order.setProduct_img(product_img);
				order.setProduct_name(product_name);
				order.setProduct_num(product_num);
				order.setProduct_price(product_price);
				order.setTotal_price(total_price);
				order.setUserName(userName);
				order.setCart_id(cart_id);
				List<OrderInfo> orderList=new ArrayList();
				orderList.add(order);
				
				request.getSession().setAttribute("order_list", orderList);
			}
		}
		request.getRequestDispatcher("customer_pay.jsp").forward(request, response);
	}

}
