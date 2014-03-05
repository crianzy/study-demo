$(function() {

	$("#btn_ok").bind("click", function() {
		var pid = $("#pid").val();
		var menuInfo_mname = $("#menuInfo_mname").val().trim();
		var menuInfo_mdesc = $("#menuInfo_mdesc").val().trim();
						// var menuInfo_actionurl =
						// $("#menuInfo_actionurl").val().trim();
						// var menuInfo_remark =
						// $("#menuInfo_remark").val().trim();
		if (menuInfo_mname.length > 10 || menuInfo_mname.length < 2) {
			$("#errmsg").html("菜单名称长度为2-10");
			$("#error_msg").show();
			$("#menuInfo_mname").focus();
			return;
		}
		if (menuInfo_mdesc.length < 2) {
			$("#errmsg").html("菜单描述名称长度为至少为2");
			$("#error_msg").show();
			$("#menuInfo_mdesc").focus();
			return;
		}
		// if (menuInfo_remark.length < 2) {
		// $("#errmsg").html("菜单备注长度至少为2-10");
		// $("#error_msg").show();
		// $("#menuInfo_remark").focus();
		// return;
		// }

		$("#btn_ok").attr('disabled', true);
		// 添加判断
		$("#menuCreate").ajaxSubmit({
			dataType : "text",
			success : function(t) {
				t = $.trim(t);
				$("#btn_ok").attr('disabled', false);
				if (t == "0000") {
					if (pid == 0) {
						window.location.href = "menu!list.do?nav="
								+ $('#nav').val() + "&lnav=" + $('#lnav').val(); // 一级菜单
					} else {
						window.location.href = "menu!listSecond.do?nav="
								+ $('#nav').val() + "&lnav=" + $('#lnav').val()
								+ "&pid=" + pid; // 二级菜单
					}
				} else if (t == "1111") {
					$("#errmsg").html("网络异常，请稍后重试!");
					$("#error_msg").show();
				} else if (t == "0") {
					$("#errmsg").html("父菜单编号为空!");
					$("#error_msg").show();
				} else if (t == "1") {
					$("#errmsg").html("菜单名称为空!");
					$("#error_msg").show();
					$("#menuInfo_mname").focus();
				} else if (t == "2") {
					$("#errmsg").html("菜单描述为空!");
					$("#error_msg").show();
					$("#menuInfo_mdesc").focus();
				} else if (t == "4") {
					$("#errmsg").html("菜单名称已存在!");
					$("#error_msg").show();
					$("#menuInfo_mname").focus();
				}
			},
			error : function() {
				$("#errmsg").html("网络异常，请稍后重试!");
				$("#error_msg").show();

			}
		});
	});
});