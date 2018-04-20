window.onload = function(){
	getHistorys();
	if (typeof (appObj) == "undefined") {
		notInApp();
	} else {
		//更新定位地址
		getLocation();
		//获取首页城市名和定位城市名
		setTimeout("updateUserPosition()",1000);
		appObj.pageLoadFinished();
	}
}

/**
 * 更新首页城市名
 * @returns
 */
function updateUserPosition() {
	$.ajax({
		type : "GET",
		url : "../position/getUserCityName.do",
		dataType : "json",
		async : false,
		success : function(data) {
			if (data.resultCode == 0){
				$("#cityName").html(data.name);
			}
			if (data.resultCode == 1){
				setTimeout("updateUserPosition()",3000);
			}
		}
	});
	updateLocalCity();
}

/**
 * 更新定位城市名
 * @returns
 */
function updateLocalCity (){
	$.ajax({
		type : "GET",
		url : "../position/getLocalCity.do",
		dataType : "json",
		async : true,
		success : function(data) {
			var name = data.name;
			if (name){
				$("#locationCity").html(name);
			}
		}
	});
}

/**
 * 选择定位地址
 * @returns
 */
function selectLoactionCity() {
	$("section").css({"z-index":"-1"});
	$(".home").css({"z-index":"1"})
	$.ajax({
		type : "GET",
		url : "../position/clearSelectPosition.do",
		data : {},
		dataType : "json",
		async : false,
		success : function(data) {
			if (data.resultCode == 0){
				$("#cityName").html(data.name);
			}
		}
	});
}

/**
 * 选择城市
 * @param cityCode
 * @returns
 */
function selectCity(cityCode) {
	$.ajax({
		type : "GET",
		url : "../position/selectUserPosition.do",
		data : {
			"cityCode" : cityCode,
		},
		dataType : "json",
		async : false,
		success : function(data) {
			if (data.resultCode == 1){
				XWalert(data.resultMessage);
				return false;
			}
			$("section").css({"z-index":"-1"});
			$(".home").css({"z-index":"1"})
			$("#cityName").html(data.resultMessage);
		}
	});
	$.ajax({
		type : "GET",
		url : "../position/getHistoryPosition.do",
		data : {},
		dataType : "json",
		async : true,
		success : function(data) {
			$("#dd_history_list").html('');
			for (var i=0;i<data.length;i++){
				html_text = '<a href="javascript:selectCity(\''
					+data[i].code+'\');" >'+data[i].name+'</a>';
				$("#dd_history_list").append(html_text);
			}
		}
	});
}

/**
 * 搜索
 * @param searchWord
 * @returns
 */
function search(searchWord){
	var  wd = $("#searchInput").val();
	if (searchWord != null && searchWord !='' && searchWord != undefined){
		wd = searchWord;
		$("#searchInput").val(searchWord);
	}
	wd = wd.replace(/(^\s*)|(\s*$)/g,''); 
	if (wd == null || wd == '' || wd == undefined){
		XWalert("请输入要搜索的关键字");
		return false ;
	}
	else {
		setHistoryCookie (wd);
		getHistorys();
		$.ajax({
			type : "GET",
			url : "../search/search.do",
			data : {
				"wd" : wd,
			},
			dataType : "json",
			async : true,
			success : function(data) {
				$("#searchHot").hide();
				$("#searchResult").show();
				$("#searchResult").html('');
				if (data.length>0){
					for (var i=0 ; i<data.length; i++){
						var html = '<a href="'+data[i].code+'">'+data[i].name+'</a>';
						$("#searchResult").append(html);
					}
				}
				else {
					$("#searchHot").hide();
					$("#searchResult").show();
					$("#searchResult").html('<a disabled=true>抱歉，未找到相关信息，可尝试修改后重试</a>');
				}
			}
		});
	}
}

$('.sel_city').on('click', function(){
	$(".home").css({"z-index":"-1"})
	$(".city_warp").css({"z-index":"1"})
});
$('.ser_warp').on('click', function(){
	$(".home").css({"z-index":"-1"})
	$(".index_search").css({"z-index":"1"})
});
$('.btn_qx,.close_city').on('click', function(){
	$("section").css({"z-index":"-1"});
	$(".home").css({"z-index":"1"})
});

/**
 * 设置充值搜索历史记录
 * @param phoneNum 手机号
 * @param name 备注
 * @returns
 */
function setHistoryCookie (searchWord,url){
	var historyFirstCookie = localStorage.historyFirstCookie;
	
	var historySecondCookie = localStorage.historySecondCookie;
	
	var historyThirdCookie = localStorage.historyThirdCookie;
	
	var historyFourthCookie = localStorage.historyFourthCookie;
	
	var historyFifthCookie = localStorage.historyFifthCookie;
	
	//如果现有的历史记录中该手机号用新手机号提掉最后一个
	if (searchWord != historyFirstCookie &&
			searchWord != historySecondCookie &&
			searchWord != historyThirdCookie&&
			searchWord != historyFourthCookie&&
			searchWord != historyFifthCookie){
		localStorage.historyFirstCookie=historySecondCookie;
		
		localStorage.historySecondCookie=historyThirdCookie;
		
		localStorage.historyThirdCookie=historyFourthCookie;
		
		localStorage.historyFourthCookie=historyFifthCookie;
		
		localStorage.historyFifthCookie=searchWord;
	}
}

//获得历史记录
function getHistorys (){
	$("#history").html('');
	
	var historyFirstCookie = localStorage.historyFirstCookie;
	
	var historySecondCookie = localStorage.historySecondCookie;
	
	var historyThirdCookie = localStorage.historyThirdCookie;

	var historyFourthCookie = localStorage.historyFourthCookie;
	
	var historyFifthCookie = localStorage.historyFifthCookie;
	
	if (historyFifthCookie != "null"&& historyFifthCookie !=undefined && historyFifthCookie != "undefined"){
		htmlText = '<a href="javascript:search(\''+historyFifthCookie+'\');">'+historyFifthCookie+'</a>';
		$("#history").append(htmlText);
	}
	
	if (historyFourthCookie != "null"&& historyFourthCookie !=undefined && historyFourthCookie != "undefined"){
		htmlText = '<a href="javascript:search(\''+historyFourthCookie+'\');">'+historyFourthCookie+'</a>';
		$("#history").append(htmlText);
	}
	
	if (historyThirdCookie != "null"&& historyThirdCookie!=undefined && historyThirdCookie != "undefined"){
		htmlText = '<a href="javascript:search(\''+historyThirdCookie+'\');">'+historyThirdCookie+'</a>';
		$("#history").append(htmlText);
	}
	
	if (historySecondCookie != "null"&& historySecondCookie!=undefined && historySecondCookie != "undefined"){
		htmlText = '<a href="javascript:search(\''+historySecondCookie+'\');">'+historySecondCookie+'</a>';
		$("#history").append(htmlText);
	}
	
	if (historyFirstCookie != "null" && historyFirstCookie!=undefined && historyFirstCookie != "undefined"){
		htmlText = '<a href="javascript:search(\''+historyFirstCookie+'\');">'+historyFirstCookie+'</a>';
		$("#history").append(htmlText);
	}
}

//清除历史记录
function clearHistory (){
	$("#history").html('');
	localStorage.clear();
}

//点击搜索页隐藏
function cancelSearch(){
	$("#searchHot").show();
	$("#searchResult").hide();
	$("#searchInput").val("");
}
