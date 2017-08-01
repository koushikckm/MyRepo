<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<title>MyMazda</title>
<!-- <script src="//assets.adobedtm.com/0b1a9905a3bfe56ba99f4bcd3560048b3d9faf5f/satelliteLib-4332df72469fbed611c92423a310658ec4352e71.js"></script>  -->
<script src="//assets.adobedtm.com/0b1a9905a3bfe56ba99f4bcd3560048b3d9faf5f/satelliteLib-9dac536f1ca76627327a770a7af6d1ac98184c84-staging.js"></script>
</head>
<%-- <%@page buffer="2000kb"%> --%>
<jsp:include page="../configuration/HowTOMazdaLoginJS.jsp"></jsp:include>
<jsp:include page="../configuration/LookUpItemsJS.jsp"></jsp:include>
<body>

<jsp:include page="../common/Header.jsp" />	
<jsp:include page="/view/common/DataLayerJs.jsp"></jsp:include>

<script type="text/javascript">
	$(document).ready(function() {
		dataLayer.page.name = 'demos_and_guides';
		dataLayer.page.URL = window.location.href;
		dataLayer.site.section = 'mymazda';
		dataLayer.site.subsection = 'vehicle';
		dataLayer.form.name = 'topic_search';
		dataLayer.form.type = 'topic_search';
		dataLayer.user.type = 'o';
	});
	var howtoyear;
	function year(){
	 	howtoyear = $("#selectedYear").val();
		dataLayer.vehicle.modelYear = howtoyear;
	}
	var selectmodel;
	function model(sel){
	 	selectmodel = sel.value;
		dataLayer.vehicle.vehicleID = selectmodel;
	}
</script>
<div class="overlay"></div>		
<div class="VehicleDemo-page">
<div class="container">
 <div class="row margin-0">
	<div class="col-xs-12 col-sm-12 col-md-12 Ser-head padding-0">
		<div class="search-field1">
			<input type="search" class="form-control vediosearchkey"
				placeholder="Locate Nearest Dealer (Enter Zip Code)"  id="vedioSearchDealer"/> <i
				class="fa fa-map-marker" onclick="return vediosearchDealers();" style="cursor:pointer;"></i>
			</div>				
		</div>
</div>
</div>
<s:if test="#session.loginUser != null">
<div class="Menu2">
          <div class="container">         
              <nav class="navbar navbar-default sub-menu2">
                 <div class="container-fluid padding-0">
                    <div class="navbar-header">
                       <button type="button" class="navbar-toggle accordion-toggle collapsed vehicle-menu" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                       <i class="fa fa-plus-circle"></i>
                       </button>
                            <s:iterator value="#session.selectedVehical" status="count" >
                                    <a href="" onclick="return homeClick();" class="summary" id="selectVehicle"><s:property value="carlineDesc"/></a>
                            </s:iterator>
                    </div>
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                       <ul class="nav navbar-nav">
                          <li>
	                            <s:iterator value="#session.selectedVehical" status="count" >
	                            	<a href="javaScript:;" onclick="return serviceHistory('<s:text name='#session.selectedVehical.vin'/>');" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="service_history"><span class="Menu2-no-divider"></span>SERVICE HISTORY</a>
	                            </s:iterator>   
                          </li>  
                          <li><a href="http://www.mazdausa.com/MusaWeb/displayManualsByModelAndYearHome.action" target="_blank" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="manuals_and_references"><span class="Menu2-divider"></span>Manuals & References</a></li>
                          <li><a href="javaScript:;" target="_blank" onclick="return maintenanceSchedule();" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="maintenance_schedule"><span class="Menu2-divider"><span class="Menu2-divider"></span>MAINTENANCE SCHEDULE</a></li>
                          <li><a href="#" onclick="return videoDemosLogin();" class="Menu2-active" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="vehicle_demos_guides"><span class="Menu2-divider"></span>Vehicle Demos & Guides</a></li>
                          <s:if test="#session.egiftTab == 0  && #session.egiftTabOnSelectVeh ==0 && #session.egiftTabOnRelPage == 0 && #session.egiftTabOnVodeoDemosChange == 0">
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
        </s:if>
