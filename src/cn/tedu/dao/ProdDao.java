package cn.tedu.dao;

import java.util.List;

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

	/**
	 * ��ѯ������Ʒ��Ϣ�����б���
	 * @return ��װ����Ʒ��Ϣ���б�
	 */
	List getAllProd();

	/**
	 * ������Ʒ��Ϣ
	 * @param prod ��װ����Ʒ��Ϣ�Ķ���
	 * @return true-���³ɹ� false-����ʧ��
	 */
	boolean updateProd(Prod prod);

	/**
	 * ������Ʒid��ѯ��Ʒ����id
	 * @param pid ��Ʒid
	 * @return ������Ʒ����idֵ
	 */
	int getCidById(int pid);

	/**
	 * ������Ʒ����id��ѯ��������Ʒ������
	 * @param cid ��Ʒ����id
	 * @return ������Ʒ������
	 */
	int getProdCountByCid(int cid);

	/**
	 * ������Ʒidɾ����Ʒ��Ϣ
	 * @param pid ��Ʒid
	 * @return true-ɾ���ɹ� false-ɾ��ʧ��
	 */
	boolean delProdById(int pid);

	/**
	 * ��������idɾ��������Ϣ
	 * @param cid ��Ʒ����id
	 * @return true-ɾ����Ʒ����ɹ� false-ɾ����Ʒ����ʧ��
	 */
	boolean delProdCateById(int cid);
}
