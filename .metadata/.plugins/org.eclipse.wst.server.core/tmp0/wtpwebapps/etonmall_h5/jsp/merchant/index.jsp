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
<title>商户首页</title>
<script src="../js/common/flex.js?v=1.0"></script>
<script src="../js/common/XWPlug.bundle.min.js?v=1.0"></script>
<link href="../css/common/XWPlug.bundle.min.css?v=1.0" rel="stylesheet">
<link href="../css/common/public.css?v=1.0" rel="stylesheet">
<link href="../css/common/style.css?v=1.0" rel="stylesheet">
</head>
<body>

<script type="text/javascript" src="../js/common/app.js"></script>

<section class="sh_bg">
	<div class="sh_warp" style="margin-top:2rem">
		<div class="shop_logo"><div class="logo_warp"><img src="${merchantLogo }"></div></div>
		<div class="shop_info01">
			<h2>${merchantName }<h2>
			<h3>当前账号：${operatorName }<h3>
		</div>
	</div>
	<c:forEach items="${operatorAuthority }" var="authority">
		<c:if test="${authority eq '0001' }">
			<dl class="sh_warp">
				<dt>商户信息</dt>
				<dd>
					<p>商户号：${operatorAttributes.merchantId }</p>
					<p>商户类型：${operatorAttributes.merchantType }</p>
					<p>地区：${operatorAttributes.city }</p>
					<c:if test="${roleId eq '0001' }">
						<p>负责人手机：${operatorAttributes.managerTelNum }</p>
						<p>已上线门店：${operatorAttributes.storeNum }家</p>
						<p>待审核门店：${operatorAttributes.auditedStoreNum }家</p>
					</c:if>
					<c:if test="${roleId eq '0002' }">
						<p>门店电话：${operatorAttributes.storeTelNum }</p>
						<p>门店地址：${operatorAttributes.storeAddress }</p>
					</c:if>
				</dd>
			</dl>
		</c:if>
		<c:if test="${authority eq '0002' }">
			<dl class="sh_warp">
				<dt>店铺公告</dt>
				<dd>
					<p>${operatorAttributes.storeNotice }</p> 
				</dd>
			</dl>
		</c:if>
		<c:if test="${authority eq '0003' }">
			<dl class="sh_warp">
				<dt>人员信息</dt>
				<dd>
					<p>职位：${operatorAttributes.position }</p>
					<p>手机号：${operatorAttributes.operatorTelNum }</p>
					<c:if test="${roleId eq '0001' }">
						<p>下属店长：${operatorAttributes.shopOwnerNum }人</p>
					</c:if>
					<c:if test="${roleId eq '0002' }">
						<p>下属店员：${operatorAttributes.storeOperatorNum }人</p>
					</c:if>
				</dd>
			</dl>
		</c:if>
		<c:if test="${authority eq '0004' }">
			<dl class="sh_warp">
				<dt>店铺数据</dt>
				<dd>
					<ul class="shop_data clear">
						<li>昨日PV：${operatorAttributes.yesterdayPV }</li>
						<li>昨日UV：${operatorAttributes.yesterdayUV }</li>
						<li>本月累计PV：${operatorAttributes.monthPV }</li>
						<li>本月累计UV：${operatorAttributes.monthUV }</li>
					</ul>
				</dd>
				<div class="btn_det"><a href="">查看详情></a></div>
			</dl>
		</c:if>
		<c:if test="${authority eq '0005' }">
			<dl class="sh_warp">
				<dt class="clear"><p>扫一扫收款</p><p>收款码收款</p></dt>
				<dd class="clear">
					<div class="paycode line">
						<img src="../images/merchant/shhz_icon_sys.png">
						<a href="javascript:scan();">扫一扫</a>
					</div>
					<div class="paycode">
						<img src="../images/merchant/shhz_icon_pay.png">
						<c:if test="${operatorAttributes.isBound eq 1 }">
							<a href="../merchant/showQRCode.do">使用收款码</a>
						</c:if>
						<c:if test="${operatorAttributes.isBound eq 0 }">
							<a href="javascript:bindingReceiptCode();">绑定收款码</a>
						</c:if>
						<c:if test="${operatorAttributes.isBound eq 2 }">
							<a href="javascript:XWalert('请联系店长绑定二维码');">未绑定</a>
						</c:if>
					</div>
				</dd>
			</dl>
		</c:if>
		<c:if test="${authority eq '0006' }">
			<dl class="sh_warp">
				<dt>收款记录</dt>
				<dd>
					<p>昨日收款：¥${operatorAttributes.yesterdayReceipts }</p>
					<p>本月累计收款：¥${operatorAttributes.monthReceipts }</p>
				</dd>
				<div class="btn_det"><a href="../merchant/receiptsPage.do">查看详情></a></div>
			</dl>
		</c:if>
		<c:if test="${authority eq '0007' }">
			<dl class="sh_warp">
				<dt>店铺账单</dt>
				<dd>
					<c:forEach items="${yesterdayStoreBills }" var="storeBill">
						<p>${storeBill.storeName }  昨日收款：¥${storeBill.amount }</p>
					</c:forEach>
				</dd>
				<div class="btn_det"><a href="../merchant/storeBillPage.do">查看详情></a></div>
			</dl>
		</c:if>
		<c:if test="${authority eq '0008' }">
			<dl class="sh_warp">
				<dt class="clear"><p>扫一扫退款</p><p>解绑收款码</p></dt>
				<dd class="clear">
					<div class="paycode line">
						<img src="../images/merchant/shhz_icon_sys.png">
						<a href="javascript:getRefund();">扫一扫</a>
					</div>
					<div class="paycode">
						<img src="../images/merchant/shhz_icon_pay.png">
						<c:if test="${operatorAttributes.isUnbundingPossible eq 1 }">
							<a href="#">您没有该权限</a>
						</c:if>
						<c:if test="${operatorAttributes.isUnbundingPossible eq 0 }">
							<c:if test="${operatorAttributes.isBound eq 1 }">
								<a href="javascript:unBindingQRCode();">解绑收款码</a>
							</c:if>
							<c:if test="${operatorAttributes.isBound eq 0 }">
								<a href="#">暂未绑定</a>
							</c:if>
						</c:if>
					</div>
				</dd>
			</dl>
		</c:if>
	</c:forEach>
	
</section>
<script src="../js/common/jquery.js?v=1.0"></script>
<script src="../js/common/j_main.js?v=1.0"></script>
<script src="../js/merchant/index.js?v=1.0"></script>
</body>
</html>