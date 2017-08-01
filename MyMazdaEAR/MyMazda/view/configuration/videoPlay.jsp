<%@ taglib uri="/struts-tags" prefix="s"%>
<link href="./view/vhelper/video/dist/skin/blue.monday/css/jplayer.blue.monday.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./view/vhelper/video/dist/jplayer/jquery.jplayer.min.js"></script>

<script type="text/javascript">
	function showSSGMsg(text){
		//alert(text);
		
		if(text.indexOf("img/") != -1 && text.indexOf("src=") == -1)
		{
		$("#ssgImageCont").attr('src', 'http://test.mymazda.com/MyMazdaWeb/SSG/XML/2014/CX5/IMAGES/DESKTOP/'+text);
		$("#ssgImageCont").show();
		$("#ssgXMLContainer").show();
		}
	else {
		$("#ssgMessage").html(text);
		$("#ssgXMLContainer").show();
		}
	}
	
	function showSSGMsg2(text){
		var temp=document.getElementById(text).innerHTML;
		$("#ssgMessage2").html(temp);
		$("#ssgXMLContainer2").show();
		
	}	
	
	function closeContant2(){
		
		 $("#ssgImageCont2").attr('src', '');
		$("#ssgXMLContainer2").hide();
	}
	
	function closeContant(){
		$("#ssgMessage").text("");
		 $("#ssgImageCont").attr('src', '');
		$("#ssgXMLContainer").hide();
		 $("#ssgImageCont2").attr('src', '');
			$("#ssgXMLContainer2").hide();
	}
	
	function controlVideo(videoName, control){
		var currentDuration = Math.trunc(($('#current-time').html($('#jquery_jplayer_1').find('video').get(0).currentTime)[0].innerHTML));
		var quater = Math.trunc(($('#time-duration').html($('#jquery_jplayer_1').find('video').get(0).duration)[0].innerHTML)/4); // 25_Percent
		var half = Math.trunc(($('#time-duration').html($('#jquery_jplayer_1').find('video').get(0).duration)[0].innerHTML)/2); // 50_percent
		var threeQuaters = Math.trunc((($('#time-duration').html($('#jquery_jplayer_1').find('video').get(0).duration)[0].innerHTML) * 75) / 100); // 75_percent
		var totalDuration = Math.trunc(($('#time-duration').html($('#jquery_jplayer_1').find('video').get(0).duration)[0].innerHTML));

		if(control == 'play' && currentDuration <= 0.1){
			dataLayer.video.engagement = videoName +":play";
		} else if(control == 'play' && currentDuration > 0.01 && currentDuration <= quater){
			dataLayer.video.engagement = videoName +":pause:25_percent";
		} else if(control == 'play' && currentDuration > 0.01 && currentDuration > quater && currentDuration <= half){
			dataLayer.video.engagement = videoName +":pause:50_percent";
		} else if(control == 'play' && currentDuration > 0.01 && currentDuration > half && currentDuration <= threeQuaters){
			dataLayer.video.engagement = videoName +":pause:75_percent";
		} else if(control == 'stop' && currentDuration <= 0.1){
			dataLayer.video.engagement = videoName +":stop";
		} else if(control == 'stop' && currentDuration > 0.01 && currentDuration <= quater){
			dataLayer.video.engagement = videoName +":stop:25_percent";
		} else if(control == 'stop' && currentDuration > 0.01 && currentDuration > quater && currentDuration <= half){
			dataLayer.video.engagement = videoName +":stop:50_percent";
		} else if(control == 'stop' && currentDuration > 0.01 && currentDuration > half && currentDuration <= threeQuaters){
			dataLayer.video.engagement = videoName +":stop:75_percent";
		}  
		
		$('#jp_video_0').on("timeupdate",function(event){
			onTrackedVideoFrame(this.currentTime, this.duration);
		});
		function onTrackedVideoFrame(currentTime, duration){
			var a = Math.trunc($('#current-time').text(currentTime)[0].innerHTML);
			var b = Math.trunc($('#time-duration').text(duration)[0].innerHTML);
			if(a == quater){
				dataLayer.video.engagement = videoName +":25_percent";
			} else if(a == half){
				dataLayer.video.engagement = videoName +":50_percent";
			} else if(a == threeQuaters){
				dataLayer.video.engagement = videoName +":75_percent";
			} else if(a == totalDuration){
				dataLayer.video.engagement = videoName +":complete";
			}
		} 
	}

	function fullScreenVideo(videoName){
		dataLayer.video.engagement = videoName +":fullscreen";
	}
