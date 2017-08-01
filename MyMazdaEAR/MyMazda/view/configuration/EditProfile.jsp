<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">
jQuery(document).ready(function(){
		var	state = '${loginDetail.state}';
		state =state.trim();
		document.getElementById("stateId").value=state;
		});
		
	function updateProfile(){
	var dataString = $("#editProfileForm").serialize();
	var firstName = $.trim($("[name='firstName']").val());
	var lastName =$.trim($("[name='lastName']").val());
	var street = $.trim($("[name='street1address']").val());
	var city = $.trim($("[name='city']").val());
	var zip = $.trim($("[name='zip']").val());
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
			}else{
			var url = "updateProfile.action";
			$.ajax({
				type : "POST",
				url : url,
				data : dataString,
				beforeSend : function() {
					showAjaxLoadMsg("Loading...");
				},success : function(result) {
					if(result =="success"){
						showRegistrationErrorMessageWithText("Profile updated successfuly.");
						setTimeout(function() {homeClick();},2250);
					}else {
						
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
	
	
	/* $(function(){	
		$("#editProfileForm").submit(function(e) {
			e.preventDefault();
			if($(".formError:visible").size() > 0){
				return false;
			}			
    		var formObj = $(this);
    		var formURL = formObj.attr("action");
    		var formData = $(this).serialize();
    		$.ajax({
        		url: formURL,
    			type: 'POST',
        		data:  formData,
        		cache: false,
    			success: function(data, textStatus, jqXHR) {
    							/* $("#roleTypeDataContainer").html(data); 
    			}, complete:function(){
	    	  		showInfoMessage();
	    		}, error: function(jqXHR, textStatus, errorThrown) {
     			}         
    		});
    		 //Prevent Default action.
    		//e.unbind();
		});	
	}); */
	
	function cancelUpdateProfile(){
		window.location="myMazda.action";
		return false;
	}
	
	
	
</script>

<header>
	<div class="top_line">
		<div class="container">
			<div class="row">
			<div style="float:left;">
						<ul class="social-links">	
							<li><a href="myMazda.action" class=""><img title="MyMazda" src="view/vhelper/images/home_m.png" class="logoutb"></a></li>
							</ul>
					</div>
				<div class="col-lg-10 col-md-10 col-xs-10 pull-right ">
				<ul class="social-links">
				<li><h2 class="heading" style="margin: 8px 15px 0px 0px;">
						Welcome :
						<s:if test="#session.loginDetail !=null">
						<s:text name="#session.loginDetail.firstName" />  <s:text name="#session.loginDetail.lastName" /> 
						</s:if>
						</h2></li>
						<s:if test="#session.loginUser != null">
							<li><a href="mymazdaHome.action" class=""><img title="Logout"  src="view/vhelper/images/logout1.png" class="logoutb"></a></li>
					</s:if>
					</ul>
				</div>
			</div>
		</div>
	</div>
</header>


<div class="section1 padd50">
      <div class="container">
        <div class="row">
        <div class="col-md-7 col-md-offset-2 col-sm-12 col-xs-12 ">
        
           <span class="welcome">Edit Account information</span>

				<div class="innerContent">
					<form class="myaccount" id="editProfileForm" action="">
						<s:iterator value="#session.loginDetail">

							<div class="col-lg-3 col-md-3 col-sm-4">
								<span><label>TITTLE</label><select
									class="selectBoxSmall ffe">
										<option>Mr.</option>
										<option>Mrs.</option>
										<option>Ms.</option>
										<option>Dr.</option>
								</select>
								</span>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-4 ffe">
								<span><label>FIRST NAME <strong>*</strong>
								</label><input type="text" value="<s:property value="firstName"/>"
									name="firstName" class="textType2 ffd">
								</span>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-4 ffe">
								<span><label>LAST NAME <strong>*</strong>
								</label><input type="text" value="<s:property value="lastName"/>"
									name="lastName" class="textType2 ffd">
								</span>
							</div>
							<div class="clearfix"></div>
							<div class="col-lg-9 col-md-12 col-sm-12 ffc">
								<span><label>street address 1 <strong>*</strong>
								</label><input type="text" value="<s:property value="street1address"/>"
									name="street1address" style="width:100%;" class="textType2">
								</span>
							</div>
							<div class="col-lg-9 col-md-12 col-sm-12 ffc">
								<span><label>street address 2</label><input type="text"
									value="<s:property value="street2address"/>"
									name="street2address" style="width:100%;" class="textType2">
								</span>
							</div>
							<div class="clearfix"></div>
							<div class="col-lg-3 col-md-3 col-sm-4">
								<span><label>CITY <strong>*</strong></label>
								<input type="text" value="<s:property value="city"/>"
									name="city" class="textType2 ffe">
								</span>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-4 ffe">
								<span><label>STATE <strong>*</strong>
								</label><select id="stateId" class="selectBox01 ffd" name="state">
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
								</span>
							</div>

							<div class="col-lg-3 col-md-3 col-sm-4">
								<span><label>ZIP CODE <strong>*</strong>
								</label><input type="text" value="<s:property value="zip"/>" name="zip"
									style="width:100%;" class="textType2">
								</span>
							</div>
							<div class="clearfix"></div>
							<div class="col-lg-9 col-md-12 col-sm-8 ffc">
								<span><label>Phone Number</label><input type="text"
									value="<s:property value="mobilePhone"/>" name="mobilePhone"
									style="width:100%;" class="textType2">
								</span>
							</div>
							<div class="clearfix"></div>
							<br>
							<div style="margin-left:18px; margin-bottom:7px;">
								<label>&nbsp;</label><input type="button"
									onclick="return updateProfile();" class="Submit_btn"
									value="Update" name="">&nbsp;&nbsp;&nbsp;
								<!-- <input type="submit" class="Submit_cncl" value="Cancel" name="" onclick="return homeClick();"> -->
								<input type="button" onclick="return cancelUpdateProfile();"
									class="Submit_cncl" value="Cancel" name="">

							</div>
						</s:iterator>
					</form>
				</div>




			</div>
      </div>
     	<s:if test="hasActionMessages()">
				   <div>
				      <s:actionmessage/>
				   </div>
				</s:if>
      </div>
      </div>
      
      <!-- Begin Omniture tag --><!-- SiteCatalyst code version: H.23.4. -->
                <script type="text/javascript">var s_account="mazdaownersdev,mazdaglobal"</script>
				<!-- mp_trans_disable_start -->
				<script language="JavaScript" src="view/vhelper/js/s.code.dev.js"></script>
				<script language="JavaScript">
				s.server="341067-MAZADS002.mazdausa.com";s.pageName="musa:owners_mymazda_profile";s.channel="owners_mymazda_profile";s.hier1="My Mazda Page, MUSA";s.prop4="X";s.prop6="tools_owners_mymazda_recalls";s.prop8="owners_mymazda_recalls";s.prop9="en";s.prop22="0";s.eVar39=screen.width+'x'+screen.height;s.eVar40="tier1";s.eVar41="no";s.business="mazdausa.com";s.eVar11="A";/************* DO NOT ALTER ANYTHING BELOW THIS LINE !**************/ var s_code=s.t();if(s_code)document.write(s_code)</script>
				<noscript><img src="http://mazdausa.112.2o7.net/b/ss/mazdausacom/1/H.23.4--NS/0" height="1" width="1" border="0" alt="" /></noscript>
				<!-- mp_trans_disable_end -->
                <!-- End Omniture tag -->
      