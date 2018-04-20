function getPay() {
	appObj.normalTdcode(gotoGetPay);
}

var gotoGetPay = function(qrCode) {
	var bar_code = "bar_code";
	var money = $("#moneyInput").val();
	if (!isPositive(money)) {
		XWalert("请输入正数");
		return;
	}
	$.ajax({
		type : "POST",
		url : "../payment/getPay.do",
		data : {
			"scene" : bar_code,
			"payQr" : qrCode,
			"money" : money
		},
		dataType : "json",
		async : true,
		success : function(data) {
			var resCode = data.resCode;
			if (resCode == 0) {
				XWalert("收款成功");
			} else if (resCode == 5) {
				// 需要用户输入密码的，跳等待页面
				qryPayResult(data.orderId);
			} else if (resCode == 3 || resCode == 2 || resCode == 1) {
				// 余额不足直接跳失败
				XWalert(data.erroMsg);
			} else {
				XWalert("系统繁忙请稍后重试");
			}
		}
	});
}

function qryPayResult(orderId) {
	XWLoading("等待用户输入密码…");
	var B = setInterval(function() {
		$.ajax({
			type : "POST",
			url : "../payment/merchantCheckOrderStatus.do",
			data : {
				"orderId" : orderId
			},
			dataType : "json",
			async : true,
			success : function(data) {
				var code = data.resCode;
				if (code == 0) {
					XWLoadingoff();
					XWalert("收款成功");
					window.clearInterval(B);
				} else if (code == 1) {
					// 错误
					XWLoadingoff();
					XWalert(data.erroMsg);
					window.clearInterval(B);
				} else if (code == 4) {
					XWLoadingoff();
					XWalert("收款失败");
					window.clearInterval(B);
				}
			},
			error : function(data) {
				XWalert("系统繁忙，请稍后重试");
			}
		});
	}, 5000);

}

function isPositive(num) {
	var reg = /^\d+(?=\.{0,1}\d+$|$)/
	if (reg.test(num))
		return true;
	return false;
}
