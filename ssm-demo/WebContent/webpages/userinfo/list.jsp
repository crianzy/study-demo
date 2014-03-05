<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<!doctype html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" /> 
	<meta charset="utf-8">
	<title>用户列表</title>
	<link rel="stylesheet" type="text/css" href="../../css/common.css">

</head>
<body>
	<%@ include file="../head.jsp" %>
	
<!--内容-->
<div class="main_bg">
	<div class="admin_box">
    	<div class="menu kefu_menu">
        	<b></b>
        	<ul>
                <s:iterator value="lmenuList">
                  <li ${lnav == mid?"class='cur'":""}><a  href="${actionurl}?nav=${nav}&lnav=${mid}">${mname}</a></li>
               </s:iterator>
            </ul>
        </div>


    <div class="box_r info">
	   <form name="app_list_form" id="app_list_form" method="post" action="userinfo!list.do?nav=${nav}&lnav=${lnav}">
		    <input type="hidden" id="pageNumber" name="pageNumber" value="${rd.pageNumber}" />
            <div class="hd">
            	<em>用户信息</em><a class="hd_add" href="coupon!list.do?nav=${nav}&lnav=${mid}">用户下载优惠</a>
            </div>
            <div class="tip">
                                   手机号： <input class="mobile" name="mobile" id="mobile" type="text">
                                注册时间：<input class="mobile" name="starttime" type="text" id="starttime" size="10" maxlength="10" onclick="new Calendar().show(this);" readonly="readonly" /> 
				 <span>到</span>
				 <input class="mobile" name="endtime" type="text" id="endtime" size="10" maxlength="10" onclick="new Calendar().show(this);" readonly="readonly" /> 
		              昵称: <input class="mobile" name="username" id="username" type="text"><br>
				 <div class="right">
				 <input class="go1" name="" value="查询" type="button" onclick="on_search()">
				 <input class="go1" name="" value="清空" type="button" onclick="on_clean()">
				 </div>
			</div>
            <div class="kefu">
            <table width="780" border="0" cellspacing="0" cellpadding="0" class="w">
            <thead>
              	<tr>
					<th scope="col">注册日期</th>
					<th scope="col">手机号</th>
					<th scope="col">昵称</th>
					<th scope="col">性别</th>
					<th scope="col">地区</th>
					<th scope="col">状态</th>
				</tr>
              </thead>
              <tbody>
             
              <s:if test="null != rd.resultlist && rd.resultlist.size() > 0">
	          	<s:iterator value="rd.resultlist" status="v">
              <tr>
                <td><s:date name="regdate"  format="yyyy-MM-dd"></s:date></td>
                <td>${mobile}</td>
                <td>${username}</td>
                <td>${usex}</td>
                <td>${province}${city}${area}</td>
                <td>${isbandpapers}</td>
              </tr>
              </s:iterator>
              </s:if>
              <s:else>
                  <tr>
                   <td colspan="7">
					 <div class="no_data"><img src="../../images/no_data.gif"></div>
		 		   </td>
		 		</tr>
		 		</s:else>
              </tbody>
            </table>
			</div>

              <div class="page">
              <s:if test="null != rd.resultlist && rd.resultlist.size() > 0">
                <app:paging pageNumber="${rd.pageNumber}" pageCount="${rd.pageCount}" recordCount="${rd.recordCount}" formId="app_list_form" ></app:paging>
                </s:if><s:else>
                <a href="">[上页]</a>1/72页<a href="">[下页]</a>跳至<input type="text" name="" class="txt">页<input type="button" value="确定" name="" class="go">
                </s:else>
            </div>
            
            </form>
        </div>
    </div>
</div>

	<%@ include file="../foot.jsp" %>
	<script  type="text/javascript">
	$(function(){
		$('#mobile').val('${mobile}');
		$('#starttime').val('${starttime}');
		$('#endtime').val('${endtime}');
		$('#username').val('${username}');
	});
function on_search(){
	$('#pageNumber').val(1);
	$('#app_list_form').submit();
}
function on_clean(){
	$('#mobile').val("");
	$('#starttime').val("");
	$('#endtime').val("");
	$('#username').val("");
}
</script>
<script type="text/javascript" src="../../js/calendar3.js"></script>
</body>
</html>
