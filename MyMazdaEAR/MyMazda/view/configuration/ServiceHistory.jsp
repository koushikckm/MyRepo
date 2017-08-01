<%@ taglib prefix="s" uri="/struts-tags"%>

<jsp:include page="../common/Header.jsp" />	
<jsp:include page="/view/common/DataLayerJs.jsp"></jsp:include>

<script type="text/javascript">
	$(document).ready(function() {
    	var year = mdlyear.innerHTML;
    	var code = carlinecode.innerHTML;
    	var vin = vinSelector.innerHTML;
    	var edityear = EditServiceYear.innerHTML;
    	var editcode = EditServiceCode.innerHTML;
    	var editvin = EditServiceVin.innerHTML;
		dataLayer.page.name = 'service_history';
		dataLayer.page.nameHistorical = 'musa:owners_mymazda_service_history';
		dataLayer.page.subCategory = 'owners_mymazda_service_history';
		dataLayer.page.URL = window.location.href;
		dataLayer.site.sectionHistorical = 'owners';
		dataLayer.site.section = 'mymazda';
		dataLayer.site.subsection = 'service';
		dataLayer.vehicle.vehicleID = code;
		dataLayer.vehicle.modelYear = year;
		dataLayer.vehicle.vin = vin;
		dataLayer.vehicle.vehicleID = editcode;
		dataLayer.vehicle.modelYear = edityear;
		dataLayer.vehicle.vin = editvin;
		dataLayer.user.type = 'o';
	});
	function text(){
	 	dataLayer.form.serviceType = $("#serviceTypeSelector option:selected").text();
	}
</script>

