function _$(id) {
	return document.getElementById(id);
}
function get_time() {
	return new Date().getTime().toString();
}
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

})(window, document)

function getPayCard() {
	var money = $("#money").val();
	if (!money) {
		XWalert("请输入交易金额");
		return;
	}
	var exp = /^(([1-9]\d*)|\d)(\.\d{1,2})?$/;
	if (!exp.test(money)) {
		XWalert('金额格式不对');
		return;
	}
	$
			.ajax({
				type : "POST",
				url : "../cardManager/getCardList.do",
				data : {
					"money" : money
				},
				dataType : "json",
				async : true,
				success : function(data) {
					if (data.resCode != 0) {
						XWalert(data.erroMsg);
					} else {
						var divContent = "";
						if (data.status == 1) {
							$
									.each(
											data.cardInfo,
											function(i) {
												var commentInfo = data.cardInfo[i];
												var num = i + 1;
												divContent += '<dd><i class="icon_pay"><img src="'
														+ commentInfo.smallLogoUrl
														+ '"></i>'
														+ commentInfo.cardNo
														+ '<span>(￥'
														+ commentInfo.cardBalance
														+ ')</span></dd>';
												divContent += '<div class="pay_lack">卡内余额不足<a href="../cardManager/bindCardPage.do">绑定新卡></a></div>';
											})
						} else {
							$
									.each(
											data.cardInfo,
											function(i) {
												var commentInfo = data.cardInfo[i];
												var num = i + 1;
												if (commentInfo.cardStatus == 0) {
													divContent += '<dd onclick="toPay('
															+ "'"
															+ commentInfo.realCardNo
															+ "'"
															+ ","
															+ "''"
															+ ')"><i class="icon_pay"><img src="'
															+ commentInfo.smallLogoUrl
															+ '"></i>'
															+ commentInfo.cardNo
															+ '<span>(￥'
															+ commentInfo.cardBalance
															+ ')</span></dd>';
												} else {
													divContent += '<dd><i class="icon_pay"><img src="'
															+ commentInfo.smallLogoUrl
															+ '"></i>'
															+ commentInfo.cardNo
															+ '<span>(￥'
															+ commentInfo.cardBalance
															+ ')</span></dd>';
												}

											})
						}
						$("#payChannel").append(divContent);
					}
				},
				error : function(data) {
					XWalert("系统繁忙，请稍后重试");
				}
			});
	$("#pays").css("display", "block");
}
