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

/**
 * Servlet implementation class Customer_Pay_All
 */
@WebServlet("/Customer_Pay_All")
public class Customer_Pay_All extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Customer_Pay_All() {
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
			request.setAttribute("info", "´íÎó£¬Äú»¹Î´µÇÂ¼£¡<br>");
		}else {
			String userName = customer.getUserName();
			String address=customer.getAddress();
			String mobile=customer.getMobile();
			
			List<CartInfo> cart_list=(List<CartInfo>) session.getAttribute("cart_list");
			List<OrderInfo> orderList=new ArrayList();
			Date dNow = new Date( );
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			String order_time=ft.format(dNow);
			for(int i=0;i<cart_list.size();i++) {
				CartInfo cart=(CartInfo)cart_list.get(i);
				OrderInfo order = new OrderInfo();
				order.setAddress(address);
				order.setCart_id(cart.getCart_id());
				order.setMobile(mobile);
				order.setOrder_time(order_time);
				order.setProduct_id(cart.getProduct_id());
				order.setProduct_img(cart.getProduct_img());
				order.setProduct_name(cart.getProduct_name());
				order.setProduct_num(cart.getProduct_num());
				order.setProduct_price(cart.getProduct_price());
				order.setTotal_price(cart.getProduct_price()*cart.getProduct_num());
				order.setUserName(userName);
				orderList.add(order);
				
				request.getSession().setAttribute("order_list", orderList);
			}
		}
		request.getRequestDispatcher("customer_pay.jsp").forward(request, response);
	}

}
