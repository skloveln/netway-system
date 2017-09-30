package cn.casnuc.service;

import java.util.List;

import cn.casnuc.dao.AttackLogInfoDao;
import cn.casnuc.entity.AttackLogInfo;
import cn.casnuc.entity.AttackQuery;
import cn.casnuc.entity.PageAttackLog;

public class AttackLogInfoService {

	private AttackLogInfoDao attackLogInfoDao;

	public void setAttackLogInfoDao(AttackLogInfoDao attackLogInfoDao) {
		this.attackLogInfoDao = attackLogInfoDao;
	}
	
	/**
	 * 分页查询
	 * @param query 条件查询对象
	 * @param page  查询的页数
	 * @param rows	每页显示的记录数
	 * @return	查询结果对象
	 */
	public PageAttackLog queryAttackLog(AttackQuery query, Integer page, Integer rows){
		
		PageAttackLog pageAttackLog = new PageAttackLog();
		
		// 计算查询的起始位置
		Integer start = (page - 1) * rows;
		
		// 调用DAO层查询
		Integer allSize = attackLogInfoDao.queryAttackLog(query);
		List<AttackLogInfo> pageList = attackLogInfoDao.queryAttackLog(query, start, rows);
		
		// 封装总记录数
		pageAttackLog.setAllRecord(allSize);

		// 封装每页显示的记录数
		pageAttackLog.setPerPageSize(rows);
		
		// 封装总页数
		Integer allPage = null;
		if(allSize % rows == 0){
			allPage = allSize / rows;
		}else{
			allPage = allSize / rows + 1;
		}
		pageAttackLog.setAllPageSize(allPage);
		
		// 封装查询结果
		pageAttackLog.setList(pageList);
		
		return pageAttackLog;
	}
};