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
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<c:if test="${addressType == 'add'}">
	<title>新建地址</title>
</c:if>
<c:if test="${addressType == 'edit'}">
	<title>编辑地址</title>
</c:if>
<script src="../js/common/flex.js?v=1.0"></script>
<link href="../css/common/public.css?v=1.0" rel="stylesheet">
<link href="../css/common/style.css?v=1.0" rel="stylesheet">
<link href="../css/common/XWPlug.bundle.min.css?v=1.0" rel="stylesheet">
<link href="../css/common/iosSelect.css?v=1.0" rel="stylesheet">
</head>
<body>
<section>
	<ul class="address_info">
		<li>收货人<input type="text"  id="userName" value="${addressInfo.userName}"  maxlength="8" ></li>
		<li>联系电话<input type="text"  id="telnum" value="${addressInfo.telnum}"></li>
		<li id="select_contact">所在地区：<input type="text" id="show_contact" readonly placeholder="请选择 >" value="${addressInfo.region}"></li>
		<li>详细地址<textarea rows="1"  class="inp_ty1" id="address">${addressInfo.address}</textarea></li>
	</ul>
	<input id="addressType" type="hidden" value="${addressType}" />
	<input id="addressId" type="hidden" value="${addressInfo.id}" />
	<div class="btn_warp">
		<a href="javascript:addressManager();">保存</a>
	</div>
</section>
<script src="../js/common/jquery.js?v=1.0"></script>
<script src="../js/user/j_main.js?v=1.0"></script>
<script src="../js/common/areaData_v2.js"></script>
<script src="../js/user/iosSelect.js"></script>
<script src="../js/common/XWPlug.bundle.min.js?v=1.0"></script>
<script src="../js/user/addressManager.js?v=1.0"></script>
<script type="text/javascript">
	var selectContactDom = $('#select_contact');
    var showContactDom = $('#show_contact');
    var contactProvinceCodeDom = $('#contact_province_code');
    var contactCityCodeDom = $('#contact_city_code');
    selectContactDom.bind('click', function () {
        var sccode = showContactDom.attr('data-city-code');
        var scname = showContactDom.attr('data-city-name');

        var oneLevelId = showContactDom.attr('data-province-code');
        var twoLevelId = showContactDom.attr('data-city-code');
        var threeLevelId = showContactDom.attr('data-district-code');
        var iosSelect = new IosSelect(3, 
            [iosProvinces, iosCitys, iosCountys],
            {
                title: '地址选择',
                itemHeight: 35,
                relation: [1, 1],
                oneLevelId: oneLevelId,
                twoLevelId: twoLevelId,
                threeLevelId: threeLevelId,
                callback: function (selectOneObj, selectTwoObj, selectThreeObj) {
                    contactProvinceCodeDom.val(selectOneObj.id); 
                    contactProvinceCodeDom.attr('data-province-name', selectOneObj.value);
                    contactCityCodeDom.val(selectTwoObj.id);
                    contactCityCodeDom.attr('data-city-name', selectTwoObj.value);

                    showContactDom.attr('data-province-code', selectOneObj.id);
                    showContactDom.attr('data-city-code', selectTwoObj.id);
                    showContactDom.attr('data-district-code', selectThreeObj.id);
                    showContactDom.val(selectOneObj.value + ' ' + selectTwoObj.value + ' ' + selectThreeObj.value);
                }
        });
    });
</script>
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