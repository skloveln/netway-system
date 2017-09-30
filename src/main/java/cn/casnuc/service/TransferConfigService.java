package cn.casnuc.service;

import cn.casnuc.dao.TransferConfigDao;
import cn.casnuc.entity.TransferConfig;

public class TransferConfigService {
	
	private TransferConfigDao transferConfigDao;

	public void setTransferConfigDao(TransferConfigDao transferConfigDao) {
		this.transferConfigDao = transferConfigDao;
	}
	
	/**
	 * 查看防护策略配置
	 * @return 
	 */
	public TransferConfig queryTransferConfig(){
		return transferConfigDao.queryTransferConfig();
	}
	
}
