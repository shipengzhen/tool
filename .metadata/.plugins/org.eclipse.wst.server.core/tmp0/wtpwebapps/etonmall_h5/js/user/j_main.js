(function(m,n){
	//$("body").on("touchmove",function(event){event.preventDefault();})
	$('.popup_warp').on('click', function(event){
		if( $(event.target).is('.close') || $(event.target).is('.popup_warp') ) {
			event.preventDefault();
			$(this).hide();
		}
	});
	$('.close').on('click', function(){
		$(".popup_warp").hide();
	});
	$('.sel_city').on('click', function(){
		$("section").hide();
		$(".city_warp").show();
	});
	$('.ser_warp').on('click', function(){
		$("section").hide();
		$(".index_search").show();
	});
	$('.btn_qx,.close_city').on('click', function(){
		$("section").hide();
		$(".home").show();
	});

	//获取验证码倒计时
	var wait=60;
	n.time = function(o) {
		if (wait == 0) {
			o.removeAttribute("disabled");
			o.value="点击获取";
			wait = 60;
		} else {
			o.setAttribute("disabled", true);
			o.value="重新获取(" + wait + ")";
			wait--;
			setTimeout(function() {
				n.time(o)
			},1000)
		}
	}

	n.code = function(){
		if(event.keyCode<48 || event.keyCode>57){
			event.returnValue=false;
		}
	}

	n.card_pop =function(){
		$(".card_pop").show();
		$(".popup_card").show();
	}
/*	$('.card_pop a').on('click', function(){
		$(".card_pop").hide();
		$(".popup_card").hide();
	});
*/
	$('.card_list dt').on('click', function(){
		$(this).toggleClass("active");
		$(this).next("dd").toggle("fast");
	});

})(window,document)
