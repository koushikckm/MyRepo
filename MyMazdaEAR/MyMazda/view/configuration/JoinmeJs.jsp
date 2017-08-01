
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
	
$('#myTab').tabCollapse(); 
function mobilecollapse(){
	$(function(){
	function toggleChevron(p) {			   
		$(p.target)
		.prev('.panel-heading')
		.find("i")
		.toggleClass('fa-plus-circle fa-minus-circle');
	}
	$('#myTab-accordion').on('hidden.bs.collapse', toggleChevron);
	$('#myTab-accordion').on('shown.bs.collapse', toggleChevron);

	});
	}
(function ($) {
	
		        //$('#drivingCondition').change(function() { //jQuery Change Function
		              // var opval = $(this).val(); //Get value from select element
		              // if(opval=="secondoption"){ //Compare it and if true
		                 // $('#driving-condition').modal("show"); //Open Modal
		              // }
		        // }); 
				//called when key is pressed in textbox
				  $("#mileage").keypress(function (e) {
				     //if the letter is not digit then display error and don't type anything
				     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
				        //display error message
				       // $("#errmsg").html("Digits Only").show().fadeOut("slow");
				               return false;
				    }
				   });
				//called when key is pressed in textbox
				  $("#zipcode").keypress(function (e) {
				     //if the letter is not digit then display error and don't type anything
				     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
				        //display error message
				       // $("#errmsg").html("Digits Only").show().fadeOut("slow");
				               return false;
				    }
				   });
				//called when key is pressed in textbox
				  $("#phonenumber").keypress(function (e) {
				     //if the letter is not digit then display error and don't type anything
				     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
				        //display error message
				       // $("#errmsg").html("Digits Only").show().fadeOut("slow");
				               return false;
				    }
				   });
				  $('#btnReview').click(function(){
					  $('.nav-tabs > .active').next('li').find('a').trigger('click');
					  document.getElementById("how-to-collapse").className = "panel-collapse collapse";
					  document.getElementById("features-collapse").className = "panel-collapse collapse in";
				   });
				   $('#Backtab').click(function(){
					  $('.nav-tabs > .active').prev('li').find('a').trigger('click');
					  document.getElementById("how-to-collapse").className = "panel-collapse collapse in";
					  document.getElementById("features-collapse").className = "panel-collapse collapse";
				   });
				  
})(jQuery);

