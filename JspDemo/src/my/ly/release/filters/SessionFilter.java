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

public class SessionFilter implements Filter {

	public void destroy() {		

	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response=(HttpServletResponse) servletResponse;
		
		HttpSession session = request.getSession();
		String username=(String) session.getAttribute("username");
		
		String uri=request.getRequestURI();
		String path=request.getContextPath();
		
		String url=uri.replace(path, "");
		System.out.println(url);
		//chain.doFilter(request, response);
		
		if (username!=null||"/login.jsp".equals(url)||"/LoginCheck".equals(url)) {
			chain.doFilter(request, response);
		} else {
			RequestDispatcher dispatcher=request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}

	}

	public void init(FilterConfig config) throws ServletException {		

	}

}
