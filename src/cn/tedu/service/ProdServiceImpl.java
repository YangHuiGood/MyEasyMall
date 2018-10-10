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
			//��������
			TransactionManager.startTran();
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
				boolean flag1 = dao.insertPC(pc);
				if(flag1 == false){
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
			 flag = dao.insertProd(prod);
			 //�ύ����
			 TransactionManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//�ع�����
			TransactionManager.rollback();
		}finally{
			//�ر����Ӷ���
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
			//��������
			TransactionManager.startTran();
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
				boolean flag1 = dao.insertPC(pc);
				if(flag1 == false){
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
			 flag = dao.updateProd(prod);
			 //�ύ����
			 TransactionManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//�ع�����
			TransactionManager.rollback();
		}finally{
			//�ر����Ӷ���
			TransactionManager.closeConn();
		}
		return flag;
	}

}
