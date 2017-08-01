
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="UTF-8" />
<title>MyMazda</title>
<meta content="xenia - responsive and retina ready template"
	name="description" />
<meta content="width=device-width, initial-scale=1, maximum-scale=1"
	name="viewport" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="view/vhelper/images/ico_mazda.ico">
<link href='view/vhelper/css/style.css' rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="view/JQuery Validation Engine_files/validationEngine.jquery.css" type="text/css"></link>
<link rel="stylesheet" href="view/JQuery Validation Engine_files/template.css" type="text/css"></link>
<link href="view/vhelper/css/menu.css" rel="stylesheet" type="text/css" />
<link href="view/vhelper/css/brand.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="view/vhelper/css/bootstrap.min.css">
<link rel="stylesheet" href="view/vhelper/css/bootstrap-datetimepicker.css">
<link rel="stylesheet" href="view/vhelper/css/font-awesome.min.css">


<!-- JS FILES -->
<script type="text/javascript" src="./view/vhelper/js/jquery.min.js"></script>
<script type="text/javascript" src="./view/vhelper/js/moment-with-locales.js"></script>
<script type="text/javascript" src="./view/vhelper/js/bootstrap.min.js"></script>

<script type="text/javascript" src="./view/vhelper/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="./view/vhelper/js/common.js"></script>
<script type="text/javascript" src="./view/vhelper/js/bootstrap-tabcollapse.js"></script>

<script type="text/javascript" src="view/JQuery Validation Engine_files/jquery.validationEngine-en.js"></script>
<script	src="view/JQuery Validation Engine_files/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="./view/vhelper/js/ddaccordion.js"></script>

<!-- <script src="//assets.adobedtm.com/0b1a9905a3bfe56ba99f4bcd3560048b3d9faf5f/satelliteLib-4332df72469fbed611c92423a310658ec4352e71.js"></script>  -->
<script src="//assets.adobedtm.com/0b1a9905a3bfe56ba99f4bcd3560048b3d9faf5f/satelliteLib-9dac536f1ca76627327a770a7af6d1ac98184c84-staging.js"></script>
</head>


