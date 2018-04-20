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
<title>我的卡包</title>
<script src="../js/card/flex.js?v=1.0"></script>
<script src="../js/common/XWPlug.bundle.min.js?v=1.0"></script>
<link href="../css/common/XWPlug.bundle.min.css?v=1.0" rel="stylesheet">
<link href="../css/card/public.css?v=1.0" rel="stylesheet">
<link href="../css/card/style.css?v=1.0" rel="stylesheet">
</head>
<body>
	<script src="../js/common/app.js?v=1.0"></script>
	<div class="sel_bar">
		<ul class="sel_tab1 clear">
			<li class="active" id="card" onclick="secCard()"><span>一卡通</span></li>
			<li id="superCard" onclick="secSuperCard()"><span>超级会员卡</span></li>
		</ul>
	</div>
	<div class="find">
		<section class="pb75">
			<!-- <div class="card_none">尚未开启，敬请期待……</div> -->
			<!-- <div class="card_box"><img src="images/card.png"><span>虚拟卡</span></div>
		<div class="card_box"><img src="images/card.png"><span>虚拟卡</span></div>
		<div class="card_box"><img src="images/card.png"><span>虚拟卡</span></div> -->
			<div id="cardList">
				<c:if test="${empty cardList}">
					<div class="await">
						<p>暂未绑定一卡通</p>
						<img src="../images/user/jqqd.png">
					</div>
				</c:if>
				<c:forEach items="${cardList}" var="list">
					<div class="card_lib" onclick="getCardDetail('${list.realCardNo}')">
						<div class="card_warp">
							<img src="${list.logoUrl}">
							<div class="card_no">${list.realCardNo}</div>
						</div>
						<div class="card_a">
							余额：${list.cardBalance}元
							<c:if test="${list.isUsed eq 0}">
								<time>有效期：${list.validTime}</time>
							</c:if>
							<c:if test="${list.isUsed eq 1}">
								<time>已过期</time>
							</c:if>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="await" id="superCardList" style="display:none">
						<p>尚未开启，敬请期待</p>
						<img src="../images/user/jqqd.png">
					</div>
		</section>
	</div>
	<div class="toolbar">
		<ul class="tool_tab clear">
			<li onclick="payNewCard()">购买新卡</li>
			<li onclick="card_pop()">绑定新卡
				<div class="card_pop">
					<a href="javascript:appObj.normalTdcode(autoBindCard);">扫描绑定</a> <a
						href="../cardManager/bindCardPage.do">手动绑定</a>
				</div>
			</li>
		</ul>
	</div>
	<div class="popup_card"></div>
	<script src="../js/card/jquery.js?v=1.0"></script>
	<script src="../js/card/cardManager.js?v=1.0"></script>
</body>
</html>