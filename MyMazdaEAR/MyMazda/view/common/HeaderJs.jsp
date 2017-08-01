
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
	function hideMenubars() {
		$('.bg-w').hide();
		$('.bg-w1').hide();
		$('.bg-w2').hide();
		$('.bg-w2').hide();
	}
	$(function() {
		/* var isiPhone = /iphone/i.test(navigator.userAgent.toLowerCase());
		
		if (isiPhone)
		{
			$('#owneresources').hide();
		} */
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
		$("#phonenumber").keypress(function (e) {
		     //if the letter is not digit then display error and don't type anything
		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
		        //display error message
		        //$("#errmsg").html("Digits Only").show().fadeOut("slow");
		               return false;
		    }
		   });
		$("#signin").show(); 
		$(document).ready(function(){
	      if ( $(window).width() >= 768 ) {
			 $(".mid-menu-drop").hover(function(){								
		        $('.bg-w').fadeIn();
		        $('.bg-w1').hide();
				$('.bg-w2').hide();
				$('.bg-w3').hide();
			  },function(){
				$('.bg-w').hide();
			  }); 
			 $(".mid-menu-drop1").hover(function(){					 
			    $('.bg-w1').fadeIn();
			    $('.bg-w').hide();
				$('.bg-w2').hide();
				$('.bg-w3').hide();
			  },function(){
				$('.bg-w1').hide();
			  });
			 $(".mid-menu-drop2").hover(function(){				 
			    $('.bg-w2').fadeIn();
			    $('.bg-w').hide();
				$('.bg-w1').hide();
				$('.bg-w3').hide();
			  },function(){
				$('.bg-w2').hide();
			  });
			 $(".mid-menu-drop3").hover(function(){				 
			    $('.bg-w3').fadeIn();
			    $('.bg-w').hide();
				$('.bg-w1').hide();
				$('.bg-w2').hide();
			  },function(){
				$('.bg-w3').hide();
			  });
			 $(".mazdausa-com").hover(function(){				 
			    hideMenubars();
			  },function(){
				hideMenubars();
			  });
			 $(".fin-ser").hover(function(){				 
			    hideMenubars();
			  },function(){
				hideMenubars();
			  });
		  }
		  else {
		    $(".mid-menu-links li").removeClass('mid-menu-drop');
		    $(".mid-menu-links li").removeClass('mid-menu-drop1');
		    $(".mid-menu-links li").removeClass('mid-menu-drop2');
		    $(".mid-menu-links li").removeClass('mid-menu-drop3');
			hideMenubars();
		  }
		});
		var html = $('html, body'), navContainer = $('.nav-container'), navToggle = $('.nav-toggle'), navDropdownToggle = $('.has-dropdown'), containerp = $('.container-fluid'), overlay = $('.overlay');

		// Nav toggle
		navToggle.on('click', function(e) {
			var $this = $(this);
			e.preventDefault();
			$this.toggleClass('is-active');
			overlay.toggle();
			$("body").toggleClass("overlay-hide");
			navContainer.toggleClass('is-visible');
			navToggle.toggleClass('is-visible');
			containerp.toggleClass('is-visible');
			html.toggleClass('nav-open');
			hideMenubars();
		});
		
		$('.side-menu-close').on('click', function(e) { if ( $(window).width() < 768 ) { navToggle.click(); } });
		$('.overlay').on('click', function(e) { if ( $(window).width() < 768 ) { navToggle.click(); } });
		

		// Nav dropdown toggle
		navDropdownToggle.on('click', function() {
			var $this = $(this);
			$this.toggleClass('is-active').children('ul').toggleClass('is-visible');
		});
		$('.menu-item-1-a').on('click', function(e) { if ( $(window).width() < 768 ) { $('.menu-item-1').click(); } });
		$('.menu-item-2-a').on('click', function(e) { if ( $(window).width() < 768 ) { $('.menu-item-2').click(); } });
		$('.menu-item-3-a').on('click', function(e) { if ( $(window).width() < 768 ) { $('.menu-item-3').click(); } });
		$('.menu-item-4-a').on('click', function(e) { if ( $(window).width() < 768 ) { $('.menu-item-4').click(); } });
		$('.menu-item-5-a').on('click', function(e) { if ( $(window).width() < 768 ) { $('.menu-item-5').click(); } });

		// Prevent click events from firing on children of navDropdownToggle
		navDropdownToggle.on('click', '*', function(e) {
			e.stopPropagation();
		});

		$('.loginPopup_trigger').click(function(e) {
			e.stopPropagation();
			if ($(e.target).is('[data-toggle=modal]')) {
				$($(e.target).data('target')).modal();
			}
		});
	});
	$(document).ready(function() {
		
		 $(".searchkey").keyup(function (e) {
		    if (e.keyCode == 13) {
		    	headsearchDealer();
				return false;
		    } 
		});
		$(".locatesearch").keyup(function (e) {
		    if (e.keyCode == 13) {
		    	
		    	searchDealers();
				return false;
		    }
		});
		$(".searchkey1").keyup(function (e) {
		    if (e.keyCode == 13) {
		    	
		    	searchDealers();
				return false;
		    }
		});
		
		/* $(document).on("keypress", ".searchkey", function() {
			if (event.keyCode == 13) {
				headsearchDealer();
				return false;
			}
		}); */
		 $(document).on("keypress", ".locatesearch", function() {
			if (event.keyCode == 13) {
				searchDealers();
				return false;
			}
		});
		$(document).on("keypress", ".searchkey1", function() {
			if (event.keyCode == 13) {
				searchDealers();
				return false;
			}
		}); 


	});
//Browser related code Starts here
var updateContent = function(stateObj) {
	  // Check to make sure that this state object is not null.
	  if (stateObj) {
	    Navigate(stateObj);
	  }
	};
/* window.addEventListener('popstate', function(event) {
	  var stateObj = event.state;
	  alert(window.stateObj.url);
	  if(stateObj!=null){
	  updateContent(stateObj);
	  }
	}); */
