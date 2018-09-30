package cn.tedu.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodingFilter implements Filter{

	private String encode = null;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.encode = filterConfig.getServletContext().getInitParameter("encode");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//处理应答乱码问题
		response.setContentType("text/html;charset="+encode);
		//创建装饰者对象，将装饰者对象传递给请求的Servlet
		MyRequest req = new MyRequest((HttpServletRequest) request);
		chain.doFilter(req, response);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	class MyRequest extends HttpServletRequestWrapper{

		private boolean hasEncode = false;//该变量表明该map集合是否被手动编解码，默认没有
		public MyRequest(HttpServletRequest request) {
			super(request);
			// TODO Auto-generated constructor stub
		}

		@Override
		public String getParameter(String name) {
			Map<String,String[]> paramMap = this.getParameterMap();
			String[] values = paramMap.get(name);
			if(values == null){
				return null;
			}
			return values[0];
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			//获取被装饰者的map集合
			Map<String, String[]> paramMap = this.getRequest().getParameterMap();
			if(this.hasEncode == false){
				//遍历map集合，进行手动编解码
				for(Entry<String,String[]> entry:paramMap.entrySet()){
					//获取一个请求参数对应的值
					String[] values = entry.getValue();
					if(values != null){
						for(int i=0;i<values.length;i++){
							try {
								//手动编解码
								values[i] = new String(values[i].getBytes("iso8859-1"),encode);
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
				this.hasEncode = true;
			}
			
			return paramMap;
		}

		@Override
		public String[] getParameterValues(String name) {
			// TODO Auto-generated method stub
			return this.getParameterMap().get(name);
		}
		
	}

}
