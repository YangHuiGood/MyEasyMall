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
		// ��ѯ���ݿ⣬ȷ���Ƿ��Ѿ����ڸ���Ʒ����
		int cid = -1;
		try {
			cid = dao.getCidByCname(prod.getCname());
		} catch (MsgException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		// ���û�������Ʒ����
		if(cid == -1){
			// ����ProdCategory���󣬷�װҪ��ӵ�����
			ProdCategory  pc = new ProdCategory();
			pc.setCname(prod.getCname());
			// 1) ����������Ʒ����
			boolean flag = dao.insertPC(pc);
			if(flag == false){
				return false;
			}
			// 2) �ٴβ�ѯcid����ȡ���ݿ����ɵ����µ�cid
			try {
				cid = dao.getCidByCname(prod.getCname());
			} catch (MsgException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}	
		}
		// ����ѯ����cid��ӵ�prod������
		prod.setCid(cid);
		// �����Ʒ
		boolean flag = dao.insertProd(prod);
		return flag;
	}

}
