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
<title>店铺名称</title>
<script src="../js/common/flex.js?v=1.0"></script>
<link href="../css/common/public.css?v=1.0" rel="stylesheet">
<link href="../css/common/style.css?v=1.0" rel="stylesheet">
</head>
<body>
<section>
	<dl class="shop_info">
		<dt><img src="${storeInfo.merchantLogo }"></dt>
		<dd>
			<h1>${storeInfo.storeName }</h1>
			<p>地址：${storeInfo.address }</p>
			<p>电话：<span>${storeInfo.contractPhone }</span></p>
		</dd>
	</dl>
	<div class="shijian"><i class="icon_sp"><img src="../images/merchant/dianpu_icon_time.png"></i>营业时间：${storeInfo.bussinessHours }</div>
	<div class="shangquan"><i class="icon_sp"><img src="../images/merchant/dianpu_iicon_address.png"></i>商圈：${storeInfo.bussinessCircleName }<span>距您${storeInfo.distance }</span></div>
	<div class="goods_info">
		<div class="goods_title">店铺展示</div>
		<div class="img_box">
			<c:forEach items="${storeInfo.imagesUrl }" var="imageUrl">
				<img src="${imageUrl }">
			</c:forEach>
		</div>
	</div>
	<div class="goods_info mt20">
		<div class="goods_title">店铺公告</div>
		<ul class="info_list">
			<li>${storeInfo.storeNotice }</li>
		</ul>
	</div>

</section>
<script src="../js/common/jquery.js?v=1.0"></script>
<script src="../js/common/j_main.js?v=1.0"></script>
</body>
</html>