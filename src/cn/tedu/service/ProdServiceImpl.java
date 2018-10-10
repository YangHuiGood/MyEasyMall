package cn.tedu.service;

import java.util.List;

import cn.tedu.dao.ProdDao;
import cn.tedu.domain.Prod;
import cn.tedu.domain.ProdCategory;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BaseFactory;
import cn.tedu.util.TransactionManager;

public class ProdServiceImpl implements ProdService {
	
	private ProdDao dao=BaseFactory.getFactory().getInstance(ProdDao.class);
	@Override
	public boolean addProd(Prod prod) {
		boolean flag = false;
		try {
			//开启事务
			TransactionManager.startTran();
			// 查询数据库，确认是否已经存在该商品种类
			int cid = -1;
			try {
				cid = dao.getCidByCname(prod.getCname());
			} catch (MsgException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			// 如果没有这个商品种类
			if(cid == -1){
				// 创建ProdCategory对象，封装要添加的数据
				ProdCategory  pc = new ProdCategory();
				pc.setCname(prod.getCname());
				// 1) 先添加这个商品种类
				boolean flag1 = dao.insertPC(pc);
				if(flag1 == false){
					return false;
				}
				// 2) 再次查询cid，获取数据库生成的最新的cid
				try {
					cid = dao.getCidByCname(prod.getCname());
				} catch (MsgException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}	
			}
			// 将查询到的cid添加到prod对象中
			prod.setCid(cid);
			// 添加商品
			 flag = dao.insertProd(prod);
			 //提交事务
			 TransactionManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//回滚事务
			TransactionManager.rollback();
		}finally{
			//关闭连接对象
			TransactionManager.closeConn();
		}
		return flag;
	}
	@Override
	public List getAllProd() {
		// TODO Auto-generated method stub
		return dao.getAllProd();
	}
	@Override
	public boolean updateProd(Prod prod) {
		boolean flag = false;
		try {
			//开启事务
			TransactionManager.startTran();
			// 查询数据库，确认是否已经存在该商品种类
			int cid = -1;
			try {
				cid = dao.getCidByCname(prod.getCname());
			} catch (MsgException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			// 如果没有这个商品种类
			if(cid == -1){
				// 创建ProdCategory对象，封装要添加的数据
				ProdCategory  pc = new ProdCategory();
				pc.setCname(prod.getCname());
				// 1) 先添加这个商品种类
				boolean flag1 = dao.insertPC(pc);
				if(flag1 == false){
					return false;
				}
				// 2) 再次查询cid，获取数据库生成的最新的cid
				try {
					cid = dao.getCidByCname(prod.getCname());
				} catch (MsgException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}	
			}
			// 将查询到的cid添加到prod对象中
			prod.setCid(cid);
			// 添加商品
			 flag = dao.updateProd(prod);
			 //提交事务
			 TransactionManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//回滚事务
			TransactionManager.rollback();
		}finally{
			//关闭连接对象
			TransactionManager.closeConn();
		}
		return flag;
	}

}
