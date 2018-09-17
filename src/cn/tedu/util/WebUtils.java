package cn.tedu.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtils {
	/**
	 * 判断字符串是否为null 或 空串
	 * @param str 被判断的字符串
	 * @return  如果字符串为null 或者为空串 返回  true
	 *          如果字符串不是null 并且不为空串 返回 false
	 */
	public static boolean isEmpty(String str){
		if(str == null || "".equals(str.trim())){
			return true;
		}
		return false;
	}
	/**
	 * 非空验证后设置错误信息并转发
	 * @param req http请求实体
	 * @param str 提示信息
	 * @param resp http应答实体
	 */
	public static void setEmptyMsg(HttpServletRequest req,String str,HttpServletResponse resp){
		//向request作用域中添加错误提示信息
		req.setAttribute("errMsg", str);
		//将请求转发给regist.jsp
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
