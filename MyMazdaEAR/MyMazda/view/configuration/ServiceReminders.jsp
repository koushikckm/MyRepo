<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="../common/Header.jsp" />
<jsp:include page="/view/common/DataLayerJs.jsp"></jsp:include>

<script type="text/javascript">
	$(document).ready(function() {
		dataLayer.page.name = 'service_reminders';
		dataLayer.page.URL = window.location.href;
		dataLayer.site.section  = 'mymazda';
		dataLayer.site.subsection = 'service';
		dataLayer.user.type = 'o';
	});
</script>

 <div class="container-fluid padding-0">
      <div class="overlay"></div>      
         <div class="ServiceReminder-Page">
            <div class="container">
               <div class="row margin-0">
                  <div class="col-sm-12">
                     <div class="search-field1">
                        <input type="search" class="form-control remindersearchkey" placeholder="Locate Nearest Dealers" id="remindDealerSearch" />
                        <i class="fa fa-map-marker" onclick="return remindersearchDealers();" style="cursor:pointer;"></i>
                     </div>                     
                  </div>
               </div>
               </div>
               <div class="Menu2">
          <div class="container">
              <nav class="navbar navbar-default sub-menu2">
                 <div class="container-fluid padding-0">
                    <div class="navbar-header">
                       <button type="button" class="navbar-toggle accordion-toggle collapsed vehicle-menu" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                       <i class="fa fa-plus-circle"></i>
                       </button>
                       <s:if test="#session.loginUser != null">
                            <s:iterator value="#session.selectedVehical" status="count" >
                                    <a href="" onclick="return homeClick();" class="summary" id="selectVehicle"><s:property value="carlineDesc"/></a>
                            </s:iterator>
                        </s:if>
                    </div>
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                       <ul class="nav navbar-nav">
                          <li>
	                          <s:if test="#session.loginUser != null">
	                            <s:iterator value="#session.selectedVehical" status="count" >
	                            	<a href="javaScript:;" onclick="return serviceHistory('<s:text name='#session.selectedVehical.vin'/>');" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="service_history"><span class="Menu2-no-divider"></span>SERVICE HISTORY</a>
	                            </s:iterator>
	                          </s:if>   
                          </li>  
                          <li><a href="http://www.mazdausa.com/MusaWeb/displayManualsByModelAndYearHome.action" target="_blank" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="manuals_and_references"><span class="Menu2-divider"></span>Manuals & References</a></li>
                          <li><a href="javaScript:;" target="_blank" onclick="return maintenanceSchedule();" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="maintenance_schedule"><span class="Menu2-divider"></span>MAINTENANCE SCHEDULE</a></li>
                          <li><a href="#" onclick="return videoDemosLogin();"><span class="Menu2-divider" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="vehicle_demos_guides"></span>Vehicle Demos & Guides</a></li>
                          <s:if test="#session.egiftTab == 0  && #session.egiftTabOnSelectVeh ==0 && #session.egiftTabOnRelPage == 0 && #session.egiftTabOnServRemChange == 0">
							<li><a href="javaScript:;" target="_blank" onclick="return eGift('<s:text name='#session.selectedVehical.vin'/>');"><span class="Menu2-divider"></span>MY eGIFTS</a></li>
						  </s:if>
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
               <div class="container submenu-class">
               <h1 class="text-uppercase">SERVICE REMINDERS</h1>
               <div class="nav-tab-div">
               <ul class="nav nav-tabs" id="myTab">
				<s:if test="#session.vehicalsList.size() ==1">
					<s:iterator value="#session.vehicalsList" status="count" begin="0"
						end="0">
						<li class="test-class" id="selector<s:property value="vin"/>"
							onclick="return showSelectedTab('<s:property value="vin"/>');">
							<a data-toggle="tab" href='#car<s:property value="vin"/>'
							cssClass="deco-none misc-class "> <s:property
									value="carlineDesc" /> <s:hidden name="id" /> <i class="fa  fa-plus-circle"  data-id ="1" id="<s:property value="vin"/>" onclick="return showSelectedTab('<s:property value="vin"/>');"></i>
						</a> <s:hidden name="id" cssClass="vehical" />
						</li>
					</s:iterator>					
				</s:if>
				<s:if test="#session.vehicalsList.size() ==2">
					<s:iterator value="#session.vehicalsList" status="count" begin="0"
						end="1">
						<li class="test-class" id="selector<s:property value="vin"/>"
							onclick="return showSelectedTab('<s:property value="vin"/>');">
							<a data-toggle="tab" href='#car<s:property value="vin"/>'
							cssClass="deco-none misc-class "> <s:property
									value="carlineDesc" /> <s:hidden name="id" /> <i id="<s:property value="vin"/>"
								class="fa  fa-plus-circle  "  data-id ="1"
								onclick="return showSelectedTab('<s:property value="vin"/>');"></i>
						</a> <s:hidden name="id" cssClass="vehical" />
						</li>
					</s:iterator>
				</s:if>
				<s:if test="#session.vehicalsList.size() ==3">
					<s:iterator value="#session.vehicalsList" status="count" begin="0"
						end="2">
						<li class="test-class" id="selector<s:property value="vin"/>"
							onclick="return showSelectedTab('<s:property value="vin"/>');">
							<a data-toggle="tab" href='#car<s:property value="vin"/>'
							cssClass="deco-none misc-class"> <s:property
									value="carlineDesc" /> <s:hidden name="id" /> <i id="<s:property value="vin"/>"
								class="fa  fa-plus-circle"  data-id ="1"
								onclick="return showSelectedTab('<s:property value="vin"/>');"></i>
						</a> <s:hidden name="id" cssClass="vehical" />
						</li>
					</s:iterator>					
				</s:if>
				<s:if test="#session.vehicalsList.size() >3">
					<s:iterator value="#session.vehicalsList" status="count" begin="0"
						end="2">
						<li class="test-class" id="selector<s:property value="vin"/>"
							onclick="return showSelectedTab('<s:property value="vin"/>');">
							<a data-toggle="tab" href='#car<s:property value="vin"/>'
							cssClass="deco-none misc-class"> <s:property
									value="carlineDesc" /> <s:hidden name="id" /> <i id="<s:property value="vin"/>"
								class="fa  fa-plus-circle"  data-id ="1"
								onclick="return showSelectedTab('<s:property value="vin"/>');"></i>
						</a> <s:hidden name="id" cssClass="vehical" />
						</li>
					</s:iterator>
					<li class="test-class dropdown tab-dropdown"><a
						data-toggle="dropdown" class="dropdown-toggle" href="#">More <b
							class="caret"></b></a>
						<ul class="dropdown-menu tab-dropdown-ul">
							<s:iterator value="#session.vehicalsList" status="count"
								begin="3">
								<li class="test-class inMore"
									id="selector<s:property value="vin"/>" data-id="inMore"
									onclick="return showSelectedTab('<s:property value="vin"/>');">
									<a data-toggle="tab" href='#car<s:property value="vin"/>'
									value="id" cssClass="deco-none misc-class"> <s:property
											value="carlineDesc" /> <s:hidden name="id" /> <i id="<s:property value="vin"/>"
										class="fa fa-plus-circle" data-id ="1"
										onclick="return showSelectedTab('<s:property value="vin"/>');"></i>
								</a> <s:hidden name="id" cssClass="vehical" />
								</li>
							</s:iterator>
						</ul></li>
				</s:if>
			</ul>
                  <div class="tab-content responsive-tab" >
                     <div class="tab-pane fade in active " id="car<s:property value="vin"/>">
                        <s:if test="hasActionMessages()">                          
                            <div class="servicetext" >
                                  <s:actionmessage />
                            </div>                                                  
                        </s:if>
					<s:else>
					<s:if test="serviceReminderList !=null && serviceReminderList.size()>0">
					<s:iterator value="serviceReminderList" status="incr">
					<div class="panel-group accordion-div" id="accordion">
					<div class="panel panel-default panel-div">
                              <div class="panel-heading">							  
                                 <h4 class="panel-title">
                                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" onclick="javascript:toggle(<s:property value="%{#incr.index}"/>);" id="#toggleText-<s:property value='%{#incr.index}'/>">
                                    <span id="couponHeader"><s:property value="couponHeader" /></span>
                                    <i id="img<s:property value="%{#incr.index}"/>" class="indicator fa fa-angle-down pull-right"></i></a>
                                 </h4>
                              </div>
                              <div id="toggleText-<s:property value="%{#incr.index}"/>"  style="display:none;" class="panel-collapse collapse in" >
                                 <div class="panel-body">
                                    <div class="print-icons"><a href="#" onclick="PrintElem('#servicereminderprint-<s:property value="%{#incr.index}"/>')"><i class="fa fa-print"></i></a><a href="#" onclick="return RemaindersendTO();" data-toggle="modal" data-target="#Mail"><i class="fa fa-envelope-o"></i></a></div>
                                     <div id="servicereminderprint-<s:property value="%{#incr.index}"/>">
                                    <div class="panel-text">
                                       <div class="row margin-0">
                                           <div class="col-sm-6 col-xs-6 padding-0" style="float:left;">
                                             <div class="logo-img" style="padding: 15px 0 15px 20px;"><img class="img-responsive logo-mobile-xs" src="view/vhelper/images/Mazda-Logo_Desktop.png" alt="" width="104"></div>
                                          </div>
                                          <div class="col-sm-6 col-xs-6 padding-0">
                                             <div class="logo-txt text-uppercase" style="float:right;padding: 15px 20px 15px 0;">
                                                VIN2:<s:text name="#session.selectedVehical.vin"/><br>
												<s:text name="#session.selectedVehical.dealerName"/><br>
												<s:text name="#session.selectedVehical.dealerAddress"/><br>
												<s:text name="#session.selectedVehical.dealerCity"/>
												<s:text name="#session.selectedVehical.dealerState"/>
												<s:text name="#session.selectedVehical.dealerZip"/><br>
												<s:text name="#session.selectedVehical.dealerPhone" />
                                             </div>
                                          </div>
                                       </div>
                                       <div style="clear:both"></div>
                                       <div class="red-strip" id="couponHeader" style="background-color:#666;color: #fff;padding: 10px 0 10px 20px;font-size:12px;">
                                       <s:property value="couponHeader" />									  
									   </div>
									   <%-- <span id="couponHeader" style="display:none;"><s:property value="couponHeader" /></span> --%>
                                      <div class="panelbody-text" style="padding: 15px 15px;">
                                          <div class="row margin-0">
                                            <div class="col-xs-12 col-sm-4 col-sm-push-8 padding-0">
                                                 <h1 id="couponValueId"><s:property value="specificationPrice"/></h1>
                                             </div>
                                             <div class="col-xs-12 col-sm-8 col-sm-pull-4 padding-0"> <ul>                                               
                                                <span id="couponDetailDivId">
                                                   <s:iterator value="textLine">
                                                  <li> <s:property/></li>
                                                   </s:iterator>
                                                   	<s:if test="descriptionsList.size() > 0">
																<s:iterator value="descriptionsList">
																<li>
																<s:text name="description" /></li>
                                                   </s:iterator>
                                                   </s:if>
                                                   </span>	
                                                </ul>
                                                
													
													<s:if test="serviceDate != null">
													<h4 class="text-uppercase">maintenance summary</h4>
													<span><p class="brand-text">Based on your most recent Full Circle Service inspection on <s:text name="serviceDate"></s:text> </span>
													</s:if>
													<ul class="m-summary" style="margin-left:0px !important;">
													<li><i class="fa fa-square"></i>&nbsp;&nbsp;<b>TIRES: </b><s:text name="titext"/>.</li>
													<li><i class="fa fa-square-o"></i>&nbsp;&nbsp;<b>BRAKES: </b><s:text name="brtext"/>.</li>
													</ul>
													
                                             </div> 
                                             <div class="col-xs-12 col-sm-12 padding-0"><span id="couponDiscalimerId"><s:text name="disclaimer" /></span></div>                                           
                                          </div>
                                       </div>
                                       </div>
                                    </div>
                                 </div>
                              </div>
                           </div>
								</div>	
						</s:iterator> 
						 </s:if>
					</s:else>
                     </div>
                  </div>
               </div>
            </div>
         </div>
         </div>
    
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
	
    $(function(){
		$('.vehicle-menu').click(function(){					    
			//$(this).next('ul').slideToggle('500');				
			$(this).find('i').toggleClass('fa-plus-circle fa-minus-circle');
			if ( $(window).width() < 768 ) {
				var i_class = $(this).find('i').attr("class");
				if(i_class == 'fa fa-minus-circle') {
					$(".submenu-class").addClass('submenu-expand');
				} else if(i_class == 'fa fa-plus-circle') {
					$(".submenu-class").removeClass('submenu-expand');
				}
			}
		});	
	});
    
   $('#myTab').tabCollapse();  
	 
       var oldpage=1;
  	jQuery(document).ready(function() {
  		selectedVehicleId = '${selectedVehical.vin}';
  		$('#selector' + selectedVehicleId).addClass("active");
  		$('#listSelector1').addClass("active");
  		$("#couponContainerId .couponClass").hide();
  		$("#coupon-0").show();
  		$("#couponLinkId a").css('color','#d00000');
  		
  		
  	});
  	
  		
  	 
    $('#myTab a').click(function(e) {
    				e.preventDefault();
    				$(this).tab('show');
    });
    $('#moreTabs a').click(function (e) {
    				e.preventDefault();
    				$(this).tab('show');
    });
    (function ($) {
    			// Test for making sure event are maintained
    			$('.js-alert-test').click(function () {
    							alert('Button Clicked: Event was maintained');
    			});
    			//fakewaffle.responsiveTabs(['xs', 'sm']);
    })(jQuery);
    $(document).ready(function() {
    	
   	 $(".remindersearchkey").keyup(function (e) {
   	    if (e.keyCode == 13) {
   	    	remindersearchDealers();
   			return false;
   	    } 
   	});
   	
   });
    function remindersearchDealers() {
 	   add='';
 	   edit='';
 	   registersearch='';
 		var data = {};
 		var dealerSearch = $("#remindDealerSearch").val();
 		if (dealerSearch.length === 0) {
 			$("#error-sucess").modal("show");
 			$("#errorMsg").html("This field cannot be blank. Please enter a valid zip code or name.");
 		} else {
 			if ($.isNumeric(dealerSearch)) {
 				data["zip"] = dealerSearch;
 			} else {
 				data["dealerSearch"] = dealerSearch;
 			}
 			$.ajax({
 				url : "locateDelear.action",
 				data : data,
 				type : "GET",
 				cache : false,
 				beforeSend : function() {
 					$("#loadingmessage").show();
 				},
 				complete : function() {
 					$("#loadingmessage").hide();
 				},
 				success : function(result) {
 					$("#container-fluid").html(result);
 					$("#result").show();
 					$("#submit").hide();
 					$("#backbutton").hide();
 					$(".radio").addClass("radioClass");
 					//$(".LocateDealer-radio").hide();
 				},
 				error : function(error) {
 					$("#error-sucess").modal("show");
 					$("#errorMsg").html("Error");

 				}
 			});
 		}
 		return false;
 	}
    function RemaindersendTO(){
		$("#submitButton").text("SEND REMINDER");
    	//window.open('mailto:test@example.com');
    	$("#Mail").show();
    	$("#cuponsendmail").html("");
    return false;
    }
    function cuponCancel(){
    	$('#emailid').val('');
    	return false;
    }
    function modalClose(){
    	$('#emailid').val('');
    	return false;
    }
 </script>
   
   