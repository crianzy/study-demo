<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="common/taglib.jsp" %>
<!--页脚-->

<!--页脚-->
<div class="footer">
	<p class="f1">祥祺世纪与你携手共创未来！</p>
    <p class="f2">Copyright &copy; 2014-2016 All Rights Reserved　粤ICP备10067299号</p>
</div>
<!-- 是否登录标识 -->
<input type="hidden" id="is_login_sign" value="0" />
<input type="hidden" id="login_no" />
<input type="hidden" id="nav"  value="${nav}"/>
<input type="hidden" id="lnav" value="${lnav}" />
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/common/header.js"></script>
<!-- 弹出框 -->
<script type="text/javascript" src="js/alertdiv.js"></script>
<div id="AContent" style="display:none;"></div><div id="AScreen" style="display:none;"></div>
