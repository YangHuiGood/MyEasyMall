package cn.tedu.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	public static String md5(String plainText) {
		// ����������ܺ�����ĵ�����
		byte[] secretBytes = null;
		try {
			//������ת��byte���鲢���м��ܣ������������
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("û��md5����㷨��");
		}
		//������������ת��16���Ʊ�ʾ���ַ���
		String md5code = new BigInteger(1, secretBytes).toString(16);
		//128λ2��������ת��16����ʱ���ܲ���32λ
		//���ַ���ǰ�油0ʹ�����ַ������Ⱦ�Ϊ32λ
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		//����32λ��������
		return md5code;
	}

}
