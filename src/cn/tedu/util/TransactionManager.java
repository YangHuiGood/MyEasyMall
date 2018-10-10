package cn.tedu.util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 用于事务管理的类
 * @author yanghui
 *
 */
public class TransactionManager {
	//用于操作每个线程（用户）的map集合管理类对象
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	/**
	 * 开启事务
	 */
	public static void startTran(){
		//为当前线程创建连接
		Connection conn = JDBCUtils.getConnection();
		//将连接添加到当前线程的map集合中
		tl.set(conn);
		try {
			//开启事务
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 提交事务
	 */
	public static void commit(){
		try {
			tl.get().commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 回滚事务
	 */
	public static void rollback(){
		try {
			tl.get().rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 得到连接对象
	 * @return 返回连接对象
	 */
	public static Connection getConn() {
		return tl.get();
	}
	
	public static void closeConn(){
		Connection conn = tl.get();
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				conn  = null;
			}
		}
		//从当前线程集合中删除当前连接对象
		tl.remove();
	}
}
