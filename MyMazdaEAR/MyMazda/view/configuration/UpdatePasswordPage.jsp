
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<jsp:include page="../common/Header.jsp" />	

<jsp:include page="/view/common/DataLayerJs.jsp"></jsp:include>	

<script type="text/javascript">
	$(document).ready(function() {
		dataLayer.page.name = 'retrieve_password_update';
		dataLayer.page.URL = window.location.href;
		dataLayer.site.sectionHistorical = 'owners';
		dataLayer.site.section = 'mymazda';
		dataLayer.site.subsection = 'owners';
		dataLayer.form.name = 'retrieve_password';
		dataLayer.form.type = 'retrieve_password';
		dataLayer.user.type = 'x';
	});
</script>			
	
	<div class="EditAccountInformation-Page">
		<div class="container">
			<h1>RESET PASSWORD</h1>			
			<form method="post" id="updatePasswordForm" action="passwordUpdate">
					<div class="row EditAccount-div margin-0">
						<div class="col-sm-12 col-md-6 paddin_30 padding-0">
							<div class="row margin-0">
							<br/>
								<div class="form-group col-sm-7">
									<label>USER NAME : </label>
									<%-- <span id="userName"><s:text name="#session['encryptedUser']"/></span> --%>
									<span style="color:#666" id="encryptedUser">
									<%-- <s:property value="#session['encryptedUser']"/> --%>
									<c:out value="${sessionScope.encryptedUser}"/>
									</span>
									<input type="hidden" id="userName" value="<c:out value="${sessionScope.encryptedUser}"/>"/>
								</div>
								<div class="form-group col-sm-7">
									<label>NEW PASSWORD<span class="mandatory-field">*</span></label>
									<input type="password" class="form-control" id="newPassword"/>
									
								</div>
								<div class="form-group col-sm-7">
									<label>CONFIRM PASSWORD<span class="mandatory-field">*</span></label>
									<input type="password" class="form-control" id="confirmPassword"/> 
									<label>Must be 4-15 characters long.<br>
									Allows alphanumeric and symbols @ * $ . ! %  </label>
								</div>
							</div>
							
							<div class="btn-spacing">
								<button type="submit" class="btn btn-default btn-red brand-button-white"
									onclick="return resetForgotPassword();">SUBMIT</button>
							</div>
						</div>
						
					</div>
			</form>
		</div>
	</div>
		
	<div>	
		<jsp:include page="../common/Footer.jsp" />			
	</div>
	
	<script type="text/javascript">
	
	$(function() {
		$("#addVehicle").hide();
	});
	
	function resetForgotPassword() {
	
		//var userName 	= $.trim($("[id='userName']").val());
		var userName = encryptedUser.innerHTML;
		var newPassword 	= $.trim($("[id='newPassword']").val());
		var confirmPassword = $.trim($("[id='confirmPassword']").val());
		var regEx = /^[a-zA-Z0-9@*$.!%\\n\\t]{4,15}$/g;
		var dataString =  "userName=" + userName 
					+ "&newPassword=" + newPassword;
					
	
		if(newPassword == ""){
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter your new password.");
			alert("Please enter your new password.");
			return false;
		} else if(confirmPassword == ""){
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter your confirm password.");
			alert("Please enter your confirm password.");
			return false;
		} else if(newPassword != confirmPassword){
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Your new password and confirm password entries do not match. Please try again.");
			alert("Your new password and confirm password entries do not match. Please try again.");
			return false;
		}  else if(!newPassword.match(regEx)){
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Password must be 4-15 characters long. Can contain alphanumeric characters and the following symbols @ * $ . ! % ");
			alert("Password must be 4-15 characters long. Can contain alphanumeric characters and the following symbols @ * $ . ! % ");
			return false;
		} else{
		
		$.ajax({ 
		 	url:"passwordUpdate.action",
		 	data:dataString,
		 	type:"POST",
		 	beforeSend:function(){
				   $("#loadingmessage").show(); 
				},
		 	complete:function(){
		 		$("#loadingmessage").hide();
			    },
		 	success:function(result){
		 		
		 		 if(result =="success"){
					$("#success-msg").modal("show");
					$("#successMsg").html("Password changed successfully.");
					//$('.modal-backdrop').hide();
					setTimeout(function() {
						returnHome(); 
						$("#success-msg").modal("hide");
					},2000);
		 			
				}else {
					$("#error-sucess").modal("show");
					$("#errorMsg").html("Password not changed. Please try again.");
				} 
				

			}
		});
		
		}	
		return false;
}

	function returnHome() {

		window.location="myMazda.action";
		return false;
	}
	
</script>
<script type="text/javascript">_satellite.pageBottom();</script>
</body>
</html>