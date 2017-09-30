package cn.casnuc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.casnuc.entity.AttackQuery;
import cn.casnuc.entity.PageAttackLog;
import cn.casnuc.entity.SystemOperate;
import cn.casnuc.entity.User;
import cn.casnuc.service.AttackLogInfoService;
import cn.casnuc.service.SystemOperateService;
import cn.casnuc.service.UserService;

/**
 * 查看单导设备的攻击日志信息
 * @author kaka
 *
 */
public class AttackLogInfoAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	private AttackLogInfoService attackLogInfoService;
	private AttackQuery attackQuery;
	private JSONObject jsonResult;
	private SystemOperateService systemOperateService;
	private HttpServletRequest request = ServletActionContext.getRequest();
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setSystemOperateService(SystemOperateService systemOperateService) {
		this.systemOperateService = systemOperateService;
	}
	public JSONObject getJsonResult() {
		return jsonResult;
	}
	public void setJsonResult(JSONObject jsonResult) {
		this.jsonResult = jsonResult;
	}
	public void setAttackLogInfoService(AttackLogInfoService attackLogInfoService) {
		this.attackLogInfoService = attackLogInfoService;
	}
	public AttackQuery getAttackQuery() {
		return attackQuery;
	}
	public void setAttackQuery(AttackQuery attackQuery) {
		this.attackQuery = attackQuery;
	}
	
	public String queryResult(){
		
		// 接收提交的数据
		HttpServletRequest request = ServletActionContext.getRequest();
		String pageStr = request.getParameter("page");
		String rowStr = request.getParameter("rows");
		String attackTarget = request.getParameter("attackTarget");
		String attackType = request.getParameter("attackType");
		String sourceMac = request.getParameter("sourceMac");
		String targetIp = request.getParameter("destinationIp");
		String targetPort = request.getParameter("destinationPort");
		String start = request.getParameter("startTime");
		String end = request.getParameter("endTime");
		
		// 转换数据类型
		if(pageStr == null || pageStr.trim().length() == 0){
			pageStr = "1";
		}
		Integer page = Integer.parseInt(pageStr);
		Integer rows = Integer.parseInt(rowStr);
		
		// 封装条件查询对象
		AttackQuery myAttackQuery = new AttackQuery();
		myAttackQuery.setAttackTarget(attackTarget);
		myAttackQuery.setAttackType(attackType);
		myAttackQuery.setSourceMac(sourceMac);
		myAttackQuery.setEndDate(end);
		myAttackQuery.setStartDate(start);
		myAttackQuery.setTargetIp(targetIp);
		myAttackQuery.setTargetPort(targetPort);
		
		// 提交到业务层查询
		PageAttackLog pageResult = attackLogInfoService.queryAttackLog(myAttackQuery, page, rows);
		
		// 封装成Json对象
		Map<String, Object> map = new LinkedHashMap<String,Object>();
		map.put("total", pageResult.getAllRecord());
		map.put("rows", pageResult.getList());		
		JSONObject jsonObject = JSONObject.fromObject(map);
		JSONObject json = new JSONObject();
		json.element("status", true);
		json.element("result", jsonObject);
		setJsonResult(json);
		
		return SUCCESS;		
	}
	
	@Override
	public String execute() throws Exception {
		
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");	
		
		if(user == null){
			
			// 用户登录信息失效
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("status", false);
			JSONObject resultJson = JSONObject.fromObject(map);
			setJsonResult(resultJson);

			return SUCCESS;
			
		}
			
		return queryResult();	
	}
}
