package cn.tedu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;
import cn.tedu.util.JDBCUtils;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean getUserByUsername(String username) {
		String sql = "select * from user where username=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(conn, ps, rs);
		}
		return false;
	}

	@Override
	public boolean saveUser(User user) {
		String sql = "insert into user values(null,?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNickname());
			ps.setString(4, user.getEmail());
			int count = ps.executeUpdate();
			if(count > -1){
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("服务器出现异常，请稍后重试..."+e.getMessage());
		}finally{
			JDBCUtils.close(conn, ps, null);
		}
		return false;
	}

	@Override
	public User getUserByUAP(String username, String password) throws MsgException{
		String sql = "select * from user where username = ? and password =?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setNickname(rs.getString("nickname"));
				user.setEmail(rs.getString("email"));
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MsgException("登录时服务器出现异常，请稍后重试！");
		}finally{
			JDBCUtils.close(conn, ps, rs);
		}
		return user;
	}

}
