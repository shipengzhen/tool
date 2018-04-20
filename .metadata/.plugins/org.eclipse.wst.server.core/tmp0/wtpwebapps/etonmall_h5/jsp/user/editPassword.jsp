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
<title>修改登录密码</title>
<script src="../js/common/flex.js?v=1.0"></script>
<link href="../css/common/public.css?v=1.0" rel="stylesheet">
<link href="../css/common/style.css?v=1.0" rel="stylesheet">
<link href="../css/common/XWPlug.bundle.min.css?v=1.0" rel="stylesheet">
</head>
<body>
<section class="bg_write">
	<input type="hidden" id="telNum" value="${sessionScope.LOGIN_USER.telnum}"/>
	<ul class="edit_form">
		<li><input type="password" id="oldpassword" placeholder="请输入旧密码"></li>
		<li><input type="password" id="newpassword" placeholder="请输入6-20位新密码，必须数字与字母组合"></li>
		<li><input type="password" id="repeatPassword" placeholder="再次输入新的密码" onfocus="removeGray();"></li>
	</ul>
	<div id="submit" class="btn_warp">
		<a href="javascript:resetPassword();">保存</a>
	</div>
</section>
<script src="../js/common/jquery.js?v=1.0"></script>
<script src="../js/user/j_main.js?v=1.0"></script>
<script src="../js/user/login.js?v=1.0"></script>
<script src="../js/user/managerPWD.js?v=1.0"></script>
<script src="../js/common/XWPlug.bundle.min.js?v=1.0"></script>
<script src="../js/common/app.js?v=1.0"></script>
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