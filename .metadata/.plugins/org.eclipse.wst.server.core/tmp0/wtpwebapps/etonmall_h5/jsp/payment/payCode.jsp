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
<title>向商家付钱</title>
<script src="../js/card/flex.js?v=1.0"></script>
<script src="../js/common/XWPlug.bundle.min.js?v=1.0"></script>
<link href="../css/common/XWPlug.bundle.min.css?v=1.0" rel="stylesheet">
<link href="../css/card/public.css?v=1.0" rel="stylesheet">
<link href="../css/card/style.css?v=1.0" rel="stylesheet">
</head>
<body>
	<section class="bg_bule">
		<div class="code_warp">
			<div class="barcode">
				<img id="barcode" src="">
			</div>
			<div class="ercode1"></div>
			<div class="code_name">付款码</div>
			<div class="pay_mode" id="payMode" onclick="showPays()">
				<c:forEach items="${cardList}" var="list" varStatus="status">
					<c:if test="${status.first}">
						<i><img src="${list.smallLogoUrl}"></i>
						<p>${list.cardName}(${list.cardNo})</p>
						<input type="hidden" value="${list.realCardNo}" />
						<span>优先使用此支付方式付款</span>
					</c:if>
				</c:forEach>
			</div>
		</div>
		<div class="paycode_note">如付款失败，请尝试其他付款方式</div>
	</section>
	<div class="popup_warp" id="pays">
		<div class="pay_info">
			<div class="pay_title">
				<a class="close" href="javascript:;"></a>选择优先支付方式
			</div>
			<dl>
				<div class="pay_note">优先使用所选付款方式付款，如付款失败将尝试使用其他付款方式完成付款</div>
				<!-- <dd><i class="icon_pay"><img src="images/pay_ykt_icon.png"></i>零钱<span>(剩余¥298)</span></dd> -->
				<dd class="pay_gray">
					<i class="icon_pay"><img
						src="../images/card/pay_ykt_icon01.png"></i>零钱<span>(剩余¥0)</span>
				</dd>
				<!-- <dd><i class="icon_pay"><img src="images/pay_ykt_logo.png"></i>一卡通商务卡<span>(￥100)</span></dd>
			<dd><i class="icon_pay"><img src="images/pay_ykt_logo.png"></i>一卡通商务卡<span>(￥100)</span></dd>
			<dd><i class="icon_pay"><img src="images/pay_ykt_logo.png"></i>一卡通商务卡<span>(￥100)</span></dd>
			<dd><i class="icon_pay"><img src="images/pay_ykt_logo.png"></i>一卡通商务卡<span>(￥100)</span></dd> -->
				<c:if test="${empty cardList}">
					<div class="pay_lack">
						卡内余额不足<a href="javascript:;">绑定新卡></a>
					</div>
				</c:if>
				<!--      -->
				<c:if test="${!empty cardList}">
					<c:forEach items="${cardList}" var="list">
						<dd>
							<a style="color: black"
								href="javascript:selectPay('${list.smallLogoUrl}','${list.cardName}','${list.realCardNo}','${list.cardNo}');">
								<i class="icon_pay"><img src="${list.smallLogoUrl}"></i>${list.cardName}<span>(￥${list.cardBalance})</span>
							</a>
						</dd>
					</c:forEach>
				</c:if>

			</dl>
		</div>
	</div>
	<!--充值-->
	<div class="popup_warp">
		<div class="pay_info">
			<div class="pay_title">
				<a class="close" href="javascript:;"></a>选择优先支付方式
			</div>
			<dl>
				<div class="pay_lack">
					<i class="icon_pay"><img
						src="../images/card/pay_ykt_icon01.png"></i>零钱<span>(剩余¥0)</span><a
						href="javascript:;">去充值></a>
				</div>
				<div class="pay_lack">
					暂未绑定新卡<a href="javascript:;">去绑定></a>
				</div>
			</dl>
		</div>
	</div>
	<script src="../js/card/jquery.js?v=1.0"></script>
	<script src="../js/card/j_main.js?v=1.0"></script>
	<script src="../js/card/JsBarcode.all.min.js"></script>
	<script src="../js/card/jquery_qrCode.js"></script>
	<script type="text/javascript">
		function selectPay(url,cardName,realCardNo,cardNo) {
			$("#payMode").find("img").attr("src", url);
			$("#payMode").find("p").text(cardName + "(" + cardNo + ")");
			$("#payMode").find("input").val(realCardNo);
			$("#pays").css("display", "none");
			getQrCode(realCardNo);
		}

		function showPays() {
			$("#pays").css("display", "block");
		}
		var qrCode;
		var realCardNo = $("#payMode").find("input").val();
		getQrCode(realCardNo);
		var B = setInterval(
				function() {
					$
							.ajax({
								type : "POST",
								url : "../payment/checkOrderStatus.do",
								data : {
									"userQr" : qrCode
								},
								dataType : "json",
								async : true,
								success : function(data) {
									var code = data.resCode;
									if (code == 0) {
										var userCode = qrCode;
										//刷新二维码 防止一直跳转成功或失败
										var realCardNo = $("#payMode").find(
												"input").val();
										getQrCode(realCardNo);
										window.location.href = "../payment/payResult.do?qrCode="
												+ userCode;
									} else if (code == 1) {
										//错误
										XWalert(data.erroMsg);
									} else if (code == 2) {
										//需要支付密码
										//XWalert(code + "===" + qrCode);
										window.location.href = "../payment/payCertain.do?qrCode="
												+ qrCode;
									} else if (code == 3) {
										//刷新二维码
										var realCardNo = $("#payMode").find(
												"input").val();
										getQrCode(realCardNo);
									} else if (code == 4) {
										//刷新二维码 防止一直跳转成功或失败
										var realCardNo = $("#payMode").find(
												"input").val();
										getQrCode(realCardNo);
										XWalert("支付失败");
									}
								},
								error : function(data) {
									XWalert("系统繁忙，请稍后重试");
								}
							});
				}, 5000);

		function getQrCode(realCardNo) {
			if (!realCardNo) {
				XWalert("请绑定卡片");
				window.clearInterval(B);
				return;
			}
			var date=new Date();
			$.ajax({
				type : "POST",
				url : "../payment/qrcodeCreate.do?date="+date,
				data : {
					"cardId" : realCardNo
				},
				dataType : "json",
				async : true,
				success : function(data) {
					if (data.resCode != 0) {
						XWalert(data.erroMsg);
					} else {
						var qrCode1 = data.qrCode;
						qrCode = qrCode1;
						$(".ercode1").empty();
						$("#barcode").empty();
						// 生成 条形码
						JsBarcode("#barcode", qrCode, {
							//format: "CODE39",
							width : 3,
							displayValue : false
						});
						// 生成 二维码
						$(".ercode1").qrcode(qrCode);
					}
				},
				error : function(data) {
					XWalert("系统繁忙，请稍后重试");
				}
			});
		}
	</script>
</body>
<script type="text/javascript">
(function()  
		{  
		var agent = navigator.userAgent.toLowerCase();  
		var iLastTouch = null; //缓存上一次tap的时间  
		if (agent.indexOf('iphone') >= 0 || agent.indexOf('ipad') >= 0) //检测是否是ios  
		{  
		document.body.addEventListener('touchend', function(event)  
		{  
		var iNow = new Date().getTime();  
		iLastTouch = iLastTouch || iNow + 1 /** 第一次时将iLastTouch设为当前时间+1 */ ;  
		var delta = iNow - iLastTouch;  
		if (delta < 500 && delta > 0)  
		{  
		event.preventDefault();  
		return false;  
		}  
		iLastTouch = iNow;  
		}, false);  
		}  
		})();  
</script>
</html>