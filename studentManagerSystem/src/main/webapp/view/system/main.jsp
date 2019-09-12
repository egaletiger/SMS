<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<html>
	<head>
		<meta charset="UTF-8">
		<title>用户界面</title>
		<script type="text/javascript" src="${pageContext.request.contextPath }/easyui/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/easyui/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/easyui/themes/icon.css"> 
		  <script type="text/javascript">
			$(function(){
				//加载用户头像
				<c:choose>
					<c:when test="${empty sessionScope.user.iconName}">
						$("#iconImg").attr("src","${pageContext.request.contextPath}/image/userIcon/default.png");
			        </c:when>
					<c:otherwise>
						$("#iconImg").attr("src","${pageContext.request.contextPath}/image/userIcon/${sessionScope.user.iconName}");
					</c:otherwise>
				</c:choose> 
				
				//建立用户工具栏
				$("#setting").tree({
					 	onClick:function(node){
						        	if(node.text == "密码修改"){
						        		$("#center").load("${pageContext.request.contextPath }/view/system/upPwd.jsp");
						        	}else if(node.text == "更换头像"){
						        		$("#center").load("${pageContext.request.contextPath }/view/system/iconUpload.jsp");
						        	}else if(node.text == "个人信息查看"){
						        		$("#center").load("${pageContext.request.contextPath }/view/system/personInfo.jsp");
						        	}else if(node.text == "添加用户"){
						        		$("#center").load("${pageContext.request.contextPath }/view/admin/register.jsp");
						        	}else if(node.text == "学生管理"){
						        		$("#center").load("${pageContext.request.contextPath }/view/admin/studentManager.jsp");
						        	}else if(node.text == "老师管理"){
						        		$("#center").load("${pageContext.request.contextPath }/view/admin/teacherManager.jsp");
						        	}else if(node.text == "学生信息查看"){
						        		$("#center").load("${pageContext.request.contextPath }/view/admin/studentManager.jsp");
						        	}
					 	},
                        data:[{  
						    "text":"个人信息管理",    
						    "iconCls":"icon-pim",    
						    "children":[{    
						        "text":"密码修改",    
						    },{
						        "text":"更换头像",    
						    },{
						        "text":"个人信息查看",    
						    }]    
						},{    
						    "text":"我的应用",    
						    "iconCls":"icon-application",
						    "children":[{
						    	<c:choose>
									<c:when test="${sessionScope.user.rid == 1}">
										"text":"老师管理"
						    			},{
										"text":"学生管理"
						    			},{
										"text":"添加用户"
							        </c:when>
							        <c:when test="${sessionScope.user.rid == 2}">
							        	"text":"学生信息查看" 
						        	</c:when>
									<c:otherwise>
										"text":"网上选课" 
									</c:otherwise>
								</c:choose> 
						    }] 
						}]
				});
				
				//获取当前时间
				var today = new Date();
				var day;
				switch(today.getDay()){
					case 0:
						day = "星期日";
						break;
					case 1:
						day = "星期一";
						break;
					case 2:
						day = "星期二";
						break;
					case 3:
						day = "星期三";
						break;
					case 4:
						day = "星期四";
						break;
					case 5:
						day = "星期五";
						break;
					case 6:
						day = "星期六";
						break;
					
				}
				var year = 1900+today.getYear();
				var month = today.getMonth()+1;
				var date = today.getDate();
				var time = "你好" + "${sessionScope.user.uname}" + "</br>" + year +"年"+ month +"月"+ date + "日" +"</br>" 
                                      +"&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;" +day;				
				$("#nowTime").html(time);
			});
			
		</script>
	</head>
	<body>
	    <div id="cc" class="easyui-layout" data-options="fit:true">   
		    <div id="north" data-options="region:'north',split:true,ollapsible:false" style="height: 100px;">
		    	<img id="iconImg" style="height: 80px;width: 80px;margin-left: 10px;margin-top: 5px;">
		    	<a href="${pageContext.request.contextPath}/common/logout" style="float:right; padding-right: 30px; padding-top: 5px;">退出</a>
		    	<span id="nowTime" style="float:right; padding-right: 30px; padding-top: 30px;">
		    	</span>
		    </div>   
		    <div data-options="region:'south',split:true,collapsible:false" style="height:100px;">
		    	<div align="center" style="margin-top:30px">
		    		<span> 
		    			花溪校区地址：重庆市巴南区红光大道69号 邮编：400054   杨家坪校区地址：重庆市杨家坪兴胜路4号   邮编：400050 
	 					Copyright ©重庆理工大学2010渝ICP备05001039号-1
		    		</span>
	    		</div>
		    </div>   
		    <div id="setting" data-options="region:'west',title:'工具栏',split:true" style="width:200px;"></div>   
		    <div id="center" data-options="region:'center',collapsible:false" style="padding:5px;background:#eee;"></div>   
		</div>  
	</body>
</html>
