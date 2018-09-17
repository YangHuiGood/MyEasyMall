package cn.tedu.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtils {
	/**
	 * �ж��ַ����Ƿ�Ϊnull �� �մ�
	 * @param str ���жϵ��ַ���
	 * @return  ����ַ���Ϊnull ����Ϊ�մ� ����  true
	 *          ����ַ�������null ���Ҳ�Ϊ�մ� ���� false
	 */
	public static boolean isEmpty(String str){
		if(str == null || "".equals(str.trim())){
			return true;
		}
		return false;
	}
	/**
	 * �ǿ���֤�����ô�����Ϣ��ת��
	 * @param req http����ʵ��
	 * @param str ��ʾ��Ϣ
	 * @param resp httpӦ��ʵ��
	 */
	public static void setEmptyMsg(HttpServletRequest req,String str,HttpServletResponse resp){
		//��request����������Ӵ�����ʾ��Ϣ
		req.setAttribute("errMsg", str);
		//������ת����regist.jsp
		try {
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
