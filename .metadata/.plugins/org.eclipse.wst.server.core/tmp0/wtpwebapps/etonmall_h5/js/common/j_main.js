(function(m,n){
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
})(window,document)
