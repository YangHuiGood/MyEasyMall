package cn.tedu.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.tedu.domain.User;
import cn.tedu.factory.BaseFactory;
import cn.tedu.service.UserService;
import cn.tedu.service.UserServiceImpl;
import cn.tedu.util.JDBCUtils;
import cn.tedu.util.MD5Util;
import cn.tedu.util.WebUtils;

public class RegistServlet extends HttpServlet {

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
		//���ձ�����
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String password2 = req.getParameter("password2");
		String nickname = req.getParameter("nickname");
		String email = req.getParameter("email");
		String valistr = req.getParameter("valistr").trim().toUpperCase();
		//����֤
		//��֤����֤
		if(WebUtils.isEmpty(valistr)){
			WebUtils.setEmptyMsg(req, "��֤�벻��Ϊ��", resp);
			return;
		}else{
			// ��session������ȡ����֤����Ϣ
			HttpSession session = req.getSession(false);
			boolean flag = true;//Ĭ����֤��û����
			if(session == null || session.getAttribute("verifyCode") == null){
				flag = false;
			}else{
				String verifyCode = (String)session.getAttribute("verifyCode");
				if(!verifyCode.equalsIgnoreCase(valistr)){
					flag = false;
				}
			}
			if(flag == false){
				WebUtils.setEmptyMsg(req, "��֤�벻��ȷ", resp);
				return;
			}
		}
		
		//�ǿ���֤
		if(WebUtils.isEmpty(username)){
			WebUtils.setEmptyMsg(req, "�û�������Ϊ��", resp);
			return;
		}
		if(WebUtils.isEmpty(password)){
			WebUtils.setEmptyMsg(req, "���벻��Ϊ��", resp);
			return;
		}
		if(WebUtils.isEmpty(password2)){
			WebUtils.setEmptyMsg(req, "ȷ�����벻��Ϊ��", resp);
			return;
		}
		if(WebUtils.isEmpty(nickname)){
			WebUtils.setEmptyMsg(req, "�ǳƲ���Ϊ��", resp);
			return;
		}
		if(WebUtils.isEmpty(email)){
			WebUtils.setEmptyMsg(req, "���䲻��Ϊ��", resp);
			return;
		}
		//����һ������֤
		if(!password.equals(password2)){
			WebUtils.setEmptyMsg(req, "�������벻һ��", resp);
			return;
		}
		//�����ʽ��֤
		String reg = "^\\w+@\\w+(\\.\\w+)+$";
		if(!email.matches(reg)){
			WebUtils.setEmptyMsg(req, "�����ʽ����ȷ", resp);
			return;
		}
		//�û���������֤
		UserService service = BaseFactory.getFactory().getInstance(UserService.class);
		boolean flag = service.hasUser(username);
		if(flag){
		WebUtils.setEmptyMsg(req, "�û����Ѵ���", resp);
		return;
	}
		
		
		
		
		

		//ʹ��MD5��������м��ܴ���
		password = MD5Util.md5(password);
		
		//�����ݴ������ݿ�
		User user = new User(-1,username,password,nickname,email);
		boolean flag1 = service.registUser(user);
		if(flag1){
		//5.����ɹ�-��ʾ�ɹ���Ϣ����ʱˢ�µ���ҳ
		resp.getWriter().write("<h1 style='text-align: center;color:red'>��ϲ����ע��ɹ���3�����ת����¼ҳ��</h1>");
		resp.setHeader("refresh","3;url="+req.getContextPath()+"/login.jsp");
		}else{
			WebUtils.setEmptyMsg(req, "�������ݳ����쳣�����Ժ�����...", resp);
			return;
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
