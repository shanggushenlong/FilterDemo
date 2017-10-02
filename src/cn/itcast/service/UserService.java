package cn.itcast.service;

import java.sql.SQLException;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;

public class UserService {

	public User login(String username, String password) throws SQLException {
		return new UserDao().findUserByUsernameAndPassword(username,password);
	}

}
 