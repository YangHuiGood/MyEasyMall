package cn.tedu.service;

import cn.tedu.domain.Prod;

public interface ProdService {
	/**
	 * �����Ʒ��Ϣ����
	 * @param prod ��װ����Ʒ��Ϣ����Ʒ����
	 * @return true - ��ӳɹ�   false - ���ʧ��
	 */
	boolean addProd(Prod prod);
}
