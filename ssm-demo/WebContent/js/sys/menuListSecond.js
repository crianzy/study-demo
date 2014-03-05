function sortUp(mid, pid) {
	$.ajax({
				type : "get",
				url : "menu!sortUp.do?menuInfo.mid=" + mid + "&pid=" + pid,
				dataType : "text",
				success : function(t) {
					t = $.trim(t);
					if (t == "0000") {
						location.reload();
					} else {
						alert("操作失败");
					}
				}
			});
}

function sortDown(mid, pid) {
	$.ajax({
				type : "get",
				url : "menu!sortDown.do?menuInfo.mid=" + mid + "&pid=" + pid,
				dataType : "text",
				success : function(t) {
					t = $.trim(t);
					if (t == "0000") {
						location.reload();
					} else {
						alert("操作失败");
					}
				}
			});
}