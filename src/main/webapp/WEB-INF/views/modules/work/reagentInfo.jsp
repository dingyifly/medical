<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>化学试剂管理</title>
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
		<%-- <li><a href="${ctx}/work/reagent/">化学试剂列表</a></li> --%>
		<li class="active"><a href="javascript:void(0)">化学试剂查看</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="reagent" action="${ctx}/work/reagent/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">化学名称：</label>
			<div class="controls">
				<form:input readonly="true" path="name" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">CAS：</label>
			<div class="controls">
				<form:input readonly="true" path="model" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">规格：</label>
			<div class="controls">
				<p class="form-control-static">${fns:getDictLabel(reagent.specification, 'reagent_specification', '')}</p>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">库存：</label>
			<div class="controls">
				<form:input readonly="true" path="repertory" htmlEscape="false" maxlength="10" class="input-small required"/>
				<span class="help-inline">${reagent.unit} </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">MSDS：</label>
			<div class="controls">
				<form:textarea readonly="true" path="instructions" htmlEscape="false" rows="4" class="input-xxlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述：</label>
			<div class="controls">
				<form:textarea readonly="true" path="descr" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div> --%>
			<div class="control-group">
				<label class="control-label">化学试剂记录表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>时间</th>
								<th>使用标识</th>
								<th>用量</th>
								<th>申请人</th>
								<th>所属部门</th>
								<th>使用项目</th>
								<th>说明</th>
								<th>审核状态</th>
								<shiro:hasPermission name="work:reagent:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="reagentRecordList">
							<c:forEach items="${reagent.reagentRecordList}" var="rr" varStatus="g">
								<tr>
									<td class="hide">
										<input id="recordId" type="hidden" value="${rr.id}">
									</td>
									<td><fmt:formatDate value="${rr.useDate}" pattern="yyyy-MM-dd"/></td>
									<td>${fns:getDictLabel(rr.useFlag, 'use_flag', '')}</td>
									<td>${rr.num}</td>
									<td>${rr.user.name}</td>
									<td>${rr.user.office.name}</td>
									<td>${rr.project}</td>
									<td>${rr.instructions}</td>
									<td>${fns:getDictLabel(rr.state, 'use_audit_flag', '')}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<script type="text/template" id="reagentRecordTpl">//<!--
						<tr id="reagentRecordList{{idx}}">
							<td class="hide">
								<input id="reagentRecordList{{idx}}_id" name="reagentRecordList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="reagentRecordList{{idx}}_delFlag" name="reagentRecordList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="reagentRecordList{{idx}}_useDate" name="reagentRecordList[{{idx}}].useDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									value="{{row.useDate}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</td>
							<td>
								<c:forEach items="${fns:getDictList('use_flag')}" var="dict" varStatus="dictStatus">
									<span><input id="reagentRecordList{{idx}}_useFlag${dictStatus.index}" name="reagentRecordList[{{idx}}].useFlag" type="checkbox" value="${dict.value}" data-value="{{row.useFlag}}"><label for="reagentRecordList{{idx}}_useFlag${dictStatus.index}">${dict.label}</label></span>
								</c:forEach>
							</td>
							<td>
								<sys:treeselect id="reagentRecordList{{idx}}_user" name="reagentRecordList[{{idx}}].user.id" value="{{row.user.id}}" labelName="reagentRecordList{{idx}}.user.name" labelValue="{{row.user.name}}"
									title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
							</td>
							<td>
								<textarea id="reagentRecordList{{idx}}_project" name="reagentRecordList[{{idx}}].project" rows="4" maxlength="100" class="input-small ">{{row.project}}</textarea>
							</td>
							<td>
								<textarea id="reagentRecordList{{idx}}_instructions" name="reagentRecordList[{{idx}}].instructions" rows="4" maxlength="255" class="input-small ">{{row.instructions}}</textarea>
							</td>
							<td>
								<input id="reagentRecordList{{idx}}_state" name="reagentRecordList[{{idx}}].state" type="text" value="{{row.state}}" maxlength="1" class="input-small "/>
							</td>
							<td>
								<textarea id="reagentRecordList{{idx}}_remarks" name="reagentRecordList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="work:reagent:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#reagentRecordList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<!-- <script type="text/javascript">
						var reagentRecordRowIdx = 0, reagentRecordTpl = $("#reagentRecordTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(reagent.reagentRecordList)};
							for (var i=0; i<data.length; i++){
								addRow('#reagentRecordList', reagentRecordRowIdx, reagentRecordTpl, data[i]);
								reagentRecordRowIdx = reagentRecordRowIdx + 1;
							}
						});
					</script> -->
				</div>
			</div>
		<div class="form-actions">
			<%-- <shiro:hasPermission name="work:reagent:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission> --%>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>