package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.util.VerifyCode;

public class VerifyCodeServlet extends HttpServlet {

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
		//�����û�����
		//������֤�빤����������֤��ͼƬ
		VerifyCode img = new VerifyCode();
		//�����ɵ���֤��ͼƬ������Ӧ������
		img.drawImage(resp.getOutputStream());
		//�����������Ҫ����ͼƬ����
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		//����֤����Ϣ���뵽����̨
//		System.out.println(img.getCode());
		//����֤����Ϣ������ServletContext��
		//�õ�ServletContext����
		ServletContext sc = this.getServletContext();
		//������֤����Ϣ
		sc.setAttribute("verifyCode", img.getCode());
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