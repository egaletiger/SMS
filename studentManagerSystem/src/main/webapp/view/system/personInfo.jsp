<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>个人信息页面</title>
<!--导入css文件-->
    <link rel="stylesheet" type="text/css" href="./easyui/themes/default/easyui.css">
	    <style type="text/css">
	    table{  
			border: 4px solid black;
			border-collapse:collapse;
		}
		td{  
			border: 2px solid black;
			text-align:center;
			vertical-align:middle;
		}
	    </style>
</head>
<body>
	<div align="center" style="margin-top: 100px">
		<table>
			<tr style="height: 60px;width:400px">
				<td style="width: 100px" colspan="2">个人信息</td>
				<td style="width: 100px" colspan="2">头像</td>
			</tr>
			<tr>
			<tr style="height: 60px;width:400px">
				<td style="width: 100px;">姓名</td>
				<td style="width: 100px">${sessionScope.user.uname}</td>
				<td rowspan="2" colspan="2">
					<div style="height: 200px; width: 200px; margin-top: 5px" align="center"  >
						<img height="190px" width="190px" src="${pageContext.request.contextPath }/image/userIcon/${sessionScope.user.iconName}">
					</div>
				</td>
			</tr>
				<td>学号</td>
				<td>${sessionScope.user.num}</td>
			</tr>
			<c:if test="${sessionScope.user.rid != 1 }">
				<tr>
					<td colspan="2">所在学院</td>
					<td colspan="2">${sessionScope.user.institute}</td>
				</tr>
			</c:if>
		</table>
	</div>
</body>
</html>