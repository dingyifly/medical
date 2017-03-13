<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>物料计算器</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		/*function Run(strPath) {
			var WSH = new ActiveXObject("wscript.shell");
			WSH.Run(strPath);
			WSH = null;
		}*/
		
		function jisuan(){
			var k = 1;
			
			$("input[name='goods_final']").each(function(i){
				if ($(this).val() != null && $(this).val() != '') {
					k = $(this).val() / $(this).prev().val();
					return false;
				}
			});
			$("input[name='goods_final']").each(function(){
				var v = $(this).prev().val() * k;
				$(this).val(v);
			})
		}
		
		function clearAll() {
			$("input[name='goods_final']").val('');
		}
		
		$(function(){
			$("#project").change(function(){
				var v = $(this).val();
				if (v != null && v != '') {
					$.ajax({
						url:'${ctx}/work/calculator/getItems?id='+v,
						type:'post',
						success:function(data){
							$("#content").html('');
							for(var i = 0; i < data.length; i++) {
								var htmlText = '<div class="control-group">';
								htmlText += '<label class="control-label">' + data[i].name + '：</label>';
								htmlText += '<div class="controls">';
								htmlText += '<input readonly="readonly" type="text" name="goods" value="' + data[i].num + '"/>&nbsp;&nbsp;&nbsp;&nbsp;';
								htmlText += '<input type="text" name="goods_final" value=""/>&nbsp;&nbsp;&nbsp;&nbsp;';
								htmlText += '</div>';
								htmlText += '</div>';
								$("#content").append(htmlText);
							}
						}
					});
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="javascript:void(0)">物料计算器</a></li>
	</ul><br/>
	<form id="inputForm" action="#" method="post" class="form-horizontal">
		<div class="control-group">
			<label class="control-label">选择项目：</label>
			<div class="controls">
				<select id="project" class="input-medium">
					<option value="" label=""/>
					<c:forEach items="${list}" var="l">
						<option value="${l.id}">${l.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div id="content">
			<%-- <div class="control-group">
				<label class="control-label">物料A：</label>
				<div class="controls">
					<input readonly="readonly" type="text" name="goods" value="100"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="text" name="goods_final" value=""/>&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">物料B：</label>
				<div class="controls">
					<input readonly="readonly" type="text" name="goods" value="50"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="text" name="goods_final" value=""/>&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">物料C：</label>
				<div class="controls">
					<input readonly="readonly" type="text" name="goods" value="20"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="text" name="goods_final" value=""/>&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">物料D：</label>
				<div class="controls">
					<input readonly="readonly" type="text" name="goods" value="30"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="text" name="goods_final" value=""/>&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">物料E：</label>
				<div class="controls">
					<input readonly="readonly" type="text" name="goods" value="15"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="text" name="goods_final" value=""/>&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
			</div> --%>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="计 算" onclick="jisuan()"/>&nbsp;
			<input id="clear" class="btn btn-primary" type="button" value="清 空" onclick="clearAll()"/>&nbsp;
			<!-- <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/> -->
		</div>
	</form>
</body>
</html>