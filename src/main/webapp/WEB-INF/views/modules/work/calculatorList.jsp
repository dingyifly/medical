<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>物料项目管理</title>
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
		
		function showItem(id) {
			$.ajax({
				url:'${ctx}/work/calculator/getItems?id='+id,
				type:'post',
				success:function(data){
					$("#itemBody").html('');
					for(var i = 0; i < data.length; i++) {
						$("#itemBody").append("<tr><td>"+data[i].name+"</td><td>"+data[i].num+"</td></tr>");
					}
					layer.open({
						type:1, 
						skin:'layui-layer-rim',
						title:'物料详情',
						area:['420px', '240px'],
						content:$('#hideItems')
					})
				}
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/work/calculator/">物料项目列表</a></li>
		<shiro:hasPermission name="work:calculator:edit"><li><a href="${ctx}/work/calculator/form">物料项目添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="calculator" action="${ctx}/work/calculator/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>项目名称：</label>
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
				<th>项目名称</th>
				<shiro:hasPermission name="work:calculator:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="calculator">
			<tr>
				<td><a href="javascript:void(0)" onclick="showItem('${calculator.id}')">
					${calculator.name}
				</a></td>
				<shiro:hasPermission name="work:calculator:edit"><td>
    				<a href="${ctx}/work/calculator/form?id=${calculator.id}">修改</a>
					<a href="${ctx}/work/calculator/delete?id=${calculator.id}" onclick="return confirmx('确认要删除该物料项目吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<div id="hideItems" class="hide">
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>物料名称</th>
					<th>物料量</th>
				</tr>
			</thead>
			<tbody id="itemBody">
				
			</tbody>
		</table>
	</div>
</body>
</html>