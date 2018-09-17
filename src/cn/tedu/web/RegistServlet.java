package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		//�����������
		//��������
		req.setCharacterEncoding("utf-8");
		//Ӧ������
		resp.setContentType("text/html;charset=utf-8");
		//���ձ�����
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String password2 = req.getParameter("password2");
		String nickname = req.getParameter("nickname");
		String email = req.getParameter("email");
		String valistr = req.getParameter("valistr");
		//����֤
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
		if(WebUtils.isEmpty(valistr)){
			WebUtils.setEmptyMsg(req, "��֤�벻��Ϊ��", resp);
			return;
		}
		//����һ������֤
		if(!password.equals(password2)){
			WebUtils.setEmptyMsg(req, "�������벻һ��", resp);
			return;
		}
		//�����ʽ��֤
		String reg = "^\\w+@\\w+(\\.\\w+)+$";
		if(email.matches(reg)){
			WebUtils.setEmptyMsg(req, "�����ʽ����ȷ", resp);
			return;
		}
		//�û���������֤
		//��֤����֤
		//�����ݴ������ݿ�
		//���û���ʾ��Ϣ

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
