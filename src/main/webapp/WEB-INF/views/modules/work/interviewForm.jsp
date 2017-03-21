<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>面试管理</title>
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
		
		function sub(s) {
			if (s && s != '' && s != null) {
				$("#state").val(s);
			}
			$("#inputForm").submit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/work/interview/">面试列表</a></li>
		<li class="active"><a href="${ctx}/work/interview/form?id=${interview.id}">面试<shiro:hasPermission name="work:interview:edit">${not empty interview.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="work:interview:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="interview" action="${ctx}/work/interview/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="state"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">面试部门：</label>
			<div class="controls">
				<sys:treeselect id="office" name="office.id" value="${interview.office.id}" labelName="office.name" labelValue="${interview.office.name}"
					title="部门" url="/sys/office/treeData?type=2&isAll=true" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">面试日期：</label>
			<div class="controls">
				<input name="faceDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${interview.faceDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:radiobuttons path="state" items="${fns:getDictList('interview_flag')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">简历：</label>
			<div class="controls">
				<form:hidden id="resume" path="resume" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<shiro:hasPermission name="work:interview:edit">
					<sys:ckfinder input="resume" type="files" uploadPath="/work/interview" selectMultiple="true"/>
				</shiro:hasPermission>
				<c:if test="${interview.state != '' && interview.state != null}">
					<sys:ckfinder readonly="true" input="resume" type="files" uploadPath="/work/interview" selectMultiple="true"/>
				</c:if>
			</div>
		</div>
		<c:if test="${interview.state != '' && interview.state != null}">
			<div class="control-group">
				<label class="control-label">描述：</label>
				<div class="controls">
					<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
				</div>
			</div>
		</c:if>
		<div class="form-actions">
			<shiro:hasAnyPermissions name="work:interview:edit,work:interview:handle">
				<shiro:hasPermission name="work:interview:edit">
					<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="sub()"/>&nbsp;
				</shiro:hasPermission>
				<c:if test="${interview.state == '0' && interview.currentUser.office.id == interview.office.id}">
					<input id="btnSubmit" class="btn btn-primary" type="button" value="通 过" onclick="sub('1')"/>&nbsp;
					<input id="btnSubmit" class="btn btn-primary" type="button" value="拒 绝" onclick="sub('3')"/>&nbsp;
				</c:if>
				<shiro:hasAnyRoles name="hr-manager">
					<c:if test="${interview.state == '1'}">
						<input id="btnSubmit" class="btn btn-primary" type="button" value="通 过" onclick="sub('2')"/>&nbsp;
						<input id="btnSubmit" class="btn btn-primary" type="button" value="拒 绝" onclick="sub('3')"/>&nbsp;
					</c:if>
				</shiro:hasAnyRoles>
			</shiro:hasAnyPermissions>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>