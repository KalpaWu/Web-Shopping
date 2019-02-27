package com.action;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class Customer_Show_Cart
 */
@WebServlet("/Customer_Show_Cart")
public class Customer_Show_Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Customer_Show_Cart() {
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
			List<CartInfo> cartList=new ArrayList();
			CartDao cartDao=new CartDao();
			cartList=cartDao.getAllcart(userName);
			if(cartList==null||cartList.isEmpty()) {
				List<CartInfo> cart_list=(List<CartInfo>)session.getAttribute("cart_list");
				if(cart_list!=null) {
					session.removeAttribute("cart_list");
				}
				request.setAttribute("info", "当前购物车没有商品！<br>");
			}else {
				request.getSession().setAttribute("cart_list", cartList);
			}
		}
		request.getRequestDispatcher("cart_show.jsp").forward(request, response);
	}

}
