<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>化学试剂管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
		
		function info(reagentId) {
			layer.open({
				type: 2,
				title: '试剂查看',
				shadeClose: true,
				shade: 0.8,
				area: ['80%', '80%'],
				content: '${ctx}/work/reagent/view?id='+reagentId
			});
		}
		
		function subit(state) {
			$("#state").val(state);
			$("#inputForm").submit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<%-- <li><a href="${ctx}/work/reagent/">化学试剂列表</a></li> --%>
		<li class="active"><a href="javascript:void(0)">化学试剂记录查看</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="reagentRecord" action="${ctx}/work/reagentRecord/audit" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="state" id="state"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">化学名称：</label>
			<div class="controls">
				<a href="javascript:void(0)" onclick="info('${reagentRecord.reagent.id}')">${reagentRecord.reagent.name}</a>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">使用时间：</label>
			<div class="controls">
				<input readonly="readonly" id="useDate" name="useDate" type="text" readonly="true" maxlength="20" class="input-medium Wdate "
						value='<fmt:formatDate value="${reagentRecord.useDate}" pattern="yyyy-MM-dd"/>' onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">使用类型：</label>
			<div class="controls">
				${fns:getDictLabel(reagentRecord.useFlag, 'use_flag', '')}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">使用量：</label>
			<div class="controls">
				<form:input readonly="true" path="num" htmlEscape="false" maxlength="100" class="input-xlarge " />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请人：</label>
			<div class="controls">
				${reagentRecord.user.name}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属部门：</label>
			<div class="controls">
				${reagentRecord.user.office.name}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所用与项目：</label>
			<div class="controls">
				<form:textarea readonly="true" path="project" htmlEscape="false" rows="4" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">说明：</label>
			<div class="controls">
				<form:textarea readonly="true" path="instructions" htmlEscape="false" rows="4" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>