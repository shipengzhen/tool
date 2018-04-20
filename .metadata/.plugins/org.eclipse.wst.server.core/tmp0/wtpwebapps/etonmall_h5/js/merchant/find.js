window.onload = function(){
	getLocation();
	appObj.pageLoadFinished();
}

$('.sel_tab li').on('click', function(){
	$(this).toggleClass("active").siblings().removeClass("active");
	console.log();
	$(".sel_cont").eq($(this).index()).toggle().siblings(".sel_cont").hide()
});

function selectArea(areaId){
	$("#regionId").val(areaId);
	var typeId = $("#typeId").val();
	var detailTypeId = $("#detailTypeId").val();
	if (detailTypeId == '' || detailTypeId == undefined || detailTypeId == null){
		window.location.href="../merchant/findPage.do?area="+areaId;
	}
	else {
		window.location.href="../merchant/findPage.do?area="+areaId+"&type="+typeId+"&detailType="+detailTypeId;
	}
}

function selectDetailType(detailTypeId){
	$("#detailTypeId").val(detailTypeId);
	var typeId = $("#typeId").val();
	var areaId = $("#regionId").val();
	if (areaId == '' || areaId == undefined || areaId == null){
		window.location.href="../merchant/findPage.do?type="+typeId+"&detailType="+detailTypeId;
	}
	else {
		window.location.href="../merchant/findPage.do?area="+areaId+"&type="+typeId+"&detailType="+detailTypeId;
	}
}

function selectSort(){
	var areaId = $("#regionId").val();
	var typeId = $("#typeId").val();
	var detailTypeId = $("#detailTypeId").val();
	if (detailTypeId == '' || detailTypeId == undefined || detailTypeId == null){
		if (areaId =='' || areaId == undefined || areaId == null ){
			window.location.href="../merchant/findPage.do?sortKey=distance";
		}
		else {
			window.location.href="../merchant/findPage.do?area="+areaId+"&sortKey=distance";
		}
	}
	else {
		if (areaId =='' || areaId == undefined || areaId == null ){
			window.location.href="../merchant/findPage.do?type="+typeId+"&detailType="+detailTypeId+"&sortKey=distance";
		}
		else {
			window.location.href="../merchant/findPage.do?area="+areaId+"&type="+typeId+"&detailType="+detailTypeId+"&sortKey=distance";
		}
	}
}

function selectType(typeId){
	$("#typeId").val(typeId);
	$("#typeDiv").find(".active").removeClass("active");
	$("#type"+typeId).addClass("active");
	$.ajax({
		type : "GET",
		url : "../merchant/detailTypes.do",
		data : {
			"typeId" :typeId,
		},
		dataType : "json",
		async : true,
		success : function(data) {
			$("#detailTypeUl").html('');
			for (var i in data){
				htmlText = "";
				htmlText += '<li onclick="javascript:selectDetailType(\''+data[i].code+'\');">'+
				data[i].name+'<span></span>'+
				'</li>';
				$("#detailTypeUl").append(htmlText);
			}
		}
	});
}

function selectAllType(){
	var areaId = $("#regionId").val();
	if (areaId != null && areaId != undefined && areaId != ''){
		window.location.href="../merchant/findPage.do?area="+areaId;
	}
	else {
		window.location.href="../merchant/findPage.do";
	}
}