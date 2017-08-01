<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
//formErrorContent
jQuery(document).ready(function() {
	if('${RegistrationTO1}' !=null){
	document.getElementsByName("state").selectedIndex = '${RegistrationTO.state}';
	//document.getElementByName("mdlYear").selectedIndex = '%{RegistrationTO.mdlYear}';
	}
});
$(".getQuotesFormSHOW").click(function(e){
   	e.preventDefault();
    e.stopPropagation();
    $(".getQuotesForm").show();
  });
	
	$(document).ready(function(){
	 $(".vinPositionDisplay").hide();
	 $("#modelDiv-0").show();
 
  });
  
   $(".locateVin").click(function(e){
	 e.preventDefault();
	 e.stopPropagation();
    $("#vinPositionDisplay").show();
  });
  
   $(".disclaimer").click(function(e){
	 e.preventDefault();
	 e.stopPropagation();
    $("#displayDisclaimer").show();
  });
  
  $(document).click(function(){
  	 $("#vinPositionDisplay").hide();
  	 $("#displayDisclaimer").hide();
  	 $("#actionMessage").hide();
  });
  
  function ValidateEmail(email) {
      var expr = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
        return expr.test(email);
    };
	
	function saveNewUser(){
		var dataString = $("#registrationForm").serialize();
		var firstName = $.trim($("#firstNameSelector").val());
		var lastName  = $.trim($("#lnameId").val());
		var street  = $.trim($("#streetSelector").val());
		var city  = $.trim($("#citySelector").val());
		var zip  = $.trim($("#zipSelector").val());
		var email  = $.trim($("#emailSelector").val());
		var emailConf  = $.trim($("#email2Selector").val());
		var passwrd  = $.trim($("#password").val());
		var passwrd2  = $.trim($("#password2").val());
		var vin		= $.trim($("#vinSelector").val());
		var mileage		= Number($("[name='mileage']").val().trim());
		/* var serviceDealerId		= Number($("[name='serviceDealerID']").val().trim()); */
			if(firstName == ""){
				showRegistrationErrorMessageWithText("You must select a First Name.");
			}
			else if(lastName == ""){
				showRegistrationErrorMessageWithText("You must select a Last Name.");
			}
			
			else if(street == ""){
				showRegistrationErrorMessageWithText("You must select a Street Address 1.");
			}
			else if(city == ""){
				showRegistrationErrorMessageWithText("You must select a City.");
			}
			else if(zip == ""){
				showRegistrationErrorMessageWithText("You must select a ZIP Code.");
			}
			else if(zip.length > 5 || zip.length < 5){
				showRegistrationErrorMessageWithText("Maximum '5' Character allowed for Zip.");
			}
			else if(email == ""){
				showRegistrationErrorMessageWithText("You must select an Email.");
			}
			else if (!ValidateEmail(email)) {
              		showRegistrationErrorMessageWithText("Invalid email address.");
        		}
			else if(emailConf == ""){
				showRegistrationErrorMessageWithText("You must select an Email Confiirm.");
			}
			else if(emailConf != email){
				showRegistrationErrorMessageWithText("Email and Email confirmation fields must match");
			}
			else if(passwrd == "" || passwrd.length<4 ){
				showRegistrationErrorMessageWithText("Password must be a minimum of 4 characters, a maximum of 15 characters and not contain any symbols");
			}
			else if(passwrd2 == ""){
				showRegistrationErrorMessageWithText("You must select a Password Confiirm");
			}
			else if(passwrd != passwrd2){
				showRegistrationErrorMessageWithText("Password and password confirmation fields must match");
			}
			else if(vin == ""){
				showRegistrationErrorMessageWithText("You must select a Vin");
			}
			else if(vin.length > 17 || vin.length < 17){
				showRegistrationErrorMessageWithText("Maximum '17' Character allowed for Vin");
			}
			else if(isNaN(mileage) || mileage<=0 ){
				showRegistrationErrorMessageWithText("You must enter valid mileage.");
			}
			/* else if(serviceDealerId<=0 ){
				showRegistrationErrorMessageWithText("Please select your preferred dealer.");
			} */
			else{
			var url = "saveRegistration.action";
			$.ajax({
				type : "POST",
				url : url,
				data : dataString,
				beforeSend : function() {
					showAjaxLoadMsg("Loading...");
				},success : function(result) {
					if(result == "User has been successfully registered with Mazda"){
						showRegistrationErrorMessageWithText(result);
						setTimeout(function() {homeClick();},2250);
					}else{
    				showRegistrationErrorMessageWithText(result);
    				}
				},complete : function() {
					hideAjaxLoadMsg();
					e.preventDefault();
				}
			});
			}
			return false;
		}
	
	function homeClick(){
		window.location="myMazda.action";
		return false;
	}
	
	var yearIndex;
		function showModel(){
			$("#selectedModel"+yearIndex).attr("name", "");
			yearIndex = $("#selectedYear")[0].selectedIndex;
			$("#selectedModel"+yearIndex).attr("name", "carlineCode");
			$("#YearModelTripDropdown .modelCl").hide();
			$("#modelDiv-"+yearIndex).show();
			
		}
	function addPreferedDealer(){
		var dataString = $("#registrationForm").serialize();
		dataString = dataString+"&flag=true";
		$.ajax({
        	url:"addPreferedDealer.action",
        	data : dataString,
        	 beforeSend:function(){
			      showAjaxLoadMsg("Loading...");
			    },
			    complete:function(){
  					$("#byZip").addClass("active");
			    	$("#searchByZipcode").addClass("active");
			    },
        		success:function(result){
        			hideAjaxLoadMsg();
     			 	$("#pageContainDiv").html(result);
     			 	 window.scrollTo(0, 0);
  			 	 }});
		return false;
	}
	 function showSchedule(){
	 $(".vinDivClass").hide();
	 var drivingCondition = $("[name='drivingCondition']:checked").val();
  	$("#scheduleContain"+drivingCondition).show();
  	
  }
 /*  function showSchedule2(){
  	$("#schedule2Contain").show();
  	$("#schedule1Contain").hide();
  } */
  			