<body>
	<!-- Starts :: Header -->

	<div class="container-fluid padding-0">            
			<%-- <tiles:insertAttribute name="header" /> --%>
			<!--Start ::  Main body part -->
			<div class="alert_layout" style="display:none" id="sucessMsg"><div class="alert_inner"></div></div>
			<div id="container-fluid">
				<tiles:insertAttribute name="body" />
			</div>
	</div>
			<!-- Footer -->

      <div id='loadingmessage' class="loading"  style="display:none"><div class="ajaxloader"><div class="arc"></div></div></div>
			<footer class="section5">
				<tiles:insertAttribute name="footer" />
			</footer>
			<!-- End :: Footer -->
			<!-- End :: Main body part -->
	

	<script>
	$('.required-input').on('blur', function() {
		if($(this).val().trim() == '') {
			$(this).addClass('required-alert');
			$(this).attr("title", "This is required");
		} else {
			$(this).removeClass('required-alert');
			$(this).attr("title", "");
		}
	});
	
		$(document)
				.ready(
						function() {
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
							//stick in the fixed 100% height behind the navbar but don't wrap it
							$('#slide-nav.navbar-inverse')
									.after(
											$('<div class="inverse" id="navbar-height-col"></div>'));

							$('#slide-nav.navbar-default').after(
									$('<div id="navbar-height-col"></div>'));

							// Enter your ids or classes
							var toggler = '.navbar-toggle';
							var pagewrapper = '#page-content';
							var navigationwrapper = '.navbar-header';
							var menuwidth = '100%'; // the menu inside the slide menu itself
							var slidewidth = '80%';
							var menuneg = '-100%';
							var slideneg = '-80%';

							$("#slide-nav")
									.on(
											"click",
											toggler,
											function(e) {

												var selected = $(this)
														.hasClass(
																'slide-active');

												$('#slidemenu')
														.stop()
														.animate(
																{
																	left : selected ? menuneg
																			: '0px'
																});

												$('#navbar-height-col')
														.stop()
														.animate(
																{
																	left : selected ? slideneg
																			: '0px'
																});

												$(pagewrapper)
														.stop()
														.animate(
																{
																	left : selected ? '0px'
																			: slidewidth
																});

												$(navigationwrapper)
														.stop()
														.animate(
																{
																	left : selected ? '0px'
																			: slidewidth
																});

												$(this).toggleClass(
														'slide-active',
														!selected);
												$('#slidemenu').toggleClass(
														'slide-active');

												$(
														'#page-content, .navbar, body, .navbar-header')
														.toggleClass(
																'slide-active');

											});

							var selected = '#slidemenu, #page-content, body, .navbar, .navbar-header';

							$(window).on(
									"resize",
									function() {

										if ($(window).width() > 767
												&& $('.navbar-toggle').is(
														':hidden')) {
											$(selected).removeClass(
													'slide-active');
										}

									});

						});
		function dialogLogin(){
			var email=$("#dialogemail").val();
			var password=$("#dialogpwd").val();
			var dataString={};
			dataString ["email"] = email;
			dataString ["password"] = password;
				if(email .length === 0){
					//showLoginErrorMessageWithText("Please enter your email and password or register with MyMazda for access");
				alert("The information you entered is invalid. Please try again");
					return false;
				}else if(password .length === 0){
					alert("Please Enter Your Password");
					//showLoginErrorMessageWithText("Please Enter Your Password");
					return false;
					}else if(!IsEmail(email)){
						alert('Invalid Email Address');
					       return false;
					       }
				else{
						if ($('#dialogcheckId').is(':checked')){
							dataString = "email="+email+"&password="+password+"& remember=true";
						}else{
							dataString = "email="+email+"&password="+password+"& remember=false";
						}
				 		var url = "loginUser.action";
							$.ajax({
								type : "POST",
								url : url,
								data : dataString,
								 beforeSend : function() {
									$("#loadingmessage").show();
								},
								success : function(result) {
									 $("#addVehicle").show();
									 $("#signin").hide();
									 $("#myprofile").show();
								if(result == "no"){
										//showLoginErrorMessageWithText("We are sorry, the email address or password you entered does not match our records. Please check the spelling and try again. Password is case sensitive");
									alert("We are sorry, the email address or password you entered does not match our records. Please check the spelling and try again. Password is case sensitive");
										return false;
									}else if( result == "serviceProblem"){
										//showLoginErrorMessageWithText("There is a problem, while communicating with web services. Please try again.");
									alert("There is a problem, while communicating with web services. Please try again.");
									}
									else{
									 $("#container-fluid").html(result);
									// $(".getQuotesForm").fadeOut(300);
									 window.location="myMazda.action";
									 } 
								},complete : function() {
									$("#loadingmessage").hide();
									 $("#Loginform").hide();
								}
							});
				}
				return false;
			}
		function IsEmail(email) {
			  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			  return regex.test(email);
			}
	</script>
	<div class="modal fade" id="forgot-password" role="dialog">
		<!-- Modal -->
		<div class="modal-dialog forg-modal">
			<div class="modal-content">
				<!-- Modal content Start-->
				<div class="close-icon">
					<i class="fa fa-times-circle" data-dismiss="modal" onclick="return forgotClose();"></i>
				</div>
				<h4 class="modal-title">RETRIEVE YOUR PASSWORD</h4>
				<p>Please Enter your Email Address, and we will Email the password reset link to you.</p>
				<form class="form-inline" id="forgetPasswordFormId">
					<div class="form-group" id="forgotmaillayout">
						<label for="passwordemail" class="email">Email</label> <input
							type="email" class="form-control" id="passwordemail"
							placeholder="Enter email">
							<span class="error_mssg" id="forgotmailerror"></span>
					</div>
					<button type="submit" class="btn btn-default btn-red brand-button-white"
						onclick="return retrivePassword();" id="forgetSubmitBtnId" data-analytics-link-component-name="form" data-analytics-link-type="submit" data-analytics-link-description="retrieve_password">RETRIEVE
						YOUR PASSWORD</button>
					<div class="clearfix"></div>
					<p class="forgotvalidation" id="passwordmessage" style="display:none;">&nbsp;</p>
				</form>
				<p>If you still cannot access to your account, Please <a data-dismiss="modal" onclick=" return contactUs();">contact us</a></p>
				
			</div>
			<!-- Modal content End-->
		</div>
	</div>
	<!-- Modal End-->

	<div class="modal fade" id="Loginform" role="dialog"><!-- Modal -->
		<div class="modal-dialog fg-modal">
			<div class="modal-content signin-popup"><!-- Modal content Start-->
				<div class="close-icon"><i class="fa fa-times-circle" data-dismiss="modal"></i></div>
				<h4 class="modal-title">SIGN IN TO MYMAZDA</h4>                    
				<form class="signin-form" role="form">
					<div class="form-group" >
						<label for="email" class="email">EMAIL</label>
						<input type="email" class="form-control" id="dialogemail">
						
					</div>
					<div class="form-group">
						<label for="email" class="email">PASSWORD</label>
						<input type="email" class="form-control" id="dialogpwd">
					</div>
					<button type="submit" class="btn btn-default btn-red brand-button" onclick="return dialogLogin();">SIGN IN</button>
					<div class="clearfix"></div>
					<div class="checkbox">
						<label><input type="checkbox" id="dialogcheckId"> Remember me</label>
					</div>
					<p>New to MyMazda? <a href="#">Register Here</a></p>
				</form>
			</div><!-- Modal content End-->
		</div>
	</div><!-- Modal End-->
	<div class="modal fade" id="findmyvin" role="dialog">
	<!-- findmyvin Modal -->
	<div class="modal-dialog fg-modal">
		<div class="modal-content popup-vin">
			<!-- Modal content Start-->
			<div class="close-icon">
				<i class="fa fa-times-circle" data-dismiss="modal"></i>
			</div>
			<h4>HOW TO FIND YOUR VIN</h4>
			<img class="img-responsive"
				src="view/vhelper/images/findmyvin_image1.png" alt="">
			<p>Every vehicle has a unique VIN, a 17-character combination of
				numbers and letters. Your VIN is Stamped on a metal tag affixed
				above the instrument panel between the windshield and the dashboard
				on the driver's side.</p>
		</div>
		<!-- Modal content End-->
	</div>
