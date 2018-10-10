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
		//��ȡ����
		String imgurl = req.getParameter("imgurl");
		//����֤
		//����service�߼�
		FileInputStream fis = null;
		try {
			//��ȡͼƬ��ʵ·��
			String imgPath = this.getServletContext().getRealPath(imgurl);
			//����������
		    fis = new FileInputStream(imgPath);
			//��ȡ�����
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
		
		
		//���ݾ���ת����Ӧҳ��

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
