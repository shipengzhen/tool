<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>
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
<title>订单管理</title>
<script src="../js/card/flex.js?v=1.0"></script>
<script src="../js/common/XWPlug.bundle.min.js?v=1.0"></script>
<link href="../css/common/XWPlug.bundle.min.css?v=1.0" rel="stylesheet">
<link href="../css/card/public.css?v=1.0" rel="stylesheet">
<link href="../css/card/style.css?v=1.0" rel="stylesheet">
</head>
<body>
<section>
	<div class="pwcode">
		<!--% <a href="javascript:;">全部</a>
		<a href="javascript:cardOrder();">一卡通订单</a>
		<a href="javascript:superCardOrder();">超级会员卡订单</a> %-->
		<a href="../payment/turnToMyGoods.do">商品订单</a>
	</div>
</section>
<script src="../js/card/jquery.js?v=1.0"></script>
<script src="../js/payment/myList.js?v=1.0"></script>
</body>
</html>