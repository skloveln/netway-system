package cn.casnuc.entity;

public class ProtectResult {
	
	private String nccheck;
	private String siemens;
	private String fanuc;
	private String heidenhain;
	private String other;
	private String mdccheck;
	private String remarks;
	
	public String getNccheck() {
		return nccheck;
	}
	public void setNccheck(String nccheck) {
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
	public String getMdccheck() {
		return mdccheck;
	}
	public void setMdccheck(String mdccheck) {
		this.mdccheck = mdccheck;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public void dataConver(ProtectReturn protectReturn){
		
		this.fanuc = protectReturn.getProtectConfig().getFanuc();
		this.heidenhain = protectReturn.getProtectConfig().getHeidenhain();
		this.other = protectReturn.getProtectConfig().getOther();
		this.remarks = protectReturn.getProtectConfig().getRemarks();
		this.siemens = protectReturn.getProtectConfig().getSiemens();
		
		if(protectReturn.getProtectConfig().getMdccheck() == 0){
			this.mdccheck = "checked";
		}else{
			this.mdccheck = "";
		}
		
		if(protectReturn.getProtectConfig().getNccheck() == 1){
			this.nccheck = "×è¶Ï";
		}else{
			this.nccheck = "·Ç×è¶Ï";
		}
		
	}
	
}
