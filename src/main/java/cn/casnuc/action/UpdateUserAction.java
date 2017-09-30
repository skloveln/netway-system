package cn.casnuc.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.casnuc.entity.Permission;
import cn.casnuc.entity.SystemOperate;
import cn.casnuc.entity.User;
import cn.casnuc.service.SystemOperateService;
import cn.casnuc.service.UserService;

/**
 * 更新系统上的用户的信息
 * @author kaka
 *
 */
@SuppressWarnings("serial")
public class UpdateUserAction extends ActionSupport{
	
	private UserService userService;
	private JSONObject jsonResult;
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private SystemOperateService systemOperateService;
	
	public void setSystemOperateService(SystemOperateService systemOperateService) {
		this.systemOperateService = systemOperateService;
	}
	public JSONObject getJsonResult() {
//		System.out.println(jsonResult);
		return jsonResult;
	}
	public void setJsonResult(JSONObject jsonResult) {
		this.jsonResult = jsonResult;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String updateInfo(){
		
		User user = (User) request.getSession().getAttribute("userinfo");	
		if(user == null){
			return "relogin";
		}
		
		// 获取要修改的信息并封装
		String name = request.getParameter("name");
		user.setName(name);
		
		// 提交到业务层处理
		String rusult = userService.updateUser(user);
		
		// 记录日志
		SystemOperate systemOperate = new SystemOperate();
		systemOperate.setAccount(user.getAccount());
		systemOperate.setEvent("修改了个人信息");
		systemOperate.setIp(request.getRemoteAddr());
		systemOperate.setName(user.getName());
		systemOperate.setRole(user.getRole());
		systemOperate.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));			
		systemOperateService.insertSystemOperateLog(systemOperate);
		
		toResult(user, rusult, "user_modifyInfo");
		return SUCCESS;
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	public String modifyPassword() {
		
		User user = (User) request.getSession().getAttribute("userinfo");
		if(user == null){
			return "relogin";
		}
		
		String oldPw = request.getParameter("oldpassword");
		String newPw = request.getParameter("newpassword");
		
		if(!oldPw.equals(user.getPassword())){
			toResult(user, "原密码错误","user_modifyPassword");
		} else {
			
			user.setPassword(newPw);
			
			// 记录日志
			SystemOperate systemOperate = new SystemOperate();
			systemOperate.setAccount(user.getAccount());
			systemOperate.setEvent("修改了密码");
			systemOperate.setIp(request.getRemoteAddr());
			systemOperate.setName(user.getName());
			systemOperate.setRole(user.getRole());
			systemOperate.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));			
			systemOperateService.insertSystemOperateLog(systemOperate);
			
			toResult(user, userService.updateUser(user), "user_modifyPassword");
		}
		
		return SUCCESS;
	}

	private void toResult(User user, String result, String url) {
		
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
	 * 更新用户信息
	 * @return
	 */
	public String updateInfoJson(){
		
		User user = (User) request.getSession().getAttribute("userinfo");	
		if(user == null){
			// 封装JSON对象返回结果
			JSONObject json = new JSONObject();
			json.element("status", false);
			setJsonResult(json);
			
		}else{
			
			// 接受需要修改的数据
			String account = request.getParameter("account");
			String name = request.getParameter("name");
			
			boolean attackLog = Boolean.parseBoolean(request.getParameter("attackLog"));
			boolean gatewayConfig = Boolean.parseBoolean(request.getParameter("gatewayConfig"));
			boolean keywordConfig = Boolean.parseBoolean(request.getParameter("keywordConfig"));
			boolean protectConfig = Boolean.parseBoolean(request.getParameter("protectConfig"));
			boolean operateLog = Boolean.parseBoolean(request.getParameter("operateLog"));
			boolean protectLog = Boolean.parseBoolean(request.getParameter("protectLog"));
			boolean transferConfig = Boolean.parseBoolean(request.getParameter("transferConfig"));
			
			// 查找要修改信息的用户
			User updateUser = userService.findByAccount(account);
			
			// 修改信息
			Permission permission = updateUser.getPermission();
			permission.setAttackLog(attackLog);
			permission.setGatewayConfig(gatewayConfig);
			permission.setKeywordConfig(keywordConfig);
			permission.setOperateLog(operateLog);
			permission.setProtectConfig(protectConfig);
			permission.setProtectLog(protectLog);
			permission.setTransferConfig(transferConfig);			
			updateUser.setName(name);
			
			// 提交更新信息
			userService.updateUser(updateUser);
			
			//记录操作日志
			SystemOperate systemOperate = new SystemOperate();
			systemOperate.setAccount(user.getAccount());
			systemOperate.setEvent("修改了用户信息，姓名改为"+updateUser.getName()+",权限改为"+
					updateUser.getPermission().getGatewayConfig()+","+
					updateUser.getPermission().getKeywordConfig()+","+
					updateUser.getPermission().getProtectConfig()+","+
					updateUser.getPermission().getTransferConfig()+","+
					updateUser.getPermission().getAttackLog()+","+
					updateUser.getPermission().getOperateLog()+","+
					updateUser.getPermission().getProtectLog());
			systemOperate.setIp(request.getRemoteAddr());
			systemOperate.setName(user.getName());
			systemOperate.setRole(user.getRole());
			systemOperate.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			systemOperate.setRemarks("权限依次为网关配置、关键字配置、防护配置、传输配置、攻击日志、操作日志和防护日志");
			systemOperateService.insertSystemOperateLog(systemOperate);
			
			// 封装JSON对象返回结果
			JSONObject json = new JSONObject();
			json.element("status", true);
			setJsonResult(json);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 更新管理员信息
	 * @return
	 */
	public String updateAdmin(){
		
		User user = (User) request.getSession().getAttribute("userinfo");	
		if(user == null){
			
			// 封装JSON对象返回结果
			JSONObject json = new JSONObject();
			json.element("status", false);
			setJsonResult(json);
			
		}else{
			
			// 接受需要修改的数据
			String account = request.getParameter("account");
			String name = request.getParameter("name");
						
			// 查找要修改信息的用户
			User updateUser = userService.findByAccount(account);
			
			// 修改信息
			updateUser.setName(name);
			
			// 提交更新信息
			userService.updateUser(updateUser);
			
			// 记录操作日志
			SystemOperate systemOperate = new SystemOperate();
			systemOperate.setAccount(user.getAccount());
			systemOperate.setEvent("修改了管理员信息，姓名改为"+updateUser.getName());
			systemOperate.setIp(request.getRemoteAddr());
			systemOperate.setName(user.getName());
			systemOperate.setRole(user.getRole());
			systemOperate.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			systemOperateService.insertSystemOperateLog(systemOperate);
			
			// 封装JSON对象返回结果
			JSONObject json = new JSONObject();
			json.element("status", true);
			setJsonResult(json);
		}
		
		return SUCCESS;
	}
	
}
