<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="HeaderJs.jsp"></jsp:include>

<div class="Menu-Section">
	<!--Menu section Start-->
	<div class="container">
		<div class="logo-div">
			<s:if test="#session.loginUser != null">
				<s:if test="#session.vehicalsList.size() >0">
					<a href="" onclick="return homeClick();"><img class="logo-desktop"
						src="view/vhelper/images/Mazda-Logo_Desktop.png" alt="" width="90"></a>
					<a href="" onclick="return homeClick();"><img
						class="img-responsive logo-mobile-xs"
						src="view/vhelper/images/Mazda-Logo_Mobile.png" alt=""></a>
				</s:if>
				<s:else>
					<a href="#"><img class="logo-desktop"
						src="view/vhelper/images/Mazda-Logo_Desktop.png" alt="" width="90"></a>
					<a href="#"><img class="img-responsive logo-mobile-xs"
						src="view/vhelper/images/Mazda-Logo_Mobile.png" alt=""></a>
				</s:else>
			</s:if>
			<s:else>
				<a href="index.jsp"><img class="logo-desktop"
					src="view/vhelper/images/Mazda-Logo_Desktop.png" alt="" width="90"></a>
				<a href="index.jsp"><img class="img-responsive logo-mobile-xs"
					src="view/vhelper/images/Mazda-Logo_Mobile.png" alt=""></a>
			</s:else>
		</div>
		
		<div class="nav-div">
					<s:if test="#session.loginUser != null">
						<div class="searchfield-div">
							<div class="search-field">
								<input type="search" id="headsearchinput" class="form-control searchkey" 
										placeholder="Locate Nearest Dealer (Enter Zip Code)" data-analytics-link-component-name="mymz-globalnav" data-analytics-link-type="nav" data-analytics-link-description="locate_dealer"/><i
										class="fa fa-map-marker" onclick="return headsearchDealer();"
										style="cursor: pointer;"></i>
										
									<div class="dropdown loggedin-myprofile">
										  <!-- <button class="dropbtn">MY PROFILE</button> -->
										  <div class="dropbtn my-profile-icon"></div>
										  <div class="dropdown-content dropdown-links-myprofile">
										    <a href="javaScript:;" class="dropdown-link"
													onclick="return updateInformation();">EDIT ACCOUNT INFO</a>
										    <a href="javaScript:;" class="dropdown-link"
													onclick="return changePassword();">CHANGE PASSWORD</a>
											<a href="mymazdaHome.action" class="dropdown-link">LOGOUT</a>
										  </div>
									</div>
							</div>
						</div>
					</s:if>
					<s:else>
						<div class="searchfield-div-pre">
							<div class="search-field-pre">
								<input type="search" id="headsearchinput" class="form-control searchkey" 
										placeholder="Locate Nearest Dealer (Enter Zip Code)" data-analytics-link-component-name="mymz-globalnav" data-analytics-link-type="nav" data-analytics-link-description="locate_dealer"/><i
										class="fa fa-map-marker" onclick="return headsearchDealer();"
										style="cursor: pointer;"></i>
							</div>
						</div>
					</s:else>
					
			<nav class="nav nav-section" role="navigation">
				<div class="nav-toggle">
					<div class="icon-menu">
						<i class="fa fa-navicon"></i>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="nav-container">
					<div class="container">
						<div class="scroll-div">
							<ul class="nav-menu menu mid-menu-links">
							
								<s:if test="#session.loginUser != null">
									<li class="menu-item has-dropdown my-profile menu-item-1" id="myprofile_x"><a
										href="#" class="menu-link menu-item-1-a">MY PROFILE</a>
										<ul class="nav-dropdown menu submenu-list5">
											<li class="menu-item side-menu-close" id="updateinformation"><a
												href="javaScript:;" class="menu-link"
												onclick="return updateInformation();">Edit Account Information</a></li>
											<li class="menu-item side-menu-close" id="changePassword"><a
												href="javaScript:;" class="menu-link"
												onclick="return changePassword();">Change Password</a></li>
										</ul>
									</li>
								</s:if>
								
								<li class="menu-item has-dropdown mid-menu-drop1 menu-item-2" id="myvehicles"><a 
										href="#!" class="menu-link letter-space-2 menu-item-2-a" data-analytics-link-component-name="mymz-globalnav" data-analytics-link-type="nav" data-analytics-link-description="my_vehicles">MY VEHICLES</a>
									<ul class="nav-dropdown menu" id="vehicle">
										<s:iterator value="#session.vehicalsList" status="count"
											var="carlist">
											<div id="vehicleList">
												<li class="menu-item carlistmenu side-menu-close" id="carselector"
													onclick="return applyToUpdateSelectedVehical('<s:property value="vin"/>');">
													<a
													href="javaScript:;"
													class="menu-link" data-analytics-link-component-name="mymz-globalnav" data-analytics-link-type="nav" data-analytics-link-description="<s:property value="carlineDesc"/>"><s:property value="carlineDesc" />
												</a>
												</li>
											</div>
										</s:iterator>
										<s:if test="#session.loginUser != null">
											<li class="menu-item side-menu-close" id="addVehicle"><a
												href="javaScript:;" onclick="return addMoreVehicalform();"
												class="menu-link"  data-analytics-link-component-name="mymz-globalnav" data-analytics-link-type="nav" data-analytics-link-description="add_new_vehicle">Add New Vehicle</a></li>
										</s:if>
										<s:else>
											<li class="menu-item side-menu-close" id="signin"><a href="/" onclick=" return getFocus();"
												class="menu-link ">Sign in to View</a></li>
										</s:else>
									</ul>
								</li>
								
								<li class="menu-item has-dropdown mid-menu-drop menu-item-3" id="owneresources"><a
									href="#!" class="menu-link letter-space-2 menu-item-3-a" data-analytics-link-component-name="mymz-globalnav" data-analytics-link-type="nav" data-analytics-link-description="owner_resources">OWNER RESOURCES</a>
									<ul class="nav-dropdown menu external-links owner-dropdown">
										<li class="menu-item sun-m-1 side-menu-close"><a
											href="#" onclick="return videoDemos();"
											target="_blank" class="menu-link M-vehicle" data-analytics-link-component-name="mymz-globalnav" data-analytics-link-type="nav" data-analytics-link-description="vehicle_demos&guides">Vehicle Demos & Guides</a>
										</li>
										<li class="menu-item sun-m-4 side-menu-close"><a
											href="http://infotainment.mazdahandsfree.com/vehicle_select?return_to=%2Fhome%3Flanguage%3Den-US"
											class="menu-link" target="_blank" data-analytics-link-component-name="mymz-globalnav" data-analytics-link-type="nav" data-analytics-link-description="mazda_connect_bluetooth">Mazda Connect&trade; / Bluetooth<sup>&reg;</sup>
										</a></li>
										<li class="menu-item sun-m-2 side-menu-close"><a
											href="http://www.mazdausa.com/MusaWeb/displayManualsByModelAndYearHome.action"
											class="menu-link M-manuals" target="_blank" data-analytics-link-component-name="mymz-globalnav" data-analytics-link-type="nav" data-analytics-link-description="manuals&references">Manuals & References</a></li>
										<li class="menu-item sun-m-5 side-menu-close"><a
											href="http://www.siriusxm.com/sxm-learnmore?utm_campaign=OEM_New_BAU&utm_source=NA_NA_Mazda-Owner-Micro-LM&utm_medium=Affiliate"
											class="menu-link" target="_blank" data-analytics-link-component-name="mymz-globalnav" data-analytics-link-type="nav" data-analytics-link-description="siriusxm_satellite_radio">SiriusXM Satellite Radio</a></li>
										<li class="menu-item sun-m-3 side-menu-close"><a
											href="http://www.mazdausa.com/MusaWeb/displayPage.action?pageParameter=FAQ"
											class="menu-link M-faqs" target="_blank" data-analytics-link-component-name="mymz-globalnav" data-analytics-link-type="nav" data-analytics-link-description="faqs">FAQs</a></li>
										<li class="menu-item sun-m-6 side-menu-close"><a
											href="https://www.mazdausa.com/about-mazda/mobile-apps"
											class="menu-link M-MyMazda" target="_blank" data-analytics-link-component-name="mymz-globalnav" data-analytics-link-type="nav" data-analytics-link-description="mymazda_mobileapp">MyMazda Mobile App</a></li>
										<li class="menu-item sun-m-3 side-menu-close"><a
											href="http://mazdanav.com/"
											class="menu-link M-gps" target="_blank" data-analytics-link-component-name="mymz-globalnav" data-analytics-link-type="nav" data-analytics-link-description="gps_navigation_updates">GPS Navigation Updates</a></li>
										<!--<div id="mktplace"><li class="menu-item sun-m-6 side-menu-close"><a
											href="http://mazdamarketplace.com/" class="menu-link"
											target="_blank">Mazda Marketplace</a></li></div>-->
										<li class="menu-item side-menu-close"><a
											href="http://www.mazdausa.com/MusaWeb/displayRecallOwners.action"
											class="menu-link" target="_blank" data-analytics-link-component-name="mymz-globalnav" data-analytics-link-type="nav" data-analytics-link-description="recalls">Recalls</a></li>
									</ul>
								</li>
								
								<li class="menu-item has-dropdown mid-menu-drop2 menu-item-4"><a
									href="#!" class="menu-link letter-space-2 menu-item-4-a" data-analytics-link-component-name="mymz-globalnav" data-analytics-link-type="nav" data-analytics-link-description="service_parts">SERVICE & PARTS</a>
									<ul class="nav-dropdown menu sr-dropmenu external-links">

										<li class="menu-item side-menu-close"><a
											href="http://accessories.mazdausa.com/webapp/wcs/stores/servlet/TopCategoriesDisplay?storeId=24601&catalogId=10001&catgroupId=16013&yearSelected=2014&top=y&parent_category_rn=&categoryId=16013"
											class="menu-link M-accessories" target="_blank" data-analytics-link-component-name="mymz-globalnav" data-analytics-link-type="nav" data-analytics-link-description="accessories">Accessories</a></li>
										<li class="menu-item side-menu-close"><a
											href="http://www.mazdausa.com/MusaWeb/displayPage.action?pageParameter=ownersPartsFullCircle"
											class="menu-link M-fullcircle" target="_blank" data-analytics-link-component-name="mymz-globalnav" data-analytics-link-type="nav" data-analytics-link-description="mazda_fullcircle_serviceexperience">Mazda Full Circle Service Experience</a></li>
										<li class="menu-item side-menu-close"><a
											href="http://www.mazdausa.com/MusaWeb/displayPage.action?pageParameter=ownersParts"
											class="menu-link M-genuine" target="_blank" data-analytics-link-component-name="mymz-globalnav" data-analytics-link-type="nav" data-analytics-link-description="genuine_mazdaparts">Genuine Mazda Parts</a></li>
									</ul>
								</li>
								
								<li class="menu-item has-dropdown mid-menu-drop2 mid-menu-drop3 menu-item-5"><a
									href="#!" class="menu-link letter-space-2 menu-item-5-a" data-analytics-link-component-name="mymz-globalnav" data-analytics-link-type="nav" data-analytics-link-description="warranty">WARRANTY</a>
									<ul class="nav-dropdown menu warranty-dropmenu external-links">
										<li class="menu-item side-menu-close"><a
											href="http://www.mazdausa.com/MusaWeb/displayPage.action?pageParameter=ownersWarranty"
											class="menu-link M-carwarranty" target="_blank" data-analytics-link-component-name="mymz-globalnav" data-analytics-link-type="nav" data-analytics-link-description="mazda_newcarwarranty">Mazda New Car Warranty</a></li>										
										<li class="menu-item side-menu-close"><a
											href="http://www.mazdausa.com/MusaWeb/displayPage.action?pageParameter=shopExtendedConfidence"
											class="menu-link M-extened" target="_blank" data-analytics-link-component-name="mymz-globalnav" data-analytics-link-type="nav" data-analytics-link-description="mazda_extendedconfidence">Mazda Extended Confidence</a></li>
										<li class="menu-item side-menu-close"><a
											href="http://www.mazdausa.com/MusaWeb/displayRecallOwners.action"
											class="menu-link" target="_blank">Recalls</a></li>
										<li class="menu-item side-menu-close"><a
											href="https://www.mazdausa.com/certified-pre-owned/program-overview"
											class="menu-link" target="_blank" data-analytics-link-component-name="mymz-globalnav" data-analytics-link-type="nav" data-analytics-link-description="mazda_cpowarranty">Mazda CPO Warranty</a></li>
									</ul>
								</li>
								
								<li class="menu-item mymazda-link mazdausa-com"><a
									href="http://www.mazdausa.com/MusaWeb/displayHomepage.action"
									class="menu-link" target="_blank" data-analytics-link-component-name="mymz-globalnav" data-analytics-link-type="nav" data-analytics-link-description="mazdausa_com">MAZDAUSA.COM</a></li>
								<li class="menu-item fin-ser"><a
									href="https://www.chase.com/business-banking/dealer-services/mazda-capital-services"
									target="_blank" class="menu-link" data-analytics-link-component-name="mymz-globalnav" data-analytics-link-type="nav" data-analytics-link-description="financial_services">FINANCIAL SERVICES</a></li>
								
								<s:if test="#session.loginUser != null">
									<li class="menu-item md-logout mobile-logout" onclick="logout();"><a
											href="mymazdaHome.action" class="menu-link">LOGOUT<i
											class="fa fa-sign-out fa-rotate-180"></i></a></li>
								</s:if>
								
							</ul>
							<div class="footer-text external-links">
								<a href="http://www.mazdausa.com/MusaWeb/displayHomepage.action" target="_blank" class="brand-link-blackbg">
									<span class="brand-underline"><span class="brand-link">MAZDAUSA.COM</span></span></a>
								<a href="https://www.chase.com/business-banking/dealer-services/mazda-capital-services" target="_blank" class="M-financing brand-link-blackbg">
									<span class="brand-underline"><span class="brand-link">FINANCIAL SERVICES</span></span></a>
								<a href="http://www.mazdausa.com/MusaWeb/displayPage.action?pageParameter=mazdaMobile" target="_blank" class="M-nextmazda brand-link-blackbg">
									<span class="brand-underline"><span class="brand-link">Your Next Mazda</span></span></a>
								<a href="http://www.mazdausa.com/MusaWeb/displayPage.action?pageParameter=legalTermsMazda" target="_blank" class="brand-link-blackbg">
									<span class="brand-underline"><span class="brand-link">Terms & Conditions</span></span></a>
								<a href="http://www.mazdausa.com/MusaWeb/displayPage.action?pageParameter=legalPrivacyMazda" target="_blank" class="brand-link-blackbg">
									<span class="brand-underline"><span class="brand-link">Privacy/CA Policy</span></span></a>
								<p class="brand-text">
									Call Us <u>1-800-222-5500</u> 24/7<br>Roadside Assist <u>1-800-866-1998</u>
								</p>
								<p class="brand-text">Text "roadside" to 62932 (MAZDA) and have the number sent to your cell phone</p>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
			</nav>
			<div class="clearfix"></div>
		</div>
		<div class="bg-w" ></div>
		<div class="bg-w1"></div>
		<div class="bg-w2"></div>
		<div class="bg-w3"></div>
		<div class="clearfix"></div>
	</div>
