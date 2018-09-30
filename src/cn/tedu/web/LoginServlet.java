package cn.tedu.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BaseFactory;
import cn.tedu.service.UserService;
import cn.tedu.service.UserServiceImpl;
import cn.tedu.util.JDBCUtils;
import cn.tedu.util.MD5Util;
import cn.tedu.util.WebUtils;

public class LoginServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//��ȡ�����е��ַ���
		String encode = this.getServletContext().getInitParameter("encode");
	    //��ȡ����
	    String username = req.getParameter("username");
	    String password = req.getParameter("password");
	    String remname = req.getParameter("remname");
	    String autologin = req.getParameter("autologin");
//	    System.out.println(username);
	    //����֤
	    if(WebUtils.isEmpty(username)){
			WebUtils.setEmptyMsg(req, "�û�������Ϊ��", resp);
			return;
		}
		if(WebUtils.isEmpty(password)){
			WebUtils.setEmptyMsg(req, "���벻��Ϊ��", resp);
			return;
		}
		//��ס�û���
		//�ж��û��Ƿ� ��ѡ�˼�ס�û���ѡ��
		if(remname != null && "true".equals(remname)){
			//����cookie������cookie
			Cookie cookie = new Cookie("remname",URLEncoder.encode(username,encode));
			//����cookie����ʱ��
			cookie.setMaxAge(60*60*24*7);
			//����cookie·��
			cookie.setPath(req.getContextPath()+"/");
			//��ӽ���Ӧͷ��
			resp.addCookie(cookie);
		}else{
			Cookie cookie = new Cookie("remname","");
			cookie.setMaxAge(0);
			cookie.setPath(req.getContextPath()+"/");
			resp.addCookie(cookie);
		}
		//��¼
		//ʹ��MD5��������м��ܴ���
		password = MD5Util.md5(password);
		
		UserService service = BaseFactory.getFactory().getInstance(UserService.class);
		User user = null;
		try {
			user = service.login(username,password);
		} catch (MsgException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.setAttribute("errMsg", e.getMessage());
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
		if(user != null){
			//���û���Ϣ��װ��User�����д���session�У��Ա��Ժ�ʹ��
			req.getSession().setAttribute("user", user);
			
			//ʵ��30���Զ���¼�߼�
			if("true".equals(autologin)){
				//�û���ѡ��30���Զ���¼ѡ��
				//���������Զ���¼��cookie
				Cookie cookie = new Cookie("autologin",URLEncoder.encode(username, encode)+"#"+password);
				//  ����cookie�����ʱ��
				cookie.setMaxAge(60*60*24*30);
				//����cookie��·��
				cookie.setPath(req.getContextPath()+"/");
				//��cookie������ӽ�Ӧ����
				resp.addCookie(cookie);
			}
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
		}else{
			req.setAttribute("errMsg", "�û������������");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		doGet(req, resp);
	}

}
