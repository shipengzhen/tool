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
<title>个人信息</title>
<script src="../js/common/flex.js?v=1.0"></script>
<link href="../css/common/public.css?v=1.0" rel="stylesheet">
<link href="../css/common/style.css?v=1.0" rel="stylesheet">
<link href="../css/common/iosSelect.css?v=1.0" rel="stylesheet">
<link href="../css/common/XWPlug.bundle.min.css?v=1.0" rel="stylesheet">
</head>
<body>
<section>
	<ul class="own_info">
		<li>头像 
		<c:choose>
			<c:when test="${not empty user.headImage}">
				<img id="img" class="img_touxiang" src="${user.headImage}" onclick="openDiv();">
			</c:when>
			<c:otherwise>
				<img id="img" class="img_touxiang" src="../images/user/kf.png" onclick="openDiv();">
			</c:otherwise>
		</c:choose>
		</li>
		<li style="background:none">用户名<input id="telNum" type="text"  value="${user.telnum}" readonly></li>
		<li>昵称
			<c:choose>
				<c:when test="${not empty user.nickName}">
					<input type="text" id="nickname" value="${user.nickName}">
				</c:when>
				<c:otherwise>
					<input type="text" id="nickname" value="一卡通用户">
				</c:otherwise>
			</c:choose>
		</li>
		<li id="selectSex">性别
		
		<c:if test="${user.sex == '0' }">
			<input type="text" readonly id="showSex" value="男" data-sex="0">
		</c:if>
		<c:if test="${user.sex == '1' }">
			<input type="text"  readonly id="showSex" value="女" data-sex="1" >
		</c:if>
		<c:if test="${user.sex != '0' && user.sex != '1'}">
			<input type="text" readonly id="showSex" value="保密" data-sex="2">
		</c:if>
		<li id="selectDate">出生日期<input id="showDate" readonly type="text" value="${user.birthday}">
		</li>
	</ul>
	
	<input id="addressId" type="hidden" value=""/>
	<input id="imageView" type="hidden" value=""/>
	</div>
	
	<div class="btn_warp">
			<a href="javascript:personInfoSubmit();">保存</a>
	</div>
</section>
<script src="../js/common/jquery.js?v=1.0"></script>
<script src="../js/user/j_main.js?v=1.0"></script>
<script src="../js/user/iosSelect.js?v=1.0"></script>
<script src="../js/user/camera.js?v=1.0"></script>
<script src="../js/common/app.js?v=1.0"></script>
<script src="../js/common/XWPlug.bundle.min.js?v=1.0"></script>
<script type="text/javascript">

	var selectDateDom = $('#selectDate');
	var showDateDom = $('#showDate');
	var now = new Date();
	var nowYear = now.getFullYear();
	var nowMonth = now.getMonth() + 1;
	var nowDate = now.getDate();
	showDateDom.attr('data-year', nowYear);
	showDateDom.attr('data-month', nowMonth);
	showDateDom.attr('data-date', nowDate);
	function formatYear (nowYear) {
		var arr = [];
		for (var i = nowYear - 100; i <= nowYear + 100; i++) {
			arr.push({
				id: i + '',
				value: i + '年'
			});
		}
		return arr;
	}
	function formatMonth () {
		var arr = [];
		for (var i = 1; i <= 12; i++) {
			arr.push({
				id: i + '',
				value: i + '月'
			});
		}
		return arr;
	}
	function formatDate (count) {
		var arr = [];
		for (var i = 1; i <= count; i++) {
			arr.push({
				id: i + '',
				value: i + '日'
			});
		}
		return arr;
	}
	var yearData = function(callback) {
		callback(formatYear(nowYear))
	}
	var monthData = function (year, callback) {
		callback(formatMonth());
	};
	var dateData = function (year, month, callback) {
		if (/^(1|3|5|7|8|10|12)$/.test(month)) {
			callback(formatDate(31));
		}
		else if (/^(4|6|9|11)$/.test(month)) {
			callback(formatDate(30));
		}
		else if (/^2$/.test(month)) {
			if (year % 4 === 0 && year % 100 !==0 || year % 400 === 0) {
				callback(formatDate(29));
			}
			else {
				callback(formatDate(28));
			}
		}
		else {
			throw new Error('month is illegal');
		}
	};

	selectDateDom.bind('click', function () {
		var oneLevelId = showDateDom.attr('data-year');
		var twoLevelId = showDateDom.attr('data-month');
		var threeLevelId = showDateDom.attr('data-date');
		var iosSelect = new IosSelect(3, 
			[yearData, monthData, dateData],
			{
				title: '时间选择',
				itemHeight: 35,
				itemShowCount: 9,
				oneLevelId: oneLevelId,
				twoLevelId: twoLevelId,
				threeLevelId: threeLevelId,
				callback: function (selectOneObj, selectTwoObj, selectThreeObj) {
					showDateDom.attr('data-year', selectOneObj.id);
					showDateDom.attr('data-month', selectTwoObj.id);
					showDateDom.attr('data-date', selectThreeObj.id);
					showDateDom.val(selectOneObj.value + selectTwoObj.value + selectThreeObj.value);
				}
			});
	});

	var selectSexDom = $('#selectSex');
	var showSexDom = $('#showSex');
	var sex = [{"id":"0","value":"男"},{"id":"1","value":"女"},{"id":"0","value":"保密"}];
	selectSexDom.bind('click', function () {
		var iosSelect = new IosSelect(1, 
			[sex],
			{
				title: '性别选择',
				itemHeight: 35,
				itemShowCount: 5,
				oneLevelId: showSexDom.attr('data-sex'),
				callback: function (selectOneObj) {
					showSexDom.attr('data-sex', selectOneObj.id);
					showSexDom.val(selectOneObj.value);
				}
			});
	});
	
	/**
	 *	保存个人信息
	 */
	function personInfoSubmit(){
		var telNum = $("#telNum").val();
		var nickname = $("#nickname").val();
		var sex = $("#showSex").val();
		var showDate = $("#showDate").val();
		$.post(
				"personInfoSubmit.do",
				{"telNum" : telNum,"nickname":nickname,"sex":sex,"showDate":showDate},
				function(data){
					if("0" == data.resultCode){
						XWDialog( {'tips':'个人信息修改成功','mainBtn':'我知道了','mainEvent':'reload()'});
					}else{
						XWalert("个人信息修改失败");
					}
		},"json");
	}
	
	/**
	 * 	刷新页面
	 */
	function reload(){
		window.location= "personInfo.do";
	}
	
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