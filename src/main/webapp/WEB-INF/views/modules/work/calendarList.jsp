<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>日程管理</title>
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
		<li class="active"><a href="${ctx}/work/calendar/">日程列表</a></li>
		<shiro:hasAnyPermissions name="work:calendar:edit,work:calendar:add"><li><a href="${ctx}/work/calendar/form">日程添加</a></li></shiro:hasAnyPermissions>
	</ul>
	<form:form id="searchForm" modelAttribute="calendar" action="${ctx}/work/calendar/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>执行人</th>
				<th>日程说明</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>更新时间</th>
				<!-- <th>备注信息</th> -->
				<shiro:hasPermission name="work:calendar:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="calendar">
			<tr>
				<td><a href="${ctx}/work/calendar/form?id=${calendar.id}">
					${calendar.user.name}
				</a></td>
				<td>
					${calendar.description}
				</td>
				<td>
					<fmt:formatDate value="${calendar.startTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${calendar.endTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${calendar.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <td>
					${calendar.remarks}
				</td> --%>
				<shiro:hasPermission name="work:calendar:edit"><td>
    				<a href="${ctx}/work/calendar/form?id=${calendar.id}">修改</a>
					<a href="${ctx}/work/calendar/delete?id=${calendar.id}" onclick="return confirmx('确认要删除该日程吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>