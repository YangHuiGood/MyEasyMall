package cn.tedu.service;

import java.util.List;

import cn.tedu.domain.Prod;

public interface ProdService {
	/**
	 * �����Ʒ��Ϣ����
	 * @param prod ��װ����Ʒ��Ϣ����Ʒ����
	 * @return true - ��ӳɹ�   false - ���ʧ��
	 */
	boolean addProd(Prod prod);

	/**
	 * ��ȡ������Ʒ��Ϣ�ķ���
	 * @return �����˷�װ����Ʒ��Ϣ���б�
	 */
	List getAllProd();

	/**
	 * ������Ʒ��Ϣ����
	 * @param prod ��װ����Ʒ��Ϣ�Ķ���
	 * @return true-���³ɹ� false-����ʧ��
	 */
	boolean updateProd(Prod prod);
}
