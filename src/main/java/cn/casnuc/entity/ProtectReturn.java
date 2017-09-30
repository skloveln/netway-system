package cn.casnuc.entity;

import java.util.List;

public class ProtectReturn {
	
	private ProtectConfig protectConfig;
	private List<KeywordConfig> list;
	
	public ProtectConfig getProtectConfig() {
		return protectConfig;
	}
	public void setProtectConfig(ProtectConfig protectConfig) {
		this.protectConfig = protectConfig;
	}
	public List<KeywordConfig> getList() {
		return list;
	}
	public void setList(List<KeywordConfig> list) {
		this.list = list;
	}
	
	
}