<div class="VideoDemo-header submenu-class" style="background-image: view/vhelper/images/bg-image.png" id="firstDivSelector">
	<div class="container">
		<H1>VEHICLE DEMOS & GUIDES</H1>
		<p class="brand-text">Are you getting the most from your Mazda? These quick videos
			show you how to operate your car's features and conveniences,
			including the instrument panel, navigation, entertainment system
			and more.</p>

		<form role="form" class="row margin-0 form-inline demosform" id="demosform">
			<s:if test="#session.globalVedioDemos != null">
				<div id="YearModelTripDropdown">
					<div class="col-xs-12 form-group year">
						<label for="email">YEAR</label>

						<div class="select-style">
							<s:select id="selectedYear" list="#session.yearModelTrim"
								listKey="year" listValue="year" onchange="showModel(); year();"
								name="year"/>
						</div>
					</div>
                            <%--  <%
                               boolean flag=false;
                             %>  --%>
					<s:iterator value="#session.yearModelTrim" status="yearStatus"
						begin="0">
					
						<div id="modelDiv-<s:property value="%{#yearStatus.index}"/>"							
							style="display: none;float:left;" class="model">
							<div class="form-group model-drop">
								<label for="email">SELECT A MODEL</label>
								<%-- <select class="form-control"> --%>
								<div class="select-style select-model">
									<s:select id="selectedModel%{#yearStatus.index}"
										list="models" listKey="modelCode" listValue="modelName"
										onchange="showTrim(); model(this);"/>
								</div>
							</div>
							<s:iterator value="models" status="modelStatus" begin="0">
								
								<s:if test="trims.size()>0">
								 <%-- <%
	                                  flag=true;
	                              %>  --%>
									<div id="trimDiv-<s:property value="%{#yearStatus.index}"/><s:property value="%{#modelStatus.index}"/>"
										class="form-group trim" style="display: none;">

										<label for="email">SELECT A TRIM</label>
										<%-- <select
											class="form-control"> --%>
										<div class="select-style select-trim">
											<s:select list="trims" />
										</div>

									</div>
								</s:if>
								<%-- <s:else> <%
	                                  flag=false;
	                              %> </s:else> --%>

							</s:iterator>

						</div>

					</s:iterator>
					<div id="withTrims" class="submit-btn">								
					<a href="#selectVideo" type="submit" class="btn btn-default btn-red brand-button-white" onclick="return howToVehicalOption();" data-analytics-link-component-name="vehicle_selection" data-analytics-link-type="submit" data-analytics-link-description="submit">Submit</a>
					</div>
					<div id="withOutTrims" class="submit-btn1">	
					<a href="#selectVideo" type="submit" class="btn btn-default btn-red brand-button-white" onclick="return howToVehicalOption();" data-analytics-link-component-name="vehicle_selection" data-analytics-link-type="submit" data-analytics-link-description="submit">Submit</a>
					</div>
					
				</div>
			</s:if>
		</form>
		<div id="modelPDF">
			<s:if test="#session.globalVedioDemos == null">
				<s:if test="#session.year == '2018' && #session.MdlCode == 'M3S' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://s3.amazonaws.com/tsd.mazdausa.com/Mazda_Owners_Manuals/2018/2018_Mazda3_SSG.pdf" target="_blank"
						style="color: #d6d6d6;text-decoration: underline;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if test="#session.year == '2018' && #session.MdlCode == 'CX3' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://s3.amazonaws.com/tsd.mazdausa.com/Mazda_Owners_Manuals/2018/2018_CX3_SSG.pdf" target="_blank"
						style="color: #d6d6d6;text-decoration: underline;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2018' && #session.MdlCode == 'M3H' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://s3.amazonaws.com/tsd.mazdausa.com/Mazda_Owners_Manuals/2018/2018_Mazda3_SSG.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if test="#session.year == '2017' && #session.MdlCode == 'M3S' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://s3.amazonaws.com/tsd.mazdausa.com/Mazda_Owners_Manuals/2017/2017_Mazda3_SSG.pdf" target="_blank"
						style="color: #d6d6d6;text-decoration: underline;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2017' && #session.MdlCode == 'M6G' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://s3.amazonaws.com/tsd.mazdausa.com/Mazda_Owners_Manuals/2017/2017_Mazda6_SSG.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if test="#session.year == '2017' && #session.MdlCode == 'CX3' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://s3.amazonaws.com/tsd.mazdausa.com/Mazda_Owners_Manuals/2017/2017_CX3_SSG.pdf" target="_blank"
						style="color: #d6d6d6;text-decoration: underline;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2017' && #session.MdlCode == 'CX5' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://s3.amazonaws.com/tsd.mazdausa.com/Mazda_Owners_Manuals/2017/2017_CX5_SSG.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2017' && #session.MdlCode == 'M3H' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://s3.amazonaws.com/tsd.mazdausa.com/Mazda_Owners_Manuals/2017/2017_Mazda3_SSG.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2017' && #session.MdlCode == 'MX5' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://s3.amazonaws.com/tsd.mazdausa.com/Mazda_Owners_Manuals/2017/2017_MX5_SSG.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2017' && #session.MdlCode == 'CX9' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://s3.amazonaws.com/tsd.mazdausa.com/Mazda_Owners_Manuals/2017/2017_CX9_SSG.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if test="#session.year == '2016' && #session.MdlCode == 'M3S' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://s3.amazonaws.com/tsd.mazdausa.com/Mazda_Owners_Manuals/2016/2016_Mazda3_SSG.pdf" target="_blank"
						 style="color: #d6d6d6;text-decoration: underline;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2016' && #session.MdlCode == 'M6G' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://s3.amazonaws.com/tsd.mazdausa.com/Mazda_Owners_Manuals/2016/2016_Mazda6_SSG.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if test="#session.year == '2016' && #session.MdlCode == 'CX3' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://s3.amazonaws.com/tsd.mazdausa.com/Mazda_Owners_Manuals/2016/2016_CX3_SSG.pdf" target="_blank"
						style="color: #d6d6d6;text-decoration: underline;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2016' && #session.MdlCode == 'CX5' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://s3.amazonaws.com/tsd.mazdausa.com/Mazda_Owners_Manuals/2016/2016_Mazda6_SSG.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2016' && #session.MdlCode == 'M3H' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://s3.amazonaws.com/tsd.mazdausa.com/Mazda_Owners_Manuals/2016/2016_Mazda3_SSG.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2016' && #session.MdlCode == 'MX5' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://s3.amazonaws.com/tsd.mazdausa.com/Mazda_Owners_Manuals/2016/2016_MX5_SSG.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2016' && #session.MdlCode == 'CX9' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://s3.amazonaws.com/tsd.mazdausa.com/Mazda_Owners_Manuals/2016/2016_CX9_SSG.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if test="#session.year == '2015' && #session.MdlCode == 'M3S' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2015_Mazda3_Smart_Start_Guide.pdf" target="_blank"
						 style="color: #d6d6d6;text-decoration: underline;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2015' && #session.MdlCode == 'M6G' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2015_Mazda6_Smart_Start_Guide.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2015' && #session.MdlCode == 'CX5' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2015_Mazda_CX5_Smart_Start_Guide.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2015' && #session.MdlCode == 'M3H' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2015_Mazda3_Smart_Start_Guide.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2015' && #session.MdlCode == 'MZ5' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2015_Mazda5_Smart_Start_Guide.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2015' && #session.MdlCode == 'MX5' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2015_Mazda_MX5_Smart_Start_Guide.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2015' && #session.MdlCode == 'CX9' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2015_Mazda_CX9_Smart_Start_Guide.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2014' && #session.MdlCode == 'MZ2' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2014_MZ2_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2014' && #session.MdlCode == 'MZ5' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2014_MAZDA5_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2014' && #session.MdlCode == 'M3S' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2014_MAZDA3_4Doors_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2014' && #session.MdlCode == 'CX5' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2014_CX5_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2014' && #session.MdlCode == 'M3H' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2014_MAZDA3_5Doors_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2014' && #session.MdlCode == 'M6G' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2014_Mazda6_Smart_Start_Guide.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2014' && #session.MdlCode == 'MX5' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2014_MX5_Miata_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2014' && #session.MdlCode == 'CX9' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2014_CX9_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2013' && #session.MdlCode == 'MZ2' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2013_MZ2_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2013' && #session.MdlCode == 'M3S' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2013_MAZDA3_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2013' && #session.MdlCode == 'M3H' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2013_MAZDA3_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2013' && #session.MdlCode == 'MZ5' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2013_MZ5_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2013' && #session.MdlCode == 'M3S' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2013_MZ6_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2013' && #session.MdlCode == 'MZ6' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2013_MZ6_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2013' && #session.MdlCode == 'MX5' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2013_MX5_Miata_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2013' && #session.MdlCode == 'MXR' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2013_MX5_Miata_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2013' && #session.MdlCode == 'MS3' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2013_MAZDA3_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2013' && #session.MdlCode == 'CX5' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2013_CX5_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2013' && #session.MdlCode == 'CX9' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2013_CX9_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2012' && #session.MdlCode == 'MZ2' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2012_MAZDA2_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2012' && #session.MdlCode == 'M3S' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2012_Mazda3_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2012' && #session.MdlCode == 'M3H' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2012_Mazda3_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2012' && #session.MdlCode == 'MZ5' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2012_MZ5_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2012' && #session.MdlCode == 'MZ6' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2012_MZ6_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2012' && #session.MdlCode == 'MX5' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2012_MX5_Miata_QT.pdf"
						target="_blank"
						style="position: absolute; font-size: 21px; margin-left: 40px; margin-top: -30px;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide">Download Smart Start Guide</a>
				</s:if>
				<s:if
					test="#session.year == '2012' && #session.MdlCode == 'MS3' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2012_MAZDASPEED3_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2012' && #session.MdlCode == 'CX7' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2012_CX7_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2012' && #session.MdlCode == 'CX9' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2012_CX9_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2011' && #session.MdlCode == 'MZ2' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2011_MAZDA2_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2011' && #session.MdlCode == 'M3S' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2011_Mazda3_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2011' && #session.MdlCode == 'M3H' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2011_Mazda3_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2011' && #session.MdlCode == 'RX8' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2011_RX8_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2011' && #session.MdlCode == 'MZ6' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2011_MZ6_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2011' && #session.MdlCode == 'MX5' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2011_MX5_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2011' && #session.MdlCode == 'MXR' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2011_MX5_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2011' && #session.MdlCode == 'MS3' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2011_MAZDASPEED3_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2011' && #session.MdlCode == 'CX7' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2011_CX7_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2011' && #session.MdlCode == 'CX9' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2011_CX9_QT.pdf"  target="_blank"
						 style="color: #d6d6d6;"data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2011' && #session.MdlCode == 'TRB' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2011_TRIBUTE_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2010' && #session.MdlCode == 'M3S' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2010_MAZDA3_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2010' && #session.MdlCode == 'M3H' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a 
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2010_MAZDA3_QT.pdf"  target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2010' && #session.MdlCode == 'MZ5' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2010_MAZDA5_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2010' && #session.MdlCode == 'MZ6' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2010_MAZDA6_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2010' && #session.MdlCode == 'MX5' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2010_MX5_Miata_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2010' && #session.MdlCode == 'MXR' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2010_MX5_Miata_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2010' && #session.MdlCode == 'RX8' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2010_RX8_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" ><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2010' && #session.MdlCode == 'MS3' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2010_MAZDASPEED3_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2010' && #session.MdlCode == 'CX7' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2010_CX7_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2010' && #session.MdlCode == 'CX9' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2010_CX9_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2009' && #session.MdlCode == 'M3S' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2009_MAZDA3.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2009' && #session.MdlCode == 'M3H' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2009_MAZDA3.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2009' && #session.MdlCode == 'MZ5' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2009_MAZDA5.pdf"
						target="_blank"
						style="position: absolute; font-size: 21px; margin-left: 40px; margin-top: -30px;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide">Download Smart Start Guide</a>
				</s:if>
				<s:if
					test="#session.year == '2009' && #session.MdlCode == 'MZ6' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2009_MAZDA6.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2009' && #session.MdlCode == 'MX5' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2009_MX5_Miata_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2009' && #session.MdlCode == 'MXR' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2009_MX5_Miata_QT.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2009' && #session.MdlCode == 'RX8' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2009_RX8.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2009' && #session.MdlCode == 'MS3' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2009_MAZDA3.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2009' && #session.MdlCode == 'CX7' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2009_CX7.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2009' && #session.MdlCode == 'CX9' ">
					<img src="view/vhelper/images/pdficon"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2009_CX9.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2009' && #session.MdlCode == 'TRB' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2009_TRIBUTE.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2009' && #session.MdlCode == 'TRH' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2009_TRIBUTE.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2009' && #session.MdlCode == 'B23' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2009_BSeries.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
				<s:if
					test="#session.year == '2009' && #session.MdlCode == 'B40' ">
					<img src="view/vhelper/images/pdficon.png"></img>
					<a
						href="https://www.mymazda.com/MusaWeb/pdf/smartguides/2009_BSeries.pdf" target="_blank"
						 style="color: #d6d6d6;" data-analytics-link-component-name="demos_and_guides" data-analytics-link-type="button" data-analytics-link-description="smart_start_guide"><span class="brand-underline"><span class="brand-link">Download the Smart Start Guide</span></span></a>
				</s:if>
			</s:if>
		</div>
	</div>
</div>
<div class="Select-video" id="selectVideo">
	<div class="container padding-0 brand-bmargin50">
		<div class="row Select-result margin-0">
			<div class="col-xs-12 col-sm-4">
				<div id="searchBarDiv" class="search-field">
					<input type="search" class="form-control inputsearchkey" id="squeryId"
						placeholder="Search for a topic" data-analytics-link-component-name="topic_search" data-analytics-link-type="submit" data-analytics-link-description="submit"/> <i class="fa fa-search" onclick="return findHowtoSearch();"></i>
				</div>
				<div class="clearfix"></div>
				<div id="searchMenu" class="vertical-menu">
					<div id="searchDiv"></div>
				</div>
				<div class="clearfix"></div>
				<div id="MainMenu" class="vertical-menu">
					<div id="listingDiv">
						<s:if test="#session.loginUser != null">
							<s:include value="HowToVehicleDetail.jsp"></s:include>
						</s:if>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-8 video-div">
			<div id="videoTitle"  style="display: none;">
			<h2 id="searchedTitle" style="padding:0 15px 20px 15px !important;"></h2>
			</div>
			<div id="videoContent">
				 <s:if test="#session.loginUser != null">
				 <div class="row margin-0">
							<s:if
								test="#session.howtoFilterList == null || #session.howtoFilterList.size==0">
								<div class="col-xs-12 col-sm-6"><div class="No-Videostext">Sorry, no videos found for this vehicle.</div></div>
							</s:if>							
							<s:if test="#session.howtoFilterList != null">
								<s:iterator value="#session.howtoFilterList" status="liCount">
									<s:if test="streemURL != null">
										<div class="col-xs-12 col-sm-6 ">
										  <div class="video-div-box">
											<a href="#"	onclick="return myFunctionVideo('<s:property value="streemURL"/>','<s:property value="title"/>')" data-analytics-link-component-name="table" data-analytics-link-type="button" data-analytics-link-description="<s:property value="title"/>">
											<img class="img-responsive"	src="view/vhelper/images/findmyvin_image.png" alt=""></a>
											<h3>
												<s:property value="title" />
											</h3>					
											</div>					
										</div>

									</s:if>
								</s:iterator>
							</s:if>

						</div>	

				</s:if>
				</div>
            </div>
			</div>
		</div>
	</div>
</div>
		
<script type="text/javascript">_satellite.pageBottom();</script>	
</body>
</html>
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
	
	 $(".vediosearchkey").keyup(function (e) {
	    if (e.keyCode == 13) {
	    	vediosearchDealers();
			return false;
	    } 
	});
	
});
$(function(){
	/* $('#squeryId').keyup(function(){
	    this.value = this.value.toUpperCase();
	}); */
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
function vediosearchDealers() {
	add='';
	edit='';
	registersearch='';
	var data = {};
	
	var dealerSearch = $("#vedioSearchDealer").val();
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