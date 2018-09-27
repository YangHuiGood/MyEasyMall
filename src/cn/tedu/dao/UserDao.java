package cn.tedu.dao;

import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;

public interface UserDao {

	/**
	 * 根据用户名查询用户信息判断是否存在
	 * @param username 用户名
	 * @return true--用户存在 false--用户不存在
	 */
	boolean getUserByUsername(String username);

	/**
	 * 根据传入的用户对象将用户信息插入数据库
	 * @param user 封装用户信息的用户对象
	 * @return true-- 插入成功 false-- 插入失败
	 */
	boolean saveUser(User user);

	/**
	 * 根据用户名和密码查询用户信息，返回封装了用户信息的对象
	 * @param username 用户名
	 * @param password 用户密码
	 * @return 成功返回--user对象 失败返回--null
	 */
	User getUserByUAP(String username, String password) throws MsgException;

}