<!DOCTYPE html>
<div class="overlay"></div>
<!--Container Start-->
<div class="ServiceHistory-Page" id="ServiceHistory-Page">
	<div class="container">
		<div class="row margin-0">
			<div class="col-xs-12 col-sm-12 col-md-12 Ser-head padding-0">
				<div class="search-field1">
					<input type="search" class="form-control historysearchkey"
						placeholder="Locate Nearest Dealer (Enter Zip Code)"  id="serviceSearchDealer"/> <i
						class="fa fa-map-marker" onclick="return historysearchDealers();" style="cursor:pointer;"></i>
				</div>				
			</div>
		</div>
	</div>
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
	<li><a href="javaScript:;" onclick="return serviceHistory('<s:text name='#session.selectedVehical.vin'/>');" class="Menu2-active" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="service_history"><span class="Menu2-no-divider"></span>SERVICE HISTORY</a></li>
	<li><a href="http://www.mazdausa.com/MusaWeb/displayManualsByModelAndYearHome.action" target="_blank" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="manuals_and_references"><span class="Menu2-divider"></span>Manuals & References</a></li>
	<li><a href="javaScript:;" target="_blank" onclick="return maintenanceSchedule();" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="maintenance_schedule"><span class="Menu2-divider"></span>MAINTENANCE SCHEDULE</a></li>
	<li><a href="#" onclick="return videoDemosLogin();" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="vehicle_demos_guides"><span class="Menu2-divider"></span>Vehicle Demos & Guides</a></li>
	<s:if test="#session.egiftTab == 0 && #session.egiftTabOnSelectVeh ==0 && #session.egiftTabOnRelPage == 0 && #session.egiftTabOnServHistChange == 0">
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
	   <h1 class="text-uppercase">SERVICE HISTORY</h1>
		<div class="nav-tab-div">
			<ul class="nav nav-tabs" id="myTab">
				<s:if test="#session.vehicalsList.size() ==1">
					<s:iterator value="#session.vehicalsList" status="count" begin="0"
						end="0">
						<li class="test-class" id="selector<s:property value="vin"/>"
							onclick="return showUpdateSelectedVehicle('<s:property value="vin"/>');">
							<a data-toggle="tab" href='#car<s:property value="vin"/>'
							cssClass="deco-none misc-class "> <s:property
									value="carlineDesc" /> <s:hidden name="id" /> <i class="fa  fa-plus-circle"  data-id ="1" id="<s:property value="vin"/>" onclick="return showUpdateSelectedVehicle('<s:property value="vin"/>');"></i>
						</a> <s:hidden name="id" cssClass="vehical" />
						</li>
					</s:iterator>					
				</s:if>
				<s:if test="#session.vehicalsList.size() ==2">
					<s:iterator value="#session.vehicalsList" status="count" begin="0"
						end="1">
						<li class="test-class" id="selector<s:property value="vin"/>"
							onclick="return showUpdateSelectedVehicle('<s:property value="vin"/>');">
							<a data-toggle="tab" href='#car<s:property value="vin"/>'
							cssClass="deco-none misc-class "> <s:property
									value="carlineDesc" /> <s:hidden name="id" /> <i id="<s:property value="vin"/>"
								class="fa  fa-plus-circle  "  data-id ="1"
								onclick="return showUpdateSelectedVehicle('<s:property value="vin"/>');"></i>
						</a> <s:hidden name="id" cssClass="vehical" />
						</li>
					</s:iterator>
				</s:if>
				<s:if test="#session.vehicalsList.size() ==3">
					<s:iterator value="#session.vehicalsList" status="count" begin="0"
						end="2">
						<li class="test-class" id="selector<s:property value="vin"/>"
							onclick="return showUpdateSelectedVehicle('<s:property value="vin"/>');">
							<a data-toggle="tab" href='#car<s:property value="vin"/>'
							cssClass="deco-none misc-class"> <s:property
									value="carlineDesc" /> <s:hidden name="id" /> <i id="<s:property value="vin"/>"
								class="fa  fa-plus-circle"  data-id ="1"
								onclick="return showUpdateSelectedVehicle('<s:property value="vin"/>');"></i>
						</a> <s:hidden name="id" cssClass="vehical" />
						</li>
					</s:iterator>					
				</s:if>
				<s:if test="#session.vehicalsList.size() >3">
					<s:iterator value="#session.vehicalsList" status="count" begin="0"
						end="2">
						<li class="test-class" id="selector<s:property value="vin"/>"
							onclick="return showUpdateSelectedVehicle('<s:property value="vin"/>');">
							<a data-toggle="tab" href='#car<s:property value="vin"/>'
							cssClass="deco-none misc-class"> <s:property
									value="carlineDesc" /> <s:hidden name="id" /> <i id="<s:property value="vin"/>"
								class="fa  fa-plus-circle"  data-id ="1"
								onclick="return showUpdateSelectedVehicle('<s:property value="vin"/>');"></i>
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
									onclick="return showUpdateSelectedVehicle('<s:property value="vin"/>');">
									<a data-toggle="tab" href='#car<s:property value="vin"/>'
									value="id" cssClass="deco-none misc-class"> <s:property
											value="carlineDesc" /> <s:hidden name="id" /> <i id="<s:property value="vin"/>"
										class="fa fa-plus-circle" data-id ="1"
										onclick="return showUpdateSelectedVehicle('<s:property value="vin"/>');"></i>
								</a> <s:hidden name="id" cssClass="vehical" />
								</li>
							</s:iterator>
						</ul></li>
				</s:if>
			</ul>
			<div class="tab-content responsive-tab" >
				<s:iterator value="#session.selectedVehical" status="count" >
					<div class="tab-pane fade in active" id="car<s:property value="vin"/>" >
					<div class="ServiceHistory-Div">
						<div class="row margin-0 SH-hedding text-uppercase">
							<s:if test="hasActionMessages()">
							<div class="col-xs-12 col-sm-3 padding-0">
									<P>
										VIN: <span id="vinSelector"> <s:text
												name="#session.selectedVehical.vin" />
										</span>
									</P>
								</div>
								<div class="col-xs-12 col-sm-3 padding-0">
									<P>
										YEAR: <span id="mdlyear"> <s:text
												name="#session.selectedVehical.mdlYear" />
										</span>
									</P>
								</div>
								<div class="col-xs-12 col-sm-3 padding-0">
									<P>
										MODEL NAME: <span id="carlinecode"> <s:text
												name="#session.selectedVehical.carlineCode" />
										</span>
									</P>
								</div>
								<div class="col-xs-12 col-sm-3 padding-0">
									<a href="javaScript:;" onclick="addServiceHistory();"
										class="btn btn-default btn-red brand-button-white" data-analytics-link-component-name="history" data-analytics-link-type="button" data-analytics-link-description="add_history">ADD SERVICE RECORD</a>
								</div>
								<div class="col-xs-12 col-sm-12 padding-0 servicetext" >
									<s:actionmessage  />
								</div>
							</s:if>
							<s:else>
								<div class="col-xs-12 col-sm-3 padding-0">
									<P>
										VIN: <span id="vinSelector"> <s:text
												name="#session.selectedVehical.vin" />
										</span>
									</P>
								</div>
								<div class="col-xs-12 col-sm-3 padding-0">
									<P>
										YEAR: <span id="mdlyear"> <s:text
												name="#session.selectedVehical.mdlYear" />
										</span>
									</P>
								</div>
								<div class="col-xs-12 col-sm-3 padding-0">
									<P>
										MODEL NAME: <span id="carlinecode"> <s:text
												name="#session.selectedVehical.carlineCode" />
										</span>
									</P>
								</div>
								<div class="col-xs-12 col-sm-3 padding-0">
									<a href="javaScript:;" onclick="addServiceHistory(undefined,'AddRecord');"
										class="btn btn-default btn-red brand-button-white" data-analytics-link-component-name="history" data-analytics-link-type="button" data-analytics-link-description="add_history">ADD SERVICE RECORD</a>
								</div>
							</s:else>
						</div>
						<s:if test="hasActionMessages()" />

						<s:else>
							<s:iterator value="serviceHistoryDetailList" status="stat">
							   <div class="clearfix"></div>
								<div class="ServiceHistory-text"
									id="serviceHistory-<s:property value="%{#stat.index}"/>">
									<div class="row margin-0 sh-header">
										<div class="col-xs-10 col-sm-6 padding-0 text-uppercase">
											<h3>
												<s:text name="serviceType" />
												<span id="opCode<s:text name="musaRefId"/>"
													style="display: none"> <s:text name="opCode" /> ,
													<s:text name="serviceType" />
												</span>
											</h3>
										</div>
										<div class="col-xs-2 col-sm-2 padding-0 col-sm-push-4">
											<div class="edit-icon">
												<a href="javaScript:"
													onclick="return updateServiceRecord(<s:text name="musaRefId"/>);" data-analytics-link-component-name="history" data-analytics-link-type="button" data-analytics-link-description="edit_history">
													<i class="fa fa-edit"></i>
												</a>
											</div>
										</div>
										<div class="col-xs-10 col-sm-4 padding-0 text-uppercase col-sm-pull-2">
											<P>
												DATE OF SERVICE: <span id="date<s:text name="musaRefId"/>">
													<s:text name="serviceDate" />
												</span>
											</P>
										</div>										
									</div>
									<div class="row margin-0 sh-text">
										<div class="col-xs-5 col-sm-4 padding-0">
											<p>CURRENT MILEAGE</p>
										</div>
										<div class="col-xs-7 col-sm-8 padding-0">
											<span id="mileage<s:text name="musaRefId"/>"> : <s:text
													name="mileage" /> Miles
											</span>
										</div>
										<div class="col-xs-5 col-sm-4 padding-0">
											<p>COST OF SERVICE</p>
										</div>
										<div class="col-xs-7 col-sm-8 padding-0">
											<span id="amount<s:text name="musaRefId"/>"> : $<s:text
													name="amount" />
											</span>
										</div>
										<div class="col-xs-5 col-sm-4 padding-0">
											<p>LOCATION</p>
										</div>
										<div class="col-xs-7 col-sm-8 padding-0">
											<span id="location<s:text name="musaRefId"/>"> : <s:text
													name="serviceLocation" />
											</span>
										</div>
										<div class="col-xs-5 col-sm-4 padding-0">
											<p>COMMENTS</p>
										</div>
										<div class="col-xs-7 col-sm-8 padding-0">
											<span id="comments<s:text name="musaRefId"/>"> : <s:text 
													name="comment" />
											</span>
										</div>
									</div>
								</div>
							</s:iterator>
							<s:iterator value="mazdaServiceHistoryDetailList" status="stat">
								<div class="ServiceHistory-text"
									id="serviceHistory-<s:property value="%{#stat.index}"/>">
									<div class="row margin-0 sh-header">
										<div class="col-xs-12 col-sm-6 padding-0">
											<h3>
												<s:text name="serviceType" />
											</h3>
										</div>
										<div class="col-xs-12 col-sm-4 padding-0">
											<P>
												DATE OF SERVICE: <span id="date<s:text name="musaRefId"/>"><s:text
														name="serviceDate" /></span>
											</P>
										</div>
									</div>
									<div class="row margin-0 sh-text">
										<div class="col-xs-5 col-sm-3 padding-0">
											<p>Dealer name</p>
										</div>
										<div class="col-xs-7 col-sm-9 padding-0">
											:<span><s:text name="dealerName" /></span><br>
										</div>
										<div class="col-xs-5 col-sm-3 padding-0">
											<p>Dealer Address</p>
										</div>
										<div class="col-xs-7 col-sm-9 padding-0">
											:<span><s:text name="dealerAddress" /><br> <s:text
													name="dealerState" /> <s:text name="dealerCity" /> <s:text
													name="dealerZip" /></span>
										</div>
									</div>
								</div>
							</s:iterator>
						</s:else>
					</div>
				</div>
				</s:iterator>
			</div>
		</div>
	 </div>
</div>

	<div id="serviceHistoryFormId" style="display: none">	
		<div class="AddService-page">
			<div class="container">
				<div class="row margin-0">
					<div class="col-xs-12 col-sm-12 col-md-12 Reg-head padding-0">
						<div class="search-field1">
							<input type="search" class="form-control addservicesearchkey"
								placeholder="Locate Nearest Dealers (Enter Zip Code or Name)" id="addserviceSearchDealer" /> <i
								class="fa fa-map-marker"  onclick="return addServiceSearchDealers();" style="cursor:pointer;"></i>
						</div>
					</div>
				</div>
				</div>			
			<div class="Menu2">
				<div class="container">
				<nav class="navbar navbar-default sub-menu2">
				<div class="container-fluid padding-0">
				<div class="navbar-header">
				<button type="button" class="navbar-toggle accordion-toggle collapsed vehicle-menu" data-toggle="collapse" data-target="#bs-example-navbar-collapse-2" aria-expanded="false">
				<i class="fa fa-plus-circle"></i>
				</button>
				<span><a href="" onclick="return homeClick();" class="summary" id="servicecarNameDisp2"></a></span>
				</div>
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
				<ul class="nav navbar-nav">
				<li><a href="javaScript:;" onclick="return serviceHistory('<s:text name='#session.selectedVehical.vin'/>');" class="Menu2-active"><span class="Menu2-no-divider"></span>SERVICE HISTORY</a></li>
				<li><a href="http://www.mazdausa.com/MusaWeb/displayManualsByModelAndYearHome.action" target="_blank"><span class="Menu2-divider"></span>Manuals & References</a></li>
				<li><a href="javaScript:;" target="_blank" onclick="return maintenanceSchedule();"><span class="Menu2-divider"></span>MAINTENANCE SCHEDULE</a></li>
				<li><a href="#" onclick="return videoDemosLogin();"><span class="Menu2-divider"></span>Vehicle Demos & Guides</a></li>
				<s:if test="#session.egiftTab == 0 && #session.egiftTabOnSelectVeh ==0 && #session.egiftTabOnRelPage == 0 && #session.egiftTabOnServHistChange == 0">
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
				<div id="titleAdd">

					<h1>ADD A SERVICE RECORD</h1>

					<p class="p-header brand-info">
						To add a service to your
						VIN: <span id="AddServiceVin"><s:text name="#session.selectedVehical.vin" /></span>,&nbsp; 
						VEHICLE YEAR: <span id="AddServiceYear"><s:text name="#session.selectedVehical.mdlYear" /></span>,&nbsp;  
						VEHICLE MODEL: <span id="AddServiceCode"><s:text name="#session.selectedVehical.carlineCode" /></span>,&nbsp; 
						USER-CREATED NAME: <span id="vehicleName"><s:text name="#session.selectedVehical.carlineDesc" /></span>,&nbsp;
						please complete the form below.
				</div>
				<div id="titleEdit" style="display: none">
					<h1>EDIT A SERVICE RECORD</h1>

					<p class="p-header brand-info">
						Please complete the form below to edit the service record on
						VIN: <span id="EditServiceVin"><s:text name="#session.selectedVehical.vin" /></span>,&nbsp;
						VEHICLE YEAR: <span id="EditServiceYear"><s:text name="#session.selectedVehical.mdlYear" /></span>,&nbsp;
						VEHICLE MODEL: <span id="EditServiceCode"><s:text name="#session.selectedVehical.carlineCode" /></span>,&nbsp;
						USER-CREATED NAME: <span><s:text name="#session.selectedVehical.carlineDesc" /></span>.
					</p>
				</div>
				<form role="form" id="serviceHistoryForm">
					<div class="row AddService-div margin-0">
						<div class="col-xs-12 col-sm-6 s-information brand-bdr-rgt">
							<h4>SERVICE INFORMATION
								<div class="required-text padding-0"><span class="mandatory-field">*</span>Required</div>
							</h4>
							<s:hidden type="input" name="musaRefId" ></s:hidden>
							<s:if test="#session.vehicalsList.size() >0">
								<span style="display: none"> <label>Vin <strong>*</strong>
								</label> <s:select name="vin" list="#session.vehicalsList" listKey="vin"
										id="vinId" listValue="vin" />
								</span>
							</s:if>
							<div class="row margin-0">
								<div class="form-group col-xs-12 padding-0">
									<label>DATE OF SERVICE<span class="mandatory-field">*</span></label>
									<div class='input-group date form-group col-xs-12 padding-0'
										id='datetimepicker1'>
										<!--<div class='input-group date s-date-field' id='datetimepicker1' style="display: block">-->
										<input name="serviceDate" type='text'
											onkeypress="return disabled" id="popupDatepicker"
											class="form-control formcontrol-padding s-date-field"
											placeholder="DATE OF SERVICE"></input> <span
											class="input-group-addon calenderspan"> <span
											class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
								</div>
								<div class="form-group col-xs-12 padding-0">
									<label>MILEAGE AT THE TIME OF SERVICE<span
										class="mandatory-field">*</span></label> <input type="text"
										name="mileage"
										class="form-control s-current-mileage formcontrol-padding"
										id="mileage" placeholder="ENTER MILEAGE (MILES)">
								</div>
								<div class="form-group col-xs-12 padding-0">
									<label>LOCATION<span class="mandatory-field">*</span></label> <input
										type="text" id="locationSelector" name="serviceLocation"
										class="form-control s-location formcontrol-padding"
										placeholder="ENTER THE LOCATION OF SERVICE">
								</div>
								<div class="form-group col-sm-12 col-xs-12 padding-0">
									<label>SERVICE TYPE<span class="mandatory-field">*</span></label>
									<div class="select-style">
										<s:select  
										headerKey="-1" headerValue="SELECT A SERVICE TYPE"
 										list="serviceTypeList" id="serviceTypeSelector"
											name="opCode" listKey="opCode" listValue="serviceType" onchange="text()">
											</s:select>
									</div>
								</div>
								<div class="form-group col-xs-12 padding-0">
									<label>COST OF SERVICE<span class="mandatory-field">*</span></label>
									<input type="text" name="amount" class="form-control s-cost"
										id="costOfServiceId" placeholder="COST OF SERVICE">
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-6 comments-padding">
							<h4><p class="required-text"></p></h4>
							<div class="row margin-0 padding-0">
								<div class="form-group col-xs-12 padding-0 brand-tmargin30">
									<label class="comment-label">COMMENTS</label>
									<textarea class="form-control form-control-textarea" rows="5"
										id="commentsFieldId" name="comment"
										placeholder="PLEASE ENTER COMMENTS HERE"></textarea>
								</div>
								<div class="clearfix"></div>
								<div class="button_section">
									<button type="submit" class="btn btn-default btn-red brand-button-white"
										onclick="return saveServiceHistory('<s:text name='#session.selectedVehical.vin'/>');"
										id="addBtn" data-analytics-link-component-name="form" data-analytics-link-type="submit" data-analytics-link-description="submit">ADD SERVICE RECORD</button>
									<button type="submit" class="btn btn-default btn-red brand-button-white"
										onclick="return updateRecord('<s:text name='#session.selectedVehical.vin'/>');"
										id="editBtn" style="display: none">SAVE CHANGES</button>
									<button type="submit" class="btn btn-default btn-gray brand-button"
										onclick="return serviceHistory('<s:text name='#session.selectedVehical.vin'/>');" data-analytics-link-component-name="form" data-analytics-link-type="button" data-analytics-link-description="cancel">CANCEL</button>
									<button type="submit" class="btn btn-default btn-red brand-button-white"
										onclick="return deleteServiceHistory('<s:text name='#session.selectedVehical.vin'/>');" id="deleteBtn" data-analytics-link-component-name="form" data-analytics-link-type="button" data-analytics-link-description="delete">DELETE</button>
								</div>
							</div>
						</div>
					</div>
				</form>
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
	
	if($('#costOfServiceId').val().trim() == '') { $('#costOfServiceId').val('0'); }
		
	$(document).ready(function() {
		
		 $(".historysearchkey").keyup(function (e) {
		    if (e.keyCode == 13) {
		    	historysearchDealers();
				return false;
		    } 
		});
		 $(".addservicesearchkey").keyup(function (e) {
			    if (e.keyCode == 13) {
			    	addServiceSearchDealers();
					return false;
			    } 
			});
 });
		$(function(){
			var car='${selectedVehical.carlineDesc}';
			 $("#servicecarNameDisp").text(car);  
			 $("#servicecarNameDisp2").text(car);  
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
      function showUpdateSelectedVehicle(vin){
    	$(".js-tabcollapse-panel-body").hide();
    	var car=$("#servicecarNameDisp").text();
    	var changecss= $("#"+vin).attr("data-id");
    	if(changecss==='1'){
    	//alert("showUpdateSelectedVehicle.action-------vin:"+vin);
    					$.ajax({
    						type : "POST",
    						url : "showUpdateSelectedVehicle.action",
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
    
    function addServiceHistory(flag,addRecord){
    	if(flag == 'undefined' || flag == null)	{
			flag = 'true';
		}
		if(flag == 'true')	{
			if(addRecord == "AddRecord"){
				setTimeout(function(){
					var addyear = AddServiceYear.innerHTML;
			    	var addcode = AddServiceCode.innerHTML;
			    	var addvin = AddServiceVin.innerHTML;
					dataLayer.page.name = 'add_service_record';
					dataLayer.page.nameHistorical = '';
					dataLayer.page.subCategory = '';
					dataLayer.page.URL = window.location.href;
					dataLayer.site.section = 'mymazda';
					dataLayer.site.subsection = 'service';
					dataLayer.form.name = 'add_service_record';
					dataLayer.form.type = 'add_service_record';
					dataLayer.vehicle.vehicleID = addcode;
					dataLayer.vehicle.modelYear = addyear;
					dataLayer.vehicle.vin = addvin;
					dataLayer.user.type = 'o';
				});
			}
			var data = {};
			var state = { 'url': 'addServiceHistory.action',data:data};
			history.pushState(state ,'mymazda', '#addServiceHistory');
		}
    	$("#ServiceHistory-Page").hide();
    	$("#serviceHistoryFormId").show();	
    	$("#menu1").hide();
    	$("#deleteBtn").hide();
      var carlineDesc=$("#vehicleName").text();
    	$("#serviceName").text(carlineDesc);
    	var car=$("#servicecarNameDisp").text();
    	$("#servicecarNameDisp2").text(car);
    }
    
    function saveServiceHistory(vin) {
    	//alert("saveServiceHistory()");
    	var data = $("#serviceHistoryForm").serialize();
    	
    	var serviceType = $("#serviceTypeSelector option:selected").text();
    	data = data+"&serviceType="+serviceType;
    	var mileage = Number($("[name='mileage']").val().trim());
    	var amt = $("[name='amount']").val().trim();
    	amt = amt.replace('$','').trim();
    	$("[name='amount']").val(amt);
    	var amount = Number(amt);
    	var location = $("#locationSelector").val();
    	var serviceDate = $("#popupDatepicker").val();
    	if(serviceDate == "" ){
 			$("#error-sucess").modal("show");
    		$("#errorMsg").html("Please enter a service date.");
    		//alert("You must enter Service Date.");
    	}else if(isNaN(mileage) || mileage<=0 ){
 			$("#error-sucess").modal("show");
    		$("#errorMsg").html("Please enter your Mazda's mileage at the time of the service.");
    		//alert("You must enter valid mileage.");
    	}else if(location == "" ){
    		$("#error-sucess").modal("show");
    		$("#errorMsg").html("Please enter a service location.");
    		//alert("You must enter Service Location.");
    	}else if(serviceType === "SELECT A SERVICE TYPE" ){
    		$("#error-sucess").modal("show");
    		$("#errorMsg").html("Please select a service type.");
    		//alert("You must enter Service Location.");
    	}else if(isNaN(amount) || amount<0 ){
    		$("#error-sucess").modal("show");
    		$("#errorMsg").html("You must enter valid Cost of Service.");
    	}else{
    		$.ajax({
           		url: "saveServiceHistory.action",
       			type: 'POST',
           		data:  data, 
           		beforeSend : function(){
           			$("#loadingmessage").show();
           		},
       			success: function(data, textStatus, jqXHR) {
       				if(data == "error"){
       					$("#error-sucess").modal("show");
       		    		$("#errorMsg").html("Sorry, unable to match a vehicle with the vin and your registered customer id. Please call 1-800-222-5500 if you have any question.");
       					//alert("Sorry, unable to match a vehicle with the vin and your registered customer id. Please call 1-800-222-5500 if you have any question.");
       				}else{
       					$("#success-msg").modal("show");
						$("#successMsg")
								.html("History added sucessfully.");
       					//alert("History added sucessfully");
       					$("#container-fluid").html(data);
       					$('#selector'+oldId).removeClass("active");
       					$('#selector'+vin).addClass("active");
       					setTimeout(function() {
							 $("#success-msg").modal("hide");
							
						}, 1000); 
       					}
       			}, complete:function(){
       				$("#loadingmessage").hide();
        		}      
       		});
       		}
       		return false;
    	} 
    var carId='${selectedVehical.vin}';
    function updateServiceRecord(serviceId){
    	addServiceHistory();
    	window.scrollTo(0, 0);
    	$("#titleAdd").hide();
    	$("#titleEdit").show();	
    	$("#editBtn").show();
    	$("#deleteBtn").show();
    	$("#addBtn").hide();
    	var date=$("#date"+serviceId).text().trim();
    	var mileage = $("#mileage"+serviceId).text().trim();
    	var	mileageData=mileage.split(':');
    	mileage=mileageData[1].trim();
    	
    	var opCode = $("#opCode"+serviceId).text().trim();
    	var opCodeArr = opCode.split(",");
    	opCode = opCodeArr[0].trim();
    	var amount = $("#amount"+serviceId).text().trim();
    	var	amountdata=amount.split('$');
    	amount=amountdata[1].trim();
    	//alert(amount);
    	var location = $("#location"+serviceId).text().trim();
    	var	locationData=location.split(':');
    	location=locationData[1].trim();
    	
    	var comment = $("#comments"+serviceId).text().trim();
    	var	commentData=comment.split(':');
    	comment=commentData[1].trim();
    	
    	var serviceType = $("#serviceType"+serviceId).text().trim();
    	//alert("date:"+date+"\nmileage:"+mileage+"\namount:"+amount+"\nlocation:"+location+"\ncomment:"+comment+"\nserviceType:"+opCode);
    	$("[name='serviceDate']").val(date);
    	$("#serviceTypeSelector").val(opCode);
    	$("#mileage").val(mileage);
    	$("[name='amount']").val(amount);
    	$("#commentsFieldId").val(comment);
    	$("#locationSelector").val(location);
    	$("[name='musaRefId']").val(serviceId);
    	showUpdateSelectedVehicle(carId);
    }	
    	
    function updateRecord(vin,flag){
    	var data={};
    	var mileage = Number($("[name='mileage']").val().replace('Miles','').trim());
    	$("[name='mileage']").val(mileage);
    	var serviceId = $("[name='musaRefId']").val();
    	var data = $("#serviceHistoryForm").serialize();
    	var serviceType =$.trim($("#serviceType"+serviceId).text());
    	var serviceTypeUpdate = $("#serviceTypeSelector option:selected").text();
    	data = data+"&serviceType="+serviceType+"&serviceDealerId="+serviceId;
    	//alert("Update Data 0:"+data);
    	//var serviceType = $("#serviceTypeSelector option:selected").text();
    	data = data+"&serviceType="+serviceType;
    	//var mileage = Number($("[name='mileage']").val().replace('Miles','').trim());
    	var amt = $("[name='amount']").val().trim();
    	amt = amt.replace('$','').trim();
    	$("[name='amount']").val(amt);
    	var amount = Number(amt);
    	var location = $("#locationSelector").val();
    	var serviceDate = $("#popupDatepicker").val();
    	if(serviceDate == "" ){
 			$("#error-sucess").modal("show");
    		$("#errorMsg").html("Please enter a service date.");
    		//alert("You must enter Service Date.");
    	}else if(isNaN(mileage) || mileage<=0 ){
 			$("#error-sucess").modal("show");
    		$("#errorMsg").html("Please enter your Mazda's mileage at the time of the service.");
    		//alert("You must enter valid mileage.");
    	}else if(location == "" ){
    		$("#error-sucess").modal("show");
    		$("#errorMsg").html("Please enter a service location.");
    		//alert("You must enter Service Location.");
    	}else if(serviceTypeUpdate === "SELECT A SERVICE TYPE" ){
    		$("#error-sucess").modal("show");
    		$("#errorMsg").html("Please select a service type.");
    		//alert("You must enter Service Location.");
    	}else if(isNaN(amount) || amount<0 ){
    		$("#error-sucess").modal("show");
    		$("#errorMsg").html("You must enter valid Cost of Service.");
    	}else{   
    		if(flag == 'undefined' || flag == null)
    		{
    		flag = 'true';
    		}
    	if(flag == 'true')
    		{
    	var state = { 'url': 'updateServiceHistory.action',data:data};
    	history.pushState(state ,'mymazda', '#updateserviceRecord');
    }
	    	$.ajax({
	           		url: "updateServiceHistory.action",
	       			type: 'POST',
	           		data:  data,
	           		beforeSend : function(){
	           			$("#loadingmessage").show();
	           		},
	       			success: function(data, textStatus, jqXHR) {
	       				if(data == "error"){
	       					$("#error-sucess").modal("show");
	       		    		$("#errorMsg").html("Sorry, unable to match a vehicle with the vin and your registered customer id. Please call 1-800-222-5500 if you have any question.");
	       					//alert("Sorry, unable to match a vehicle with the vin and your registered customer id. Please call 1-800-222-5500 if you have any question.");
	       				}else{
	       		    		$("#success-msg").modal("show");
							$("#successMsg")
									.html(" History updated sucessfully.");
	    					//alert("History updated sucessfully");
	    					$("#container-fluid").html(data);
	    					$('#selector'+oldId).removeClass("active");
	    					$('#selector'+vin).addClass("active");
	    					oldId = vin; 
	    					setTimeout(function() {
								 $("#success-msg").modal("hide");
								
							}, 1000); 
	    				}
	       				//	$("#container-fluid").html(data);
	       			}, complete:function(){
	       				$("#loadingmessage").hide();
	        		}      
	       		});
    	}
    	setTimeout(function() {
    		showUpdateSelectedVehicle(vin);
			
		}, 2000); 
    	
     	return false;
    }
    function historysearchDealers() {
    	add='';
    	edit='';
    	registersearch='';
    	var data = {};
    	
    	var dealerSearch = $("#serviceSearchDealer").val();
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
    function addServiceSearchDealers() {
    	add='';
    	edit='';
    	registersearch='';
    	var data = {};
    	
    	var dealerSearch = $("#addserviceSearchDealer").val();
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
    
    function deleteServiceHistory(vin) {
    	//alert("saveServiceHistory()");
    	var mileage = Number($("[name='mileage']").val().replace('Miles','').trim());
    	$("[name='mileage']").val(mileage);
    	var data = $("#serviceHistoryForm").serialize();
    	
    	var serviceType = $("#serviceTypeSelector option:selected").text();
    	data = data+"&serviceType="+serviceType;
    	
    	var amt = $("[name='amount']").val().trim();
    	amt = amt.replace('$','').trim();
    	$("[name='amount']").val(amt);
    		$.ajax({
           		url: "deleteServiceHistory.action",
       			type: 'POST',
           		data:  data, 
           		beforeSend : function(){
           			$("#loadingmessage").show();
           		},
       			success: function(data, textStatus, jqXHR) {
       				if(data == "error"){
       					$("#error-sucess").modal("show");
       		    		$("#errorMsg").html("Sorry, unable to delete the History.");
       					//alert("Sorry, unable to match a vehicle with the vin and your registered customer id. Please call 1-800-222-5500 if you have any question.");
       				}else{
       					$("#success-msg").modal("show");
						$("#successMsg")
								.html("History deleted sucessfully.");
       					//alert("History added sucessfully");
       					$("#container-fluid").html(data);
       					$('#selector'+oldId).removeClass("active");
       					$('#selector'+vin).addClass("active");
       					setTimeout(function() {
							 $("#success-msg").modal("hide");
							
						}, 1000); 
       					}
       			}, complete:function(){
       				$("#loadingmessage").hide();
        		}      
       		});
       		
       		return false;
    	} 