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
		//解决乱码问题
		//请求乱码
		req.setCharacterEncoding("utf-8");
		//应答乱码
		resp.setContentType("text/html;charset=utf-8");
		//接收表单数据
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String password2 = req.getParameter("password2");
		String nickname = req.getParameter("nickname");
		String email = req.getParameter("email");
		String valistr = req.getParameter("valistr");
		//表单验证
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
		if(WebUtils.isEmpty(valistr)){
			WebUtils.setEmptyMsg(req, "验证码不能为空", resp);
			return;
		}
		//密码一致性验证
		if(!password.equals(password2)){
			WebUtils.setEmptyMsg(req, "两次密码不一致", resp);
			return;
		}
		//邮箱格式验证
		String reg = "^\\w+@\\w+(\\.\\w+)+$";
		if(email.matches(reg)){
			WebUtils.setEmptyMsg(req, "邮箱格式不正确", resp);
			return;
		}
		//用户名存在验证
		//验证码验证
		//将数据存入数据库
		//给用户提示信息

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
