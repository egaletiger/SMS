<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>错误页面</title>
		<!--导入jQuery插件-->
		<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>
		<script type="text/javascript">
			$(function(){
				var num = 9;
				setInterval(function(){
					$("#num").text(num);
					if(num > 0){
						num--;
					}else{
						this.window.location.href = "${pageContext.request.contextPath}/view/public/login.jsp";
					}
					
				},1000);
			})
		</script>
	</head>
	<body>
		<div align="center" style="margin-top: 200px;">
			用户名或密码错误！<span id="num" style="font-weight: bold;font-size: 30px;color: red;">10</span>秒后返回登录页面！
		</div>
	</body>
</html>
