<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>官网页面</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css">
</head>
<body style="background-color: black;">
<div align="center" style="margin-top: 100px;">
    <img src="${pageContext.request.contextPath}/image/logo.png" />
    <br />
    <br />
    <br />
    <br />
    <span style="font-family: '黑体'; font-size: 20px; font-weight: bold; color: red;">
	    		自强日新，明德笃行！
	    	</span>
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <a id="btn" href="${pageContext.request.contextPath}/view/public/login.jsp"></a>
</div>

<script type="text/javascript" >
    $('#btn').linkbutton({
        height:50,
        width:150,
        text:"进入数字化校园",
        plain:true
    });
</script>
</body>
</html>

