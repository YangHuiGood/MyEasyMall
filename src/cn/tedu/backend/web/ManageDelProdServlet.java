package cn.tedu.backend.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.factory.BaseFactory;
import cn.tedu.service.ProdService;

public class ManageDelProdServlet extends HttpServlet {

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
		//获取参数
		int pid = Integer.parseInt(req.getParameter("pid"));
		//表单验证
		//执行service逻辑
		ProdService service = BaseFactory.getFactory().getInstance(ProdService.class);
		boolean flag = service.delProd(pid);
		//根据结果返回相应的页面
		if(flag){
			resp.getWriter().write("<h1 style='text-align:center;color:red'>商品删除成功,3秒后自动跳转商品列表页</h1>");
			resp.setHeader("refresh", "3;url="+req.getContextPath()+"/ManageShowAllProdServlet");
		}else{
			resp.getWriter().write("<h1 style='text-align:center;color:red'>商品删除失败,3秒后自动跳转商品类表页</h1>");
			resp.setHeader("refresh", "3;url="+req.getContextPath()+"/ManageShowAllProdServlet");
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
