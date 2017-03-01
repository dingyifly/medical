<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文档管理</title>
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
		<li class="active"><a href="${ctx}/work/document/?type=${document.type}">${document.type == 0 ? '文挡' : '文献'}列表</a></li>
		<shiro:hasPermission name="work:document:edit"><li><a href="${ctx}/work/document/form?type=${document.type}">${document.type == 0 ? '文挡' : '文献'}添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="document" action="${ctx}/work/document/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<form:hidden path="type"/>
		<ul class="ul-form">
			<li><label>${document.type == 0 ? '文挡' : '文献'}名：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>${document.type == 0 ? '文挡' : '文献'}名</th>
				<c:if test="${document.type == 1}">
					<th>部门</th>
				</c:if>
				<th>创建者</th>
				<th>创建时间</th>
				<shiro:hasPermission name="work:document:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="document">
			<tr>
				<td><a href="${ctx}/work/document/form?id=${document.id}">
					${document.name}
				</a></td>
				<c:if test="${document.type == 1}">
					<td>
						${document.office.name}
					</td>
				</c:if>
				<td>
					${document.createBy.name}
				</td>
				<td>
					<fmt:formatDate value="${document.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="work:document:edit"><td>
    				<a href="${ctx}/work/document/form?id=${document.id}">修改</a>
					<a href="${ctx}/work/document/delete?id=${document.id}" onclick="return confirmx('确认要删除该文档吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>