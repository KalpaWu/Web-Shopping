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
 * Servlet implementation class Admin_Show_All_Order
 */
@WebServlet("/Admin_Show_All_Order")
public class Admin_Show_All_Order extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_Show_All_Order() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");
		
		OrderDao orderDao=new OrderDao();
		List<OrderInfo> orderList=orderDao.getAllOrder();
		if(orderList==null||orderList.isEmpty()) {
			request.setAttribute("info", "当前没有订单信息！<br>");
		}else {
			request.getSession().setAttribute("all_order_list", orderList);
		}
		request.getRequestDispatcher("admin_show_all_order.jsp").forward(request, response);
	}

}
