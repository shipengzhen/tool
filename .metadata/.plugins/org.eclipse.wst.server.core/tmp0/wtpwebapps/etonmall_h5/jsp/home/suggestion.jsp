<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>
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
		<title>意见反馈</title>
		<script src="../js/common/flex.js?v=1.0"></script>
		<script src="../js/common/XWPlug.bundle.min.js?v=1.0"></script>
		<link href="../css/common/public.css?v=1.0" rel="stylesheet">
		<link href="../css/common/style.css?v=1.0" rel="stylesheet">
		<link href="../css/common/XWPlug.bundle.min.css?v=1.0" rel="stylesheet">
	</head>
	<body>
	<section>
		<div class="pwcode">
			<textarea class="suggest" id="suggestion" placeholder="写下您的订单问题或其他需要解决的问题，我们将会为您及时解决！"></textarea>
		</div>
		<div class="btn_warp">
			<a href="javascript:suggestCommit()">提交反馈</a>
		</div>
	</section>
	<script src="../js/common/jquery.js?v=1.0"></script>
	<script type="text/javascript">
		function suggestCommit() {
			var suggestion = $("#suggestion").val();
			if (!suggestion) {
				XWalert("请填写意见后再提交");
			} else {
				XWalert("确认提交意见" , 'javascript:excuteCommit()');
			}
		}
		function excuteCommit() {
			offlayer();
			var suggestion = $("#suggestion").val();
			$.ajax({
	            type: "POST",
	            url: "commitSuggestion.do",
	            dataType: "json",
	            data: {
	               "suggestion": suggestion
	            },
	            async: false,
	            success: function (data) {
	            	XWalert('您的意见已提交');
	            	$('.btn_warp').hide();
// 	            	window.close();
	         	},
	            error: function () {
			    	   XWalert('您的意见已提交');
			    	   $('.btn_warp').hide();
// 			    	   window.close();
			      }
	         })
		}
	</script>
	</body>
</html>