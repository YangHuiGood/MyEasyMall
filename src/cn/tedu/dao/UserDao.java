package cn.tedu.dao;

import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;

public interface UserDao {

	/**
	 * �����û�����ѯ�û���Ϣ�ж��Ƿ����
	 * @param username �û���
	 * @return true--�û����� false--�û�������
	 */
	boolean getUserByUsername(String username);

	/**
	 * ���ݴ�����û������û���Ϣ�������ݿ�
	 * @param user ��װ�û���Ϣ���û�����
	 * @return true-- ����ɹ� false-- ����ʧ��
	 */
	boolean saveUser(User user);

	/**
	 * �����û����������ѯ�û���Ϣ�����ط�װ���û���Ϣ�Ķ���
	 * @param username �û���
	 * @param password �û�����
	 * @return �ɹ�����--user���� ʧ�ܷ���--null
	 */
	User getUserByUAP(String username, String password) throws MsgException;

}
