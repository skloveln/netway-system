package cn.casnuc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.casnuc.entity.DataQuery;
import cn.casnuc.entity.ProtectLogInfo;

public class ProtectLogInfoDao {
		
	// 注入SessionFactory对象
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
		
	/**
	 * 条件查询,根据时间查询
	 */
	public List<ProtectLogInfo> findByDate(String start, String end){		
		Session sesssion = sessionFactory.getCurrentSession();
		
		Criteria c = sesssion.createCriteria(ProtectLogInfo.class);
		c.add(Restrictions.eq("fileName", "3514.nc"));
		c.add(Restrictions.ge("time", start));
		c.add(Restrictions.le("time", end));
		
		Query query = sesssion.createQuery("from ProtectLogInfo where time between ? and ?");
		query.setParameter(0, start);
		query.setParameter(1, end);
		
		return c.list();
	}
	
	/**
	 * 查询记录总数
	 * @param object 查询条件对象
	 * @return 记录总数
	 */
	@SuppressWarnings("unchecked")
	public List<ProtectLogInfo> queryProtectLog(DataQuery object){
			
		Session sesssion = sessionFactory.getCurrentSession();
		Criteria c = sesssion.createCriteria(ProtectLogInfo.class);
		
		if(!object.getFileName().isEmpty() && !object.getFileName().equals("全部")){
			c.add(Restrictions.eq("fileName", object.getFileName()));
		}	
		if(object.getTransferDrection() == 1 || object.getTransferDrection() == 2){
			c.add(Restrictions.eq("transferDrection", object.getTransferDrection()));
		}
		if(object.getTransferResult() == 0 || object.getTransferResult() == 1){
			c.add(Restrictions.eq("transferResult", object.getTransferResult()));
		}
		c.add(Restrictions.ge("time", object.getStart()));
		c.add(Restrictions.le("time", object.getEnd()));
		
		return c.list();
	}
	
	/**
	 * 分页查询
	 * @param object  查询条件对象
	 * @param start   从第几条开始查询，（从0开始算）
	 * @param rows	    查询多少条记录	
	 * @return
	 */
	public List<ProtectLogInfo> queryProtectLog(DataQuery object, int start, int rows){
		
		Session sesssion = sessionFactory.getCurrentSession();
		Criteria c = sesssion.createCriteria(ProtectLogInfo.class);
		
		if(!object.getFileName().isEmpty() && !object.getFileName().equals("全部")){
			c.add(Restrictions.eq("fileName", object.getFileName()));
		}	
		if(object.getTransferDrection() == 1 || object.getTransferDrection() == 2){
			c.add(Restrictions.eq("transferDrection", object.getTransferDrection()));
		}
		if(object.getTransferResult() == 0 || object.getTransferResult() == 1){
			c.add(Restrictions.eq("transferResult", object.getTransferResult()));
		}
		c.add(Restrictions.ge("time", object.getStart()));
		c.add(Restrictions.le("time", object.getEnd()));
		c.addOrder(Order.asc("time"));
		
		c.setFirstResult(start - 1);
		c.setMaxResults(rows);
		
		return c.list();
	}
}