</script>

		<s:if test="#session.selectedVideoURL != null">
			<div class="row margin-0">
				<script type="text/javascript">
								//<![CDATA[
					$(document).ready(function(){
						$("#jquery_jplayer_1").jPlayer({
							ready: function () {
								$(this).jPlayer("setMedia", {
									m4v: "<s:property value="streemURL"/>",
								});
							},
							swfPath: "./view/vhelper/video/dist/jplayer",
							supplied: "webmv, ogv, m4v",
							size: {
								width: "100%",
								height: "360px",
								cssClass: "jp-video-360p"
							},
							useStateClassSkin: true,
							autoBlur: false,
							smoothPlayBar: true,
							keyEnabled: true,
							remainingDuration: true,
							toggleDuration: true
						});
					
					});
					</script>
					
				
					<div class="col-md-12 hidden-md hidden-lg" style="display:none;height:450px;">
						<div class="embed-responsive embed-responsive-16by9">
						    <iframe class="embed-responsive-item" src="<s:property value="streemURL"/>"></iframe>
						</div>
					
						
					</div>
					
			<div class="col-md-12 col-xs-12">
				<div id="jp_container_1" class="jp-video embed-responsive embed-responsive-16by9"  role="application" aria-label="media player" style="height:450px;">
					<div class="jp-type-single">
						<div id="jquery_jplayer_1" class="jp-jplayer"></div>
						<div class="jp-gui">
							<div class="jp-video-play">
								<button class="jp-video-play-icon" role="button" tabindex="0" style="height: 63px;" data-analytics-link-component-name="video_player" data-analytics-link-type="submit" data-analytics-link-description="play" onclick="controlVideo('<s:property value="title"/>','play')">play</button>
							</div>
							<div class="jp-interface">
								<div class="jp-progress">
									<div class="jp-seek-bar">
										<div class="jp-play-bar"></div>
									</div>
								</div>
								<div class="jp-current-time" id="current-time" role="timer" aria-label="time">&nbsp;</div>
								<div class="jp-duration" id="time-duration" role="timer" aria-label="duration">&nbsp;</div>
								<div class="jp-controls-holder">
									<div class="jp-controls">
										<button class="jp-play" role="button" tabindex="0" data-analytics-link-component-name="video_player" data-analytics-link-type="submit" data-analytics-link-description="play" onclick="controlVideo('<s:property value="title"/>','play')">play</button>
										<button class="jp-stop" role="button" tabindex="0" data-analytics-link-component-name="video_player" data-analytics-link-type="cancel" data-analytics-link-description="stop" onclick="controlVideo('<s:property value="title"/>','stop')">stop</button>
									</div>
									<div class="jp-volume-controls">
										<button class="jp-mute" role="button" tabindex="0" data-analytics-link-component-name="video_player" data-analytics-link-type="button" data-analytics-link-description="mute">mute</button>
										<button class="jp-volume-max" role="button" tabindex="0" data-analytics-link-component-name="video_player" data-analytics-link-type="button" data-analytics-link-description="max_volume">max volume</button>
										<div class="jp-volume-bar">
											<div class="jp-volume-bar-value"></div>
										</div>
									</div>
									<div class="jp-toggles">
										<button class="jp-repeat" role="button" tabindex="0" data-analytics-link-component-name="video_player" data-analytics-link-type="button" data-analytics-link-description="repeat">repeat</button>
										<button class="jp-full-screen" role="button" tabindex="0" data-analytics-link-component-name="video_player" data-analytics-link-type="button" data-analytics-link-description="fullscreen" onclick="fullScreenVideo('<s:property value="title"/>')">full screen</button>
									</div>
								</div>
								<div class="jp-details">
									<div class="jp-title" aria-label="title">&nbsp;</div>
								</div>
							</div>
						</div>
						<div class="jp-no-solution">
							<span>Update Required</span>
							To play the media you will need to either update your browser to a recent version or update your <a href="http://get.adobe.com/flashplayer/" target="_blank">Flash plugin</a>.
						</div>
					</div>
				</div>
					</div>
					
				
  				<!-- <input type="submit" style="margin-left: 39%;"	onclick="return scheduleContaint();" class="Submit_btn" value="OK"> -->
			</div>
		</s:if>
		<s:else>
		
			${generatedSSGStr}
			<!-- 
			<s:iterator var="temp" value="#session.selectedImageURL">
			${temp.textContent}
			
			<img src="<s:property value="streemImg"/>" id="imageid" usemap="#ssgmap">
				<map name="ssgmap">
					<s:iterator value="coordsList">
						 <area shape="rect" style="outline: none;" coords="<s:property value="x1"/>,<s:property value="y1"/>,<s:property value="x2"/>,<s:property value="y2"/>" href="javaScript:;" onclick="return showSSGMsg('<s:text name="textContent"/>')">
					</s:iterator>
					<%-- <div style="font-size: 18px; font-family: roboto;">
						<s:iterator value="coordsList">						
							 <s:text name="textContent"/>
						</s:iterator>
					</div> --%>
				</map>
			</s:iterator>
			 -->
		</s:else>
		
		<div id="ssgXMLContainer" class="vinDivClass signpop extra" style=" display: none; padding:10px; overflow: scroll; width: auto;height: auto;max-height: 500px;">
		  	
		  	
		  	<div id="ssgMessage"></div>
		  	
		  	<div>
		  		<img id="ssgImageCont" src="" style="display: none;"> 
		  	</div>
		  	<input type="submit" onclick="return closeContant()" class="Submit_btn" value="OK" style="float:right;margin: 10px 48% 10px 0px;">
		  		
		</div>
		
		<div id="ssgXMLContainer2" class="vinDivClass signpop" style=" display: none; padding:20px; overflow: scroll; max-width: 400px;height: auto;max-height: 400px;">
		  	
		  	<div id="ssgMessage2"></div>
		  	
		  	<div>
		  		<img id="ssgImageCont2" src="" style="display: none;"> 
		  	</div>
		  	<input type="submit" onclick="return closeContant2()" class="Submit_btn" value="OK" style="float:right;margin: 10px 34% 10px 0px;">
		  	
		  		
		</div>		
		
		<div id="xyz1" style="display: none;">Test popup div....</div>
		
		
		<s:iterator var="temp" value="#request.divItems">
			<div id="${temp.key}" style="display: none;">${temp.value}</div>
		</s:iterator>
		
		