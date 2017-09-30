package cn.casnuc.action;

import java.text.SimpleDateFormat;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.casnuc.entity.Permission;
import cn.casnuc.entity.SystemOperate;
import cn.casnuc.entity.User;
import cn.casnuc.service.SystemOperateService;
import cn.casnuc.service.UserService;

/**
 * 增加用户（包括三员和普通用户）
 * @author kaka
 *
 */
@SuppressWarnings("serial")
public class AddUserAction extends ActionSupport{
	
	private User user;
	private JSONObject result;
	private UserService userService;
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private SystemOperateService systemOperateService;
	
	public void setSystemOperateService(SystemOperateService systemOperateService) {
		this.systemOperateService = systemOperateService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public JSONObject getResult() {
//		System.out.println("get方法返回的data:"+result.toString());
		return result;
	}
	public void setResult(JSONObject result) {
		this.result = result;
	}
	
	@Override
	public String execute(){
		
		User loginUser = (User) request.getSession().getAttribute("userinfo");
		if (loginUser == null || !loginUser.getSessionId().equals( request.getSession().getId())) {
			
			return "relogin";
		
		} else {
			
			String result = null;
			if (loginUser.getRole().equals("系统管理员")) {
				result = addSysAdmin(user, loginUser);
			} else if (loginUser.getRole().equals("安全管理员")) {
				result = addSafeAdmin(user, loginUser);
			} else if (loginUser.getRole().equals("审计管理员")) {
				result = addAuditAdmin(user, loginUser);
			}
			
			toResult(loginUser.getRole(), result);

			return SUCCESS;
		}
	}
	
	private String addSysAdmin(User user, User loginUser){
		
		if (user.getRole().equals("系统管理员")) {
			
			//封装对象
			Permission permission = new Permission();
  			permission.setKeywordConfig(false);
			permission.setOperateLog(false);
			permission.setProtectConfig(false);
			permission.setTransferConfig(false);
			permission.setAttackLog(false);
			permission.setGatewayConfig(false);
			permission.setProtectLog(false);			
			user.setPermission(permission);
			
			// 记录日志
			SystemOperate systemOperate = new SystemOperate();
			systemOperate.setAccount(loginUser.getAccount());
			systemOperate.setEvent("增加了系统管理员"+ user.getAccount()+ "  " +user.getName());
			systemOperate.setIp(request.getRemoteAddr());
			systemOperate.setName(loginUser.getName());
			systemOperate.setRole(loginUser.getRole());
			systemOperate.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			systemOperateService.insertSystemOperateLog(systemOperate);
					
			return addUser(user);
			
		} else {
			
			//封装用户
			user.setRole("用户");
			Permission permission = new Permission();
			permission.setKeywordConfig(conversionData(request.getParameter("keywordConfig")));
			permission.setOperateLog(conversionData(request.getParameter("operateLog")));
			permission.setProtectConfig(conversionData(request.getParameter("protectConfig")));
			permission.setTransferConfig(conversionData(request.getParameter("transferConfig")));
			permission.setAttackLog(conversionData(request.getParameter("attackLog")));
			permission.setGatewayConfig(conversionData(request.getParameter("gatewayConfig")));
			permission.setProtectLog(conversionData(request.getParameter("protectLog")));
			user.setPermission(permission);
			
			// 记录日志
			SystemOperate systemOperate = new SystemOperate();
			systemOperate.setAccount(loginUser.getAccount());
			systemOperate.setEvent("增加了用户"+user.getAccount()+"  "+user.getName());
			systemOperate.setIp(request.getRemoteAddr());
			systemOperate.setName(loginUser.getName());
			systemOperate.setRole(loginUser.getRole());
			systemOperate.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			systemOperateService.insertSystemOperateLog(systemOperate);
			
			return addUser(user);
		}	
	}
	
	private String addAuditAdmin(User user, User loginUser) {

		//封装对象
		user.setRole("审计管理员");
		Permission permission = new Permission();
		permission.setKeywordConfig(false);
		permission.setOperateLog(false);
		permission.setProtectConfig(false);
		permission.setTransferConfig(false);
		permission.setAttackLog(false);
		permission.setGatewayConfig(false);
		permission.setProtectLog(false);		
		user.setPermission(permission);
		
		// 记录日志
		SystemOperate systemOperate = new SystemOperate();
		systemOperate.setAccount(loginUser.getAccount());
		systemOperate.setEvent("增加了审计管理员"+user.getAccount()+"  "+user.getName());
		systemOperate.setIp(request.getRemoteAddr());
		systemOperate.setName(loginUser.getName());
		systemOperate.setRole(loginUser.getRole());
		systemOperate.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		systemOperateService.insertSystemOperateLog(systemOperate);
		
		return addUser(user);
	}
	
	private String addSafeAdmin(User user, User loginUser) {
		
		// 封装对象
		user.setRole("安全管理员");
		Permission permission = new Permission();
		permission.setKeywordConfig(false);
		permission.setOperateLog(false);
		permission.setProtectConfig(false);
		permission.setTransferConfig(false);
		permission.setAttackLog(false);
		permission.setGatewayConfig(false);
		permission.setProtectLog(false);	
		user.setPermission(permission);
		
		// 记录操作日志、
		SystemOperate systemOperate = new SystemOperate();
		systemOperate.setAccount(loginUser.getAccount());
		systemOperate.setEvent("增加了安全管理员"+user.getAccount()+"  "+user.getName());
		systemOperate.setIp(request.getRemoteAddr());
		systemOperate.setName(loginUser.getName());
		systemOperate.setRole(loginUser.getRole());
		systemOperate.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		systemOperateService.insertSystemOperateLog(systemOperate);
		
		return addUser(user);
	}
	
	/**
	 * 调用业务层增加用户
	 * @param user	要添加的用户
	 * @return 以字符串返回结果信息
	 */
	private String addUser(User user) {
		return userService.addUser(user);		
	}
	
	/**
	 * 将String类型的权限转换为boolean类型
	 * @param string 要转换的字符转
	 * @return 返回boolean类型
	 */
	private boolean conversionData(String string){
		if(string != null && string.equals("true")){
			return true;
		}else{
			return false;
		}
	}
	
	private void toResult(String role, String result){
		
		String url = null;
		
		if(role.equals("系统管理员")){
			url = "user_add";
		}else if(role.equals("审计管理员")){
			url = "user_add_audit";
		}else if(role.equals("安全管理员")){
			url = "user_add_safe";
		}
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print("<script language='JavaScript'>alert(\""+ result +"\");window.location.href='/ssh/"+url+"'</script>");
		out.flush();
		out.close();
	}
	
	/**
	 * 检测用户名是否存在
	 * @return
	 */
	public String queryUserAccount(){
		
		String account = request.getParameter("account");		// 获取数据

		User queryUser = new User();
		queryUser.setAccount(account);
		List<User> list = userService.queryUser(queryUser);
		Map<String, Object> map = new HashMap<String, Object>();
		if(list.size() > 0){
			map.put("result", false);
		}else{
			map.put("result", true);
		}
		
		JSONObject json = JSONObject.fromObject(map);// 将map对象转换成json类型数据
		
		setResult(json);
		
		return SUCCESS;
	}
}
