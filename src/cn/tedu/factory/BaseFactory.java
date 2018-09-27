package cn.tedu.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 通用的工厂类
 * 实现层与层之间的解耦
 * 利用工厂模式+单例+配置文件+接口的方式将耦合管理起来
 * @author tarena
 *
 */
public class BaseFactory {
	//私有化构造函数
	private BaseFactory(){
		//获取配置文件的绝对路径
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
	//声明私有静态类变量创建本类的实例对象
	private static BaseFactory factory = new BaseFactory();
	//提供公共的入口实现返回本类的对象的方法
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
