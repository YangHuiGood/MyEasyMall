package cn.tedu.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProdImgServlet extends HttpServlet {

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
		String imgurl = req.getParameter("imgurl");
		//表单验证
		//调用service逻辑
		FileInputStream fis = null;
		try {
			//获取图片真实路径
			String imgPath = this.getServletContext().getRealPath(imgurl);
			//创建输入流
		    fis = new FileInputStream(imgPath);
			//获取输出流
			ServletOutputStream sos = resp.getOutputStream();
			byte[] array = new byte[1024];
			int len = fis.read(array);
			while(len != -1){
				sos.write(array,0,len);
				len = fis.read(array);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			if(fis != null){
				fis.close();
			}
		}
		
		
		//根据经过转发相应页面

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
