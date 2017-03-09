<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会议管理</title>
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
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/work/meeting/">会议列表</a></li>
		<li class="active"><a href="javascript:void(0)">查看</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="meeting" action="${ctx}/work/meeting/info" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">会议时间：</label>
			<div class="controls">
				<input name="meetingTime" type="text" readonly="readonly" maxlength="20" class="input-medium"
					value="<fmt:formatDate value="${meeting.meetingTime}" pattern="yyyy-MM-dd"/>"
					/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主持人：</label>
			<div class="controls">
				<input type="text" class="input-xlarge" value="${meeting.compere.name}" readonly="readonly">
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主题：</label>
			<div class="controls">
				<form:input readonly="true" path="theme" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参加会议人员：</label>
			<div class="controls">
				<%-- <input type="text" class="input-xlarge" value="${meeting.participantNames}" readonly="readonly">
				<span class="help-inline"><font color="red">*</font> </span> --%>
				<font size="4">${meeting.participantNames}</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">会议记录：</label>
			<div class="controls">
				<form:textarea readonly="true" path="record" htmlEscape="false" rows="4" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">记录员：</label>
			<div class="controls">
				<input type="text" class="input-xlarge" value="${meeting.recorder.name}" readonly="readonly">
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">执行情况：</label>
			<div class="controls">
				<form:textarea readonly="true" path="executiveCondition" htmlEscape="false" rows="4" class="input-xxlarge "/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea readonly="true" path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div> --%>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>