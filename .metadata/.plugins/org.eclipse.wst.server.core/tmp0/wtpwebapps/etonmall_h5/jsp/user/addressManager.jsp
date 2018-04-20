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
<title>地址管理</title>
<script src="../js/common/flex.js?v=1.0"></script>
<link href="../css/common/public.css?v=1.0" rel="stylesheet">
<link href="../css/common/style.css?v=1.0" rel="stylesheet">
<link href="../css/common/XWPlug.bundle.min.css?v=1.0" rel="stylesheet">
</head>
<body>
<section>
	<c:choose>
		<c:when test="${not empty adressList}">
				    <c:forEach items="${adressList}" var="adress">
				    <div class="address_list">
				    	<h3><h3>${adress.userName}<span>${adress.telnum}</span></h3>
				    	<p>${adress.address}</p>
						<div class="operate">
							<c:choose>
								<c:when test="${adress.isDefault == '0'}">
									<div class="check_warp active" id="default" onclick="isDefault('${adress.id}','${adress.isDefault}');" addressId="${adress.id}" isDefault="${adress.isDefault}"><label class="check_group active"></label>默认地址</div>
								</c:when>
								<c:otherwise>
									<div class="check_warp" id="default" onclick="isDefault('${adress.id}','${adress.isDefault}');" addressId="${adress.id}" isDefault="${adress.isDefault}"><label class="check_group"></label>默认地址</div>
								</c:otherwise>
							</c:choose>
							<a href="javascript:deleteAddress('${adress.id}');" addressId="${adress.id}"><i class="icon_del"></i>删除</a>
							<a href="javascript:toEditAddress('${adress.id}');" addressId="${adress.id}"><i class="icon_edit"></i>编辑</a>
						</div>
					</div>
					</c:forEach>
		</c:when>
		<c:otherwise>
			<div class="await">
				<p>
				尚未创建收货地址  <br/>
				正确创建收货地址，才是您收到心仪货品的保证哦
				</p >
				<img src="../images/user/jqqd.png">
			</div>
		</c:otherwise>
	</c:choose>
	<div class="btn_warp2">
		<a href="javascript:toAddAddress();">+ 新建地址</a>
	</div>
</section>

<script src="../js/common/jquery.js?v=1.0"></script>
<script src="../js/user/j_main.js?v=1.0"></script>
<script src="../js/common/XWPlug.bundle.min.js?v=1.0"></script>
<script src="../js/user/addressManager.js?v=1.0"></script>
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