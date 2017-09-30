package cn.casnuc.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import cn.casnuc.entity.SystemOperate;
import cn.casnuc.entity.User;
import cn.casnuc.service.SystemOperateService;
import cn.casnuc.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 用户登录的Action
 * @author kaka
 *
 */
@SuppressWarnings("serial")
public class UserAction extends ActionSupport{
	
	private UserService userService;
	private User loginUser;
	private String loginResult;
	private SystemOperateService systemOperateService;
	private HttpServletRequest request = ServletActionContext.getRequest();
	private JSONObject jsonResult;
	
	
	public JSONObject getJsonResult() {
		return jsonResult;
	}
	public void setJsonResult(JSONObject jsonResult) {
		this.jsonResult = jsonResult;
	}
	public void setSystemOperateService(SystemOperateService systemOperateService) {
		this.systemOperateService = systemOperateService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String toLoginUI(){
		return "login";
	}
	public User getLoginUser() {
		return loginUser;
	}	
	public void setLoginUser(User user) {
		this.loginUser = user;
	}		
	public String getLoginResult() {
		return loginResult;
	}
	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}
	
	/**
	 * 用户登录
	 */
	public String login(){		
		
		try{
			if(this.loginUser != null && (this.loginUser.getAccount() != null || this.loginUser.getPassword() != null)){			
				
				//判断账户是否存在
				User account = userService.findByAccount(this.loginUser.getAccount());
				if(account == null){				
					loginResult = "用户名不存在！";
					return toLoginUI();
				}
				
				//判断账户是否锁定
				if(account.getFlag()){
					
					//判断锁定时间是否还有效
					if(isLimit(account.getTime())){
						loginResult = "该帐号被锁定，请稍后再试！";					
						return toLoginUI();
					}
					
					//解除限制
					account.setFlag(false);				//将限制标志值为false
					account.setCount(0);				//将密码输错次数置0
					userService.updateUser(account);
				}
				
				// 账户没有锁定
				User user = userService.findByAccountAndPass(this.loginUser.getAccount(), this.loginUser.getPassword());									
				
				// 判断密码是否正确
				if(user != null){			
					
					//设置sessionId
					user.setSessionId(request.getSession().getId());
					// 将错误次数清0
					user.setCount(0);
					userService.updateUser(user);
					
					// 登陆成功
					request.getSession().setAttribute("userinfo", user);				
					
					// 记录日志
					SystemOperate systemOperate = new SystemOperate();
					systemOperate.setAccount(user.getAccount());
					systemOperate.setName(user.getName());
					systemOperate.setRole(user.getRole());
					systemOperate.setEvent("登录了系统");
					systemOperate.setIp(request.getRemoteAddr());	// 设置ip
					systemOperate.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));  //设置日期 
					
					systemOperateService.insertSystemOperateLog(systemOperate);
					
					return SUCCESS;
					
				}

				// 密码输入错误
				// 判断距离上次密码输入错误时间是否大于5分钟
				if(isGtTimes(account.getLastErrorTime())){					
					account.setCount(0);	// 将密码输入错误次数置0
				}
				
				// 判断密码输错次数是否到达5次
				if(account.getCount() >= 4) {
					
					account.setFlag(true); 			// 将锁定标志位置为true
					account.setTime(new Date());	// 设置锁定时间
					account.setLastErrorTime(new Date());  // 设置密码输入错误时间
					userService.updateUser(account);
					
					// 记录账户锁定事件
					SystemOperate systemOperate = new SystemOperate();
					systemOperate.setAccount("");
					systemOperate.setName("");
					systemOperate.setRole("");
					systemOperate.setEvent("账户 " + account.getAccount() + " 被锁定");
					systemOperate.setRemarks("账户被锁定");
					systemOperate.setIp(request.getRemoteAddr());	// 设置ip
					systemOperate.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));  //设置日期 
					systemOperateService.insertSystemOperateLog(systemOperate);
					
					loginResult = "密码输错5次，账户被锁定";
					return toLoginUI();
				}
				
				// 输错次数小于5次
				Integer errorCount = account.getCount() + 1;
				account.setCount(errorCount);	// 设置输入错误次数
				account.setLastErrorTime(new Date());  // 设置密码输入错误时间
				userService.updateUser(account);
				
				loginResult = "密码不正确，输入错误" + errorCount + "次！";
				return toLoginUI();
				
			}else{
				
				loginResult = "用户名或密码不能为空！";
				
				return toLoginUI();
			}
			
		}catch(Exception e){
			
			System.out.println("登录出现异常"+e);
			
			return toLoginUI();
		}
	}
	
	/**
	 * 判断当前时间与给定时间差是否小于1小时
	 * @param time
	 * @return 小于1小时返回true
	 * @throws ParseException
	 */
	private boolean isLimit(Date date) throws ParseException {
		
		if(date == null){
    		return false;
    	}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		Date now = sdf.parse(sdf.format(new Date()));
        if( now.getTime()-date.getTime() < 60*60*1000 ){
            return true;
        }
        else{
            return false;
        }
	}
	
	/**
     * 判断当前时间与给定时间差是否大于5分钟 
     * @param date
     * @return 大于5分钟返回true
     * @throws Exception
     */
    public boolean isGtTimes(Date date) throws Exception{
        
    	if(date == null){
    		return true;
    	}
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Date now=sdf.parse(sdf.format(new Date()));
        if( now.getTime()-date.getTime() > 5*60*1000 ){
            return true;
        } else {
            return false;
        }
    }
	
    /**
     * 判断用户的登录状况
     */
    public String userState(){
    	
    	User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");	
    	
    	Map<String, Object> map = new LinkedHashMap<String, Object>();
    	
    	if(user == null){
    		map.put("status", "null");
    	}else if(user.getSessionId().equals(userService.findByAccount(user.getAccount()).getSessionId())){
    		map.put("status", "ok");
    	}else{
    		map.put("status", "relogin");
    	}
    	
    	JSONObject resultJson = JSONObject.fromObject(map);
		setJsonResult(resultJson);
    	
    	return SUCCESS;
    }
    
    /**
     * 
     * @return
     */
	public String publicMethod(){
		return SUCCESS;
	}

}