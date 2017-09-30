package cn.casnuc.entity;

import java.util.List;

public class PageAttackLog {
	
	private Integer allPageSize;	// 总页数
	private Integer allRecord;		// 总记录数
	private Integer perPageSize;	// 每页显示的记录数
	private Integer currentPage; 	// 当前页数
	private List<AttackLogInfo> list; // 当前页的结果集合
	
	public Integer getAllPageSize() {
		return allPageSize;
	}
	public void setAllPageSize(Integer allPageSize) {
		this.allPageSize = allPageSize;
	}
	public Integer getAllRecord() {
		return allRecord;
	}
	public void setAllRecord(Integer allRecord) {
		this.allRecord = allRecord;
	}
	public Integer getPerPageSize() {
		return perPageSize;
	}
	public void setPerPageSize(Integer perPageSize) {
		this.perPageSize = perPageSize;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public List<AttackLogInfo> getList() {
		return list;
	}
	public void setList(List<AttackLogInfo> list) {
		this.list = list;
	}
	
	
}
