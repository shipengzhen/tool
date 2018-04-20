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
<title>绑定新一卡通</title>
<script src="../js/card/flex.js?v=1.0"></script>
<link href="../css/card/public.css?v=1.0" rel="stylesheet">
<link href="../css/card/style.css?v=1.0" rel="stylesheet">
<script src="../js/card/jquery.js?v=1.0"></script>
<script src="../js/common/XWPlug.bundle.min.js?v=1.0"></script>
<link href="../css/common/XWPlug.bundle.min.css?v=1.0" rel="stylesheet">
<link rel="stylesheet" href="../css/microdone-h5.css">
<script type="text/javascript" src="../js/payment/tripledes.js"></script>
<script type="text/javascript" src="../js/payment/mode-ecb.js"></script>
<script src="../js/card/cardManager.js?v=1.0"></script>
<script type="text/javascript" src="../js/demo.js"></script>
<script type="text/javascript" src="../js/microdone-h5.min.js"></script>
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
				"maxLength" : 8,// 最大输入长度
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
<script src="../js/common/app.js?v=1.0"></script>
<section class="bg_write">
	<div class="ykt_form">
		<p style="display: inline;margin-right: 0.4rem">请输入一卡通卡号</p><a href="javascript:appObj.normalTdcode(autoBindCard);" style="font-size: 0.375rem">戳我,扫码输入</a>
		<label><input type="text" placeholder="请输入一卡通卡号" value="${cardNo}" id="cardNo" onkeypress="code()"></label>
	</div>
	<div class="ykt_form">
		<p>请输入一卡通密码</p>
		<label>
		<!-- <input type="password" id="kb1" class="default" placeholder="卡密码不区分大小写" maxlength="8"> -->
		<input type="text" id="kb1" class="default" placeholder="卡密码不区分大小写" maxlength="8" readonly>
		<script>
			passGuard1.generate("kb1", kb, 1);
		</script>
		</label>
	</div>
	<div class="ykt_form">
		<p>请输入一卡通CVV2验证码</p>
		<label><input type="text" placeholder="cvv2验证码" id="cvv2" maxlength="3" onkeypress="code()"></label>
	</div>
	<div class="btn_warp"><a href="javascript:bindCard();">绑定</a></div>
</section>
<div class="popup_warp">
	<div class="ykt_tips">
		<img src="../images/card/fail.png">
		<p>绑定失败，该一卡通已被绑定</p>
	</div>
</div>
<script>
 window.onload = function() {
	Le = $(".default").length;
	kb.generate();
	winHeight = $(window).height();
}; 
function code(){
	if(event.keyCode<48 || event.keyCode>57){
		event.returnValue=false;
	}
}
</script>
</body>
</html>