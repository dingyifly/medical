<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>检测申请管理</title>
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
		<li><a href="${ctx}/work/workCheck/">检测申请列表</a></li>
		<li class="active"><a href="${ctx}/work/workCheck/form?id=${workCheck.id}">检测申请<shiro:hasPermission name="work:workCheck:edit">${not empty workCheck.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="work:workCheck:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="workCheck" action="${ctx}/work/workCheck/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">申请单号：</label>
			<div class="controls">
				<form:input path="applyNo" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">检测含量表：</label>
			<div class="controls">
				<table id="contentTable" class="table table-striped table-bordered table-condensed" style="width: 25%">
					<thead>
						<tr>
							<th class="hide"></th>
							<th width="50%">含量类型</th>
							<th width="40%">含量</th>
							<shiro:hasPermission name="work:workCheck:add"><th width="10%">&nbsp;</th></shiro:hasPermission>
						</tr>
					</thead>
					<tbody id="workCheckItemList">
					</tbody>
					<shiro:hasPermission name="work:workCheck:add"><tfoot>
						<tr><td colspan="4"><a href="javascript:" onclick="addRow('#workCheckItemList', workCheckItemRowIdx, workCheckItemTpl);workCheckItemRowIdx = workCheckItemRowIdx + 1;" class="btn">新增</a></td></tr>
					</tfoot></shiro:hasPermission>
				</table>
				<script type="text/template" id="workCheckItemTpl">//<!--
						<tr id="workCheckItemList{{idx}}">
							<td class="hide">
								<input id="workCheckItemList{{idx}}_id" name="workCheckItemList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="workCheckItemList{{idx}}_delFlag" name="workCheckItemList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<select id="workCheckItemList{{idx}}_dictId" name="workCheckItemList[{{idx}}].dictId" data-value="{{row.dictId}}" class="input-small ">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('content_type')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<input id="workCheckItemList{{idx}}_num" name="workCheckItemList[{{idx}}].num" type="text" value="{{row.num}}" maxlength="64" class="input-small "/>
							</td>
							<shiro:hasPermission name="work:workCheck:add"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#workCheckItemList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
				<script type="text/javascript">
					var workCheckItemRowIdx = 0, workCheckItemTpl = $("#workCheckItemTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
					$(document).ready(function() {
						var data = ${fns:toJson(workCheck.workCheckItemList)};
						for (var i=0; i<data.length; i++){
							addRow('#workCheckItemList', workCheckItemRowIdx, workCheckItemTpl, data[i]);
							workCheckItemRowIdx = workCheckItemRowIdx + 1;
						}
					});
				</script>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">测试报告状态：</label>
			<div class="controls">
				<form:select path="state" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('check_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div> 
		<div class="control-group">
			<label class="control-label">分析人：</label>
			<div class="controls">
				<sys:treeselect id="analyser" name="analyser.id" value="${workCheck.analyser.id}" labelName="analyser.name" labelValue="${workCheck.analyser.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">检测报告：</label>
			<div class="controls">
				<form:textarea path="report" htmlEscape="false" rows="4" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">检测报告文件：</label>
			<div class="controls">
				<form:hidden id="reportFiles" path="reportFiles" htmlEscape="false" class="input-xlarge"/>
				<sys:ckfinder input="reportFiles" type="files" uploadPath="/work/workCheck" selectMultiple="true"/>
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="work:workCheck:add"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>