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
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title>明细</title>
<script src="../js/card/flex.js?v=1.0"></script>
<script src="../js/common/XWPlug.bundle.min.js?v=1.0"></script>
<link href="../css/common/XWPlug.bundle.min.css?v=1.0" rel="stylesheet">
<link href="../css/card/public.css?v=1.0" rel="stylesheet">
<link href="../css/card/style.css?v=1.0" rel="stylesheet">
<link href="../css/card/iosSelect.css?v=1.0" rel="stylesheet">
</head>
<body>
	<section class="bg_write">
		<!-- <ul class="mx_tab clear">
			<li><span>零钱</span></li>
			<li class="active"><span>一卡通</span></li>
			<li><span>超级会员卡</span></li>
		</ul> -->
		<div class="mx_ser">
			<div class="sel_other">全部</div>
			<div class="sel_ykt">选择一卡通</div>
		</div>
		<c:if test="${cardEmpty eq 0}">
			<div class="await">
				<p>暂无交易记录</p>
				<img src="../images/user/jqqd.png">
			</div>
			<div class="popup_card"></div>
		</c:if>
		<ul class="mx_cont" id="orderList">
			<c:forEach items="${orderList}" var="list">

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
		</li> -->
		</ul>
	</section>
	<script src="../js/card/jquery.js?v=1.0"></script>
	<script src="../js/payment/orderDetail.js"></script>
	<script src="../js/card/iosSelect.js"></script>
	<script type="text/javascript">
		var selDom = $('.sel_other');
		var data1 = [ {
			"id" : "0",
			"value" : "全部"
		}, {
			"id" : "1",
			"value" : "收入"
		}, {
			"id" : "2",
			"value" : "支出"
		} ];
		selDom.bind('click', function() {
			var iosSelect = new IosSelect(1, [ data1 ], {
				title : '',
				itemHeight : 40,
				itemShowCount : 5,
				oneLevelId : 0,
				callback : function(selectOneObj) {
					selDom.attr('data-id', selectOneObj.id);
					selDom.html(selectOneObj.value);
				}
			});
		});

		var selDom1 = $('.sel_ykt');
		var data2 =${cardList};
		selDom1.bind('click', function() {
			var iosSelect = new IosSelect(1, [ data2 ], {
				title : '',
				itemHeight : 40,
				itemShowCount : 5,
				oneLevelId : 0,
				callback : function(selectOneObj) {
					selDom1.attr('data-id', selectOneObj.id);
					selDom1.html(selectOneObj.value);
					getRecordByCardId(selectOneObj.id);
				}
			});
		});
		function getRecordByCardId(cardId) {
			$.ajax({
				type : "POST",
				url : "../payment/orderDetailAjax.do",
				data : {
					"cardId" : cardId
				},
				dataType : "json",
				async : true,
				success : function(data) {
					if (data.resCode != 0) {
						XWalert(data.erroMsg);
					} else {
						var divContent = "";
						$.each(data.cardList, function(i) {
							var commentInfo = data.cardList[i];
							var num = i + 1;
							if (commentInfo.orderStatus == 0) {
								divContent += '<li onclick=getOrderDetail("'
										+ commentInfo.orderId + '")><h1>'
										+ commentInfo.merchantName + '<span>-'
										+ commentInfo.price + '元'
										+ '</span></h1><p>交易成功<time>'
										+ commentInfo.createTime
										+ '</time></p></li>';
							} else if (commentInfo.orderStatus == 1) {
								divContent += '<li onclick=getOrderDetail("'
										+ commentInfo.orderId + '")><h1>'
										+ commentInfo.merchantName + '<span>-'
										+ commentInfo.price + '元'
										+ '</span></h1><p>已退款<time>'
										+ commentInfo.createTime
										+ '</time></p></li>';
							} else if (commentInfo.orderStatus == 2) {
								divContent += '<li onclick=getOrderDetail("'
										+ commentInfo.orderId + '")><h1>'
										+ commentInfo.merchantName + '<span>-'
										+ commentInfo.price + '元'
										+ '</span></h1><p>交易失败<time>'
										+ commentInfo.createTime
										+ '</time></p></li>';
							}

						})
						$("#orderList").html(divContent);
					}
				},
				error : function(data) {
					XWalert("系统繁忙，请稍后重试");
				}
			});
		}
	</script>
</body>
</html>