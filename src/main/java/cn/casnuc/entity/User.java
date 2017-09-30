package cn.casnuc.entity;

import java.util.Date;

public class User {

	private int id; // 用户编号
	private String account; // 用户账号
	private String name; // 用户姓名
	private String password; // 用户密码
	private String role; // 用户角色
	private Permission permission; // 用户权限
	private Date time; // 用户账户锁定时间
	private Integer count; // 用户输入密码错误次数
	private boolean flag; // 用户账户锁定标志位
	private Date lastErrorTime; //最后一次输错密码的时间
	private String sessionId;  //用户登录的唯一标识
	
	public User() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Date getLastErrorTime() {
		return lastErrorTime;
	}

	public void setLastErrorTime(Date lastErrorTime) {
		this.lastErrorTime = lastErrorTime;
	}
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String toString() {
		return account + " - " + name + " - " + role;
	}

}