function scheduleContaint(){
	$(".vinDivClass").hide();
}
  
</script>

<header>
	<div class="top_line">
		<div class="container">
			<div class="row">
				<div class="homeicon">
					<ul class="social-links">
						<li><a href="myMazda.action" class=""><img
								title="MyMazda" src="view/vhelper/images/home_m.png"
								class="logoutb">
						</a>
						</li>
					</ul>
				</div>
				<div
					class="col-lg-10 col-md-10 col-xs-10 col-xs-offset-2 col-sm-offset-0 pull-right ">
					<s:if test="#session.loginUser != null">
						<div class="col-lg-10 col-md-10 col-ms-12 pull-right ">
							<ul class="social-links">
								<li><h2 class="heading" style="margin: 8px 15px 0px 0px;">Registration</h2>
								</li>
								<li><a href="logout" class="browseCategory">LogOut</a></li>
							</ul>
						</div>
					</s:if>
					<s:else>
						<div class="row">
							<div class="col-lg-10 col-md-10 col-ms-12  pull-right ">
								<ul class="social-links">
									<li><h2 class="heading hidden-xs"
											style="margin: 8px 15px 0 0;">Registration</h2>
									</li>
									<li><a href="javaScript:;"
										class="browseCategory getQuotesFormSHOW">Login</a>
									</li>
									<li><a href="javaScript:;" onclick="return registerNew();"
										class="browseCategory">Sign Up</a>
									</li>
								</ul>
							</div>
						</div>
					</s:else>
				</div>
			</div>
		</div>
	</div>
</header>


