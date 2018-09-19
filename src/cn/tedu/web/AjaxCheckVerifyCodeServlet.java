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
		// 获取web.xml中配置的字符集
		ServletContext sc = this.getServletContext();
		String encode = sc.getInitParameter("encode");
		// 1.处理乱码问题 请求乱码 应答乱码
		resp.setContentType("text/html;charset=" + encode);
		//获取请求参数
		String valistr = req.getParameter("valistr").trim().toUpperCase();
		// 从ServletContext对象中取出验证码信息
//		String verifyCode = sc.getAttribute("verifyCode").toString().toUpperCase();
		//从session中取出验证码信息
		String verifyCode = req.getSession().getAttribute("verifyCode").toString().toUpperCase();
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
