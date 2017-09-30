package cn.casnuc.service;

import java.util.Date;
import java.util.List;

import cn.casnuc.dao.UserDao;
import cn.casnuc.entity.PageInfo;
import cn.casnuc.entity.User;

public class UserService {
	
	// IOC注入
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 登录查询
	 * @param emp
	 */
	public User findByAccountAndPass(String account, String password) {
		User user = userDao.findByAccountAndPass(account, password);
		return user;
	}
	
	/**
	 * 增加用户
	 */
	public String addUser(User user){
		return userDao.addUser(user);
	}
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public String updateUser(User user) {
		
		return userDao.updateUser(user);
	}

	/**
	 * 条件查询
	 * @param user	查询条件对象
	 */
	public List<User> queryUser(User user) {
		return userDao.queryUser(user);
	}
	
	/**
	 * 分页查询用户
	 * @param user	查询条件对象
	 * @return	当前页结果对象
	 */
	public PageInfo queryUser(User user, Integer page, Integer rows) {
		
		PageInfo pageInfo = new PageInfo();
		
		// 计算查询的起始节点
		Integer start = (page - 1) * rows;
				
		// 查询结果
		List<?> list = userDao.queryUser(user, start, rows);
		Integer allSize = userDao.queryUser(user).size();
		
		// 计算总页数
		Integer allPage = null;
		if(allSize % rows == 0){
			allPage = allSize / rows;
		}else{
			allPage = allSize / rows + 1;
		}
				
		// 封装查询结果
		pageInfo.setPerPageSize(rows);
		pageInfo.setAllPage(allPage);
		pageInfo.setAllSize(allSize);
		pageInfo.setCurrentPage(page);
		pageInfo.setList(list);
		
		return pageInfo;
	}
	
	/**
	 * 删除指定用户
	 * @param removeUser 需要删除的用户
	 * @return true 删除成功， false 无此用户
	 */
	public User deleteUser(User user) {
		// 查找用户
		user = userDao.findUserByAccount(user.getAccount());
		// 找到用户则删除
		if(user != null){
			userDao.deleteUser(user);
		}
		
		return user;
	}
	
	/**
	 * 根据用户名查找出指定用户
	 * @param account 用户名
	 * @return 需要查找的用户对象
	 */
	public User findByAccount(String account) {
		return userDao.findUserByAccount(account);
	}
	
	/**
	 * 根据用户名判断账户是否被锁定
	 * @param account 用户名
	 * @return true 被锁定， false 没有被锁定
	 */
	public boolean isLock(String account){
		return userDao.isLock(account);
	}
	
	/**
	 * 根据用户名返回锁定时间
	 * @param account 用户名
	 * @return true 被锁定， false 没有被锁定
	 */
	public Date lockTime(String account) {
		return userDao.lockTime(account);
	}
}
