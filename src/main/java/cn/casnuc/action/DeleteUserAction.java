package cn.casnuc.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import cn.casnuc.entity.SystemOperate;
import cn.casnuc.entity.User;
import cn.casnuc.service.SystemOperateService;
import cn.casnuc.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 删除用户（包括管理员和用户）
 * @author kaka
 *
 */
@SuppressWarnings("serial")
public class DeleteUserAction extends ActionSupport{

	private UserService userService;
	private JSONObject jsonResult;
	private SystemOperateService systemOperateService;
	
	public void setSystemOperateService(SystemOperateService systemOperateService) {
		this.systemOperateService = systemOperateService;
	}
	public JSONObject getJsonResult() {
		return jsonResult;
	}
	public void setJsonResult(JSONObject jsonResult) {
		this.jsonResult = jsonResult;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public String execute() throws Exception {
		// 判断用户信息是否有效
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("userinfo");
		if(user == null){			
			
			// 封装JSON对象返回结果
			JSONObject json = new JSONObject();
			json.element("status", false);
			setJsonResult(json);			
			return SUCCESS;
		
		} else {
			
			HttpServletRequest request = ServletActionContext.getRequest();
			String account = request.getParameter("account");
			
			// 封装要删除的用户
			User removeUser = new User();
			removeUser.setAccount(account);			
			
			// 调用业务层
			removeUser = userService.deleteUser(removeUser);
			
			//记录操作日志
			SystemOperate systemOperate = new SystemOperate();
			systemOperate.setAccount(user.getAccount());
			systemOperate.setEvent("删除了用户，用户名为"+removeUser.getAccount() + "，姓名为"+removeUser.getName());
			systemOperate.setIp(request.getRemoteAddr());
			systemOperate.setName(user.getName());
			systemOperate.setRole(user.getRole());
			systemOperate.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			systemOperate.setRemarks("被删除角色为" + removeUser.getRole());
			systemOperateService.insertSystemOperateLog(systemOperate);
			
			// 返回json对象
			JSONObject json = new JSONObject();
			json.element("status", true); 
			setJsonResult(json);			
			
			return SUCCESS;
		}				
	}
}
