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
<title>一卡通中心</title>
<script src="../js/card/flex.js?v=1.0"></script>
<script src="../js/common/XWPlug.bundle.min.js?v=1.0"></script>
<link href="../css/common/XWPlug.bundle.min.css?v=1.0" rel="stylesheet">
<link href="../css/card/public.css?v=1.0" rel="stylesheet">
<link href="../css/card/style.css?v=1.0" rel="stylesheet">
</head>
<body>
<script src="../js/common/app.js?v=1.0"></script>
<!-- <div class="sel_bar">
	<ul class="sel_tab1 clear">
		<li class="active" onclick="secCard()" id="card"><span>虚拟卡</span></li>
		<li onclick="secSuperCard()" id="superCard"><span>实体卡</span></li>
	</ul>
</div> -->
<div class="find">
	<section class="pb75">
		<!-- <div class="card_box"><img src="images/card.png"><span>虚拟卡</span></div>
		<div class="card_box"><img src="images/card.png"><span>虚拟卡</span></div>
		<div class="card_box"><img src="images/card.png"><span>虚拟卡</span></div> -->
		<c:forEach items ="${cardList}" var = "list">
			<div class="card_box" onclick="getPayCardDetail('${list.cardType}')"><img src="${list.logoUrl}"></div>
		</c:forEach>
		
	</section>
</div>
<!-- <div class="await" style="display:none;">
				<p>
				尚未开启，敬请期待
				</p >
				<img src="../images/user/jqqd.png">
</div> -->
<div class="popup_card"></div>
<script src="../js/card/jquery.js?v=1.0"></script>
<script src="../js/card/cardManager.js?v=1.0"></script>
</body>
</html>