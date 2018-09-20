package cn.tedu.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	    req.setCharacterEncoding(encode);
	    //��ȡ����
	    String username = req.getParameter("username");
	    String password = req.getParameter("password");
	    String remname = req.getParameter("remname");
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
		resp.sendRedirect(req.getContextPath()+"/index.jsp");
		
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