var dataString;
var accountData;
function getDetails(){
	setInterval(function (){
		dataLayer.page.name = 'register_vehicle';
		dataLayer.page.URL = window.location.href;
		dataLayer.site.sectionHistorical = 'owners';
		dataLayer.site.section = 'mymazda';
		dataLayer.site.subsection = 'owners';
		dataLayer.form.name = 'create_account_vehicle';
		dataLayer.form.type = 'create_account';
		dataLayer.user.type = 'x';
	});
	$("#addVehicle").hide();
	dataString={};
	var firstName=$("#firstname").val();
	var lastName=$("#lastname").val();
	var street1address=$("#streetaddress1").val();
	var street2address=$("#streetaddress2").val();
	var city=$("#city").val();
	var state=$("#state option:selected").text();
	var zip=$("#zipcode").val();
	var mobilePhone=$("#phonenumber").val();
	var email=$("#email").val();
	var confEmail=$("#confirmemail").val();
	var password=$("#password").val();
	var confPassword=$("#repassword").val();
	var regEx = /^[a-zA-Z0-9@*$.!%\\n\\t]{4,15}$/g;
	//var phoneType=$("#phonetype option:selected").text();
	if(firstName.length === 0){
		$("#error-sucess").modal("show");
		$("#errorMsg").html("Please enter your first name.");
		return false;
	}
	if(lastName.length === 0){
		$("#error-sucess").modal("show");
		$("#errorMsg").html("Please enter your last name.");
			return false;
		}
	if(street1address.length === 0){
		$("#error-sucess").modal("show");
		$("#errorMsg").html("Please enter your street address.");
			return false;
		}
	/* if(street2address.length === 0){
		alert("Please enter your street2address");
			return false;
		} */
	if(city.length === 0){
		$("#error-sucess").modal("show");
		$("#errorMsg").html("Please enter your city.");
			return false;
		}
	if(state.length === 0){
		$("#error-sucess").modal("show");
		$("#errorMsg").html("Please select your state");
			return false;
		}
	if(zip.length === 0){
		$("#error-sucess").modal("show");
		$("#errorMsg").html("Please enter your zip code.");
			return false;
		}
	if(zip.length > 5 || zip.length < 5){
		$("#error-sucess").modal("show");
		$("#errorMsg").html("Maximum '5' Character allowed for Zip");
		return false;
	}
	if(mobilePhone.length === 0 || mobilePhone.length != 10){
		$("#error-sucess").modal("show");
		$("#errorMsg").html("Please enter a valid phone number.");
			return false;
		}
	if(email.length === 0){
		$("#error-sucess").modal("show");
		$("#errorMsg").html("Please enter your email address.");
			return false;
		}
	if(!IsEmail(email)){
		$("#error-sucess").modal("show");
		$("#errorMsg").html("The email address you entered is invalid. Please try again.");
   return false;
	}
	if(confEmail.length === 0){
		$("#error-sucess").modal("show");
		$("#errorMsg").html("Please confirm your email address.");
			return false;
		}
	if(!IsEmail(confEmail)){
		$("#error-sucess").modal("show");
		$("#errorMsg").html("The email address you entered is invalid. Please try again.");
   		return false;
	}
	if(confEmail != email){
		$("#error-sucess").modal("show");
		$("#errorMsg").html("Your email and confirm email entries must match. Please try again.");
		return false;
	}
	if(password.length === 0){
		$("#error-sucess").modal("show");
		$("#errorMsg").html("Please enter your password.");
			return false;
		}
	if(confPassword.length === 0){
		$("#error-sucess").modal("show");
		$("#errorMsg").html("Please enter your confirm password.");
			return false;
		}
	
	if(password != confPassword){
		$("#error-sucess").modal("show");
		$("#errorMsg").html("Your password and confirm password entries do not match. Please try again.");
		return false;
	}
	if(!confPassword.match(regEx)){
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Password must be 4-15 characters long. Can contain alphanumeric characters and the following symbols @ * $ . ! % ");
			return false;
	}
	/* var flag='';
	if ($('#checkbox1').is(":checked"))
	{flag = "true";
	}else{
		flag = "false";
	} */
	
	dataString ["firstName"] = firstName;
	dataString ["lastName"] = lastName;
	dataString ["street1address"] = street1address;
	dataString ["street2address"] = street2address;
	dataString ["city"] = city;
	dataString ["state"] = state;
	dataString ["zip"] = zip;
	dataString ["email"] = email;
	dataString ["confEmail"] = confEmail;
	dataString ["password"] = password;
	dataString ["confPassword"] = confPassword;
	//dataString ["flag"] = flag;
	dataString ["mobilePhone"] = mobilePhone;
	//dataString ["phoneType"] = phoneType;
	dataString = "firstName="+firstName+"&lastName="+lastName+"&street1address="+street1address+"&street2address="+street2address+
	"&city="+city+"&state="+state+"&zip="+zip+"&email="+email+"&confEmail="+confEmail+"&password="+password+"&mobilePhone="+mobilePhone+"&confPassword="+confPassword;
	 accountData=firstName+','+lastName+','+street1address+','+street2address+','+city+','+state+','+zip+','+email+','+confEmail+','+mobilePhone+','+password+','+confPassword;
	$.ajax({
		url : "searchByZip.action",
		data :  {
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
			//$("#addVehicle").show(); 
			//$("#creaateacc").hide(); 
			$('.nav-tabs > .active').next('li').find('a').trigger('click');
			if(changevalue ==='clickbackbutton'){
			addvehicledata();
			 window.scrollTo(0, 0);
			}
		},
		error : function(error) {
			$("#error-sucess").modal("show");
			 $("#errorMsg").html('Error');
		}
	});
	return false;
	
}
function addvehicledata(){
	var arry=vehicledata.split(',');
	 $("#nickname").val(arry[0]);
		$("#regvin").val(arry[1]);
		$("#mileage").val(arry[2]);
		$("#milesPerDay").val(arry[3]);
		$("#drivingCondition").val(arry[4]);
}