</div>

<script>
    var isMobile = /iPhone|BlackBerry|IEMobile|Windows Phone|Android/i.test(navigator.userAgent);
    if(isMobile) {    	
    	$(".external-links .M-financing").attr("href", "${pageContext.request.contextPath}/view/common/MobileSupport.jsp");
		$(".external-links .M-nextmazda").attr("href", "${pageContext.request.contextPath}/view/common/MobileSupport.jsp");
		
		
		$(".external-links .M-carwarranty").attr("href", "${pageContext.request.contextPath}/view/common/MobileSupport.jsp");
		$(".external-links .M-extened").attr("href", "${pageContext.request.contextPath}/view/common/MobileSupport.jsp");
		$(".external-links .M-vehicle").attr("href", "${pageContext.request.contextPath}/view/common/MobileSupport.jsp");
		
		$(".external-links .M-manuals").attr("href", "${pageContext.request.contextPath}/view/common/MobileSupport.jsp");
		$(".external-links .M-faqs").attr("href", "${pageContext.request.contextPath}/view/common/MobileSupport.jsp");
		$(".external-links .M-MyMazda").attr("href", "${pageContext.request.contextPath}/view/common/MobileSupport.jsp");
		$(".external-links .M-gps").attr("href", "${pageContext.request.contextPath}/view/common/MobileSupport.jsp");
		
		$(".external-links .M-accessories").attr("href", "${pageContext.request.contextPath}/view/common/MobileSupport.jsp");
		$(".external-links .M-fullcircle").attr("href", "${pageContext.request.contextPath}/view/common/MobileSupport.jsp");
		$(".external-links .M-genuine").attr("href", "${pageContext.request.contextPath}/view/common/MobileSupport.jsp");
	} 
</script>
