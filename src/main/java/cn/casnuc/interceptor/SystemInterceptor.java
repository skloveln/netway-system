package cn.casnuc.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;

@SuppressWarnings("serial")
public class SystemInterceptor implements Interceptor{

	public void destroy() { }

	public void init() { }

	public String intercept(ActionInvocation invocation) throws Exception {
		
		// 获取request对象
		HttpServletRequest request = ServletActionContext.getRequest();
		
		// 获取action的代理对象
		ActionProxy proxy = invocation.getProxy();
		 
		// 获取当前执行的方法名
		String methodName = proxy.getMethod();
		String actionName = proxy.getActionName();
		
		if("exit".equals(actionName)){
			return invocation.invoke();
		}
		
		// 对action的访问做判断
		if (!"login".equals(methodName)) {
			// 先获取当前登陆的用户
			Object obj = request.getSession().getAttribute("userinfo");
			if (obj == null) {
				// 没有登陆
				return "relogin";
			} else {
				// 当前用户有登陆
				return invocation.invoke();
			}
		} else {
			// 说明当前用户正在登陆
			return invocation.invoke();
		}
	}

}