</div>
<!--findmyvin  Modal End-->
<div class="modal fade" id="Locatedealer" role="dialog">
	<!-- Locatedealer Start-->
	<div class="modal-dialog">
		<div class="modal-content Located-popup">
			<!-- Modal content Start-->
			<h5 class="text-uppercase">mazda express service</h5>
			<img class="img-responsive" src="view/vhelper/images/mazde_express_service.png"
				alt="">
			<p>Mazda Express Service dealers offer you the convenience of
				completing basic maintenance services while you wait.</p>
			<p>From arrival to re-delivery, these maintenance services are
				completed in less than one hour at a competitive price.</p>
			<p>With convenient hours and a "no-appointment required"
				policy,you will receive the same great service,only faster.</p>
			<p>Basic services include:Minor maintenance services, Lube, oil,
				filter, Tire Rotation, filter Replacements & wiper Blade
				Replacements.</p>
			<button data-dismiss="modal"
				class="btn btn-default btn-red brand-button text-uppercase" type="submit">ok</button>
		</div>
		<!-- Locatedealer End-->
	</div>
</div>
<div class="modal fade" id="driving-condition" role="dialog">
	<!-- driving-condition Modal -->
	<div class="modal-dialog fg-modal">
		<div class="modal-content popup-driver">
			<!-- Modal content Start-->
			<div class="close-icon">
				<i class="fa fa-times-circle" data-dismiss="modal"></i>
			</div>
			<h4>HOW TO DETERMINE YOUR<br>TYPICAL DRIVING CONDITIONS</h4>
			<p>For standard driving conditions, a 7,500-mile service interval
				will apply. For heavy-duty conditions, a 5,000-mile interval will
				apply. If any of the following conditions apply, select Heavy Duty.</p>
			<ul>
				<li>Repeated short-distance driving</li>
				<li>Driving in dusty conditions</li>
				<li>Driving with an extended use of brakes</li>
				<li>Driving in areas where salt or other corrosive materials
					are being used</li>
				<li>Driving on rough or muddy roads</li>
				<li>Extended periods of idling or low-speed operation</li>
				<li>Driving for a prolonged period in cold temperatures or
					extremely humid climates</li>
			</ul>
		</div>
		<!-- Modal content End-->
	</div>
</div>
<!--driving-condition Modal End-->

<div class="modal fade" id="error-sucess" role="dialog"><!-- driving-condition Modal -->	
	<div class="modal-dialog fg-modal">
	<div class="modal-content Error-popup"><!-- Modal content Start-->
	        <h6>ERROR!</h6>	        					
			<p id="errorMsg"></p>			
			<button class="btn btn-default btn-red brand-button-white text-uppercase" data-dismiss="modal">OK</button>		
		 <div class="clearfix"></div>
	</div><!-- Modal content End-->		
	</div>
</div>
<div class="modal fade" id="success-msg" role="dialog"><!-- driving-condition Modal -->	
	<div class="modal-dialog fg-modal">
	<div class="modal-content Error-popup"><!-- Modal content Start-->	
			<p id="successMsg"></p>
		 <div class="clearfix"></div>
	</div><!-- Modal content End-->		
	</div>
</div>
<div class="modal fade" id="confirm-msg" role="dialog"><!-- driving-condition Modal -->	
	<div class="modal-dialog fg-modal">
	<div class="modal-content Error-popup"><!-- Modal content Start-->
	        <h6>CONFIRM</h6>	        					
			<p id="confirmMsg"></p>			
			<button class="btn btn-default btn-red brand-button" data-dismiss="modal">CANCEL</button>
			<button class="btn btn-default btn-red brand-button-white" data-dismiss="modal" id="confirmBtnOperation" onclick="">OK</button>
		 <div class="clearfix"></div>
	</div><!-- Modal content End-->		
	</div>
</div>
<script type="text/javascript">_satellite.pageBottom();</script>
</body>
</html>
