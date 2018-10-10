package cn.tedu.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//通过参数获取ServletContext对象
		ServletContext sc = sce.getServletContext();
		//配置全局的app路径并存入ServletContext作用域
		sc.setAttribute("app", sc.getContextPath());
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
