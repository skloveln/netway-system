package cn.casnuc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import cn.casnuc.entity.ProtectResult;
import cn.casnuc.entity.ProtectReturn;
import cn.casnuc.entity.SystemOperate;
import cn.casnuc.entity.User;
import cn.casnuc.service.ProtectConfigService;
import cn.casnuc.service.SystemOperateService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 查看防护策略配置信息
 * @author kaka
 *
 */
@SuppressWarnings("serial")
public class ProtectConfigAction extends ActionSupport{
	
	private ProtectConfigService protectConfigService;
	private SystemOperateService systemOperateService;
	private ProtectResult protectResult;
	
	public void setSystemOperateService(SystemOperateService systemOperateService) {
		this.systemOperateService = systemOperateService;
	}
	public ProtectResult getProtectResult() {
		return protectResult;
	}
	public void setProtectResult(ProtectReturn protectReturn) {
		ProtectResult protectResult = new ProtectResult();
		protectResult.dataConver(protectReturn);
		this.protectResult = protectResult;
	}
	public void setProtectConfigService(ProtectConfigService protectConfigService) {
		this.protectConfigService = protectConfigService;
	}
	
	@Override
	public String execute() {

		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("userinfo");
		
		if(user == null){			
			return "relogin";
		}
		
		//查询防护配置
		ProtectReturn pr = protectConfigService.queryProtectConfig();
		setProtectResult(pr);
		Map<String, Object> map = ActionContext.getContext().getContextMap();
		map.put("key", pr.getList());
		
		//记录操作日志
		SystemOperate systemOperate = new SystemOperate();
		systemOperate.setAccount(user.getAccount());
		systemOperate.setEvent("查看了防护策略配置信息");
		systemOperate.setIp(ServletActionContext.getRequest().getRemoteAddr());
		systemOperate.setName(user.getName());
		systemOperate.setRole(user.getRole());
		systemOperate.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		systemOperateService.insertSystemOperateLog(systemOperate);
		
		return SUCCESS;
	}
}
