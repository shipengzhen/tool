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
<title>发现</title>
<script src="../js/common/flex.js?v=1.0"></script>
<link href="../css/common/public.css?v=1.0" rel="stylesheet">
<link href="../css/common/swiper.min.css?v=1.0" rel="stylesheet">
<link href="../css/common/style.css?v=1.0" rel="stylesheet">
</head>
<body>

<script type="text/javascript" src="../js/common/app.js"></script>

<div class="sel_bar">
	<ul class="sel_tab clear" id="tab">
	<c:if test="${empty activeDetailType }">
		<li id="typeTab"><span>${activeType.name }</span></li>
	</c:if>
	<c:if test="${not empty activeDetailType }">
		<li id="typeTab"><span>${activeDetailType.name }</span></li>
	</c:if>
		<li id="regionTab"><span>${activeArea.name }</span></li>
		<li id="sortTab"><span>智能排序</span></li>
	</ul>
</div>

<div class="sel_cont" id="typeDiv">
	<div class="sel_row">
		<ul>
			<li onclick="javascript:selectAllType();">
				全部分类
			</li>
			<c:forEach items="${typeList }" var="type">
				<c:if test="${type.code eq activeType.code }">
					<li class="active" id="type${type.code }" onclick="javascript:selectType('${type.code}');">
						${type.name }
					</li>
				</c:if>
				<c:if test="${type.code ne activeType.code }">
					<li id="type${type.code }" onclick="javascript:selectType('${type.code}');">
						${type.name }
					</li>
				</c:if>
			</c:forEach>
		</ul>
		<ul id="detailTypeUl">
			<c:if test="${not empty activeDetailType }">
				<c:forEach items="${detailTypeList }" var="detailType">
				<c:if test="${detailType.code eq activeDetailType.code }">
					<li class="active" onclick="javascript:selectDetailType('${detailType.code}');">
						${detailType.name }
					</li>
				</c:if>
				<c:if test="${detailType.code ne activeDetailType.code }">
					<li onclick="javascript:selectDetailType('${detailType.code}');">
						${detailType.name }
					</li>
				</c:if>
			</c:forEach>
			</c:if>
			<c:if test="${empty activeDetailType }">
				<c:forEach items="${detailTypeList }" var="detailType">
					<li onclick="javascript:selectDetailType('${detailType.code}');">
						${detailType.name }
					</li>
				</c:forEach>
			</c:if>
		</ul>
	</div>
</div>

<div class="sel_cont" id="areaDiv">
	<div class="sel_warp">
		<ul>
			<c:if test="${activeArea.code eq '7777'}">
				<li class="active" onclick="javascript:selectArea('7777');">
					全部
				</li>
			</c:if>
			<c:if test="${activeArea.code ne '7777'}">
				<li onclick="javascript:selectArea('7777');">
					全部
				</li>
			</c:if>
			<c:forEach items="${areaList }" var="area">
				<c:if test="${area.code eq activeArea.code }">
					<li class="active" onclick="javascript:selectArea('${area.code}');">
						${area.name }
					</li>
				</c:if>
				<c:if test="${area.code ne activeArea.code }">
					<li onclick="javascript:selectArea('${area.code}');">
						${area.name }
					</li>
				</c:if>
			</c:forEach>
		</ul>
	</div>
</div>

<div class="sel_cont" id="sortDiv">
	<div class="sel_warp">
		<ul>
			<li class="active" onclick="javascript:selectSort();">
				按距离排序
			</li>
		</ul>
	</div>
</div>

<div class="find">
	<section>
		<div class="find_banner">
			<div class="swiper-container">
				<div class="swiper-wrapper">
					<c:forEach items="${images }" var="image">
						<a class="swiper-slide" href="javascript:appObj.appHrefBlank('${image.dictValue }');"><img src="${image.dictName }"></a>
					</c:forEach>
				</div>
				<div class="swiper-pagination"></div>
			</div>
		</div>
		<c:forEach items="${storeList }" var="store">
			<dl>
				<a href="../merchant/storeInfoPage.do?storeId=${store.storeId }">
					<dt><img src="${store.merchantLogo }"></dt>
					<dd>
						<h1>${store.storeName }</h1>
						<h2>${store.bussinessCircleName }<span>${store.distance }</span></h2>
						<p>地址：${store.address }</p>
						<p>电话：${store.contractPhone }</p>
					</dd>
				</a>
			</dl>
		</c:forEach>
	</section>
	
	<input name="regionId" type="hidden" value="${activeArea.code }" id="regionId" />
	<input name="typeId" type="hidden" value="${activeType.code }" id="typeId" />
	<input name="detailTypeId" type="hidden" value="${activeDetailType.code }" id="detailTypeId">
</div>
<script src="../js/common/jquery.js?v=1.0"></script>
<script src="../js/common/j_main.js?v=1.0"></script>
<script src="../js/common/swiper.min.js?v=1.0"></script>
<script src="../js/common/position.js?v=1.0"></script>
<script src="../js/merchant/find.js?v=1.0"></script>
<script>
	var swiper = new Swiper('.swiper-container', {
		pagination: '.swiper-pagination',
		paginationClickable: true,
		loop:true
	});
</script>
</body>
</html>