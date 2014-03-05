<div class="page">
	<#if parameters.pageNumber != 1><a href="javascript:void(0);" onclick="javascript:btnPage('${parameters.prePage}');" title="上一页">[上页]</a><#else><a href="javascript:void(0);" title="上一页">[上页]</a></#if>${parameters.pageNumber}/${parameters.pageCount}页<#if parameters.pageNumber != parameters.pageCount><a href="javascript:void(0);" onclick="javascript:btnPage('${parameters.nextPage}');" title="下一页">[下页]</a><#else><a href="javascript:void(0);" title="下一页">[下页]</a></#if>跳至<input class="txt" type="text" onkeyup="javascript:cpaging();" id="inputPage" />页<input class="go" type="button" value="确定" onclick="javascript:btnPage($('#inputPage').val());" />
</div>
<input type="hidden" id="formIdFlt${parameters.nonsynchronous}" value="${parameters.formId}"/>
<input type="hidden" id="pageCount${parameters.nonsynchronous}" value="${parameters.pageCount}"/>
<input type="hidden" id="pageNumber${parameters.nonsynchronous}" value="${parameters.pageNumber}"/>
<input type="hidden" id="nonsynchronous" value="${parameters.nonsynchronous}"/>
<input type="hidden" id="nondiv" value="${parameters.nondiv}"/>
<script>
	function btnPage(toPage) {
		if(null != toPage && toPage != ""){
			toPage = parseInt(toPage.replace(",",""));
			var nonsynchronous = $.trim($("#nonsynchronous").val());
			var pageNumber = parseInt($('#pageNumber' + nonsynchronous).val().replace(",",""));
			var pageCount = parseInt($('#pageCount' + nonsynchronous).val().replace(",",""));
			if (toPage == pageNumber) {
				// alert('已在当前页!');
			} else if (toPage > pageCount || toPage < 1) {
				// alert('没有此页!');
			} else {
				var form = $('#' + $('#formIdFlt' + nonsynchronous).val());
				$('#pageNumber' + nonsynchronous).val(toPage);
				var nnn = $("#nonsynchronous").val();
				var nondiv = $("#nondiv").val();
				if (null != nnn && nnn != '') {
					form.ajaxSubmit(function(t) {
								$("#" + $('#nondiv').val()).html(t);
							});
				} else {
					form.submit();
				}
			}
		}
	}
	function currpage() {
		// alert('已在当前页!');
	}
	function cpaging() {
		var patt = /^[0-9]+$/;
		if (!patt.exec($.trim($("#inputPage").val()))) {
			$("#inputPage").val($("#inputPage").val().replace(/[^0-9]/, ""));
		}
	}
</script>