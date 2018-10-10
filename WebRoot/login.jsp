<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import= "java.net.URLDecoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="${app }/css/login.css"/>
		<title>EasyMall欢迎您登陆</title>
		<script type="text/javascript" src="${app }/js/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="${app }/js/jquery.validate.js"></script>
		<script type="text/javascript" src="${app }/js/messages_zh.js"></script>
		<script type="text/javascript" src="${app }/js/additional-methods.js"></script>
		<style>
        .error{
	       color:red;
           }
     </style>
	</head>
	<body>
	
	 <%
	  //1.获取用户本次请求携带的所有cookie
	  Cookie[] cs = request.getCookies();
	  //2.判断用户是否携带了记住用户名的cookie
	  Cookie findC = null;
	  if(cs != null){
	      for(Cookie c:cs){
	         if("remname".equals(c.getName())){
	            findC = c;
	            break;
	         }
	       }
	  }
	  //3.如果携带了，获取cookie中保存的用户名
	  String username = "";
	  if(findC != null){
	     username = URLDecoder.decode(findC.getValue(),"utf-8");
	  }
	  //4.将用户名添加到username的input中
	  %>
	  
		<h1>欢迎登陆MyEasyMall</h1>
		<form id="login" action="/LoginServlet" method="POST">
			<table>
			    <tr>
			    
			       <td style="text-align: center;color:red">${requestScope.errMsg}</td>
			    </tr>
				<tr>
					<td class="tdx">用户名：</td>
					
					<c:if test="${ not empty cookie.remname.value}" var="flag" scope="page">
					  <td><input type="text" name="username" value="<%=username%>"/></td>
					</c:if>
					<c:if test="${!flag}">
					  <td><input type="text" name="username" value=""/></td>
					</c:if>
					
				</tr>
				<tr>
					<td class="tdx">密&nbsp;&nbsp; 码：</td>
					<td><input type="password" name="password"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="checkbox" 
						${empty cookie.remname.value ? "" : "checked='checked'"}
						 name="remname" value="true"/>记住用户名
						<input type="checkbox" name="autologin" value="true"/>30天内自动登陆
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center">
						<input type="submit" value="登 陆"/>
					</td>
				</tr>
			</table>
		</form>		
	</body>
	<script type="text/javascript">
	    $().ready(function() {
			// 在键盘按下并释放及提交后验证提交表单
			  $("#login").validate({
			    rules: {
			      username: {
			        required: true,
			        minlength: 2,
			      },
			      password: {
			        required: true,
			        minlength: 5
			      }
			    },
			    messages: {
			      username: {
			        required: "请输入用户名",
			        minlength: "用户名太短",
			      },
			      password: {
			        required: "请输入密码",
			        minlength: "密码长度不够"
			      }
			     },
			     errorPlacement: function(error, element) {  
                            error.appendTo(element.parent());  
                          },
                 onkeyup:false                 
			    });
});
	   </script>
</html>
