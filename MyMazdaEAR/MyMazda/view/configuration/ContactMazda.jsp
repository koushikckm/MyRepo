<%@ taglib prefix="s" uri="/struts-tags"%>

	<script>
		$(".getQuotesFormSHOW").click(function(e) {
			e.preventDefault();
			e.stopPropagation();
			$(".getQuotesForm").show();
		});
		
  function ValidateEmail(email) {
      var expr = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
        return expr.test(email);
    };
		
	function sendMail(){
		var dataString = $("#contactMazdaFormId").serialize();
		var vinNumber = $.trim($("#vinNumberId").val());
		var firstName  = $.trim($("#formId").val());
	    var email  = $.trim($("#emailId").val());
	    var subject  = $.trim($("#subjectId").val());
	    var message  = $.trim($("#textAreaId").val());
			if(vinNumber == ""){
				showRegistrationErrorMessageWithText("You must select a VIN.");
			}
			else if((vinNumber.length) != 17){
				showRegistrationErrorMessageWithText("Please enter your 17 digit valid VIN .");
			}
			else if(firstName == ""){
				showRegistrationErrorMessageWithText("You must select a First Name.");
			}
			else if(email == ""){
				showRegistrationErrorMessageWithText("You must select a Email.");
			} 
			else if (!ValidateEmail(email)) {
              		showRegistrationErrorMessageWithText("Invalid email address.");
        		}
			else if(subject == ""){
				showRegistrationErrorMessageWithText("You must select a Subject.");
			}
			else if(message == ""){
				showRegistrationErrorMessageWithText("You must select a Message.");
			} else{
				var url = "sendMail.action";
			$.ajax({
				type : "POST",
				url : url,
				data : dataString,
				beforeSend : function() {
					showAjaxLoadMsg("Sending Details");
				},success : function(result) {
					showAjaxLoadMsg("Successfully Send");
				},complete : function() {
					hideAjaxLoadMsg();
	    	  		window.scrollTo(0, 50);
	    	  		$("#vinNumberId").val("");
	    	  		$("#formId").val("");
	    	  		$("#emailId").val("");
	    	  		$("#subjectId").val("");
	    	  		$("#textAreaId").val("");
				}
			});
			}
			return false;
	}
	
	</script>


<style>
.welcome{background: none repeat scroll 0 0 hsl(0, 0%, 96%);
    border: 1px solid hsl(0, 0%, 90%);
    color: hsl(0, 0%, 0%);
    display: block;
    font-size: 16px;
    margin: 0 0 -5px;
    padding: 8px 0 5px 10px;
    position: relative;
   }
</style>
<header>
	<div class="top_line">
		<div class="container">
			<div class="row">
				<s:if test="#session.loginUser != null">
				<div class="homeicon">
						<ul class="social-links">	
							<li><a href="myMazda.action" class=""><img title="MyMazda" src="view/vhelper/images/home_m.png" class="logoutb"></a></li>
							</ul>
					</div>
					<div class="col-lg-10 col-md-10 col-xs-10 pull-right ">
						<ul class="social-links">
							<li><h2 class="heading" style="margin: 8px 15px 0 0;">CONTACT MAZDA</h2>
							</li>
							<li><a href="mymazdaHome.action" class=""><img title="Logout"  src="view/vhelper/images/logout1.png" class="logoutb"></a>
							</li>
						</ul>
					</div>
				</s:if>
				<s:else>
				<div class="homeicon">
						<ul class="social-links">	
							<li><a href="myMazda.action" class=""><img title="MyMazda" src="view/vhelper/images/home_m.png" class="logoutb"></a></li>
							</ul>
					</div>
					<div class="col-lg-10 col-md-10 col-xs-10 pull-right ">
						<div class="row">
						<div class="col-lg-9 col-md-8 col-sm-7"><h2 class="heading" style="margin: 8px 15px 0 0;">CONTACT MAZDA</h2></div>
							<div class="col-lg-3 col-md-4 col-sm-5">								
								<ul class="social-links">									
									<li><a href="javaScript:;"
										class="browseCategory getQuotesFormSHOW ">Login</a>
									</li>
									<li><a href="javaScript:;" onclick="return registerNew();"
										class="browseCategory">Sign Up</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</s:else>
			</div>
		</div>
	</div>
</header>

