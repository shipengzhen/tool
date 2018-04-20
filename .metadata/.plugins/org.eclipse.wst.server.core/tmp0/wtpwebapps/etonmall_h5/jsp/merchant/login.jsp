<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta content="no-cache,must-revalidate" http-equiv="Cache-Control">
<meta content="no-cache" http-equiv="pragma">
<meta content="0" http-equiv="expires">
<meta content="telephone=no, address=no" name="format-detection">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
<title>商户登录</title>
<script src="../js/common/XWPlug.bundle.min.js?v=1.0"></script>
<script src="../js/common/flex.js?v=1.0"></script>
<link href="../css/common/XWPlug.bundle.min.css?v=1.0" rel="stylesheet">
<link href="../css/common/public.css?v=1.0" rel="stylesheet">
<link href="../css/common/style.css?v=1.0" rel="stylesheet">
</head>
<body>
<section class="login">
	<div class="login_logo"><img src="../images/merchant/logo.png"></div>
	<ul class="login_form">
		<li><input id="telNum" type="tel" placeholder="请输入手机号"  maxlength="11"></li>
		<li><input id="merchantId" type="text" placeholder="请输入商户号"></li>
		<li><input id="validateCode" class="yzm" type="text" placeholder="请输入验证码" maxlength="6" ><input id="recode" type="button" class="login_yzm" value="获取验证码" onclick="javascript:getCode();"></li>
	
	</ul>
	<div class="btn_warp1">
		<a href="javascript:login();">登录</a>
	</div>
	<div class="login_note">
		<p>您尚未开通商户权限</p>
		<p>如有需要，请致电：4006186900</p>
	</div>
</section>
<script src="../js/common/jquery.js?v=1.0"></script>
<script src="../js/common/j_main.js?v=1.0"></script>
<script src="../js/merchant/login.js?v=1.0"></script>
</body>
</html>