<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>老师管理页面</title>
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
	<!--自定义js-->
		<script type="text/javascript">
		//设置删除按钮事件
		function del(param){
			var rows = $("#dg").datagrid("getRows");//获取所有行
			var index = $(param).parents('.datagrid-row').attr('datagrid-row-index');//获取当前行号
			var id = rows[index].id;
			 $.ajax({
				url:"${pageContext.request.contextPath}/admin/delTeacher",
				type:"post",
				data:{'id':id},
				success:function(){
					alert("删除成功");
					$("#dg").datagrid("deleteRow",index);
				},
				error:function(){
					alert("网络繁忙,请重试");
				}
			}) 
		};
		
		//设置编辑按钮事件
		var can = true;//设置标记，限制每次只能修改一行
		function edit(param){
			if(can == true){
				can = false;
				var index = $(param).parents('.datagrid-row').attr('datagrid-row-index');//获取当前行号
				$("#dg").datagrid('beginEdit', index);
				$("#dg").datagrid("getEditor",{
					index:index,
				});
				$("a[name='save']").eq(index).css("display","block");
			}else{
				alert("请先保存数据，在进行此次编辑");
			}
		}
		//设置保存更改事件
		function save(param){
			can = true;
			var rows = $("#dg").datagrid('getRows');
			var index = $(param).parents('.datagrid-row').attr('datagrid-row-index');//获取当前行号
			$("#dg").datagrid('endEdit', index);
			var id = rows[index].id;
			var num = rows[index].workNum;
			var uname = rows[index].uname;
			var institute = rows[index].institute;
			$.ajax({
				url:"${pageContext.request.contextPath}/admin/updTeacher",
				type:"post",
				contenType:'application/json;charset=utf-8',
				data:{"id":id,"num":num,"uname":uname,"institute":institute},
				success:function(){
					alert("修改成功");
					$("a[name='save']").eq(index).css("display","none");
				},
				error:function(){
					alert("网络繁忙，请重试");
					$("#dg").datagrid('beginEdit', index);
					$("#dg").datagrid("getEditor",{
						index:index
					});
				}
			});
		}
		$(function(){
			$('#dg').datagrid({   
				url:"${pageContext.request.contextPath}/admin/showTeachers",
			    columns:[[    
			    	{field:'id',title:'ID号',width:50,hidden:true,align:"center"},
			    	{field:'box',width:50,hidden:true,align:"center"},
			        {field:'workNum',title:'职工号',width:100,align:"center",editor:{type:'text'}},    
			        {field:'uname',title:'教师姓名',width:100,align:"center",editor:{type:'text'}},    
			        {field:'institute',title:'所在学院',width:200,align:"center",editor:{type:'text'}},
			       	{field:'operation',title:'操作',width:300,align:"center"}
			    ]],
			    pagination:true,
			    pagePosition:"bottom",
			    pageSize:3,//设置默认分页大小
			    pageList:[3,6,9,12,15]
			}); 
			
			//批量操作按钮
			$("#manyBtn").click(function(){
				$("#manyBtn").css("display","none");
				$("#dg").datagrid("showColumn","box");
				$("#quitBtn").css("display","block");
				$("#allSel").css("display","block");
				$("#allCancel").css("display","block");
				$("#delBtn").css("display","block");
			});
			//退出批量操作按钮
			$("#quitBtn").click(function(){
				$("#quitBtn").css("display","none");
				$("#dg").datagrid("hideColumn","box");
				$("#allSel").css("display","none");
				$("#allCancel").css("display","none");
				$("#delBtn").css("display","none");
				$("#manyBtn").css("display","block");
			});
			
			//选中所有行
			$("#allSel").click(function(){
				$(":checkbox").prop("checked",true);
			});
			//取消所有行
			$("#allCancel").click(function(){
				$(":checkbox").prop("checked",false);
			});
			//删除选中行
			$("#delBtn").click(function(){
				var boxs = $(":checkbox");
				var arr = [];
				var indexs = [];
				var k = 0;
				for(var i = 0; i < boxs.length; i++){
					if(boxs[i].checked){
						var index = $(boxs[i]).parents('.datagrid-row').attr('datagrid-row-index');//获取当前行号
						var id = $("#dg").datagrid("getRows")[index].id;//获取id号
						indexs[k] = index;
						arr[k++] = id;
					}
				}
				if(arr.length != 0){
					$.ajax({
						url:"${pageContext.request.contextPath}/admin/delTeachers",
						type:"post",
						data:{"arr":arr},
						traditional:true,
						success:function(){
							alert("删除成功");
							for(var i = 0; i < indexs.length; i++){
								$("#dg").datagrid("deleteRow",indexs[i]);
							}
						},
						error:function(){
							alert("网络繁忙,请重试");
						}
					});
				}else{
					alert("你还没有选中要删除的内容");
				}
			});
			
			//根据职工号查询按钮事件
			$("#numSearchBtn").click(function(){
				var num = $("#numSearch").val();
				if(num == ""){
					alert("搜索内容不能为空！");
				}else{
					var op = $("#dg").datagrid("options");
					op.url = "${pageContext.request.contextPath}/admin/selTeachersByNum?num="+num;
					$("#dg").datagrid("reload");
					$("#nameSearch").textbox('setValue','');
				}
			});
			
			//根据教师姓名查询按钮事件
			$("#nameSearchBtn").click(function(){
				var uname = $("#nameSearch").val();
				if(uname == ""){
					alert("搜索内容不能为空！");
				}else{
					var op = $("#dg").datagrid("options");
					op.url = "${pageContext.request.contextPath}/admin/selTeachersByUname?uname="+uname;
					$("#dg").datagrid("reload");
					$("#nameSearch").textbox('setValue','');
				}
			});	
			
			//全部查询按钮事件
			$("#allSearchBtn").click(function(){
				var op = $("#dg").datagrid("options");
				op.url = "${pageContext.request.contextPath}/admin/showTeachers";
				$("#dg").datagrid("reload");
			});	
		});
	</script>
</head>
<body>
	<div align="center" style="height: 100px;width: 700px;margin-top: 50px;">
		<a id="allSearchBtn" class="easyui-linkbutton">显示全部</a>
		<input  id="numSearch" class="easyui-textbox"/><a id="numSearchBtn" class="easyui-linkbutton">根据职工号查询</a>
		<input  id="nameSearch" class="easyui-textbox"/><a id="nameSearchBtn" class="easyui-linkbutton">根据姓名查询</a>
	</div>
	<div align="center" style="width: 700px;">
		
		<a id="quitBtn" class="easyui-linkbutton" href="#" style="display: none;width: 100px;float: right;">退出操作</a>
		<a id="manyBtn" class="easyui-linkbutton" href="#" style="width: 100px;float: right;">批量操作</a>
		<a id="allSel" class="easyui-linkbutton" href="#" style="display: none;width: 100px;float: right;">全部选中</a>
		<a id="allCancel" class="easyui-linkbutton" href="#" style="display: none;width: 100px;float: right;">全部取消</a>
		<a id="delBtn" class="easyui-linkbutton" href="#" style="display: none;width: 100px;float: right;">批量删除</a>
		<table id="dg" style="width: 700px;" data-options="fitColumns:true,singleSelect:true,scrollbarSize:0"></table>
	</div>
</body>
</html>