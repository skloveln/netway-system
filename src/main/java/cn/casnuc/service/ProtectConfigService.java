package cn.casnuc.service;

import cn.casnuc.dao.ProtectConfigDao;
import cn.casnuc.entity.ProtectReturn;

public class ProtectConfigService {

	private ProtectConfigDao protectConfigDao;

	public void setProtectConfigDao(ProtectConfigDao protectConfigDao) {
		this.protectConfigDao = protectConfigDao;
	}
	
	/**
	 * 查看防护策略配置
	 * @return
	 */
	public ProtectReturn queryProtectConfig(){
		return protectConfigDao.queryProtectConfig();
	}
	
}
