package cn.tedu.service;

import cn.tedu.dao.ProdDao;
import cn.tedu.domain.Prod;
import cn.tedu.domain.ProdCategory;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BaseFactory;

public class ProdServiceImpl implements ProdService {
	
	private ProdDao dao=BaseFactory.getFactory().getInstance(ProdDao.class);
	@Override
	public boolean addProd(Prod prod) {
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
			boolean flag = dao.insertPC(pc);
			if(flag == false){
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
		boolean flag = dao.insertProd(prod);
		return flag;
	}

}
