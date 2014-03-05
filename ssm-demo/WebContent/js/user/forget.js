/**
 * @author caiqifan
 * @deprecated 找回密码
 * @date 2012-12-14
 */
var forget = {
	errorimg : "<img src=\"../../images/error.gif\" />",
	init : function() {
		forget.send();
		forget.submit();
		$("#mobile").focus();
	},
	/** 获取短信验证码 */
	send : function() {
		$("#sms_send").bind("click", function() {
			$.get(	"forget!sendSmsCode.do?mobile=" + $("#mobile").val()
							+ "&d=" + new Date().getTime(), function(t) {
						if (t == "1") {
							$("#errors_div").show();
							$("#errors_msg").html(forget.errorimg + "手机号码不能为空");
							$("#mobile").focus();
						} else if (t == '2') {
							$("#errors_div").show();
							$("#errors_msg").html(forget.errorimg
									+ "手机号码格式错误，请重新输入");
							$("#mobile").val("");
							$("#mobile").focus();
						} else if (t == '3') {
							$("#errors_div").show();
							$("#errors_msg").html(forget.errorimg
									+ "手机号码不存在，请重新输入");
							$("#mobile").val("");
							$("#mobile").focus();
						} else if (t == '0000') {
							$("#sms_send").hide();
							$("#sms_show").show();
							forget.remaintime(60);
						} else if (t == '1111') {
							$("#errors_div").show();
							$("#errors_msg").html(forget.errorimg
									+ "网络异常，请稍后重试");
						}
					});
		});
	},
	/** 验证码有效期倒计时 */
	remaintime : function(second) {
		if (second == 1) {
			clearTimeout(SmsTime);
			$("#sms_show").hide();
			$("#sms_send").show();
		} else {
			second = second - 1;
			$("#sms_time").html(second);
			SmsTime = setTimeout("forget.remaintime(" + second + ")", 1000);
		}
	},
	/** 提交表单 */
	submit : function() {
		$("#ForgetSubForm").bind("click", function() {
			$("#ForgetForm").ajaxSubmit({
				dataType : "text",
				success : function(t) {
					t = t.toLowerCase().substring(t.indexOf(">") + 1).replace(
							"</pre>", "");
					if (t == '1') {
						$("#errors_div").show();
						$("#errors_msg").html(forget.errorimg + "手机号码不能为空");
						$("#mobile").focus();
					} else if (t == '2') {
						$("#errors_div").show();
						$("#errors_msg").html(forget.errorimg
								+ "手机号码格式错误，请重新输入");
						$("#mobile").val("");
						$("#mobile").focus();
					} else if (t == '3') {
						$("#errors_div").show();
						$("#errors_msg")
								.html(forget.errorimg + "手机号码不存在，请重新输入");
						$("#mobile").val("");
						$("#mobile").focus();
					} else if (t == '4') {
						$("#errors_div").show();
						$("#errors_msg").html(forget.errorimg + "新密码不能为空");
						$("#password").focus();
					} else if (t == '5') {
						$("#errors_div").show();
						$("#errors_msg").html(forget.errorimg
								+ "新密码限6-16个字符，字母、数字或符号");
						$("#password").focus();
					} else if (t == '6') {
						$("#errors_div").show();
						$("#errors_msg").html(forget.errorimg + "确认新密码不能为空");
						$("#passwordconfirm").focus();
					} else if (t == "7") {
						$("#errors_div").show();
						$("#errors_msg")
								.html(forget.errorimg + "两次密码不一致，请重新输入");
						$("#passwordconfirm").val("");
						$("#passwordconfirm").focus();
					} else if (t == '8') {
						$("#errors_div").show();
						$("#errors_msg").html(forget.errorimg + "短信验证码不能为空");
						$("#smscode").focus();
					} else if (t == '9') {
						$("#errors_div").show();
						$("#errors_msg").html(forget.errorimg + "请点击获取短信验证码");
						$("#smscode").val("");
						$("#smscode").focus();
					} else if (t == '10') {
						$("#errors_div").show();
						$("#errors_msg").html(forget.errorimg
								+ "短信验证码已过期，请点击重新获取验证码");
						$("#smscode").val("");
						$("#smscode").focus();
					} else if (t == '11') {
						$("#errors_div").show();
						$("#errors_msg")
								.html(forget.errorimg + "短信验证码错误，请重新输入");
						$("#smscode").val("");
						$("#smscode").focus();
					} else if (t == "0000") {
						$("#errors_div").hide();
						$("#errors_msg").html("");
						window.location.href = "login!index.do";
					} else if (t == "1111") {
						$("#errors_div").show();
						$("#errors_msg").html(forget.errorimg + "网络异常，请稍后重试");
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
			forget.init();
		});