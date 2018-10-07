package cn.tedu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.tedu.domain.Prod;
import cn.tedu.domain.ProdCategory;
import cn.tedu.exception.MsgException;
import cn.tedu.util.JDBCUtils;

public class ProdDaoImpl implements ProdDao {

	@Override
	public int getCidByCname(String cname) throws MsgException {
		String sql="select id from prod_category where cname=?";
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, cname);
			rs = ps.executeQuery();
			if(rs.next()){
				//说明存在该商品种类记录
				return rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MsgException("查询商品种类时出现异常");
		}finally{
			JDBCUtils.close(conn, ps, rs);
		}
		return -1;
	}

	@Override
	public boolean insertPC(ProdCategory pc) {
		String sql="insert into prod_category values(null,?)";
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=JDBCUtils.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, pc.getCname());
			int i=ps.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(conn, ps, null);
		}
		return false;
	}

	@Override
	public boolean insertProd(Prod prod) {
		String sql="insert into prod values(null,?,?,?,?,?,?)";
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=JDBCUtils.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, prod.getName());
			ps.setDouble(2, prod.getPrice());
			ps.setInt(3, prod.getCid());
			ps.setInt(4, prod.getPnum());
			ps.setString(5, prod.getImgurl());
			ps.setString(6, prod.getDescription());
			int i=ps.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(conn, ps, null);
		}
		return false;
	}

}
