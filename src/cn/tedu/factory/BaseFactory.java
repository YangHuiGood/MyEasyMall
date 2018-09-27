package cn.tedu.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * ͨ�õĹ�����
 * ʵ�ֲ����֮��Ľ���
 * ���ù���ģʽ+����+�����ļ�+�ӿڵķ�ʽ����Ϲ�������
 * @author tarena
 *
 */
public class BaseFactory {
	//˽�л����캯��
	private BaseFactory(){
		//��ȡ�����ļ��ľ���·��
		String path = BaseFactory.class.getClassLoader().getResource("config.properties").getPath();
		try {
			prop.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//����˽�о�̬��������������ʵ������
	private static BaseFactory factory = new BaseFactory();
	//�ṩ���������ʵ�ַ��ر���Ķ���ķ���
	public static BaseFactory getFactory(){
		return factory;
	}
	private Properties prop = new Properties();
	public <T> T getInstance(Class<T> c){
		String value = prop.getProperty(c.getSimpleName());
		try {
			Class cz = Class.forName(value);
			return (T) cz.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
}
