<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
	<head>
		<style type="text/css">
			h1{
				text-align: center;
			}
		</style>
		<%
		  String imgurl = request.getParameter("imgurl");
		  request.getSession().setAttribute("imgurl", imgurl);
		 %>
	</head>
	<body>
		<h1>EasyMall_修改商品</h1>
		<hr>
			<form action="${app }/ManageUpdateProdServlet" method="POST" enctype="multipart/form-data">
			    <input type="text" hidden="hidden" name="id" value="${param.id }"/>
			    <input type="text" hidden="hidden" name="imgurl" value="${param.imgurl }"/>
				<table align="center" border="1" cellspacing="0px" cellpadding="5px">
				    <tr>
						<td colspan="2" style="text-align:center;color:red">
							${requestScope.errMsg}
						</td>
					</tr>
					<tr>
						<td>商品名称</td>
						<td><input type="text" name="name" value="${param.name }"/></td>
					</tr>
					<tr>
						<td>商品单价</td>
						<td><input type="text" name="price" value="${param.price }"/></td>
					</tr>
					<tr>
						<td>商品种类</td>
						<td><input type="text" name="cname" value="${param.cname }"/></td>
					</tr>
					<tr>
						<td>库存数量</td>
						<td><input type="text" name="pnum" value="${param.pnum }"/></td>
					</tr>
					<tr>
						<td>商品图片</td>
						<td><input type="file" name="fx"/></td>
					</tr>
					<tr>
						<td>描述信息</td>
						<td>
							<textarea rows="5" cols="30" name="description">${param.description }</textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="更新商品"/></td>
					</tr>
				</table>
			</form>
		<hr>
	</body>
</html>
