function scan(){
	window.location.href = "../merchant/getPayPage.do";
}

function bindingReceiptCode(){
	XWDialog( {'tips':'如果您已获得纸质收款码码请点击绑定，否则请点击申请','mainBtn':'绑定','mainEvent':'binding()','extendBtn':'申请','extendEvent':'apply()'} )
}

function binding() {
	appObj.normalTdcode(gotoBinding);
}

function apply() {
	$.ajax({
		type : "POST",
		url : "../merchant/bindingQRCode.do",
		data : {
		},
		dataType : "json",
		async : true,
		success : function(data) {
			if (data.resultCode == "0"){
				XWDialog( {'tips':'绑定成功','mainBtn':'我知道了','mainEvent':'window.location.href="../merchant/indexPage.do"'});
			}
			else {
				XWalert(data.resultMessage);
			}
		}
	});
}

var gotoBinding = function (qrCode){
	$.ajax({
		type : "POST",
		url : "../merchant/bindingQRCode.do",
		data : {
			"QRCode":qrCode
		},
		dataType : "json",
		async : true,
		success : function(data) {
			if (data.resultCode == "0"){
				XWDialog( {'tips':'绑定成功','mainBtn':'我知道了','mainEvent':'window.location.href="../merchant/indexPage.do"'});
			}
			else {
				XWalert(data.resultMessage);
			}
		}
	});
}

function unBindingQRCode (){
	XWDialog( {'tips':'是否确定解绑收款码？','mainBtn':'取消','mainEvent':'','extendBtn':'确定','extendEvent':'unBindingReceiptCode()'} )
}

function unBindingReceiptCode(){
	XWalert
	$.ajax({
		type : "POST",
		url : "../merchant/unBindingQRCode.do",
		data : {
		},
		dataType : "json",
		async : true,
		success : function(data) {
			if (data.resultCode == "0"){
				XWDialog( {'tips':'解绑成功','mainBtn':'我知道了','mainEvent':'window.location.href="../merchant/indexPage.do"'});
			}
			else {
				XWalert(data.resultMessage);
			}
		}
	});
}

function getRefund() {
	appObj.normalTdcode(gotoRefund);
}

var gotoRefund = function(qrCode){
	var bar_code = "bar_code";
	$.ajax({
		type : "POST",
		url : "../payment/toRefund.do",
		data : {
			"scene" :bar_code,
			"qrCode" : qrCode
		},
		dataType : "json",
		async : true,
		success : function(data) {
			var resCode = data.resultCode;
			if (resCode == 0){
				XWalert("退款成功");
			}
			else {
				XWalert(data.resultMessage);
			}
		}
	});
}
