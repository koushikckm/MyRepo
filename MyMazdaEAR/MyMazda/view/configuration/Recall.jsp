<%@ taglib prefix="s" uri="/struts-tags"%>

<jsp:include page="../common/Header.jsp" />
<jsp:include page="/view/common/DataLayerJs.jsp"></jsp:include>

<script type="text/javascript">
	$(document).ready(function() {
		dataLayer.page.name = 'recall';
		dataLayer.page.URL = window.location.href;
		dataLayer.site.section  = 'mymazda';
		dataLayer.site.subsection = 'service';
		dataLayer.user.type = 'o';
	});
</script>

   <div class="overlay"></div>  
   <div class="Recallinfo-page">
      <div class="container">
         <div class="row margin-0">
            <div class="col-sm-12 padding-0">
               <div class="search-field1">
                  <input type="search" class="form-control recallsearchkey" placeholder="Locate Nearest Dealers (Enter Zip Code or Name)" id="recallsearchKey" />
                  <i class="fa fa-map-marker" onclick="return recallsearchDealers();" style="cursor:pointer;"></i>
               </div>               
            </div>
         </div>
         </div>
          <div class="Menu2" id="menu1">
			<div class="container">
			<nav class="navbar navbar-default sub-menu2">
			<div class="container-fluid padding-0">
			<div class="navbar-header">
			<button type="button" class="navbar-toggle accordion-toggle collapsed vehicle-menu" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
			<i class="fa fa-plus-circle"></i>
			</button>
			<a href=""  onclick="return homeClick();" class="summary" id="servicecarName"><s:text name='#session.selectedVehical.carlineDesc'/></a>
			</div>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
			<li><a href="javaScript:;" onclick="return serviceHistory('<s:text name='#session.selectedVehical.vin'/>');" data-analytics-link-component-name="subnav" data-analytics-link-type="button" data-analytics-link-description="service_history"><span class="Menu2-no-divider"></span>SERVICE HISTORY</a></li>
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
		 <h1 class="text-uppercase">Recall Information</h1>
         <div class="row margin-0 Car-Box">
            <div class="col-xs-12 col-sm-12 col-md-12">
               <%-- <div class="Car-Img text-center">
                 <!--  <h2>My First Car</h2> -->
                  <!-- <img class="img-responsive" src="view/vhelper/images/car1.png" alt="Mazda"> -->
                  <s:iterator value="#session.vehicalsList" status="count">
                     <li id="selector<s:property value="vin"/>"
                        onclick="showUpdateSelectedVehicle('<s:property value="vin"/>');">
                        <s:a href="javaScript:;" value="id" cssClass="lightbox">
                           <s:property value="carlineDesc" />
                           <s:hidden name="id" />
                        </s:a>
                        <s:hidden name="id" cssClass="vehical" />
                     </li>
                     <!-- <img class="img-responsive" src="view/vhelper/images/car1.png" alt="Mazda"> -->
                  </s:iterator>
               </div>  --%>
            </div>
            <s:if test="hasActionMessages()">
               <!-- <div style="margin:3px 3px 0 0; background: #F4F4F4;"> -->
               <div class="servicetext">
               <div class="Car-Img text-center">
                <h2 class="bold-txt" >   <s:text name="#session.selectedVehical.carlineDesc" /></h2>
              <!--  <img class="img-responsive" src="view/vhelper/images/car1.png" alt="Mazda" style="height:160px"> -->
               </div>	
               
               <h3 class="bold-txt" ><s:property value="#session.selectedVehical.mdlYear"/> <s:text name="#session.selectedVehical.carlineDesc"/></h3>
                  <div style="padding-top : 15px;">
                  <s:actionmessage />
                  </div>
               </div>
            </s:if>
            <s:else>
               <div class="col-xs-12 col-sm-12 col-md-12">
                  <div class="Car-Img text-center">
                     <s:if test="#session.imagePath!=null ">
                        <h2 class="bold-txt"> <s:text name="#session.selectedVehical.carlineDesc" />
                    </h2>
                    <%--  <img class="img-responsive" style=" margin: auto; width: 33%;" src="<s:property value="#session.imagePath"/>" alt="Mazda"> --%>													  													  
                   </s:if>
                   <s:else>
                    <h2 class="bold-txt"> <s:text name="#session.selectedVehical.carlineDesc" /></h2>
                   <!--  <img class="img-responsive" src="view/vhelper/images/mx5_car_image.png" alt="Mazda" style=" margin: auto; width: 33%;"> -->
                   </s:else>
                  </div>
               </div>
               <div class="col-xs-12 col-sm-12 col-md-12">
                     <div class="Car-Data">
                    
                     
                     <%-- <h3 class="bold-txt"><s:text name="#session.selectedVehical.carlineDesc"/></h3> --%>
                     	<s:if test="alertList!=null"><s:if test="alertList.size()==0">
                     		<div style="text-align: center;" class="no-recall-message">
                     			NO RECALLS
                     		</div>
                     	</s:if></s:if>
                        <s:iterator value="alertList">
                           <div class="CarData-Row">
                              <table>
                              
                                 <tr>
                                    <td class="bold-txt">NUMBER</td>
                                    <td>: <s:text name="recallSSPNo"/></td>
                                 </tr>
                                 <tr>
                                    <td class="bold-txt">START DATE</td>
                                    <td>: <s:text name="startDate"/></td>
                                 </tr>
                                 <tr>
                                    <td class="bold-txt">DESCRIPTION</td>
                                    <td>: <s:text name="recallSSPDesc"/></td>
                                 </tr>
                              </table>
                           </div>
                        </s:iterator>
                     </div>
               </div>
                 
		</s:else>
         </div>
         <div class="row margin-0">
            <div class="col-md-12 padding-0">
               <p class="brand-text">For any questions regarding a Mazda Recall or Special Service Program campaign, please contact the Mazda Customer Experience Center.<br>
                  Monday-Friday, 5:00 a.m.-6:00 p.m., Pacific Time <br>
                  Saturday, 7:00 a.m.-3:00 p.m. Pacific Time <br>
                  <a href="http://www.mazdausa.com/MusaWeb/contactMazda.action" target="_blank" class="chat-us" data-analytics-link-component-name="recall" data-analytics-link-type="in-line" data-analytics-link-description="chat_us">Chat Us</a> or Call (800) 222-5500
               </p>
            </div>
         </div>
      </div>
      </div>
     

<!--Container End-->
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
		
	 $(".recallsearchkey").keyup(function (e) {
	    if (e.keyCode == 13) {
	    	recallsearchDealers();
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
       function recallsearchDealers() {
    	   add='';
    	   edit='';
    	   registersearch='';
    		var data = {};
    		var dealerSearch = $("#recallsearchKey").val();
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

