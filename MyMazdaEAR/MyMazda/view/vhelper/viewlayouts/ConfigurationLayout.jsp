
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="UTF-8" />
<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /><![endif]-->
<title>MyMazda</title>
<meta content="xenia - responsive and retina ready template"
	name="description" />
<meta content="width=device-width, initial-scale=1, maximum-scale=1"
	name="viewport" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="view/vhelper/images/ico_mazda.ico">
<!-- JS FILES -->
<script type="text/javascript" src="./view/vhelper/js/jquery.min.js"></script>
<script type="text/javascript" src="./view/vhelper/js/moment-with-locales.js"></script>
<script type="text/javascript" src="./view/vhelper/js/bootstrap.min.js"></script>
<script type="text/javascript" src="./view/vhelper/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="./view/vhelper/js/common.js"></script>
<script type="text/javascript" src="./view/vhelper/js/bootstrap-tabcollapse.js"></script>

<script type="text/javascript" src="view/JQuery Validation Engine_files/jquery.validationEngine-en.js"></script>
<script	src="view/JQuery Validation Engine_files/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<!-- CSS FILES -->
<link rel="stylesheet" href="view/vhelper/css/bootstrap-datetimepicker.css" >
<link href='view/vhelper/css/style.css' rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="view/JQuery Validation Engine_files/validationEngine.jquery.css" type="text/css"></link>
<link rel="stylesheet" href="view/JQuery Validation Engine_files/template.css" type="text/css"></link>
<link href="view/vhelper/css/menu.css" rel="stylesheet" type="text/css" />
<link href="view/vhelper/css/brand.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="./view/vhelper/js/ddaccordion.js"></script>
<link rel="stylesheet" href="view/vhelper/css/bootstrap.min.css">
<link rel="stylesheet" href="view/vhelper/css/font-awesome.min.css">

<!-- Omniture tagging vars end -->
<!-- <script src="//assets.adobedtm.com/0b1a9905a3bfe56ba99f4bcd3560048b3d9faf5f/satelliteLib-4332df72469fbed611c92423a310658ec4352e71.js"></script>  -->
<script src="//assets.adobedtm.com/0b1a9905a3bfe56ba99f4bcd3560048b3d9faf5f/satelliteLib-9dac536f1ca76627327a770a7af6d1ac98184c84-staging.js"></script>
</head>


<body>
	<!-- Starts :: Header -->
        <div class="container-fluid padding-0">
			<%-- <tiles:insertAttribute name="header" /> --%>
			<!--Start ::  Main body part -->

			<div id="container-fluid" >
				<tiles:insertAttribute  name="body" />
			</div>

			
			
        </div>
<div id='loadingmessage' class="loading"  style="display:none"><div class="ajaxloader"><div class="arc"></div></div></div>
			<!-- Footer -->
			<footer>
				<tiles:insertAttribute name="footer" />
			</footer>
			<!-- End :: Footer -->
			<!-- End :: Main body part -->


	<script>
$(document).ready(function () {

    //stick in the fixed 100% height behind the navbar but don't wrap it
    $('#slide-nav.navbar-inverse').after($('<div class="inverse" id="navbar-height-col"></div>'));
  
    $('#slide-nav.navbar-default').after($('<div id="navbar-height-col"></div>'));  
    
    // Enter your ids or classes
    var toggler = '.navbar-toggle';
    var pagewrapper = '#page-content';
    var navigationwrapper = '.navbar-header';
    var menuwidth = '100%'; // the menu inside the slide menu itself
    var slidewidth = '80%';
    var menuneg = '-100%';
    var slideneg = '-80%';


    $("#slide-nav").on("click", toggler, function (e) {

        var selected = $(this).hasClass('slide-active');

        $('#slidemenu').stop().animate({
            left: selected ? menuneg : '0px'
        });

        $('#navbar-height-col').stop().animate({
            left: selected ? slideneg : '0px'
        });

        $(pagewrapper).stop().animate({
            left: selected ? '0px' : slidewidth
        });

        $(navigationwrapper).stop().animate({
            left: selected ? '0px' : slidewidth
        });


        $(this).toggleClass('slide-active', !selected);
        $('#slidemenu').toggleClass('slide-active');


        $('#page-content, .navbar, body, .navbar-header').toggleClass('slide-active');


    });


    var selected = '#slidemenu, #page-content, body, .navbar, .navbar-header';


    $(window).on("resize", function () {

        if ($(window).width() > 767 && $('.navbar-toggle').is(':hidden')) {
            $(selected).removeClass('slide-active');
        }


    });




});

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
	$('.required-input').on('blur', function() {
		if($(this).val().trim() == '') {
			$(this).addClass('required-alert');
			$(this).attr("title", "This is required");
		} else {
			$(this).removeClass('required-alert');
			$(this).attr("title", "");
		}
	});
