package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bo.AdminBean;
import model.bo.BusinessBean;
import model.bo.UserBean;
import model.vo.BusinessPojo;
import model.vo.User;

public class AdminLoginCheck extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AdminLoginCheck() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 
		HttpSession session = request.getSession();
  		String username=request.getParameter("username");
  		String password=request.getParameter("password");
  		
		//调用用户工具类
  		AdminBean ub= new AdminBean();
  
		boolean flag= ub.checkAdminUser(username, password);	
		
		//验证失败后跳转的URL
		String url="/loginerror.jsp";
		//判断验证的结果
		if(flag){ 
			session.setAttribute("username",username);
			session.setAttribute("password",password);					
			url="./oper/list.jsp";
			
			BusinessBean bb = new BusinessBean() ; 				
			ArrayList<BusinessPojo> bpl =bb.businessList();
			request.setAttribute("BusinessPojoList",bpl);
			
		}else{
			System.out.println("function login  error!");
		}
	  	RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request,response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
