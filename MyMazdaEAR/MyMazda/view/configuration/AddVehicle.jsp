<%@ taglib prefix="s" uri="/struts-tags"%>

<jsp:include page="/view/common/DataLayerJs.jsp"></jsp:include>

<script type="text/javascript">
	$(document).ready(function() {
		dataLayer.page.name = 'add_vehicle';
		dataLayer.page.nameHistorical = 'musa:owners_mymazda_add_new_vehicle';
		dataLayer.page.nameHistorical = 'owners_mymazda_myvehicle';
		dataLayer.page.URL = window.location.href;
		dataLayer.site.sectionHistorical = 'owners';
		dataLayer.site.section = 'mymazda';
		dataLayer.site.subsection = 'vehicle';
		dataLayer.form.name = 'add_new_vehicle';
		dataLayer.form.type = 'add_vehicle';	
		dataLayer.user.type = 'o';
	});
	function vinNum(){
	var trackVin = $("#vinSelector").val();
		if(trackVin.length == 17){
			dataLayer.vehicle.vin = trackVin;
		}
	}
	var addcondition, addcond, addmiles;
	function text(){
	 	addcondition = $("#drivingCondition").val();
	 	if(addcondition == 'S'){
	 		addcond = 'std';
	 	}else{
	 		addcond = 'hd';
	 	}
		addmiles = $("#milesPerDay").val();
		dataLayer.vehicle.milesBySchedule = addcond+':'+joinmiles;
	}
