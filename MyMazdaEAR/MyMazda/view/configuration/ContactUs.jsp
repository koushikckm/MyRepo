<jsp:include page="../common/Header.jsp" />

      <div class="overlay"></div>
      <div class="container-fluid padding-0">
         <!--Container Start-->
         <div class="ContactUs-page">
          <div class="container">
            <div class="row margin-0">
                <div class="col-xs-12 col-sm-12 col-md-12 padding-0">
                   <div class="search-field1">
                      <input type="search" class="form-control contactsearchkey" placeholder="Locate Nearest Dealer (Enter Zip Code)" id="contactdealersearch" />
                      <i class="fa fa-map-marker" onclick="return contactsearchDealers();" style="cursor:pointer;"></i>
                   </div>
                </div>
            </div>
        </div>
        	<% 	String allowemail = (String)request.getSession().getAttribute("allowemail"); 
        		if(allowemail == null) { allowemail = "yes"; } %>
            <div class="container">
               <div class="row margin-0">
                  <h1>CONTACT MAZDA</h1>
                  <p class="brand-text">Mazda Customer Assistance Representatives are available<br>Monday - Friday 6:00 a.m - 5:00 pm Pacific Time.<br>(1-800) 222-5500</p>
               </div>
               <div class="ContactUs-Div">
                  <form role="form">
                     <div class="row margin-0">
                     	<% if(allowemail.equalsIgnoreCase("no")) {
               	           	%><div class="Email-Diasbled-Message">
					        	Thank you for your interest in Mazda. We are currently experiencing delays with emails and apologize for the inconvenience. 
					        	For immediate assistance, we encourage you to use the call option above or contact your local Mazda dealership. Thank you again for contacting Mazda!
					        </div><%
                        } else { %>
                        <div class="col-xs-12 col-sm-6 padding-0">
                           <h4 class="text-uppercase">Contact Mazda USA by email</h4>
                           <div class="form-group col-xs-12 padding-0">
                           	  <label>VIN<span class="mandatory-field">*</span></label>
                              <input type="mileage" class="form-control" id="conctactVin" placeholder="VIN NUMBER" maxlength="17">																					
                           </div>
                           <div class="form-group col-xs-12 padding-0">
                              <label>FROM<span class="mandatory-field">*</span></label>
                              <input type="mileage" class="form-control" id="fromname" placeholder="YOUR NAME">																					
                           </div>
                           <div class="form-group col-xs-12 padding-0">
                              <label>EMAIL<span class="mandatory-field">*</span></label>
                              <input type="mileage" class="form-control" id="yourMail" placeholder="YOUR EMAIL">																					
                           </div>
                           <div class="form-group col-xs-12 padding-0">
                              <label>TO<span class="mandatory-field">*</span></label>
                              <input type="mileage" class="form-control" id="toMail" value="MAZDACUSTOMEREXPERIENCE@MAZDAUSA.COM" readonly="readonly">																					
                           </div>
                        </div>
                        <div class="col-xs-12 col-sm-6 padding-0">
                           <p class="required-text" style="text-align: right;"><span class="mandatory-field">*</span>Required</p>
                           <div class="clearfix"></div>
                           <div class="form-group col-xs-12 brand-tmargin10">
                              <label>SUBJECT<span class="mandatory-field">*</span></label>
                              <input type="mileage" class="form-control" id="subject" placeholder="SUBJECT">																					
                           </div>
                           <div class="form-group col-xs-12">
                              <label class="commentslabel">MESSAGE<span class="mandatory-field">*</span></label>
                              <textarea class="form-control" rows="5" id="comment"></textarea> 								
                           </div>
                           <div class="col-xs-12">
                              <a type="submit" class="btn btn-default btn-red brand-button-white" onClick="return contactDetails();" id="sendEmailBtn">SEND</a>  
                           </div>                           
                        </div>
                        <% } %>
                     </div>
                  </form>
               </div>
            </div>
         </div>
      </div>
        <script>
        $(document).ready(function() {
       		
     		 $(".contactsearchkey").keyup(function (e) {
     		    if (e.keyCode == 13) {
     		    	contactsearchDealers();
     				return false;
     		    } 
     		});
         });
       function contactsearchDealers() {
    	   add='';
     	   edit='';
     	   registersearch='';
    		var data = {};
    		var dealerSearch = $("#contactdealersearch").val();
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