</script>

<div class="modal fade" id="forgot-password" role="dialog">
        <!-- Modal -->
        <div class="modal-dialog fg-modal">
            <div class="modal-content">
                <!-- Modal content Start-->
                <div class="close-icon">
                    <i class="fa fa-times-circle" data-dismiss="modal" onclick="return forgotClose();"></i>
                </div>
                <h4 class="modal-title">RETRIEVE YOUR PASSWORD</h4>
                <p>Please enter your email address and we will email your
                    password to you.</p>
                <form class="form-inline" id="forgetPasswordFormId">
                    <div class="form-group">
                        <label for="passwordemail" class="email">Email</label> <input
                            type="email" class="form-control" id="passwordemail"
                            placeholder="Enter email">
                    </div>
                    <button type="submit" class="btn btn-default btn-red brand-button-white"
                        onclick="return retrivePassword();" id="forgetSubmitBtnId">RETRIEVE
                        YOUR PASSWORD</button>
                    <div class="clearfix"></div>
                    <p class="forgotvalidation" id="passwordmessage" style="display:none;">&nbsp;</p>
                </form>
                <p>If you still cannot access to your account, <a data-dismiss="modal" onclick=" return contactUs();">please contact us</a></p>
            </div>
            <!-- Modal content End-->
        </div>
    </div>
    <!-- Modal End-->

    <div class="modal fade" id="Loginform" role="dialog"><!-- Modal -->
        <div class="modal-dialog fg-modal">
            <div class="modal-content signin-popup"><!-- Modal content Start-->
                <div class="close-icon"><i class="fa fa-times-circle" data-dismiss="modal" ></i></div>
                <h4 class="modal-title">SIGN IN TO MYMAZDA</h4>                    
                <form class="signin-form" role="form">
                    <div class="form-group">
                        <label for="email" class="email">EMAIL</label>
                        <input type="email" class="form-control" id="email">
                    </div>
                    <div class="form-group">
                        <label for="email" class="email">PASSWORD</label>
                        <input type="email" class="form-control" id="email">
                    </div>
                    <button type="submit" class="btn btn-default btn-red brand-button">SIGN IN</button>
                    <div class="clearfix"></div>
                    <div class="checkbox">
                        <label><input type="checkbox"> Remember me</label>
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
<!--driving-condition Modal End-->

<div class="modal fade" id="error-sucess" role="dialog"><!-- driving-condition Modal -->	
	<div class="modal-dialog fg-modal">
	<div class="modal-content Error-popup"><!-- Modal content Start-->
	        <h6>ERROR!</h6>	        					
			<p id="errorMsg"></p>			
			<button class="btn btn-default btn-red text-uppercase brand-button-white" data-dismiss="modal">OK</button>		
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
<div id="Mail" class="modal fade" role="dialog">
         <!-- Modal -->
         <div class="modal-dialog">
            <div class="modal-content mail-modal">
               <!-- Modal content Start-->
               <div class="close-icon"><i class="fa fa-times-circle" data-dismiss="modal" onclick="return modalClose();"></i></div>
               <form role="form" class="mail-modalform">
                  <div class="form-group">
                     <label for="email" class="email">SEND EMAIL TO<span class="mandatory-field">*</span></label>
                     <input type="email" class="form-control" id="emailid" placeholder="PLEASE ENTER EMAIL ID" style="font-size:12px;">
                     <p class="forgotvalidation" id="cuponsendmail" style="display:none;">&nbsp;</p>
                  </div>
                  <button id="submitButton"type="submit" class="btn btn-default btn-red brand-button" onclick="return sendCoupoun();" >SEND COUPON</button>
                  <button type="submit" class="btn btn-default btn-gray brand-button" data-dismiss="modal" onclick="return cuponCancel();">CANCEL</button>
                  <div class="clearfix"></div>
               </form>
            </div>
            <!-- Modal content End-->
         </div>
      </div>
<script type="text/javascript">_satellite.pageBottom();</script>
</body>
</html>
