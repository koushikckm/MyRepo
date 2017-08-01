<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<div class="overlay"></div>
<!--Container Start-->
<div class="EgiftDetails-Page" id="EgiftDetails-Page">
	
	<div class="Menu2" id="menu1">
		<div class="container">
		<nav class="navbar navbar-default sub-menu2">
			<div class="container-fluid padding-0">
				<div class="navbar-header">
				<button type="button" class="navbar-toggle accordion-toggle collapsed vehicle-menu" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				<i class="fa fa-plus-circle"></i>
				</button>
				<span><a href="" onclick="return homeClick();" class="summary" id="servicecarNameDisp"></a></span>
				</div>
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
				<li><a href="javaScript:;" onclick="return serviceHistory('<s:text name='#session.selectedVehical.vin'/>');"><span class="Menu2-no-divider"></span>SERVICE HISTORY</a></li>
				<li><a href="http://www.mazdausa.com/MusaWeb/displayManualsByModelAndYearHome.action" target="_blank"><span class="Menu2-divider"></span>Manuals & References</a></li>
	 			<li><a href="javaScript:;" target="_blank" onclick="return maintenanceSchedule();"><span class="Menu2-divider"></span>MAINTENANCE SCHEDULE</a></li>
				<li><a  onclick="return videoDemosLogin();" href="#"><span class="Menu2-divider"></span>Vehicle Demos & Guides</a></li>
				<s:if test="#session.egiftTab == 0  && #session.egiftTabOnSelectVeh ==0 && #session.egiftTabOnRelPage == 0">
					<li><a href="javaScript:;" target="_blank" onclick="return eGift('<s:text name='#session.selectedVehical.vin'/>');" class="Menu2-active"><span class="Menu2-divider"></span>MY eGIFTS</a></li>
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
		<h1>MY eGIFTS</h1>
		<div class="nav-tab-div">	
		
			<ul class="nav nav-tabs" id="myTab">
				<%-- <s:if test="#session.vehicalsList.size() ==1">
					<s:iterator value="#session.vehicalsList" status="count" begin="0"
						end="0">
						<li class="test-class" id="selector<s:property value="vin"/>"
							onclick="return showUpdateSelectedVehicleEgift('<s:property value="vin"/>');">
							<a data-toggle="tab" href='#car<s:property value="vin"/>'
							cssClass="deco-none misc-class "> <s:property
									value="carlineDesc" /> <s:hidden name="id" /> <i class="fa  fa-plus-circle"  data-id ="1" id="<s:property value="vin"/>" onclick="return showUpdateSelectedVehicleEgift('<s:property value="vin"/>');"></i>
						</a> <s:hidden name="id" cssClass="vehical" />
						</li>
					</s:iterator>					
				</s:if>
				<s:if test="#session.vehicalsList.size() ==2">
					<s:iterator value="#session.vehicalsList" status="count" begin="0"
						end="1">
						<li class="test-class" id="selector<s:property value="vin"/>"
							onclick="return showUpdateSelectedVehicleEgift('<s:property value="vin"/>');">
							<a data-toggle="tab" href='#car<s:property value="vin"/>'
							cssClass="deco-none misc-class "> <s:property
									value="carlineDesc" /> <s:hidden name="id" /> <i id="<s:property value="vin"/>"
								class="fa  fa-plus-circle  "  data-id ="1"
								onclick="return showUpdateSelectedVehicleEgift('<s:property value="vin"/>');"></i>
						</a> <s:hidden name="id" cssClass="vehical" />
						</li>
					</s:iterator>
				</s:if>
				<s:if test="#session.vehicalsList.size() ==3">
					<s:iterator value="#session.vehicalsList" status="count" begin="0"
						end="2">
						<li class="test-class" id="selector<s:property value="vin"/>"
							onclick="return showUpdateSelectedVehicleEgift('<s:property value="vin"/>');">
							<a data-toggle="tab" href='#car<s:property value="vin"/>'
							cssClass="deco-none misc-class"> <s:property
									value="carlineDesc" /> <s:hidden name="id" /> <i id="<s:property value="vin"/>"
								class="fa  fa-plus-circle"  data-id ="1"
								onclick="return showUpdateSelectedVehicleEgift('<s:property value="vin"/>');"></i>
						</a> <s:hidden name="id" cssClass="vehical" />
						</li>
					</s:iterator>					
				</s:if>
				<s:if test="#session.vehicalsList.size() >3">
					<s:iterator value="#session.vehicalsList" status="count" begin="0"
						end="2">
						<li class="test-class" id="selector<s:property value="vin"/>"
							onclick="return showUpdateSelectedVehicleEgift('<s:property value="vin"/>');">
							<a data-toggle="tab" href='#car<s:property value="vin"/>'
							cssClass="deco-none misc-class"> <s:property
									value="carlineDesc" /> <s:hidden name="id" /> <i id="<s:property value="vin"/>"
								class="fa  fa-plus-circle"  data-id ="1"
								onclick="return showUpdateSelectedVehicleEgift('<s:property value="vin"/>');"></i>
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
									onclick="return showUpdateSelectedVehicleEgift('<s:property value="vin"/>');">
									<a data-toggle="tab" href='#car<s:property value="vin"/>'
									value="id" cssClass="deco-none misc-class"> <s:property
											value="carlineDesc" /> <s:hidden name="id" /> <i id="<s:property value="vin"/>"
										class="fa fa-plus-circle" data-id ="1"
										onclick="return showUpdateSelectedVehicleEgift('<s:property value="vin"/>');"></i>
								</a> <s:hidden name="id" cssClass="vehical" />
								</li>
							</s:iterator>
						</ul></li>
				</s:if> --%>
				<s:if test="newVehicalsList.size() ==1">
					<s:iterator value="newVehicalsList" status="count" begin="0"
						end="0">
						<li class="test-class" id="selector<s:property value="vin"/>"
							onclick="return showUpdateSelectedVehicleEgift('<s:property value="vin"/>');">
							<a data-toggle="tab" href='#car<s:property value="vin"/>'
							cssClass="deco-none misc-class "> <s:property
									value="carlineDesc" /> <s:hidden name="id" /> <i class="fa  fa-plus-circle"  data-id ="1" id="<s:property value="vin"/>" onclick="return showUpdateSelectedVehicleEgift('<s:property value="vin"/>');"></i>
						</a> <s:hidden name="id" cssClass="vehical" />
						</li>
					</s:iterator>					
				</s:if>
				<s:if test="newVehicalsList.size() ==2">
					<s:iterator value="newVehicalsList" status="count" begin="0"
						end="1">
						<li class="test-class" id="selector<s:property value="vin"/>"
							onclick="return showUpdateSelectedVehicleEgift('<s:property value="vin"/>');">
							<a data-toggle="tab" href='#car<s:property value="vin"/>'
							cssClass="deco-none misc-class "> <s:property
									value="carlineDesc" /> <s:hidden name="id" /> <i id="<s:property value="vin"/>"
								class="fa  fa-plus-circle  "  data-id ="1"
								onclick="return showUpdateSelectedVehicleEgift('<s:property value="vin"/>');"></i>
						</a> <s:hidden name="id" cssClass="vehical" />
						</li>
					</s:iterator>
				</s:if>
				<s:if test="newVehicalsList.size() ==3">
					<s:iterator value="newVehicalsList" status="count" begin="0"
						end="2">
						<li class="test-class" id="selector<s:property value="vin"/>"
							onclick="return showUpdateSelectedVehicleEgift('<s:property value="vin"/>');">
							<a data-toggle="tab" href='#car<s:property value="vin"/>'
							cssClass="deco-none misc-class"> <s:property
									value="carlineDesc" /> <s:hidden name="id" /> <i id="<s:property value="vin"/>"
								class="fa  fa-plus-circle"  data-id ="1"
								onclick="return showUpdateSelectedVehicleEgift('<s:property value="vin"/>');"></i>
						</a> <s:hidden name="id" cssClass="vehical" />
						</li>
					</s:iterator>					
				</s:if>
				<s:if test="newVehicalsList.size() >3">
					<s:iterator value="newVehicalsList" status="count" begin="0"
						end="2">
						<li class="test-class" id="selector<s:property value="vin"/>"
							onclick="return showUpdateSelectedVehicleEgift('<s:property value="vin"/>');">
							<a data-toggle="tab" href='#car<s:property value="vin"/>'
							cssClass="deco-none misc-class"> <s:property
									value="carlineDesc" /> <s:hidden name="id" /> <i id="<s:property value="vin"/>"
								class="fa  fa-plus-circle"  data-id ="1"
								onclick="return showUpdateSelectedVehicleEgift('<s:property value="vin"/>');"></i>
						</a> <s:hidden name="id" cssClass="vehical" />
						</li>
					</s:iterator>
					<li class="test-class dropdown tab-dropdown"><a
						data-toggle="dropdown" class="dropdown-toggle" href="#">More <b
							class="caret"></b></a>
						<ul class="dropdown-menu tab-dropdown-ul">
							<s:iterator value="newVehicalsList" status="count"
								begin="3">
								<li class="test-class inMore"
									id="selector<s:property value="vin"/>" data-id="inMore"
									onclick="return showUpdateSelectedVehicleEgift('<s:property value="vin"/>');">
									<a data-toggle="tab" href='#car<s:property value="vin"/>'
									value="id" cssClass="deco-none misc-class"> <s:property
											value="carlineDesc" /> <s:hidden name="id" /> <i id="<s:property value="vin"/>"
										class="fa fa-plus-circle" data-id ="1"
										onclick="return showUpdateSelectedVehicleEgift('<s:property value="vin"/>');"></i>
								</a> <s:hidden name="id" cssClass="vehical" />
								</li>
							</s:iterator>
						</ul></li>
				</s:if>
			</ul>
						
			<div class="tab-content responsive-tab" >
				<s:iterator value="#session.selectedVehical" status="count" >
					<div class="tab-pane fade in active" id="car<s:property value="vin"/>" >
						<div class="EgiftDetails-Div">
					
							<div class="row margin-0 SH-hedding text-uppercase">
								<div class="col-xs-12 col-sm-3 padding-0">
									<P>VIN: <s:text name="#session.selectedVehical.vin" /></P>
								</div>
								<div class="col-xs-12 col-sm-3 padding-0">
									<P>YEAR: <s:text name="#session.selectedVehical.mdlYear" /></P>
								</div>
								<div class="col-xs-12 col-sm-3 padding-0">
									<P>MODEL NAME: <s:text name="#session.selectedVehical.carlineCode" /></P>
								</div>
							</div>
					
					
						<div class="row">
									
						<div class="col-md-8">
						 <!-- Content Starts -->
						 <s:if test="hasActionMessages()">
							<div class="col-xs-12 col-sm-12 padding-0 servicetext" >
								<s:actionmessage  />
							</div>
						</s:if>
						<s:else>
						<s:iterator value="egiftList" status="stat" var="myobj">
							<div class="clearfix"></div>
							<div class="EgiftDetails-text"
									id="egift-<s:property value="%{#stat.index}"/>"> 
							<div class="row margin-0 sh-text">
								
							<div class="form-group row padding-0">
    							<label class="col-md-5 control-label">CERTIFICATE #<span id="certificateNo<s:text name="musaRefId"/>"> : <s:text name="certificateNo" />
										</span>
								</label>
    							<div class="col-md-8">
      								&nbsp;
    							</div>
 							</div>
  
  							<div class="form-group row padding-0">
    						<label class="col-md-4 control-label">AMOUNT
    							<span id="amount<s:text name="musaRefId"/>"> <s:text name="amount" /></span>
							</label>
							<s:if test="#myobj.egiftStatus == 'ISSUED'">
   						 		<div class="col-md-3 col-md-offset-5">
     							<span style="color:green !important;" id="egiftStatus<s:text name="musaRefId"/>"> <s:text name="egiftStatus" />
								</span> 
  								</div>
  							</s:if>
  							<s:else>
  								<div class="col-md-3 col-md-offset-5">
     							<span style="color:grey !important;" id="egiftStatus<s:text name="musaRefId"/>"> <s:text name="egiftStatus" />
								</span>  
  								</div>
  							</s:else>
  							</div>
  							
  							<div class="row">
  							<div class="col-xs-4 col-sm-4 0 padding-0">
								<p class="egift-label">ISSUED DATE</p>
							</div>
							<div class="col-xs-7 col-sm-8 padding-0">
								<span id="issueDate<s:text name="musaRefId"/>"> : &nbsp;<s:text name="issueDate" />
								</span>
							</div>
							</div>
								
							<div class="row">					
							<div class="col-xs-4 col-sm-4 padding-0">
								<p class="egift-label">REDEMPTION AMOUNT</p>
							</div>
							<div class="col-xs-7 col-sm-8 padding-0">
								<span id="redemptionAmount<s:text name="musaRefId"/>"> : &nbsp;<s:text name="redemptionAmount" />
								</span>
							</div>
							</div>	
							
							<div class="row">
							<div class="col-xs-4 col-sm-4 padding-0">
								<p class="egift-label">REDEMPTION DEALER</p>
							</div>
							<div style="max-width:10px;" class="col-xs-4 col-sm-0 padding-0">
								<span>:</span>
							</div>
							<s:if test="#myobj.egiftStatus == 'REDEEMED'">
								<div class="col-xs-1 col-sm-4 padding-0">
									<span id="dealerName<s:text name="musaRefId"/>">  <s:text name="dealerName" />
									</span>
								</div>
							</s:if>
							</div>	
								
							<div class="row">										
							<div class="col-xs-4 col-sm-4  padding-0">
								<p class="egift-label">REDEMPTION DATE</p>
							</div>
							<div class="col-xs-7 col-sm-8 padding-0">
								<span id="redemptionDate<s:text name="musaRefId"/>"> : &nbsp;<s:text name="redemptionDate" />
								</span>
							</div>
							</div>
								
							<div class="row">		
							<div class="col-xs-4 col-sm-4 padding-0">
								<p class="egift-label">EXPIRATION DATE</p>
							</div>
							<div class="col-xs-7 col-sm-8 padding-0">
								<span id="expirationDate<s:text name="musaRefId"/>"> : &nbsp;<s:text name="expirationDate" />
								</span>
							</div>
							</div>
									
							</div>
  							</div>
  							<!-- <HR SIZE="2"> -->
						</s:iterator>
						</s:else>
						 <!-- Content Ends -->
						</div>
						
						<div class="col-md-4 egift-info">
						<!-- <div class="EgiftDetails-Text"> -->
						<h5>MAZDA CUSTOMER EGIFT</h5>
						<br/>
						<p class="brand-text">The eGift is redeemable at any authorized 
						U.S. Mazda Dealership toward Genuine Mazda Parts,Accessories or Service.
						eGifts are non-transferrable and valid only for a single visit;
						eGift cannot be redeemed for cash.</p>
						<br/>
						<p class="brand-text">Our Customer Experience Center would be happy to assist you with any questions</p>
						<p class="brand-text">Phone Number: (800) 222-5500</p>
									
						<!-- </div> -->
						</div>
						</div>
					</div>
					</div>
				</s:iterator>
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
			var car='${selectedVehical.carlineDesc}';
			 $("#servicecarNameDisp").text(car);  
			 //$("#servicecarNameDisp2").text(car);  
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
			var serviceType = {};
			$("select[name='opCode'] > option").each(function () {
			    if(serviceType[this.text]) {
			        $(this).remove();
			    } else {
			    	serviceType[this.text] = this.value;
			    }
			    $('#serviceTypeSelector').append(serviceType);
			});
		});
		
		$('#myTab').tabCollapse();  

$('#datetimepicker1').datetimepicker({
    //format: 'YYYY-MM-DD'
      format: 'MM/DD/YYYY'
  });
  
    var oldId='${selectedVehical.vin}';
    
 function showUpdateSelectedVehicleEgift(vin){
    	$(".js-tabcollapse-panel-body").hide();
    	var car=$("#servicecarNameDisp").text();
    	var changecss= $("#"+vin).attr("data-id");
    	if(changecss==='1'){
    					$.ajax({
    						type : "POST",
    						url : "showUpdateSelectedVehicleEgift.action",
    						data : "vin ="+vin,
    						beforeSend : function() {
    							$("#loadingmessage").show();
    						},
    						success : function(result) {
    							$("#container-fluid").html(result);
    							$("#servicecarNameDisp").text(car);
    							 $('#selector'+oldId).removeClass("active");
    							$('#selector'+vin).addClass("active"); 
    							 $("#"+vin).attr("data-id", "2");
    							 $("#"+vin).addClass("fa-minus-circle"); 
    							 $("#"+vin). removeClass("fa-plus-circle");
    							
    							$('#datetimepicker1').datetimepicker({
							        format: 'MM/DD/YYYY'
							    });
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
		</script>