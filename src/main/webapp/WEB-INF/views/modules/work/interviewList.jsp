<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>面试管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/work/interview/">面试列表</a></li>
		<shiro:hasPermission name="work:interview:edit"><li><a href="${ctx}/work/interview/form">面试添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="interview" action="${ctx}/work/interview/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>面试部门：</label>
				<sys:treeselect id="office" name="office.id" value="${interview.office.id}" labelName="office.name" labelValue="${interview.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>面试日期：</label>
				<input name="faceDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${interview.faceDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>状态：</label>
				<%-- <form:radiobuttons path="state" items="${fns:getDictList('interview_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
				<form:select path="state" class="input-large">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('interview_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>面试部门</th>
				<th>面试日期</th>
				<th>状态</th>
				<th>更新时间</th>
				<!-- <th>备注信息</th> -->
				<shiro:hasPermission name="work:interview:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="interview">
			<tr>
				<td><a href="${ctx}/work/interview/form?id=${interview.id}">
					${interview.name}
				</a></td>
				<td>
					${interview.office.name}
				</td>
				<td>
					<fmt:formatDate value="${interview.faceDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${fns:getDictLabel(interview.state, 'interview_flag', '')}
				</td>
				<td>
					<fmt:formatDate value="${interview.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <td>
					${interview.remarks}
				</td> --%>
				<shiro:hasPermission name="work:interview:edit"><td>
    				<a href="${ctx}/work/interview/form?id=${interview.id}">修改</a>
					<a href="${ctx}/work/interview/delete?id=${interview.id}" onclick="return confirmx('确认要删除该面试吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>