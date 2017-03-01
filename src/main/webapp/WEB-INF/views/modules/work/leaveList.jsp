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
		<li class="active"><a href="${ctx}/work/leave/">请假列表</a></li>
		<shiro:hasPermission name="work:leave:edit"><li><a href="${ctx}/work/leave/form">请假添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="leave" action="${ctx}/work/leave/" method="post" class="breadcrumb form-search">
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
				<th>创建者</th>
				<shiro:hasPermission name="work:leave:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="leave">
			<tr>
				<td><a href="${ctx}/work/leave/form?id=${leave.id}">
					<fmt:formatDate value="${leave.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					<fmt:formatDate value="${leave.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(leave.leaveType, 'oa_leave_type', '')}
				</td>
				<td>
					${fns:getDictLabel(leave.state, 'work_leave_flag', '')}
				</td>
				<td>
					${leave.createBy.id}
				</td>
				<shiro:hasPermission name="work:leave:edit"><td>
    				<a href="${ctx}/work/leave/form?id=${leave.id}">修改</a>
					<a href="${ctx}/work/leave/delete?id=${leave.id}" onclick="return confirmx('确认要删除该请假吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>