<%@page import="com.mazda.configuration.common.transferobject.RegistrationTO"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
function loginContactMazda(){
        	var serviceUrl ='${selectedVehical.serviceUrl}';
        	var dealerName = '${selectedVehical.dealerName}';
        	var phone = '${selectedVehical.dealerPhone}';
        	if(serviceUrl == null || serviceUrl==""){
        		if(!dealerName.trim()){
        			var message = "You have not selected preferred dealer, please select a dealer to schedule appointment.";
        		} else{
        		var message =dealerName+" does not have the online appointment process. Please call "+ phone + " to  setup an appointment with "+dealerName;
        		}
        		$("#appointmentMessage").html(message);
        		$("#appointMessId").show();
        	}else{
        	var message ="Please proceed to setup an appointment with "+dealerName;
				showAjaxLoadMsg(message);
				setTimeout(function() {},2250);
				window.open(serviceUrl,'_blank');
				hideAjaxLoadMsg();
        	}
        	window.scrollTo(0, 50);
        		return false;
        }



selectedVehicleVin = '${selectedVehical.vin}';
		$('#selector' + selectedVehicleVin).addClass("active");
		
		
		 function updateSelectedVehicalPreferredDealer(vin){
        	$.ajax({ 
        	data: {"vin":vin},
        	url:"updateSelectedVehicalPreferredDealer.action",
        	 beforeSend:function(){
			      showAjaxLoadMsg("Loading...");
			    },
			    complete:function(){
			    },
        	success:function(result){
        		hideAjaxLoadMsg();	
     			 $("#pageContainDiv").html(result);
     			 hideAppointmentMessage();
     			 window.scrollTo(0, 50);
  			 	 }});
  			 	 return false;
        }
        
        function editProfile(){
		$.ajax({
        	url:"editProfile.action",
        	success:function(result){
     			 $("#pageContainDiv").html(result);
     			 window.scrollTo(0, 50);
  			 	 }});
			}
        
        function showExpressMess(){
			$("#mazdaExpress").show();
		}
		
		function hideExpressMess(){
			$("#mazdaExpress").hide();
		}
		
	 function addPreferedDealer(){
		var dataString ="&addVehicleFlag=true&editUpdateFlag=Prefer";
		$.ajax({
        	url:"addPreferedDealer.action",
        	data : dataString,
        	 beforeSend:function(){
			      showAjaxLoadMsg("Loading...");
			    },
			    complete:function(){
  					$("#byZip").addClass("active");
			    	$("#searchByZipcode").addClass("active");
			    },
        		success:function(result){
        			hideAjaxLoadMsg();
        			window.scrollTo(0,0);
     			 	$("#pageContainDiv").html(result);
     			 	
  			 	 }});
		return false;
	}

</script>
<header>
	<div class="top_line">
		<div class="container">
			<div class="row" style="">
				<s:if test="#session.loginUser != null">
				<div class="homeicon">
						<ul class="social-links">	
							<li><a href="myMazda.action" class=""><img title="MyMazda" src="view/vhelper/images/home_m.png" class="logoutb"></a></li>
							</ul>
					</div>
					<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10 pull-right ">
						<ul class="social-links">
							<li><h2 class="heading" style="margin: 8px 15px 0px 0px;">Edit Preferred Dealer</h2></li>
							<li><a href="mymazdaHome.action" class=""><img title="Logout"  src="view/vhelper/images/logout1.png" class="logoutb"></a></li>
						</ul>
					</div>
				</s:if>
				<s:else>
				<div class="homeicon">
						<ul class="social-links">	
							<li><a href="myMazda.action" class=""><img src="view/vhelper/images/home_m.png" class="logoutb"></a></li>
							</ul>
					</div>
					<div class="col-lg-10 col-md-10 col-ms-12 pull-right ">
						<ul class="social-links">
							<li><h2 class="heading" style="margin: 8px 15px 0px 0px;">Edit Preferred Dealer</h2></li>
							<li><a href="javaScript:;" onclick="return login();"
								class="browseCategory getQuotesFormSHOW">Login</a></li>
							<li><a href="javaScript:;" onclick="return registerNew();"
								class="browseCategory">Sign Up</a></li>
						</ul>
					</div>
				</s:else>
			</div>
		</div>
	</div>
