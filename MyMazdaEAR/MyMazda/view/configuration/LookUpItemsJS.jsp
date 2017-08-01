<script type="text/javascript">
	$('.required-input').on('blur', function() {
		if($(this).val().trim() == '') {
			$(this).addClass('required-alert');
			$(this).attr("title", "This is required");
		} else {
			$(this).removeClass('required-alert');
			$(this).attr("title", "");
		}
	});

	$(".getQuotesFormSHOW").click(function(e) {
		e.preventDefault();
		e.stopPropagation();
		$(".getQuotesForm").show();
	});

	function myFunctionImages(streemImg, title) {
		var url = "howtoVideoPlay.action";
		$.ajax({
			type : "POST",
			url : url,
			data : {
				"streemImg" : streemImg,
				"title" : title
			},
			beforeSend : function() {
				//showAjaxLoadMsg("Loading...");
			},
			success : function(result) {
				//hideAjaxLoadMsg();
				$("#videoNameList").hide();
				$("#videoList").html(result);
				$("#videoList").show();
				$("#addCarSubmitBtn").hide();
				$("#levelTwo").hide();
				$(".innerContent").hide();
				$("#searchBoxDiv").hide();
				$("#showingDiv").hide();
				$("#mySubmitBackBtn").show();
				//$("#searchedTitle").html(clickedVideo);

			},
			complete : function() {

				$("#addCarSubmitBtn").hide();
				
			}
		});
	}
	function myFunction() {
		$("#myFrameVideo").hide();
		$("#myFrame").show();
		$("#listDivId").hide();
		window.scrollTo(0, 550);
		return false;
	}
	
	
	
	var oldId;
	function myFunctionVideo(streemURL, clickedVideo) {
		//dataLayer.video.engagement = clickedVideo;
		//"MX5_Exterior_Lights.m4v"
		var url = "howtoVideoPlay.action";
		$.ajax({
			type : "POST",
			url : url,
			data : {
				"streemURL" : streemURL,
				"title": clickedVideo
			},
			beforeSend : function() {
				$("#loadingmessage").show();
			},
			success : function(result) {
				//hideAjaxLoadMsg();	
				$("#videoContent").html(result);
				$("#videoContent").show();
				$("#searchedTitle").html(clickedVideo);	
				$("#videoTitle").show();
				
			},
			complete : function() {
				$("#loadingmessage").hide();
			}
		});
	}

	function goToVideoOption() {
		$("#videoNameList").show();
		$("#searchBoxDiv").show();
		$("#videoList").hide();
		$("#addCarSubmitBtn").show();
		$("#showingDiv").show();
		$("#mySubmitBackBtn").hide();
		$("#videoList").empty();
		$("#searchedTitle").html("");
		return false;
	}

	function back1() {
		$("#myFrame").hide();
		$("#myFrameVideo").hide();
		$("#listDivId").show();
		$("#searchBoxDiv").show();
		window.scrollTo(0, 550);
		return false;
	}
</script>