package cn.itcast.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.domain.User;
import cn.itcast.utils.DataSourceUtils;

public class UserDao {

	public User findUserByUsernameAndPassword(String username, String password) throws SQLException {
		
		String sql = "select * from user where username = ? and password = ?";
		
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class),username,password);
	}

}
