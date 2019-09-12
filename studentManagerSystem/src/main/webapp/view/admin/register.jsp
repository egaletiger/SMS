<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册页面</title>
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
        input,a{
            width: 300px;
            height: 40px;
        }
        input[name='vcode']{
            width: 160px;
            vertical-align: middle;
        }

        #vcodeImg{
            width: 100px;
            height: 40px;
            vertical-align: middle;
        }
    </style>

    <!--自定义jQuery-->
    <script type="text/javascript">
        $(function(){
            $("#btn").click(function(){
                if(emptyCheck()){
                    $.messager.alert("提示", "有信息尚未填写完整", "warning");
                }else{
                    /*校对重复密码是否一致*/
                    if($(":input[name='rePassword']").val() != $(":input[name='password']").val()){
                        $.messager.alert("提示", "两次密码输入不一致", "warning");
                        $(":input[name='rePassword']").val("");
                        return false;
                    }
                    /* 校验验证码是否一致*/
                    if($(":input[name='vcode']").val() != "${sessionScope.vcode}"){
                        $.messager.alert("提示", "验证码输入错误", "warning");
                       	refresh();
                        return false;
                    }
                }
                
                $.ajax({
                    url:"${pageContext.request.contextPath}/admin/register",
                    type:"post",
                    data:$("#registerForm").serialize(),
                    dataType:"text",
                    success:function(result){
                        if(result == "yes"){
                            $.messager.alert("提示","注册成功","info");
                            $(":input[name='username']").val("");
                            $(":input[name='workNum']").val("");
                            refresh();
                        }else{
                        	 $.messager.alert("提示","已有该学号或职工号","warning");
                             $(":input[name='workNum']").val("");
                        }
                    },
                    error: function(){
                        $.messager.alert("提示","发送请求失败，请重新发送","warning");
                        refresh();
                    }
                });
                return false;
            });
            
            /*自定义校验规则*/
	        $.extend($.fn.validatebox.defaults.rules, { 
	            username: {// 验证姓名，中文
              			  	validator: function (value) {
                 		  	return /^[\Α-\￥]{2,4}$/.test(value);
              			 },
              		 message: '姓名输入有误'
           		},
				phone: {// 验证工号
	                validator: function (value) {
	                    return /^\d{2,6}$/.test(value);
	                },
	                message: '学号或职工号号码格式不正确'
	            }
	        }); 

    		
    		/*非空检查*/
    		function emptyCheck(node){
    			if($(node).val() == ""){
    				var msg = "";
    				if($(node).attr("name") == "username"){
    					msg = "姓名";
    				}else if($(node).attr("name") == "workNum"){
    					msg = "学好或职工号";
    				}else if($(node).attr("name") == "vcode"){
    					msg = "验证码";
    				} 
    				$.messager.alert("提示", msg+"不能为空", "warning");
    				return false;
    			}
    			return true;
    		}

            /*异步刷新验证码*/
            function refresh(){
                $("#vcodeImg").attr("src","${pageContext.request.contextPath}/admin/getVCodeImage?t=" + new Date().getTime());
                $(":input[name='vcode']").val("");
            };

            $("#vcodeImg").click(function(){
                refresh();
            });
        })
        
    </script>
</head>
<body  style="background-size: 100%,auto;">
	<div align="center">
		<div style="margin-top: 20px; height: 450px; width: 340px; border: solid; background-image: url(${pageContext.request.contextPath}/image/register.jpg);">
			<form id="registerForm" method="post" style="margin-top: 25px;"> 
				<input type="hidden" name="pwd" value="000"/><!-- 所有学生教师的初始密码为000 -->
			    <div>   
			        <input class="easyui-validatebox" placeholder="用户名" type="text" name="username" value="" data-options="validType:'username',missingMessage:'2-4个汉字'" />   
			    </div>
			  	<br />
			    <div>   
			        <input class="easyui-validatebox" placeholder="学号/职工号" type="text" name="workNum" value=""  data-options="validType:'phone',missingMessage:'2-6位数字'" />   
			    </div>
			    <br />
			    <div>
			    	<select class="easyui-combobox" name="institute" style="width:300px;height:40px;"> 
					    <option value="车辆工程学院">车辆工程学院</option>   
					    <option value="机械工程学院">机械工程学院</option>   
					    <option value="电子电气学院">电子电气学院</option>   
					    <option value="计算机技术学院">计算机技术学院</option>   
					    <option value="会计学院">会计学院</option>
					    <option value="管理学院">管理学院</option>
					    <option value="化学化工学院">化学化工学院</option>
					    <option value="外国语学院">外国语学院</option>
					    <option value="管理学院">管理学院</option>
					    <option value="生物科学学院">生物科学学院</option>
					</select>  
			    </div>
			    <br />
			    <div>
			    	<select class="easyui-combobox" name="identify" style="width:300px;height:40px;" > 
						    <option value="student" selected="true">学生</option>   
						    <option value="teacher">教师</option>     
					</select> 
				</div>
				<br />
			    <div>
			       <input name="vcode" class="easyui-validatebox" type="text" value="" placeholder="验证码" />
			       <img title="点击图片切换验证码" id="vcodeImg" src="${pageContext.request.contextPath}/admin/getVCodeImage?t="+new Data().getTime()>
			    </div>
			    <br />
			    <br />
			    <br />
			    <div>
			    	 <a id="btn" href="#" class="easyui-linkbutton">提 交</a> 
			    </div>
			</form>  
		</div>
	</div>
</body>
</html>
