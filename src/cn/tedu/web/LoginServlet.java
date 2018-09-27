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
		//登录
		
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
			//将用户信息封装在User对象中存入session中，以便以后使用
			req.getSession().setAttribute("user", user);
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
		}else{
			req.setAttribute("errMsg", "用户名或密码错误");
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
