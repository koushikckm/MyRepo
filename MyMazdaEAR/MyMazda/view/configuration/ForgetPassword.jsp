<%-- 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<script>
function checkPass(){
  var guess = document.myForm.password.value;
  var secret = document.myForm.cpassword.value;

  if (guess == secret){

     return true;
  } else {
  alert("New Password & Conform Password Doesn`t Matched");
   return false;
  }
}
</script>



<div class="userLoginContainer">
	<div class="loginHeader">Reset Password</div>
		<s:if test="#session.userlist == null">
			<div class="loginContent">
				<s:form action="?createNewTab=1" id="signInFormID" >
				 <s:if test="hasActionMessages()">
					<div style="color: blue;"><s:actionmessage /></div>
	  			</s:if>
				<div class="inputBlock">
				<s:if test="checklist.size<1 || resetPasswordlist.size>0">
					<div class="clear-x" >
						<s:textfield  name="email_id" key="Enter User Id" id="email_Id" placeholder="Enter your E-Mail" cssClass="account-input" />
					</div>
						<s:submit type="button" key="Reset" action="checkPassword" cssClass="appBtn" style="margin-left: 208px;"/>
				</s:if>
				<s:if test="checklist.size>=1">
					<div class="clear-x">
						<s:textfield  name="password" key="New Password" id="passwordId" size="50" cssClass="account-input"/>
					</div>
					<div class="clear-x">
						<s:textfield  name="cpassword" key="Conform New Password" id="passwordId" size="50" cssClass="account-input"/>
					</div>
					<div>
					  <s:submit type="button" key="Change Password" action="resetPassword" cssClass="appBtn" onclick="return checkPass();" />
					</div>
				</s:if>
				</div>
	
			</s:form>
		</div>
	<div style="clear: both;height: 1px;">&nbsp;</div>
</div>
 --%>
 <script>
function resetForgetEmail(){
	$("[name='email']").val("");
}
</script>
 
 

 <div class="slider"><div class="forgotpassword">
        <h1>Please Enter your Email  Address, and we will Email your password to you shortly</h1>
        <form class="enquiryForm" method="post" action="forgetPassword">
        
        <fieldset>
	         <input name="email" class="inputs1" type="text" placeholder="Email">
	         <input name="" class="inputsBtn" type="submit" value="Submit">
	         <input name="" class="inputsBtncancel" type="button" value="Cancel" onclick="return resetForgetEmail();">
         </fieldset>
        </form>
        if you still cannot access to your account , <a href="contactMazda">please contact us</a>
        </div>
      <div class="tp-banner-container" style="height:500px;">
        <div class="tp-banner">
          <ul style="display:none;">
            <li data-transition="fade" data-slotamount="7" data-masterspeed="700">
              <img src="view/vhelper/images/fon1.jpg" alt="" data-bgfit="cover" data-bgposition="center center" data-bgrepeat="no-repeat" />
            </li>
            <li data-transition="random" data-slotamount="7" data-masterspeed="700">
              <img src="view/vhelper/images/fon2.jpg" alt="" data-bgfit="cover" data-bgposition="center center" data-bgrepeat="no-repeat" />
            </li>
            <li data-transition="random" data-slotamount="7" data-masterspeed="700">
              <img src="view/vhelper/images/fon3.jpg" alt="" data-bgfit="cover" data-bgposition="center center" data-bgrepeat="no-repeat" />
            </li>
          </ul>
        </div>
      </div>
     </div>