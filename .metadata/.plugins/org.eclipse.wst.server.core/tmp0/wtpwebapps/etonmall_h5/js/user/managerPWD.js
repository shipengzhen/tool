/**
 * 跳转密码修改页面
 */
function editPassword(){
	window.location.href = "../user/toEditPassword.do";
}

/**
 * 跳转支付密码修改页面
 */
function editPayPassword(){
	window.location.href = "../user/toValidatePayPassword.do";
}

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
 * 校验支付密码
 */
function validatePayPassword(){
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
			"validatePayPassword.do",
			{"telNum" : telNum,"verificationCode":verificationCode},
			function(data){
				if("0" == data.resultCode){
					//跳转重置密码页面
					window.location.href= "toEditPayPassword.do";
				}else{
					//弹框提示手机号或验证码错误
					XWalert(data.resultMessage);
				}
	},"json");
}

/**
 * 修改支付密码
 */
function resetPayPassword(){

	if(passGuard1.getLength()==0){
		XWalert("原密码不能为空！");
		return false;
	}
	if(passGuard2.getLength()==0){
		XWalert("新密码不能为空！");
		return false;
	}
	if(passGuard1.getHash()!=passGuard2.getHash()){
		XWalert("新密码和确认密码不一致！");
		return false;
	}
	$.ajax({
		url : "../send_randkey.jsp?" + get_time(),
		type : "GET",
		async : false,
		success : function(ranKey) {
			passGuard2.setRandKey(ranKey);
			
		}
	});
	//获取密文
	//_$("password").value = passGuard1.getOutput();
	//_$("kb1").value = passGuard1.getOutput();
	var pin2 = passGuard2.getOutput();
	
	$.post(
			"modifyPayPassword.do",
			{"newPayPassword":pin2,"repeatPayPassword":pin2},
			function(data){
				if("0" == data.resultCode){
					XWDialog( {'tips':'支付密码修改成功','mainBtn':'我知道了','mainEvent':'gohistory()'});

				}else{
					XWalert(data.resultMessage);
				}
	},"json");
}

/**
 * 设置支付密码
 */
function setPayPassword(){

	if(passGuard1.getLength()==0){
		XWalert("原密码不能为空！");
		return false;
	}
	if(passGuard2.getLength()==0){
		XWalert("新密码不能为空！");
		return false;
	}
	if(passGuard1.getHash()!=passGuard2.getHash()){
		XWalert("新密码和确认密码不一致！");
		return false;
	}
	$.ajax({
		url : "../send_randkey.jsp?" + get_time(),
		type : "GET",
		async : false,
		success : function(ranKey) {
			passGuard2.setRandKey(ranKey);
			
		}
	});
	//获取密文
	//_$("password").value = passGuard1.getOutput();
	//_$("kb1").value = passGuard1.getOutput();
	var pin2 = passGuard2.getOutput();
	
	$.post(
			"etonRegister.do",
			{"payPassword":pin2,},
			function(data){
				if("0" == data.resultCode){
					XWDialog( {'tips':'支付密码设置成功','mainBtn':'我知道了','mainEvent':'gotoHome()'});

				}else{
					XWalert(data.resultMessage);
				}
	},"json");
}

/**
 * 修改密码
 */
function resetPassword(){
	var telNum = $("#telNum").val();
	var oldpassword = $("#oldpassword").val();
	var newpassword = $("#newpassword").val();
	var repeatPassword = $("#repeatPassword").val();
	
	if(isBlank(oldpassword)){
		XWalert("请输入老密码");
		return;
	}
	if(isBlank(newpassword)){
		XWalert("请输入新密码");
		return;
	}
	if(isBlank(repeatPassword)){
		XWalert("请输入确认密码");
		return;
	}
	
	$.post(
			"modifyPassword.do",
			{"telNum" : telNum,"oldPassword":oldpassword,"newPassword":newpassword,"repeatPassword":repeatPassword},
			function(data){
				if("0" == data.resultCode){
					//XWalert("密码修改成功，请重新登陆",toLogin());
					XWDialog( {'tips':'密码修改成功，请重新登陆','mainBtn':'我知道了','mainEvent':'reLogin()'});
				}else{
					XWalert(data.resultMessage);
				}
	},"json");
}




/**
 * 去除button灰色
 */
function removeGray(){
	$("#submit").removeClass("btn_gray");
}

/**
 * 跳转登录页面
 */
function reLogin(){
	var url;
	if (typeof (appObj) == "undefined") {
		url = "toLogin.do";
		window.location.href=url;
	} else {
		//appObj.nativeClearLoginInfo();
		appObj.nativeHomeToLogin();
	}
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

function gohistory(){
	history.go(-2);
}

function gotoHome(){
	//var url ="";
	if (typeof (appObj) == "undefined") {
		//url = "toLogin.do";
		//window.location.href=url;
	} else {
		appObj.appGoHome();
	}
}