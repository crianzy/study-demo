/**
 * @author caiqifan
 * @deprecated 用户登录
 * @date 2012-6-19
 */
var login = {
	errorimg : "<img src=\"../../images/error.gif\" />",
	init : function() {
		login.submit();
		login.gotoJoin();
	},
	/** 跳转企业申请 */
	gotoJoin : function() {
		$("#btnJoin").bind("click", function() {
					$('#shopjoinForm').submit();
				});
	},
	/** 提交注册表单 */
	submit : function() {
		$("#UserLoginSubForm").bind("click", function() {
			$("#UserLoginForm").ajaxSubmit({
				dataType : "text",
				success : function(t) {
					t = t.toLowerCase().substring(t.indexOf(">") + 1).replace(
							"</pre>", "");
					if (t == '1') {
						$("#login_errors_div").show();
						$("#login_errors").html(login.errorimg + "登录帐号不能为空");
						$("#username").focus();
					} else if (t == '2') {
						$("#login_errors_div").show();
						$("#login_errors").html(login.errorimg
								+ "登录帐号不存在，请重新输入");
						$("#username").val("");
						$("#username").focus();
					} else if (t == '3') {
						$("#login_errors_div").show();
						$("#login_errors").html(login.errorimg + "登录帐号已冻结");
						$("#username").focus();
					} else if (t == '4') {
						$("#login_errors_div").show();
						$("#login_errors").html(login.errorimg + "登录帐号已锁定");
						$("#username").focus();
					} else if (t == '5') {
						$("#login_errors_div").show();
						$("#login_errors").html(login.errorimg + "登录帐号已关闭");
						$("#username").focus();
					} else if (t == '6') {
						$("#login_errors_div").show();
						$("#login_errors").html(login.errorimg + "密码不能为空");
						$("#password").focus();
					} else if (t == '7') {
						$("#login_errors_div").show();
						$("#login_errors").html(login.errorimg + "密码错误，请重新输入");
						$("#password").val("");
						$("#password").focus();
					} else if (t == "2222") {
						$("#login_errors_div").hide();
						$("#login_errors").html("");
						location.href = "user!first.do"; // 首次登录
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
						$("#login_errors").html(login.errorimg + "网络异常，请稍后重试");
					}
				},
				error : function() {
					$("#login_errors_div").show();
					$("#login_errors").html(login.errorimg + "网络异常，请稍后重试");
				}
			});
		});
	}
};

$(document).ready(function() {
			login.init();
		});