<div id="wrapper">
<div class="section1 padd50">
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-md-10 col-sm-12" style="text-transform: uppercase;">
				<h2>Contact Mazda By phone</h2>
				<p class="brand-text" style="text-transform: none;">
					Mazda Customer Assistance Representatives are available,<br>
					Monday - Friday 6:00 a.m - 5:00 pm Pacific Time<br>(1-800)
					222-5500
				</p>
				<span class="welcome" style="text-transform: none;">Contact Mazda USA by email</span>

				<div class="innerContent" style="padding-left: 10px;">
					<form class="myaccount" method="post" id="contactMazdaFormId">
					<s:if test="#session.loginUser != null">
						<span><label>Vin <strong>*</strong>
						<s:if test="#session.vehicalsList.size() >0"></s:if>
							</label> <s:select name="vin" list="#session.vehicalsList" listKey="vin" style="width:72%;"
									cssClass="selectBox01"  id="vinNumberId" listValue="vin"/>
						</span>
						</s:if>
						<s:if test="#session.loginUser == null">
							<span><label>Vin <strong>*</strong> </label><input style="width:72%;" name="vin"
								type="text" class="textType2" name="from"
								placeholder="Vin Number" id="vinNumberId"> </span>
						</s:if>
						<s:if test="#session.loginUser == null">
						 <span><label>From <strong>*</strong> </label><input style="width:72%;"
							type="text" class="textType2" name="firstName"
							placeholder="Your Name" id="formId"> </span> 
							
							<span><label>Email <strong>*</strong>
								 </label><input type="text" style="width:72%;"  class="textType2" name="email"
							placeholder="Your Email" id="emailId"> </span>
							</s:if> 
							
						<s:if test="#session.loginUser != null">
						<s:iterator value="#session.loginDetail">
						 <span><label>From <strong>*</strong> </label><input style="width:72%;" readonly="readonly"
							type="text" class="textType2" name="firstName"
							value="<s:property value="firstName"/> <s:property value="lastName"/>"> </span> 
							
							<span><label>Email <strong>*</strong>
								 </label><input type="text" style="width:72%;"  class="textType2" 
								 readonly="readonly" name="email"
							value="<s:property value="email"/>"> </span>
							</s:iterator>
							</s:if>
							
							
							<span><label>To
						</label><input type="text" class="textType2" name="to" style="width:72%;" value="MazdaCustomerExperience@mazdausa.com" readonly="readonly"> </span>
							 <span><label>Subject <strong>*</strong>
						</label><input type="text" class="textType2" name="subject" style="width:72%;" id="subjectId"
							placeholder="Subject"> </span> 
							<span><label>Message <strong>*</strong>
								 </label> <textarea   name="message" id="textAreaId" class="form-control"></textarea> </span> <span><label>&nbsp;</label>
								<input type="submit" class="inputsBtn" value="Send" name="" onclick="return sendMail();" > </span>
					</form>
				</div>

			</div>
		</div>
		</div>
		</div>
	</div>
	
	 <!-- Begin Omniture tag --><!-- SiteCatalyst code version: H.23.4. -->
                <script type="text/javascript">var s_account="mazdaownersdev,mazdaglobal"</script>
				<!-- mp_trans_disable_start -->
				<script language="JavaScript" src="view/vhelper/js/s.code.dev.js"></script>
				<script language="JavaScript">
				s.server="341067-MAZADS002.mazdausa.com";s.pageName="musa:owners_mymazda_profile_contact_info";s.channel="owners_mymazda_profile_contact_info";s.hier1="My Mazda Page, MUSA";s.prop4="X";s.prop6="tools_owners_mymazda_recalls";s.prop8="owners_mymazda_recalls";s.prop9="en";s.prop22="0";s.eVar39=screen.width+'x'+screen.height;s.eVar40="tier1";s.eVar41="no";s.business="mazdausa.com";s.eVar11="A";/************* DO NOT ALTER ANYTHING BELOW THIS LINE !**************/ var s_code=s.t();if(s_code)document.write(s_code)</script>
				<noscript><img src="http://mazdausa.112.2o7.net/b/ss/mazdausacom/1/H.23.4--NS/0" height="1" width="1" border="0" alt="" /></noscript>
				<!-- mp_trans_disable_end -->
                <!-- End Omniture tag -->