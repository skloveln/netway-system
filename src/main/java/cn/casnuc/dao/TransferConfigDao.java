package cn.casnuc.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import cn.casnuc.entity.TransferConfig;

public class TransferConfigDao {
	
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 查看防护策略配置
	 * @return
	 */
	public TransferConfig queryTransferConfig(){
		
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(TransferConfig.class);

		return (TransferConfig) c.list().get(0);
	}
	
}	
