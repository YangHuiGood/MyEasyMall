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
		//获取ServletContext对象
		ServletContext sc = this.getServletContext();
		String encode = sc.getInitParameter("encode");
		// 声明临时文件夹和上传文件夹的相对路径
		String tempPath = "/WEB-INF/temp";
		String uploadPath = "WEB-INF/upload";
		// 声明一个用于保存普通表单项数据的map集合
		Map<String,String> pMap = new HashMap<String,String>();
		// 声明一个变量，保存商品图片的实际url
		String imgurl = null;
		// 1.接收请求参数
		// 2.表单验证（略）
		// 借助commons-fileupload.jar
		DiskFileItemFactory factory = new DiskFileItemFactory(1024*1024,new File(sc.getRealPath(tempPath)));
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		if(!fileUpload.isMultipartContent(req)){
			throw new RuntimeException("请使用正确的文件上传表单");
		}
		// 设置单个文件的大小
		fileUpload.setFileSizeMax(1024*1024);
		// 设置一次上传的文件的总大小
		fileUpload.setSizeMax(1024*1024*10);
		// 解决上传文件名的乱码问题
		fileUpload.setHeaderEncoding(encode);
		
		
		try {
			//解析请求
			List<FileItem> list = fileUpload.parseRequest(req);
			if(list != null){
				for(FileItem fileItem:list){
					if(fileItem.isFormField()){
						//普通表单项
						String name = fileItem.getFieldName();
						String value = fileItem.getString(encode);
						//将普通表单项添加到pMap集合中
						pMap.put(name, value);
					}else{
						//3.将上传的文件保存到相应的位置
						//文件上传项
						String fileName = fileItem.getName();
						
						//ie浏览器的bug问题
						if(fileName.contains("\\")){
							fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
						}
						//文件名重复问题
						fileName = UUID.randomUUID().toString()+"_"+fileName;
						//文件路径问题
						String hexStr = Integer.toHexString(fileName.hashCode());
						// 补足8位
						if(hexStr.length() < 8){
							hexStr = "0"+hexStr;
						}
						//拆分路径
						String midPath = "/";
						for(int i=0;i<hexStr.length();i++){
							midPath += hexStr.charAt(i)+"/";
						}
						imgurl = uploadPath+midPath+fileName;
						//生成目录
						uploadPath = sc.getRealPath(uploadPath+midPath);
						new File(uploadPath).mkdirs();
						//将文件保存到目标目录
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
							throw new RuntimeException("商品图片上传失败");
						}finally{
							is.close();
							fos.close();
							//删除临时文件夹中的数据
							fileItem.delete();
						}
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		//4.调用service执行逻辑
		ProdService service = BaseFactory.getFactory().getInstance(ProdService.class);
		// 创建一个JavaBean，封装本次表单提交的商品信息
		Prod prod = new Prod();
		// 从pMap中获取普通表单项数据，添加到prod对象中
		prod.setName(pMap.get("name"));
		prod.setPrice(Double.parseDouble(pMap.get("price")));
		prod.setCname(pMap.get("cname"));
		prod.setPnum(Integer.parseInt(pMap.get("pnum")));
		// 保存商品图片的url 相对于当前应用的路径
		prod.setImgurl(imgurl);
		prod.setDescription(pMap.get("description"));
		boolean flag = service.addProd(prod);
		// 5.根据执行的结果转发对应的视图
		if(flag){
			//添加成功，定时刷新到后台首页
			resp.getWriter().write("<h1 style='text-align:center;color:red'>商品添加成功,3秒后自动跳转首页</h1>");
			resp.setHeader("refresh", "3;url="+req.getContextPath()+"/backend/_right.jsp");
		}else{
			//添加失败，添加错误提示信息转发到添加商品页面
			req.setAttribute("errMsg", "商品添加失败");
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
