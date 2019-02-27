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
		//设置编码
		response.setContentType("text/html");
		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");
		
		//获取表单的属性
//		String fileName=request.getParameter("image");
//		String product_name=request.getParameter("product_name");
//		String product_description=request.getParameter("product_description");
//		float product_price=Float.parseFloat(request.getParameter("product_price"));
//		String product_category=request.getParameter("product_category");
		
		List<String> list=new ArrayList<String>();
		String fileName=PhotoDao.getPhotoNewName();
		ServletContext servletContext=null;
		servletContext=request.getSession().getServletContext();
		//第一步:获取页面上上传的图片资源
		List<FileItem> items=PhotoDao.getRequsetFileItems(request,servletContext);
		boolean isLoadToSQL=false;
//		if(items==null) {
//			System.out.println("oh!");
//		}
		if(items!=null&&!items.isEmpty()) {
			for(FileItem item:items) {
				if(!item.isFormField()){
					//判断后缀名是否是jpg
					if(PhotoDao.isGif(item)) {
						isLoadToSQL=PhotoDao.saveFile(item,fileName);
					}else {
						 System.out.println("后缀格式有误，保存文件失败");
					}
				}else { 
					//获取表单中的非文件值
					list.add(item.getString("UTF-8"));
				}
			}
		}

		String product_name=(String)list.get(0);
		//System.out.println(product_name);
		float product_price=Float.parseFloat((String)list.get(1));
		String product_category=(String)list.get(2);
		String product_description=(String)list.get(3);
		//存在数据库里面的照片路径是在项目里的相对路径
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
			request.setAttribute("info", "恭喜，"+product_name+"添加成功！<br>");
		}else {
			request.setAttribute("info", "错误，该商品不存在！<br>");
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
