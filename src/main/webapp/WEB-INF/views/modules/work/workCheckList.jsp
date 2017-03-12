<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>检测申请管理</title>
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
		<li class="active"><a href="${ctx}/work/workCheck/">检测申请列表</a></li>
		<shiro:hasPermission name="work:workCheck:add"><li><a href="${ctx}/work/workCheck/form">检测申请添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="workCheck" action="${ctx}/work/workCheck/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>报告状态：</label>
				<form:select path="state" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('check_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>申请单号</th>
				<th>申请人</th>
				<th>测试报告状态</th>
				<th>分析人</th>
				<!-- <th>更新时间</th>
				<th>备注信息</th> -->
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="workCheck">
			<tr>
				<td>
					${workCheck.applyNo}
				</td>
				<td>
					${workCheck.applyer.name}
				</td>
				<td>
					${fns:getDictLabel(workCheck.state, 'check_flag', '')}
				</td>
				<td>
					${workCheck.analyser.name}
				</td>
				<%-- <td>
					<fmt:formatDate value="${workCheck.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${workCheck.remarks}
				</td> --%>
				<td>
					<a href="${ctx}/work/workCheck/view?id=${workCheck.id}">查看</a>
					<shiro:hasPermission name="work:workCheck:admin">
	    				<a href="${ctx}/work/workCheck/form?id=${workCheck.id}">修改</a>
						<a href="${ctx}/work/workCheck/delete?id=${workCheck.id}" onclick="return confirmx('确认要删除该检测申请吗？', this.href)">删除</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="work:workCheck:audit">
						<c:if test="${workCheck.state ==  '0' && workCheck.currentUser.office.id == workCheck.applyer.office.id}">
							<a href="${ctx}/work/workCheck/deal?id=${workCheck.id}">处理</a>
						</c:if>
					</shiro:hasPermission>
					<shiro:hasPermission name="work:workCheck:deal">
						<c:if test="${workCheck.state ==  '1' && workCheck.currentUser.id == workCheck.analyser.id}">
							<a href="${ctx}/work/workCheck/deal?id=${workCheck.id}">处理</a>
						</c:if>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>