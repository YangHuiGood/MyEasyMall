package cn.tedu.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.util.JDBCUtils;
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
		if(!email.matches(reg)){
			WebUtils.setEmptyMsg(req, "�����ʽ����ȷ", resp);
			return;
		}
		//�û���������֤
		String sql1 = "select * from user where username=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql1);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(rs.next()){
				WebUtils.setEmptyMsg(req, "�û����Ѵ���", resp);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			WebUtils.setEmptyMsg(req, "ע������쳣�����Ժ�����...", resp);
			return;
		}finally{
			JDBCUtils.close(conn, ps, rs);
		}
		
		//��֤����֤
		//�����ݴ������ݿ�
		String sql2 = "insert into user values(null,?,?,?,?)";
		Connection conn2 = null;
		PreparedStatement ps2 = null;
		try {
			conn2 = JDBCUtils.getConnection();
			ps2 = conn2.prepareStatement(sql2);
			ps2.setString(1, username);
			ps2.setString(2, password);
			ps2.setString(3, nickname);
			ps2.setString(4, email);
			int count = ps2.executeUpdate();
			if(count > -1){
				//5.����ɹ�-��ʾ�ɹ���Ϣ����ʱˢ�µ���ҳ
				resp.getWriter().write("<h1 style='text-align: center;color:red'>��ϲ����ע��ɹ���3�����ת����ҳ</h1>");
				resp.setHeader("refresh","3;url="+req.getContextPath()+"/index.jsp");
			}else{
				WebUtils.setEmptyMsg(req, "�������ݳ����쳣�����Ժ�����...", resp);
				return;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("�����������쳣�����Ժ�����..."+e.getMessage());
		}finally{
			JDBCUtils.close(conn2, ps2, null);
		}
		
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