</header>

<div class="section1 padd50">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 heightless4">
				
				<div class="col-xs-12 ">
				<h2 class="heading22">
					Welcome :
					<s:if test="#session.loginDetail.firstName != null || #session.loginDetail.lastName != null">
					<s:text name="#session.loginDetail.firstName" /> <s:text name="#session.loginDetail.lastName" />
				</s:if>	
				</h2>
				</div>	
				<div class="col-xs-12">
					<a class="Editprofile" onclick="return editProfile();" href="javaScript:;">EDIT PROFILE</a> 
					
				</div>
				<div style="clear: both;"></div>	
				<div class="tabs">
					<ul class="nav nav-tabs">
						<s:if test="#session.loginUser != null">
							<s:iterator value="#session.vehicalsList" status="count">
								<li id="selector<s:property value="vin"/>"><a
									href="javaScript:;"
									onclick="return updateSelectedVehicalPreferredDealer('<s:property value="vin"/>');">
										<s:property value="carlineDesc" /> </a></li>
							</s:iterator>
						</s:if>
						<!-- <a href="myMazda.action" id="addCarSubmitBtn" class="Submit_btn" style="right:1px; text-align:center; text-transform: none; top: 7px;position: absolute;float: right;top: 7px;">Back</a> -->
					</ul>
				</div>
				<div class="tab-content" id="mydiv" style="margin:1px 0 0 0;">
							<div class="tab-pane active" id="popularPosts" style="font-family: roboto;font-size: 14px; font-weight: bold;">
								<div class="tabbedwidget">
										<span style="font-weight:600; margin:0 0 0 0;">
										<s:text name="#session.selectedVehical.vin"/>
										</span>
										<div style="margin:19px 0px 8px 0px; font-weight:600;">
										<s:if test="#session.selectedVehical.dealerName != null && #session.selectedVehical.serviceDealerID >0">
										<s:text name="#session.selectedVehical.dealerName"/><br>
											<s:text name="#session.selectedVehical.dealerAddress"/><br>
											<s:text name="#session.selectedVehical.dealerCity"/>
											<s:text name="#session.selectedVehical.dealerState"/>
											<s:text name="#session.selectedVehical.dealerZip"/><br>
											<span style="color: #7e0000;">
											<s:text name="#session.selectedVehical.dealerPhone" />
											</span>
											<s:if test="#session.selectedVehical.expServiceFlag !=null">
													<br><a href="javaScript:;" onclick="return showExpressMess();" style="color: #00004C;font-weight: 800;mrgin-top: -5px; text-decoration:underline;">
													<s:text name="#session.selectedVehical.expServiceFlag" />
												</a>
											</s:if>
											<span style="margin: 0 0 0 0;"><br/>
												<a href="javaScript:;" onclick="return addPreferedDealer();" style="text-decoration: underline; margin: 0px;">Edit Preferred Dealer</a>
											</span>
											</s:if>
											<s:else>
												<a href="javaScript:;" onclick="return addPreferedDealer();" style="text-decoration: underline; margin: 0px;">Add Preferred Dealer</a>
											</s:else>
										</div>
			</div>
		</div>
	</div>
</div>
</div>
</div>
</div>

<div id="mazdaExpress" class="vinDivClass" style="position: fixed; left: 37%;margin-top:130px;  display:none; background: #F4F4F4;">
<center>	<h2 >Mazda Express Service</h2>
<img alt="" src="view/vhelper/images/ICONmes@2x.png" height="100px;"></center>
<p class="brand-text" style="text-align: justify; padding: 10px;">
Mazda Express Service dealers offer you
the convenience of completing
basic maintenance services while you wait.<br><br>
From arrival to re-delivery,
these maintenance services are completed in less than 
one hour at a competitive price.<br><br>
With convenient hours and a
"no-appointment required"
policy,you will receive the same
great service,only faster.<br><br>
Basic services include:Minor maintenance services, Lube, oil, filter, Tire Rotation, filter Replacements & wiper Blade Replacements.</p>
  <input type="submit" style="margin-left: 39%;"	onclick="return hideExpressMess();" class="Submit_btn" value="OK">
</div>