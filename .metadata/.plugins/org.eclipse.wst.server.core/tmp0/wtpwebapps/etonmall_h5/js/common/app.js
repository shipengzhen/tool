//if(typeof SharePlat == "undefined"){
//		var SharePlat = {
//　　　　　　　 QQ: 0,
//　　　　　　　 Wechat: 1,
//　　　　　　　 WechatMoment: 2,
//　　　　　　　 SinaWeibo: 3,
//　　 　　　　　Clipboard: 4,
//　　　　　　　 QZone: 5,
//　　　　　　　 ShortMessage: 6,
//　　　　　　　 DimenCode: 7,
//		}
//}
if (window.share) {
	appObj = {
		logout : function(msg) {
			var iframe = document.createElement('iframe');
			iframe.hidden = true;
			iframe.src = "sdmccwap://logout?msg=" + msg;
			document.body.appendChild(iframe);
			document.body.removeChild(iframe);
		},
		isAndroid : function() {
			return (window.share.plat() == "android");
		},
		version : function() {
			if (appObj.isAndroid()) {
				return window.share.version();
			} else {
				return os_ios.version();
			}
		},
		zt_tn : function() {
			if (appObj.isAndroid()) {
				return window.share.zt_tn();
			} else {
				return os_ios.zt_tn();
			}
		},
		zt_tk : function() {
			if (appObj.isAndroid()) {
				return window.share.zt_tk();
			} else {
				return os_ios.zt_tk();
			}
		},
		pageLoadFinished : function()
		{
			if (appObj.isAndroid()) {
				return window.share.loadFinished();
			} else {
				return os_ios.loadFinished();
			}
		},
		toast:function(msg){
			if (appObj.isAndroid()) {
				return window.share.toast(msg);
			} else {
				return os_ios.toast();
			}
		},
		showProgressDialog:function(){
			if (appObj.isAndroid()) {
				return window.share.showProgressDialog();
			} else {
				return os_ios.showProgressDialog();
			}
		},
		dismissProgressDialog:function(){
			if (appObj.isAndroid()) {
				return window.share.dismissProgressDialog();
			} else {
				return os_ios.dismissProgressDialog();
			}
		},
		callBackList : {},
		callback : function(hookKey, json) {
			if (appObj.callBackList[hookKey] != undefined) {
				appObj.callBackList[hookKey](json);
				appObj.callBackList[hookKey] = undefined;
			}else{
				alert("系统异常");
			}
		},
		address : function(callbackFunction) {
			hookKey = "hk"+new Date().getTime() +""+ Math.round(Math.random()*10000);
			appObj.callBackList[hookKey] = callbackFunction;
			if (appObj.isAndroid()) {
				window.share.address(hookKey);
			} else {
				return os_ios.address(hookKey);
			}
		},
		backNotify : function(url)
		{
			if (appObj.isAndroid()) {
				window.share.webViewBackNotify(url);
			} else {
				return os_ios.webViewBackNotify(url);
			}
		},
		twoDimensionCodeQuery : function(callbackFunction)
		{
			hookKey = "twoDimensionCodeQuery"+new Date().getTime() +""+ Math.round(Math.random()*10000);
			appObj.callBackList[hookKey] = callbackFunction;
			if (appObj.isAndroid()) {
				window.share.twoDimensionCode(hookKey);
			} else {
				return os_ios.twoDimensionCode(hookKey);
			}
		},
		normalTdcode:function(callbackFunction)
		{
			hookKey = "twoDimensionCodeQuery"+new Date().getTime() +""+ Math.round(Math.random()*10000);
			appObj.callBackList[hookKey] = callbackFunction;
			if (appObj.isAndroid()) {
				window.share.normalTdcode(hookKey);
			} else {
				return os_ios.normalTdcode(hookKey);
			}
		},
		nativeHomeToLogin : function() {
			if (appObj.isAndroid()) {
				window.share.callLoginAboveHome();
			} else {
				return os_ios.callLoginAboveHome();
			}
		},
		nativeLogin : function(callbackFunction) {
			hookKey = "nativeLogin"+new Date().getTime() +""+ Math.round(Math.random()*10000);
			appObj.callBackList[hookKey] = callbackFunction;
			if (appObj.isAndroid()) {
				window.share.callLogin(hookKey);
			} else {
				return os_ios.callLogin(hookKey);
			}
		},
		nativeClearLoginInfo : function()
		{
			if (appObj.isAndroid()) {
				return window.share.clearLoginInfo();
			} else {
				return os_ios.clearLoginInfo();
			}
		},
		appHrefBlank : function(link)
		{
			if (appObj.isAndroid()) {
				return window.share.loadUrlNewWebView(link);
			} else {
				return os_ios.loadUrlNewWebView(link);
			}
		},
		appGoHome : function()
		{
			if (appObj.isAndroid()) {
				return window.share.goHome();
			} else {
				return os_ios.goHome();
			}
		},
		appjsGetLocation : function(callbackFunction) {
			hookKey = "getLocation"+new Date().getTime() +""+ Math.round(Math.random()*10000);
			appObj.callBackList[hookKey] = callbackFunction;
			if (appObj.isAndroid()) {
				window.share.getLocation(hookKey);
			} else {
				return os_ios.getLocation(hookKey);
			}
		},
		share : function(plat, title, text, image, url, callbackFunction)
		{
			hookKey = "share"+new Date().getTime() +""+ Math.round(Math.random()*10000);
			appObj.callBackList[hookKey] = callbackFunction;
			if (appObj.isAndroid()) {
				window.share.oneKeyShare(plat, ""+title, ""+text, ""+image, ""+url, hookKey);
			} else {
				return os_ios.oneKeyShare(plat, ""+title, ""+text, ""+image, ""+url, hookKey);
			}
		},
		addressList : function(callbackFunction) {
			hookKey = "addressList"+new Date().getTime() +""+ Math.round(Math.random()*10000);
			appObj.callBackList[hookKey] = callbackFunction;
			if (appObj.isAndroid()) {
				window.share.addressList(hookKey);
			} else {
				return os_ios.addressList(hookKey);
			}
		},
		newShare : function(paramJson, callbackFunction)
		{
			hookKey = "newShare"+new Date().getTime() +""+ Math.round(Math.random()*10000);
			appObj.callBackList[hookKey] = callbackFunction;
			if (appObj.isAndroid()) {
				window.share.newshare(paramJson, hookKey);
			} else {
				return os_ios.newshare(paramJson, hookKey);
			}
		},
		saveBase64Image : function(base64img)
		{
			if (appObj.isAndroid()) {
				window.share.saveBase64Image(base64img);
			} else {
				return os_ios.saveBase64Image(base64img);
			}
		},
		transferVoiceToCharacter : function(callbackFunction)
		{
			hookKey = "hk"+new Date().getTime() +""+ Math.round(Math.random()*10000);
			appObj.callBackList[hookKey] = callbackFunction;
			if (appObj.isAndroid()) {
				window.share.voiceToCharacter(hookKey);
			} else {
				return os_ios.voiceToCharacter(hookKey);
			}
		},
		transferCharacterToVoice : function(text, callbackFunction)
		{
			hookKey = "hk"+new Date().getTime() +""+ Math.round(Math.random()*10000);
			appObj.callBackList[hookKey] = callbackFunction;
			if (appObj.isAndroid()) {
				window.share.characterToVoice(text, hookKey);
			} else {
				return os_ios.characterToVoice(text, hookKey);
			}
		},
		allSpokesperson : function(callbackFunction)
		{
			hookKey = "hk"+new Date().getTime() +""+ Math.round(Math.random()*10000);
			appObj.callBackList[hookKey] = callbackFunction;
			if (appObj.isAndroid()) {
				window.share.getAllSpokesperson(hookKey);
			} else {
				return os_ios.getAllSpokesperson(hookKey);
			}
		},
		setVoiceSpokesperson : function(text)
		{
			// alert('hello');
			if (appObj.isAndroid()) {
				window.share.setSpokesperson(text);
			} else {
				return os_ios.setSpokesperson(text);
			}
		},
		camera : function(hookMethod) {
			// alert('onlyCamera2');
			hookKey = "hk"+new Date().getTime() +""+ Math.round(Math.random()*10000);
			appObj.callBackList[hookKey] = hookMethod;
			// alert('onlyCamera 3');
			if (appObj.isAndroid()) {
				// alert('onlyCamera 4');
				window.share.nativeCamera(hookKey);
			} else {
				return os_ios.nativeCamera(hookKey);
			}
		},
		selectImages : function(imageCount, hookMethod) {
			hookKey = "hk"+new Date().getTime() +""+ Math.round(Math.random()*10000);
			appObj.callBackList[hookKey] = hookMethod;
			if (appObj.isAndroid()) {
				window.share.imageSelector(hookKey, imageCount);
			} else {
				return os_ios.imageSelector(hookKey, imageCount);
			}
		},
		imageSelectorWithCamera : function(imageCount, hookMethod) {
			hookKey = "hk"+new Date().getTime() +""+ Math.round(Math.random()*10000);
			appObj.callBackList[hookKey] = hookMethod;
			if (appObj.isAndroid()) {
				window.share.selectorWithCamera(hookKey, imageCount);
			} else {
				return os_ios.selectorWithCamera(hookKey, imageCount);
			}
		}
	}
}