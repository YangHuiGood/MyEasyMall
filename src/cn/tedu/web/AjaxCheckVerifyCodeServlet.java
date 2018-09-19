package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxCheckVerifyCodeServlet extends HttpServlet {

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
		// ��ȡweb.xml�����õ��ַ���
		ServletContext sc = this.getServletContext();
		String encode = sc.getInitParameter("encode");
		// 1.������������ �������� Ӧ������
		resp.setContentType("text/html;charset=" + encode);
		//��ȡ�������
		String valistr = req.getParameter("valistr").trim().toUpperCase();
		// ��ServletContext������ȡ����֤����Ϣ
		String verifyCode = sc.getAttribute("verifyCode").toString().toUpperCase();
		if(verifyCode.equals(valistr)){
			resp.getWriter().write("true");
		}else{
			resp.getWriter().write("false");
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
