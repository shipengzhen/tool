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
<link href="../css/card/public.css?v=1.0" rel="stylesheet">
<link href="../css/card/style.css?v=1.0" rel="stylesheet">
<link rel="stylesheet" href="../css/microdone-h5.css">
<script src="../js/card/jquery.js?v=1.0"></script>
<script src="../js/common/XWPlug.bundle.min.js?v=1.0"></script>
<link href="../css/common/XWPlug.bundle.min.css?v=1.0" rel="stylesheet">
<script type="text/javascript" src="../js/demo.js"></script>
<script type="text/javascript" src="../js/microdone-h5.min.js"></script>
<script src="../js/payment/toMerchantPay.js?v=1.0"></script>
<script type="text/javascript">
	var kb = new keyBoard({
		"chaosMode" : 0,// 混乱模式,0:无混乱;1:打开时乱一次;2:每输入一个字符乱一次,默认值0
		"pressStatus" : 1,// 按键状态,0:按下、抬起按键无变化;1:按下后有放大镜效果;2:按下、抬起按键的颜色变化,默认值0
		"kbType" : 1,// 键盘类型,0:全键盘;1:纯数字键盘,默认值0
		"svg" : "svg"//svg图片的地址
	});
	var passGuard1 = new passGuard(
			{
				"mappurl" : "../send_mapping.jsp",
				"maxLength" : 6,// 最大输入长度
				//"regExp1" : "[\\S\\s]",// 输入过程限制的正则
				//"regExp2" : "[0-9]{6,12}",
				"displayMode" : 0,// 显示形式,0:星号;1:明文,默认值0
				"callBack" : cb1,//成功回调
				"errorCallBack" : cb2,//失败回调
				"rsaPublicKey" : "3082010a0282010100c953b75dc1cd49ce853fd60b72b07395f08c94020efdc46a72ad26158b1e43764eb1864511321ada937ab701f159a2ec84135543b7ed139bef6b8f786e1055b6e82dca4844c85187c8da21cf4e9a1ad2b8a851e299a6e103d2d0f9abf120e7bd980397dc3b3a22a037567539ccbe02ebad513c66087a3cfd267fa0c8e400dc3012b119a3bb837b4252039efe715995a17f4070a1bc5313b55faa1638fa0d9f68f4bd9cda659e5760544398c0f1fe7276ff992783e42c0af76dfdfb516bbe526a3c8c4cd1da8f682ba6ffc7fe8e929a0fe8ef67d321c6d6cce6350387cf93a6b6df819631cdb48783390996c9847ae2652243865004dd11a515a59946b8f577650203010001"// rsa公钥
			});
	function cb1() {
		console.log("成功1");

	}
	function cb2() {
		console.log("失败1");
	}
