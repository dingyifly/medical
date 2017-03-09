<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>请假管理</title>
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
	</script>
	<script type="text/javascript">
		function subit(state) {
			$("#state").val(state);
			$("#inputForm").submit();
		}
		
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/work/workLeave/auditList">请假审核列表</a></li>
		<li class="active"><a href="javascript:void(0)">审核</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="workLeave" action="${ctx}/work/workLeave/audit" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="state" id="state"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">开始时间：</label>
			<div class="controls">
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium"
					value="<fmt:formatDate value="${workLeave.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束时间：</label>
			<div class="controls">
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium"
					value="<fmt:formatDate value="${workLeave.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">请假类型：</label>
			<div class="controls">
				${fns:getDictLabel(workLeave.leaveType, 'oa_leave_type', '')}
				<form:hidden path="leaveType"/>
				<span class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">请假理由：</label>
			<div class="controls">
				<form:textarea readonly="true" path="reason" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请时间：</label>
			<div class="controls">
				<input name="applyTime" type="text" readonly="readonly" maxlength="20" class="input-medium"
					value="<fmt:formatDate value="${workLeave.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasRole name="dmanager">
				<input id="pass" class="btn btn-primary" type="button" value="通过" onclick="subit('1')"/>&nbsp;
			</shiro:hasRole>
			<shiro:hasAnyRoles name="manager,boss,hr">
				<input id="pass" class="btn btn-primary" type="button" value="通过" onclick="subit('2')"/>&nbsp;
			</shiro:hasAnyRoles>
			<input id="back" class="btn btn-primary" type="button" value="退回" onclick="subit('3')"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>