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
 * Servlet implementation class Customer_Register
 */
@WebServlet("/Customer_Register")
public class Customer_Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Customer_Register() {
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
		
		//��ȡ��������
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		String realName=request.getParameter("realName");
		String address=request.getParameter("address");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		String repassword=request.getParameter("repassword");
		
		CustomerDao customerDao=new CustomerDao();
		
		if(userName==null||password==null||realName==null||address==null||email==null||mobile==null||repassword==null)
			request.setAttribute("info","���󣬴���ע����ϢΪ��!<br>");
		if(!(password.equals(repassword)))
			request.setAttribute("info", "ע�������ȷ�����벻һ��");
		if(userName!=null&&!userName.isEmpty()) {
			if(!customerDao.customerIsExist(userName)) {
				CustomerInfo customer=new CustomerInfo();
				customer.setUserName(userName);
				customer.setAddress(address);
				customer.setEmail(email);
				customer.setMobile(mobile);
				customer.setPassword(password);
				customer.setRealName(realName);
				customerDao.saveCustomer(customer);
				request.setAttribute("info", "��ϲ��"+userName+"ע��ɹ���<br>");
			}else {
				request.setAttribute("info", "���󣬸��û����Ѵ��ڣ�<br>");
			}
		}
		request.getRequestDispatcher("customer_message.jsp").forward(request, response);
	}

}
