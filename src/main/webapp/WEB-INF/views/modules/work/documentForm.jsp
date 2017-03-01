<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文档管理</title>
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
			<li><a href="${ctx}/work/document/">${document.type == 0 ? '文挡' : '文献'}列表</a></li>
			<li class="active"><a href="${ctx}/work/document/form?type=${document.type}&id=${document.id}">${document.type == 0 ? '文挡' : '文献'}<shiro:hasPermission name="work:document:edit">${not empty document.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="work:document:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="document" action="${ctx}/work/document/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="type" />
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">${document.type == 0 ? '文挡' : '文献'}名：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<c:if test="${document.type == 1}">
			<div class="control-group">
				<label class="control-label">部门：</label>
				<div class="controls">
					<sys:treeselect id="office" name="office.id" value="${document.office.id}" labelName="office.name" labelValue="${document.office.name}"
						title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
				</div>
			</div>
		</c:if>
		<%-- <div class="control-group">
			<label class="control-label">类别：</label>
			<div class="controls">
				<form:input path="type" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">文件：</label>
			<div class="controls">
				<form:hidden id="filePath" path="filePath" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="filePath" type="files" uploadPath="/work/document" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">${document.type == 0 ? '文挡' : '文献'}内容(可选填)：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" class="input-xxlarge "/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="work:document:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>