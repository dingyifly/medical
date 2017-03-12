<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>化学试剂记录列表</title>
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
		<li class="active"><a href="${ctx}/work/reagentRecord/auditList">化学试剂记录列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="reagentRecord" action="${ctx}/work/reagentRecord/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>化学名称：</label>
				<form:input path="reagent.name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>化学名称</th>
				<th>使用时间</th>
				<th>使用状态</th>
				<th>用量</th>
				<th>申请人</th>
				<th>所属部门</th>
				<th>所属项目</th>
				<th>说明</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="record">
			<tr>
				<td>
					${record.reagent.name}
				</td>
				<td>
					<fmt:formatDate value="${record.useDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${fns:getDictLabel(record.useFlag, 'use_flag', '')}
				</td>
				<td>
					${record.num}
				</td>
				<td>
					${record.user.name}
				</td>
				<td>
					${record.user.office.name}
				</td>
				<td>
					${record.project}
				</td>
				<td>
					${record.instructions}
				</td>
				<td>
					${fns:getDictLabel(record.state, 'use_audit_flag', '')}
				</td>
				<td>
					<a href="${ctx}/work/reagentRecord/view?id=${record.id}">查看</a>
					<%-- <shiro:hasAnyPermissions name="work:reagent:use,work:reagent:edit">
						<a href="${ctx}/work/reagent/toUse?id=${reagent.id}">使用</a>
						<shiro:hasPermission name="work:reagent:edit">
	    				<a href="${ctx}/work/reagent/form?id=${reagent.id}">修改</a>
						<a href="${ctx}/work/reagent/delete?id=${reagent.id}" onclick="return confirmx('确认要删除该化学试剂吗？', this.href)">删除</a>
					</shiro:hasPermission>
					</shiro:hasAnyPermissions> --%>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>