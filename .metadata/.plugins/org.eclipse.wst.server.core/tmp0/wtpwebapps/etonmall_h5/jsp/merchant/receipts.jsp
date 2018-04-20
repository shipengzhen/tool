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
<title>店铺账单</title>
<script src="../js/common/flex.js?v=1.0"></script>
<link href="../css/common/public.css?v=1.0" rel="stylesheet">
<link href="../css/common/swiper.min.css?v=1.0" rel="stylesheet">
<link href="../css/common/style.css?v=1.0" rel="stylesheet">
</head>
<body>
<section class="bg_bule shop_datas">
	<div class="swiper-container">
		<div class="swiper-wrapper">
		<c:forEach items="${receipts }" var="receipt">
			<div class="swiper-slide">
				<div class="shop_title">
					<p>${receipt.key }</p>
				</div>
				<div class="shop_datainfo">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th>日期</th>
						<th>收款</th>
					</tr>
					<c:forEach items="${receipt.value }" var="oneReceipt">
						<tr>
							<td>${oneReceipt.showName }</td>
							<td>${oneReceipt.amount }</td>
						</tr>
					</c:forEach>
					</table>
				</div>
			</div>
		</c:forEach>
		</div><div class="swiper-pagination"></div>
	</div>
</section>
<script src="../js/common/jquery.js?v=1.0"></script>
<script src="../js/common/j_main.js?v=1.0"></script>
<script src="../js/common/swiper.min.js?v=1.0"></script>
<script type="text/javascript">
	var w = $('.swiper-wrapper').width();
	var b = $('.wiper-slide').width();
	new Swiper('.swiper-container',{
		pagination: '.swiper-pagination',
        effect: 'coverflow',
        grabCursor: true,
		loop:false,
		width:w,
		centeredSlides: true,
		slidesPerView:'auto',
		spaceBetween:b,
		slideToClickedSlide:true,
		observer:true,
		observeParents:true,
		coverflow: {
            rotate: 50,
            stretch: 0,
            depth: 200,
            modifier: 1,
            slideShadows : true
        }
	});
</script>
</body>
</html>