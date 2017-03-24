<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/work/project/">项目列表</a></li>
		<li class="active"><a href="${ctx}/work/project/form?id=${project.id}">项目<shiro:hasPermission name="work:project:edit">${not empty project.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="work:project:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="project" action="${ctx}/work/project/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">项目编号：</label>
			<div class="controls">
				<form:input path="no" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目负责人：</label>
			<div class="controls">
				<sys:treeselect id="principal" name="principal.id" value="${project.principal.id}" labelName="principal.name" labelValue="${project.principal.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评级：</label>
			<div class="controls">
				<form:checkboxes path="lvl" items="${fns:getDictList('work_project_lvl')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">调研报告：</label>
			<div class="controls">
				<form:hidden id="investigationReport" path="investigationReport" htmlEscape="false" class="input-xlarge"/>
				<sys:ckfinder input="investigationReport" type="files" uploadPath="/work/project" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">成本预算：</label>
			<div class="controls">
				<form:input path="costBudgeting" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开发状态：</label>
			<div class="controls">
				<form:input path="devFlag" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">小试次数：</label>
			<div class="controls">
				<form:input path="smallNum" htmlEscape="false" maxlength="5" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中试次数：</label>
			<div class="controls">
				<form:input path="middleNum" htmlEscape="false" maxlength="5" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发大次数：</label>
			<div class="controls">
				<form:input path="largeNum" htmlEscape="false" maxlength="5" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总结：</label>
			<div class="controls">
				<form:input path="summary" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">项目开发进度表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>项目编号</th>
								<th>开发状态</th>
								<th>次数</th>
								<th>总结</th>
								<shiro:hasPermission name="work:project:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="projectDevelopList">
						</tbody>
						<shiro:hasPermission name="work:project:edit"><tfoot>
							<tr><td colspan="6"><a href="javascript:" onclick="addRow('#projectDevelopList', projectDevelopRowIdx, projectDevelopTpl);projectDevelopRowIdx = projectDevelopRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="projectDevelopTpl">//<!--
						<tr id="projectDevelopList{{idx}}">
							<td class="hide">
								<input id="projectDevelopList{{idx}}_id" name="projectDevelopList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="projectDevelopList{{idx}}_delFlag" name="projectDevelopList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="projectDevelopList{{idx}}_projectNo" name="projectDevelopList[{{idx}}].projectNo" type="text" value="{{row.projectNo}}" maxlength="64" class="input-small "/>
							</td>
							<td>
								<c:forEach items="${fns:getDictList('work_dev_flag')}" var="dict" varStatus="dictStatus">
									<span><input id="projectDevelopList{{idx}}_testFlag${dictStatus.index}" name="projectDevelopList[{{idx}}].testFlag" type="checkbox" value="${dict.value}" data-value="{{row.testFlag}}"><label for="projectDevelopList{{idx}}_testFlag${dictStatus.index}">${dict.label}</label></span>
								</c:forEach>
							</td>
							<td>
								<input id="projectDevelopList{{idx}}_num" name="projectDevelopList[{{idx}}].num" type="text" value="{{row.num}}" maxlength="5" class="input-small "/>
							</td>
							<td>
								<input id="projectDevelopList{{idx}}_summary" name="projectDevelopList[{{idx}}].summary" type="text" value="{{row.summary}}" class="input-small "/>
							</td>
							<shiro:hasPermission name="work:project:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#projectDevelopList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var projectDevelopRowIdx = 0, projectDevelopTpl = $("#projectDevelopTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(project.projectDevelopList)};
							for (var i=0; i<data.length; i++){
								addRow('#projectDevelopList', projectDevelopRowIdx, projectDevelopTpl, data[i]);
								projectDevelopRowIdx = projectDevelopRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="work:project:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>