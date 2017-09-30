package cn.casnuc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import cn.casnuc.entity.SystemOperate;

public class SystemOperateDao {
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 插入操作日志
	 * @param systemOperate  需要插入的日志数据
	 */
	public void insertOperateLog(SystemOperate systemOperate){
		
		Session sesssion = sessionFactory.getCurrentSession();
		sesssion.save(systemOperate);
		
	}
	
	/**
	 * 删除操作日志
	 * @param systemOperate 需要删除的日志数据
	 */
	public void deleteOperateLog(SystemOperate systemOperate){
		
		Session sesssion = sessionFactory.getCurrentSession();
		sesssion.delete(systemOperate);
	
	}
	
	/**
	 *  分页查询
	 * @param operate 要查询的信息
	 * @param start 查询的起始位置
	 * @param rows 每页显示的行数
	 * @return 返回查询到的结果信息
	 */
	@SuppressWarnings("unchecked")
	public List<SystemOperate> queryOperateLog(SystemOperate operate, Integer start, Integer rows, String startTime, String endTime) {
		
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(SystemOperate.class);
		
		if(!operate.getAccount().isEmpty() && !operate.getAccount().equals("全部") ){
			c.add(Restrictions.eq("account", operate.getAccount()));
		}
		if(!operate.getName().isEmpty() && !operate.getName().equals("全部")){
			c.add(Restrictions.eq("name", operate.getName()));
		}
		if(operate.getRole().equals("审计管理员")){
			c.add(Restrictions.ne("role", "系统管理员"));
			c.add(Restrictions.ne("role", "用户"));
			c.add(Restrictions.ne("role", "安全管理员"));
		} else if(operate.getRole().equals("全部")){
			c.add(Restrictions.or(
					Restrictions.eq("role", "安全管理员"),
					Restrictions.eq("role", "系统管理员")
					));
		} else {
			c.add(Restrictions.eq("role", operate.getRole()));
		}
	
		c.add(Restrictions.ge("time", startTime));
		c.add(Restrictions.le("time", endTime));
		
		c.setFirstResult(start);
		c.setMaxResults(rows);
		
		return c.list();
	}
	
	/**
	 * 查询总数
	 * @param operate 要查询的信息
	 * @return 返回查询到的结果信息
	 */
	public Integer queryOperateAll(SystemOperate operate, String startTime, String endTime) {
		
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(SystemOperate.class);
		if(!operate.getAccount().isEmpty() && !operate.getAccount().equals("全部") ){
			c.add(Restrictions.eq("account", operate.getAccount()));
		}
		if(!operate.getName().isEmpty() && !operate.getName().equals("全部")){
			c.add(Restrictions.eq("name", operate.getName()));
		}
		
		if(operate.getRole().equals("审计管理员")){
			c.add(Restrictions.ne("role", "系统管理员"));
			c.add(Restrictions.ne("role", "用户"));
			c.add(Restrictions.ne("role", "安全管理员"));
		} else if(operate.getRole().equals("全部")){
			c.add(Restrictions.or(
					Restrictions.eq("role", "安全管理员"),
					Restrictions.eq("role", "系统管理员")
					));
		}else{
			c.add(Restrictions.eq("role", operate.getRole()));
		}
		
		c.add(Restrictions.ge("time", startTime));
		c.add(Restrictions.le("time", endTime));
		
		return c.list().size();
	}
	
	
}
