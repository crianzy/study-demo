$(function() {

	$("#btn_ok").bind("click", function() {
		var userInfo_realname=$("#userInfo_realname").val().trim();
		var userInfo_uphone=$("#userInfo_uphone").val().trim();
		var userInfo_remark=$("#userInfo_remark").val().trim();
		
		if (!$.base.onlyChchar(userInfo_realname)||userInfo_realname.length < 2
				||userInfo_realname.length > 5) {
			$("#errmsg").html("请填写正确的名字!");
			$("#error_msg").show();
			$("#userInfo_realname").focus();
			return;
		}
		if (userInfo_uphone.length>0){
			if(!$.base.isMobile(userInfo_uphone)
					&& !$.base.isTel(userInfo_uphone)) {
				$("#errmsg").html("请填写正确的电话号码!");
				$("#error_msg").show();
				$("#userInfo_uphone").focus();
				return;
			}
		}
		if(userInfo_remark.length < 1){
			$("#errmsg").html("请填写说明!");
			$("#error_msg").show();
			$("#userInfo_remark").focus();
			return;
		}
		$("#btn_ok").attr('disabled', true);
		// 添加判断
		$("#userEdit").ajaxSubmit({
			dataType : "text",
			success : function(t) {
				t = $.trim(t);
				$("#btn_ok").attr('disabled', false);
				if (t == "0000") {
					window.location.href= "user!list.do?nav="
						+ $('#nav').val() + "&lnav=" + $('#lnav').val();;
				} 
				else if (t == "1") {
					$("#errmsg").html("真实姓名为空!");
					$("#error_msg").show();
				} else if (t == "1111") {
					$("#errmsg").html("编辑失败!");
					$("#error_msg").show();
				}
			},
			error : function() {
				$("#errmsg").html("网络异常，请稍后重试!");
				$("#error_msg").show();
			}
		});
	});
});
 