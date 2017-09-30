package cn.casnuc.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import cn.casnuc.entity.KeywordConfig;
import cn.casnuc.entity.ProtectConfig;
import cn.casnuc.entity.ProtectReturn;

public class ProtectConfigDao {
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * ≤È—Ø∑¿ª§≤ﬂ¬‘≈‰÷√
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ProtectReturn queryProtectConfig(){
		
		ProtectReturn pr = new ProtectReturn(); 
		
		Session session = sessionFactory.getCurrentSession();
		Criteria c1 = session.createCriteria(ProtectConfig.class);
		Criteria c2 = session.createCriteria(KeywordConfig.class);
		pr.setProtectConfig((ProtectConfig)c1.list().get(0));
		pr.setList(c2.list());
		
		
		
		return pr;
	} 
	
}
