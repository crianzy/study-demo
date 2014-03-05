<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" /> 
	<meta charset="utf-8" />
	<title>商户登录</title>
	<link type="text/css" href="css/common.css" rel="stylesheet" />

</head>
<body>
	<!--商户头部-->
<div class="top">
    <div class="main clearfix ">
        <h1><a href="/"><img src="images/logo.jpg" /></a></h1>
    </div>
</div>
	
	<!--内容-->
	<div class="login_main">
		<div class="main">
			<div class="login_box">
				<form id="UserLoginForm" action="login!loginSub.do" method="post" onsubmit="return false">
			   		<input type="hidden" id="fromurl" name="fromurl" value="${fromurl}"/>
			    	<input class="txt" type="text" id="username" name="username" value="用户名/手机号" onblur="if(this.value == '') { this.value='用户名/手机号'}" onfocus="if(this.value == '用户名/手机号') { this.value='';this.value=''}" title="登录帐号" tabindex="1" />
			        <input class="txt" type="password" id="password" name="password" oncopy="return false" oncut="return false" onpaste="return false" title="密码" tabindex="2" />
			        <div class="error top8" id="login_errors_div" style="display:none;"><span id="login_errors"></span></div>
			        <div class="top8"><input class="bt1" type="submit" value="登录" id="UserLoginSubForm" /></div>
			        <div class="clear"></div>
			       
				</form>
		    </div>
	   	</div>
	</div>
	
	


	<%@ include file="../foot.jsp" %>
	<script type="text/javascript" src="js/user/login.js"></script>
</body>
</html>
