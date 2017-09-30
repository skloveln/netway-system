package cn.casnuc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.struts2.ServletActionContext;

import cn.casnuc.entity.SystemOperate;
import cn.casnuc.entity.TransferConfig;
import cn.casnuc.entity.TransferResult;
import cn.casnuc.entity.User;
import cn.casnuc.service.SystemOperateService;
import cn.casnuc.service.TransferConfigService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 查看单导上的传输服务配置信息
 * @author kaka
 *
 */
@SuppressWarnings("serial")
public class TransferConfigAction extends ActionSupport{
	
	private TransferResult transferResult;
	private TransferConfigService transferConfigService;
	private SystemOperateService systemOperateService;
		
	public TransferResult getTransferResult() {
		return transferResult;
	}
	public void setTransferResult(TransferConfig transferConfig) {
		
		TransferResult transferResult = new TransferResult();
		transferResult.dataConver(transferConfig);
		
		this.transferResult = transferResult;
	}
	public void setTransferConfigService(TransferConfigService transferConfigService) {
		this.transferConfigService = transferConfigService;
	}
	public void setSystemOperateService(SystemOperateService systemOperateService) {
		this.systemOperateService = systemOperateService;
	}
	
	public String queryTransferConfig(){
		
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("userinfo");
		
		if(user == null){
			return "relogin";
		}
		
		// 查看防护配置
		TransferConfig transferConfig = transferConfigService.queryTransferConfig();
		
		//封装传输结果
		setTransferResult(transferConfig);
		
		// 记录日志
		SystemOperate systemOperate = new SystemOperate();
		systemOperate.setAccount(user.getAccount());
		systemOperate.setEvent("查看了传输服务配置信息");
		systemOperate.setIp(ServletActionContext.getRequest().getRemoteAddr());
		systemOperate.setName(user.getName());
		systemOperate.setRole(user.getRole());
		systemOperate.setTime(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
		systemOperateService.insertSystemOperateLog(systemOperate);
		
		return SUCCESS;
	}
	
}
