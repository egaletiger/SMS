<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>头像上传</title>
		<!--导入jQuery插件-->
		<script type="text/javascript" src="${pageContext.request.contextPath }/easyui/jquery.min.js"></script>
		<script type="text/javascript">
			$(function(){
				<c:if test="${empty sessionScope.user.iconName}">
					$("#img").attr("src","${pageContext.request.contextPath}/image/userIcon/default.png");
				</c:if>
				<c:if test="${not empty sessionScope.user.iconName}">
					$("#img").attr("src","${pageContext.request.contextPath}/image/userIcon/${sessionScope.user.iconName}");
				</c:if>
				
				$("#ff").submit(function(){
					var file = $(":file")[0].files[0];//获取上传文件
					if(file == ""){
						return false;
					}
					//获取上传文件后缀
					var suffix = file.name.substring(file.name.lastIndexOf(".")).toLowerCase();
					if(suffix != ".png" && suffix != ".jpeg" && suffix != ".gif" && suffix != ".jpg"){
						alert("上传文件格式不正确！请重新上传");
						return false;
					}else{
						var formData = new FormData();
						formData.append("file",file);
						$.ajax({
							url:"${pageContext.request.contextPath}/common/iconImgUpload",
							type:"post",
							data:formData,
							contentType:false,
			                processData:false,
			                async:false,
			                cache: false,//文件不设置缓存
			                dataType:"text",
			                success: function(){
			                	alert("文件上传成功");
			                	$("#iconImg").attr("src","${pageContext.request.contextPath}/common/getIconImg");
			                },
			                error: function(){
			                	alert("网络繁忙请重试!");
			                }
						});
						
						return false;
					}
					
				});
			});
		</script>
</head>
<body>
	<div align="center" style="margin-top: 100px;">
		<div style="height: 300px;width: 300px;border: solid;">
			<div style="height: 120px;width: 120px;margin-top: 20px;">
					<img id="img" style="height: 120px;width: 120px;">
			</div>
			<br />
			<form id="ff" enctype="multipart/form-data">
				<div>   
				    <input type="file">
				    <br />   
				    (注意:图片格式只能为png,gif,jpeg,jpg)
				    <br />
				    <input type="submit" value="上传">
			    </div>
			</form>  
		</div>
	</div>
</body>
</html>