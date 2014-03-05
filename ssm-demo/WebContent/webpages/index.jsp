<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="common/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" /> 
	<meta charset="utf-8">
	<title>首页</title>
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
	<%@ include file="head.jsp" %>
	首页
	<%@ include file="foot.jsp" %>

	
</body>
</html>
