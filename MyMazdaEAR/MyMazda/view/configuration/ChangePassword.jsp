<jsp:include page="../common/Header.jsp" />	
<jsp:include page="/view/common/DataLayerJs.jsp"></jsp:include>
<jsp:include page="ChangePasswordJs.jsp"></jsp:include>
<%@ page import="com.mazda.configuration.common.transferobject.RegistrationTO"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">
	$(document).ready(function() {
		dataLayer.page.name = 'change_password';
		dataLayer.page.URL = window.location.href;
		dataLayer.site.sectionHistorical = 'owners';
		dataLayer.site.section = 'mymazda';
		dataLayer.site.subsection = 'owners';
		dataLayer.form.name = 'change_password';
		dataLayer.form.type = 'change_password';
		dataLayer.user.type = 'o';
	});
</script>

	<div class="overlay"></div>
	<div class="EditAccountInformation-Page">
		<div class="container">
			<h1>CHANGE PASSWORD</h1>
			<form class="myaccount" id="changePasswordForm" action="">
				<s:iterator value="#session.loginDetail">
					<div class="row EditAccount-div margin-0">
						<div class="col-sm-12 col-md-6 paddin_30 padding-0">
							<div class="row margin-0">
								<s:if test="#session.loginDetail != null"><br>
								<div class="required-text"><span class="mandatory-field">*</span>Required</div>
								<div class="form-group col-sm-8">
									<label>CURRENT PASSWORD<span class="mandatory-field">*</span></label>
									<input type="password" class="form-control" id="currentPassword"/>
									
								</div>
								<div class="form-group col-sm-8">
									<label>NEW PASSWORD<span class="mandatory-field">*</span></label>
									<input type="password" class="form-control" id="newPassword"/> 
									<label class="brand-info">Must be 4-15 characters long.<br>
									Allows alphanumeric and symbols @ * $ . ! %  </label>
								</div>
								<div class="form-group col-sm-8">
									<label>CONFIRM PASSWORD<span class="mandatory-field">*</span></label>
									<input type="password" class="form-control" id="confirmPassword"/>
								</div>
								</s:if>
							</div>
							
							<div class="btn-spacing">
								<button type="submit" class="btn btn-default btn-red brand-button-white"
									onclick="return submitPasswordChange();" data-analytics-link-component-name="form" data-analytics-link-type="submit" data-analytics-link-description="submit">SUBMIT</button>
								<button type="submit" class="btn btn-default btn-gray brand-button"
									onclick="return cancelChangePassword();" data-analytics-link-component-name="form" data-analytics-link-type="button" data-analytics-link-description="cancel">CANCEL</button>
							</div>
						</div>
						
					</div>
				</s:iterator>
			</form>
		</div>
	</div>

