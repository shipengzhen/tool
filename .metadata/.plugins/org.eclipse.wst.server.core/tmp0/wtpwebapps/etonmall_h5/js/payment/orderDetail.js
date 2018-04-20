function getOrderDetail(orderId) {
	window.location.href = "../payment/getOrderDetail.do?orderId=" + orderId;
}

function showTotal(){
	$(".active").removeClass();
	$("#showTotal").addClass("active");
	$(".mx_cont").css("display","block");
}

function showOut(){
	$(".active").removeClass();
	$("#showOut").addClass("active");
	$(".mx_cont").css("display","block");
}

function showIn(){
	$(".active").removeClass();
	$("#showIn").addClass("active");
	$(".mx_cont").css("display","none");
}