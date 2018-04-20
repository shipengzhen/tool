/**
 * 登录
 * @returns
 */
function login() {
	var merchantId = $("#merchantId").val();
	var telNum = $("#telNum").val();
	var validateCode = $("#validateCode").val();
	if (validate(merchantId,telNum,validateCode)){
		$.ajax({
			type : "POST",
			url : "../merchant/login.do",
			data : {
				"telNum" :telNum,
				"merchantId" :merchantId,
				"verificationCode" :validateCode
			},
			dataType : "json",
			async : true,
			success : function(data) {
				if (data.resultCode == "0"){
					window.location.href="../merchant/indexPage.do";
				}
				else {
					XWalert(data.resultMessage);
				}
			},
			error : function(data) {
				XWalert('系统繁忙，请稍后重试！');
			}
		});
	}	
}

/**
 * 验证参数是否为空
 * @param merchatId
 * @param password
 * @param validateCode
 * @returns
 */
function validate(merchatId,telNum,validateCode) {
	if (!telNum){
		XWalert('请输入手机号码');
		return false ;
	}
	if (!merchatId){
		XWalert('请输入商户号');
		return false ;
	}
	if (!validateCode){
		XWalert('请输入短信验证码');
		return false ;
	}
	return true ;
}

/**
 * 发送短信
 * @returns
 */
function getCode (){
	var telNum=$("#telNum").val();
	if (!telNum){
		XWalert("请输入手机号");
		return false ;
	}
	$.ajax({
		type : "POST",
		url : "../merchant/sendValidateCode.do",
		data : {
			"telNum" :telNum
		},
		dataType : "json",
		async : true,
		success : function(data) {
			if (0 != data.resultCode) {
				XWalert(data.resultMessage);
			}else{
				time();
			}
		},
		error : function(data) {
			XWalert('系统繁忙，请稍后重试！');
		}
	});
}

var wait=60;
function time() {
	if (wait == 0) {
		$("#recode").removeAttr("disabled");
		$("#recode").val("获取验证码");
		wait = 60;
	} else {
		$("#recode").attr("disabled","disabled");
		$("#recode").val("重新获取(" + wait + ")");
		wait--;
		setTimeout(function() {
			time();
		},
		1000);
	}
}

