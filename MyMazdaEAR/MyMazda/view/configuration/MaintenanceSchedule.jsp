<%@ taglib prefix="s" uri="/struts-tags"%>

<jsp:include page="../common/Header.jsp" />	
<jsp:include page="/view/common/DataLayerJs.jsp"></jsp:include>

<script type="text/javascript">
	$(document).ready(function() {
		dataLayer.page.name = 'maintenance_schedule';
		dataLayer.page.nameHistorical = 'musa:owners_mymazda_maintenance_schedule';
		dataLayer.page.subCategory = 'owners_mymazda_maintenance_schedule';
		dataLayer.page.URL = window.location.href;
		dataLayer.site.sectionHistorical = 'owners';
		dataLayer.site.section  = 'mymazda';
		dataLayer.site.subsection = 'service';
		dataLayer.user.type = 'o';
	});
</script>

	<div class="overlay"></div>
	<div class="container-fluid padding-0">
		<!--Container Start-->
		<div class="Menu2" id="menu1">
	<div class="container">
	<nav class="navbar navbar-default sub-menu2">
	<div class="container-fluid padding-0">
	<div class="navbar-header">
	<button type="button" class="navbar-toggle accordion-toggle collapsed vehicle-menu" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
	<i class="fa fa-plus-circle"></i>
	</button>
	<a href="#" class="summary" id="maintenance"  onclick="return homeClick();"><s:text name='#session.selectedVehical.carlineDesc'/></a>
	</div>
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	<ul class="nav navbar-nav">
	<li><a href="javaScript:;" onclick="return serviceHistory('<s:text name='#session.selectedVehical.vin'/>');" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="service_history"><span class="Menu2-no-divider"></span>SERVICE HISTORY</a></li>
	<li><a href="http://www.mazdausa.com/MusaWeb/displayManualsByModelAndYearHome.action" target="_blank" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="manuals_and_references"><span class="Menu2-divider"></span>Manuals & References</a></li>
	<li><a href="javaScript:;" target="_blank" onclick="return maintenanceSchedule();" class="Menu2-active" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="maintenance_schedule"><span class="Menu2-divider"></span>MAINTENANCE SCHEDULE</a></li>
	<li><a href="#" onclick="return videoDemosLogin();" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="vehicle_demos_guides"><span class="Menu2-divider"></span>Vehicle Demos & Guides</a></li>
	<s:if test="#session.egiftTab == 0 && #session.egiftTabOnSelectVeh ==0 && #session.egiftTabOnRelPage == 0 && #session.egiftTabOnMaintSchChange == 0">
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
		<div class="Maintenance-Page submenu-class">
			<div class="container">
				<div class="row margin-0">
					<h1 class="text-uppercase">maintenance schedule</h1>					
				</div>
				<div class="M-Container">
				 <iframe src ="https://consumer.xtime.com/menu/?variant=MAZDAUSA&make=MAZDA&show_age=false&locale=en_US"  border="0"  frameborder="no" width="780px" border="0" height="749px" ></iframe>
					<div class="row margin-0">
						<div class="col-sm-12 padding-0">
							<!-- <h2 class="text-uppercase">maintenance schedule</h2> -->
							<!-- <img src="view/vhelper/images/MaintenanceScheduleImage.png"
								class="img-responsive" alt="Cinque Terre" width="304"
								height="236"> -->
								
						</div>						
					</div>					
				</div>
			</div>
		</div>
	</div>
	<!--Container End-->
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
	</script>