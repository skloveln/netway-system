package cn.casnuc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.casnuc.entity.AttackLogInfo;
import cn.casnuc.entity.AttackQuery;

public class AttackLogInfoDao {
	
	// 注入SessionFactory对象
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 查询记录总数
	 * @param attackQuery 条件查询对象
	 * @return 全部结果的集合
	 */
	@SuppressWarnings("unchecked")
	public Integer queryAttackLog(AttackQuery attackQuery){
		
		Session sesssion = sessionFactory.getCurrentSession();
		Criteria c = sesssion.createCriteria(AttackLogInfo.class);
		
		if(attackQuery.getAttackTarget() == 1 || attackQuery.getAttackTarget() == 2){
			c.add(Restrictions.eq("attackTarget", attackQuery.getAttackTarget()));
		}
		if(attackQuery.getAttackType() != 999){
			c.add(Restrictions.eq("attackType", attackQuery.getAttackType()));
		}
		if(!attackQuery.getSourceMac().equals("全部")){
			c.add(Restrictions.eq("sourceMac", attackQuery.getSourceMac()));
		}
		if(!attackQuery.getTargetIp().equals("全部")){
			c.add(Restrictions.eq("targetIp", attackQuery.getTargetIp()));
		}
		if(attackQuery.getTargetPort() != -1){
			c.add(Restrictions.eq("targetPort", attackQuery.getTargetPort()));
		}
		c.add(Restrictions.ge("time", attackQuery.getStartDate()));
		c.add(Restrictions.le("time", attackQuery.getEndDate()));
		
		ScrollableResults scroll = c.scroll();
		scroll.last();
		
		return scroll.getRowNumber()+1;
	}
	
	/**
	 * 分页查询	
	 * @param attackQuery 条件查询的对象
 	 * @param start 查询的起始记录
	 * @param rows  查询的记录条数
	 * @return  该页的攻击日志对象集合
	 */
	@SuppressWarnings("unchecked")
	public List<AttackLogInfo> queryAttackLog(AttackQuery attackQuery, Integer start, Integer rows){
		
		Session sesssion = sessionFactory.getCurrentSession();
		Criteria c = sesssion.createCriteria(AttackLogInfo.class);
		
		if(attackQuery.getAttackTarget() == 1 || attackQuery.getAttackTarget() == 2){
			c.add(Restrictions.eq("attackTarget", attackQuery.getAttackTarget()));
		}
		if(attackQuery.getAttackType() != 999){
			c.add(Restrictions.eq("attackType", attackQuery.getAttackType()));
		}
		if(!attackQuery.getSourceMac().equals("全部")){
			c.add(Restrictions.eq("sourceMac", attackQuery.getSourceMac()));
		}
		if(!attackQuery.getTargetIp().equals("全部")){
			c.add(Restrictions.eq("targetIp", attackQuery.getTargetIp()));
		}
		if(attackQuery.getTargetPort() != -1){
			c.add(Restrictions.eq("targetPort", attackQuery.getTargetPort()));
		}
		c.add(Restrictions.ge("time", attackQuery.getStartDate()));
		c.add(Restrictions.le("time", attackQuery.getEndDate()));
		c.addOrder(Order.asc("time"));
		
		c.setFirstResult(start);
		c.setMaxResults(rows);
		
		return c.list();
	}
	
}