</script>

    <div class="overlay"></div>    
    <div class="AddNewVehicle-Page">
        <div class="container">
            <div class="row margin-0">
                <div class="col-xs-12 col-sm-12 col-md-12 Reg-head">
                    <div class="search-field1">
                        <input type="search" class="form-control addsearchkey" placeholder="Locate Nearest Dealer (Enter Zip Code)" id="addsearchkey" />
                        <i class="fa fa-map-marker" onclick="return addsearchDealers();" style="cursor:pointer;"></i>
                    </div>
                </div>
            </div>
            <h1>ADD NEW VEHICLE</h1>
            <p class="p-header brand-text">Congratulations on owning another Mazda. Please enter your new vehicle's information below to get started.</p>
            <div class="row AddNewVehicle-div margin-0">
                <s:if test="#session.RegistrationTO != null ">
                <s:iterator value="#session.RegistrationTO">
                <div class="col-xs-12 col-sm-6 v-information brand-bdr-rgt" >
                    <h4>VEHICLE INFORMATION
                        <p class="required-text"><span class="mandatory-field">*</span>Required</p>
                    </h4>
                    <div class="row margin-0">
                        <div class="form-group col-sm-6 col-md-5">
                            <label>NAME YOUR MAZDA<span class="mandatory-field">*</span></label>
                            <input type="Nameyourmazda" class="form-control spacing-mazda" id="titleSelector" placeholder="ENTER NAME" name="carlineDesc" value="<s:property value="carlineDesc"/>">                                                                                 
                        </div>
                        <div class="col-md-7 col-sm-6 v-align display-none">
                            <span class="brand-info">For Example "My First Mazda"</span>
                        </div>
                        <div class="clear"></div>
                        <div class="form-group col-sm-6">
                            <label>VIN<span class="mandatory-field">*</span></label>
                            <input type="vin" class="form-control" name="vin" id="vinSelector" onkeyup="vinNum()" placeholder="ENTER VIN" value="<s:property value="vin"/>" maxlength="17">                                                                                    
                        </div>
                        <div class="col-sm-6 v-align">
                            <a href="#" data-toggle="modal" data-target="#findmyvin" data-analytics-link-component-name="add_new_vehicle_form" data-analytics-link-type="button" data-analytics-link-description="find_vin"><span 
									class="brand-underline"><span class="brand-link">Where do I find my VIN?</span></span></a>
                        </div>
                        <div class="clearfix"></div>
                        <div class="form-group col-sm-12 v-align-p">
                            <span class="brand-info">By entering your vehicle information, you allow us to access important details about your vehicle that will help maximize your experience.</span>
                        </div>                                                                           
                        <div class="form-group col-sm-12">
                            <label>CURRENT MILEAGE<span class="mandatory-field">*</span></label>
                            <input type="mileage" class="form-control mileage-input"  id="mileage"  placeholder="ENTER MILEAGE"name="mileage" value="<s:property value="mileage"/>">                                                                                    
                        </div>
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
                                    <option value="10">101-150</option>
									<option value="200">151-200</option>
									<option value="300">201-300</option>
                                </select>
                            </div>                                                              
                        </div>
                        <div class="clearfix"></div>
                        <div class="form-group col-sm-8 col-md-7" id="drivingConditions">
                            <label>TYPICAL DRIVING CONDITIONS<span class="mandatory-field">*</span></label>
                            <div class="select-style">
                               <select   name="drivingCondition" id="drivingCondition" onchange="text()">
                                                <option value="">SELECT</option>
													<option value="S">Standard Driving Conditions</option>
													<option value="H">Heavy-Duty Conditions</option>
													
												</select>
                            </div>                                                                                  
                        </div>
                        <div class="clearfix"></div>
						<div style="text-align: left; margin-left: 5px;">
							<a href="#" data-toggle="modal" data-target="#driving-condition" data-analytics-link-component-name="add_new_vehicle_form" data-analytics-link-type="button" data-analytics-link-description="driving_cond_def"><span 
									class="brand-underline"><span class="brand-link">DRIVING CONDITION DEFINITIONS</span></span></a>
						</div>
                    </div>  
                </div>
                <div class="col-xs-12 col-sm-6 preferred-dealer-div" id="addvehicle">
                    <h4>PREFERRED SERVICE DEALER
						<p class="required-text"><span class="mandatory-field">*</span>Required</p>
					</h4>
                        <div  class="preferred-dealer">
                  
                            <div id="editdealer" class="Editdealer-text">
                             <s:if test="#session.RegistrationTO.dealerName != null ">
													<table id="dealerContentId">
														<tr>
														<td class="brand-bpadding20">
															<p id='Dealer-<s:property value="#session.RegistrationTO.ServiceDealerID"/>' style="margin: 0% 0% 0% -27%;padding-left:10%;">
																<h6 id="adddelername"><s:text name="#session.RegistrationTO.dealerName"></s:text></h6>
																
																<span id="addDealerStreet"><s:text name="#session.RegistrationTO.dealerStreet" /></span><br>
																<span id="addDealerCity"><s:text name="#session.RegistrationTO.dealerCity" /></span> <s:if test="#session.RegistrationTO.dealerName != '' ">,
																<span id="addDealerSate"><s:text name="#session.RegistrationTO.dealerState" /></span> <span id="addDealerzip"><s:text name="#session.RegistrationTO.dealerZip" /></span>
																<br> 
																<span>Phone: </span><span id="addDealerphone"><s:text name="#session.RegistrationTO.dealerPhone" /></span></s:if>
															</p>
														</td>
														</tr>
													</table>
													<div id="regdealerID" style="display:none"><s:property value="#session.RegistrationTO.ServiceDealerID"/></div>
												</s:if>
                                                    </div>
                                <a id="changedealer"href="javaScript:;" onclick="return preferredsearchDealer();" ><span 
											class="brand-underline link-height"><span class="brand-link">Change your preferred service dealer</span></span></a>
                                <a id="adddealer" style="display:none;" href="javaScript:;" data-id="addvehicle" onclick="return preferredsearchDealer();" data-analytics-link-component-name="add_new_vehicle_form" data-analytics-link-type="button" data-analytics-link-description="add_preferred_dealer"><span 
											class="brand-underline link-height"><span class="brand-link">Add your preferred service dealer</span></span></a>
                                </div>
                        <div class="button_section">
                            <button type="submit" class="btn btn-default btn-red brand-button-white" id="addCarSubmitBtn" onclick="return addMoreVehical('Reg');" data-analytics-link-component-name="add_new_vehicle_form" data-analytics-link-type="submit" data-analytics-link-description="add_vehicle">ADD VEHICLE</button>
                            <button type="submit" class="btn btn-default btn-gray brand-button" onclick="return cancel();" id="addCancel" data-analytics-link-component-name="add_new_vehicle_form" data-analytics-link-type="button" data-analytics-link-description="cancel">CANCEL</button>
                        </div>
                    </div>
                
                        </s:iterator>
                    </s:if>
                                                  
                 <s:else>
                <div class="col-xs-12 col-sm-6 v-information brand-bdr-rgt">
                    <h4>VEHICLE INFORMATION
                       <p class="required-text"><span class="mandatory-field">*</span>Required</p>
                    </h4>
                       
                    <div class="row margin-0">
                        <div class="form-group col-sm-6 col-md-5">
                            <label>NAME YOUR MAZDA<span class="mandatory-field">*</span></label>
                            <input type="Nameyourmazda" class="form-control spacing-mazda" id="titleSelector" name="carlineDesc" placeholder="ENTER NAME">                                                                                  
                        </div>
                        <div class="col-md-7 col-sm-6 v-align display-none">
                            <span class="brand-info">For Example "My First Mazda"</span>
                        </div>
                        <div class="clear"></div>
                        <div class="form-group col-sm-6">
                            <label>VIN<span class="mandatory-field">*</span></label>
                            <input type="vin" class="form-control" id="vinSelector" onkeyup="vinNum()" name="vin" placeholder="ENTER VIN" maxlength="17">                                                                                 
                        </div>
                        <div class="col-sm-6 v-align">
                        	<a href="#" data-toggle="modal" data-target="#findmyvin" data-analytics-link-component-name="add_new_vehicle_form" data-analytics-link-type="button" data-analytics-link-description="find_vin"><span 
									class="brand-underline"><span class="brand-link">Where do I find my VIN?</span></span></a>
                        </div>
                        <div class="clearfix"></div>
                        <div class="form-group col-sm-12 v-align-p">
                                <span class="brand-info">By entering your vehicle information, you allow us to access important details about your vehicle that will help maximize your experience.</span>
                        </div>                                                                           
                        <div class="form-group col-sm-12">
                            <label>CURRENT MILEAGE<span class="mandatory-field">*</span></label>
                            <input type="mileage" class="form-control mileage-input" id="mileage" name="mileage" placeholder="ENTER MILEAGE">                                                                                   
                        </div>
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
                                    <option value="10">101-150</option>
									<option value="200">151-200</option>
									<option value="300">201-300</option>
                                </select>
                            </div>                                                                              
                        </div>
                        <div class="clearfix"></div>
                        <div class="form-group col-sm-8 col-md-7" id="drivingConditions">
                            <label>TYPICAL DRIVING CONDITIONS<span class="mandatory-field">*</span></label>
                            <div class="select-style">
                               <select name="drivingCondition" id="drivingCondition" onchange="text()">
                                            <option value="">SELECT</option>
													<option value="S">Standard Driving Conditions</option>
													<option value="H">Heavy-Duty Conditions</option>
													
												</select>
                            </div>                                                                              
                        </div>
                        <div class="clearfix"></div>
						<div style="text-align: left; margin-left: 5px;">
							<a href="#" data-toggle="modal" data-target="#driving-condition" data-analytics-link-component-name="add_new_vehicle_form" data-analytics-link-type="button" data-analytics-link-description="driving_cond_def"><span 
									class="brand-underline"><span class="brand-link">DRIVING CONDITION DEFINITIONS</span></span></a>
						</div>
                    </div>  
                </div>
                <div class="col-xs-12 col-sm-6 preferred-dealer-div" id="addvehicle">
                    <h4>PREFERRED SERVICE DEALER
                    <p class="required-text"><span class="mandatory-field">*</span>Required</p>
                    </h4>
              
                    <div class="preferred-dealer brand-tmargin20" >
                    	<a href="javaScript:;" data-id="addvehicle" onclick="return preferredsearchDealer();" data-analytics-link-component-name="add_new_vehicle_form" data-analytics-link-type="button" data-analytics-link-description="add_preferred_dealer"><span 
								class="brand-underline link-height"><span class="brand-link">Add your preferred service dealer</span></span></a>
                    </div>
                    <div class="button_section">
                         <button type="submit" class="btn btn-default btn-red brand-button-white" id="addCarSubmitBtn" onclick="return addMoreVehical('Edit');" data-analytics-link-component-name="add_new_vehicle_form" data-analytics-link-type="submit" data-analytics-link-description="add_vehicle">ADD VEHICLE</button>
                         <button type="submit" class="btn btn-default btn-gray brand-button" onclick="return cancel();" id="addCancel1" data-analytics-link-component-name="add_new_vehicle_form" data-analytics-link-type="button" data-analytics-link-description="cancel">CANCEL</button>
                         <a href="mymazdaHome.action" class="btn btn-default btn-gray brand-button" id="logout_addveh" style="display: none;">CANCEL</a>
                    </div>
                </div>
                </s:else>
                 </div>
                     
             </div>
             <% request.getSession().setAttribute("RegistrationTO", null); %> 
             <% request.getSession().setAttribute("addPreferedDealer", null); %>
             </div>
      
       <script>
       $(document).ready(function() {
   		
  		 $(".addsearchkey").keyup(function (e) {
  		    if (e.keyCode == 13) {
  		    	addsearchDealers();
  				return false;
  		    } 
  		});
      });
       $(function(){
    	   $("#signin").hide();
    	   var name='${selectedVehical.carlineDesc}';
    	   if(name==''){
    		   $("#addCancel").hide();
    		   $("#addCancel1").hide();
    		   $("#logout_addveh").show();
    	   }
       });
       function addsearchDealers() {
    	   add='';
    		edit='';
    		registersearch='';
    		var data = {};
    		var dealerSearch = $("#addsearchkey").val();
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




