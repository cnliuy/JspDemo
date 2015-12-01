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

import model.bo.BusinessBean;
import model.bo.UserBean;
import model.vo.BusinessPojo;
import model.vo.User;
@Deprecated
public class LoginCheck extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginCheck() {
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
	@Deprecated
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println("LoginCheck");
		HttpSession session = request.getSession();
  		String username=request.getParameter("username");
  		String password=request.getParameter("password");
  		
		//调用用户工具类
  		UserBean ub= new UserBean();
  
		boolean flag= ub.check(username,password);	
		
		//验证失败后跳转的URL
		String url="/loginerror.jsp";
		//判断验证的结果
		if(flag){
			boolean loginflag = ub.login(username, password);
			
			
			if  (loginflag){
				session.setAttribute("username",username);
				session.setAttribute("password",password);			
				User oneByNamePwd = ub.getOneByNamePwd(username, password);
				String userid=oneByNamePwd.getUserid();
				session.setAttribute("userid",userid);

				url="./oper/index.jsp";
			}else{
				System.out.println("function login  error!");
			}
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
