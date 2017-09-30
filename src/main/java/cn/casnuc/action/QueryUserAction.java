package cn.casnuc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import cn.casnuc.entity.PageInfo;
import cn.casnuc.entity.SystemOperate;
import cn.casnuc.entity.User;
import cn.casnuc.service.SystemOperateService;
import cn.casnuc.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 查找用户
 * @author kaka
 *
 */
@SuppressWarnings("serial")
public class QueryUserAction extends ActionSupport{
	
	private User user;
	private UserService userService;
	private JSONObject jsonResult;
	private SystemOperateService systemOperateService;
	private HttpServletRequest request = ServletActionContext.getRequest();
	
	public void setSystemOperateService(SystemOperateService systemOperateService) {
		this.systemOperateService = systemOperateService;
	}
	public JSONObject getJsonResult() {
//		System.out.println(jsonResult.toString());
		return jsonResult;
	}
	public void setJsonResult(JSONObject jsonResult) {
		this.jsonResult = jsonResult;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * 查看用户信息
	 * @return
	 */
	public String queryUser(){
		
		User loginUser = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
		
		// 判断用户是否失效
		if(loginUser == null){
			// 封装JSON对象返回结果
			JSONObject json = new JSONObject();
			json.element("status", false);
			setJsonResult(json);
			return SUCCESS;
		}

		// 根据用户角色设置查询对象
		User queryUser = new User();		
		if(loginUser.getRole().equals("系统管理员")){
			queryUser.setRole("用户");
		}else if(loginUser.getRole().equals("安全管理员")){
			queryUser.setRole("安全管理员");
		}else if(loginUser.getRole().equals("审计管理员")){
			queryUser.setRole("审计管理员");
		}
		
		return queryUserInfo(queryUser, loginUser);
	}
	
	/**
	 * 查看管理员信息
	 * @return
	 */
	public String queryAdmin(){
		
		User loginUser = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
		if(loginUser == null){
			// 封装JSON对象返回结果
			JSONObject json = new JSONObject();
			json.element("status", false);
			setJsonResult(json);
			return SUCCESS;
		}
		
		// 根据用户角色设置查询对象
		User queryUser = new User();		
		if(loginUser.getRole().equals("系统管理员")){
			queryUser.setRole("系统管理员");
		}else if(loginUser.getRole().equals("安全管理员")){
			queryUser.setRole("安全管理员");
		}else if(loginUser.getRole().equals("审计管理员")){
			queryUser.setRole("审计管理员");
		}
		
		return queryUserInfo(queryUser, loginUser);
		
	}
	
	public String queryUserInfo(User queryUser, User loginUser){
		
		// 接收提交信息并封装
		queryUser.setAccount(request.getParameter("account"));
		queryUser.setName(request.getParameter("name"));
		String pageStr = request.getParameter("page");
		String rowStr = request.getParameter("rows");
		if(pageStr == null || pageStr.trim().length() == 0){
			pageStr = "1";
		}
		Integer page = Integer.parseInt(pageStr);
		Integer rows = Integer.parseInt(rowStr);
		
		// 提交到业务层处理
		PageInfo pageInfo = userService.queryUser(queryUser, page, rows);
		
		//记录操作日志
		SystemOperate systemOperate = new SystemOperate();
		systemOperate.setAccount(loginUser.getAccount());
		systemOperate.setEvent("查看了用户名为"+queryUser.getAccount()+", 姓名为"+ queryUser.getName()+"的" + queryUser.getRole());
		systemOperate.setIp(request.getRemoteAddr());
		systemOperate.setName(loginUser.getName());
		systemOperate.setRole(loginUser.getRole());
		systemOperate.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		systemOperateService.insertSystemOperateLog(systemOperate);
		
		// 封装Json对象返回结果
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("total", pageInfo.getAllSize());
		map.put("rows", pageInfo.getList());
		JSONObject jsonChild = JSONObject.fromObject(map);
//		JSONObject json = new JSONObject();
//		json.element("status", true);
//		json.element("result", jsonChild);
//		setJsonResult(json);
		
		JSONArray ja = jsonChild.getJSONArray("rows");
		JSONArray jar = new JSONArray();
		
		for(int i=0; i<ja.size(); i++){
			JSONObject j = ja.getJSONObject(i);
			JSONObject a = new JSONObject();
			a.element("name", j.get("name"));
			a.element("account", j.get("account"));
			a.element("id", j.get("id"));
			a.element("role", j.get("role"));
			a.element("password", j.get("password"));
			a.element("attackLog", ((JSONObject)j.get("permission")).get("attackLog"));
			a.element("gatewayConfig", ((JSONObject)j.get("permission")).get("gatewayConfig"));
			a.element("keywordConfig", ((JSONObject)j.get("permission")).get("keywordConfig"));
			a.element("operateLog", ((JSONObject)j.get("permission")).get("operateLog"));
			a.element("protectConfig", ((JSONObject)j.get("permission")).get("protectConfig"));
			a.element("protectLog", ((JSONObject)j.get("permission")).get("protectLog"));
			a.element("transferConfig", ((JSONObject)j.get("permission")).get("transferConfig"));			
			jar.add(a);
		}
		
		JSONObject p = new JSONObject();
		JSONObject jsonr = new JSONObject();
		jsonr.element("total", pageInfo.getAllSize());
		jsonr.element("rows", jar);	
		p.element("status", true);
		p.element("result", jsonr);
	//	System.out.println(p.toString());
		setJsonResult(p);
		
		return SUCCESS;
	}
	
}
