<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会议管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("a").click(function(){
				$(this).text()
				return window.parent.addTab($(this));
			});	
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
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<%-- <thead>
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
		</thead> --%>
		<tbody>
		<c:forEach items="${backlogList}" var="b">
			<tr>
				<td>
					<a href="${ctx}/${b.url}" target="mainFrame">${b.text}</a>
				</td>
				<td>
					${b.num}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:if test="${fn:length(backlogList) == 0}">
		暂无待办
	</c:if>
</body>
</html>