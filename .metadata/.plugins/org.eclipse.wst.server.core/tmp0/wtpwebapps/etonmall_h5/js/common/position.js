function getLocation() {
	if (typeof (appObj) == "undefined") {
		notInApp();
	} else {
		appObj.appjsGetLocation(function(json) {
			json = eval('(' + json + ')');
			var errorCode= json.errorCode;
			var location = json.location;
			var posiX = location.latitude;
		 	var posiY = location.longitude;
		 	var provinceName = location.province;
		 	var cityCode = location.cityCode;
		 	var areaCode = location.adCode;
			//调用成功
			if (errorCode == 0){
			 	$.ajax({
					type : "post",
					url : "../position/updatePositionInfo.do",
					data : {"provinceName" : provinceName , 
							"cityCode" : cityCode,
							"areaCode" : areaCode,
							"posiX" : posiX,
							"posiY" : posiY},
					async : true ,
					success:function (data){
						if(data.resultCode == 1){
							XWalert(data.resultMessage);
						}
					}
			 	});
			}
			else {
				$.ajax({
					type : "post",
					url : "../position/updatePositionInfo.do",
					async : true ,
					success:function (data){
						if(data.resultCode == 1){
							XWalert(data.resultMessage);
						}
					}
			 	});
				//未完成获取
				if (errorCode == 10000){
					setTimeout("getLocation()",3000);
				}
				//未开启定位
				else{
					XWalert("请开启定位功能");
				}
			}
		});
	}
}

function updateCity (cityCode){
	$.ajax({
		type : "post",
		url : "../position/updatePositionInfo.do",
		data : { 
				"cityCode" : cityCode,
			},
		async : true ,
		success:function (data){
			if(data.resultCode == 1){
				XWalert(data.resultMessage);
			}
		}
 	});
}
