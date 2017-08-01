<%@ taglib prefix="s" uri="/struts-tags"%>

<jsp:include page="../common/Header.jsp" />	
<jsp:include page="/view/common/DataLayerJs.jsp"></jsp:include>
<jsp:include page="MyMazdaJs.jsp"></jsp:include>
<jsp:include page="HowTOMazdaLoginJS.jsp"></jsp:include>
<jsp:include page="LookUpItemsJS.jsp"></jsp:include>
 
<script type="text/javascript">
	$(document).ready(function() {
    	var mdlcode = mdlYearcarlineCode.innerHTML;
    	var carlinecode = mdlcode.substring(10);
		dataLayer.page.name = 'vehicle_overview';
		dataLayer.page.subCategory = 'owners_mymazda_myvehicle';
		dataLayer.page.URL = window.location.href;
		dataLayer.site.sectionHistorical = 'vehicle';
		dataLayer.site.section = 'mymazda';
		dataLayer.site.subsection = 'vehicle';
		dataLayer.vehicle.vehicleID = carlinecode;
		dataLayer.vehicle.modelYear = mdlcode.charAt(0)+mdlcode.charAt(1)+mdlcode.charAt(2)+mdlcode.charAt(3);
		dataLayer.user.type = 'o';
	});
</script>

<div class="overlay"></div>
   <div class="PostLogin-Page">
	    <div class="container">
            <div class="row margin-0">
                <div class="col-xs-12 col-sm-12 col-md-12 padding-0">
                   <div class="search-field1">
                      <input type="search" class="form-control searchkey3" placeholder="Locate Nearest Dealer (Enter Zip Code)" id="postlogindealersearch" />
                      <i class="fa fa-map-marker" onclick="return mazdasearchDealers();" style="cursor:pointer;"></i>
                   </div>
                </div>
            </div>
        </div>
        
        <div class="Menu2">
          <div class="container">
              <nav class="navbar navbar-default sub-menu2">
                 <div class="container-fluid padding-0">
                    <div class="navbar-header">
                       <button type="button" class="navbar-toggle accordion-toggle collapsed vehicle-menu-home" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                       		<i class="fa fa-plus-circle" id="userimagetoggle"></i>
                       </button>
                       <s:if test="#session.loginUser != null">
                            <s:iterator value="#session.selectedVehical" status="count" >
                                <%-- <a href="#" class="summary" style="cursor: default;"><s:property value="mdlYear"/></a>&nbsp;&nbsp; --%>
                                <a href="#" class="summary" id="selectVehicle" style="cursor: default !important;"><s:property value="carlineDesc"/></a>
                            </s:iterator>
                        </s:if>
                    </div>
                    
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" style="text-align: right !important;">
                        <ul class="nav navbar-nav">
                          <li>
	                          <s:if test="#session.loginUser != null">
	                            <s:iterator value="#session.selectedVehical" status="count" >
	                            	<a href="javaScript:;" onclick="return serviceHistory('<s:text name='#session.selectedVehical.vin'/>');" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="service_history"><span class="Menu2-no-divider"></span>SERVICE HISTORY</a>
	                            </s:iterator>
	                          </s:if>   
                          </li>                       
                          <li><a href="http://www.mazdausa.com/MusaWeb/displayManualsByModelAndYearHome.action" target="_blank" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="manuals_and_references"><span class="Menu2-divider"></span>MANUALS & REFERENCES</a></li>
                          <li><a href="javaScript:;" target="_blank" onclick="return maintenanceSchedule();" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="maintenance_schedule"><span class="Menu2-divider"></span>MAINTENANCE SCHEDULE</a></li>
                          <li class="loggedin-veh-demo"><a class="dropdown-link" onclick="return videoDemosLogin();" href="#" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="vehicle_demos_guides"><span class="Menu2-divider"></span>VEHICLE DEMOS & GUIDES</a></li>
                          <s:if test="#session.egiftTab == 0  && #session.egiftTabOnSelectVeh ==0 && #session.egiftTabOnRelPage == 0">
						  		<li><a href="javaScript:;" target="_blank" onclick="return eGift('<s:text name='#session.selectedVehical.vin'/>');"><span class="Menu2-divider"></span>MY eGIFTS</a></li>
						  </s:if>
						  <!-- 
						  <div class="dropdown veh-demo-dots">
							  <button class="dropbtn">...</button>
							  <div class="dropdown-content dropdown-link-video">
							    <a class="dropdown-link" onclick="return videoDemosLogin();" href="#">VEHICLE DEMOS & GUIDES</a>
							  </div>
						  </div>
						  -->
						  
						  <!-- <div class="dropdown loggedin-myprofile">
							  <button class="dropbtn">MY PROFILE</button>
							  <div class="dropdown-content dropdown-links-myprofile">
							    <a href="javaScript:;" class="dropdown-link"
										onclick="return updateInformation();">EDIT ACCOUNT INFO</a>
							    <a href="javaScript:;" class="dropdown-link"
										onclick="return changePassword();">CHANGE PASSWORD</a>
								<a href="mymazdaHome.action" class="dropdown-link">LOGOUT</a>
							  </div>
						 </div> -->
						 
                       </ul>
                       
                    </div>
                 </div>
              </nav>
           </div>
        </div>
        
        <div class="PostLogin-Header submenu-class">
            <div class="container">
            
            	<!-- Message Container -->
            	<s:if test="#session.messageOnLogin!=null">
	            	<div class="PostLogin-Message-Block PostLogin-Message-Border">
		        		<s:property value="#session.messageOnLogin"/>
		        	</div>
	        	</s:if>
	        	<s:else>
	        		<div class="PostLogin-Message-Block"></div>
	        	</s:else>
	        	
                <div class="row margin-0">
                
                	<!-- Image Portion -->
                	<div class="col-xs-12 col-sm-7 padding-0">
                		<div class="user-image-div">
							<s:if test="#session.userUploadedImagePath!=null">
								<img class="user-uploaded-image" src="<s:property value="#session.userUploadedImagePath"/>" alt="Mazda">
    						</s:if>
    						<s:elseif test="#session.stockImagePath!=null">
    							<img class="user-uploaded-image" src="<s:property value="#session.stockImagePath"/>" alt="Mazda">
    						</s:elseif>
    						<s:else>
    							<img class="user-uploaded-image" src="view/vhelper/images/default-user-img.png"/>
    						</s:else>
                		</div>
                	</div>
                	
                	<div class="col-xs-12 col-sm-5 padding-0">
                		<div class="Car-Div">
						<div class="clearfix"></div>
	                         <div class="user-created">
                                <div class="row margin-0">
                                    <div class="col-xs-12 col-sm-10 padding-0" style="width: 100%;">
                                        <div class="Car-Heading">
                                         <s:if test="#session.loginUser != null">
					                        <s:iterator value="#session.selectedVehical" status="count" >
					                           
                                            <div class="row" id="vehicledetail">
                                                 <div class="col-xs-12 col-sm-12 carline-info-header">
                                                    <span class="carline-info-title" id="mdlYearcarlineCode"><s:property value="mdlYear"/>&nbsp;<s:property value="carlineCode"/></span><br/><br/>
                                                 </div>
                                                 <div class="col-xs-12 col-sm-12">
                                                    <p class="carline-info-text">Mileage: <s:property value="mileage" /> Miles</p><br>
					                                <p class="carline-info-text">VIN: <s:property value="vin" /></p><br>
					                                <p class="carline-info-text">Transmission: <s:property value="transmission" /></p><br>
					                                <p class="carline-info-text">Trim: <s:property value="trim" /></p><br>
					                                <p class="carline-info-text">Engine: <s:property value="engine"/></p><br>
					                                <p class="carline-info-text">Int / Ext Color: <s:property value="intColorDesc" /> / <s:property value="extColorDesc" /></p><br>
                                                 </div>
                                              </div>
                                              
                                              <table border="0" width="100%"><tr>
														<td width="100%" style="padding: 30px 0 30px 0;"><a href="#"
															onclick="return editVehicle('<s:property value="carlineDesc"/>','<s:property value="mileage"/>',' <s:property value="vin"/>','<s:property value="milesPerDay"/>','<s:property value="drivingCondition"/>')"
															class="btn btn-default btn-red brand-button-black" style="float: left" data-analytics-link-component-name="50_50_left" data-analytics-link-type="button" data-analytics-link-description="edit_vehicle_info">Edit Vehicle Info</a></td>
														<!-- Testing -->
                                            			<%-- <td width="20%"><a href="#" onclick="return deleteVehicle('<s:property value="vin"/>','<s:property value="carlineDesc"/>')" 
                                            			class="btn btn-default btn-red text-uppercase evi-btn brand-button-white">Delete Vehicle</a></td> --%>
                                              </tr></table>
                                            </s:iterator>
                                          </s:if>
                                            
                                        </div>
                                      </div> 
                                   </div>
                             	</div>
                         </div>
                	</div>
                	
                   <div class="col-xs-12 col-sm-7 padding-0">
                   	  <div class="Psd-Div">
                         <div class="Service-Dealer">
                            <span class="carline-info-header">Your preferred service dealer</span><br/><br/>
                            <div id="dealerinfo" class="Dealer-info">
		                           <s:iterator value="#session.selectedVehical" status="count" var="dealer" >
		                          <s:if test="%{#dealer.dealerName != null && #dealer.dealerName != ''}">
								<span class="carline-info-dealer" id="dealername"><s:property value="dealerName" /></span>
								<!-- <a href="#" onclick="return changesearchDealer();">(change)</a></h4> -->
								
                              <p class="carline-info-text"><s:property value="dealerAddress" /><br /><s:property value="dealerCity" />, <s:property value="dealerState" /> <s:property value="dealerZip" /><br>
                              	<span class="carline-info-text">Phone:</span>&nbsp;<span class="carline-info-text" id="dealerphone"> <s:property value="dealerPhone" /></span></p>
		                           </s:if>
		                           <s:else>
		                           <br>
		                           <!-- <a href="javaScript:;"  onclick="changesearchDealer();" style="color:#fff;text-decoration:underline;">Add your preferred service dealer</a> -->
		                            <span class="carline-info-text">Note: To Add/Change your preferred dealer, please click on 'EDIT VEHICLE INFO'.</span>
		                           </s:else>
		                           </s:iterator>
		                          <s:if test="%{#dealer.dealerName != null && #dealer.dealerName != ''}">
		                           <br/><button class="brand-button-black" type="submit" onclick="return maintenanceScheduleService();" data-analytics-link-component-name="divider_block" data-analytics-link-type="cta" data-analytics-link-description="schedule_service_appt">schedule service appointment</button><br/><br/>
		                         </s:if>
		                           </div>
		                            <span id="servicelink" style="display:none;"><s:property value="#session.selectedVehical.serviceUrl"/></span>
		                           <div id="userloginmail" value="<s:property value="#session.CheckboxRegistrationTO.email"/>" style="display:none"></div>
		                           <div  id="userloginpass" value="<s:property value="#session.CheckboxRegistrationTO.password"/>" style="display:none"></div>
		                            <div id="dealerinfo1" style="display:none" class="Dealer-info">
		                          <h4 class="text-uppercase" ></h4>
		                          <span id="header1"></span>
								<!-- <a href="#" onclick="return changesearchDealer();">(change)</a></h4> -->
								 <p class="carline-info-text"><span id="address"></span><br /><span id="city"></span>,<span id="state"></span>,<span id="zip"></span><br><a href="#" class="carline-info-text">Phone: <span id="phone"></span></a></p>
		                        <div class="clearfix"></div> 
		                       <%--  <s:if test="%{#dealer.dealerName != null && #dealer.dealerName != ''}">  
		                         <button class="btn btn-default btn-red brand-button-white text-uppercase" type="submit" onclick="return maintenanceSchedule();">schedule service appointment</button>
		                         </s:if> --%>
		                        
		                         <button class="btn btn-default btn-red brand-button-white text-uppercas" type="submit" onclick="return maintenanceScheduleService();">schedule service appointment</button>
		                          </div>
                         		<div class="clearfix"></div> 
                         		
                            
                           
                            <div class="clearfix"></div>
                         </div>
                      </div>
                   </div> 
                                     
                   <div class="col-xs-12 col-sm-5 padding-0">
                   		<div class="Service-Coupons">
                            <span class="carline-info-header">service coupons</span><br/><br/>
                            <p class="carline-info-text">Print service coupons from your preferred Mazda Dealer</p><br/>
                            <a href="javaScript:;" onclick="return myServiceOffer('<s:text name='#session.selectedVehical.vin'/>');" data-analytics-link-component-name="divider_block" data-analytics-link-type="button" data-analytics-link-description="service_coupons_view_now">
    	                      		<span class="brand-underline-blackbg"><span class="brand-link-blackbg">View Now</span></span></a>
                        </div>
                   </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        
        <div class="PostLogin-Links">
            <div class="container">
                <div class="row Ser-Div margin-0">
    	            <div class="col-xs-12 col-sm-4 padding-l">
    	                <div class="Ser-box">
    	                   <span class="brand-header">service reminders</span>
						   <div class="brand-bmargin20"></div>
    	                   <p class="brand-text">Next Recommended Service<br/><span id="RecommendedService" class="brand-text"></span> Mile Service
    	                   	  <div class="brand-bmargin30"></div>
    	                      <a href="javaScript:;" onclick="return maintenanceSchedule();" data-analytics-link-component-name="divider_block" data-analytics-link-type="button" data-analytics-link-description="view_full_maintenance_schedule">
    	                      		<span class="brand-underline link-height"><span class="brand-link">View Full Maintenance Schedule</span></span></a><br/><br/><br/>
    	                      <a href="javaScript:;" onclick="return serviceReminder('<s:text name='#session.selectedVehical.vin'/>');" data-analytics-link-component-name="divider_block" data-analytics-link-type="button" data-analytics-link-description="view_service_reminders">
    	                      		<span class="brand-underline"><span class="brand-link">View Service Reminders</span></span></a><br/>
    	                      
    	                   </p>
    	                </div>
    	            </div>
    	            <div class="col-xs-12 col-sm-4 padding-l">
    	                <div class="Ser-box external-links quick-links">
    	                   <span class="brand-header">quick links</span>
    	                   <div class="brand-bmargin20"></div>
    	                   <p class="brand-text">
    	                   	  <a class="MobileError" href="http://www.mazdausa.com/MusaWeb/displayManualsByModelAndYearHome.action" target="__blank" data-analytics-link-component-name="divider_block" data-analytics-link-type="button" data-analytics-link-description="owners_manual">
    	                   	  		<span class="brand-underline"><span class="brand-link">Owner's Manual</span></span></a><br/><br/><br/>
    	                      <a class="MobileError" href="http://www.mazdausa.com/MusaWeb/displayManualsByModelAndYearHome.action" target="__blank" data-analytics-link-component-name="divider_block" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide">
    	                      		<span class="brand-underline"><span class="brand-link">Smart Start Guide</span></span></a><br/><br/><br/>
    	                      <a class="MobileError" href="http://www.mazdausa.com/MusaWeb/displayPage.action?pageParameter=ownersParts" target="__blank" data-analytics-link-component-name="divider_block" data-analytics-link-type="button" data-analytics-link-description="service_and_parts">
    	                      		<span class="brand-underline"><span class="brand-link">Service And Parts</span></span></a><br/>
    	                   </p>
    	                </div>
    	            </div>
    	            <div class="col-xs-12 col-sm-4 padding-0">
    	                <div class="Ser-box recall-info">
    	                   <span class="brand-header">recall information</span>
    	                   <div class="brand-bmargin20"></div>
    	                   <p class="brand-text">Search for pending Recalls or Special Service Programs by vehicle VIN
    	                     <div class="brand-bmargin30"></div>
                             <a href="javaScript:;"  onclick="return recall();" target="_blank" class="text-capitalize" data-analytics-link-component-name="divider_block" data-analytics-link-type="button" data-analytics-link-description="search_for_recalls">
                             		<span class="brand-underline"><span class="brand-link">Search For Recalls</span></span></a><br/>
    	                   </p>
    	                </div>
    	            </div>
    	        </div>
            </div>
        </div>
        <div class="Vehicle-Videos">
             <div class="container padding-0">
                <div class="row margin-0">
                   <div class="col-sm-4 row-padding">
                      <div class="row">
                         <div class="col-xs-12 col-sm-12 Video-Box" style="padding: 0 !important;">
                            <a class="MobileError" href="#" onclick="return videoDemos();" target="_blank" data-analytics-link-component-name="top_block" data-analytics-link-type="image" data-analytics-link-description="vehicle_demos_guides"><img class="img-responsive" src="view/vhelper/images/videosdemos.png" alt="Mazda"></a>
                         </div>
                         <div class="col-xs-12 col-sm-12 Video-Txt" style="padding: 30px 30px 50px 30px !important; text-align: center;">
                            <a class="MobileError" href="#" onclick="return videoDemos();" target="_blank" data-analytics-link-component-name="copy" data-analytics-link-type="button" data-analytics-link-description="vehicle_demos_guides"><span class="brand-header">Vehicle Demos & Guides</span></a>                            
                         </div>
                      </div>
                   </div>
                   <div class="col-sm-4 row-padding">
                      <div class="row">
                         <div class="col-xs-12 col-sm-12 Video-Box" style="padding: 0 !important;">
                            <a href="http://insidemazda.mazdausa.com/" target="_blank" data-analytics-link-component-name="top_block" data-analytics-link-type="image" data-analytics-link-description="inside_mazda"><img class="img-responsive" src="view/vhelper/images/insidemazda.png" alt="Mazda"></a>
                         </div>
                         <div class="col-xs-12 col-sm-12 Video-Txt" style="padding: 30px 30px 50px 30px !important; text-align: center;">
                            <a href="http://insidemazda.mazdausa.com/" target="_blank" data-analytics-link-component-name="copy" data-analytics-link-type="button" data-analytics-link-description="inside_mazda"><span class="brand-header">inside mazda</span></a>                            
                         </div>
                      </div>
                   </div>
                   <div class="col-sm-4 row-padding">
                      <div class="row">
                         <div class="col-xs-12 col-sm-12 Video-Box" style="padding: 0 !important;">
                            <a href="http://www.mazdausa.com/MusaWeb/displayPage.action?pageParameter=ownersParts" target="_blank" data-analytics-link-component-name="top_block" data-analytics-link-type="image" data-analytics-link-description="service_and_parts"><img class="img-responsive" src="view/vhelper/images/serviceparts4.png" alt="Mazda"></a>
                         </div>
                         <div class="col-xs-12 col-sm-12 Video-Txt" style="padding: 30px 30px 50px 30px !important; text-align: center;">
                            <a href="http://www.mazdausa.com/MusaWeb/displayPage.action?pageParameter=ownersParts" target="_blank" data-analytics-link-component-name="copy" data-analytics-link-type="button" data-analytics-link-description="service_and_parts"><span class="brand-header">service & parts</span></a>                            
                         </div>
                      </div>
                   </div>                   
                </div>
             </div>
        </div>
        <div class="More-Mazda margin-0 text-center">
            <!--moremazda section start-->
            <div class="container more-mazda-div">
               <div class="row margin-0">
                  <div class="col-xs-12 col-sm-4 col-md-4 padding-0">
                     <div style="margin-top: 30px"><img src="view/vhelper/images/myservice_offers_icon.png" class="img-responsive" alt=""></div>
                     <div style="margin-top: 35px"><span class="brand-header">SERVICE COUPONS</span></div>
                     <div style="margin: 40px 10px 30px 10px;"><p class="brand-text">Download service and maintenance coupons.</p></div>
                  </div>
                  <div class="col-xs-12 col-sm-4 col-md-4 padding-0">
                     <div style="margin-top: 40px"><img src="view/vhelper/images/mazda_capital_icon.png" class="img-responsive" alt=""></div>
                     <div style="margin-top: 36px"><span class="brand-header">MAZDA CAPITAL SERVICES</span></div>
                     <div style="margin: 40px 10px 30px 10px;"><p class="brand-text">Learn more about auto financing and leasing options.</p></div>
                  </div>
                  <div class="col-xs-12 col-sm-4 col-md-4 padding-0">
                     <div style="margin-top: 30px"><img src="view/vhelper/images/schedule_service_icon.png" class="img-responsive" alt=""></div>
                     <div style="margin-top: 33px"><span class="brand-header">SCHEDULE SERVICE APPOINTMENT</span></div>
                     <div style="margin: 40px 10px 30px 10px;"><p class="brand-text">Find your nearest service dealer to schedule an appointment today.</p></div>
                  </div>
               </div>
            </div>
          </div>
   </div>

   <script type="text/javascript">
   /*(function()
	{
 		if( window.localStorage )
  			{
    			if( !localStorage.getItem( 'firstLoad' ) )
   	 				{
      					localStorage[ 'firstLoad' ] = true;
     					var url="#";
     					location.reload();
    				}  
   					else
     				localStorage.removeItem( 'firstLoad' );
  			}
	})();*/
	
   var isMobile = /iPhone|BlackBerry|IEMobile|Windows Phone|Android/i.test(navigator.userAgent);
   if(isMobile) {    	
	   $(".external-links .M-financing").attr("href", "${pageContext.request.contextPath}/view/common/MobileSupport.jsp");
		$(".external-links .M-nextmazda").attr("href", "${pageContext.request.contextPath}/view/common/MobileSupport.jsp");
		$("a.MobileError").each(
			function(index)
			{
				$(this).attr("href", "${pageContext.request.contextPath}/view/common/MobileSupport.jsp");
			}
		);
	} 
   
   /* if ( $(window).width() <= 767 ) {	   
	   $(".external-links a").attr("href", "${pageContext.request.contextPath}/view/common/MobileSupport.jsp");
	} */

   var intervalDef;
   var mileage;
$(document).ready(function(){
			
			if('${selectedVehical.drivingCondition}'!="" && '${selectedVehical.drivingCondition}'!=null){
			scheduleType = '${selectedVehical.drivingCondition}';
			 mileage= $("#mileagee").text();
			
			}else{
				scheduleType = "S";
			}
			
var recommendeser;
if(scheduleType == "H"){
	intervalDef=5000;
	
	var remainder =mileage%intervalDef;
	recommendeser=(parseInt(mileage-remainder, 10)+parseInt(intervalDef, 10));
	recommendeser=recommendeser.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
	$("#RecommendedService").html(recommendeser);
}else{
		intervalDef=7500;
		
		var remainder =mileage%intervalDef;
		recommendeser=(parseInt(mileage-remainder, 10)+parseInt(intervalDef, 10));
		recommendeser=recommendeser.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
		$("#RecommendedService").html(recommendeser);
}

  		return false;
	});
	</script>
	
