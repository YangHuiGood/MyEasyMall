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
		//接收表单数据
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String password2 = req.getParameter("password2");
		String nickname = req.getParameter("nickname");
		String email = req.getParameter("email");
		String valistr = req.getParameter("valistr").trim().toUpperCase();
		//表单验证
		//验证码验证
		if(WebUtils.isEmpty(valistr)){
			WebUtils.setEmptyMsg(req, "验证码不能为空", resp);
			return;
		}else{
			// 从session对象中取出验证码信息
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
			if(flag == false){
				WebUtils.setEmptyMsg(req, "验证码不正确", resp);
				return;
			}
		}
		
		//非空验证
		if(WebUtils.isEmpty(username)){
			WebUtils.setEmptyMsg(req, "用户名不能为空", resp);
			return;
		}
		if(WebUtils.isEmpty(password)){
			WebUtils.setEmptyMsg(req, "密码不能为空", resp);
			return;
		}
		if(WebUtils.isEmpty(password2)){
			WebUtils.setEmptyMsg(req, "确认密码不能为空", resp);
			return;
		}
		if(WebUtils.isEmpty(nickname)){
			WebUtils.setEmptyMsg(req, "昵称不能为空", resp);
			return;
		}
		if(WebUtils.isEmpty(email)){
			WebUtils.setEmptyMsg(req, "邮箱不能为空", resp);
			return;
		}
		//密码一致性验证
		if(!password.equals(password2)){
			WebUtils.setEmptyMsg(req, "两次密码不一致", resp);
			return;
		}
		//邮箱格式验证
		String reg = "^\\w+@\\w+(\\.\\w+)+$";
		if(!email.matches(reg)){
			WebUtils.setEmptyMsg(req, "邮箱格式不正确", resp);
			return;
		}
		//用户名存在验证
		UserService service = BaseFactory.getFactory().getInstance(UserService.class);
		boolean flag = service.hasUser(username);
		if(flag){
		WebUtils.setEmptyMsg(req, "用户名已存在", resp);
		return;
	}
		
		
		
		
		

		//使用MD5对密码进行加密处理
		password = MD5Util.md5(password);
		
		//将数据存入数据库
		User user = new User(-1,username,password,nickname,email);
		boolean flag1 = service.registUser(user);
		if(flag1){
		//5.保存成功-提示成功信息，定时刷新到首页
		resp.getWriter().write("<h1 style='text-align: center;color:red'>恭喜您，注册成功！3秒后跳转至登录页面</h1>");
		resp.setHeader("refresh","3;url="+req.getContextPath()+"/login.jsp");
		}else{
			WebUtils.setEmptyMsg(req, "插入数据出现异常，请稍后重试...", resp);
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
