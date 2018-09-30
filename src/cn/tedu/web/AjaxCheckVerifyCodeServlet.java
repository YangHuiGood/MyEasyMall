package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.tedu.util.WebUtils;

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
		//获取请求参数
		String valistr = req.getParameter("valistr");
		//从session中取出验证码信息
		HttpSession session = req.getSession(false);
		boolean flag = true;//默认验证码没问题
		if(session == null || session.getAttribute("verifyCode") == null){
			flag = false;
		}else{
			String verifyCode = (String)session.getAttribute("verifyCode");
			if(!verifyCode.equalsIgnoreCase(valistr)){
				flag = false;
			}
		}
		if(flag){
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
