/**
 * @author caiqifan
 * @deprecated 找回密码
 * @date 2012-12-14
 */
var updatepwd = {
	errorimg : "<img src=\"../../images/error.gif\" />",
	sucimg : "<img src=\"../../images/suc.gif\" />",
	init : function() {
		updatepwd.submit();
		$("#password").focus();
	},
	/** 提交表单 */
	submit : function() {
		$("#UserPwdSubForm").bind("click", function() {
			$("#UserPwdForm").ajaxSubmit({
				dataType : "text",
				success : function(t) {
					t = t.toLowerCase().substring(t.indexOf(">") + 1).replace(
							"</pre>", "");
					if (t == '1') {
						$("#errors_div").show();
						$("#errors_msg").html(updatepwd.errorimg + "旧密码不能为空");
						$("#password").focus();
					} else if (t == "2") {
						$("#errors_div").show();
						$("#errors_msg").html(updatepwd.errorimg
								+ "旧密码错误，请重新输入");
						$("#password").val("");
						$("#password").focus();
					} else if (t == '3') {
						$("#errors_div").show();
						$("#errors_msg").html(updatepwd.errorimg + "新密码不能为空");
						$("#passwordnew").focus();
					} else if (t == '4') {
						$("#errors_div").show();
						$("#errors_msg").html(updatepwd.errorimg
								+ "新密码限6-16个字符，字母、数字或符号");
						$("#passwordnew").focus();
					} else if (t == '5') {
						$("#errors_div").show();
						$("#errors_msg").html(updatepwd.errorimg + "确认新密码不能为空");
						$("#passwordconfirm").focus();
					} else if (t == "6") {
						$("#errors_div").show();
						$("#errors_msg").html(updatepwd.errorimg
								+ "两次密码不一致，请重新输入");
						$("#passwordconfirm").val("");
						$("#passwordconfirm").focus();
					} else if (t == "0000") {
						$("#errors_div").hide();
						$("#errors_msg").html("");
						$("#suc_div").show();
						$("#suc_msg").html(updatepwd.sucimg + "修改成功");
						$("#UserPwdSubForm").attr("disabled", true);
					} else if (t == "1111") {
						$("#errors_div").show();
						$("#errors_msg")
								.html(updatepwd.errorimg + "网络异常，请稍后重试");
					}
				},
				error : function() {
					$("#errors_div").show();
					$("#errors_msg").html(login.errorimg + "网络异常，请稍后重试");
				}
			});
		});
	}
};

$(document).ready(function() {
			updatepwd.init();
		});