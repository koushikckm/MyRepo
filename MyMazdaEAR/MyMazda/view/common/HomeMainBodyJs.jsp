
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
	$('#email').focus();
	 $("#addVehicle").hide();
	 $("#myprofile").hide();
	 $('#phoneprofile').hide();
	 $('.carlistmenu').hide();
	 $('#signin').show();
	 $(".searchkeyhome").keyup(function (e) {
		    if (e.keyCode == 13) {
		    	
		    	searchDealers();
				return false;
		    }
		});
	 
});
	
$(document).ready(function() {
	
	 $(".searchkeyhomebody").keyup(function (e) {
	    if (e.keyCode == 13) {
	    	homesearchDealers();
			return false;
	    } 
	});
	
});
	function register(){
		
		$.ajax({ 
		 	url:"registration.action",
		 	 beforeSend:function(){
				   $("#loadingmessage").show(); 
					    },
		 	 complete:function(){
		 		$("#loadingmessage").hide();
		 		mobilecollapse();
			    },
		 	success:function(result){
		 			
					 $("#container-fluid").html(result);					
					 window.scrollTo(0, 0);
				 	 }
				    });
				 	
				 	 //location.href="view/configuration/Registration.jsp";
		return false;
		
	}
	 function retrivePassword(){
		var dataString={};
		var email=$("#passwordemail").val();
		
		dataString ["email"] = email;
		dataString = "email="+email;
		if(email .length === 0){
			//showLoginErrorMessageWithText("Please enter your email and password or register with MyMazda for access");
			//alert("Please enter your email address ");

			$("#passwordmessage").show();
			$("#passwordmessage").html("Please enter your email address");
			 
			$("#passwordemail").val('');
			return false;
		}
		if(!IsEmail(email)){
			//alert('Invalid Email Address');
			$("#passwordmessage").show();
			$("#passwordmessage").html("The email address you entered is invalid. Please try again.");
			
			$("#passwordemail").val('');
      	 	return false;
		}
		
		$.ajax({ 
		 	url:"forgotPassword.action",
		 	data:dataString,
		 	type:"GET",
		 	beforeSend:function(){
				   $("#loadingmessage").show(); 
				},
		 	complete:function(){
		 		$("#loadingmessage").hide();
			    },
		 	success:function(result){
		 		if(result =="failure"){
		 			$("#passwordmessage").show();
					$("#passwordmessage").html("We could not find any account associated with this e-mail. Please try again.");
		 			$("#passwordemail").val('');
		 			return false;
				}else {
					$("#success-msg").modal("show");
		 			 /* $("#successMsg").html("Your password has been successfully sent."); */
		 			 $("#successMsg").html("Your password reset link has been successfully sent.");
					 $("#passwordemail").val('');
					 $("#forgot-password").modal("hide");
					 $("#ccontainer-fluid").html(result);
					 setTimeout(function() {
						 $("#success-msg").modal("hide");						 						
						}, 1500);
				}
		 		//window.scrollTo(0, 50);

			}
		});
		
		return false;
	} 
	 function cleanPassword(name){
	 	dataLayer.page.name = 'retrieve_password';
	 	dataLayer.page.nameHistorical = '';
		dataLayer.page.subCategory = '';
		dataLayer.page.URL = window.location.href;
		dataLayer.site.sectionHistorical = 'owners';
		dataLayer.site.section = 'mymazda';
		dataLayer.form.name = 'retrieve_password';
		dataLayer.form.type = 'retrieve_password';
		dataLayer.user.type = 'x';
	 	if(name !== 'reg'){
	 		$("#passwordmessage").text('');
	 	}
	 }
	function forgotClose(){
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
		 
		$("#passwordemail").val('');
		$("#passwordmessage").text('');
		return false;
	}
	 function IsEmail(email) {
		  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		  return regex.test(email);
		}

	
	function videoDemos(){
		$.ajax({													
			url : "howTOMazdaNotLogin.action",
			beforeSend : function() {
				$("#loadingmessage").show();
			},
			success : function(result) {

				$("#container-fluid").html(result);
				window.scrollTo(0, 0);
			},
			complete : function() {
				$("#loadingmessage").hide();
			}
		});
		return false;
	 }
	function homesearchDealers() {
		var data = {};
		var dealerSearch = $("#homesearch").val();
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