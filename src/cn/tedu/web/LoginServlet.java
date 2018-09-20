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
		//获取配置中的字符集
		String encode = this.getServletContext().getInitParameter("encode");
	    req.setCharacterEncoding(encode);
	    //获取参数
	    String username = req.getParameter("username");
	    String password = req.getParameter("password");
	    String remname = req.getParameter("remname");
	    //表单验证
	    if(WebUtils.isEmpty(username)){
			WebUtils.setEmptyMsg(req, "用户名不能为空", resp);
			return;
		}
		if(WebUtils.isEmpty(password)){
			WebUtils.setEmptyMsg(req, "密码不能为空", resp);
			return;
		}
		//记住用户名
		//判断用户是否 勾选了记住用户名选项
		if(remname != null && "true".equals(remname)){
			//创建cookie并设置cookie
			Cookie cookie = new Cookie("remname",URLEncoder.encode(username,encode));
			//设置cookie存活的时间
			cookie.setMaxAge(60*60*24*7);
			//设置cookie路径
			cookie.setPath(req.getContextPath()+"/");
			//添加进相应头中
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
