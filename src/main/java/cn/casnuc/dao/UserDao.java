package cn.casnuc.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import cn.casnuc.entity.User;

public class UserDao {
	
	// 注入SessionFactory对象
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
		
	/**
	 * 根据用户名和密码查找用户
 	 * @param account 用户名
	 * @param password 密码
	 * @return 返回查找到的用户
	 */
	public User findByAccountAndPass(String account, String password ) {
		Session sesssion = sessionFactory.getCurrentSession();
		Query query = sesssion.createQuery("from User where account = ? and password = ?");
		query.setParameter(0, account);
		query.setParameter(1, password);
		List<User> list = query.list();
		
		if(list.size() == 0) {
			return null;	//未找到这个用户
		}else{
			return (User)list.get(0);
		}	
	}
	
	/**
	 * 根据用户名查找用户
	 * @param account 用户名
	 * @return 返回找到的用户，没找到返回null
	 */
	public User findUserByAccount(String account){
		Session sesssion = sessionFactory.getCurrentSession();
		Query query = sesssion.createQuery("from User where account = ?"); 
		query.setParameter(0, account);
		List<User> list = query.list();
		
		if(list.size() == 0) {
			return null;	//未找到这个用户
		}else{
			return (User)list.get(0);
		}
	}
	
	/**
	 * 添加用户
	 */
	public String addUser(User user){		
		Session sesssion = sessionFactory.getCurrentSession();
		Query query = sesssion.createQuery("from User where account = ?");
		query.setParameter(0, user.getAccount());
		List<User> list = query.list();
		if(list.size() == 0){
			sesssion.save(user);
			return "添加成功";
		}else{
			return "用户名已经存在";
		}		
	}
	
	/**
	 * 删除指定用户
	 * @param user 要删除的用户
	 * @return true 删除成功  false 没有该用户
	 */
	public void deleteUser(User user){
		
		Session sesssion = sessionFactory.getCurrentSession();
		if(user != null){
			sesssion.delete(user);		
		}
	}
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public String updateUser(User user) {
		
		Session sesssion = sessionFactory.getCurrentSession();
		sesssion.update(user);
		
		return "更新成功";		
	}
	
	/**
	 * 分页查询
	 * @param user 查询的条件对象
	 * @param start 开始查询的位置
	 * @param rows 查询的信息条数
	 * @return 用户结果集合
	 */
	public List<User> queryUser(User user, Integer start, Integer rows){
		
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(User.class);
		if(!user.getName().isEmpty() && !user.getName().equals("全部")){
			c.add(Restrictions.eq("name", user.getName()));
		}
		if(!user.getAccount().isEmpty() && !user.getAccount().equals("全部")){
			c.add(Restrictions.eq("account", user.getAccount()));
		}
		if(user.getRole() != null){
			c.add(Restrictions.eq("role", user.getRole()));
		}
		c.setFirstResult(start);
		c.setMaxResults(rows);
		c.add(Restrictions.ne("account", "sysadmin"));
		c.add(Restrictions.ne("account", "safeadmin"));
		c.add(Restrictions.ne("account", "auditadmin"));
		
		return c.list();
	}
	
	/**
	 * 查询符合条件的记录总数
	 * @param user 条件查询对象
	 * @return 符合条件的记录总数
	 */
	public List<User> queryUser(User user){
		
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(User.class);
		if(user.getName() != null && !user.getName().equals("全部")){
			c.add(Restrictions.eq("name", user.getName()));
		}
		if(user.getAccount() != null && !user.getAccount().equals("全部")){
			c.add(Restrictions.eq("account", user.getAccount()));
		}
		if(user.getRole() != null){
			c.add(Restrictions.eq("role", user.getRole()));
		}

		return c.list();
	}

	/**
	 * 根据用户名判断账户是否被锁定
	 * @param account 用户名
	 * @return true 被锁定， false 没有被锁定
	 */
	public boolean isLock(String account) {
		
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(User.class);
		c.add(Restrictions.eq("account", account));
		User user = (User) c.list().get(0);
		
		return user.getFlag();
	}
	
	/**
	 * 根据用户名返回锁定时间
	 * @param account 用户名
	 * @return true 被锁定， false 没有被锁定
	 */
	public Date lockTime(String account) {
		
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(User.class);
		c.add(Restrictions.eq("account", account));
		User user = (User) c.list().get(0);
		
		return user.getTime();
	}
}