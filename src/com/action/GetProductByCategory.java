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
 * Servlet implementation class GetProductByCategory
 */
@WebServlet("/GetProductByCategory")
public class GetProductByCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProductByCategory() {
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
		
		String product_category=request.getParameter("product_category");
		ProductDao productDao=new ProductDao();
		List<ProductInfo> product_list=productDao.getProductbyCategory(product_category);
		
		if(product_list!=null&&!product_list.isEmpty()) {
			request.getSession().setAttribute("product_list", product_list);
			request.getRequestDispatcher("product_show.jsp").forward(request, response);
		}else {
			request.setAttribute("info", "没有商品符合当前搜索条件！<br>");
			request.getRequestDispatcher("product_show.jsp").forward(request, response);
		}
	}

}
