//跳转新增地址页面
function toAddAddress(){
	$.post(
			"validateAddressInfo.do",
			{},
			function(data){
				if("0" == data.resultCode){
					window.location.href= "toAddAddress.do";
				}else if("2" == data.resultCode){
					XWalert("对不起，你的地址已经达到上限");
				}
				else{
					XWalert(data.resultMessage);
				}
	},"json");
}

//设为默认
function isDefault(addressId,isDefault){
	var addressId = addressId;
	var isDefault = isDefault;
	$.post(
			"isDefault.do",
			{"addressId" : addressId,"isDefault":isDefault},
			function(data){
				if("0" == data.resultCode){
					reload();
					//XWalert("地址设置默认成功", reload());
				}else{
					XWalert("地址设置默认失败");
				}
	},"json");
}

//删除地址
function deleteAddress(addressId){
	var addressId = addressId;
	$.post(
			"deleteAddress.do",
			{"addressId" : addressId},
			function(data){
				if("0" == data.resultCode){
					XWDialog( {'tips':'地址删除成功','mainBtn':'我知道了','mainEvent':'reload()'});
				}else{
					XWalert("地址删除失败");
				}
	},"json");
}

//地址管理
function addressManager(){
	var userName = $("#userName").val();
	var telnum = $("#telnum").val();
	var region = $("#show_contact").val();
	var address = $("#address").val();
	var addressType = $("#addressType").val();
	var addressId = $("#addressId").val();
	if(!userName){
		XWalert("收货人不可为空");
		return ;
	}
	if(!telnum){
		XWalert("联系电话不可为空");
		return ;
	}
	if(!region){
		XWalert("所在地区不可为空");
		return ;
	}
	if(!address){
		XWalert("地址不可为空");
		return ;
	}
	
	if(!IsTelephone(telnum)){
		XWalert("手机号码不合法");
		return ;
	}
	
	var url = "";
	if(addressType == "add"){
		url = "addAddressInfo.do";
	}
	else{
		url = "editAddressInfo.do";
	}
	$.post(
			url,
			{"addressType" : addressType,"userName":userName,"telnum":telnum,"region":region,"address":address,"addressId":addressId},
			function(data){
				if("0" == data.resultCode){
					window.location.href= "addressManager.do";
				}else{
					XWalert(data.resultMessage);
				}
	},"json");
}

//编辑地址
function toEditAddress(addressId){
	var addressId = addressId;
	window.location.href= "toAddAddress.do?addressId="+addressId;
}

//刷新页面
function reload(){
	window.location.reload();
}

function IsTelephone(telnum)// 正则判断
{ 
	var pattern1 =/^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/;
	var pattern2 = /^1\d{10}$/;
	if(!pattern1.test(telnum) && !pattern2.test(telnum)) 
	{ 
		return false;
	}
	else{
		return true;
	}
}