<div class="section1 padd50">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 marginless">
				<div class="tabs">
					<div class="tab-content">
						<div id="popularPosts" class="tab-pane active">
							<div class="tabbedwidget">
								<div class="row">

									<form method="post" action="saveRegistration"
										id="registrationForm">
										<s:if test="#session.RegistrationTO != null ">
											<s:iterator value="#session.RegistrationTO">
												<div class="col-lg-6 col-md-6 col-sm-12">
													<div
														style="border-right:#333 solid 1px; overflow:hidden; margin:0 0px 0 0 ;">
														<h2 class="heading2">Contact information
															<span style="float:right; margin-right:10px;">
																<label><strong style="color:#d00000;">*</strong>Required</label>
															</span>
														</h2>

														<div class="">
															<s:hidden
																value="%{#session.RegistrationTO.ServiceDealerID}"
																name="serviceDealerID"></s:hidden>
															<div class="myaccount row">
																<div class="col-lg-3 col-md-3 col-sm-4">
																	<span><label>Title </label> 
																	<s:select cssClass="selectBox01"
																			name="title"
																			list="#{'Mr':'Mr.', 'Mrs':'Mrs.','Ms':'Ms',
													 							'Dr':'Dr.'}"
																			value="%{title}" /> </span>
																</div>
																<div class="col-lg-3 col-md-3 col-sm-4">
																	<span><label>First Name <strong>*</strong>
																	</label><input type="text"
																		value="<s:property value="firstName"/>"
																		id="firstNameSelector" class="textType2"
																		 name="firstName"> </span>
																</div>
																<div class="col-lg-3 col-md-3 col-sm-4">
																	<span><label>Last Name <strong>*</strong>
																	</label> <input type="text" name="lastName" id="lnameId"
																		class="textType2" 
																		value="<s:property value="lastName"/>" /> </span>
																</div>

																<div class="clearfix"></div>

																<div class="col-sm-12">
																	<span> 
																	<label>Street Address 1 <strong>*</strong></label>
																	<input type="text" class="textType2"
																		name="street1address"
																		value="<s:property value="street1address"/>"
																		id="streetSelector"> </span>
																		
																		<span><label>Street
																			Address 2 </label><br>
																	<input type="text" class="textType2"
																		name="street2address"
																		value="<s:property value="street2address"/>">
																	</span>

																</div>
																<div class="col-lg-3 col-md-3 col-sm-12">
																	<span><label>City <strong>*</strong> </label><input
																		id="citySelector" type="text" class="textType2" name="city"
																		value="<s:property value="city"/>"> </span>
																</div>
																<div class="col-lg-3 col-md-3 col-sm-12">
																	<span><label>State <strong>*</strong> </label> 
																	<s:select
																			 cssClass="selectBox01"
																			name="state"
																			list="#{'AK':'AK', 'AL':'AL','AR':'AR',
													 							'AZ':'AZ', 'CA':'CA','CO':'CO',
													 							'CT':'CT', 'DC':'DC','DE':'DE',
													 							'FL':'FL', 'GA':'GA','HI':'HI',
													 							'IA':'IA', 'ID':'ID','IL':'IL',
													 							'IN':'IN', 'KS':'KS','KY':'KY',
													 							'LA':'LA', 'MA':'MA','MD':'MD',
													 							'ME':'ME', 'MI':'MI','MN':'MN',
													 							'MO':'MO', 'MS':'MS','MT':'MT',
													 							'NC':'NC', 'NC':'NC','NE':'NE',
													 							'NH':'NH', 'NJ':'NJ','NM':'NM',
													 							'NV':'NV', 'NY':'NY','OH':'OH',
													 							'OK':'OK', 'OR':'OR','PA':'PA',
													 							'RI':'RI', 'SC':'SC','SD':'SD',
													 							'TN':'TN', 'UT':'UT','VA':'VA','VT':'VT',
													 							'WA':'WA', 'WI':'WI','WV':'WV','WY':'WY'}"
																			value="%{state}" /> </span>
																</div>
																<div class="col-lg-3 col-md-3 col-sm-12"
																	>
																	<span><label>Zip code <strong>*</strong>
																	</label><input type="text" class="textType2"
																		style="width:117%;" id="zipSelector" name="zip"
																		value="<s:property value="zip"/>" maxlength="5">
																	</span>
																</div>
																<div class="col-sm-12">
																	<span><label>Phone Number </label><input
																		type="text" class="textType2" name="mobilePhone"
																		value="<s:property value="mobilePhone"/>"> </span>
																</div>
																<div class="clearfix"></div>
																<h2  class="heading2">Account information</h2>

																<div class="col-lg-5 col-md-5 col-sm-12">
																	<span><label>Email <strong>*</strong> </label><input
																		id="emailSelector" type="text" class="textType2"
																		name="email"
																		value="<s:property value="email"/>" id="email">
																	</span>
																</div>
																<div class="col-lg-5 col-md-5 col-sm-12">
																	<span><label>Confirm Email <strong>*</strong>
																	</label><input  type="text" class="textType2"
																		name="email2" id="email2Selector"
																		value="<s:property value="email"/>" id="email2">
																	</span>
																</div>
																<div class="col-lg-5 col-md-5 col-sm-12">
																	<span><label>Password <strong>*</strong>
																	</label><input  type="password"
																		class="passwordType2 text-input" name="password"
																		value="<s:property value="password"/>" id="password">
																	</span>
																</div>
																<div
																	class="col-lg-5 col-md-5 col-sm-12 password2formError">
																	<span><label>Re-Enter Password <strong>*</strong>
																	</label><input  type="password"
																		class="text-input passwordType2" name="password2"
																		value="<s:property value="password"/>" id="password2">
																	</span>
																</div>


															</div>
														</div>
													</div>
												</div>

												<div class="col-lg-6 col-md-6 col-sm-12">
													<h2 class="heading2">Vehicle information</h2>
													<div class="">
														<div class="myaccount">
															<div class="col-lg-4 col-md-3 col-sm-12">
																<span><label>Title </label><input
																	 type="text" class="textType2"
																	name="vtitle" value="<s:property value="vtitle"/>">
																</span>
															</div>
															<div class="col-lg-7 col-md-3 col-sm-12"
																>
																<span>Name Your Mazda for Ex. My Machine</span>
															</div>
															<div class="clearfix"></div>
															<div class="col-lg-4 col-md-3 col-sm-12"
																>
																<span><label>VIN <strong>*</strong> </label><input
																	 type="text"
																	class="textType2" name="vin"
																	value="<s:property value="vin"/>" id="vinSelector"
																	maxlength="17"> </span>
															</div>
															<div class="col-lg-7 col-md-3 col-sm-12"
																>
																<span><a href="javaScript:;" class="locateVin"
																	style="text-decoration: underline;">Unable to
																		locate Vin?</a> </span>
															</div>
															<div class="clearfix"></div>

															<div id="YearModelTripDropdown" style="clear: both;">
																<s:if test="#session.yearModelTrim !=null">
																	<div class="col-lg-4 col-md-3 col-sm-12" >
																		<span>
																		     <label>YEAR </label> 
																		     <s:select
																				name="mdlYear" list="#session.yearModelTrim"
																				listKey="year" id="selectedYear" listValue="year"
																				onchange="showModel();" 
																				cssClass="selectBox01" value="%{mdlYear}" /> </span>
																	</div>
																	<div style="clear:both;"></div>
																	<s:iterator value="#session.yearModelTrim"
																		status="yearStatus" begin="0">
																		<div class="col-lg-4 col-md-3 col-sm-12 modelCl"
																			id="modelDiv-<s:property value="%{#yearStatus.index}"/>"
																			style="display: none;">
																			<span><label class="dropdown1">MODEL </label>
																				<s:select name="" list="models" listKey="modelCode"
																					
																					cssClass="selectBox01"
																					id="selectedModel%{#yearStatus.index}"
																					listValue="modelName" value="%{carlineCode}" /> </span>
																		</div>
																	</s:iterator>
																</s:if>
															</div>
															<div class="clearfix"></div>
															<div class="col-lg-5 col-md-5 col-sm-12"
																>
																<span><label>Current mileage <strong>*</strong>
																</label><input type="text" class="textType2 " name="mileage"
																	 placeholder="Current Mileage"
																	value="<s:property value="mileage"/>"> </span>
															</div>
															<div class="clearfix"></div>
															<div class="col-lg-7 col-md-3 col-sm-12"
																>
																<span> <s:if
																		test="#session.RegistrationTO != null && #session.RegistrationTO.dealerName != null">
																		<a href="javaScript:;"
																			onclick="return addPreferedDealer();"
																			style="text-decoration: underline; ">Edit
																			Preferred Dealer </a>

																	</s:if> <s:else>
																		<a href="javaScript:;"
																			onclick="return addPreferedDealer();"
																			style="text-decoration: underline; margin: -69px;">Add
																			Preferred Dealer</a>
																	</s:else> <s:if
																		test="#session.RegistrationTO != null && #session.RegistrationTO.dealerName != null">
																		<table id="dealerContentId"
																			>
																			<tr>
																				<td>
																					<p
																						id='Dealer-<s:property value="#session.RegistrationTO.ServiceDealerID"/>'
																						style="width:530px;">
																						<strong><s:text
																								name="#session.RegistrationTO.dealerName"></s:text>
																							<s:text
																								name="#session.RegistrationTO.dealerPhone" /> </strong><br>
																						<s:text
																							name="#session.RegistrationTO.dealerStreet" />
																						<br>
																					</p></td>
																			</tr>
																		</table>
																	</s:if> </span>
															</div>


															<div class="clearfix"></div>

															<h2 class="heading2" style="float: left;  margin-top: 42px;  width: 45%;">Driving habit & CONDITIONS</h2>
															<br>
															<p class="brand-info"
																style="float: right;  margin:0px -7px 0 0;  text-align: justify;  width: 58%;">By
																entering your vehicle information, you allow us to
																access important details about your vehicle that will
																help maximize your experience</p>
															<br>

															<div class="clearfix"></div>
															<div class="col-lg-4 col-md-3 col-sm-12"
																style="padding:0; margin:-24px 0 0">
																<span><label>Miles per day </label> <s:select
																		style="width:117%;" cssClass="selectBox01"
																		name="milesPerDay"
																		list="#{'10':'0-10', '25':'11-25','50':'26-50',
													 							'75':'51-75', '100':'76-100','150':'101-150',
													 							'200':'151-200', '300':'201-300'}"
																		value="%{milesPerDay}" /> </span>
															</div>
															<div class="clearfix"></div>
															<div class="col-lg-4 col-md-3 col-sm-12"
																style="padding:0; margin:0 0 0 0px">
																<span><label>Driving Conditions</label>
																</span>
															</div>
															<br>
															<div
																style="padding:0; width: 67%;float: left;margin:0 0 0 0px">
																<s:radio onchange="return showSchedule();"
																	name="drivingCondition"
																	list="#{'S':'Schedule 1','H':'Schedule 2'}"
																	value="#session.RegistrationTO.drivingCondition" />
																<img src="./view/vhelper/images/dis.png"
																	class="disclaimer" style="margin: -9px 0px 0px;">

															</div>
															<div class="clearfix"></div>
															<br>

															<div>
																<label>&nbsp;</label> <input type="submit"
																	onclick="return saveNewUser();" class="Submit_btn"
																	value="Submit" name="">&nbsp;&nbsp;&nbsp;
																<!-- <input type="submit" class="Submit_cncl" value="Cancel" name="" onclick="return homeClick();"> -->
																<a href="#" class="Submit_cncl"
																	onclick="return homeClick();">Cancel</a>
															</div>
														</div>
													</div>
												</div>
											</s:iterator>
										</s:if>
										
										
										<s:else>
											<div class="col-lg-6 col-md-6 col-sm-12 ">
												<div class="border-none ">
													<s:hidden name="serviceDealerID"></s:hidden>
													<h2 class="heading2">Contact information 
														<span class="req" style="float:right; margin-right:10px;">
															<label><strong style="color:#d00000;">*</strong>Required</label>
														</span>
													</h2>

													<div class="row">
														<div class="myaccount">
															<div class="col-lg-4 col-sm-4">
																<span><label>Title </label>
																	<select name="title" class="selectBox66">
																			<option>Mr.</option>
																			<option>Mrs.</option>
																			<option>Ms.</option>
																			<option>Dr.</option>
																	</select> 
																</span>
															</div>
															<div class="col-lg-4 col-sm-4">
																	<span>
																        <label>First Name <strong>*</strong></label>
																		<input type="text" id="firstNameSelector"
																			class="textType2" name="firstName"
																			placeholder="First name"> 
																	</span>
																</div>

															<div class="col-lg-4 col-sm-4">
																<span>
																<label>Last Name <strong>*</strong></label> 
																<input type="text" name="lastName" id="lnameId"
																	class="textType2" placeholder="Last name" /> 
																</span>
															</div>
															

															<div class="col-sm-12">
																<span>
																<label>Street Address 1 <strong>*</strong></label>
																 <input type="text" id="streetSelector"
																	class="textType2" name="street1address"
																	placeholder="Street Address 1"> </span><span><label>Street
																		Address 2 </label>
																<input type="text" class="textType2"
																	name="street2address" placeholder="Street Address 2 ">
																</span>

															</div>
															<div class="col-lg-4 col-sm-4">
																<span><label>City <strong>*</strong> </label><input
																	id="citySelector" type="text" class="textType2"
																	 name="city" placeholder="City">
																</span>
															</div>
															<div class="col-lg-4 col-sm-4">
																<span><label>State <strong>*</strong> </label><select
																	class="selectBox66" name="state">
																		<option>AK</option>
																		<option>AL</option>
																		<option>AR</option>
																		<option>AZ</option>
																		<option>CA</option>
																		<option>CO</option>
																		<option>CT</option>
																		<option>DC</option>
																		<option>DE</option>
																		<option>FL</option>
																		<option>GA</option>
																		<option>HI</option>
																		<option>IA</option>
																		<option>ID</option>
																		<option>IL</option>
																		<option>IN</option>
																		<option>KS</option>
																		<option>KY</option>
																		<option>LA</option>
																		<option>MA</option>
																		<option>MD</option>
																		<option>ME</option>
																		<option>MI</option>
																		<option>MN</option>
																		<option>MO</option>
																		<option>MS</option>
																		<option>MT</option>
																		<option>NC</option>
																		<option>ND</option>
																		<option>NE</option>
																		<option>NH</option>
																		<option>NJ</option>
																		<option>NM</option>
																		<option>NV</option>
																		<option>NY</option>
																		<option>OH</option>
																		<option>OK</option>
																		<option>OR</option>
																		<option>PA</option>
																		<option>RI</option>
																		<option>SC</option>
																		<option>SD</option>
																		<option>TN</option>
																		<option>UT</option>
																		<option>VA</option>
																		<option>VT</option>
																		<option>WA</option>
																		<option>WI</option>
																		<option>WV</option>
																		<option>WY</option>
																</select> </span>
															</div>
															
															<div class="col-lg-4 col-sm-4">
																<span><label>Zip code <strong>*</strong>
																</label>
																<input type="text" class="textType2"
																	 name="zip" placeholder="Zip Code"
																	id="zipSelector" maxlength="5"> </span>
															</div>
															<div class="col-lg-12 col-sm-12">
																<span><label>Phone Number </label><input
																	type="text" class="textType2"
																	name="mobilePhone" placeholder="Phone Number">
																</span>
															</div>
															<div class="clearfix"></div>
															<div class="col-lg-12 col-sm-12">
																<h2 class="heading2">Account information</h2>
															</div>

														<div class="col-lg-6 col-sm-6">
																<span><label>Email <strong>*</strong> </label><input
																	type="text" class="textType2" name="email"
																	placeholder="Email"
																	id="emailSelector"> </span>
															</div>
															<div class="col-lg-6 col-sm-6">
																<span><label>Confirm Email <strong>*</strong>
																</label><input type="text"
																	class="textType2" name="email2"
																	placeholder="Email" id="email2Selector"> </span>
															</div>
															<div class="col-lg-6 col-sm-6">
																<span><label>Password <strong>*</strong>
																</label><input  type="password"
																	class="passwordType2"
																	name="password" placeholder="Password" id="password">
																</span>
															</div>
															<div
																class="col-lg-6 col-sm-6 password2formError"
																>
																<span><label>Re-Enter Password <strong>*</strong>
																</label><input  type="password"
																	class="text-input passwordType2"
																	name="password2" placeholder="Re-Enter Password"
																	id="password2"> </span>
															</div>
														</div>
													</div>
												</div>
											</div>

											<div class="col-lg-6 col-md-6 col-sm-12">
												<h2 class="heading2">Vehicle information</h2>
												<div class="row">

													<div class="myaccount">
														<div class="col-sm-6">
															<span><label>Title </label>
																<input type="text" class="textType2" name="vtitle" placeholder="Title"> 
															</span>
														</div>
														
														<div class="col-sm-6">
															<span>
															<br/><br>
															 <p class="brand-info">Name Your Mazda for Ex. My Machine </p>
															</span>
														</div>
														
														<div class="clearfix"></div>
														<div class="col-sm-6">
															<span>
																<label>VIN <strong>*</strong> </label> 
																<input type="text" class="textType2" name="vin" placeholder="Vin Number" id="vinSelector" maxlength="17"> 
															</span>
														</div>
														<div class="col-sm-6">
														<br/>
														<br>
															<span><a href="javaScript:;" class="locateVin"  style="text-decoration: underline;">Unable to locate Vin?</a> </span>
														</div>
														
														<div class="clearfix"></div>



														<div id="YearModelTripDropdown"
															style="clear: both;  position: relative;">
															<s:if test="#session.yearModelTrim !=null">
																<div class="col-sm-4">
																<span>
																<label>YEAR </label> 
																	<s:select name="mdlYear" list="#session.yearModelTrim"
																			listKey="year" id="selectedYear" listValue="year"
																			onchange="showModel();"
																			cssClass="selectBox66 " /> 
																</span>
																</div>
																<s:iterator value="#session.yearModelTrim"
																	status="yearStatus" begin="0">
																	<div class="col-sm-4 modelCl model-nw"
																		id="modelDiv-<s:property value="%{#yearStatus.index}"/>"
																		style="display: none;   ">
																		<span><label class="dropdown1">MODEL </label> <s:select
																				name="" list="models" listKey="modelCode"
																				cssClass="selectBox66 nw-model" 
																				id="selectedModel%{#yearStatus.index}"
																				listValue="modelName"
																				 /> </span>
																	</div>
																</s:iterator>
															</s:if>
														</div>


														<div class="clearfix"></div>
														<div class="col-sm-6">
															<span class="milage">
																<label>Current mileage <strong>*</strong> </label>
																<input type="text" class="textType2" name="mileage" placeholder="Current Mileage"> 
															</span>
														</div>
														<div class="clearfix"></div>
														<div class="col-sm-12">
															<span class="dealer"><a href="javaScript:;"
																onclick="return addPreferedDealer();"
																style="text-decoration: underline;">Add Preferred
																	Dealer </a> </span>
														</div>

														<div class="col-sm-12">
															<h2 class="heading2">Driving habit & CONDITIONS</h2>
														</div>

														<div class="col-sm-12">
														<p class="brand-info">
															By entering your vehicle information, you allow us to
															access important details about your vehicle that will
															help maximize your experience</p>
														</div>
														
														<div class="clearfix"></div>
														<div class="col-sm-4">
															<span><label>Miles per day </label>
															<select	class="selectBox66"
																name="milesPerDay">
																	<option value="10">0-10</option>
																	<option value="25">11-25</option>
																	<option value="50">26-50</option>
																	<option value="75">51-75</option>
																	<option value="150">76-100</option>
																	<option value="10">101-150</option>
																	<option value="200">151-200</option>
																	<option value="300">201-300</option>
															</select> 
															</span>
														</div>

														<div class="clearfix"></div>

														<div class="col-sm-12">
															<span>
																<label>Driving Conditions</label>
															</span>
														</div>
														
														<div class="col-sm-12">
															
															<input type="radio" onclick="showSchedule();" name="drivingCondition" value="S" checked="checked"> <b>Schedule1 </b>
																	&nbsp;&nbsp; <input type="radio"
																	onclick="showSchedule();" name="drivingCondition"
																	value="H">&nbsp;<b>Schedule 2</b>&nbsp;&nbsp; <img
																	src="./view/vhelper/images/dis.png" class="disclaimer"
																	>
														
														</div>
														

														<div class="col-sm-12 form-group">
															<br/>
															<input type="submit" onclick="return saveNewUser();" class="Submit_btn"	value="Submit" name="">
															<a href="#" style="padding-top: 8px; padding-left: 21px; margin-left:5px;" class="Submit_cncl" onclick="return homeClick();">Cancel</a>
														</div>
													</div>
												</div>
											</div>
										</s:else>
									</form>
								</div>

							</div>
							<%
								request.getSession().setAttribute("RegistrationTO", null);
							%>

						</div>
					</div>

				</div>

			</div>
		</div>


	</div>
</div>
<div id="vinPositionDisplay" class="vinDivClass signpop">
	<img alt="" src="./view/vhelper/images/mymazda_profile_vin.jpg"
		class="img-class"><br>
	<p class="brand-text" style="text-align: justify; padding: 10px;">
		How to find your VIN<br>
		<br> Every vehicle has a unique VIN, usually a 17-character
		combination of numbers and letters. Your VIN should be stamped on a
		metal tag affixed above the instrument panel between the windshield
		and the dashboard on the driver's side.
	</p>
</div>

<div id="displayDisclaimer" class="vinDivClass signpop"
	style="display: none;  height: 435px; overflow: scroll;">
	<h2>Driving Habits and Conditions</h2>
	<p class="brand-text" style="text-align: justify; padding: 10px;">
		<br> Please help us to accurately calculate your maintenance
		schedule based on your driving habits and environmental conditions:<br>
		<br> For Schedule 1 driving conditions, a 7,500-mile service
		interval will apply. For Schedule 2 conditions, a 5,000-mile interval
		will apply. If any of the following conditions apply, select Schedule
		2 Duty. <br> Repeated short-distance driving<br> Driving in
		dusty conditions <br> Driving with an extended use of brakes <br>
		Driving in areas where salt or other corrosive materials are being
		used. <br> Driving on rough or muddy roads <br> Extended
		periods of idling or low-speed operation <br> Driving for a
		prolonged period in cold temperatures or extremely humid climates
	</p>
	<input type="submit" onclick="return scheduleContaint();"
		class="Submit_btn cbutton" value="OK">
</div>



<div id="scheduleContainS" class="vinDivClass signpop"
	style=" display: none;  height: 435px; padding:20px; overflow: scroll;">
	<p class="brand-text" style="text-align: justify; padding: 10px;">
		Follow Schedule 1 if the vehicle is operated mainly where NONE of the
		following conditions apply.<br>
		<br>* Repeated short-distance driving<br>* Driving in dusty
		conditions<br>* Driving with extended use of brakes<br>*
		Driving in areas where salt or other corrosive materials are used<br>*
		Driving on rough or muddy roads<br>* Extended periods of idling
		or low-speed operation<br>* Driving for long periods in cold
		temperatures or extremely humid climates<br>* Driving in
		extremely hot conditions<br>* Driving in mountainous conditions
		continually<br>
		<br>If any do apply, follow Schedule 2 (Canada and Puerto Rico
		residents follow Schedule 2). <br>
		<br>NOTE<br>After the prescribed period, continue to follow
		the described maintenance at the recommended intervals.
	</p>
	<input type="submit" onclick="return scheduleContaint();"
		class="Submit_btn cbutton" value="OK">
</div>

<div id="scheduleContainH" class="vinDivClass signpop"
	style="display: none; height: 435px; overflow: scroll;">
	<p class="brand-text" style="text-align: justify; padding: 10px;">
		Schedule 2 should be followed if any of the following driving
		conditions applies:<br>
		<br>* Repeated short-distance driving or with extended use of
		brakes<br>* Driving in areas where salt or other corrosive
		materials are used<br>* Driving on rough or muddy roads<br>*
		Extended periods of idling or low-speed operation<br>* Driving
		for long periods in cold temperatures or extremely humid climates<br>*
		Driving in dusty or extremely hot conditions<br>* Driving in
		mountainous conditions continually<br>(Canada and Puerto Rico
		residents follow Schedule 2)<br>
		<br>NOTE<br>After the prescribed period, continue to follow
		the described maintenance at the recommended intervals.
	</p>
	<input type="submit" onclick="return scheduleContaint();"
		class="Submit_btn cbutton" value="OK">

</div>
<!-- <div id="actionMessage" class="serverMessage" style="margin:311px 379px 0 585px; display: none; background: #F4F4F4;">
</div> -->


<!-- Begin Omniture tag -->
<!-- SiteCatalyst code version: H.23.4. -->
<script type="text/javascript">var s_account="mazdaownersdev,mazdaglobal"</script>
<!-- mp_trans_disable_start -->
<script language="JavaScript" src="view/vhelper/js/s.code.dev.js"></script>
<script language="JavaScript">
				s.server="341067-MAZADS002.mazdausa.com";s.pageName="musa:owners_profile_create";s.channel="owners_profile_create";s.hier1="My Mazda Page, MUSA";s.prop4="X";s.prop6="tools_owners_mymazda_recalls";s.prop8="owners_mymazda_recalls";s.prop9="en";s.prop22="0";s.eVar39=screen.width+'x'+screen.height;s.eVar40="tier1";s.eVar41="no";s.business="mazdausa.com";s.eVar11="A";/************* DO NOT ALTER ANYTHING BELOW THIS LINE !**************/ var s_code=s.t();if(s_code)document.write(s_code)</script>
<noscript>
	<img src="http://mazdausa.112.2o7.net/b/ss/mazdausacom/1/H.23.4--NS/0"
		height="1" width="1" border="0" alt="" />
</noscript>
<!-- mp_trans_disable_end -->
<!-- End Omniture tag -->