</script>
</head>
<body>
	<section>
		<div class="shop_des">
			<img src="${info.logo}">
			<p>商家：${info.merchantName}</p>
		</div>
		<div class="cont_warp">
			<p class="gathering_t">付款金额</p>
			<label class="gathering_m">￥<input type="text" value=""
				id="money" placeholder="请输入付款金额"></label>

		</div>
		<input type="hidden" value="${qrCode}" id="qrCode" />
		<input type="hidden" value="${operatorId}" id="operatorId" />
		<input type="hidden" value="${storeId}" id="storeId" />
		 <input type="hidden" value="" id="cardNo" />
			 <input type="hidden" value=""
			id="orderId" />
		<div class="btn_warp">
			<a href="javascript:getPayCard();">确认付款</a>
		</div>
	</section>
	<div class="popup_warp" style="display: none;" id="pays">
		<div class="pay_info">
			<div class="pay_title">
				<a class="close" href="javascript:;"></a>选择优先支付方式
			</div>
			<dl id="payChannel">
				<!-- <dd><i class="icon_pay"><img src="images/pay_ykt_icon.png"></i>零钱<span>(剩余¥298)</span></dd> -->
				<dd class="pay_gray">
					<i class="icon_pay"><img
						src="../images/card/pay_ykt_icon01.png"></i>零钱<span>(剩余¥0)</span>
				</dd>
				<!-- <dd><i class="icon_pay"><img src="images/pay_ykt_logo.png" onclick="toPay()"></i>一卡通商务卡<span>(￥100)</span></dd>
			<dd><i class="icon_pay"><img src="images/pay_ykt_logo.png"></i>一卡通商务卡<span>(￥100)</span></dd>
			<dd><i class="icon_pay"><img src="images/pay_ykt_logo.png"></i>一卡通商务卡<span>(￥100)</span></dd>
			<dd><i class="icon_pay"><img src="images/pay_ykt_logo.png"></i>一卡通商务卡<span>(￥100)</span></dd> -->
				<!-- <div class="pay_lack">卡内余额不足<a href="javascript:;">绑定新卡></a></div> -->
			</dl>
		</div>
	</div>
	<div class="popup_warp" id="paycode">
		<div class="entercode">
			<div class="code_title">
				<a href="javascript:;"></a>请输入支付密码
			</div>
			<div class="code_main">
				<input type="text" readonly id="kb1" class="default" value=""
					maxlength="6">
				<script>
					passGuard1.generate("kb1", kb, 1);
				</script>
			</div>
			<div class="code_rem">
				<a href="../user/toValidatePayPassword.do">忘记密码？</a>
			</div>
			<div class="btn_warp">
				<a href="javascript:formSubmit();">确定</a>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		window.onload = function() {
			Le = $(".default").length;
			kb.generate();
			winHeight = $(window).height();
		};

		function formSubmit() {
			//判断密码长度
			if (passGuard1.getLength() == 0) {
				XWalert("密码不能为空！");
				return false;
			}

			$.ajax({
				url : "../send_randkey.jsp?" + get_time(),
				type : "GET",
				async : false,
				success : function(ranKey) {
					passGuard1.setRandKey(ranKey);
				}
			});
			//获取密文
			//_$("password").value = passGuard1.getOutput();
			//_$("kb1").value = passGuard1.getOutput();

			/* for(var i = 1;i <= Le;i++){
				$("#kb"+i).attr('placeholder',PH.arrPlace[i-1])
			}  */
			console.log(_$("kb1").value);
			//提交
			//document.forms[0].submit();
			toPay($("#cardNo").val(), passGuard1.getOutput());

		}
		/* $("#paycode").show(); */
		function toPay(cardNo, password) {
			$("#cardNo").val(cardNo);
			var qrCode = $("#qrCode").val();
			var money = $("#money").val();
			var orderId = $("#orderId").val();
			var operatorId = $("#operatorId").val();
			var storeId = $("#storeId").val();
			var scene = "card_code";
			XWLoading("等待支付结果确认");
			console.log("=======" + qrCode);
			$
					.ajax({
						type : "POST",
						url : "../payment/toPay.do",
						data : {
							"payQr" : qrCode,
							"money" : money,
							"scene" : scene,
							"cardNo" : cardNo,
							"payWord" : password,
							"orderId" : orderId,
							"storeId" : storeId,
							"operatorId" : operatorId

						},
						dataType : "json",
						async : true,
						success : function(data) {
							console.log(1111111111);
							XWLoadingoff();
							if (data.resCode == 0) {
								$("#orderId").val("");
								window.location.href = "../payment/payResult.do?orderId="
										+ data.orderId;
							} else if (data.resCode == 1) {
								$("#orderId").val("");
								XWalert(data.erroMsg);
							} else if (data.resCode == 2) {
								//XWalert(data.erroMsg);
								XWalert(data.erroMsg);
								$("#orderId").val(data.orderId);
								$("#pays").css("display", "none");
								$("#paycode").show();
							} else if (data.resCode == 3) {
								$("#orderId").val("");
								XWalert(data.erroMsg);
							} else if (data.resCode == 4) {
								$("#orderId").val("");
								XWalert(data.erroMsg);
							} else if (data.resCode == 5) {
								//XWalert(data.erroMsg);
								$("#orderId").val(data.orderId);
								$("#pays").css("display", "none");
								$("#paycode").show();
							}
						},
						error : function(data) {
							XWLoadingoff();
							XWalert("系统繁忙，请稍后重试");
						}
					});
		}

		$('.popup_warp').on(
				'click',
				function(event) {
					if ($(event.target).is('.close')
							|| $(event.target).is('.popup_warp')) {
						event.preventDefault();
						$(this).hide();
					}
				});
	</script>
</body>
</html>