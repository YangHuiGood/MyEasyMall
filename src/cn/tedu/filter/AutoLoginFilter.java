package cn.tedu.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BaseFactory;
import cn.tedu.service.UserService;

public class AutoLoginFilter implements Filter{

	private String encode = null;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.encode = filterConfig.getServletContext().getInitParameter("encode");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		//�Ȼ�ȡHttpSession����
		HttpSession session = req.getSession(false);
		//�ж��û�û�е�¼
		if(session == null || session.getAttribute("user") == null){
			Cookie[] cs = req.getCookies();
			Cookie FindC = null;
			if(cs != null){
				for(Cookie c:cs){
					if("autologin".equals(c.getName())){
						FindC = c;
						break;
					}
				}
			}
			//�û�Я�����Զ���¼��cookie
			if(FindC != null){
				//3.�Զ���¼cookie�б�����û�������������ȷ��
				String value = FindC.getValue();//�õ��û�������cookie�е�username*password�ַ���
				//��ȡ������cookie�е��ַ���ȡ���û���������
				String username = URLDecoder.decode(value.split("#")[0],encode);
				String password = value.split("#")[1];
				UserService service = BaseFactory.getFactory().getInstance(UserService.class);
				try {
					User user = service.login(username, password);
					if(user != null){
						//��¼�ɹ��������¼��Ϣ
						req.getSession().setAttribute("user",user);
					}
				} catch (MsgException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		//��������
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
