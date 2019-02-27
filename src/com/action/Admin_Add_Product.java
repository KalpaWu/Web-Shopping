package com.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import com.bean.ProductInfo;
import com.dao.PhotoDao;
import com.dao.ProductDao;

/**
 * Servlet implementation class Admin_Add_Product
 */
@WebServlet("/Admin_Add_Product")
public class Admin_Add_Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_Add_Product() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ñ���
		response.setContentType("text/html");
		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");
		
		//��ȡ��������
//		String fileName=request.getParameter("image");
//		String product_name=request.getParameter("product_name");
//		String product_description=request.getParameter("product_description");
//		float product_price=Float.parseFloat(request.getParameter("product_price"));
//		String product_category=request.getParameter("product_category");
		
		List<String> list=new ArrayList<String>();
		String fileName=PhotoDao.getPhotoNewName();
		ServletContext servletContext=null;
		servletContext=request.getSession().getServletContext();
		//��һ��:��ȡҳ�����ϴ���ͼƬ��Դ
		List<FileItem> items=PhotoDao.getRequsetFileItems(request,servletContext);
		boolean isLoadToSQL=false;
//		if(items==null) {
//			System.out.println("oh!");
//		}
		if(items!=null&&!items.isEmpty()) {
			for(FileItem item:items) {
				if(!item.isFormField()){
					//�жϺ�׺���Ƿ���jpg
					if(PhotoDao.isGif(item)) {
						isLoadToSQL=PhotoDao.saveFile(item,fileName);
					}else {
						 System.out.println("��׺��ʽ���󣬱����ļ�ʧ��");
					}
				}else { 
					//��ȡ���еķ��ļ�ֵ
					list.add(item.getString("UTF-8"));
				}
			}
		}

		String product_name=(String)list.get(0);
		//System.out.println(product_name);
		float product_price=Float.parseFloat((String)list.get(1));
		String product_category=(String)list.get(2);
		String product_description=(String)list.get(3);
		//�������ݿ��������Ƭ·��������Ŀ������·��
		String product_img= request.getContextPath()+"/image/"+fileName;

		ProductDao productDao=new ProductDao();
		if(product_name!=null&&!product_name.isEmpty()) {
			ProductInfo product=new ProductInfo();
			product.setProduct_description(product_description);
			product.setProduct_category(product_category);
			product.setProduct_name(product_name);
			product.setProduct_price(product_price);
			product.setProduct_img(product_img);
			productDao.saveProduct(product);
			request.setAttribute("info", "��ϲ��"+product_name+"��ӳɹ���<br>");
		}else {
			request.setAttribute("info", "���󣬸���Ʒ�����ڣ�<br>");
		}
		request.getRequestDispatcher("product_message.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
