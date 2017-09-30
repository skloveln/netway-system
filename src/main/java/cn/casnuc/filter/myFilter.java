package cn.casnuc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class myFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		// 所有对html或jsp的直接访问全部跳转到登录页
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;  
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String url = httpServletRequest.getRequestURI();  
        if(url != null && url.endsWith(".jsp")){
        	 httpServletResponse.sendRedirect(httpServletRequest.getContextPath()); 
        	 return;
        } 
        chain.doFilter(request, response);  
		
	}

	public void init(FilterConfig arg0) throws ServletException { }

}
