<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>请假管理</title>
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
		<li class="active"><a href="${ctx}/work/workLeave/">请假列表</a></li>
		<shiro:hasAnyPermissions name="work:workLeave:edit,work:workLeave:add"><li><a href="${ctx}/work/workLeave/form">请假添加</a></li></shiro:hasAnyPermissions>
	</ul>
	<form:form id="searchForm" modelAttribute="workLeave" action="${ctx}/work/workLeave/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>请假类型：</label>
				<form:select path="leaveType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('oa_leave_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>开始时间</th>
				<th>结束时间</th>
				<th>请假类型</th>
				<th>审核状态</th>
				<th>申请人</th>
				<shiro:hasPermission name="work:workLeave:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="workLeave">
			<tr>
				<td><a href="${ctx}/work/workLeave/form?id=${workLeave.id}">
					<fmt:formatDate value="${workLeave.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					<fmt:formatDate value="${workLeave.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(workLeave.leaveType, 'oa_leave_type', '')}
				</td>
				<td>
					${fns:getDictLabel(workLeave.state, 'leave_flag', '')}
				</td>
				<td>
					${workLeave.createBy.name}
				</td>
				<shiro:hasPermission name="work:workLeave:edit"><td>
    				<a href="${ctx}/work/workLeave/form?id=${workLeave.id}">修改</a>
					<a href="${ctx}/work/workLeave/delete?id=${workLeave.id}" onclick="return confirmx('确认要删除该请假吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>