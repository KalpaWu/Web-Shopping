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
 * Servlet implementation class GetProductByName
 */
@WebServlet("/GetProductByName")
public class GetProductByName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProductByName() {
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
		
		//设置编码
		response.setContentType("text/html");
		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");
		
		String product_name=request.getParameter("product_name");
		//System.out.println("hello"+product_name);
		ProductDao productDao=new ProductDao();
		List<ProductInfo> product_list=productDao.getProductbyName(product_name);
		if(product_list!=null&&!product_list.isEmpty()) {
			request.getSession().setAttribute("product_list", product_list);
			request.getRequestDispatcher("product_show.jsp").forward(request, response);
		}else {
			request.setAttribute("info", "没有商品符合当前搜索条件！<br>");
			request.getRequestDispatcher("product_show.jsp").forward(request, response);
		}
	}

}
