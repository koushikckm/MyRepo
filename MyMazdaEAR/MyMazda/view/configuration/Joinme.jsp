<%@ taglib prefix="s" uri="/struts-tags"%>

<jsp:include page="../common/Header.jsp" />
<jsp:include page="/view/common/DataLayerJs.jsp"></jsp:include>
<jsp:include page="JoinmeJs.jsp"></jsp:include>

<script type="text/javascript">
	$(document).ready(function() {
		dataLayer.page.name = 'register_user';
		dataLayer.page.URL = window.location.href;
		dataLayer.site.sectionHistorical = 'owners';
		dataLayer.site.section = 'mymazda';
		dataLayer.site.subsection = 'owners';
		dataLayer.form.name = 'create_account_user';
		dataLayer.form.type = 'create_account';
		dataLayer.user.type = 'x';
	});
	function vinNum(){
	var trackVin = $("#regvin").val();
		if(trackVin.length == 17){
			dataLayer.vehicle.vin = trackVin;
		}
	}
	var joincondition, joincond, joinmiles;
	function text(){
	 	joincondition = $("#drivingCondition").val();
	 	if(joincondition == 'S'){
	 		joincond = 'std';
	 	}else{
	 		joincond = 'hd';
	 	}
		joinmiles = $("#milesPerDay").val();
		dataLayer.vehicle.milesBySchedule = joincond+':'+joinmiles;
	}
	$('#zipcode').on("input", function() {
		var jInput = this.value;
		if(jInput.length === 5){
		dataLayer.zipCode = jInput;
		}
	});
</script>

