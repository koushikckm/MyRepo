<%@ taglib uri="/struts-tags" prefix="s"%>

<script>
$(function(){
	var title='${session.videoTitle}';
});
	function myFunction() {
		$("#myFrameVideo").hide();
		$("#myFrame").show();
		$("#listDivId").hide();
		window.scrollTo(0, 550);
		return false;
	}
	var oldId;

	function myFunctionVideo(streemURL, clickedVideo) {
		dataLayer.video.engagement = clickedVideo;
		var url = "howtoVideoPlay.action";
		$.ajax({
			type : "POST",
			url : url,
			data : {
				"streemURL" : streemURL,
				"title": clickedVideo
			},
			
			beforeSend : function() {
				//showAjaxLoadMsg("Loading...");
			},
			
			success : function(result) {
			
				//hideAjaxLoadMsg();

				$("#videoContent").html(result);
				$("#videoContent").show();
				$("#searchedTitle").html(clickedVideo);
				$("#videoTitle").show();
			},
			
			complete : function() {
				$("#addCarSubmitBtn").hide();
			}
		});
	}

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
				$("#searchedTitle").html(clickedVideo);
				$("#videoTitle").show();

			},
			
			complete : function() {

				$("#addCarSubmitBtn").hide();
				
			}
		});
	}

	function goToVideoOption() {
		$("#videoNameList").show();
		$("#videoList").hide();
		$("#addCarSubmitBtn").show();
		$("#levelTwo").show();
		$(".innerContent").show();
		$("#showingDiv").show();
		$("#mySubmitBackBtn").hide();
		$("#titleDiv").show();
		$("#videoList").empty();
		$("#searchedTitle").html("");
		return false;
	}
	function back1() {
		$("#myFrame").hide();
		$("#myFrameVideo").hide();
		$("#listDivId").show();
		window.scrollTo(0, 550);
		return false;
	}
</script>
<% System.out.println("jsp >> MyLookUpItems");%>

 <s:if test="#session.VehiclesearchFilterList == null || #session.VehiclesearchFilterList.size==0">
 <div class="video-div" style="padding:0px !important;"><div class="No-Videostext">Search Results not found!</div>
</div>
</s:if> 

<div id="MainMenu" class="vertical-menu">
	<div class="list-group panel search-links">
	<s:if test="#session.VehiclesearchFilterList != null">
		<s:iterator value="#session.VehiclesearchFilterList" status="liCount">
			<s:if test="streemURL != null">
				<a href="#" class="list-group-item list-group-item-success strong"  onclick="return myFunctionVideo('<s:property value="streemURL"/>','<s:property value="title"/>')" data-analytics-link-component-name="table" data-analytics-link-type="button" data-analytics-link-description="<s:property value="title"/>">	
					<i class="fa fa-play-circle-o"></i>  
					<div><s:property value="title" /> </div>
					<span><s:property value="trim" /></span>							
					<!-- <div style="float: left;   height: 30px; "> <i class="fa fa-play-circle-o"></i> </div>  
					<div style="float: center; height: 15px;padding-left: 15px;"><s:property value="title" /> </div>
					<span style="float: center;  height: 15px;padding-left: 15px;color: #868383;font-size: 10px;">   <s:property value="trim" /></span>
						 -->
				</a>
			</s:if>
			</s:iterator>
	</s:if>
	</div>
</div>	




