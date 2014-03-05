/**
 * @author caiqifan
 * @deprecated 用户信息js
 * @date 2012-12-14
 */
var userinfo = {
	errorimg : "<img src=\"../../images/error.gif\" />",
	sucimg : "<img src=\"../../images/suc.gif\" />",
	init : function() {
		userinfo.send();
		userinfo.mobileSubmit();
		userinfo.infoSubmit();
	},
	/** 获取短信验证码 */
	send : function() {
		$("#sms_send").bind("click", function() {
			$.get(	"user!sendSmsCode.do?mobile=" + $("#mobile").val() + "&d="
							+ new Date().getTime(), function(t) {
						if (t == "1") {
							$("#errors_div").show();
							$("#errors_msg").html(userinfo.errorimg
									+ "手机号码不能为空");
							$("#mobile").focus();
						} else if (t == '2') {
							$("#errors_div").show();
							$("#errors_msg").html(userinfo.errorimg
									+ "手机号码格式错误，请重新输入");
							$("#mobile").val("");
							$("#mobile").focus();
						} else if (t == '3') {
							$("#errors_div").show();
							$("#errors_msg").html(userinfo.errorimg
									+ "手机号码已存在，请重新输入");
							$("#mobile").val("");
							$("#mobile").focus();
						} else if (t == '0000') {
							$("#sms_send").hide();
							$("#sms_show").show();
							userinfo.remaintime(60);
						} else if (t == '1111') {
							$("#errors_div").show();
							$("#errors_msg").html(userinfo.errorimg
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
			SmsTime = setTimeout("userinfo.remaintime(" + second + ")", 1000);
		}
	},
	/** 手机号码-提交表单 */
	mobileSubmit : function() {
		$("#MobileSubForm").bind("click", function() {
			$("#MobileForm").ajaxSubmit({
				dataType : "text",
				success : function(t) {
					t = t.toLowerCase().substring(t.indexOf(">") + 1).replace(
							"</pre>", "");
					if (t == "1") {
						$("#errors_div").show();
						$("#errors_msg").html(userinfo.errorimg + "手机号码不能为空");
						$("#mobile").focus();
					} else if (t == '2') {
						$("#errors_div").show();
						$("#errors_msg").html(userinfo.errorimg
								+ "手机号码格式错误，请重新输入");
						$("#mobile").val("");
						$("#mobile").focus();
					} else if (t == '3') {
						$("#errors_div").show();
						$("#errors_msg").html(userinfo.errorimg
								+ "手机号码已存在，请重新输入");
						$("#mobile").val("");
						$("#mobile").focus();
					} else if (t == '4') {
						$("#errors_div").show();
						$("#errors_msg").html(userinfo.errorimg + "短信验证码不能为空");
						$("#smscode").focus();
					} else if (t == '5') {
						$("#errors_div").show();
						$("#errors_msg").html(userinfo.errorimg + "请点击获取短信验证码");
						$("#smscode").val("");
						$("#smscode").focus();
					} else if (t == '6') {
						$("#errors_div").show();
						$("#errors_msg").html(userinfo.errorimg
								+ "短信验证码已过期，请点击重新获取验证码");
						$("#smscode").val("");
						$("#smscode").focus();
					} else if (t == '7') {
						$("#errors_div").show();
						$("#errors_msg").html(userinfo.errorimg
								+ "短信验证码错误，请重新输入");
						$("#smscode").val("");
						$("#smscode").focus();
					} else if (t == '8') {
						$("#errors_div").show();
						$("#errors_msg").html(userinfo.errorimg + "密码不能为空");
						$("#password").focus();
					} else if (t == '9') {
						$("#errors_div").show();
						$("#errors_msg").html(userinfo.errorimg + "密码错误，请重新输入");
						$("#password").val("");
						$("#password").focus();
					} else if (t == "0000") {
						$("#errors_div").hide();
						$("#errors_msg").html("");
						location.href = "user!info.do?nav=11";
					} else if (t == "1111") {
						$("#errors_div").show();
						$("#errors_msg").html(userinfo.errorimg + "网络异常，请稍后重试");
					}
				},
				error : function() {
					$("#errors_div").show();
					$("#errors_msg").html(login.errorimg + "网络异常，请稍后重试");
				}
			});
		});
	},
	/** 个人资料-提交表单 */
	infoSubmit : function() {
		/** 真实姓名修改*/
		$("#UserSubForm").bind("click", function() {
			$("#UserForm").ajaxSubmit({
				dataType : "text",
				success : function(t) {
					t = t.toLowerCase().substring(t.indexOf(">") + 1).replace(
							"</pre>", "");
					if (t == "1") {
						$("#errors_div1").show();
						$("#errors_msg1").html(userinfo.errorimg + "真实姓名不能为空");
						$("#realname").focus();
					} else if (t == "0000") {
						$("#errors_div1").hide();
						$("#errors_msg1").html("");
						location.reload(); 
						$('.show_tr1').hide();
					} else if (t == "1111") {
						$("#errors_div1").show();
						$("#errors_msg1")
								.html(userinfo.errorimg + "网络异常，请稍后重试");
					}
				},
				error : function() {
					$("#errors_div1").show();
					$("#errors_msg1").html(login.errorimg + "网络异常，请稍后重试");
				}
			});
		});
	}
};
/** 密码重置*/
$("#RsetPwdForm").bind("click", function() {
	$("#PwdForm").ajaxSubmit({
		dataType : "text",
		success : function(t) {
			t = t.toLowerCase().substring(t.indexOf(">") + 1).replace(
					"</pre>", "");
			if (t == "1") {
				$("#errors_div2").show();
				$("#errors_msg2").html(userinfo.errorimg + "密码不能为空");
				$("#password").focus();
			} else if (t == "0000") {
				$("#errors_div2").hide();
				$("#errors_msg2").html("");
				location.reload(); 
				$('.show_tr2').hide();
			} else if (t == "1111") {
				$("#errors_div2").show();
				$("#errors_msg2")
						.html(userinfo.errorimg + "网络异常，请稍后重试");
			}
		},
		error : function() {
			$("#errors_div2").show();
			$("#errors_msg2").html(login.errorimg + "网络异常，请稍后重试");
		}
	});
});
$(document).ready(function() {
			userinfo.init();
		});