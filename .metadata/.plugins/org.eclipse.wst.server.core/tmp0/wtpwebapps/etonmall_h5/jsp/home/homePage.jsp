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
<title>首页</title>
<script src="../js/common/XWPlug.bundle.min.js?v=1.0"></script>
<link href="../css/common/XWPlug.bundle.min.css?v=1.0" rel="stylesheet">
<script src="../js/common/flex.js?v=1.0"></script>
<link href="../css/common/public.css?v=1.0" rel="stylesheet">
<link href="../css/common/swiper.min.css" rel="stylesheet">
<link href="../css/home/style.css?v=1.0" rel="stylesheet">
</head>
<body>
	<script src="../js/common/app.js?v=1.0"></script>
	<section class="home">
		<div class="index_header">
			<div class="index_ser clear">
				<a href="javascript:;" class="sel_city"><span id="cityName">济南</span><i
					class="icon_jiantou"></i></a> <label class="ser_warp"><i
					class="icon_search"></i><input type="text" value="一卡通充值满200赠10"
					readonly></label>
			</div>
			<ul class="index_fun clear">
				<c:forEach items="${bannerList}" var="function">
					<li><a href=${function.FUNCTIONURL}> <img
							src="${function.IMAGE}">
							<p>${function.FUNCTIONNAME}</p>
					</a></li>
				</c:forEach>
			</ul>
		</div>
		<ul class="fun_list clear">
			<c:forEach items="${gonggeList}" var="function">
				<li><a href="${function.FUNCTIONURL}"> <img
						src="${function.IMAGE}">
						<p>${function.FUNCTIONNAME}</p>
				</a></li>
			</c:forEach>
		</ul>

		<div class="module_warp mt20">
			<div class="module_title">
				<h1>专属权益</h1>
				<a class="module_more" href="outerJump.do?pageType=1">更多></a>
			</div>
			<div class="swiper-container">
				<div class="swiper-wrapper">
					<c:forEach items="${rights}" var="right">
						<a class="swiper-slide"
							href="outerJump.do?pageType=3&goodsId=${right.GOODSID}"><img
							src="${right.GOODSIMAGE}"></a>
					</c:forEach>
				</div>
			</div>
		</div>

		<div class="module_warp mt20">
			<a href="outerJump.do?pageType=0"><img
				src="${scroll.GOODSIMAGE }"></a>
		</div>

		<div class="module_warp mt20">
			<div class="module_title">
				<h1>精品推荐</h1>
				<a class="module_more" href="outerJump.do?pageType=0">更多></a>
			</div>
			<div class="module01 clear">
				<c:forEach items="${goods1}" var="good1">
					<a href="outerJump.do?pageType=3&goodsId=${good1.GOODSID}">
						<h2>${good1.GOODSNAME }</h2>
						<h3>${good1.GOODSDESC }</h3> <img src="${good1.GOODSIMAGE}">
					</a>
				</c:forEach>
			</div>
			<div class="module02 clear">
				<c:forEach items="${goods2}" var="good2">
					<a href="outerJump.do?pageType=3&goodsId=${good2.GOODSID}">
						<h2>${good2.GOODSNAME }</h2>
						<h3>${good2.GOODSDESC }</h3> <img src="${good2.GOODSIMAGE}">
					</a>
				</c:forEach>
			</div>
		</div>

		<div class="module_warp mt20">
			<div class="module_title">
				<h1>发现好货</h1>
				<a class="module_more" href="outerJump.do?pageType=0">更多></a>
			</div>
			<div class="module03 clear">
				<c:forEach items="${goods3}" var="good3">
					<a href="outerJump.do?pageType=3&goodsId=${good3.GOODSID}">
						<h2>${good3.GOODSNAME}</h2>
						<p>${good3.PRICE}<del>${good3.ORIGINALPRICE}</del>
						</p> <img src="${good3.GOODSIMAGE}">
					</a>
				</c:forEach>
			</div>
		</div>
	</section>

	<section class="index_search">
		<div class="inp_ser clear">
			<label class="inp_warp"><i class="icon_search01"></i><input
				id="searchInput" type="text" maxlength="15"><a
				href="javascript:search();" class="btn_ss">搜索</a></label> <a
				href="javascript:cancelSearch();" class="btn_qx">取消</a>
		</div>
		<div id="searchHot">
			<dl class="hot_search">
				<dt>热搜</dt>
				<dd>
					<a href="../cardManager/bindCardPage.do">绑卡</a> <a
						href="../cardManager/unbindCardPage.do">解绑</a> <a
						href="../cardManager/getCardDetailByType.do?cardType=1014">商务卡</a>
				</dd>
			</dl>
			<div class="swarp mt20">
				<div class="ser_title">历史搜索</div>
				<div id="history"></div>
			</div>
			<div class="btn_qk" onclick="clearHistory()">
				<span>清空历史搜索</span>
			</div>
		</div>
		<div id="searchResult" class="swarp mt20" style="display: none;">
		</div>
	</section>

	<section class="city_warp">
		<div class="city_title">
			<a href="javascript:;" class="close_city"></a>选择城市
		</div>
		<!-- <div class="city_ser">
			<label class="inp_warp01"><i class="icon_search01"></i><input
				id="searchCityInput" type="text" placeholder="一卡通充值满200赠10"><a href="javascript:searchCity();"
				class="btn_ss">搜索</a></label>
		</div> -->
		<div class="city_box">
			<div class="ser_title">山东省</div>
			<div class="city_sel">
				<dt>根据您的定位推荐</dt>
				<dd>
					<a href="javascript:selectLoactionCity();" class="set"
						id="locationCity">请在手机-设置中开启定位服务功能</a>
				</dd>
				<dt>历史记录</dt>
				<dd id="dd_history_list">
					<c:forEach items="${historyCitys }" var="historyCity">
						<a href="javascript:selectCity('${historyCity.code }');">${historyCity.name }</a>
					</c:forEach>
				</dd>
				<dt>选择城市</dt>
				<dd>
					<c:forEach items="${citys}" var="city">
						<a href="javascript:selectCity('${city.code }');">${city.name }</a>
					</c:forEach>
				</dd>
			</div>
		</div>

	</section>

	<script src="../js/common/position.js?v=1.0"></script>
	<script src="../js/common/jquery.js?v=1.0"></script>
	<script src="../js/common/swiper.min.js?v=1.0"></script>
	<script src="../js/home/j_main.js?v=1.0"></script>
	<script type="text/javascript">
		var w = $('.swiper-wrapper').width();
		var b = $('.wiper-slide').width();
		new Swiper('.swiper-container', {
			loop : true,
			width : w,
			centeredSlides : true,
			slidesPerView : 'auto',
			spaceBetween : b,
			slideToClickedSlide : true,
			observer : true
		//observeParents : true
		});

		var qrCode = function(code) {
			if (code.indexOf("app.ecton.com.cn:808/APPDownload/") != -1) {
					var param = code.split("?")[1].split("&");
					code = param[0].split("=")[1];
					var operatorId = param[1].split("=")[1];
					var storeId = param[2].split("=")[1];
					appObj.appHrefBlank("payment/userScan.do?qrCode=" + code
							+ "&operatorId=" + operatorId + "&storeId=" + storeId);
			} else {
				appObj.appHrefBlank("payment/userScan.do?qrCode=" + code);
			}
		}
	</script>
</body>
</html>