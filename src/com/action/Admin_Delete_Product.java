package com.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ProductDao;

/**
 * Servlet implementation class Admin_DeleteProduct
 */
@WebServlet("/Admin_DeleteProduct")
public class Admin_Delete_Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_Delete_Product() {
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
		//设置编码
		response.setContentType("text/html");
		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");	
		
		int product_id=Integer.parseInt(request.getParameter("product_id"));
		ProductDao productDao=new ProductDao();
		if(productDao.deleteProduct(product_id)) {
			request.setAttribute("info", "此商品已成功删除！<br>");
			request.getRequestDispatcher("product_show.jsp").forward(request, response);
		}else {
			request.setAttribute("info", "没有商品符合当前搜索条件！<br>");
			request.getRequestDispatcher("product_show.jsp").forward(request, response);
		}
	}
}
