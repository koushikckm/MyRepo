<jsp:include page="../common/Header.jsp" />	
<jsp:include page="/view/common/DataLayerJs.jsp"></jsp:include>
<jsp:include page="EditAccountInformationJs.jsp"></jsp:include>
<%@page import="com.mazda.configuration.common.transferobject.RegistrationTO"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">
	$(document).ready(function() {
		dataLayer.page.name = 'edit_profile';
		dataLayer.page.URL = window.location.href;
		dataLayer.site.sectionHistorical = 'owners';
		dataLayer.site.section = 'mymazda';
		dataLayer.site.subsection = 'edit_account';
		dataLayer.form.type = 'edit_account';
		dataLayer.user.type = 'o';
	});
	$('#zipcode').on("input", function() {
		var eInput = this.value;
		if(eInput.length === 5){
		dataLayer.zipCode = eInput;
		}
	});
</script>

<div class="overlay"></div>
	<div class="EditAccountInformation-Page">
		<div class="container">
		   <div class="search-field1">
               <input type="search" class="form-control editaccountsearchkey" placeholder="Locate Nearest Dealers (Enter Zip Code or Name)" id="editsearchKey" />
               <i class="fa fa-map-marker" onclick="return editAccountsearchDealers();" style="cursor:pointer;"></i>
            </div>
			<h1>EDIT ACCOUNT INFORMATION</h1>			
			<form class="myaccount" id="editProfileForm" action="">
				<s:iterator value="#session.loginDetail">
					<div class="row EditAccount-div margin-0">
						<div class="col-xs-12 col-sm-12 col-md-12 padding-0">
						  <h5>CONTACT INFORMATION</h5>
						  <p class="required-text" style="padding: 0 !important;">
						  		<span class="mandatory-field">*</span>Required</p>
						</div>
						<div class="col-sm-12 col-md-6 paddin_30 padding-0">
							<div class="row margin-0">
								<%-- <div class="form-group col-sm-2">
									<label for="email">TITLE</label>
									<div class="select-style">
									 <select class="form-control selectpicker" id="title">
										<option>Mr.</option>
										<option>Mrs.</option>
										<option>Ms.</option>
										<option>Dr.</option>										
									</select>
									</div>
								</div> --%>
								<s:if test="#session.loginDetail != null">
								<div class="form-group col-sm-6">
									<label>FIRST NAME<span class="mandatory-field">*</span></label>
									<input type="First Name" class="form-control" id="firstname"
										value="<s:property value="firstName"/>">
								</div>
								<div class="form-group col-sm-6">
									<label>LAST NAME<span class="mandatory-field">*</span></label>
									<input type="Last Name" class="form-control" id="lastname"
										value="<s:property value="lastName"/>">
								</div>
								<div class="form-group col-sm-12">
									<label>STREET ADDRESS 1<span class="mandatory-field">*</span></label>
									<input type="streetaddress1" class="form-control"
										id="streetaddress1"
										value="<s:property value="street1address"/>">
								</div>
								<div class="form-group col-sm-12">
									<label>STREET ADDRESS 2</label> <input type="street2address2"
										class="form-control" id="streetaddress2"
										value="<s:property value="street2address"/>">
								</div>
								<div class="form-group col-sm-5">
									<label>CITY<span class="mandatory-field">*</span></label> <input
										type="Cityname" class="form-control" id="cityName"
										value="<s:property value="city"/>">
								</div>
								<div class="form-group col-sm-2">
									<label>STATE<span class="mandatory-field">*</span></label>
									<div class="select-style">
									 <select  name="state1" id="state">
									 <s:if test="loginDetail.state!=''">
										 <option selected="selected" ><s:property value="state"/></option> 
									 </s:if>
												<% 
													String stateCodes = (String)request.getSession().getAttribute("STATE_CODES"); 
													if(stateCodes != null) {
														String[] stateCodesArr = stateCodes.split(",");
														for(int stCd=0; stCd < stateCodesArr.length; stCd++) {
															%><option value="<%=stateCodesArr[stCd]%>"><%=stateCodesArr[stCd]%></option><%
														}
													}
												%>
									</select>
								  </div>
								</div>
								<div class="form-group col-sm-5">
									<label>ZIP CODE<span class="mandatory-field">*</span></label> <input
										type="zipcode" class="form-control" id="zipcode" maxlength="5"
										value="<s:property value="zip"/>">
								</div>
								<div class="form-group col-sm-5">
									<label>PHONE NUMBER<span class="mandatory-field">*</span></label> <input type="phonenumber"
										class="form-control" id="phonenumber"
										value="<s:property value="mobilePhone"/>">
								</div>
								</s:if>
								<div class="form-group col-sm-7">
									
								</div>								


							</div>
							<div class="btn-spacing">
								<button type="submit" class="btn btn-default btn-red brand-button-white"
									onclick="return updateProfile();" data-analytics-link-component-name="form" data-analytics-link-type="submit" data-analytics-link-description="update">UPDATE</button>
								<button type="submit" class="btn btn-default btn-gray brand-button"
									onclick="return cancelUpdateProfile();" data-analytics-link-component-name="form" data-analytics-link-type="button" data-analytics-link-description="cancel">CANCEL</button>
							</div>
						</div>
						
					</div>
				</s:iterator>
			</form>
			<s:if test="hasActionMessages()">
				<div>
					<s:actionmessage />
				</div>
			</s:if>
		</div>
	</div>

