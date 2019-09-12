<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>登录页面</title>
    <!--导入jQuery插件-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>
    <!--导入easyui框架-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
    <!--汉化-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>
    <!--导入css文件-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">
    <!--导入图标css文件-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css">

    <!--自定义css-->
	    <style type="text/css">
	    	/*input表单组件长宽设置*/
	    	input{
	    		width: 200px;
	    		height: 80px;
	    	}
	    	input[name='rememberMe']{
	    		width: 15px;
	    		height: 15px;
	    	}

	    	/*账户输入框与密码输入框边框设置*/
	    	#inp1,#inp2{
	    		border-top: hidden;
	    		border-left: hidden;
	    		border-right: hidden;
	    		border-bottom: solid;
	    	}
	    	
	    	/*超链接样式设置*/
	    	.href{
	    		text-decoration:none;
	    	}
	    </style>
		<script type="text/javascript">
			$(function(){
				$('#btn').linkbutton({    
					width:100,
	 				height:30,
	 				text:"登    录",
				});  
				$("#btn").click(function(){
					if($(":checkbox").checked){
						$(":checkbox").attr("value","yes");
					}
					$("#ff").submit();
				});   
			})
		</script>
	
	<body  style="background-color: green;background-size: 100%,100%;">
		<div style="margin-top: 200px;">
			<div align="center" style="margin-top: 10px;">
				<label style="font-family: '微软雅黑'; font-weight: bolder; font-size: 90;color: cadetblue;">登录通道</label>
				<form id="ff" method="post" action="${pageContext.request.contextPath}/common/login">
					<table width="200" cellspacing="5">
						<tr>
							<td><input id="inp1"  class="easyui-textbox" type="text"  name="username" value="" data-options="iconCls:'icon-man',iconAlign:'left',prompt:'学号/职工号'"/></td>
						</tr>
						<tr>
							<td><input id="inp2" class="easyui-textbox" type="password"  placeholder="密码" name="password" value="" data-options="iconCls:'icon-lock',iconAlign:'left',prompt:'首次登录密码:000'"/></td>
						</tr>
						<tr>
							<td align="center">
								<select class="easyui-combobox" name="identify" style="width:100px;height:30px;" > 
								    <option value="student" selected="true">学生</option>   
								    <option value="teacher">教师</option>   
								    <option value="admin">管理员</option>   
								</select> 
							</td>
						</tr>
						<tr>
							<td align="center"><a id="btn" href="#"></a></td>
						</tr>
					</table>
				</form>
			</div> 
		</div>
	</body>
</html>