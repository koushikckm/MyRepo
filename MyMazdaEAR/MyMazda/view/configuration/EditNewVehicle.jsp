<%@ taglib prefix="s" uri="/struts-tags"%>  

<jsp:include page="../common/Header.jsp" />	
<jsp:include page="/view/common/DataLayerJs.jsp"></jsp:include>

<script type="text/javascript">
	$(document).ready(function() {
    	var editvinnumber = vinSelector.innerHTML;
		dataLayer.page.name = 'edit_vehicle';
		dataLayer.page.URL = window.location.href;
		dataLayer.site.section = 'mymazda';
		dataLayer.site.subsection = 'vehicle';
		dataLayer.form.name = 'edit_vehicle';
		dataLayer.form.type = 'edit_vehicle';
		dataLayer.vehicle.vin = editvinnumber;
		dataLayer.user.type = 'o';
	});
	var editcondition, editcond, editmiles;
	function text(){
	 	editcondition = $("#drivingCondition").val();
	 	if(editcondition == 'S'){
	 		editcond = 'std';
	 	}else{
	 		editcond = 'hd';
	 	}
		editmiles = $("#milesPerDay").val();
		dataLayer.vehicle.milesBySchedule = editcond+':'+editmiles;
	}
</script>
  
    <div class="overlay"></div>    
    <div class="EditNewVehicle-Page">
        <div class="container">
			<div class="row margin-0">
			<div class="col-xs-12 col-sm-12 col-md-12 Reg-head">
			<div class="search-field1">
			<input type="search" class="form-control editsearchkey" placeholder="Locate Nearest Dealer (Enter Zip Code)" id="editSearchDealer"/>
			<i class="fa fa-map-marker"  onclick="return editsearchDealers();" style="cursor:pointer;"></i>
			</div>
			</div>
			</div>
		</div>
		<div class="Menu2">
			<div class="container">
				<nav class="navbar navbar-default sub-menu2">
				<div class="container-fluid padding-0">
				<div class="navbar-header">
				<button type="button" class="navbar-toggle accordion-toggle collapsed vehicle-menu" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				<i class="fa fa-plus-circle"></i>
				</button>
				<a href="index.jsp" class="summary" id="editcarname"><s:text name='#session.selectedVehical.carlineDesc'/></a>
				</div>
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
				<li><a href="javaScript:;" onclick="return serviceHistory('<s:text name='#session.selectedVehical.vin'/>');" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="service_history"><span class="Menu2-no-divider"><span class="Menu2-no-divider"></span>SERVICE HISTORY</a></li>
				<li><a href="http://www.mazdausa.com/MusaWeb/displayManualsByModelAndYearHome.action" target="_blank" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="manuals_and_references"><span class="Menu2-divider"></span>Manuals & References</a></li>
				<li><a href="javaScript:;" target="_blank" onclick="return maintenanceSchedule();" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="maintenance_schedule"><span class="Menu2-divider"></span>MAINTENANCE SCHEDULE</a></li>
				<li><a href="#" onclick="return videoDemosLogin();" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="vehicle_demos_guides"><span class="Menu2-divider"></span>Vehicle Demos & Guides</a></li>
				<s:if test="#session.egiftTab == 0  && #session.egiftTabOnSelectVeh ==0 && #session.egiftTabOnRelPage == 0">
					<li><a href="javaScript:;" target="_blank" onclick="return eGift('<s:text name='#session.selectedVehical.vin'/>');"><span class="Menu2-divider"></span>MY eGIFTS</a></li>
				</s:if>
						<!-- <div class="dropdown loggedin-myprofile">
							  <button class="dropbtn">MY PROFILE</button>
							  <div class="dropdown-content dropdown-links-myprofile">
							    <a href="javaScript:;" class="dropdown-link"
										onclick="return updateInformation();">EDIT ACCOUNT INFO</a>
							    <a href="javaScript:;" class="dropdown-link"
										onclick="return changePassword();">CHANGE PASSWORD</a>
								<a href="mymazdaHome.action" class="dropdown-link">LOGOUT</a>
							  </div>
						</div> -->
				</ul>
				</div>
				</div>
				</nav>
			</div>
		</div>
        <div class="container submenu-class">            
            <h1>EDIT YOUR VEHICLE</h1>
            <p class="p-header brand-text">MyMazda is the most helpful when you can keep your vehicle's information current.</p>
                <s:if test="#session.RegistrationTO != null">
                    <s:iterator value="#session.RegistrationTO">
                        <div class="row EditNewVehicle-div margin-0">
                        	<div class="col-xs-12 col-sm-3 v-information">
                                 <h4>
                                    UPLOAD IMAGE
                                </h4> 
                                
								<s:form action="uploadImage" method="post" enctype="multipart/form-data">
									<div class="button_section">
                                		<!-- <input type='file' name="myImage" onchange="readURL(this);" /> -->
										<input type="file" id="fileChooser" name="myImage" onchange="return validateFileUpload()" />     									
										<s:if test="#session.userUploadedImagePath!=null">
    										<img id="myImage" src="<s:property value="#session.userUploadedImagePath"/>" width="200" height="200" alt="your image" />
    									</s:if>
    									<s:elseif test="#session.stockImagePath!=null">
    										<img id="myImage" src="<s:property value="#session.stockImagePath"/>" width="200" height="200" alt="your image" />
    									</s:elseif>
    									<s:else>
    										<img id="myImage" src="view/vhelper/images/default-user-img.png" width="200" height="200" alt="your image" />
    										<!-- <img class="user-uploaded-image" src="view/vhelper/images/default-user-img.png"/> -->
    									</s:else>
    								</div>
    								<br>
    								<div class="button_section">
										<button type="submit" class="btn btn-default btn-red brand-button-white" onclick="return upload();">UPLOAD PHOTO</button>
									</div>
								</s:form>
								<br>
								<s:if test="#session.userUploadedImagePath!=null">
									<s:form action="deleteImage" method="post" >
										<div class="button_section">
											<button type="submit" class="btn btn-default btn-red brand-button-white">DELETE PHOTO</button>
										</div>
									</s:form>
								</s:if>
                        </div>
                            <div class="col-xs-12 col-sm-4 v-information brand-bdr-rgt">
                                <h4>VEHICLE INFORMATION
                                    <div class="required-text"><span class="mandatory-field">*</span>Required</div>
                                </h4>
                                <div class="row margin-0">
                                <%-- <s:if test="#session.RegistrationTO != null ">
                                     <s:iterator value="#session.RegistrationTO"> --%>
                                <%-- <s:if test="#session.vehicalsList != null">
                                   <s:iterator value="#session.vehicalsList" status="count" >
                                   <s:if test="#count.index <1"> --%>
                                    <div class="form-group col-sm-6 col-md-5">
                                        <label>NAME YOUR MAZDA<span class="mandatory-field">*</span></label>
                                        <input type="Nameyourmazda" class="form-control" id="titleSelector" name="carlineDesc" value="<s:property value="carlineDesc"/>">                                                                                   
                                    </div>
                                    <div class="col-md-7 col-sm-6 v-align">
                                        <span class="brand-info">For Example "My First Mazda"</span>
                                    </div>
                                    <div class="clearfix"></div>
                                     
                                    <div class="form-group col-sm-8">
                                        <label>VIN<span class="mandatory-field">*</span></label>
                                        <input type="vin" class="form-control" id="vinSelector" value="<s:property value="vin"/>" maxlength="17" readonly="readonly">                          
                                    </div>
                                    <div class="form-group col-sm-12">
                                        <label>CURRENT MILEAGE</label>
                                       <input type="mileage" class="form-control mileage-input" id="mileage" name="mileage" value="<s:property value="mileage"/>">                                                                                  
                                    </div>
                                    <%-- </s:if>
                                   </s:iterator>
                                   </s:if>  --%> 
                                    <div class="clearfix"></div>
                                    <div class="form-group col-sm-7 col-md-7">
                                        <label>MILES DRIVEN PER DAY<span class="mandatory-field">*</span></label>
                                        <div class="select-style">
                                            <select name="milesPerDay" id="milesPerDay" onchange="text()">
                                             	<option value="0">SELECT</option>
                                                <option value="10">0-10</option>
												<option value="25">11-25</option>
												<option value="50">26-50</option>
												<option value="75">51-75</option>
												<option value="100">76-100</option>
												<option value="150">101-150</option>
												<option value="200">151-200</option>
												<option value="300">201-300</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="clearfix"></div>
                                    <div class="form-group col-sm-8">
                                        <label>TYPICAL DRIVING CONDITIONS<span class="mandatory-field">*</span></label>
                                        <div class="select-style">
                                            <select id="drivingCondition" onchange="text()">
                                              	<option value="">SELECT</option>
												<option value="S">Standard Driving Conditions</option>
												<option value="H">Heavy-Duty Conditions</option>
											</select>
                                        </div>
                                    </div>
                                    <div class="clearfix"></div>
	                                <div class=" col-sm-12 v-align edit_drive_info">
                                  <a href="#" data-toggle="modal" data-target="#driving-condition" data-analytics-link-component-name="form" data-analytics-link-type="button" data-analytics-link-description="driving_cond_defc"><span class="brand-underline"> 
                                 	DETERMINE YOUR TYPICAL DRIVING CONDITIONS
                                  </span>
                                  </a>
                                </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-5 preferred-dealer-div">
                      		<h4>PREFERRED SERVICE DEALER
                      			<div class="required-text"><span class="mandatory-field">*</span>Required</div>
                      		</h4>
                     <div  class="preferred-dealer">
                     <%-- <div id="hidedealer">
                        <h4 id="anotherSearch1" ></h4>
                         <div id="anotherSearch2" ></div>
                         <div id="anotherSearch5" ></div>
                         Phone: <span class="phone-number"><span id="anotherSearch3" ></span></span>
                         <div id="anotherSearch4" ></div>
                      </div> --%>
                            <div id="editdealer" class="Editdealer-text">
                            <s:if test="#session.RegistrationTO.dealerName != null ">
							<table id="dealerContentId">
								<tr>
								<td class="brand-bpadding20">
									<p id='Dealer-<s:property value="#session.RegistrationTO.ServiceDealerID"/>' style="margin: 0% 0% 0% -27%;padding-left:10%;"></p>
										<h6 id="adddelername"><s:text name="#session.RegistrationTO.dealerName"></s:text></h6>
										
										<span id="addDealerStreet"><s:text name="#session.RegistrationTO.dealerStreet" /></span><br>
										<span id="addDealerCity"><s:text name="#session.RegistrationTO.dealerCity" /></span>
										<s:if test="#session.RegistrationTO.dealerName != '' ">,
										<span id="addDealerSate"><s:text name="#session.RegistrationTO.dealerState" /></span>
										<span id="addDealerzip"><s:text name="#session.RegistrationTO.dealerZip" /></span>										
										<p style="padding:5px 0 0 0 !important">Phone: <span id="addDealerphone"><s:text name="#session.RegistrationTO.dealerPhone" /></span></p></s:if>
									
								</td>
								</tr>
							</table>
								<div id="EditregdealerID" style="display:none"><s:property value="#session.RegistrationTO.ServiceDealerID"/></div>
						</s:if>
                            </div>
                        
                                 <s:if test="#session.selectedVehical.dealerName != null">   <a href="javaScript:;" data-id="addvehicle" onclick=" preferredsearchDealerEdit();  editLogin();" id="changedealer"  ><span
										class="brand-underline link-height"><span class="brand-link">Change your preferred service dealer</span></span></a></s:if>
                                <s:else>    <a href="javaScript:;" data-id="addvehicle" onclick=" preferredsearchDealerEdit();  editLogin();" id="adddealer" ><span
										class="brand-underline link-height"><span class="brand-link">Add your preferred service dealer</span></span></a></s:else>
                                <a href="javaScript:;" data-id="addvehicle" onclick=" preferredsearchDealerEdit();  editLogin();" id="changedealer" style="display:none;" data-analytics-link-component-name="form" data-analytics-link-type="button" data-analytics-link-description="edit_dealer"><span
										class="brand-underline link-height"><span class="brand-link">Change your preferred service dealer</span></span></a>
                    </div>
                           <div class="button_section">
                                <button type="submit" class="btn btn-default btn-red brand-button-white" onclick="return updateMileage('Reg');" data-analytics-link-component-name="form" data-analytics-link-type="button" data-analytics-link-description="submit">SAVE CHANGES</button>
                                <button type="submit" class="btn btn-default btn-gray brand-button" onclick="return cancel();" data-analytics-link-component-name="form" data-analytics-link-type="button" data-analytics-link-description="cancel_edit">CANCEL</button>
                                <button type="submit" class="btn btn-default btn-red brand-button-white" onclick="return deleteVehicle('<s:text name='#session.selectedVehical.vin'/>','<s:text name='#session.selectedVehical.carlineDesc'/>');" data-analytics-link-component-name="form" data-analytics-link-type="button" data-analytics-link-description="delete_vehicle">DELETE</button>
                            </div>
                        </div>                        
                        </div>
                    </s:iterator>
                </s:if>
                <s:else>
                    <div class="row EditNewVehicle-div margin-0">
                    
                    	<div class="col-xs-12 col-sm-3 v-information">
                                 <h4>
                                    UPLOAD IMAGE
                                </h4> 
                                
								<s:form action="uploadImage" method="post" enctype="multipart/form-data">
									<div class="button_section">
                                		<!-- <input type='file' name="myImage" onchange="readURL(this);" /> -->
										 <input type="file" id="fileChooser" name="myImage" onchange="return validateFileUpload()" />    									
										<s:if test="#session.userUploadedImagePath!=null">
    										<img id="myImage" src="<s:property value="#session.userUploadedImagePath"/>" width="200" height="200" alt="your image" />
    									</s:if>
    									<s:elseif test="#session.stockImagePath!=null">
    										<img id="myImage" src="<s:property value="#session.stockImagePath"/>" width="200" height="200" alt="your image" />
    									</s:elseif>
    									<s:else>
    										<img id="myImage" src="view/vhelper/images/default-user-img.png" width="200" height="200" alt="your image" />
    										<!-- <img class="user-uploaded-image" src="view/vhelper/images/default-user-img.png"/> -->
    									</s:else>
    								</div>
    								<br>
    								<div class="button_section">
										<button id="uploadPhoto" type="submit" class="btn btn-default btn-red brand-button-white" onclick="return upload();">UPLOAD PHOTO</button>
									</div>
								</s:form>
								<br>
								<s:if test="#session.userUploadedImagePath!=null">
									<s:form action="deleteImage" method="post" >
										<div class="button_section">
											<button type="submit" class="btn btn-default btn-red brand-button-white">DELETE PHOTO</button>
										</div>
									</s:form>
								</s:if>
                        </div>
                    	
                        <div class="col-xs-12 col-sm-4 v-information brand-bdr-rgt">
                            <h4>VEHICLE INFORMATION
                                <div class="required-text"><span class="mandatory-field">*</span>Required</div>
                            </h4>
                            <div class="row margin-0">
                                <%-- <s:if test="#session.vehicalsList != null">
                                   <s:iterator value="#session.vehicalsList" status="count" > --%>
                                   
                                    <div class="form-group col-sm-6 col-md-5">
                                        <label>NAME YOUR MAZDA<span class="mandatory-field">*</span></label>
                                        <input type="Nameyourmazda" class="form-control" id="titleSelector" name="carlineDesc" value="<s:property value="carlineDesc"/>">                                                                                   
                                    </div>
                                    <div class="col-md-7 col-sm-6 v-align">
                                        <span class="brand-info">For Example "My First Mazda"</span>
                                    </div>
                                    <div class="clearfix"></div>
                                     
                                    <div class="form-group col-sm-8 col-md-7">
                                        <label>VIN<span class="mandatory-field">*</span></label>
                                        <input type="vin" class="form-control" id="vinSelector" value="<s:property value="vin"/>" maxlength="17" readonly="readonly">                          
                                    </div>
                                    <div class="form-group col-sm-12">
                                        <label>CURRENT MILEAGE<span class="mandatory-field">*</span></label>
                                       <input type="mileage" class="form-control mileage-input" id="mileage" name="mileage" value="<s:property value="mileage"/>">                                                                                  
                                    </div>
                                    
                                  <%--  </s:iterator>
                                   </s:if> --%>
                                <div class="clearfix"></div>
                                <div class="form-group col-sm-7 col-md-7">
                                    <label>MILES DRIVEN PER DAY<span class="mandatory-field">*</span></label>
                                    <div class="select-style">
                                        <select name="milesPerDay" id="milesPerDay" onchange="text()">
                                         <option value="">SELECT</option>
                                            <option value="10">0-10</option>
											<option value="25">11-25</option>
											<option value="50">26-50</option>
											<option value="75">51-75</option>
											<option value="100">76-100</option>
											<option value="150">101-150</option>
											<option value="200">151-200</option>
											<option value="300">201-300</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="clearfix"></div>
                                <div class="form-group col-sm-8">
                                    <label>TYPICAL DRIVING CONDITIONS<span class="mandatory-field">*</span></label>
                                    <div class="select-style">
                                        <select id="drivingCondition" onchange="text()">
                                          <option value="">SELECT</option>
										  <option value="S">Standard Driving Conditions</option>
										  <option value="H">Heavy-Duty Conditions</option>
										</select>
                                    </div>
                                </div>
                                <!-- <div class="clearfix"></div> -->
                                <div class=" col-sm-12 v-align edit_drive_info">
                                  <a href="#" data-toggle="modal" data-target="#driving-condition" data-analytics-link-component-name="form" data-analytics-link-type="button" data-analytics-link-description="driving_cond_defc"><span class="brand-underline"> 
                                 	DETERMINE YOUR TYPICAL DRIVING CONDITIONS
                                  </span>
                                  </a>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-5 preferred-dealer-div">
                      	<h4>PREFERRED SERVICE DEALER
                      			<div class="required-text"><span class="mandatory-field">*</span>Required</div>
                      		</h4>
                      <div class="preferred-dealer" >
                    
                        <div id="editdealer" class="Editdealer-text">
                             <s:if test="#session.selectedVehical.dealerName != null && #session.selectedVehical.serviceDealerID >0">
							<table id="dealerContentId">
								<tr>
								<td class="brand-bpadding20">
									<p id='Dealer-<s:property value="#session.selectedVehical.ServiceDealerID"/>' class="addvhcldealer"></p>
										<h6 id="adddelername"><s:text name="#session.selectedVehical.dealerName"></s:text></h6>
										
										<span id="addDealerStreet"><s:text name="#session.selectedVehical.dealerAddress" /></span><br>
										<span id="addDealerCity"><s:text name="#session.selectedVehical.dealerCity" /></span><s:if test="#session.selectedVehical.dealerName != '' ">,
										<span id="addDealerSate"><s:text name="#session.selectedVehical.dealerState"/></span>
									    <span id="addDealerzip"><s:text name="#session.selectedVehical.dealerZip" /></span>
										<p style="padding:5px 0 0 0 !important">Phone: <span id="addDealerphone"><s:text name="#session.selectedVehical.dealerPhone" /></span></p></s:if>
									
								</td>
								</tr>
							</table>
							<div id="regeditdealerID" style="display:none"><s:property value="#session.selectedVehical.ServiceDealerID"/></div>
						</s:if>
                                
                                
                        </div>
                          <s:if test="#session.selectedVehical.dealerName != null">
                                     <a href="javaScript:;" data-id="addvehicle" onclick=" preferredsearchDealerEdit();  editLogin();" id="changedealer" data-analytics-link-component-name="form" data-analytics-link-type="button" data-analytics-link-description="edit_dealer"><span
										class="brand-underline link-height"><span class="brand-link">Change your preferred service dealer</span></span></a></s:if>
                                 <s:else>  <a href="javaScript:;" data-id="addvehicle" onclick=" preferredsearchDealerEdit();  editLogin();" id="adddealer"><span
										class="brand-underline link-height"><span class="brand-link">Add your preferred service dealer</span></span></a></s:else>
                   					<a href="javaScript:;" data-id="addvehicle" onclick=" preferredsearchDealerEdit();  editLogin();" id="changedealer" style="display:none;" ><span
										class="brand-underline link-height"><span class="brand-link">Change your preferred service dealer</span></span></a>
                    </div>
                    <div class="button_section">
                        <button type="submit" class="btn btn-default btn-red brand-button-white" onclick="return updateMileage('Edit');" data-analytics-link-component-name="form" data-analytics-link-type="button" data-analytics-link-description="submit">SAVE CHANGES</button>
						<button type="submit" class="btn btn-default btn-gray brand-button" onclick="return cancel();" data-analytics-link-component-name="form" data-analytics-link-type="button" data-analytics-link-description="cancel_edit">CANCEL</button>
						<button type="submit" class="btn btn-default btn-red brand-button-white" onclick="return deleteVehicle('<s:text name='#session.selectedVehical.vin'/>','<s:text name='#session.selectedVehical.carlineDesc'/>');" data-analytics-link-component-name="form" data-analytics-link-type="button" data-analytics-link-description="delete_vehicle">DELETE</button>
                    </div>
                    </div>
                </s:else>
        </div>
         <% request.getSession().setAttribute("RegistrationTO", null); %> 
        <% request.getSession().setAttribute("addPreferedDealer", null); %>
    </div>

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
	
    $(document).ready(function() {
		
		document.getElementById("uploadPhoto").style.visibility = "hidden";
		
		 $(".editsearchkey").keyup(function (e) {
		    if (e.keyCode == 13) {
		    	editsearchDealers();
				return false;
		    } 
		});
    });
    $(function(){
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
    });
       function editsearchDealers() {
    	   add='';
    		edit='';
    		registersearch='';
    		var data = {};
    		var dealerSearch = $("#editSearchDealer").val();
    		if (dealerSearch.length === 0) {
    			$("#error-sucess").modal("show");
    			$("#errorMsg").html("You must enter a valid zip code or name");
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
     

	/* 	$(document).ready(function(){
			document.getElementById("uploadPhoto").style.visibility = "hidden";
		}); */
		
    	function validateFileUpload() {
    		//Max Image size set to 2 MB
    		
    		document.getElementById("uploadPhoto").style.visibility = "visible";
			
			var MAX_SIZE = 2097152;
			var fuData = document.getElementById('fileChooser');
			var FileUploadPath = fuData.value;
			if (FileUploadPath == '') {
				$("#error-sucess").modal("show");
				$("#errorMsg").html("Upload an image");			
			}
			else {
    			var Extension = FileUploadPath.substring(FileUploadPath.lastIndexOf('.') + 1).toLowerCase();
    			if ( Extension == "png" || Extension == "jpg" || Extension == "jpeg" || Extension == "PNG" || Extension == "JPG" || Extension == "JPEG") {
                	if (fuData.files && fuData.files[0]) {
                		var size = fuData.files[0].size;
                			if(size > MAX_SIZE){
                   				$("#error-sucess").modal("show");
								$("#errorMsg").html("Image size should be less than 2 MB");
                   				return;
               				}
               				else{
                   				var reader = new FileReader();
                   				reader.onload = function(e) {
                       			$('#myImage')
                       			.attr('src', e.target.result)
                    			.width(200)
                    			.height(200);
             		      		}
             		      	reader.readAsDataURL(fuData.files[0]);
                			}
            			}
    			}
    			else {
        			$("#error-sucess").modal("show");
					$("#errorMsg").html("Images should be of type PNG, JPG, JPEG");
					return;
   				}
			}
	}
	
	
	function upload(){
		var fuData = document.getElementById('fileChooser');
		var FileUploadPath = fuData.value;
			if (FileUploadPath == "") {
				$("#error-sucess").modal("show");
				$("#errorMsg").html("Select an image to upload.");
				return;		
			}
	}
       </script>

