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
<title>一卡通详情</title>
<script src="../js/card/flex.js?v=1.0"></script>
<script src="../js/common/XWPlug.bundle.min.js?v=1.0"></script>
<link href="../css/common/XWPlug.bundle.min.css?v=1.0" rel="stylesheet">
<link href="../css/card/public.css?v=1.0" rel="stylesheet">
<link href="../css/card/style.css?v=1.0" rel="stylesheet">
</head>
<body>
<section>
	<div class="card_det">
		<img src="${card.logoUrl}">
		<p>增值服务更高，消费快捷方便<br></p>
	</div>
	<dl class="card_list">
		<dt>卡详情</dt>
		<dd>
			<p>${card.cardDetail}</p>
		</dd>
		<dt>使用注意事项</dt>
		<dd>
			<p>${card.attention}</p>
		</dd>
		<dt>适用商户</dt>
		<dd>
			<p>${card.appliMerchant}</p>
		</dd>
	</dl>
	<div class="btn_warp btn_gray"><a href="javascript:;">暂时无法购买</a></div>
</section>
<script src="../js/card/jquery.js?v=1.0"></script>
<script src="../js/card/cardManager.js?v=1.0"></script>
</body>
</html>