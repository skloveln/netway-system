package cn.casnuc.entity;

public class AttackLogInfo {
	
	private int id;
	private String time;
	private String sourceMac;
	private int attackTarget;
	private int attackType;
	private String targetIp;
	private int targetPort;
	private String remarks;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSourceMac() {
		return sourceMac;
	}
	public void setSourceMac(String sourceMac) {
		this.sourceMac = sourceMac;
	}
	public int getAttackTarget() {
		return attackTarget;
	}
	public void setAttackTarget(int attackTarget) {
		this.attackTarget = attackTarget;
	}
	public int getAttackType() {
		return attackType;
	}
	public void setAttackType(int attackType) {
		this.attackType = attackType;
	}
	public String getTargetIp() {
		return targetIp;
	}
	public void setTargetIp(String targetIp) {
		this.targetIp = targetIp;
	}
	public int getTargetPort() {
		return targetPort;
	}
	public void setTargetPort(int targetPort) {
		this.targetPort = targetPort;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
