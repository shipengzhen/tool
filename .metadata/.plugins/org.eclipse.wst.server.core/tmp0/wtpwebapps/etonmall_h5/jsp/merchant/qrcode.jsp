<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>收款码</title>
<script src="../js/common/flex.js?v=1.0"></script>
<script src="../js/common/jquery.js?v=1.0"></script>
<script src="../js/common/j_main.js?v=1.0"></script>
<script src="../js/card/jquery_qrCode.js"></script>
<link href="../css/common/public.css?v=1.0" rel="stylesheet">
<link href="../css/common/style.css?v=1.0" rel="stylesheet">
</head>
<body>
<section>
	<div class="cont_warp">
		<p class="pay_codet">请在用户付款时，向用户出示该收款码</p>
		<div id="qrcode1" class="pay_code"></div>
	</div>
</section>
<script>
//二维码
if( $('#qrcode1').length > 0 ) {
    $('#qrcode1').qrcode( "${qrCode}");
}
</script>
<style type="text/css">
.pay_code canvas{width:100%;height:100%}
</style>

</body>
</html>