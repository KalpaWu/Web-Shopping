package com.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.CartInfo;
import com.bean.CustomerInfo;
import com.dao.CartDao;

/**
 * Servlet implementation class Customer_Delete_Cart
 */
@WebServlet("/Customer_Delete_Cart")
public class Customer_Delete_Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Customer_Delete_Cart() {
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
		List<CartInfo> cart_list=(List<CartInfo>)session.getAttribute("cart_list");
		String userName = customer.getUserName();
		CartDao cartDao=new CartDao();
		int cart_id=Integer.parseInt(request.getParameter("cart_id"));
		if(cartDao.deleteCart(userName, cart_id)) {
			request.setAttribute("info", "此商品已成功从购物车移除！<br>");
			if(cart_list!=null||!cart_list.isEmpty())
				session.removeAttribute("cart_list");
		}else {
			request.setAttribute("info", "此商品从购物车移除失败！<br>");
		}
		request.getRequestDispatcher("product_message.jsp").forward(request, response);
	}

}
