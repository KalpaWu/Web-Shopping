package com.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.OrderInfo;
import com.dao.OrderDao;

/**
 * Servlet implementation class Customer_Submit_Order
 */
@WebServlet("/Customer_Submit_Order")
public class Customer_Submit_Order extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Customer_Submit_Order() {
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
		HttpSession session=request.getSession();
		List<OrderInfo> order_list=(List<OrderInfo>)session.getAttribute("order_list");
		OrderDao orderDao=new OrderDao();
		for(int i=0;i<order_list.size();i++) {
			OrderInfo order=(OrderInfo)order_list.get(i);
			orderDao.saveOrder(order);
		}
		request.setAttribute("info", "¹ºÂò³É¹¦£¡<br>");
		request.getRequestDispatcher("pay_success.jsp").forward(request, response);
	}

}
