package com.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.CustomerInfo;
import com.dao.CustomerDao;


/**
 * Servlet implementation class Customer_Login
 */
@WebServlet("/Customer_Login")
public class Customer_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Customer_Login() {
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
		//���ñ���
		response.setContentType("text/html");
		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");
		
		//��ȡ�ͻ��˷������û���������
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		CustomerDao customerDao=new CustomerDao();
		CustomerInfo customer=customerDao.login(userName, password);
		
		if(customer!=null) {
			request.getSession().setAttribute("customer", customer);
			request.getRequestDispatcher("customer_message.jsp").forward(request, response);
		}else {
			request.setAttribute("info", "�����û������������<br>");
			request.getRequestDispatcher("customer_message.jsp").forward(request, response);
		}
	}

}
