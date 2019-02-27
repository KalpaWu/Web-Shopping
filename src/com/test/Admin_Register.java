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
 * Servlet implementation class Admin_Register
 */
@WebServlet("/Admin_Register")
public class Admin_Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_Register() {
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
		//���ñ���
		response.setContentType("text/html");
		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");
				
		//��ȡ��������
		String admin_userName=request.getParameter("admin_userName");
		String admin_password=request.getParameter("admin_password");
		String admin_repassword=request.getParameter("admin_repassword");
				
		AdminDao adminDao=new AdminDao();
			
		if(admin_userName==null||admin_password==null||admin_repassword==null)
			request.setAttribute("info","���󣬴���ע����ϢΪ��!<br>");
		if(!(admin_password.equals(admin_repassword)))
			request.setAttribute("info", "ע�������ȷ�����벻һ��");
		if(admin_userName!=null&&!admin_userName.isEmpty()) {
			if(!adminDao.adminIsExist(admin_userName)) {
				AdminInfo admin=new AdminInfo();
				admin.setAUserName(admin_userName);
				admin.setAPassword(admin_password);
				adminDao.saveAdmin(admin);
				request.setAttribute("info", "��ϲ��"+admin_userName+"ע��ɹ���<br>");
			}else {
				request.setAttribute("info", "���󣬸��û����Ѵ��ڣ�<br>");
			}
		}
		request.getRequestDispatcher("admin_message.jsp").forward(request, response);
	}
}
