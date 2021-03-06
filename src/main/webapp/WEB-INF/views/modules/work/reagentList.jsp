<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>化学试剂管理</title>
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
		<li class="active"><a href="${ctx}/work/reagent/">化学试剂列表</a></li>
		<shiro:hasPermission name="work:reagent:edit"><li><a href="${ctx}/work/reagent/form">化学试剂添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="reagent" action="${ctx}/work/reagent/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>化学名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
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
				<th>CAS</th>
				<th>库存</th>
				<th>规格</th>
				<th>描述</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="reagent">
			<tr>
				<td>
					${reagent.name}
				</td>
				<td>${reagent.model}</td>
				<td>
					${reagent.repertory}${reagent.unit}
				</td>
				<td>
					${fns:getDictLabel(reagent.specification, 'reagent_specification', '')}
				</td>
				<td>
					${reagent.descr}
				</td>
				<td>
					<a href="${ctx}/work/reagent/view?id=${reagent.id}">查看</a>
					<shiro:hasAnyPermissions name="work:reagent:use,work:reagent:edit">
						<a href="${ctx}/work/reagent/toUse?id=${reagent.id}">使用</a>
						<shiro:hasPermission name="work:reagent:edit">
	    				<a href="${ctx}/work/reagent/form?id=${reagent.id}">修改</a>
						<a href="${ctx}/work/reagent/delete?id=${reagent.id}" onclick="return confirmx('确认要删除该化学试剂吗？', this.href)">删除</a>
					</shiro:hasPermission>
					</shiro:hasAnyPermissions>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>