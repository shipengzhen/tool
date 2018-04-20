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
<title>收款</title>
<script src="../js/common/XWPlug.bundle.min.js?v=1.0"></script>
<link href="../css/common/XWPlug.bundle.min.css?v=1.0" rel="stylesheet">
<script src="../js/common/flex.js?v=1.0"></script>
<link href="../css/common/public.css?v=1.0" rel="stylesheet">
<link href="../css/common/style.css?v=1.0" rel="stylesheet">
</head>
<body>

<script type="text/javascript" src="../js/common/app.js"></script>

<section>
	<div class="shop_des"></div>
	<div class="cont_warp">
		<p class="gathering_t">收款金额</p>
		<label class="gathering_m">￥<input id="moneyInput" type="text" value=""></label>
	</div>
	<div class="btn_warp">
		<a href="javascript:getPay();">确认收款</a>
	</div>
</section>
<script src="../js/common/jquery.js?v=1.0"></script>
<script src="../js/common/j_main.js?v=1.0"></script>
<script src="../js/merchant/refund.js?v=1.0"></script>
</body>
</html>