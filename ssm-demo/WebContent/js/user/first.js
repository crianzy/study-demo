/**
 * @author caiqifan
 * @deprecated 首次登录js
 * @date 2012-6-19
 */
var first = {
	errorimg : "<img src=\"../../images/error.gif\" />",
	init : function() {
		first.submit();
	},
	/** 提交注册表单 */
	submit : function() {
		$("#FirstSubForm").bind("click", function() {
			$("#FirstForm").ajaxSubmit({
				dataType : "text",
				success : function(t) {
					t = t.toLowerCase().substring(t.indexOf(">") + 1).replace(
							"</pre>", "");
					if (t == '1') {
						$("#errors_div").show();
						$("#errors_msg").html(first.errorimg + "用户名不能为空");
						$("#username").focus();
					} else if (t == '2') {
						$("#errors_div").show();
						$("#errors_msg").html(first.errorimg + "用户名已存在，请重新输入");
						$("#username").val("");
						$("#username").focus();
					} else if (t == '3') {
						$("#errors_div").show();
						$("#errors_msg").html(first.errorimg + "密码不能为空");
						$("#password").focus();
					} else if (t == '4') {
						$("#errors_div").show();
						$("#errors_msg").html(first.errorimg + "密码错误，请重新输入");
						$("#password").val("");
						$("#password").focus();
					} else if (t == '5') {
						$("#errors_div").show();
						$("#errors_msg").html(first.errorimg + "确认密码不能为空");
						$("#passwordconfirm").focus();
					} else if (t == "6") {
						$("#errors_div").show();
						$("#errors_msg").html(first.errorimg + "两次密码不一致，请重新输入");
						$("#passwordconfirm").val("");
						$("#passwordconfirm").focus();
					} else if (t == "0000") {
						$("#errors_div").hide();
						$("#errors_msg").html("");
						location.href = "default.do"; // 正常登录
					} else if (t == "1111") {
						$("#errors_div").show();
						$("#errors_msg").html(first.errorimg + "网络异常，请稍后重试");
					}
				},
				error : function() {
					$("#errors_div").show();
					$("#errors_msg").html(first.errorimg + "网络异常，请稍后重试");
				}
			});
		});
	}
};

$(document).ready(function() {
			first.init();
		});