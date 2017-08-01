<%@ taglib prefix="s" uri="/struts-tags"%>

<jsp:include page="../common/Header.jsp" />
<jsp:include page="/view/common/DataLayerJs.jsp"></jsp:include>

<script type="text/javascript">
	$(document).ready(function() {
		dataLayer.page.name = 'service_offers';
		dataLayer.page.URL = window.location.href;
		dataLayer.site.section  = 'mymazda';
		dataLayer.site.subsection = 'service';
		dataLayer.user.type = 'o';
	});
</script>

<!DOCTYPE html>
<div class="container-fluid padding-0">
	
	<!--Container Start-->
	<div class="ServiceCoupons-Page">
		<div class="container">
			<div class="row margin-0">
			  <div class="col-xs-12 col-sm-12 col-md-12 Ser-head">
				<div class="search-field1">
					<input type="search" class="form-control serviceOffersearchkey"
						placeholder="Locate Nearest Dealer (Enter Zip Code)"  id="serviceofferSearchDealer"/> <i
						class="fa fa-map-marker" onclick="return offerssearchDealers();" style="cursor:pointer;"></i>
				</div>				
			   </div>
			</div>
			</div>
			<div class="Menu2" id="menu1">
		<div class="container">		  
			<nav class="navbar navbar-default sub-menu2">
				<div class="container-fluid padding-0">
					<div class="navbar-header">
						<button type="button"
							class="navbar-toggle accordion-toggle collapsed vehicle-menu"
							data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
							<i class="fa fa-plus-circle"></i>
						</button>
						<span><a href="" onclick="return homeClick();" class="summary" id="servicecarName"></a></span>
					</div>
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li><a href="javaScript:;" onclick="return serviceHistory('<s:text name='#session.selectedVehical.vin'/>');" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="service_history"><span class="Menu2-no-divider"></span>SERVICE HISTORY</a></li>
							<li><a href="http://www.mazdausa.com/MusaWeb/displayManualsByModelAndYearHome.action" target="_blank" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="manuals_and_references"><span class="Menu2-divider"></span>Manuals & References</a></li>
							<li><a href="javaScript:;" target="_blank" onclick="return maintenanceSchedule();" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="maintenance_schedule"><span class="Menu2-divider"></span>MAINTENANCE SCHEDULE</a></li>
							<li><a href="#" onclick="return videoDemosLogin();" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="vehicle_demos_guides"><span class="Menu2-divider"></span>Vehicle Demos & Guides</a></li>
							<s:if test="#session.egiftTab == 0  && #session.egiftTabOnSelectVeh ==0 && #session.egiftTabOnRelPage == 0 && #session.egiftTabOnServCoup == 0">
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
			<h1 class="text-uppercase">Service Coupons</h1>
			<div class="nav-tab-div">
				<ul class="nav nav-tabs" id="myTab">
					<s:if test="#session.vehicalsList.size() ==1">
						<s:iterator value="#session.vehicalsList" status="count" begin="0"
							end="0">
							<li class="test-class" id="selector<s:property value="vin"/>"
								onclick="return UpdateSelectedVehical('<s:property value="vin"/>');">
								<a data-toggle="tab" href='#car<s:property value="vin"/>'
								cssClass="deco-none misc-class "> <s:property
										value="carlineDesc" /> <s:hidden name="id" /> <i
									class="fa  fa-plus-circle" data-id="1"
									id="<s:property value="vin"/>"
									onclick="return UpdateSelectedVehical('<s:property value="vin"/>');"></i>
							</a> <s:hidden name="id" cssClass="vehical" />
							</li>
						</s:iterator>
					</s:if>
					<s:if test="#session.vehicalsList.size() ==2">
						<s:iterator value="#session.vehicalsList" status="count" begin="0"
							end="1">
							<li class="test-class" id="selector<s:property value="vin"/>"
								onclick="return UpdateSelectedVehical('<s:property value="vin"/>');">
								<a data-toggle="tab" href='#car<s:property value="vin"/>'
								cssClass="deco-none misc-class "> <s:property
										value="carlineDesc" /> <s:hidden name="id" /> <i
									id="<s:property value="vin"/>" class="fa  fa-plus-circle  "
									data-id="1"
									onclick="return UpdateSelectedVehical('<s:property value="vin"/>');"></i>
							</a> <s:hidden name="id" cssClass="vehical" />
							</li>
						</s:iterator>
					</s:if>
					<s:if test="#session.vehicalsList.size() ==3">
						<s:iterator value="#session.vehicalsList" status="count" begin="0"
							end="2">
							<li class="test-class" id="selector<s:property value="vin"/>"
								onclick="return UpdateSelectedVehical('<s:property value="vin"/>');">
								<a data-toggle="tab" href='#car<s:property value="vin"/>'
								cssClass="deco-none misc-class"> <s:property
										value="carlineDesc" /> <s:hidden name="id" /> <i
									id="<s:property value="vin"/>" class="fa  fa-plus-circle"
									data-id="1"
									onclick="return UpdateSelectedVehical('<s:property value="vin"/>');"></i>
							</a> <s:hidden name="id" cssClass="vehical" />
							</li>
						</s:iterator>
					</s:if>
					<s:if test="#session.vehicalsList.size() >3">
						<s:iterator value="#session.vehicalsList" status="count" begin="0"
							end="2">
							<li class="test-class" id="selector<s:property value="vin"/>"
								onclick="return UpdateSelectedVehical('<s:property value="vin"/>');">
								<a data-toggle="tab" href='#car<s:property value="vin"/>'
								cssClass="deco-none misc-class"> <s:property
										value="carlineDesc" /> <s:hidden name="id" /> <i
									id="<s:property value="vin"/>" class="fa  fa-plus-circle"
									data-id="1"
									onclick="return UpdateSelectedVehical('<s:property value="vin"/>');"></i>
							</a> <s:hidden name="id" cssClass="vehical" />
							</li>
						</s:iterator>
						<li class="test-class dropdown tab-dropdown"><a
							data-toggle="dropdown" class="dropdown-toggle" href="#">More
								<b class="caret"></b>
						</a>
							<ul class="dropdown-menu tab-dropdown-ul">
								<s:iterator value="#session.vehicalsList" status="count"
									begin="3">
									<li class="test-class inMore"
										id="selector<s:property value="vin"/>" data-id="inMore"
										onclick="return UpdateSelectedVehical('<s:property value="vin"/>');">
										<a data-toggle="tab" href='#car<s:property value="vin"/>'
										value="id" cssClass="deco-none misc-class"> <s:property
												value="carlineDesc" /> <s:hidden name="id" /> <i
											id="<s:property value="vin"/>" class="fa fa-plus-circle"
											data-id="1"
											onclick="return UpdateSelectedVehical('<s:property value="vin"/>');"></i>
									</a> <s:hidden name="id" cssClass="vehical" />
									</li>
								</s:iterator>
							</ul></li>
					</s:if>
				</ul>

				<div class="tab-content responsive responsive-tab">
					<div class="tab-pane active" id="car<s:property value="vin"/>">
						<s:if test="hasActionMessages()">
							<div class="servicetext">
								<s:actionmessage />
							</div>
						</s:if>
						<s:else>
							<s:if test="coupnsList !=null">
							 <s:set var="emptyrecord" value="0"/>
								<s:iterator value="coupnsList" status="incr" var="data" >
								<s:if test="%{#data.title != null && #data.title != ''}">
									<div class="panel-group accordion-div"
										id="accordion<s:property value='%{#incr.index}'/>">
										<div class="panel panel-default panel-div">
											<div class="panel-heading">
												<h4 class="panel-title">
													<a class="accordion-toggle" data-toggle="collapse"
														data-parent="#accordion"
														href="#toggleText-<s:property value='%{#incr.index}'/>"
														data-target="toggleText-<s:property value='%{#incr.index}'/>"
														onclick="javascript:toggle(<s:property value="%{#incr.index}"/>);" data-analytics-link-component-name="list" data-analytics-link-type="button" data-analytics-link-description="listarrow:<s:property value="title"/>">
														<span><s:property value="title" /></span> <arrow
														id="img<s:property value='%{#incr.index}'/>"
														class="indicator fa fa-angle-down pull-right" />
													</a>
												</h4>
											</div>
											<div id="toggleText-<s:property value='%{#incr.index}'/>"
												class="panel-collapse collapse in" style="display: none;">
												<div class="panel-body">
													<div class="print-icons">
														<a href="#"
															onclick="PrintElem1('#serviceoffersprint-<s:property value="%{#incr.index}"/>')" data-analytics-link-component-name="offer_details" data-analytics-link-type="button" data-analytics-link-description="print"><i
															class="fa fa-print"></i></a> <a href="#"  onclick="return sendTO();"data-toggle="modal"
															data-target="#Mail" data-analytics-link-component-name="offer_details" data-analytics-link-type="button" data-analytics-link-description="email"><i class="fa fa-envelope-o"></i></a>
													</div>
													<div class="panel-text">
														<div class="row margin-0">
															<div class="col-sm-6 col-xs-6 padding-0">
																<div class="logo-img">
																	<img class="img-responsive logo-mobile-xs"
																		src="view/vhelper/images/Mazda-Logo_Desktop.png"
																		alt="" width="104">
																</div>
															</div>
															<div class="col-sm-6 col-xs-6 padding-0">
																<div class="logo-txt text-uppercase">
																	<!-- vin:1yvhp81b595m20554<br>nelson manda of cool springs<br>7104 south springs drive<br>franklin tn 37067<br>(615) 250-2212 -->
																	vin:
																	<s:text name="#session.selectedVehical.vin" />
																	<br>
																	<s:text name="#session.selectedVehical.dealerName" />
																	<br>
																	<s:text name="#session.selectedVehical.dealerAddress" />
																	<br>
																	<s:text name="#session.selectedVehical.dealerCity" />
																	<s:text name="#session.selectedVehical.dealerState" />
																	<s:text name="#session.selectedVehical.dealerZip" />
																	<br>
																	<s:text name="#session.selectedVehical.dealerPhone" />
																</div>
															</div>
														</div>
														<div id="serviceoffersprint-<s:property value="%{#incr.index}"/>">	
														<div class="red-strip" style="background-color:#666;color: #fff;padding: 10px 0 10px 20px;font-size:12px;">
															<span id="couponTitleId"> <s:property
																	value="title" />
															</span>
														</div>													
														<div class="panelbody-text" style="padding: 15px 20px;">
														<div class="row margin-0">															  
															   <div class="col-xs-12 col-sm-4 col-sm-push-8 padding-0" id="couponValueId">
																	<h1>

																		<s:property value="deal" />
																	</h1>
																</div>
																<div class="col-xs-12 col-sm-8 col-sm-pull-4 padding-0">
																	<p id="couponDetailDivId">
																		<s:iterator
																				value="descriptionsList">
																				<s:text name="description" />
																			</s:iterator>																		
																	</p>
																	<ul>
																		<s:if test="servicesList.size() > 0">
																			<s:iterator value="servicesList">
																				<li><s:text name="service" /></li>
																			</s:iterator>
																		</s:if>
																	</ul>
																	
																</div>
																<div class="col-xs-12 col-sm-12 padding-0"><span id="couponDiscalimerId"> <s:text
																			name="disclaimer" />
																	</span></div>																
															</div>
														  </div>
														</div>
													</div>
												</div>
											</div>
										</div>

									</div>
									</s:if>
									 <s:else>
									 <s:if test="#emptyrecord < 1">
									<div class="servicetext">
									<ul>
									  <li><span>Apologies, the system is currently experiencing network issues, and we are unable to service your request. Please check back.</span></li>
									</ul>								
							</div>
						</s:if>
						<s:set var="emptyrecord" value="3"/>
									</s:else> 
								</s:iterator>
						</s:if>
						</s:else>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--Container End-->

<!-- Modal End-->
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
	
$(document).ready(function() {
	
  	 $(".serviceOffersearchkey").keyup(function (e) {
  	    if (e.keyCode == 13) {
  	    	offerssearchDealers();
  			return false;
  	    } 
  	});
  	
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
var oldId;
function UpdateSelectedVehical(vin){
	$(".js-tabcollapse-panel-body").hide();
	var car = $("#servicecarName").text();
	var changecss= $("#"+vin).attr("data-id");
	if(changecss==='1'){
	//alert("showUpdateSelectedVehicle.action-------vin:"+vin);
					$.ajax({
						type : "POST",
						url : "myServiceOfferVehicleUpdate.action",
						data : "vin ="+vin,
						beforeSend : function() {
							$("#loadingmessage").show();
						},
						success : function(result) {
							$("#container-fluid").html(result);
							$("#servicecarName").text(car);
							 $('#selector'+oldId).removeClass("active");
							$('#selector'+vin).addClass("active"); 
							 $("#"+vin).attr("data-id", "2");
							 $("#"+vin).addClass("fa-minus-circle"); 
							 $("#"+vin). removeClass("fa-plus-circle");
							/* $('#datetimepicker1').datetimepicker({
						        format: 'MM/DD/YYYY'
						    }); */
							if($("#selector"+vin).attr("data-id")){    								
								$('.tab-dropdown').addClass("active");	
							}
							oldId = vin;    
							
							$(this).find('i').toggleClass('fa-plus-circle fa-minus-circle');
						},complete : function() {
							$("#loadingmessage").hide();
						}
					});
			return false;
	}
	else{
		 $("#"+vin).attr("data-id", "1");
		 $("#"+vin).addClass("fa-plus-circle"); 
		 $("#"+vin). removeClass("fa-minus-circle");
	}
}
/* function UpdateSelectedVehical(vin){
	 var url = "myServiceOfferVehicleUpdate.action";
		$.ajax({
			type : "POST",
			url : url,
			data : {"vin":vin},
			beforeSend : function() {
				$("#loadingmessage").show();	
			},success : function(result) {				
				$("#container-fluid").html(result);
				$('#selector'+oldId).removeClass("active");
				$('#selector'+vin).addClass("active");
				$('#CarName'+oldId).removeClass("active");
				$('#CarName'+vin).addClass("active");				
				oldId = vin;				
			},complete : function() {
				$("#loadingmessage").hide();				
			}
	}); 	
} */

var oldpage = null;
var flag=0;
var previous = null;
function toggle(selectedLi) {//alert("yyyyy")
	$(".panel-collapse collapse in").hide();
	var ele = $("#toggleText-"+selectedLi);
	if(selectedLi == oldpage) {
		if(flag==0){
			flag=1;
    		ele.show();
    		$("#img"+selectedLi).removeClass("indicator fa fa-angle-down pull-right");
    		$("#img"+selectedLi).addClass("indicator fa fa-angle-up pull-right");
    		
		}else{//alert("*******");
			flag=0;
    		ele.hide();    		
    		$("#img"+selectedLi).removeClass("indicator fa fa-angle-up pull-right");
    		$("#img"+selectedLi).addClass("indicator fa fa-angle-down pull-right");
    		
		}
  	}else {
		ele.show();
		$("#img"+selectedLi).removeClass("indicator fa fa-angle-down pull-right");
		$("#img"+selectedLi).addClass("indicator fa fa-angle-up pull-right");	
		$("#toggleText-"+oldpage).hide();
		flag=1;
		if(previous!=null){
			$("#img"+previous).removeClass("indicator fa fa-angle-up pull-right");
    		$("#img"+previous).addClass("indicator fa fa-angle-down pull-right");
		}
	}
	
	oldpage = selectedLi;
	previous = selectedLi;
    }
    
function sendTO(){
	//window.open('mailto:test@example.com');
	$("#submitButton").text("SEND COUPON");
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
function sendCoupoun(){
	var discalimer = $.trim($("#toggleText-"+(oldpage)+" #couponDiscalimerId").text());
	var emailmessage = $.trim($("#toggleText-"+(oldpage)+" #couponDetailDivId").text());
	var couponTitle = $.trim($("#toggleText-"+(oldpage)+" #couponTitleId").text());
	var couponValue = $.trim($("#toggleText-"+(oldpage)+" #couponValueId").text());
	var email = $("#emailid").val();
	var atpos = email.indexOf("@");
	var dotpos = email.lastIndexOf(".");
		if(email == ""){
			$("#Mail").show();
			$("#cuponsendmail").show();
				$("#cuponsendmail").html("Please enter an email address");
				return false;
			}
			else if(atpos<1 || dotpos<atpos+2 || dotpos+2 >email.length || dotpos+2 ==email.length){
				
			$("#Mail").show();
			$("#cuponsendmail").show();
				$("#cuponsendmail").html("Please enter valid email address");
				return false;
			}else{
	$.ajax({
    	url:"sendCouponByEamil.action",
    	data : {"title":couponTitle,"disclaimer":discalimer,"email":email,"description":emailmessage,"deal":couponValue},
    	 beforeSend:function(){
    		 $("#loadingmessage").show();
		    },
		    complete:function(){
		     $("#loadingmessage").hide();	
		   	 $("#Mail").hide();		   	 
		    },
    		success:function(result){
				$("#success-msg").modal("show");
	 			 $("#successMsg").html(result);
	 			 $('.modal-backdrop').hide();
	 			 setTimeout(function() {
					 $("#success-msg").modal("hide");
					}, 1000);
	 			$('#emailid').val('');
			 	 }
			 	});
				
			}
			
	return false;
}

function PrintElem1(elem) {
    Popup($(elem).html());
    $(elem).show();
}

function Popup(data)    {
    var mywindow = window.open('', 'my div', 'height=600,width=700');
    mywindow.document.write('<html><head><title>My Mazda</title>');
    mywindow.document.write('<script src="//assets.adobedtm.com/0b1a9905a3bfe56ba99f4bcd3560048b3d9faf5f/satelliteLib-9dac536f1ca76627327a770a7af6d1ac98184c84-staging.js"><\/script>');
    mywindow.document.write('</head><body >');  
    mywindow.document.write("<div style='border:1px solid #d00; color: #535353;font-family:verdana;font-size: 14px;line-height:18px;'><div style='padding: 15px 0 15px 20px;float:left;width:250px;'><img class='img-responsive logo-mobile-xs' src='view/vhelper/images/Mazda-Logo_Desktop.png' alt='' width='104'></div><div style='float: right; padding: 15px 20px 15px 0;font-size:12px;'>vin:<s:text name='#session.selectedVehical.vin' /><br><s:text name='#session.selectedVehical.dealerName' /><br><s:text name='#session.selectedVehical.dealerAddress' /><br><s:text name='#session.selectedVehical.dealerCity' /><s:text name='#session.selectedVehical.dealerState' /><s:text name='#session.selectedVehical.dealerZip' /><br><s:text name='#session.selectedVehical.dealerPhone' /></div><div style='clear:both'></div>");
    mywindow.document.write(data); 
    mywindow.document.write("<div style='clear:both'></div></div>");
    mywindow.document.write('<script type="text/javascript">_satellite.pageBottom();<\/script>');   
    mywindow.document.write('</body></html>');
    mywindow.document.close(); // necessary for IE >= 10
    mywindow.focus(); // necessary for IE >= 10

    mywindow.print();
    mywindow.close();

    return false;
}
function offerssearchDealers() {
	add='';
	edit='';
	registersearch='';
	var data = {};
	
	var dealerSearch = $("#serviceofferSearchDealer").val();
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
</script>