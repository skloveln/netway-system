package cn.casnuc.entity;

import java.io.Serializable;

public class TransferConfig implements Serializable{
	
	private Integer inNetIp;
	private Long inNetMac;
	private Integer inNetPort;
	private Integer outNetIp; 
	private Long outNetMac;
	private Integer outNetPort;
	private Integer serviceType;
	private Integer transferDirection;
	private Integer transferCount;
	private String remarks;
	
	
	public Integer getInNetIp() {
		return inNetIp;
	}
	public void setInNetIp(Integer inNetIp) {
		this.inNetIp = inNetIp;
	}
	public Long getInNetMac() {
		return inNetMac;
	}
	public void setInNetMac(Long inNetMac) {
		this.inNetMac = inNetMac;
	}
	public Integer getInNetPort() {
		return inNetPort;
	}
	public void setInNetPort(Integer inNetPort) {
		this.inNetPort = inNetPort;
	}
	public Integer getOutNetIp() {
		return outNetIp;
	}
	public void setOutNetIp(Integer outNetIp) {
		this.outNetIp = outNetIp;
	}
	public Long getOutNetMac() {
		return outNetMac;
	}
	public void setOutNetMac(Long outNetMac) {
		this.outNetMac = outNetMac;
	}
	public Integer getOutNetPort() {
		return outNetPort;
	}
	public void setOutNetPort(Integer outNetPort) {
		this.outNetPort = outNetPort;
	}
	public Integer getServiceType() {
		return serviceType;
	}
	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}
	public Integer getTransferDirection() {
		return transferDirection;
	}
	public void setTransferDirection(Integer transferDirection) {
		this.transferDirection = transferDirection;
	}
	public Integer getTransferCount() {
		return transferCount;
	}
	public void setTransferCount(Integer transferCount) {
		this.transferCount = transferCount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
