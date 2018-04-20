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
<title>消费记录</title>
<script src="../js/card/flex.js?v=1.0"></script>
<script src="../js/common/XWPlug.bundle.min.js?v=1.0"></script>
<link href="../css/common/XWPlug.bundle.min.css?v=1.0" rel="stylesheet">
<link href="../css/card/public.css?v=1.0" rel="stylesheet">
<link href="../css/card/style.css?v=1.0" rel="stylesheet">
</head>
<body>
	<section class="bg_write">
		<ul class="mx_tab clear">
			<li onclick="showTotal()" id="showTotal"><span>全部</span></li>
			<li class="active" onclick="showOut()" id="showOut"><span>支出</span></li>
			<li onclick="showIn()" id="showIn"><span>收入</span></li>
		</ul>
		<ul class="mx_cont">

			<c:forEach items="${orderList}" var="list">
				<%-- <li onclick="getOrderDetail('${list.orderId}')">
					<h1>${list.merchantName}<span>-${list.price}元</span>
					</h1>
					<p>
						<time>${list.createTime}</time>
					</p>
				</li> --%>
				<li onclick="getOrderDetail('${list.orderId}')">
					<h1>${list.merchantName}<span>-${list.price}元</span>
					</h1>
					<c:if test="${list.orderStatus eq 0}">
					<p>交易成功
						<time>${list.createTime}</time>
					</p>
					</c:if>
					<c:if test="${list.orderStatus eq 1}">
					<p>已退款
						<time>${list.createTime}</time>
					</p>
					</c:if>
					<c:if test="${list.orderStatus eq 2}">
					<p>交易失败
						<time>${list.createTime}</time>
					</p>
					</c:if>
				</li>
			</c:forEach>
			<!-- <li>
			<h1>华夏良子（北园店）<span>-56.34</span></h1>
			<p>余额：143.66<time>2017-10-30</time></p>
		</li>
		<li>
			<h1>华夏良子（北园店）<span>-56.34</span></h1>
			<p>余额：143.66<time>2017-10-30</time></p>
		</li>
		<li>
			<h1>华夏良子（北园店）<span>-56.34</span></h1>
			<p>余额：143.66<time>2017-10-30</time></p>
		</li> -->
		</ul>
	</section>
	<script src="../js/card/jquery.js?v=1.0"></script>
	<script src="../js/payment/orderDetail.js?v=1.0"></script>
</body>
</html>