$(window).bind('popstate', function(event) {
    var stateObj = event.originalEvent.state;
	  if(stateObj!==null){
	  updateContent(stateObj);
	  }
	  /* else
		  {
		  window.location="myMazda.action";
		  } */
 })
 
 function Navigate(stateObj)
 {

	 switch(stateObj.url)
	 {
	 case 'addVehicle.action' :
		 addMoreVehicalform(false);
		 break;
	 case 'applyToUpdateSelectedVehical.action':
		 applyToUpdateSelectedVehical(stateObj.data["vin"],false);
		 break;
	 case 'editNewVehicle.action':
		 editVehicle(stateObj.data["carlineDesc"],stateObj.data["mileage"],stateObj.data["vin"],stateObj.data["milesPerDay"],stateObj.data["drivingCondition"], false);
		 break;
	 case 'serviceHistory.action':
		// var vin='${session.selectedVehical.vin}';
		 serviceHistory(stateObj.data["vin"],false);
		 break;
	 case 'howTOMazda.action':
		 videoDemosLogin(false);
		 break;
	 case 'editProfile.action':
		 updateInformation(false);
		 break;
	 case 'recall.action':
		 recall();
		 break;
	 case 'serviceReminder.action':
	 serviceReminder(stateObj.data["vin"],false);
	 break;
	 case 'maintenanceSchedule.action':
		 maintenanceSchedule();
		 break;
	 case 'myServiceOffer.action':
		 myServiceOffer(stateObj.data["vin"],false);
		 break;
	 case'locateDelear.action':
		 searchDealers(false);
		 break;
	 case 'addServiceHistory':
		 addServiceHistory(false);
		 break;
	 case 'sendMail.action':
		 contactDetails(false);
		 break;
	 case 'saveRegistration.action':
		 joinMymazda(false);
		 break;
	 case 'updateServiceHistory':
		 var vin='${session.selectedVehical.vin}';
		 updateRecord(vin,false);
		 break;
	 case 'locateDelear1.action':
		 headsearchDealer(false);
		 break;
	 default:
		 window.location="myMazda.action";
		 break;
	 
	 }
	 
 }
 //End here
	var email;
	var password;
	var details;
	var loginpage;
	function login() {
		email = $("#email").val();
		password = $("#pwd").val();
		var dataString = {};
		dataString["email"] = email;
		dataString["password"] = password;
		if (email.length === 0) {
			$("#error-sucess").modal("show");
			$("#errorMsg")
					.html(
							"<span class='errMsg'>Please enter your email address and password.</br></br></span> If you do not have an account with MyMazda, please start the registration process by clicking on the 'Join MyMazda' button.");
			return false;
		} else if (password.length === 0) {
			//alert("Please Enter Your Password");
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter your password");
			return false;
		} else {
			if ($('#checkId').is(':checked')) {
				dataString = "email=" + email + "&password=" + password
						+ "&remember=true";
				details = email + ',' + password + ',' + 'true';
			} else {
				dataString = "email=" + email + "&password=" + password
						+ "&remember=false";
				details = email + ',' + password + ',' + 'false';
			}
			var url = "loginUser.action";
			var state={'url':url,data:dataString};
			$
					.ajax({
						type : "POST",
						url : url,
						data : dataString,
						beforeSend : function() {
							$("#loadingmessage").show();
						},
						success : function(result) {
							$("#signin").hide();
							$("#myprofile").show();
							if (result == "no") {
								$("#error-sucess").modal("show");
								$("#errorMsg")
										.html(
												"<span class='errMsg'>The information you entered is invalid. </br>Please try again.</br></br></span> Passwords are case sensitive, please check that the caps lock is not on.");
								$("#signin").show();
								$("#myprofile").hide();
								return false;
							} else if (result == "serviceProblem") {
								$("#error-sucess").modal("show");
								$("#errorMsg")
										.html(
												"There is a problem, while communicating with web services. Please try again.");
								$("#signin").show();
								$("#myprofile").hide();
							}
							else if (result == "add") {
								$("#signin").hide();
								addvechicle();
								window.location="addvechicle.action";
								
							}else {
								$("#selector").show();

								$('#vehicleList').show();
								
								$("#container-fluid").html(result);
								window.scrollTo(0, 0);
								/* window.location="myMazda.action"; */
								$("#addVehicle").show();
							}
						},
						complete : function() {
							$("#loadingmessage").hide();
						}
					});
		}
		return false;
	}
	function addvechicle(){
		$.ajax({
			url : "addvechicle.action",
			beforeSend : function() {
				$("#loadingmessage").show();
			},
			complete : function() {
				$("#loadingmessage").hide();
			},
			success : function(result) {
				$("#container-fluid").html(result);
				window.scrollTo(0, 0);
			}
		});
		return false;
	}
	function recall(flag) {
		if(flag == 'undefined' || flag == null)
		{
		flag = 'true';
		}
	if(flag == 'true')
		{
	var data = {};
	var state = { 'url': 'recall.action',data:data};
	history.pushState(state ,'mymazda', '#recall');
}
		
		$.ajax({
			url : "recall.action",
			beforeSend : function() {
				$("#loadingmessage").show();
			},
			complete : function() {
				$("#loadingmessage").hide();
			},
			success : function(result) {
				$("#container-fluid").html(result);
				window.scrollTo(0, 0);
			}
		});
		return false;
	}
	var dealerSearch;
	function searchDealer() {
		
		var data = {};
		dealerSearch = $("#searchinput").val();
		/* 
		if (dealerSearch.length === 0) {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("You must Enter a Valid ZipCode or state");
			return false;
		} */
		if($.isNumeric(dealerSearch)) { 
			data["zip"] = dealerSearch;
			} 
		else { 
			data["dealerSearch"] = dealerSearch;
			}
		$.ajax({
			url : "locateDelear.action",
			data :data,
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
				//$(".LocateDealer-radio").hide();
			},
			error : function(error) {
				$("#error-sucess").modal("show");
				$("#errorMsg").html("Error");

			}
		});
		$("#searchinput").val('');
	}
	var headsearch;
	 var miles;
	function headsearchDealer(flag) {
		headsearch='searchfromheader';
		var data = {};
		var dealerSearch = $("#headsearchinput").val();
			if(dealerSearch.length === 5){
				dataLayer.zipCode = dealerSearch;
			}
		if (dealerSearch === '') {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("This field cannot be blank. Please enter a valid zip code or name.");
			return false;
		}  
	  
		if($.isNumeric(dealerSearch)) { 
			data["zip"] = dealerSearch;
			} 
		else { 
			data["dealerSearch"] = dealerSearch;
			 miles='miles';
			
			}
		if(flag == 'undefined' || flag == null)
		{
		flag = 'true';
		}
	if(flag == 'true')
		{
	var state = { 'url': 'locateDelear1.action',data:data};
	history.pushState(state ,'mymazda', '#headerSearch');
}
	 
		$.ajax({
			url : "locateDelear.action",
			data :data,
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
				$(".radio").addClass("radioClass");
				$("#backbutton").hide();
				$("#selectdealer").hide();
				if(miles==='miles'){
					$(".milesfrom").hide();
				}
			},
			error : function(error) {
				$("#error-sucess").modal("show");
				$("#errorMsg").html("Error");

			}
			
		});
	$(".searchkey").val('');
		return false;
	}
	var useremail;
	var userpass;
	function changesearchDealer() {
		loginpage = 'loginscreen';
		add ='';
		edit='';
		useremail= $("#userloginmail").text();
		userpass= $("#userloginpass").text();
		var data = {};
		dealerSearch = $("#searchinput").val();
		/* 
		if (dealerSearch.length === 0) {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("You must Enter a Valid ZipCode or state");
			return false;
		} */
		if($.isNumeric(dealerSearch)) { 
			data["zip"] = dealerSearch;
			} 
		else { 
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
				//$(".LocateDealer-radio").hide();
			},
			error : function(error) {
				$("#error-sucess").modal("show");
				$("#errorMsg").html("Error");

			}
		});
		$("#searchinput").val('');
	}

	var add;
	function addMoreVehicalform(flag) {
		$("#hidedealer").hide();
		edit='';
		add = 'addMoreVehicle';
		if(flag == 'undefined' || flag == null)
		{
		flag = 'true';
		}
	if(flag == 'true')
		{
	var data = {};
	var state = { 'url': 'addVehicle.action',data:data};
	history.pushState(state ,'mymazda', '#addVehicle');
}
		
		$.ajax({
			url : "addVehicle.action",
			beforeSend : function() {
				$("#loadingmessage").show();
			},
			complete : function() {
				$("#loadingmessage").hide();
			},
			success : function(result) {

				$("#container-fluid").html(result);
				$("#hidedealer").hide();
				$("#dealer1").hide();

				$("#editdealer").show();

				window.scrollTo(0, 0);
			}
		});
		return false;
	}
	
	function changePassword(){
		 $.ajax({
			url : "changePassword.action",
			beforeSend : function() {
				$("#loadingmessage").show();
			},
			complete : function() {
				$("#loadingmessage").hide();
			},
			success : function(result) {
				$("#container-fluid").html(result);
			}
		});

		return false;
	}
	
	function updateInformation(flag) {
		if(flag == 'undefined' || flag == null)
		{
			flag = 'true';
		}
		if(flag == 'true')
		{
			var data = {};
			var state = { 'url': 'editProfile.action',data:data};
			history.pushState(state ,'mymazda', '#editProfile');
		}
		$.ajax({
			url : "editProfile.action",
			beforeSend : function() {
				$("#loadingmessage").show();
			},
			complete : function() {
				$("#loadingmessage").hide();
			},
			success : function(result) {
				$("#container-fluid").html(result);
			}
		});

		return false;
	}
	var addvehicleinfo;
	function addMoreVehical(section) {
		var carlineDesc = $("#titleSelector").val();
		var vin = $("#vinSelector").val();
		var mileage = $("#mileage").val();
		var milesPerDay = $("#milesPerDay option:selected").val();
		var drivingCondition = $("#drivingCondition option:selected").val();
		var dealerName = $("#adddelername").text();
	     var dealerStreet = $("#addDealerStreet").text();
	     var dealerCity = $("#addDealerCity").text();
	     var dealerState = $("#addDealerSate").text();
	     var dealerPhone = $("#addDealerphone").text();
	     
		var serviceDealerID;
		 serviceDealerID = $("#regdealerID").text();
		/* if (section == "Reg") {
			serviceDealerID = $("#regdealerID").text();
		} else if (section == "Edit") {
			serviceDealerID = $("#editdealerID").text();
		}
 */
		var url = "addMoreVehical.action";
		var data = {};
		data["carlineDesc"] = carlineDesc;
		data["mileage"] = mileage;
		data["milesPerDay"] = milesPerDay;
		data["drivingCondition"] = drivingCondition;
		data["serviceDealerID"] = serviceDealerID;
		data["vin"] = vin;
		 data ["dealerName"] =dealerName;
	     data ["dealerStreet"] =dealerStreet;
	     data ["dealerCity"] =dealerCity;
	     data ["dealerState"] =dealerState;
	     data ["dealerPhone"] =dealerPhone;
		data = "carlineDesc =" + carlineDesc + "& mileage =" + mileage + "& milesPerDay ="
				+ milesPerDay + "& vin =" + vin + "& drivingCondition ="
				+ drivingCondition + "& serviceDealerID =" + serviceDealerID+ "& dealerName =" + dealerName + "& dealerStreet ="
				+ dealerStreet + "& dealerCity =" + dealerCity + "& dealerState ="
				+ dealerState+ "& dealerPhone =" + dealerPhone;
		if (carlineDesc == "") {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter a name for your Mazda.");
		} else if (vin == "") {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter your Mazda's VIN number.");
		} else if (vin.length > 17 || vin.length < 17) {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter the correct VIN number. Maximum allowed characters is 17.");
		} else if (isNaN(mileage) || mileage <= 0) {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter your current mileage.");
			
		}else if (milesPerDay =="") {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter your average mileage per day.");
			
		}
		else if (drivingCondition == "") {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please select a driving condition.");
			
		}
		else if (serviceDealerID =="") {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter your preferred service dealer.");
			
		}else {
			$.ajax({
				url : url,
				type : "POST",
				data : data,
				beforeSend : function() {
					$("#loadingmessage").show();
				},
				complete : function() {
					$("#loadingmessage").hide();
				},
				success : function(result) {
                      if(result == ""){
                    	  $("#error-sucess").modal("show");
							$("#errorMsg")
									.html(
											"We regret that the system is currently experiencing network issues, and we are unable to service your request. Please try again after some time.");
							return false;
                      }
					if (result == "Error") {
						 $("#error-sucess").modal("show");
							$("#errorMsg")
									.html("We regret that the system is currently experiencing network issues, and we are unable to service your request. Please try again after some time.");
						$("#actionMessage").show();
						return false;
					} else {
						$("#success-msg").modal("show");
						$("#successMsg")
								.html(" Your vehicle added successfully.");

						$("#container-fluid").html(result);
						 $('.modal-backdrop').hide();
						setTimeout(function() {
							 $("#success-msg").modal("hide");
							homeClick();
						}, 1000); 
						
					}
			 		window.scrollTo(0, 0);

				},
				error : function(error) {
					$("#error-sucess").modal("show");
					$("#errorMsg").html("Error");
				},
				
			});
		}
		return false;

	}
	
	function logout(){
		 $('#usermail').text('');
		 $("#signin").show();
	}
	function updateProfile() {
		var dataString = {};
		var firstName = $("#firstname").val();
		var lastName = $("#lastname").val();
		var street1address = $("#streetaddress1").val();
		var street2address = $("#streetaddress2").val();
		var city = $("#cityName").val();
		var state = $("#state option:selected").text();
		var zip = $("#zipcode").val();
		var mobilePhone = $("#phonenumber").val();
		//var phoneType = $("#phonetype").val();
		dataString = "firstName=" + firstName
				+ "&lastName=" + lastName + "&street1address=" + street1address
				+ "&street2address=" + street2address + "&city=" + city
				+ "&state=" + state + "&zip=" + zip + "&mobilePhone="
				+ mobilePhone ;
		if (firstName.length === 0) {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter your first name.");
		} else if (lastName.length === 0) {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter your last name.");
		} else if (street1address.length === 0) {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter your street address.");
		} else if (city.length === 0) {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter your city.");
		} else if (zip.length === 0) {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter your zip code.");
		} else if ((mobilePhone.length === 0) || (mobilePhone.length != 10)) {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter a valid phone number.");
		} else {
			var url = "updateProfile.action";
			$.ajax({
				type : "POST",
				url : url,
				data : dataString,
				beforeSend : function() {
					$("#loadingmessage").show();
				},
				complete : function() {
					$("#loadingmessage").hide();
				},
				success : function(result) {
					if (result == "success") {
						$("#success-msg").modal("show");
						$("#successMsg").html("Your profile updated successfully.");
						 $('.modal-backdrop').hide();
						setTimeout(function() {
							homeClick();
							 $("#success-msg").modal("hide");
						},1000); 
					} else {

					}
				},
				
			});
		}
		return false;
	}

	function homeClick() {

		window.location="myMazda.action";
		return false;
	}
	function cancelUpdateProfile() {
		window.location = "myMazda.action";
		return false;
	}
	var dealerSearch = '';
	var mile;
	function searchDealers() {
		var data = {};
		
		dealerSearch = $("#searchDelar").val();
		if (dealerSearch.length === 0) {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("This field cannot be blank. Please enter a valid zip code or name.");
		} else {
			if ($.isNumeric(dealerSearch)) {
				data["zip"] = dealerSearch;
			} else {
				data["dealerSearch"] = dealerSearch;
				mile='milesfrom';
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
					if(mile==='milesfrom')
						$(".milesfrom").hide();
					
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
	var registersearch;
	function searchDealers(flag) {
		var usermail= $('#usermail').text();
		var data = {};
		dealerSearch = $("#searchDelar").val();
		if (dealerSearch.length === 0) {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("This field cannot be blank. Please enter a valid zip code or name.");
		}
		else{
			if($.isNumeric(dealerSearch)) { 
				data["zip"] = dealerSearch;
				} 
			else { 
				data["dealerSearch"] = dealerSearch;
				mile='milesfrom';
				}
			if(flag == 'undefined' || flag == null)
			{
			flag = 'true';
			}
		if(flag == 'true')
			{
		
		var state = { 'url': 'locateDelear.action',data:data};
		history.pushState(state ,'mymazda', '#locateDealer');
	}
			$.ajax({
				url : "locateDelear.action",
				data :data,
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
					$("#searchDelar").val(dealerSearch);
					if(registersearch==='joinmee'){
						$("#backbutton").show();
						$("#submit").show();
					}
					else if(headsearch==='searchfromheader'){
						$("#backbutton").hide();
						$("#submit").hide();
						$(".radio").addClass("radioClass");
					}
	else if(add==='adddealerback'){
						$("#backbutton").show();
						$("#submit").show();
						
					}else if(edit==='editdealerback'){
						$("#backbutton").show();
						$("#submit").show();
						
					}
					else if(usermail===''){
						$("#backbutton").hide();
						$("#submit").hide();
						$(".radio").addClass("radioClass");
					}
else{
						$("#backbutton").hide();
						$("#submit").hide();	
						$(".radio").addClass("radioClass");
					}
					 
					if(mile==='milesfrom')
						$(".milesfrom").hide();
					//$(".LocateDealer-radio").hide();
					//headsearch='';
				},
				error : function(error) {
					$("#error-sucess").modal("show");
					$("#errorMsg").html("Error");

				}
			});
		}
		return false;
	}
	var searchname;
	var searchstreet;
	var searchphone;
	var searchcity;
	var searchstate;
	var searchserviceDealerId;
	var serviceUrl;
	var searchzip;
	function getsearchDealer() {
		var radioval = $('input[name=optradio]:checked').val();
		var arry = '';
		if(radioval=== undefined){
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please select any one of the nearest dealer.");
			return false;
		} 
		if (radioval.length > 10) {
			arry = radioval.split(';');
		} 
		searchname = arry[0];
		searchstreet = arry[1];
		searchcity = arry[2];
		searchstate = arry[3];
		searchphone = arry[4];
		searchserviceDealerId = arry[5];
		serviceUrl=arry[6];
		searchzip=arry[7];
		if (editpreferredealer==='editdealer') {
			addAndEditvehicle();
		} else if (editDealer==='editDealersearch') {
			addAndEditvehicle();
		}else if (add === 'addMoreVehicle') {
			addAndEditvehicle();
		}
		else if (loginpage === 'loginscreen') {
			userlogin();
		} else {
			zipcode();
			
		}
	}
function maintenanceSchedule(){
	//window.location=serviceUrl;
	open(serviceUrl, '_blank');
	 
	return false;
}
function maintenanceScheduleService(){
	var link=	$("#servicelink").text();
	var url='${selectedVehical.serviceUrl}';
	var phone=$("#dealerphone").text();
	var dealer=$("#dealername").text();
	if(link==''){
		$("#error-sucess").modal("show"); 
		$("#errorMsg").html("APOLOGIES. THIS DEALERSHIP DOES NOT SUPPORT ONLINE APPOINTMENT SCHEDULING.</br></br>Please call "+phone+" to set up an appointment with "+dealer+".");
		return false;
	}else{
	open(link, '_blank');
	}
	return false;
}

function addAndEditvehicle(){
	if(searchname.indexOf('#') == 0) searchname = '@@@'+searchname.substring(1);
	var url="addVehiclePreferedDealer.action";
    var data={};
    data ["dealerName"] = searchname;
	data ["dealerStreet"] = searchstreet;
	data ["dealerCity"] = searchcity;
	data ["dealerState"] = searchstate;
	data ["dealerPhone"] = searchphone;
	data ["serviceDealerID"] = searchserviceDealerId;
	data ["dealerZip"] = searchzip;
	data="dealerName ="+searchname+"& dealerStreet ="+searchstreet+"& dealerCity ="+searchcity+"& dealerState ="+searchstate+"& dealerPhone ="+searchphone+"& serviceDealerID ="+searchserviceDealerId+"& dealerZip ="+searchzip;
	
	
	$.ajax({ 
		url: url,
    	data : data,
			    complete:function(){
			    },
	 	success:function(result){
	 		
	 		$("#container-fluid").html(result);
	 		if (add === 'adddealerback'){
	 			var arry=	adddetails.split(',');
				$("#titleSelector").text(arry[0]);
				$("#milesPerDay").val(arry[1]);
				$("#drivingCondition").val(arry[2]);
				
	 		}
	 		else{
	 		var arry=	editdetails.split(',');
			$("#titleSelector").text(arry[0]);
			$("#milesPerDay").val(arry[1]);
			$("#drivingCondition").val(arry[2]);
			$("#adddealer").hide();
			$("#changedealer").show();
	 		}
	 		window.scrollTo(0, 0);
			 	 }});
			 	 return false;
	
		
	}
function userlogin() {
var email=useremail;
var password=userpass;
		var dataString = {};
		dataString["email"] = email;
		dataString["password"] = password;
		if (email.length === 0) {
			$("#error-sucess").modal("show");
			$("#errorMsg")
					.html(
							"Please enter your email and password or register with MyMazda for access");
			return false;
		} else if (password.length === 0) {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter your password");
			return false;
		} else {
			if ($('#checkId').is(':checked')) {
				dataString = "email=" + email + "&password=" + password
						+ "&remember=true";
			} else {
				dataString = "email=" + email + "&password=" + password
						+ "&remember=false";
			}
			var url = "loginUser.action";
			$
					.ajax({
						type : "POST",
						url : url,
						data : dataString,
						beforeSend : function() {
							$("#loadingmessage").show();
						},
						success : function(result) {
							
							if (result == "no") {
								$("#error-sucess").modal("show");
								$("#errorMsg")
										.html(
												"We are sorry, the email address or password you entered does not match our records. Please check the spelling and try again. Password is case sensitive");
								return false;
							} else if (result == "serviceProblem") {
								$("#error-sucess").modal("show");
								$("#errorMsg")
										.html(
												"There is a problem, while communicating with web services. Please try again.");

							} else {
								$("#container-fluid").html(result);
								$("#selector").show();
								$("#addVehicle").show();
								$("#signin").hide();
								$("#myprofile").show();
								$("#dealerinfo").hide();
								$("#dealerinfo1").show();
								$("#header1").html(searchname);
								$("#address").html(searchstreet);
								$("#city").html(searchcity);
								$("#state").html(searchstate);
								$("#phone").html(searchphone);

							}
						},
						complete : function() {
							$("#loadingmessage").hide();

						}
					});
		}
	
return false;
}
	var joinmedata;
	var zip
	function locatelogin() {
		loginpage = '';
		var carlineDesc = $("#nickname").val();
		var vin = $("#regvin").val();
		var mileage = $("#mileage").val();
		var milesPerDay = $("#milesPerDay option:selected").val();
		var drivingCondition = $("#drivingCondition option:selected").val();
		joinmedata = carlineDesc + ',' + vin + ',' + mileage + ','
				+ milesPerDay + ',' + drivingCondition;
	}
	function editLogin() {
		loginpage = '';
		var carlineDesc = $("#titleSelector").val();
		var vin = $("#vinSelector").val();
		var mileage = $("#mileage").val();
		var milesPerDay = $("#milesPerDay option:selected").val();
		var drivingCondition = $("#drivingCondition option:selected").text();
		joinmedata = carlineDesc + ',' + vin + ',' + mileage + ','
				+ milesPerDay + ',' + drivingCondition;
	}

	function zipcode() {
		
		$.ajax({
					url : "searchByZip.action",
					data : {
						"zip" : zip
					},
					type : "GET",

					beforeSend : function() {
						$("#loadingmessage").show();
					},
					complete : function() {
						$("#loadingmessage").hide();
					},
					success : function(result) {

						$("#container-fluid").html(result);
						$("#features").show();
						$("#how-to").hide();
						$("#radiovalues").hide();
						$("#anotherSearch").show();
						$("#anotherSearch")
								.html(
										'<div class="radio"><input type="radio" id="radio" value="${dealer.name},${dealer.street},${dealer.searchcity},${dealer.searchstate},${dealer.searchzip},${dealer.phone},${dealer.searchserviceDealerId}" name="optradio"><label style="cursor: text;">'
												+ ''
												+'<h6>'+ searchname+'</h6>'
												+'<span id="space"style="display:none;">'+','+'</span>'
												+ '<span>'
												+ searchstreet
												+'<span id="spacecity"style="display:none;">'+','+'</span>'
												+ '<br>'
												+ '<span>'
												+ searchcity
												+ ','
												+ '</span><span>'+searchstate+'</span> <span>'+searchzip+'</span><br>'
												+ ''
												+ '<span>Phone: </span>'
												+'<span id="spaceid"style="display:none;">'+','+'</span>'
												+ searchphone
												+ '</span> <span id="dealerid"style="display:none;">'+','+searchserviceDealerId+'</span></label></div>');
						$("#radio").prop("checked", true);
						
						var arry = joinmedata.split(',');
						$("#nickname").val(arry[0]);
						$("#regvin").val(arry[1]);
						$("#mileage").val(arry[2]);
						$("#milesPerDay").val(arry[3]);
						$("#drivingCondition").val(arry[4]);
						$('.nav-tabs > .active').next('li').find('a').trigger(
								'click');
						$("#changeDealer").show();
						$(".Dealer").hide();
						$("#dealerslist").hide();
						$("#dealerslistdata").hide();
					},
					error : function(error) {
						$("#error-sucess").modal("show");
						$("#errorMsg").html("Error");
					}
				});

	}
	
	function addMoreVehicle() {

		$.ajax({
			url : "addVehicle.action",
			beforeSend : function() {
				$("#loadingmessage").show();
			},
			complete : function() {
				$("#loadingmessage").hide();
			},
			success : function(result) {
				$("#loadingmessage").hide();
				$("#container-fluid").html(result);
				$("#anotherSearch1").show();
				$("#hidedealer").show();
				$("#editdealer").hide();
				$("#anotherSearch1").text(searchname);
				$("#anotherSearch2").text(searchstreet);
				$("#anotherSearch3").text(searchphone);
				$("#anotherSearch5").text(searchcity + ',' + searchstate);
				//$("#anotherSearch6").text(searchstate);
				$("#regdealerID").text(searchserviceDealerId);
				$("#editdealerID").text(searchserviceDealerId);
				//window.scrollTo(0, 50);
			}
		});
		return false;
	}
	var editvehicle;
	function editVehicle() {
		addVehicle ='';
		editvehicle = 'editvehicles';
		$.ajax({
			url : "editNewVehicle.action",
			beforeSend : function() {
				$("#loadingmessage").show();
			},
			complete : function() {
				$("#loadingmessage").hide();
			},
			success : function(result) {

				$("#container-fluid").html(result);
				$("#hidedealer").hide();
				$("#dealer1").hide();
				window.scrollTo(0, 50);
			}
		});
		return false;

	}
	function updateMileage(section) {

		var carlineDesc = $("#titleSelector").val();
		var vin = $("#vinSelector").val();
		vin = vin.trim();
		var mileage = $("#mileage").val();
		var milesPerDay = $("#milesPerDay option:selected").val();
		var drivingCondition = $("#drivingCondition option:selected").val();
		var serviceDealerID;

		if (section == "Reg") {
			serviceDealerID = $("#EditregdealerID").text();
		} else if (section == "Edit") {
			serviceDealerID = $("#regeditdealerID").text();
		}
		var url = "updateMileage.action";
		var data = {};
		data["carlineDesc"] = carlineDesc;
		data["vin"] = vin;
		data["mileage"] = mileage;
		data["milesPerDay"] = milesPerDay;
		data["drivingCondition"] = drivingCondition;
		data["serviceDealerID"] = serviceDealerID;
		data = "carlineDesc =" + carlineDesc + "& vin =" + vin + "& mileage =" + mileage
				+ "& milesPerDay =" + milesPerDay + "& drivingCondition ="
				+ drivingCondition + "& serviceDealerID =" + serviceDealerID;

		if (carlineDesc == "") {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter a name for your Mazda.");
		} else if (vin == "") {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter your Mazda's VIN number.");
		} else if (vin.length > 17 || vin.length < 17) {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter the correct VIN number. Maximum allowed characters is 17.");
		} else if (isNaN(mileage) || mileage <= 0) {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter your current mileage.");
		}else if (milesPerDay == "") {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter your average mileage per day.");
			
		}
		else if (drivingCondition == "") {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please select a driving condition.");
			
		} else {
			$.ajax({
				url : url,
				type : "POST",
				data : data,
				beforeSend : function() {
					$("#loadingmessage").show();
				},
				complete : function() {
					$("#loadingmessage").hide();
				},
				success : function(result) {
					if (result == "error") {
						$("#error-sucess").modal("show");
						$("#errorMsg")
								.html(
										"We regret that the system is currently experiencing network issues, and we are unable to service your request. Please try again after some time.");
						$("#actionMessage").show();
						return false;
					} else {
						$("#success-msg").modal("show");
						$("#successMsg")
								.html(
										" Your vehicle updated successfully.");
						 $('.modal-backdrop').hide();
						//$("#container-fluid").html(result);
						setTimeout(function() {
							$("#success-msg").modal("hide");
							applyToUpdateSelectedVehical(vin);
						}, 1000); 
						$("#servicelink").text(serviceUrl);
					}
					window.scrollTo(0, 0);
				},
				error : function(error) {
					$("#error-sucess").modal("show");
					$("#errorMsg").html("Error");
				}
			});
		}
		return false;
	}

	function back() {
    if (add === 'adddealerback') {
			
			backaddMoreVehicle();
		} else if (edit==='editdealerback') {
			
			backeditVehicleform();
		}
		/* else if (addVehicle === 'addMoreVehicle') {
			backuserlogin();
		} else if (editvehicle === 'editvehicles') {
			backuserlogin();
		} */ else if (loginpage === 'loginscreen') {
			backuserlogin();
		}

		else {
			backzipcode();

		}
		return false;
	} 
	function cancel(){
		
		window.location="myMazda.action";
		return false;
	}
	var zip
	var backDealer;
	function backzipcode() {
		$.ajax({
			url : "searchByZip.action",
			data : {
				"zip" : zip
			},
			type : "GET",

			beforeSend : function() {
				$("#loadingmessage").show();
			},
			complete : function() {
				$("#loadingmessage").hide();
			},
			success : function(result) {

				$("#container-fluid").html(result);
				$("#features").show();
				$("#how-to").hide();
				$('.nav-tabs > .active').next('li').find('a').trigger('click');
				var arry = joinmedata.split(',');
				$("#nickname").val(arry[0]);
				$("#regvin").val(arry[1]);
				$("#mileage").val(arry[2]);
				$("#milesPerDay").val(arry[3]);
				$("#drivingCondition").val(arry[4]);
				$("#dealerslistdata").show();
				$("#dealerslist").show();
				//$("#dealerslist").hide();
				
				if(backDealer.length>10){
					//$("#anotherSearch").append(backDealer);
					$("#radiovalues").hide();
					$("#anotherSearch").show();
					$("#changeDealer").show();
					$("#adddealer").hide();
					$("#adddealer1").hide();
					$("#dealerslist").hide();
					$("#dealerslistdata").hide();
					
					$("#anotherSearch")
					.html(
							'<div class="radio"><input type="radio" id="radio" value="${dealer.name},${dealer.street},${dealer.searchcity},${dealer.searchstate},${dealer.phone}" name="optradio"><label style="cursor: text;">'
									+ ''
									+'<h6>'+ searchname+'</h6>'									
									+ '<span>'
									+ searchstreet
									+ '<br>'
									+ '<span>'
									+ searchcity
									+ ','
									+ searchstate
									+ '</span><br>'
									+ ''
									+ '<span>phone: </span>'
									+ searchphone
									+ '</span></label></div>');
					$("#radio").prop("checked", true);
				}
				
			},
			error : function(error) {
				$("#error-sucess").modal("show");
				$("#errorMsg").html("Error");
			}
		});
	

	}
	function backeditVehicleform() {
		$.ajax({
			url : "editNewVehicle.action",
			beforeSend : function() {
				$("#loadingmessage").show();
			},
			complete : function() {
				$("#loadingmessage").hide();
			},
			success : function(result) {
				$("#loadingmessage").hide();
				$("#container-fluid").html(result);
			var arry=	editdetails.split(',');
			$("#titleSelector").text(arry[0]);
			$("#milesPerDay").val(arry[1]);
			$("#drivingCondition").val(arry[2]);
				//window.scrollTo(0, 50);
			}
		});
		return false;

	}
	function backaddMoreVehicle() {
		$.ajax({
			url : "addVehicle.action",
			beforeSend : function() {
				$("#loadingmessage").show();
			},
			complete : function() {
				$("#loadingmessage").hide();
			},
			success : function(result) {
				$("#loadingmessage").hide();
				$("#container-fluid").html(result);
				//window.scrollTo(0, 50);
				var data=$("#addDealerStreet").text();
				if(data===''){
					$("#adddealer").show();
					$("#changedealer").hide();
				}
				else{
					$("#changedealer").show();
					$("#adddealer").hide();
				}
				if(backDealerName===''){
					$("#editdealer").hide();
				}
				var arry=	adddetails.split(',');
				$("#titleSelector").text(arry[0]);
				$("#milesPerDay").val(arry[1]);
				$("#drivingCondition").val(arry[2]);
				$("#regdealerID").text(arry[3]);
			}
		});
		return false;
	}
	function backuserlogin() {

		window.location="myMazda.action";
		
		return false;
	}

	var oldId = '${selectedVehical.vin}';
	var loginvin;
	function applyToUpdateSelectedVehical(vin,flag){
		loginvin=vin;
		var url = "applyToUpdateSelectedVehical.action";
		/* var selectorval = $(this).text();
		$("#selectVehicle").html(selectorval); */
		if(flag == 'undefined' || flag == null)
		{
		flag = 'true';
		}
	if(flag == 'true')
		{
	var data = {};
	data["vin"] = vin;
	var state = { 'url': url,data:data};
	history.pushState(state ,'mymazda', '#MyVehicle');
		}
		//$("#recentPosts").hide();
						$.ajax({
							type : "POST",
							url : url,
							data : {"vin":vin},
							beforeSend : function() {
								$("#loadingmessage").show();
							},success : function(result) {
								$("#container-fluid").html(result);
								window.scrollTo(0, 0);
							},complete : function() {
								$("#loadingmessage").hide();
							}
						});
		return false;
	}
	
	
	var edit;
	function editVehicle(carlineDesc, mileage, vin,milesPerDay,drivingCondition,flag) {
		
		editvehicle = 'editvehicles';
		var data = {};
		data["carlineDesc"] = carlineDesc;
		data["mileage"] = mileage;
		data["vin"] = vin;
		data["milesPerDay"] = milesPerDay;
		data["drivingCondition"] = drivingCondition;
		if(flag == 'undefined' || flag == null)
		{
		flag = 'true';
		}
		if(flag=='true')
			{
		var url="editNewVehicle.action";
        var state = { 'url': url , data : data};
		history.pushState(state ,'mymazda', '#editVehicle');
			}
		
		$.ajax({
			url : "editNewVehicle.action",
			data : data,
			beforeSend : function() {
				$("#loadingmessage").show();
			},
			complete : function() {
				$("#loadingmessage").hide();
			},
			success : function(result) {
				
				$("#container-fluid").html(result);
				$("#hidedealer").hide();
				$("#dealer1").hide();
				$("#headerName").text(carlineDesc);
				$("#milesPerDay").val(milesPerDay);
				$("#drivingCondition").val(drivingCondition);
				window.scrollTo(0, 0);
			}
		});
		return false;

	}
	function editVehicleform() {
		$.ajax({
			url : "editNewVehicle.action",
			beforeSend : function() {
				$("#loadingmessage").show();
			},
			complete : function() {
				$("#loadingmessage").hide();
			},
			success : function(result) {
				$("#loadingmessage").hide();
				$("#container-fluid").html(result);
				$("#anotherSearch1").show();
				$("#hidedealer").show();
				$("#editdealer").hide();
				$("#anotherSearch1").text(searchname);
				$("#anotherSearch2").text(searchstreet);
				$("#anotherSearch3").text(searchphone);
				$("#anotherSearch5").text(searchcity + ',' + searchstate);
				//$("#anotherSearch6").text(searchstate);
				$("#regdealerID").text(searchserviceDealerId);
				$("#editdealerID").text(searchserviceDealerId);
				var arry = joinmedata.split(',');
				$("#titleSelector").val(arry[0]);
				$("#vinSelector").val(arry[1]);
				$("#mileage").val(arry[2]);
				$("#milesPerDay").val(arry[3]);
				$("#drivingCondition").val(arry[4]);
				window.scrollTo(0, 0);
			}
		});
		return false;

	}

var editDealer;
var backDealerName;
var adddetails;
	function preferredsearchDealer() {
		add='adddealerback';
		edit='';
		editDealer='editDealersearch';
		headsearch='';
		 var titleSelector =$("#titleSelector").val();
	     var vin = $("#vinSelector").val();
	     var mileage = $("#mileage").val();
	     var milesPerDay = $("#milesPerDay option:selected").val();
	     var drivingCondition =  $("#drivingCondition option:selected").val();
	     var dealerName = $("#adddelername").text();
	     var dealerStreet = $("#addDealerStreet").text();
	     var dealerCity = $("#addDealerCity").text();
	     var dealerState = $("#addDealerSate").text();
	     var dealerPhone = $("#addDealerphone").text();
	    var  addDealerzip= $("#addDealerzip").text();
	    var dealerID=$("#regdealerID").text();
	    backDealerName=dealerName;
	     var data={};
	     data ["name"] = titleSelector;
	     data ["mileage"] = mileage;
	     data ["milesPerDay"] = milesPerDay;
	     data ["drivingCondition"] = drivingCondition;
	     
	     data ["dealerName"] =dealerName;
	     data ["dealerStreet"] =dealerStreet;
	     data ["dealerCity"] =dealerCity;
	     data ["dealerState"] =dealerState;
	     data ["dealerZip"] =addDealerzip;
	     data ["dealerPhone"] =dealerPhone;
	     //data ["serviceDealerID"] = serviceDealerID;
	     data ["vin"] = vin;
	     data="carlineDesc ="+titleSelector+"& mileage ="+mileage+"& milesPerDay ="+milesPerDay+"& vin ="+vin+"& drivingCondition ="+drivingCondition+"& dealerName ="+dealerName+"& dealerStreet ="+dealerStreet+"& dealerCity ="+dealerCity+"& dealerState ="+dealerState+"& dealerPhone ="+dealerPhone+"& dealerZip ="+addDealerzip;
	     data = data+"&addVehicleFlag=true&editUpdateFlag=Add";
	     adddetails=titleSelector+','+milesPerDay+','+drivingCondition+','+dealerID;
	     var url = "addPreferedDealer.action";
			$.ajax({
				
				data : data,
				type : "GET",
				url : url,
	            
				 beforeSend : function() {
						$("#loadingmessage").show();
					},
				complete : function() {
					$("#loadingmessage").hide();
				},
				success : function(result) {
				$(".LocateDealer-radio").hide();
				$("#container-fluid").html(result);
				window.scrollTo(0, 0);
			},
			error : function(error) {
				$("#error-sucess").modal("show");
				$("#errorMsg").html("Error");

			}
		});
			//}
	}
	
	var editpreferredealer;
	var edit;
	var add;
	var editdetails;
	function preferredsearchDealerEdit() {
		edit='editdealerback';
		add='';
		editpreferredealer='editdealer';
		headsearch='';
		 var titleSelector =$("#titleSelector").val();
	     var vin = $("#vinSelector").val();
	     vin=vin.trim();
	     var mileage = $("#mileage").val();
	     var milesPerDay = $("#milesPerDay option:selected").val();
	     var drivingCondition =  $("#drivingCondition option:selected").val();
	     var dealerName = $("#adddelername").text();
	     var dealerStreet = $("#addDealerStreet").text();
	     var dealerCity = $("#addDealerCity").text();
	     var dealerState = $("#addDealerSate").text();
	     var dealerZip = $("#addDealerzip").text();
	     var dealerPhone = $("#addDealerphone").text();
	     
	     var data={};
	     data ["name"] = titleSelector;
	     data ["mileage"] = mileage;
	     data ["milesPerDay"] = milesPerDay;
	     data ["drivingCondition"] = drivingCondition;
	     //data ["serviceDealerID"] = serviceDealerID;
	     data ["vin"] = vin.trim();
	     data ["dealerName"] =dealerName;
	     data ["dealerStreet"] =dealerStreet;
	     data ["dealerCity"] =dealerCity;
	     data ["dealerState"] =dealerState;
	     data ["dealerZip"] =dealerZip;
	     data ["dealerPhone"] =dealerPhone;
	     data="carlineDesc ="+titleSelector+"& mileage ="+mileage+"& milesPerDay ="+milesPerDay+"& vin ="+vin+"& drivingCondition ="+drivingCondition+"& dealerName ="+dealerName+"& dealerStreet ="+dealerStreet+"& dealerCity ="+dealerCity+"& dealerState ="+dealerState+"& dealerZip ="+dealerZip+"& dealerPhone ="+dealerPhone;
	     data = data+"&addVehicleFlag=true&editUpdateFlag=edit";
	     editdetails=titleSelector+','+milesPerDay+','+drivingCondition;
	     var url = "addPreferedDealer.action";
	     if (vin == "") {
				$("#error-sucess").modal("show");
				$("#errorMsg").html("Please enter your Mazda's Vin number.");
			} else if (vin.length > 17 || vin.length < 17) {
				$("#error-sucess").modal("show");
				$("#errorMsg").html("Please enter the correct VIN number. Maximum allowed characters is 17.");
			}else if (isNaN(mileage) || mileage <= 0) {
				$("#error-sucess").modal("show");
				$("#errorMsg").html("Please enter your current mileage.");
			} else{
			
			$.ajax({
				
				data : data,
				type : "GET",
				url : url,
	            
				 beforeSend : function() {
						$("#loadingmessage").show();
					},
				complete : function() {
					$("#loadingmessage").hide();
				},
				success : function(result) {
				$(".LocateDealer-radio").hide();
					$("#container-fluid").html(result);
					window.scrollTo(0, 0);
				},
				error : function(error) {
					$("#error-sucess").modal("show");
		 			$("#errorMsg").html("Error");	
					
				}
			});
		}
	}
	function showUpdateSelectedVehicle(vin){
        var data={};
      data ["vin"] = vin;
        $.ajax({
              type : "POST",
              url : "showUpdateSelectedVehicleRecall.action",
              data :data,
              beforeSend : function() {
                    $("#loadingmessage").show();
              },
        complete : function() {
              $("#loadingmessage").hide();
        },
              success : function(result) {
                    
                    $("#container-fluid").html(result);
              },complete : function() {
              }
        });
     return false;
     }
	function maintenanceSchedule(flag){
		if(flag == 'undefined' || flag == null)
		{
		flag = 'true';
		}
	if(flag == 'true')
		{
		var data = {};
	var state = { 'url': 'maintenanceSchedule.action',data:data};
	history.pushState(state ,'mymazda', '#maintainschedule');
}
		 $.ajax({
             url : "maintenanceSchedule.action",
             beforeSend : function() {
                   $("#loadingmessage").show();
             },
       complete : function() {
             $("#loadingmessage").hide();
       },
             success : function(result) {
                   $("#container-fluid").html(result);
                   window.scrollTo(0, 0);
             }
       });
		 return false;
		
	}
	function contactUs(){
		$("#forgot-password").hide();		 
		 $.ajax({
             url : "contactUs.action",
             beforeSend : function() {
                   $("#loadingmessage").show();
             },
       complete : function() {
             $("#loadingmessage").hide();
       },
             success : function(result) {
                   
                   $("#container-fluid").html(result);
                  
             }
       });
		 return false;		 
	}
	var contactData;
	function  contactDetails(flag){
		var data={};
		var vin= $("#conctactVin").val();
		var fromName=$("#fromname").val();
		var yourMail=$("#yourMail").val();
		var toMail=$("#toMail").val();
		var subject=$("#subject").val();
		var comment=$("#comment").val();
		if(vin.length === 0){
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter your VIN number.");
			return false;
		}
		if(vin.length > 17 || vin.length < 17){
			 $("#error-sucess").modal("show");
			 $("#errorMsg").html("Maximum '17' characters allowed for Vin");
			return false;
		} 
		if(fromName.length === 0){
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter your first and last name.");
				return false;
			}
		if(yourMail.length === 0){
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter your email address.");
				return false;
			}
		if(!IsEmail(yourMail)){
			$("#error-sucess").modal("show");
			$("#errorMsg").html("The email address you entered is invalid. Please try again.");
	    return false;
		}
		 if(toMail.length === 0){
			 $("#error-sucess").modal("show");
				$("#errorMsg").html("Please enter TO email address.");
					return false;
			} 
		 if(!IsEmail(toMail)){
				$("#error-sucess").modal("show");
				$("#errorMsg").html("Invalid Email Address");
		   return false;
			}
		if(subject.length === 0){
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter a subject.");
				return false;
			}
		
		data["vin"]=vin;
		data["to"]=toMail;
		data["subject"]=subject;
		data["message"]=comment;
		data["email"]=yourMail;
		data["from"]=fromName;
		data="vin="+vin+"&to="+toMail+"&subject="+subject+"&message="+comment+"&email="+yourMail+"&from="+fromName+"&mobilePhone=";
		var url="sendMail.action";
		if(flag == 'undefined' || flag == null)
		{
		flag = 'true';
		}
		if(flag == 'true')
		{
			var state = { 'url': url,data:data};
			history.pushState(state ,'mymazda', '#contactUs');
		}
		
		contactData = data;
		$('#confirmBtnOperation').attr('onClick', 'sendContactMazdaEmail()');
		$("#confirm-msg").modal("show");
		$("#confirmMsg").html("If you are sure about sending this email, please click OK to send.");
		
		return false;
	}	
	
	
	function sendContactMazdaEmail() {	
	
		$('#sendEmailBtn').attr('onClick', '');
		$('#sendEmailBtn').attr('disabled', 'disabled');
		var url = "sendMail.action";
		var data = contactData;
		
		$.ajax({
			data : data,
			type : "GET",
			url : url,
            
			 beforeSend : function() {
					$("#loadingmessage").show();
				},
			complete : function() {
				$("#loadingmessage").hide();
			},
			success : function(result) {
				if(result == ""){
	 			 	$("#success-msg").modal("show");
					$("#successMsg").html("Your email has been sent! Thank you for contacting Mazda.");
					$('.modal-backdrop').hide();
					setTimeout(function() {
						homeClick(); 
						$("#success-msg").modal("hide");
					},3000); 
				
				} else {
					$('#sendEmailBtn').attr('onClick', 'return contactDetails();');
					$('#sendEmailBtn').attr('disabled', false);
					$("#error-sucess").modal("show");
		 			$("#errorMsg").html("Failed..! Please try again.");
				}
					
			},
			error : function(error) {
				$('#sendEmailBtn').attr('onClick', 'return contactDetails();');
				$('#sendEmailBtn').attr('disabled', false);
				$("#error-sucess").modal("show");
	 			$("#errorMsg").html("Failed..! Please try again.");
			}
		});
		return false;
	}
	function IsEmail(email) {
		  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		  return regex.test(email);
	}
	//Service Reminders section starts...
	
	function serviceReminder(vin,flag){
		var car=$("#selectVehicle").text();	
		var data={};
	    data ["vin"] = vin;
		if(flag == 'undefined' || flag == null)
		{
		flag = 'true';
		}
		if(flag == 'true')
		{
		var state = { 'url': 'serviceReminder.action',data:data};
		history.pushState(state ,'mymazda', '#serviceReminders');
		}
    	$.ajax({
    		type : "POST",
    		url:"serviceReminder.action",
			data : data,
    	
    	beforeSend : function() {
			$("#loadingmessage").show();
		},
		complete : function() {
			$("#loadingmessage").hide();
		}, 
   		success : function(result) {
   			$("#container-fluid").html(result);
   			$('#selectVehicle').text(car);
   			$('#selector'+oldId).removeClass("active");
   			$('#selector'+vin).addClass("active");
   			$('#car'+vin).addClass("active");
   			$('#listSelector1').addClass("active");
   			
   			if($("#selector"+vin).attr("data-id")){
   			
   				$('.tab-dropdown').addClass("active");	
   			} 
   			$("#"+vin).addClass(" fa-minus-circle");
   			 $("#"+vin).attr("data-id", "2");
   			oldId = vin;
   		 window.scrollTo(0, 0);
   		},
   		error:function(error) {
   				//alert("error");
   		}
   		});
   		return false;
   	}
	var oldId='${selectedVehical.vin}';
	function showSelectedTab(vin){
		$(".js-tabcollapse-panel-body").hide();
		var car=$('#selectVehicle').text();
    	var changecss= $("#"+vin).attr("data-id");
    	var data={};
	    data ["vin"] = vin;
    	if(changecss==='1'){
    					$.ajax({
    						type : "POST",
    						url : "serviceReminderOfVehical.action",
    						data : data,
    						beforeSend : function() {
    							$("#loadingmessage").show();
    						},
    						success : function(result) {
    							$("#container-fluid").html(result);
    							$('#selectVehicle').text(car);
    							 $('#selector'+oldId).removeClass("active");
     							$('#selector'+vin).addClass("active"); 
    							
    							 $("#"+vin).attr("data-id", "2");
    							 $("#"+vin).addClass("fa-minus-circle"); 
    							 $("#"+vin). removeClass("fa-plus-circle");
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
	var oldpage=1;
  	jQuery(document).ready(function() {
  		selectedVehicleId = '${selectedVehical.vin}';
  		$('#selector'+selectedVehicleId).addClass("active");
  		$('#listSelector1').addClass("active");
  		$("#couponContainerId .couponClass").hide();
  		$("#coupon-0").show();
  		$("#couponLinkId a").css('color','#d00000');
  		
  	});
  	
  		function prevServicePage(){
  		if(oldpage>1){
  			couponNo(oldpage-1);
  		}
  	}
  	
  	function nextServicePage(){
  		if(oldpage<(counter-1)){
  			var nextpage =(oldpage);
  			++nextpage;
  			couponNo(nextpage);
  		}
  	}
  	function couponNo(page){
		$("#couponContainerId .couponClass").hide();
		$("#coupon-"+(page-1)).show();
		$("#listSelector"+page+" a").css({'background-color': '#7e0000'});
		$("#listSelector"+oldpage+" a").removeAttr('style');
		oldpage = page;
	}
	
	function PrintElem(elem)
    {
        Popup($(elem).html());
        $("#reminderprint").show();
    }

    function Popup(data) 
    {
        var mywindow = window.open('', 'my div', 'height=600,width=900');
        mywindow.document.write('<html><head><title>My Mazda</title>');
		mywindow.document.write('<script src="//assets.adobedtm.com/0b1a9905a3bfe56ba99f4bcd3560048b3d9faf5f/satelliteLib-9dac536f1ca76627327a770a7af6d1ac98184c84-staging.js"><\/script>');
        mywindow.document.write('</head><body >');
        mywindow.document.write("<div style='border:1px solid #d00; color: #535353;font-family:verdana;font-size: 14px;line-height:18px;'>");
        mywindow.document.write(data); 
        mywindow.document.write("<div style='clear:both'></div></div>");   
		mywindow.document.write('<script type="text/javascript">_satellite.pageBottom();<\/script>');
        mywindow.document.write('</body></html>');

        mywindow.document.close(); // necessary for IE >= 10
        mywindow.focus(); // necessary for IE >= 10

        mywindow.print();
        mywindow.close();

        return false;
    }
    var oldpage = null;
    var flag=0;
    var previous = null;
    function toggle(selectedLi) {//alert("yyyyy")
      $(".panel-collapse collapse in").hide();
      var ele = $("#toggleText-"+selectedLi);
      if(selectedLi == oldpage) {
            if(flag==0){
                  flag=1;
                  ele.show();
                  $("#img"+selectedLi).removeClass("indicator fa fa-angle-down pull-right");
                  $("#img"+selectedLi).addClass("indicator fa fa-angle-up pull-right");
                  
            }else{//alert("*******");
                  flag=0;
                  ele.hide();             
                  $("#img"+selectedLi).removeClass("indicator fa fa-angle-up pull-right");
                  $("#img"+selectedLi).addClass("indicator fa fa-angle-down pull-right");
                  
            }
            }else {
           ele.show();
            $("#img"+selectedLi).removeClass("indicator fa fa-angle-down pull-right");
            $("#img"+selectedLi).addClass("indicator fa fa-angle-up pull-right");   
            $("#toggleText-"+oldpage).hide();
            flag=1;
            if(previous!=null){
                  $("#img"+previous).removeClass("indicator fa fa-angle-up pull-right");
                  $("#img"+previous).addClass("indicator fa fa-angle-down pull-right");
            }
      }
      
      oldpage = selectedLi;
      previous = selectedLi;
        }

    function sendCoupoun(){
		var discalimer = ($("#toggleText-"+(oldpage)+" #couponDiscalimerId").text());
		var emailmessage = ($("#toggleText-"+(oldpage)+" #couponDetailDivId").text());
		var couponTitle = ($("#toggleText-"+(oldpage)+" #couponHeader").text());
		var couponValue = ($("#toggleText-"+(oldpage)+" #couponValueId").text());
		var email = $("#emailid").val();
		
		 var atpos = email.indexOf("@");
    	var dotpos = email.lastIndexOf("."); 
    	var data={};
	     data ["title"] = couponTitle;
	     data ["disclaimer"] = discalimer;
	     data ["email"] = email;
	     data ["description"] = emailmessage;
	     data ["deal"] = couponValue;
	     data="title ="+couponTitle+"& disclaimer ="+discalimer+"& email ="+email+"& description ="+emailmessage+"& deal ="+couponValue;
	     //alert(data);
    	var url = "sendCouponByEamil.action";
    	if(email == ""){
			  $("#Mail").show();
				$("#cuponsendmail").show();
					$("#cuponsendmail").html("Please enter an email address");
			}
			else if(atpos<1 || dotpos<atpos+2 || dotpos+2 >= email.length){
				$("#Mail").show();
				$("#cuponsendmail").show();
					$("#cuponsendmail").html("Please enter valid email address");
			}else{ 
				$.ajax({
					type : "POST",
					url : url,
					data : data,
        	
        	beforeSend : function() {
				$("#loadingmessage").show();
			},
			    complete:function(){
			    	$("#loadingmessage").hide();
			    	 $("#Mail").hide();
			    },
        		success:function(result){
        			$("#success-msg").modal("show");
      	 			 $("#successMsg").html(result);
      	 			 $('.modal-backdrop').hide();
      	 			 setTimeout(function() {
      					 $("#success-msg").modal("hide");
      					}, 1000);
      	 			$('#emailid').val('');
  			 	 }
  			 	});
				}
				return false;	
				} 
	
	//Delete Vehicle
	var delVin = '';
	function deleteVehicle(vin, carlineDesc) {
		//alert('vin: '+vin+', carlineDesc:'+carlineDesc);
		if(vin != ""){
			delVin = vin;
			$('#confirmBtnOperation').attr('onClick', 'return confirmDeleteVehicle()');
			$("#confirm-msg").modal("show");
			$("#confirmMsg").html("You are about to remove this vehicle \"" + carlineDesc + " ("+vin+")\" and all it's contents from your list. <br><br>Please click OK to delete.");
		} 
		return false;
	}
	
	function confirmDeleteVehicle() {	
	
		//alert('delVin: '+delVin);
		if(delVin != '') {
			var dataString =  "vin=" + delVin 
			//alert('dataString: '+dataString);
			var url = "deleteVehicle.action";
			
			$.ajax({
				type : "POST",
				url : url,
				data : dataString,
				beforeSend : function() {
					$("#loadingmessage").show();
				},
				complete : function() {
					$("#loadingmessage").hide();
				},
				success : function(result) {
					//alert('result: '+result);
					if (result == "success") {
						$("#success-msg").modal("show");
						$("#successMsg").html("Vehicle deleted successfully.");
						$('.modal-backdrop').hide();
						setTimeout(function() {
							homeClick(); 
							$("#success-msg").modal("hide");
						},3000); 
					} else {
						$("#error-sucess").modal("show");
						$("#errorMsg").html("Vehicle not deleted. Please try again.");
					}
				},
				
			});
			
		}
		return false;
	}
	
	/* End of Service Reminders Section ends... */
	function getFocus(){
		window.location="welcome.action";
		$('#email').focus();
		return false;
	}
</script>