function joinMymazda(flag){
	var name;
	var street;
	var phone;
	var serviceDealerID;
	var dataString1={};
	var arry='';
	var carlineDesc=$("#nickname").val();
	var vin=$("#regvin").val();
	var mileage=$("#mileage").val();
	var milesPerDay=$("#milesPerDay option:selected").val();
	var drivingCondition=$("#drivingCondition option:selected").val();
	var radioval=$('input[name=optradio]:checked').val();
	var anotherSearch=$("#anotherSearch").text();
	if(carlineDesc .length === 0){
		$("#error-sucess").modal("show");
		$("#errorMsg").html("Please enter a name for your Mazda.");
		return false;
	}
	if(vin .length === 0){
		$("#error-sucess").modal("show");
		$("#errorMsg").html("Please enter your Mazda's VIN number.");
		return false;
	}
	if(mileage .length === 0){
		$("#error-sucess").modal("show");
		$("#errorMsg").html("Please enter your current mileage.");
		return false;
	}
	 if(vin.length > 17 || vin.length < 17){
		 $("#error-sucess").modal("show");
		 $("#errorMsg").html("Please enter the correct VIN number. Maximum allowed characters is 17.");
		return false;
	} 
	  if (milesPerDay == "") {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter your average mileage per day.");
			return false;
		}
		 if (drivingCondition == "") {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please select driving condition.");
			return false;
		} 
	 if(anotherSearch.length>20){
	 if(anotherSearch  != 'undefined' && anotherSearch  != '' && anotherSearch  != null){
		var arry1=anotherSearch.split(',');
		 name=arry1[0];
		   street=arry1[1];
		    phone=arry1[4];
		    serviceDealerID=arry1[5];
	}
	 }
	 else{
	if(radioval != 'undefined' && radioval != '' && radioval != null){
		 arry=radioval.split(';');
		  name=arry[0];
		   street=arry[1];
		   phone=arry[5];
		  serviceDealerID=arry[6];
		}
	 }
	    dataString ["carlineDesc"] = carlineDesc;
		dataString ["vin"] = vin;
		dataString ["mileage"] = mileage;
		dataString ["milesPerDay"] = milesPerDay;
		dataString ["drivingCondition"] = drivingCondition;
		dataString ["name"] = name;
		dataString ["street"] = street;
		dataString ["phone"] = phone;
		dataString ["serviceDealerID"] = serviceDealerID;
		dataString1 = "&carlineDesc="+carlineDesc+"&vin="+vin+"&milesPerDay="+milesPerDay+"&drivingCondition="+drivingCondition+
		"&name="+name+"&street="+street+"&mileage="+mileage+"&phone="+phone+"&serviceDealerID="+serviceDealerID;
		
		if(flag == 'undefined' || flag == null)
		{
		flag = 'true';
		}
	if(flag == 'true')
		{
		data=dataString+dataString1;
	var state = { 'url': 'saveRegistration.action',data:data};
	history.pushState(state ,'mymazda', '#registration');
}
		 $.ajax({
			url : "saveRegistration.action",
			data :dataString+dataString1 ,
			type : "POST",
			 beforeSend : function() {
					$("#loadingmessage").show();
				},
			complete : function() {
				$("#loadingmessage").hide();
			},
			success : function(result) {
				if( result == "serviceProblem"){
				
					$("#error-sucess").modal("show");
	 			 	$("#errorMsg").html("There is a problem, while communicating with web services. Please try again.");
					return false;
				}else if( result == "Email"){
					$("#error-sucess").modal("show");
	 			 	$("#errorMsg").html("Email already exist.");
						return false;
					}else if( result == "vin"){
						$("#error-sucess").modal("show");
	 			 		$("#errorMsg").html("There is some problem in VIN registration, You might be registered with us. Please Try login with these credentials and email us, we will be rectifying the issue soon. Meanwhile you can another VIN if you have.");
							return false;	
					}
					else if( result == "vinvalidation"){
						$("#error-sucess").modal("show");
	 			 		$("#errorMsg").html("Sorry, we were unable to validate this VIN number with our records. Please try again.");
							return false;	
					}
					else if( result == "zip"){
						$("#error-sucess").modal("show");
	 			 		$("#errorMsg").html("Please enter a valid combination of ZIP Code and state.");
							return false;	
					}
					else{
						 $("#success-msg").modal("show");
			 			 $("#successMsg").html("User has been successfully registered with Mazda");
						 $("#container-fluid").html(result);
						 setTimeout(function() {
							 $("#success-msg").modal("hide");;
							}, 1500);
							return false;
					}
					
			},
			error : function(error) {
				$("#error-sucess").modal("show");
			 	$("#errorMsg").html("Error");
			}
		}); 
    return false;
}

