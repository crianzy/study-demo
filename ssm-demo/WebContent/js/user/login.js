/**
 * @author tangjiandong
 * @deprecated 用户登录
 * @date 2012-6-19
 */
var login = {
	init : function() {
		login.submit();
		$("#username").focus();
	},
	/** 提交表单 */
	submit : function() {
		$("#UserLoginSubForm").bind("click", function() {
			$("#UserLoginForm").ajaxSubmit({
				dataType : "text",
				success : function(t) {
					t = t.toLowerCase().substring(t.indexOf(">") + 1).replace(
							"</pre>", "");
					if (t == '1') {
						alert("用户不能为空");
						$("#login_errors_div").show();
						$("#login_errors").html("用户不能为空");
						$("#username").focus();
					} else if (t == '2') {
						$("#login_errors_div").show();
						$("#login_errors").html("用户不存在，请重新输入");
						$("#username").focus();
					} else if (t == '3') {
				
						$("#login_errors_div").show();
						$("#login_errors").html("用户无效");
						$("#username").focus();
					} else if (t == '4') {
				
						$("#login_errors_div").show();
						$("#login_errors").html("密码不能为空");
						$("#password").focus();
					} else if (t == '5') {
					
						$("#login_errors_div").show();
						$("#login_errors").html("密码错误，请重新输入");
						$("#password").val("");
						$("#password").focus();
					} else if (t == "0000") {
						$("#login_errors_div").hide();
						$("#login_errors").html("");
						
						var fromurl = $.trim($("#fromurl").val());
						if (fromurl != null && fromurl != "") {
							location.href = fromurl; // 跳转到登录之前的页面
						} else {
							location.href = "default.do"; // 正常登录
						}
					} else if (t == "1111") {
						$("#login_errors_div").show();
						$("#login_errors").html("网络异常，请稍候重试");
					}
				},
				error : function() {
					$("#login_errors_div").show();
					$("#login_errors").html("网络异常，请稍候重试");
				}
			});
		});
	}
};

$(document).ready(function() {
			login.init();
		});