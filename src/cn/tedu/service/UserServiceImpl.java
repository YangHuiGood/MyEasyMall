package cn.tedu.service;

import cn.tedu.dao.UserDao;
import cn.tedu.dao.UserDaoImpl;
import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BaseFactory;

public class UserServiceImpl implements UserService {
	private UserDao userDao = BaseFactory.getFactory().getInstance(UserDao.class);

	@Override
	public boolean hasUser(String username) {
		boolean flag = userDao.getUserByUsername(username);
		return flag;
	}

	@Override
	public boolean registUser(User user) {
		boolean flag = userDao.saveUser(user);
		return flag;
	}

	@Override
	public User login(String username, String password) throws MsgException{
		User user = userDao.getUserByUAP(username,password);
		return user;
	}

}
