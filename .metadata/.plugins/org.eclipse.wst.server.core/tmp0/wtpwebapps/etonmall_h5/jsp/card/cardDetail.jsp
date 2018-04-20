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
<style rel="stylesheet">
 dt.dtlink:after{background:none;}
</style>
</head>
<body>
<section>
	<div class="card_lib">
		<div class="card_warp">
			<img src="${card.logoUrl}">
			<div class="card_no">${card.realCardNo}</div>
		</div>
		<div class="card_a">
			余额：${card.cardBalance}
			<c:if test="${card.isUsed eq 0}">
			<time>有效期：${card.validTime}</time>
			</c:if>
			<c:if test="${card.isUsed eq 1}">
			<time>已过期</time>
			</c:if>
		</div>
	</div>
	<dl class="card_list">
		<dt class="dtlink" onclick="getOrderListByCardId('${card.realCardNo}')">消费记录</dt>
		<dt class="dtlink" onclick="recharge()">充值</dt>
		<dt>卡详情</dt>
		<dd>
			<p>${card.cardDetail}</p>
		</dd>
		<dt>适用商户</dt>
		<dd>
			<p>${card.appliMerchant}</p>
		</dd>
		<div class="clone_dt" onclick="turnUnbindCard('${card.realCardNo}')">解绑</div>
	</dl>

</section>
<script src="../js/card/jquery.js?v=1.0"></script>
<script src="../js/card/cardManager.js?v=1.0"></script>
<!-- <script src="../js/card/j_main.js?v=1.0"></script> -->
</body>
</html>