package my.ly.release.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;


public class SessionFilter implements Filter {
	/**   
	* 需要排除的页面   
	*/    	  
	private String excludedPages;       
	private String[] excludedPageArray;  

	public void destroy() {		

	}

	public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response=(HttpServletResponse) servletResponse;		
		HttpSession session = request.getSession();
		
		boolean isExcludedPage = false;     
		for (String page : excludedPageArray) {
			//判断是否在过滤url之外     
			if( request.getServletPath().equals(page)){     
				isExcludedPage = true;     
				break;     
			}     
		} 
		
		
		if (isExcludedPage) {
			//---是无需过滤的url   					
			chain.doFilter(request, response);
		}else{
			//---需要过滤的url，判断session是否存在 		
			String username=(String) session.getAttribute("username");
			
			String uri=request.getRequestURI();
			String path=request.getContextPath();		
			String url=uri.replace(path, "");
			//System.out.println(url);

		
			//if (username!=null||"/login.jsp".equals(url)||"/LoginCheck".equals(url)) {
			if (username!=null) {	
				chain.doFilter(request, response);
			}else{
				RequestDispatcher dispatcher=request.getRequestDispatcher("/login.jsp");
				dispatcher.forward(request, response);
			}
		}

	}


	/**   
	* 初始化函数，获取需要排除在外的url   
	*/	  
	public void init(FilterConfig fConfig) throws ServletException {     
		excludedPages = fConfig.getInitParameter("excludedPages");  
		System.out.println("--------"+excludedPages);
		if (StringUtils.isNotEmpty(excludedPages)) {     
			excludedPageArray = excludedPages.split(",");     
		}    
		return; 
	}

}