function searchDealer(){
  zip = "<%=session.getAttribute("zip")%>";
  registersearch='joinmee';
  backDealer=$("#anotherSearch").text();
	$.ajax({
	 	url:"locateDelear.action",
	 	 beforeSend : function() {
				$("#loadingmessage").show();
			},
		complete : function() {
			$("#loadingmessage").hide();
		},
	 	success:function(result){
	 		//alert("1");
	 		
			$("#container-fluid").html(result);
				 window.scrollTo(0, 0);
			 	 }});
	
}

function IsEmail(email) {
	  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	  return regex.test(email);
	}
	function getPopup(){
		$("#driving-condition").show();
	}
	function openAccountTab(){
		$("#how-to").show();
		$("#features").hide();
		var arry=accountData.split(',');
		$("#firstname").val(arry[0]);
		$("#lastname").val(arry[1]);
		$("#streetaddress1").val(arry[2]);
		$("#streetaddress2").val(arry[3]);
		$("#city").val(arry[4]);
		$("#state").val(arry[5]);
		$("#zipcode").val(arry[6]);
		$("#email").val(arry[7]);
		$("#confirmemail").val(arry[8]);
		$("#phonenumber").val(arry[9]);
		$("#password").val(arry[10]);
		$("#repassword").val(arry[11]);
		
		
	}
	function openVehicleTab(){
		$("#features").show();
		$("#how-to").hide();
	}
	var vehicledata;
	var changevalue;
	function tabChange(){
		changevalue='clickbackbutton';
		$("#features").hide();
		$("#how-to").show();
		var arry=accountData.split(',');
		$("#firstname").val(arry[0]);
		$("#lastname").val(arry[1]);
		$("#streetaddress1").val(arry[2]);
		$("#streetaddress2").val(arry[3]);
		$("#city").val(arry[4]);
		$("#state").val(arry[5]);
		$("#zipcode").val(arry[6]);
		$("#email").val(arry[7]);
		$("#confirmemail").val(arry[8]);
		$("#phonenumber").val(arry[9]);
		$("#password").val(arry[10]);
		$("#repassword").val(arry[11]);
		var carlineDesc=$("#nickname").val();
		var vin=$("#regvin").val();
		var mileage=$("#mileage").val();
		var milesPerDay=$("#milesPerDay option:selected").val();
		var drivingCondition=$("#drivingCondition option:selected").val();
		vehicledata=carlineDesc+','+vin+','+mileage+','+milesPerDay+','+drivingCondition;
		 
	}
	 function joinmesearchDealers() {
 		var data = {};
 		var dealerSearch = $("#joinmesearchdealer").val();
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