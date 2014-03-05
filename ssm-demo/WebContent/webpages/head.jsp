<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="common/taglib.jsp"%>
<!--通用头部-->
<div class="top">
	<div class="main clearfix ">
		<h1>
			<a href=""><img src="images/logo.jpg"></a>
		</h1>
		<div id="login_h_div" class="t_r2 t_r">
			欢迎您， <label id="userlogin_name">******</label> <a class="left20 p_re"
				href="#">通知<b><label id="notice_num">0</label></b></a><a
				class="left20" href="login!exit.do">退出</a>
		</div>
	</div>
</div>



<!--导航-->
<div class="nav">
	<div class="main">
		<a href="#">首页</a>
		<s:iterator value="menuList">
			<a ${nav == mid?"class='cur'":""} href="${actionurl}?nav=${mid}">${mname}</a>
		</s:iterator>

	</div>
</div>