<div class="overlay"></div>
<div class="Register-Page">
	<div class="container">
		<div class="row margin-0">
			<div class="col-xs-12 col-sm-12 col-md-12 Reg-head padding-0">
				<div class="search-field1">
					<input type="search" class="form-control"
						placeholder="Locate Nearest Dealers (Enter Zip Code or Name)"
						id="joinmesearchdealer" /> <i class="fa fa-map-marker"
						onclick="return joinmesearchDealers();" style="cursor: pointer;"></i>
				</div>
				<h1 class="text-uppercase">JOIN MYMAZDA</h1>
				<p class="brand-text">Enjoy the full Mazda Ownership Experience.</p>
			</div>
		</div>
		<div class="nav-tab-div">
			<ul class="nav nav-tabs" id="myTab">
				<li class="active"><a data-toggle="tab"
					class="deco-none misc-class" href="#how-to"
					onclick="openAccountTab();"><span id="creaateacc">CREATE
							YOUR ACCOUNT</span></a></li>
				<li><a data-toggle="tab" href="#features"
					onclick="openVehicleTab();"><span id="addVehicle">ADD
							YOUR VEHICLE</span></a></li>
			</ul>
			<div class="tab-content responsive-tab" id="responsive-tab">
				<div class="tab-pane fade in active" id="how-to">
					<form role="form" >
						<div class="row margin-0">
							<div class="col-sm-6 col-md-6 contact-inf brand-bdr-rgt">
								<h4>
									CONTACT INFORMATION
									<p class="required-text"><span class="mandatory-field">*</span>Required</p>
								</h4>
								<div class="row margin-0">
									<%-- <div class="form-group col-sm-3">
										<label for="email">TITLE</label>
										<div class="select-style">
											<select id="title">
												<option>Mr.</option>
												<option>Mrs.</option>
												<option>Ms.</option>
												<option>Dr.</option>
											</select>
										</div>
									</div> --%>
									<div class="form-group col-sm-6" id="firsterror">
										<label>FIRST NAME<span class="mandatory-field">*</span></label>
										<input type="First Name" class="form-control" id="firstname"
											placeholder="ENTER FIRST NAME"> <span
											class="error_mssg" id="firstnameerror"></span>
									</div>
									<div class="form-group col-sm-6">
										<label>LAST NAME<span class="mandatory-field">*</span></label>
										<input type="Last Name" class="form-control" id="lastname"
											placeholder="ENTER LAST NAME">
									</div>
									<div class="form-group col-sm-12">
										<label>STREET ADDRESS 1<span class="mandatory-field">*</span></label>
										<input type="streetaddress1" class="form-control"
											id="streetaddress1" placeholder="ENTER STREET ADDRESS">
									</div>
									<div class="form-group col-sm-12">
										<label>STREET ADDRESS 2</label> <input type="streetaddress2"
											class="form-control" id="streetaddress2"
											placeholder="ENTER STREET ADDRESS">
									</div>
									<div class="form-group col-sm-5">
										<label>CITY<span class="mandatory-field">*</span></label> <input
											type="City" class="form-control" id="city"
											placeholder="ENTER CITY">
									</div>
									<div class="form-group col-sm-3">
										<label>STATE<span class="mandatory-field">*</span></label>
										<div class="select-style">
											<select id="state" placeholder="ENTER STATE">
												<% 
													String stateCodes = (String)request.getSession().getAttribute("STATE_CODES"); 
													if(stateCodes != null) {
														String[] stateCodesArr = stateCodes.split(",");
														for(int stCd=0; stCd < stateCodesArr.length; stCd++) {
															%><option><%=stateCodesArr[stCd]%></option><%
														}
													}
												%>
											</select>
										</div>
									</div>
									<div class="form-group col-sm-4">
										<label>ZIP CODE<span class="mandatory-field">*</span></label>
										<input type="zipcode" class="form-control" id="zipcode"
											maxlength="5" min="5" placeholder="ENTER ZIP CODE">
									</div>
									<div id="searchzip" style="display: none"></div>
									<div class="form-group col-sm-6">
										<label>PHONE NUMBER<span class="mandatory-field">*</span></label>
										<input type="phonenumber" class="form-control"
											id="phonenumber" placeholder="ENTER PHONE NUMBER"><span
											id="errmsg"></span>
									</div>
									<%-- <div class="form-group col-sm-7">
											<label>PHONE TYPE</label> <select
												class="form-control selectpicker" id="phonetype">
												<option>MOBILE</option>
												<option>LAND LINE</option>
											</select>
										<div class="form-group col-sm-7">
											<label>PHONE TYPE</label> 
											<div class="select-style">
												<select
													class="form-control selectpicker" id="phonetype">
													<option>MOBILE</option>
													<option>LAND LINE</option>
												</select>
											</div>
										</div>
										<div class="clearfix"></div>
										<a href="#" class="addadditional-number">Add an additional
											phone number</a> --%>
								</div>
							</div>
							<div class="col-sm-6 col-md-6 account-inf">
								<h4>
									ACCOUNT INFORMATION
									<p class="required-text"><span class="mandatory-field">*</span>Required</p>
								</h4>
								<div class="row margin-0">
									<div class="form-group col-sm-6">
										<label>EMAIL<span class="mandatory-field">*</span></label> <input
											type="Email" class="form-control" id="email" 
											placeholder="ENTER EMAIL">
									</div>
									<div class="form-group col-sm-6">
										<label>CONFIRM EMAIL<span class="mandatory-field">*</span></label>
										<input type="Confirm Email" class="form-control"
											id="confirmemail" placeholder="RE-ENTER EMAIL">
									</div>
									<div class="form-group col-sm-6">
										<label>PASSWORD<span class="mandatory-field">*</span></label>
										<input type="Password" class="form-control" id="password"
											maxlength="15" min="4" placeholder="ENTER PASSWORD">
									</div>
									<div class="form-group col-sm-6">
										<label>CONFIRM PASSWORD<span class="mandatory-field">*</span></label>
										<input type="Password" class="form-control" id="repassword"
											placeholder="RE-ENTER PASSWORD">
									</div>
									<div class="form-group col-sm-12">
									<label class="brand-info">Must be 4-15 characters long.<br>
									Allows alphanumeric and symbols @ * $ . ! %  </label>
									</div>
									<!-- <div class="checkbox col-sm-12">
											<input type="checkbox" value="1" id="checkbox1">
											<input id="checkbox1" class="styled" type="checkbox">
											<label> Remember Me111?</label>
										</div> -->
									<div class="error">
										<div id="completeErrorLayout"></div>
									</div>
									<button type="submit" onclick="return getDetails(); "
										class="btn btn-default btn-red btn-continue brand-button-white" data-analytics-link-component-name="form" data-analytics-link-type="button" data-analytics-link-description="next">CONTINUE</button>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="tab-pane fade addvehicle-section" id="features">
					<form role="form">
						<div class="row margin-0">
							<div class="col-sm-6 col-md-6 brand-bdr-rgt v-information">
								<h4>
									VEHICLE INFORMATION
									<p class="required-text"><span class="mandatory-field">*</span>Required</p>
								</h4>
								<div class="row margin-0">
									<div class="form-group col-sm-5">
										<label>NAME YOUR MAZDA<span class="mandatory-field">*</span></label>
										<input type="Nickname" class="form-control" id="nickname"
											placeholder="ENTER NAME">
									</div>
									<div class="col-sm-7 v-align">
										<span class="brand-info">For Example "My First Mazda"</span>
									</div>
									<div class="clearfix"></div>
									<div class="form-group col-sm-5">
										<label>VIN<span class="mandatory-field">*</span></label> <input
											type="vin" class="form-control" id="regvin"  maxlength="17"
											min="17" placeholder="ENTER VIN" onkeyup="vinNum()">
									</div>
									<div class="col-sm-7 v-align mob-margin-bottom">
										<a href="#" data-toggle="modal" data-target="#findmyvin"><span 
											class="brand-underline"><span class="brand-link" data-analytics-link-component-name="form" data-analytics-link-type="button" data-analytics-link-description="find_vin">WHERE DO I FIND MY VIN?</span></span></a>
									</div>
									<div class="clearfix"></div>
									<div class="form-group col-sm-12 v-align-p">
										<span class="brand-info">By entering your vehicle information, you allow
											us to access important details about your vehicle that will
											help maximize your experience.</span>
									</div>
									<div class="form-group col-sm-5 mob-margin-top">
										<label>CURRENT MILEAGE<span class="mandatory-field">*</span></label>
										<input type="mileage" class="form-control mileage-input"
											id="mileage" placeholder="ENTER MILEAGE">
									</div>
									<div class="clearfix"></div>
									<div class="form-group col-sm-5">
										<label>MILES DRIVEN PER DAY<span
											class="mandatory-field">*</span></label>
										<div class="select-style">
											<select id="milesPerDay" onchange="text()">
											 <option value="">SELECT</option>
												<option value="10">0-10</option>
												<option value="25">11-25</option>
												<option value="50">26-50</option>
												<option value="75">51-75</option>
												<option value="150">76-100</option>
												<option value="10">101-150</option>
												<option value="200">151-200</option>
												<option value="300">201-300</option>
											</select>
										</div>
									</div>
									<!-- <div class="col-sm-8 v-align">
											<a href="#">Why do I need this?</a>
										</div> -->
									<div class="clearfix"></div>
									<div class="form-group col-sm-7">
										<label>TYPICAL DRIVING CONDITIONS<span
											class="mandatory-field">*</span></label>
										<div class="select-style">
											<select id="drivingCondition" onchange="text()">
											 <option value="">SELECT</option>
												<option value="S">Standard Driving Conditions</option>
												<option value="H">Heavy-Duty Conditions</option>
											</select>
										</div>
									</div>
									<div class="clearfix"></div>
									<div style="text-align: left; margin-left: 5px;">
										<a href="#" data-toggle="modal" data-target="#driving-condition"><span 
											class="brand-underline"><span class="brand-link" data-analytics-link-component-name="form" data-analytics-link-type="button" data-analytics-link-description="driving_cond_def">DRIVING CONDITION DEFINITIONS</span></span></a>
									</div>
								</div>
							</div>
							<div class="col-sm-6 col-md-6 preferred-dealer mob-margin-top">
								<h4>PREFERRED SERVICE DEALER
									<s:if test="dealerList.size()=0">(<a href="javaScript:;"
											onclick="return searchDealer();" data-analytics-link-component-name="form" data-analytics-link-type="button" data-analytics-link-description="add_a_dealer"><span 
											class="brand-underline"><span class="brand-link">ADD
											YOUR PREFERRED SERVICE DEALER</span></span></a>)</s:if>
								</h4>
                                    <div class="dealers-list">
                                    <s:if test="dealerList.size()>3">
									<s:if test="dealerList.size()>0">
									<p id="dealerslistdata" class="brand-info">
										Here are the 3 closest dealer(s) based on the information you entered. Select a preferred dealer or </p></s:if><br><a href="javaScript:;"  
											onclick="searchDealer();  locatelogin();" data-analytics-link-component-name="form" data-analytics-link-type="button" data-analytics-link-description="add_a_dealer"><span id="adddealer" class="brand-underline link-height"><span class="brand-link">Add your preferred service dealer.</span></span></a>
									
									</s:if>
									<s:else>
									<s:if test="dealerList.size()>0">
									<p id="dealerslist" class="brand-info">
										Here are the <s:property value="dealerList.size()" /> closest dealer(s) based on the information you entered. Select a preferred dealer or </p></s:if><br><a href="javaScript:;"  
											onclick="searchDealer();  locatelogin();" class="adddealer-link" data-analytics-link-component-name="form" data-analytics-link-type="button" data-analytics-link-description="add_a_dealer"><span id="adddealer1" class="brand-underline link-height"><span class="brand-link">Add your preferred service dealer.</span></span></a>
									
									</s:else>
									<div class="row margin-0 LocateDealer-radio brand-tmargin20" id="radiovalues">
										<s:if test="dealerList.size()>0">
											<s:iterator value="dealerList" status="status" var="dealer">
												<s:if test="#status.index <3">
													<div class="radio">
														<input type="radio"
															value="${dealer.name};${dealer.street};${dealer.city};${dealer.state};${dealer.zip};${dealer.phone};${dealer.id}"
															name="optradio"> <label style="cursor: text;">

															<h6><s:text name="name" /></h6><span> <s:text
																	name="street" /><br> <s:text name="city" />,<s:text
																	name="state" /> <s:text
																	name="zip" /><br> Phone: <s:text name="phone" />
														</span>

														</label>

													</div>
												</s:if>
											</s:iterator>
										</s:if>
									</div>

									<div id="anotherSearch"
										style="display: none; padding-bottom: 0px; "></div>
									<span id="changeDealer" style="display: none"> <a
										href="javaScript:;" onclick="searchDealer();  locatelogin();"><span 
										class="brand-underline"><span class="brand-link">CHANGE
											YOUR PREFERRED SERVICE DEALER</span></span></a></span>
								</div>

								<div class="error">
									<div id="joinmeErrormssg"></div>
								</div>
								<div class="brand-tmargin50">
								<button class="btn btn-default btn-red brand-button-white"
									onclick="return joinMymazda();" type="submit" data-analytics-link-component-name="form" data-analytics-link-type="submit" data-analytics-link-description="submit">JOIN
									MYMAZDA</button>
								<button type="button"
									class="btn btn-default btn-gray brand-button-white" id="Backtab"
									onclick="tabChange();" data-analytics-link-component-name="form" data-analytics-link-type="button" data-analytics-link-description="back">Back</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>






