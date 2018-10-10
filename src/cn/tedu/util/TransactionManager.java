package cn.tedu.util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * ��������������
 * @author yanghui
 *
 */
public class TransactionManager {
	//���ڲ���ÿ���̣߳��û�����map���Ϲ��������
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	/**
	 * ��������
	 */
	public static void startTran(){
		//Ϊ��ǰ�̴߳�������
		Connection conn = JDBCUtils.getConnection();
		//��������ӵ���ǰ�̵߳�map������
		tl.set(conn);
		try {
			//��������
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * �ύ����
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
	 * �ع�����
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
	 * �õ����Ӷ���
	 * @return �������Ӷ���
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
		//�ӵ�ǰ�̼߳�����ɾ����ǰ���Ӷ���
		tl.remove();
	}
}
