package cn.tedu.backend.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.tedu.domain.Prod;
import cn.tedu.factory.BaseFactory;
import cn.tedu.service.ProdService;

public class ManageAddProdServlet extends HttpServlet {

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
		//��ȡServletContext����
		ServletContext sc = this.getServletContext();
		String encode = sc.getInitParameter("encode");
		// ������ʱ�ļ��к��ϴ��ļ��е����·��
		String tempPath = "/WEB-INF/temp";
		String uploadPath = "WEB-INF/upload";
		// ����һ�����ڱ�����ͨ�������ݵ�map����
		Map<String,String> pMap = new HashMap<String,String>();
		// ����һ��������������ƷͼƬ��ʵ��url
		String imgurl = null;
		// 1.�����������
		// 2.����֤���ԣ�
		// ����commons-fileupload.jar
		DiskFileItemFactory factory = new DiskFileItemFactory(1024*1024,new File(sc.getRealPath(tempPath)));
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		if(!fileUpload.isMultipartContent(req)){
			throw new RuntimeException("��ʹ����ȷ���ļ��ϴ���");
		}
		// ���õ����ļ��Ĵ�С
		fileUpload.setFileSizeMax(1024*1024);
		// ����һ���ϴ����ļ����ܴ�С
		fileUpload.setSizeMax(1024*1024*10);
		// ����ϴ��ļ�������������
		fileUpload.setHeaderEncoding(encode);
		
		
		try {
			//��������
			List<FileItem> list = fileUpload.parseRequest(req);
			if(list != null){
				for(FileItem fileItem:list){
					if(fileItem.isFormField()){
						//��ͨ����
						String name = fileItem.getFieldName();
						String value = fileItem.getString(encode);
						//����ͨ������ӵ�pMap������
						pMap.put(name, value);
					}else{
						//3.���ϴ����ļ����浽��Ӧ��λ��
						//�ļ��ϴ���
						String fileName = fileItem.getName();
						
						//ie�������bug����
						if(fileName.contains("\\")){
							fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
						}
						//�ļ����ظ�����
						fileName = UUID.randomUUID().toString()+"_"+fileName;
						//�ļ�·������
						String hexStr = Integer.toHexString(fileName.hashCode());
						// ����8λ
						if(hexStr.length() < 8){
							hexStr = "0"+hexStr;
						}
						//���·��
						String midPath = "/";
						for(int i=0;i<hexStr.length();i++){
							midPath += hexStr.charAt(i)+"/";
						}
						imgurl = uploadPath+midPath+fileName;
						//����Ŀ¼
						uploadPath = sc.getRealPath(uploadPath+midPath);
						new File(uploadPath).mkdirs();
						//���ļ����浽Ŀ��Ŀ¼
						InputStream is = fileItem.getInputStream();
						FileOutputStream fos = null;
						try {
							fos = new FileOutputStream(uploadPath+"/"+fileName);
							byte[] array = new byte[1024];
							int len = is.read(array);
							while(len != -1){
								fos.write(array,0,len);
								len = is.read(array);
							}
						} catch (Exception e) {
							e.printStackTrace();
							throw new RuntimeException("��ƷͼƬ�ϴ�ʧ��");
						}finally{
							is.close();
							fos.close();
							//ɾ����ʱ�ļ����е�����
							fileItem.delete();
						}
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		//4.����serviceִ���߼�
		ProdService service = BaseFactory.getFactory().getInstance(ProdService.class);
		// ����һ��JavaBean����װ���α��ύ����Ʒ��Ϣ
		Prod prod = new Prod();
		// ��pMap�л�ȡ��ͨ�������ݣ���ӵ�prod������
		prod.setName(pMap.get("name"));
		prod.setPrice(Double.parseDouble(pMap.get("price")));
		prod.setCname(pMap.get("cname"));
		prod.setPnum(Integer.parseInt(pMap.get("pnum")));
		// ������ƷͼƬ��url ����ڵ�ǰӦ�õ�·��
		prod.setImgurl(imgurl);
		prod.setDescription(pMap.get("description"));
		boolean flag = service.addProd(prod);
		// 5.����ִ�еĽ��ת����Ӧ����ͼ
		if(flag){
			//��ӳɹ�����ʱˢ�µ���̨��ҳ
			resp.getWriter().write("<h1 style='text-align:center;color:red'>��Ʒ��ӳɹ�,3����Զ���ת��ҳ</h1>");
			resp.setHeader("refresh", "3;url="+req.getContextPath()+"/backend/_right.jsp");
		}else{
			//���ʧ�ܣ���Ӵ�����ʾ��Ϣת���������Ʒҳ��
			req.setAttribute("errMsg", "��Ʒ���ʧ��");
			req.getRequestDispatcher("/backend/manageAddProd.jsp").forward(req, resp);
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
