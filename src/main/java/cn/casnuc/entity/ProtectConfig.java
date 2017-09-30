package cn.casnuc.entity;

import java.io.Serializable;

public class ProtectConfig implements Serializable {
	
	private Integer nccheck;
	private String siemens;
	private String fanuc;
	private String heidenhain;
	private String other;
	private Integer mdccheck;
	private String remarks;
	
	public Integer getNccheck() {
		return nccheck;
	}
	public void setNccheck(Integer nccheck) {
		this.nccheck = nccheck;
	}
	public String getSiemens() {
		return siemens;
	}
	public void setSiemens(String siemens) {
		this.siemens = siemens;
	}
	public String getFanuc() {
		return fanuc;
	}
	public void setFanuc(String fanuc) {
		this.fanuc = fanuc;
	}
	public String getHeidenhain() {
		return heidenhain;
	}
	public void setHeidenhain(String heidenhain) {
		this.heidenhain = heidenhain;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public Integer getMdccheck() {
		return mdccheck;
	}
	public void setMdccheck(Integer mdccheck) {
		this.mdccheck = mdccheck;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
