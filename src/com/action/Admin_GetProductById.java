package com.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.ProductInfo;
import com.dao.ProductDao;

/**
 * Servlet implementation class Admin_GetProductById
 */
@WebServlet("/Admin_GetProductById")
public class Admin_GetProductById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_GetProductById() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ñ���
		response.setContentType("text/html");
		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");
		
		int product_id=Integer.parseInt(request.getParameter("product_id"));
		ProductDao productDao=new ProductDao();
		List<ProductInfo> product_list=productDao.getProductbyId(product_id);
		if(product_list!=null&&!product_list.isEmpty()) {
			request.getSession().setAttribute("product_list", product_list);
			request.getRequestDispatcher("product_show.jsp").forward(request, response);
		}else {
			request.setAttribute("info", "û����Ʒ���ϵ�ǰ����������<br>");
			request.getRequestDispatcher("product_show.jsp").forward(request, response);
		}
	}

}
