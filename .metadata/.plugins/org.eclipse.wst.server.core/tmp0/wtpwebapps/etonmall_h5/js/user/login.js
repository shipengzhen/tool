/**
 * 发送短信验证码
 */
function sendverificationCode(code){
	var telNum = $("#telNum").val();
	if (isBlank(telNum)) { 
		XWalert("请先输入号码");
		return;
	} 
	$.post(
			"sendVerificationCodeForH5.do",
			{"telNum" : telNum,"origin":code},
			function(data){
				if("0" == data.resultCode){
					//发送成功
					//XWalert("发送成功");
					time();
				}else{
					//发送失败
					XWalert(data.resultMessage);
				}
	},"json");
}

/**
 * 登录提交
 */
function loginSubmit(){
	var telNum = $("#telNum").val();
	var verificationCode = $("#verificationCode").val();
	if (isBlank(telNum)) { 
		XWalert("请先输入号码");
		return;
	}
	if(isBlank(verificationCode)){
		XWalert("请先输入验证码");
		return;
	}
	$.post(
			"loginBySMS.do",
			{"telNum" : telNum,"verificationCode":verificationCode},
			function(data){
				if("0" == data.resultCode){
					//发送成功
					//XWalert("登录成功");
					window.location.href= "../home/homePage.do";
				}else{
					//发送失败
					XWalert(data.resultMessage);
				}
	},"json");
}

/**
 * 忘记密码下一步
 */
function forgetPwd(){
	var telNum = $("#telNum").val();
	var verificationCode = $("#verificationCode").val();
	if (isBlank(telNum)) { 
		XWalert("请先输入号码");
		return;
	}
	if(isBlank(verificationCode)){
		XWalert("请先输入验证码");
		return;
	}
	$.post(
			"forgetPassword.do",
			{"telNum" : telNum,"verificationCode":verificationCode},
			function(data){
				if("0" == data.resultCode){
					//跳转重置密码页面
					window.location.href= "toForgetPwdNext.do?telNum="+ data.resultObj;
				}else{
					//弹框提示手机号或验证码错误
					XWalert(data.resultMessage);
				}
	},"json");
}

/**
 * 重置密码
 */
function resetPassword(){
	var telNum = $("#telNum").val();
	var password = $("#password").val();
	var repeatPassword = $("#repeatPassword").val();
	
	if(isBlank(password)){
		XWalert("请输入密码");
		return;
	}
	
	if(isBlank(repeatPassword)){
		XWalert("请输入确认密码");
		return;
	}
	
	$.post(
			"resetPassword.do",
			{"telNum" : telNum,"password":password,"repeatPassword":repeatPassword},
			function(data){
				if("0" == data.resultCode){
					toLogin();
					//XWalert("密码重置成功，请重新登陆", toLogin());
				}else{
					XWalert(data.resultMessage);
				}
	},"json");
}

/**
 * 跳转登录页面
 */
function toLogin(){
	var url ="";
	if (typeof (appObj) == "undefined") {
		url = "toLogin.do";
		window.location.href=url;
	} else {
		if (appObj.isAndroid()) {
			appObj.nativeHomeToLogin();
		}
		else{
			appObj.nativeLogin();
		}
	}
}

/** 
 * 是否为空字符串，全空格也是空字符串 
 * @param str 
 * @returns {Boolean} 
 */  
function isBlank(str){  
    if(str == null || typeof str == "undefined" ||   
            str == "" || trim(str) == ""){  
        return true;  
    }  
    return false;  
}

/** 
* 去掉字符串头尾空格 
* @param str 传入的字符串值 
* @since 2015-08-21 
*/  
function trim(str) {  
    if(str == null || typeof str == "undefined"){  
        return "";  
    }  
    return str.replace(/(^\s*)|(\s*$)/g, "");  
}

/**
 * 去除button灰色
 */
function removeGray(){
	$("#submit").removeClass("btn_gray");
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