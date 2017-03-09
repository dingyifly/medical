<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会议管理</title>
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
		<li class="active"><a href="${ctx}/work/meeting/">会议列表</a></li>
		<shiro:hasPermission name="work:meeting:edit"><li><a href="${ctx}/work/meeting/form">会议添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="meeting" action="${ctx}/work/meeting/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>会议时间：</label>
				<input name="meetingTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${meeting.meetingTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li><label>主题：</label>
				<form:input path="theme" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>会议时间</th>
				<th>主持人</th>
				<th>主题</th>
				<!-- <th>参加会议人员</th> -->
				<th>记录员</th>
				<th>执行情况</th>
				<th>更新时间</th>
				<!-- <th>备注信息</th> -->
				<shiro:hasPermission name="work:meeting:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="meeting">
			<tr>
				<td><a href="${ctx}/work/meeting/view?id=${meeting.id}">
					<fmt:formatDate value="${meeting.meetingTime}" pattern="yyyy-MM-dd"/>
				</a></td>
				<td>
					${meeting.compere.name}
				</td>
				<td>
					${meeting.theme}
				</td>
				<%-- <td>
					${meeting.}
				</td> --%>
				<td>
					${meeting.recorder.name}
				</td>
				<td>
					${meeting.executiveCondition}
				</td>
				<td>
					<fmt:formatDate value="${meeting.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <td>
					${meeting.remarks}
				</td> --%>
				<shiro:hasPermission name="work:meeting:edit"><td>
    				<a href="${ctx}/work/meeting/form?id=${meeting.id}">修改</a>
					<a href="${ctx}/work/meeting/delete?id=${meeting.id}" onclick="return confirmx('确认要删除该会议吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>