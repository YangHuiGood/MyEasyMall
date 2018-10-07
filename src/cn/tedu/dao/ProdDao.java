package cn.tedu.dao;

import cn.tedu.domain.Prod;
import cn.tedu.domain.ProdCategory;
import cn.tedu.exception.MsgException;

public interface ProdDao {
	/**
	 * ������Ʒ�������Ʋ�ѯ��Ʒ����id�ķ���
	 * @param cname ��Ʒ��������
	 * @return ���ڷ��� ��Ʒid  �����ڷ���-1
	 * @throws MsgException
	 */
	int getCidByCname(String cname) throws MsgException;
	
	/**
	 * �����Ʒ����ķ���
	 * @param pc ��װ����Ʒ������Ϣ�Ķ���
	 * @return ��ӳɹ�����true �����ʧ�ܷ���false
	 */
	boolean insertPC(ProdCategory pc);
	
	/**
	 * �����Ʒ��Ϣ�ķ���
	 * @param prod ��װ����Ʒ��Ϣ�Ķ���
	 * @return �ɹ����� true ʧ�ܷ��� false
	 */
	boolean insertProd(Prod prod);
}
