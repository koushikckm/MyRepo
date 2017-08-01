<%@ taglib prefix="s" uri="/struts-tags"%>

<jsp:include page="../common/Header.jsp" />	
<jsp:include page="DataLayerJs.jsp"></jsp:include>
<jsp:include page="HomeMainBodyJs.jsp"></jsp:include>

<script type="text/javascript">
	$(document).ready(function() {
		dataLayer.page.name = 'overview';
		dataLayer.page.nameHistorical = 'musa:owners_mymazda_myvehicle';
		dataLayer.page.subCategory = 'owners_mymazda_myvehicle';
		dataLayer.page.URL = window.location.href;
		dataLayer.site.sectionHistorical = 'owners';
		dataLayer.site.section = 'mymazda';
		dataLayer.site.subsection = 'owners';
		dataLayer.form.name = 'login';
		dataLayer.form.type = 'login';
		dataLayer.user.type = 'x';
	});
</script>
	
<div class="overlay"></div>
	
<div class="Login-Page" >
		<!--Container Start-->
		<div class="Slider-Img text-center">
			<div class="container">
				<div class="search-field1">
					<input type="search" class="form-control searchkeyhomebody" id="homesearch"
						placeholder="Locate Nearest Dealer (Enter Zip Code)"
						 /> <i class="fa fa-map-marker" onclick="return homesearchDealers();" style="cursor:pointer;"></i>
				</div>
				<div class="login-top-title">MYMAZDA</div>
				<div class="login-top-subtitle">PUSHING THE START BUTTON IS JUST THE BEGINNING</div>
				<div class="login-top-text">The excitement of owning a Mazda doesn't fade away with the
					new car smell. MyMazda makes ownership hassle-free with
					personalized service offers, service reminders, service history and more.  We'll handle the paperwork so you can focus on the
					exhilaration ahead.</div>		
							
			</div>
		</div>
		
		<!--Signup section Start-->
		<div class="Signup-Div">
			<!--Register and Login-->
			<div class="container">
				<div class="row margin-0">
					<div class="col-md-6 col-sm-6 col-xs-12 padding-0">
						<div class="signup-section text-left">
							<div class="brand-header brand-tmargin30 brand-bmargin20">REGISTER</div>
							<div class="brand-title brand-size14px brand-bmargin20 brand-lpadding40 ser-off">PERSONALIZED SERVICE OFFERS</div>
							<div class="brand-title brand-size14px brand-bmargin20 brand-lpadding40 ser-rem">SERVICE REMINDERS</div>
							<div class="brand-title brand-size14px brand-bmargin30 brand-lpadding40 ser-his">SERVICE HISTORY</div>
							<div class="brand-bmargin30"><a href="javaScript:;" onclick="return register();" type="button"
								class="btn btn-default btn-gray brand-button-white" data-analytics-link-component-name="table" data-analytics-link-type="cta" data-analytics-link-description="join_my_mazda">JOIN MY MAZDA</a></div>
						</div>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-12 padding-0">
						<div class="signin-section text-left">
							<div class="brand-header brand-tmargin30 brand-bmargin20">SIGN IN</div>
							<div class="brand-title brand-size14px brand-bmargin20">PLEASE SIGN IN TO ACCESS YOUR VEHICLE INFORMATION.</div>
							<form id="enquiryFormId" role="form" 
									class="row loginform-div margin-0">
								<s:if test="#session.CheckboxRegistrationTO != null">
									<div
										class="form-group col-md-6 col-sm-6 col-xs-12 margin-0 padding-0 email-div brand-bmargin20" id="emailerror_layout">
										<label for="email" class="email"><span class="brand-title brand-size13px">EMAIL</span></label> <input
											type="email" class="form-control" id="email"
											placeholder="EMAIL"
											value="<s:property value="#session.CheckboxRegistrationTO.email"/>">
											<span class="error_mssg" id="mailerror"></span>
									</div>
									<div
										class="form-group col-md-6 col-sm-6 col-xs-12 margin-0 padding-0 password-field brand-bmargin20" id="passworderror_layout">
										<label for="pwd" class="psw"><span class="brand-title brand-size13px">PASSWORD</span></label> <input
											type="password" class="form-control" id="pwd"
											placeholder="PASSWORD"
											value="<s:property value="#session.CheckboxRegistrationTO.password"/>">
											<span class="error_mssg" id="passworderror"></span>
									</div>
									<div class="clearfix"></div>
									<div class="checkbox brand-bmargin20">
										<input type="checkbox" value="1" id="checkId" class="styled" checked="checked">
										<label><span class="brand-title brand-size13px">Remember Me?</span><span class="terms-break"><br/></span><a href="#" data-toggle="modal" class="forgot-pwd"
											data-target="#forgot-password" onclick="return cleanPassword();" data-analytics-link-component-name="form" data-analytics-link-type="button" data-analytics-link-description="forgot_password"><span class="brand-underline"><span class="brand-link">Forgot Password</span></span></a>
										</label>
									</div>
								</s:if>
								<s:else>
									<div
										class="form-group col-md-6 col-sm-6 col-xs-12 margin-0 padding-0 email-div brand-bmargin20" id="emailerror_layout">
										<label for="email" class="email"><span class="brand-title brand-size13px">EMAIL</span></label> <input
											type="email" class="form-control" id="email"
											placeholder="EMAIL">
											<span class="error_mssg" id="mailerror"></span>
									</div>
									<div
										class="form-group col-md-6 col-sm-6 col-xs-12 margin-0 padding-0 password-field brand-bmargin20" id="passworderror_layout" >
										<label for="pwd" class="psw"><span class="brand-title brand-size13px">PASSWORD</span></label> <input
											type="password" class="form-control" id="pwd"
											placeholder="PASSWORD">
											<span class="error_mssg" id="passworderror"></span>
									</div>
									<div class="clearfix"></div>
									<div class="checkbox brand-bmargin20">
											<input type="checkbox" id="checkId">
											<label><span class="brand-title brand-size13px">Remember Me?</span><span class="terms-break"><br/></span><a href="#" data-toggle="modal" class="forgot-pwd"
													data-target="#forgot-password" onclick="cleanPassword('reg');" data-analytics-link-component-name="form" data-analytics-link-type="button" data-analytics-link-description="forgot_password"><span class="brand-underline"><span class="brand-link">Forgot Password</span></span></a>
											</label>
									</div>
								</s:else>
								<button onclick="return login();" type="submit"
									class="btn btn-default btn-gray brand-button-white" data-analytics-link-component-name="form" data-analytics-link-type="submit" data-analytics-link-description="login">SIGN IN</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--Signup section End-->
		
		<div class="Products-Div text-center">
            <!--Product section Start-->
            <div class="container">
            	
            	<!-- 3 small images in a row -->
            	<div class="row margin-0">
                   <div class="col-sm-4 row-padding">
                      <div class="row">
                         <div class="col-xs-12 col-sm-12 Video-Box" style="padding: 0 !important;">
                            <a class="MobileError" href="#" onclick="return videoDemos();" target="_blank" data-analytics-link-component-name="top_block" data-analytics-link-type="image" data-analytics-link-description="vehicle_demos_guides"><img 
                            	class="img-responsive" src="view/vhelper/images/videosdemos.png" alt="Mazda"></a>
                         </div>
                         <div class="col-xs-12 col-sm-12 Video-Txt" style="padding: 30px 30px 50px 30px !important; text-align: center;">
                            <div class="brand-bmargin20"><a class="MobileError" href="#" onclick="return videoDemos();" target="_blank" data-analytics-link-component-name="copy" data-analytics-link-type="button" data-analytics-link-description="vehicle_demos_guides"><span
                            	class="brand-header">VEHICLE DEMOS & GUIDES</span></a></div>
                            <p>Learn more about your Mazda's features and conveniences.</p>                            
                         </div>
                      </div>
                   </div>
                   <div class="col-sm-4 row-padding">
                      <div class="row">
                         <div class="col-xs-12 col-sm-12 Video-Box" style="padding: 0 !important;">
                            <a href="http://infotainment.mazdahandsfree.com/vehicle_select?return_to=%2Fhome%3Flanguage%3Den-US" target="_blank" data-analytics-link-component-name="top_block" data-analytics-link-type="image" data-analytics-link-description="mazda_connect_bluetooth"><img 
                            	class="img-responsive" src="view/vhelper/images/insidemazda.png" alt="Mazda"></a>
                         </div>
                         <div class="col-xs-12 col-sm-12 Video-Txt" style="padding: 30px 30px 50px 30px !important; text-align: center;">
                            <div class="brand-bmargin20"><a href="http://infotainment.mazdahandsfree.com/vehicle_select?return_to=%2Fhome%3Flanguage%3Den-US" target="_blank" data-analytics-link-component-name="copy" data-analytics-link-type="button" data-analytics-link-description="mazda_connect_bluetooth"><span 
                            	class="brand-header">MAZDA CONNECT&trade; / BLUETOOTH<sup>&reg;</sup></span></a></div>      
                            <p>Explore available MAZDA CONNECT&trade; infotainment&#42; features.</p>                      
                         </div>
                      </div>
                   </div>
                   <div class="col-sm-4 row-padding">
                      <div class="row">
                         <div class="col-xs-12 col-sm-12 Video-Box" style="padding: 0 !important;">
                            <a href="http://www.mazdausa.com/MusaWeb/displayRecallOwners.action" target="_blank" data-analytics-link-component-name="top_block" data-analytics-link-type="image" data-analytics-link-description="find_open_recalls"><img 
                            	class="img-responsive" src="view/vhelper/images/recall.png" alt="Mazda"></a>
                         </div>
                         <div class="col-xs-12 col-sm-12 Video-Txt" style="padding: 30px 30px 50px 30px !important; text-align: center;">
                            <div class="brand-bmargin20"><a href="http://www.mazdausa.com/MusaWeb/displayRecallOwners.action" target="_blank" data-analytics-link-component-name="copy" data-analytics-link-type="button" data-analytics-link-description="find_open_recalls"><span 
                            	class="brand-header">FIND OPEN RECALLS</span></a></div>  
                            <p>With vin look-up, find open recalls or special services available specifically for your Mazda.</p>                          
                         </div>
                      </div>
                   </div>                   
                </div>
                
                <!-- 1 big image in a row -->
                <div class="row margin-0">
                   <div class="col-sm-12 row-padding">
                      <div class="row">
                         <div class="prod4" style="padding: 30px 30px 50px 30px !important; text-align: center;">
                         	<div class="divTable" style="padding-top: 150px;"><div class="divRow">
                         	<div class="divCellLeft" style="width: 50%;">
	                            <div class="brand-bmargin20"><a href="https://www.mazdausa.com/about-mazda/mobile-apps" target="_blank"><span 
	                            	class="brand-header" style="color: #fff !important;">MYMAZDA MOBILE APP</span></a></div>
	                            <p>Access your vehicle's maintenance schedule, coupons, interactive guides and more.</p>
	                            <div class="brand-tmargin20"><a href="https://www.mazdausa.com/about-mazda/mobile-apps" target="_blank"
									class="btn btn-default btn-gray brand-button-black" data-analytics-link-component-name="hero" data-analytics-link-type="button" data-analytics-link-description="mobile_app_learn_more">LEARN MORE</a></div>
                            </div></div></div>                        
                         </div>
                      </div>
                   </div>
                </div>
                <div class="row margin-0">
                   <div class="col-sm-12 row-padding">
                      <div class="row">
                         <div class="col-xs-12 col-sm-12 Video-Txt prod5" style="padding: 30px 30px 50px 30px !important; text-align: center;">
                         	<div class="divTable" style="padding-top: 150px;"><div class="divRow">
                         	<div class="accessories-div" style="width: 50%;">
	                            <div class="brand-bmargin20"><a href="http://accessories.mazdausa.com/webapp/wcs/stores/servlet/TopCategoriesDisplay?storeId=24601&catalogId=10001&catgroupId=16013&yearSelected=2014&top=y&parent_category_rn=&categoryId=16013" target="_blank"><span 
	                            	class="brand-header" style="color: #fff !important;">BUY MAZDA ACCESSORIES</span></a></div>      
	                            <p>Shop for accessories to personalize your Mazda.</p>
	                            <div class="brand-tmargin20"><a href="http://accessories.mazdausa.com/webapp/wcs/stores/servlet/TopCategoriesDisplay?storeId=24601&catalogId=10001&catgroupId=16013&yearSelected=2014&top=y&parent_category_rn=&categoryId=16013" target="_blank"
									class="btn btn-default btn-gray brand-button-black" data-analytics-link-component-name="hero" data-analytics-link-type="button" data-analytics-link-description="mazda_accessories_explore">EXPLORE</a></div>
	                        </div></div></div>                      
                         </div>
                      </div>
                   </div>
                </div>
               <!-- <div class="row margin-0">
                   <div class="col-sm-12 row-padding">
                      <div class="row">
                         <div class="col-xs-12 col-sm-12 Video-Txt prod6" style="padding: 30px 30px 50px 30px !important; text-align: center;">
                         	<div class="divTable" style="padding-top: 150px;"><div class="divRow">
                         	<div class="divCellLeft" style="width: 50%;">
	                            <div class="brand-bmargin20"><a href="http://mazdamarketplace.com/" target="_blank"><span 
	                            	class="brand-header" style="color: #fff !important;">MAZDA MARKETPLACE</span></a></div>  
	                            <p>Shop for Mazda branded gifts and apparel.</p>
	                            <div class="brand-tmargin20"><a href="http://mazdamarketplace.com/" target="_blank"
									class="btn btn-default btn-gray brand-button-black">EXPLORE</a></div>
                            </div></div></div>                           
                         </div>
                      </div>
                   </div>                   
                </div>-->
                
            </div>
        </div>
        <!--Product section End-->
        <div class="More-Mazda margin-0 text-center">
            <!--moremazda section start-->
            <div class="container">
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
		<!--moremazda section end-->
		
</div>
<!--Container End-->
<script>
    var isMobile = /iPhone|BlackBerry|IEMobile|Windows Phone|Android/i.test(navigator.userAgent);
    if(isMobile) {    	
    	$(".M-MyMazdaBanner").attr("href", "${pageContext.request.contextPath}/view/common/MobileSupport.jsp");
    	}
</script>
