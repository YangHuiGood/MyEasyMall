<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>欢迎注册MyEasyMall</title>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="${app }/css/regist.css"/>
		<script type="text/javascript" src="${app }/js/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="${app }/js/jquery.validate.js"></script>
		<script type="text/javascript" src="${app }/js/messages_zh.js"></script>
		<script type="text/javascript" src="${app }/js/additional-methods.js"></script>
		<script type="text/javascript">
	    $().ready(function() {
			// 在键盘按下并释放及提交后验证提交表单
			  $("#onfocusout").validate({
			    rules: {
			      username: {
			        required: true,
			        minlength: 2,
			        remote: {
						    url: "/AjaxCheckUsernameServlet",     //后台处理程序
						    type: "post",               //数据发送方式  
						    dataType: "json",           //接受数据格式 
						    data: {                      //要传递的数据
						        username: function() {
						            return $("#username").val();
						        }
						    }
						    /*
						    不要轻信百度，瞎几把乱写。害我
						    dataFilter: function (data) {//判断控制器返回的内容
						    
                            if (data == "true") {
                            alert(data);
                                return true;
                            }
                            else {
                            alert(data);
                                return false;
                            }
                        }*/
					}
			      },
			      password: {
			        required: true,
			        minlength: 5
			      },
			      password2: {
			        required: true,
			        minlength: 5,
			        equalTo: "#password"
			      },
			      nickname: {
			        required: true,
			        minlength: 2
			      },
			      email: {
			        required: true,
			        email: true
			      },
			      valistr: {
			        required: true,
			        remote: {
						    url: "/AjaxCheckVerifyCodeServlet",     //后台处理程序
						    type: "post",               //数据发送方式  
						    dataType: "json",           //接受数据格式 
						    data: {                      //要传递的数据
						        username: function() {
						            return $("#valistr").val();
						        }
						    }
					}
			      }
			    },
			    messages: {
			      username: {
			        required: "请输入用户名",
			        minlength: "用户名至少由两个字母组成",
			        remote:"用户名不可用"
			      },
			      password: {
			        required: "请输入密码",
			        minlength: "密码不能小于 5 个字母"
			      },
			      password2: {
			        required: "请输入确认密码",
			        minlength: "确认密码 同上",
			        equalTo: "两次密码输入不一致"
			      },
			      nickname: {
			        required: "请输入昵称",
			        minlength: "昵称至少由两个字母组成"
			      },
			      email: {
			        required: "请输入邮箱",
			        email: "请输入一个正确的邮箱"
			      },
			      valistr: {
			        required: "请输入验证码",
			        remote:"验证码不正确"
			      }
			     },
			     errorPlacement: function(error, element) {  
                            error.appendTo(element.parent());  
                          },
                 onkeyup:false                 
			    });
			    
			    
	  //为img标签添加一个点击事件
      $("#valiImage").click(function(){
		  //每次点击修改src的值，在后面拼接一个不同的参数
		  //获取当前的时间戳
		  var timeStr = new Date().getTime();
		  //将时间戳拼接在url后面，实现每次点击都不一样
		  var url = "/VerifyCodeServlet?time="+timeStr;
		  //修改img的src的属性
		  $(this).attr("src",url);
	});
});
	   </script>
	   <style>
        .error{
	       color:red;
           }
     </style>
	</head>
	<body>
		<form action="/RegistServlet" method="POST" id="onfocusout">
			<h1>欢迎注册EasyMall</h1>
			<table>
			    <tr>
			        <td colspan="2" style="text-align: center;color:red">
			           ${requestScope.errMsg}
			        </td>
			    </tr>
				<tr>
					<td class="tds">用户名：</td>
					<td>
						<input type="text" id="username" name="username" value="${param.username}"/>
					</td>
				</tr>
				<tr>
					<td class="tds">密码：</td>
					<td>
						<input type="password" id="password" name="password" value="${param.password}"/>
					</td>
				</tr>
				<tr>
					<td class="tds">确认密码：</td>
					<td>
						<input type="password" id="password2" name="password2" value="${param.password2}"/>
					</td>
				</tr>
				<tr>
					<td class="tds">昵称：</td>
					<td>
						<input type="text" id="nickname" name="nickname" value="${param.nickname}"/>
					</td>
				</tr>
				<tr>
					<td class="tds">邮箱：</td>
					<td>
						<input type="text" id="email" name="email" value="${param.email}"/>
					</td>
				</tr>
				<tr>
					<td class="tds">验证码：</td>
					<td>
						<input type="text" id="valistr" name="valistr"/>
						<img id="valiImage" src="/VerifyCodeServlet" width="" height="" alt="" />
					</td>
				</tr>
				<tr>
					<td class="sub_td" colspan="2" class="tds">
						<input type="submit" value="注册用户"/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
