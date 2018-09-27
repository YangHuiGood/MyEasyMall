package cn.tedu.service;

import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;

public interface UserService {

	/**
	 * 判断用户是否存在的方法
	 * @param username 用户的用户名
	 * @return true- 用户存在 false-用户不存在
	 */
	boolean hasUser(String username);

	/**
	 * 根据用户的对象将用户信息存入数据库中
	 * @param user 封装了用户信息的用户对象
	 * @return true-插入操作成功 false-插入操作失败
	 */
	boolean registUser(User user);

	/**
	 * 根据用户名和密码实现用户的登录方法
	 * @param username 用户名
	 * @param password 用户密码
	 * @return 登录成功--返回封装了用户信息的user对象 登录失败-- 返回null
	 */
	User login(String username, String password) throws MsgException;

}
