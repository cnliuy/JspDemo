package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bo.BusinessBean;
import model.bo.UserBean;
import model.file.FileTools;
import model.vo.BusinessPojo;
import model.vo.User;

public class UpdateNew extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateNew() {
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
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();

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

		String businessId = request.getParameter("Id");
		String businessname = request.getParameter("Businessname");
		String businesslink = request.getParameter("Businesslink");
		
		FileTools ft = new FileTools();
		String filepathtype="listfile";	
		String filepath = ft.gogetFilePath(filepathtype) ;
		BusinessPojo bp = new BusinessPojo();
		bp.setId(Integer.parseInt(businessId));
		bp.setBusinessname(businessname);
		bp.setBusinesslink(businesslink);		
		String writenline =BusinessPojo.genStrbyPojoByBusinessPojo(bp);		
		ft.executeWriteFile(filepath, businessId, writenline);
		
		String url="./oper/list.jsp";
		BusinessBean bb = new BusinessBean() ; 				
		ArrayList<BusinessPojo> bpl =bb.businessList();
		request.setAttribute("BusinessPojoList",bpl);		

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
