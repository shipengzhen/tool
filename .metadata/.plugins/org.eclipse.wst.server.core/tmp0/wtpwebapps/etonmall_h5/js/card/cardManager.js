(function(m, n) {
	// $("body").on("touchmove",function(event){event.preventDefault();})
	$('.popup_warp').on('click', function(event) {
		if ($(event.target).is('.close') || $(event.target).is('.popup_warp')) {
			event.preventDefault();
			$(this).hide();
		}
	});
	$('.close').on('click', function() {
		$(".popup_warp").hide();
	});
	$('.sel_city').on('click', function() {
		$("section").hide();
		$(".city_warp").show();
	});
	$('.ser_warp').on('click', function() {
		$("section").hide();
		$(".index_search").show();
	});
	$('.btn_qx,.close_city').on('click', function() {
		$("section").hide();
		$(".home").show();
	});

	// 获取验证码倒计时
	var wait = 60;
	n.time = function(o) {
		if (wait == 0) {
			o.removeAttribute("disabled");
			o.value = "点击获取";
			wait = 60;
		} else {
			o.setAttribute("disabled", true);
			o.value = "重新获取(" + wait + ")";
			wait--;
			setTimeout(function() {
				n.time(o)
			}, 1000)
		}
	}

	n.code = function() {
		if (event.keyCode < 48 || event.keyCode > 57) {
			event.returnValue = false;
		}
	}
	n.card_pop =function(){
		$(".card_pop").toggle();
		$(".popup_card").toggle();
	}
/*	$('.card_pop a').on('click', function(){
		$(".card_pop").hide();
		$(".popup_card").hide();
	});
*/
	$('.card_list dt').on('click', function(){
		$(this).toggleClass("active");
		$(this).next("dd").toggle("fast");
	});
	
})(window, document)

function bindCard() {
	var cardNo = $("#cardNo").val();
	var patrn = /^[0-9]*$/;
	if(!patrn.test(cardNo)){
		XWalert("请输入正确的卡号");
		return;
	}
	var cvv2 = $("#cvv2").val();
	if(!patrn.test(cvv2)){
		XWalert("请输入正确的CVV2");
		return;
	}
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
	var pin = passGuard1.getOutput();
	//var pin=$("#kb1").val();
	if(!cardNo){
		XWalert("卡号不可为空");
		return;
	}
	if(!pin){
		XWalert("pin不可为空");
		return;
	}
	if(!cvv2){
		XWalert("cvv2不可为空");
		return;
	}
	$.ajax({
		type : "POST",
		url : "../cardManager/bindingCard.do",
		data : {
			"cardId" : cardNo,
			"pin" : pin,
			"cvv2" : cvv2
		},
		dataType : "json",
		async : true,
		success : function(data) {
			if (data.resCode != 0) {
				XWalert(data.erroMsg);
			} else {
				XWalert("绑定成功！");
				window.location.href="../cardManager/myCard.do";
			}
		},
		error : function(data) {
			XWalert("系统繁忙，请稍后重试");
		}
	});
}

function turnUnbindCard(cardNo){
	window.location.href="../cardManager/unbindCardPage.do?cardNo="+cardNo;
}


function unbindCard() {
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
	var cardNo = $("#cardNo").val();
	var pin = passGuard1.getOutput();
	//var pin=$("#kb1").val();
	$.ajax({
		type : "POST",
		url : "../cardManager/unbundLingCard.do",
		data : {
			"cardId" : cardNo,
			"pin" : pin
		},
		dataType : "json",
		async : true,
		success : function(data) {
			if (data.resCode != 0) {
				XWalert(data.erroMsg);
			} else {
				XWalert("解绑成功！");
				window.location.href="../cardManager/myCard.do";
			}
		},
		error : function(data) {
			XWalert("系统繁忙，请稍后重试");
		}
	});
}


//DES 加密
function encryptByDES(message, key) {
	var keyHex = CryptoJS.enc.Utf8.parse(key);
	var encrypted = CryptoJS.DES.encrypt(message, keyHex, {
		mode : CryptoJS.mode.ECB,
		padding : CryptoJS.pad.Pkcs7
	});
	return encrypted.toString();
}


function getPayCardDetail(cardType){
	window.location.href="../cardManager/getCardDetailByType.do?cardType="+cardType;
}

function secSuperCard(){
	$("#superCard").addClass("active");
	$("#card").removeClass("active");
	$("#cardList").css("display","none");
	$("#superCardList").css("display","block")
}

function secCard(){
	$("#superCard").removeClass("active");
	$("#card").addClass("active");
	$("#cardList").css("display","block");
	$("#superCardList").css("display","none")
}

function autoBindCard(cardNo) {
	window.location.href = "../cardManager/bindCardPage.do?cardNo=" + cardNo;
}

function payNewCard(){
	XWalert("暂未开启，敬请期待");
}

function getCardDetail(cardNo){
	window.location.href="../cardManager/getCardDetail.do?cardNo="+cardNo;
}

function getOrderListByCardId(cardId){
	window.location.href="../payment/orderDetailById.do?cardId="+cardId;
}

function recharge(){
	window.location.href="../payment/recharge.do";
}