<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title>密码修改</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css"> 
		<style type="text/css">
			input,a{
				height: 30px;
				width: 150px;
			}
		</style>
		<script type="text/javascript">
			$(function(){
				//密码效验准则
		        $.extend($.fn.validatebox.defaults.rules, { 
            		password: {// 验证密码
		                validator: function (value,param) {
		                   var len = $.trim(value).length;
                		   return len >= param[0] && len <= param[1];
		                },
		                message: '密码长度不符合要求'
		            },
		            repassword: {// 重复密码对比
		                validator: function (value, param) {
		                   return value == $(param[0]).val();    
		                },
		                message: '两次输入的密码不一致'
		            }
			
		        }); 
				$("#btn").click(function(){
					/*检查密码是否填写完整*/
		    		$(":input[name='password']").blur(function(){
		    			if($(this).val() == ""){
		    				$.messager.alert("提示", "密码不能为空", "warning");
		    				return;
		    			}
		    		});
					
		    		/*检查重复密码是否与之前密码一致*/
		    		$(":input[name='repassword']").blur(function(){
	    				if($(this).val() != $(":input[name='password']").val()){
	    					$.messager.alert("提示", "两次密码输入不一致", "warning");
	    					return;
	    				}
		    		});
		    		var newPwd = $(":input[name='password']").val();
		    		var rid = ${sessionScope.user.rid};
		    		$.ajax({
		    			url:"${pageContext.request.contextPath}/common/upPwd",
		    			type:"post",
		    			data:{"newPwd":newPwd},
	                    success:function(){
	                           $.messager.alert("提示","修改完成","info")
	                    },
	                    error: function(){
	                        $.messager.alert("提示","发送请求失败，请重新发送","warning");
	                    }
		    		});
				});
			})
		</script>
	</head>
	<body>
		<div align="center" style="margin-top: 100px;">
			<div style="height: 200px;width: 300px;border: solid;">
				<form id="ff" method="post"> 
					<div style="margin-top: 20px;">   
					     <input class="easyui-validatebox" placeholder="新密码"  id="pwd" type="password" name="password" value="" data-options="validType:'password[6,12]',required:true,missingMessage:'6-12位字符'" />   
					</div>
					<br />
					<div>   
					     <input class="easyui-validatebox" placeholder="确认密码" type="password" name="repassword" value="" validType="repassword['#pwd']" data-options="required:true,missingMessage:'6-12位字符'" />   
					</div>
				    <br />
				    <div>
				    	<a id="btn" href="#" class="easyui-linkbutton">提交</a>  
				    </div>
				</form>  
			</div>
		</div>
	</body>
</html>
