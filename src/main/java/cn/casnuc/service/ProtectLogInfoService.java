package cn.casnuc.service;

import java.util.List;

import cn.casnuc.dao.ProtectLogInfoDao;
import cn.casnuc.entity.DataQuery;
import cn.casnuc.entity.PageProtectLog;
import cn.casnuc.entity.ProtectLogInfo;

public class ProtectLogInfoService {

	private ProtectLogInfoDao protectLogInfoDao;

	public void setProtectLogInfoDao(ProtectLogInfoDao protectLogInfoDao) {
		this.protectLogInfoDao = protectLogInfoDao;
	}

	public List<ProtectLogInfo> findByDate(String start, String end) {
		return protectLogInfoDao.findByDate(start, end);
	}

	public List<ProtectLogInfo> queryProtectLog(DataQuery object) {
		return protectLogInfoDao.queryProtectLog(object);
	}
	
	/**
	 * 业务层 
	 * @param object 查询条件
	 * @param page	查询页码
	 * @param rows	页面行数
	 * @return 封装好的分页结果对象
	 */
	public PageProtectLog queryProtectLog(DataQuery object, Integer page, Integer rows) {
		
		PageProtectLog pageProtectLog = new PageProtectLog();
		
		// 计算起始地址
		Integer start = (page - 1) * rows + 1;
		
		// 封装每页显示的记录数
		pageProtectLog.setPerPageSize(rows);
		
		// 封装分页结果
		List<ProtectLogInfo> list = protectLogInfoDao.queryProtectLog(object, start, rows);
		pageProtectLog.setpLogList(list);
		
		// 封装总记录数
		Integer allRecord = protectLogInfoDao.queryProtectLog(object).size();
		pageProtectLog.setAllRecordNO(allRecord);
		
		// 封装总页数
		Integer allPage = null;
		if(allRecord % pageProtectLog.getPerPageSize() == 0){
			allPage = allRecord / pageProtectLog.getPerPageSize();
		}else{
			allPage = allRecord / pageProtectLog.getPerPageSize() + 1;
		}		
		pageProtectLog.setAllPageNO(allPage);
		
		return pageProtectLog;
	}
};