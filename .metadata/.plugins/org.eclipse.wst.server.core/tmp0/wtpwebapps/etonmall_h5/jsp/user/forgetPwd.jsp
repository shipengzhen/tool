<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
<title>忘记密码</title>
<script src="../js/common/flex.js?v=1.0"></script>
<link href="../css/common/public.css?v=1.0" rel="stylesheet">
<link href="../css/common/style.css?v=1.0" rel="stylesheet">
<link href="../css/common/XWPlug.bundle.min.css?v=1.0" rel="stylesheet">
</head>
<body>
<section class="bg_write">
	<div class="edit_code_tips">为了您的账号安全<br>我们将发送验证码到账号绑定的手机</div>
	<ul class="edit_form1">
		<li><label>手机号</label><input class="inpt1" type="text" placeholder="请输入手机号" name="telNum" id="telNum"></li>
		<li><label>验证码</label><input type="text" class="inpt2" placeholder="请输入验证码" maxlength="6" onkeypress="code()" name="verificationCode" id="verificationCode"><input id="recode" type="button" class="inpt3" value="获取验证码" onclick="sendverificationCode('forgetPwd');"></li>
	</ul>
	<div class="btn_warp">
		<a href="javascript:forgetPwd();">下一步</a>
	</div>
</section>
<script src="../js/common/jquery.js?v=1.0"></script>
<script src="../js/user/j_main.js?v=1.0"></script>
<script src="../js/user/login.js?v=1.0"></script>
<script src="../js/common/XWPlug.bundle.min.js?v=1.0"></script>
</body>
	<script type="text/javascript">
	(function()
			{
				var agent = navigator.userAgent.toLowerCase();
				var iLastTouch = null; //缓存上一次tap的时间
				if (agent.indexOf('iphone') >= 0 || agent.indexOf('ipad') >= 0) //检测是否是ios
				{
					document.body.addEventListener('touchend', function(event)
					{
						var iNow = new Date().getTime();
						iLastTouch = iLastTouch || iNow + 1 /** 第一次时将iLastTouch设为当前时间+1 */ ;
						var delta = iNow - iLastTouch;
						if (delta < 500 && delta > 0)
						{
							event.preventDefault();
							return false;
						}  
						iLastTouch = iNow;
						}, false);
						}
			})();
	</script>
</html>