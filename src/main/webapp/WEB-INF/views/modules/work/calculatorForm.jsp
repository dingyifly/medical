<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>物料项目管理</title>
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
		<li><a href="${ctx}/work/calculator/">物料项目列表</a></li>
		<li class="active"><a href="${ctx}/work/calculator/form?id=${calculator.id}">物料项目<shiro:hasPermission name="work:calculator:edit">${not empty calculator.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="work:calculator:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="calculator" action="${ctx}/work/calculator/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">项目名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div> --%>
			<div class="control-group">
				<label class="control-label">项目物料：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed" style="width: 50%">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>物料名称</th>
								<th>物料量</th>
								<shiro:hasPermission name="work:calculator:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="calculatorItemList">
						</tbody>
						<shiro:hasPermission name="work:calculator:edit"><tfoot>
							<tr><td colspan="4"><a href="javascript:" onclick="addRow('#calculatorItemList', calculatorItemRowIdx, calculatorItemTpl);calculatorItemRowIdx = calculatorItemRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="calculatorItemTpl">//<!--
						<tr id="calculatorItemList{{idx}}">
							<td class="hide">
								<input id="calculatorItemList{{idx}}_id" name="calculatorItemList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="calculatorItemList{{idx}}_delFlag" name="calculatorItemList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="calculatorItemList{{idx}}_name" name="calculatorItemList[{{idx}}].name" type="text" value="{{row.name}}" maxlength="100" class="input-small "/>
							</td>
							<td>
								<input id="calculatorItemList{{idx}}_num" name="calculatorItemList[{{idx}}].num" type="text" value="{{row.num}}" maxlength="20" class="input-small "/>
							</td>
							<shiro:hasPermission name="work:calculator:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#calculatorItemList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var calculatorItemRowIdx = 0, calculatorItemTpl = $("#calculatorItemTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(calculator.calculatorItemList)};
							for (var i=0; i<data.length; i++){
								addRow('#calculatorItemList', calculatorItemRowIdx, calculatorItemTpl, data[i]);
								calculatorItemRowIdx = calculatorItemRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="work:calculator:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>