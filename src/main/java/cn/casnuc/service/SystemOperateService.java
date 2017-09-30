package cn.casnuc.service;

import java.util.List;

import cn.casnuc.dao.SystemOperateDao;
import cn.casnuc.entity.PageInfo;
import cn.casnuc.entity.SystemOperate;

public class SystemOperateService {
	
	private SystemOperateDao systemOperateDao;

	public void setSystemOperateDao(SystemOperateDao systemOperateDao) {
		this.systemOperateDao = systemOperateDao;
	}
	
	/**
	 * 插入操作日志数据
	 * @param systemOperate 需要增加的数据
	 */
	public void insertSystemOperateLog(SystemOperate systemOperate){
		
		systemOperateDao.insertOperateLog(systemOperate);
		
	}
	
	/**
	 * 删除操作日志数据
	 * @param systemOperate 需要删除的目标对象
	 */
	public void deleteSystemOperateLog(SystemOperate systemOperate){
		
		systemOperateDao.deleteOperateLog(systemOperate);
	}
	
	/**
	 * 分页查询操作日志
	 * @param operate 要查询的信息
	 * @param page	第几页
	 * @param rows	每页的行数
	 */
	public PageInfo queryOperateLog(SystemOperate operate, String startTime, String endTime, Integer page, Integer rows) {
		
		PageInfo pageInfo = new PageInfo();
		
		// 计算查询的起始位置
		Integer start = (page - 1) * rows;
		
		// 调用Dao层查询
		List list = systemOperateDao.queryOperateLog(operate, start, rows, startTime, endTime);
		Integer allSize = systemOperateDao.queryOperateAll(operate, startTime, endTime);
		
		// 计算总页数
		Integer allPage = null;
		if(allSize % rows == 0){
			allPage = allSize / rows;
		}else{
			allPage = allSize / rows + 1;
		}
		
		// 封装结果
		pageInfo.setAllPage(allPage);
		pageInfo.setAllSize(allSize);
		pageInfo.setCurrentPage(page);
		pageInfo.setList(list);
		pageInfo.setPerPageSize(rows);
		
		return pageInfo;
	}
}
