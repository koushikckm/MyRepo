<%@ taglib prefix="s" uri="/struts-tags"%>

<jsp:include page="../common/Header.jsp" />
<jsp:include page="/view/common/DataLayerJs.jsp"></jsp:include>

<script type="text/javascript">
 	$(function(){
    	$("#result").click(function() {     
        		array = $("input[name=optradio]:checked").val().split(";");
        		$.each(array,function(i){
				});
				dataLayer.dealer.ID = array[5];
    	});
	}); 
	$('#searchDelar').on("input", function() {
		var dInput = this.value;
		if(dInput.length === 5){
		dataLayer.zipCode = dInput;
		}
	});
	$(document).ready(function() {
		dataLayer.page.name = 'find_a_dealer';
		dataLayer.page.URL = window.location.href;
		dataLayer.site.section = 'mymazda';
		dataLayer.site.subsection = 'dealer';
		dataLayer.form.name = 'locate_dealer';
		dataLayer.form.type = 'search';
		dataLayer.user.type = 'o';
	});
</script>

<jsp:include page="LocateDealerJs.jsp"></jsp:include>
	<div class="LocateDealer">
		<div class="container">
			<div class="row margin-0">
              <div class="col-xs-12 col-sm-12 padding-0">
                 <h1 class="text-uppercase hed1">locate your nearest mazda dealers</h1>
                 <div class="search-field1">
                    <input type="search" id="searchDelar" class="form-control locatesearch" placeholder="Locate Nearest Dealer (Enter Zip Code)" data-analytics-link-component-name="form" data-analytics-link-type="button" data-analytics-link-description="search"/>
                    <i class="fa fa-map-marker" onclick="return searchDealers();" style="cursor:pointer;"></i>
                 </div>
                 <h1 class="text-uppercase hed2">locate your nearest mazda dealers</h1>
                 <s:if test="dealerList.size()>0">
                 <p class="brand-text">Here are the <s:property value="dealerList.size()" /> closest dealer(s) based on keyword you entered. <span class="brand-text" id="selectdealer">Select a preferred dealer.</span></p>
              </s:if>
              <s:else>
              <p class="brand-text">No dealer found for the specified keyword.</p>
              </s:else>
              </div>
           	</div>
           	 <div id="usermail" style="display:none"><s:property value="#session.loginDetail.email"/></div>
			<div id="result">
				<s:if test="dealerList.size()>0">
				 <div class="row margin-0 LocateDealer-radio">
					<s:iterator value="dealerList" status="status" var="dealer">
					 
						<div class="radio">
							<s:if test="#session.CheckboxRegistrationTO != null">
								<input type="radio" name="optradio" value="${dealer.name};${dealer.street};${dealer.city};${dealer.state};${dealer.phone};${dealer.id};${dealer.serviceUrl};${dealer.zip}" >
							</s:if>
							 <input type="radio" name="optradio" value="${dealer.name};${dealer.street};${dealer.city};${dealer.state};${dealer.phone};${dealer.id};${dealer.serviceUrl};${dealer.zip}" > 
							<label>  
								<s:text name="name" />
								<p> <s:text name="street" /><br>
								<s:text name="city" />,<s:text name="state" /> <s:text name="zip" /><br>
									Phone: <s:text name="phone" /><br>
								<span class="milesfrom">	(<s:text name="distance" /> MILES FROM ZIP)</span>
									
								</p>
								<s:if test="expServiceFlag !=null">
									<a href="javaScript:;" onclick="return showExpressMess();"
										data-toggle="modal" data-target="#Locatedealer"
										class="text-uppercase mazda-link brand-tmargin10" data-analytics-link-component-name="table" data-analytics-link-type="button" data-analytics-link-description="express_service"><span 
										class="brand-underline"><span class="brand-link"><s:text name="MAZDA EXPRESS SERVICE" /></span></span></a>
								</s:if>
								
								<s:if test="ServiceUrl !=null">
									<s:if test="expServiceFlag !=null">										
									</s:if>
									<a href="<s:property value="ServiceUrl"/>" target="_blank"
										data-toggle="modal" class="text-uppercase mazda-link brand-tmargin10" data-analytics-link-component-name="dealer_results" data-analytics-link-type="button" data-analytics-link-description="schedule_service"><span 
										class="brand-underline link-height"><span class="brand-link">SCHEDULE SERVICE WITH THIS DEALER</span></span></a>
								</s:if>
							</label>
						</div>					
					</s:iterator>
					<div class="ld-buttons">					
						<button id="submit" class="btn btn-default btn-red brand-button-white text-uppercase" 
								onclick="return getsearchDealer();" type="submit" data-analytics-link-component-name="table" data-analytics-link-type="button" data-analytics-link-description="submit">Submit</button> 
						<a id="backbutton" class="btn btn-default btn-gray brand-button text-uppercase" href="javaScript:;" 
								onclick="return back();" data-analytics-link-component-name="table" data-analytics-link-type="button" data-analytics-link-description="back">Back</a>
					</div>
				</div>
				</s:if>
				
			</div>
		</div>
	</div>



