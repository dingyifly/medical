<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考勤表管理</title>
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
		<li class="active"><a href="${ctx}/work/attendance/">考勤表列表</a></li>
		<shiro:hasPermission name="work:attendance:edit"><li><a href="${ctx}/work/attendance/form">考勤表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="attendance" action="${ctx}/work/attendance/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>日期：</label>
				<input name="dateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${attendance.dateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>用户：</label>
				<sys:treeselect id="user" name="user.id" value="${attendance.user.id}" labelName="user.name" labelValue="${attendance.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>工号：</label>
				<form:input path="no" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>日期</th>
				<th>用户</th>
				<th>工号</th>
				<th>上班应打卡时间</th>
				<th>上班实际打卡时间</th>
				<th>下班应打卡时间</th>
				<th>下班实际打卡时间</th>
				<th>迟到标记</th>
				<th>早退标记</th>
				<th>旷到标记</th>
				<shiro:hasPermission name="work:attendance:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="attendance">
			<tr>
				<td><a href="${ctx}/work/attendance/form?id=${attendance.id}">
					${attendance.dateTime}
				</a></td>
				<td>
					${attendance.user.name}
				</td>
				<td>
					${attendance.no}
				</td>
				<td>
					${attendance.amPlanTime}
				</td>
				<td>
					${attendance.amRealityTime}
				</td>
				<td>
					${attendance.pmPlayTime}
				</td>
				<td>
					${attendance.pmRealityTim}
				</td>
				<td>
					${fns:getDictLabel(attendance.lateFlag, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(attendance.leaveFlag, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(attendance.absentFlag, 'yes_no', '')}
				</td>
				<shiro:hasPermission name="work:attendance:edit"><td>
    				<a href="${ctx}/work/attendance/form?id=${attendance.id}">修改</a>
					<a href="${ctx}/work/attendance/delete?id=${attendance.id}" onclick="return confirmx('确认要删除该考勤表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>