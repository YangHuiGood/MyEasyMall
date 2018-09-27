package cn.tedu.service;

import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;

public interface UserService {

	/**
	 * �ж��û��Ƿ���ڵķ���
	 * @param username �û����û���
	 * @return true- �û����� false-�û�������
	 */
	boolean hasUser(String username);

	/**
	 * �����û��Ķ����û���Ϣ�������ݿ���
	 * @param user ��װ���û���Ϣ���û�����
	 * @return true-��������ɹ� false-�������ʧ��
	 */
	boolean registUser(User user);

	/**
	 * �����û���������ʵ���û��ĵ�¼����
	 * @param username �û���
	 * @param password �û�����
	 * @return ��¼�ɹ�--���ط�װ���û���Ϣ��user���� ��¼ʧ��-- ����null
	 */
	User login(String username, String password) throws MsgException;

}
