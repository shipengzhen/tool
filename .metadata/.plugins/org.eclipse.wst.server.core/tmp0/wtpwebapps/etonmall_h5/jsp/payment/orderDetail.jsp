<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<meta name="apple-mobile-web-app-status-bar-style"
	content="black-translucent">
<title>消费详情</title>
<script src="../js/card/flex.js?v=1.0"></script>
<script src="../js/common/XWPlug.bundle.min.js?v=1.0"></script>
<link href="../css/common/XWPlug.bundle.min.css?v=1.0" rel="stylesheet">
<link href="../css/card/public.css?v=1.0" rel="stylesheet">
<link href="../css/card/style.css?v=1.0" rel="stylesheet">
</head>
<body>
	<section class="bg_write">
		<div class="postage01">
			<img src="${cardDetail.merchantLogo}"> <span>${cardDetail.merchantName}</span>
		</div>
		<div class="postage02">-${cardDetail.price}元</div>
		<c:if test="${cardDetail.orderStatus eq 0}">
			<div class="postage03">交易成功</div>
		</c:if>
		<c:if test="${cardDetail.orderStatus eq 1}">
			<div class="postage03">已退款</div>
		</c:if>
		<c:if test="${cardDetail.orderStatus eq 2}">
			<div class="postage03">交易失败</div>
		</c:if>
		<ul class="postage04">
			<li>付款方式
				<p>一卡通${cardDetail.cardNo}</p>
			</li>
			<li>创建时间
				<p>${cardDetail.createTime}</p>
			</li>
			<li>订单号
				<p>${cardDetail.orderId}</p>
			</li>
			<c:if test="${cardDetail.orderStatus eq 0}">
			<li>退款码
				<p>商户可扫描该码进行退款</p>
			</li>
			</c:if>
		</ul>
		<c:if test="${cardDetail.orderStatus eq 0}">
			<div class="postage05">
				<img id="barCodeImage" src="">
			</div>
			<div class="postage06" id="barcode">${cardDetail.refundQr}</div>
		</c:if>
	</section>
	<script src="../js/card/jquery.js?v=1.0"></script>
	<script src="../js/payment/orderDetail.js?v=1.0"></script>
	<script src="../js/card/JsBarcode.all.min.js"></script>
	<script type="text/javascript">
		var barCode = $("#barcode").text();

		//生成 条形码
		JsBarcode("#barCodeImage", barCode, {
			// format: "CODE39",
			width : 3,
			displayValue : false
		});
	</script>
</body>
</html>