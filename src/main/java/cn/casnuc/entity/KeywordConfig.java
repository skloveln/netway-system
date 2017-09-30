package cn.casnuc.entity;

import java.io.Serializable;

public class KeywordConfig implements Serializable {
	
	private int id;
	private String keyword;
	private String rate;
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}	
	
}
