package com.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.AdminInfo;
import com.dao.AdminDao;

/**
 * Servlet implementation class Admin_Login
 */
@WebServlet("/Admin_Login")
public class Admin_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_Login() {
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
		//设置编码
		response.setContentType("text/html");
		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");
		
		//获取客户端发来的用户名和密码
		String admin_userName = request.getParameter("admin_userName");
		String admin_password = request.getParameter("admin_password");
		
		AdminDao adminDao=new AdminDao();
		AdminInfo admin=adminDao.login(admin_userName, admin_password);
		
		if(admin!=null) {
			request.getSession().setAttribute("admin", admin);
			request.getRequestDispatcher("admin_message.jsp").forward(request, response);
		}else {
			request.setAttribute("info", "错误，用户名或密码错误！<br>");
			request.getRequestDispatcher("admin_message.jsp").forward(request, response);
		}
	}

}
