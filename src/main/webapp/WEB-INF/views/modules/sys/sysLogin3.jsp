<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<!-- saved from url=(0058)http://www.saith.com.cn/index.php -->
<HTML>
<HEAD>
<TITLE>武汉赛狮药物化学有限公司</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<META name=GENERATOR content="MSHTML 8.00.7601.18210">
<META name=searchtitle content=赛狮，赛狮药话，药物化学，武汉赛狮药物化学有限公司>
<META name=description
	content="武汉赛狮药物化学有限公司成立于2011年，是一家致力于将先进的化学合成技术和全球化市场需求相结合的医药科技公司，经过5年的努力发展， 研发中心于2016年11月整体迁至武汉光谷生物医药产业园，实验室面积1100平方米，配备了气相色谱仪、高效液相色谱仪、液质联用仪、紫外等分析检测仪器和平行反应器等工艺开发设备，拥有两个规范化的合作生产基地。公司建立起一套完善的研发流程和管理体系，在工艺开发、质量研究、放大生产方面进行严格的质量控制；定制化的研发办公系统极大提升了研发效率；专业化的敬业团队保障了研发项目快速工业转化。">
<STYLE>
	* {
		padding-bottom: 0px;
		margin: 0px;
		padding-left: 0px;
		padding-right: 0px;
		font-family: 微软雅黑;
		padding-top: 0px
	}
	html {
		background: url('${ctxStatic}/images/custom/loginbj.png') no-repeat;
		height: 100%
	}
	body {
		background: url('${ctxStatic}/images/custom/loginbj.png') no-repeat;
		height: 100%
	}
	img {
		margin: auto;
		display: block
	}
	section {
		padding-top: 40px
	}
	form {
		padding-bottom: 45px;
		margin: auto;
		padding-left: 72px;
		width: 349px;
		padding-right: 72px;
		background: url('${ctxStatic}/images/custom/login.png') no-repeat;
		height: 246px;
		padding-top: 45px
	}
	input {
		border-bottom: #47b6b9 1px solid;
		border-left: #47b6b9 1px solid;
		padding-bottom: 10px;
		outline-style: none;
		outline-color: invert;
		padding-left: 37px;
		outline-width: medium;
		width: 310px;
		padding-right: 0px;
		background-repeat: no-repeat;
		background-position: 12px center;
		margin-bottom: 20px;
		border-top: #47b6b9 1px solid;
		border-right: #47b6b9 1px solid;
		padding-top: 10px;
		border-radius: 5px
	}
	a {
		color: #111;
		font-size: 14px;
		text-decoration: none
	}
	#username{
		margin-top:0px;
	}
	 .header{height:30px;padding-top:0px;}
	 .alert{
	 		position:relative;width:350px;margin:0 auto;*padding-bottom:0px;
	 	 	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
  			background-color: #f2dede;
	 }
	 label.error{background:none;width:270px;font-weight:normal;color:inherit;margin:0;}
	 .error{background-color: #f2dede;}
	 .validateCode{float:right;}
</STYLE>
	<!--[if lt IE 9]>
<SCRIPT src=""></SCRIPT>
<![endif]-->
	<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#loginForm").validate({
				rules: {
					validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
				},
				messages: {
					username: {required: "请填写用户名."},password: {required: "请填写密码."},
					validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
				},
				errorLabelContainer: "#messageBox",
				errorPlacement: function(error, element) {
					error.appendTo($("#loginError").parent());
				} 
			});
		});
		// 如果在框架或在对话框中，则弹出提示并跳转到首页
		if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
			alert('未登录或登录超时。请重新登录，谢谢！');
			top.location = "${ctx}";
		}
		$(function(){
			//$('body').width(screen.width).height(screen.height);
			//$('body').css
			$("html").attr("style","overflow-x:auto;_overflow-y:auto");
		});
	</script>
</HEAD>
<BODY>
	<%-- <div class="header">
		<div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}"><button data-dismiss="alert" class="close">×</button>
			<label id="loginError" class="error">${message}</label>
		</div>
	</div> --%>
	<SECTION>
		<IMG alt="" src="${ctxStatic}/images/custom/loginlogo.png" width=196 height=52>
		<IMG style="MARGIN: 35px auto" alt=""
			src="${ctxStatic}/images/custom/loginh.png" width=565 height=45>
		<form id="loginForm" class="form-signin" action="${ctx}/login" method="post">
			<div class="header">
				<div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}">
					<label id="loginError" class="error">${message}</label>
				</div>
			</div>
			<input type="text" id="username" name="username" class="required" value="${username}"
				style="BACKGROUND-IMAGE: url(${ctxStatic}/images/custom/user.png)"
				placeholder="输入工号">
			<input type="password" id="password" name="password" class="required"
				style="BACKGROUND-IMAGE: url(${ctxStatic}/images/custom/password.png)"
				placeholder="输入密码">
			<%-- <DIV
				style="BORDER-BOTTOM: #47b6b9 1px solid; BORDER-LEFT: #47b6b9 1px solid; MARGIN-BOTTOM: 20px; OVERFLOW: hidden; BORDER-TOP: #47b6b9 1px solid; BORDER-RIGHT: #47b6b9 1px solid; border-radius: 5px">
				<INPUT
					style="BACKGROUND-IMAGE: url(${ctxStatic}/images/custom/reg.png); BORDER-BOTTOM: medium none; BORDER-LEFT: medium none; WIDTH: 58%; MARGIN-BOTTOM: 0px; BORDER-TOP: medium none; BORDER-RIGHT: medium none"
					required placeholder="请输入验证码"> <IMG
					style="BORDER-LEFT: #47b6b9 1px solid; FLOAT: right" alt=""
					src="${ctxStatic}/images/custom/regcontain.png" width="30%">
			</DIV> --%>
			<c:if test="${isValidateCodeLogin}">
				<div class="validateCode">
					<label class="input-label mid" for="validateCode">验证码</label>
					<sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
				</div>
			</c:if>
			<INPUT
				style="BORDER-BOTTOM: medium none; TEXT-ALIGN: center; BORDER-LEFT: medium none; PADDING-BOTTOM: 0px; LINE-HEIGHT: 2.5; PADDING-LEFT: 0px; WIDTH: 100%; PADDING-RIGHT: 0px; BACKGROUND: #47b6b9; COLOR: #fff; FONT-SIZE: 16px; BORDER-TOP: medium none; CURSOR: pointer; BORDER-RIGHT: medium none; PADDING-TOP: 0px"
				value="立即登录" type="submit">
		</form>
		<DIV style="TEXT-ALIGN: center; MARGIN-TOP: -65px">
			<label for="rememberMe" title="下次不需要再登录">
				<input style="width:20px;" type="checkbox" id="rememberMe" name="rememberMe" ${rememberMe ? 'checked' : ''}/>
				 记住我（公共场所慎用）
			 </label>
		</DIV>
	</SECTION>
</BODY>
</HTML>
