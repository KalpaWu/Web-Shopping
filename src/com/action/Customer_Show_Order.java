package com.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.CustomerInfo;
import com.bean.OrderInfo;
import com.dao.OrderDao;

/**
 * Servlet implementation class Customer_Show_Order
 */
@WebServlet("/Customer_Show_Order")
public class Customer_Show_Order extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Customer_Show_Order() {
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
			String userName=customer.getUserName();
			OrderDao orderDao=new OrderDao();
			List<OrderInfo> orderList=orderDao.getOrderByName(userName);
			if(orderList==null||orderList.isEmpty()) {
				request.setAttribute("info", "您当前没有订单信息！<br>");
			}else {
				request.getSession().setAttribute("customer_order_list", orderList);
			}
		}
		request.getRequestDispatcher("customer_order_show.jsp").forward(request, response);
	}

}
