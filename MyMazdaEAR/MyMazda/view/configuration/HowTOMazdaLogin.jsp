<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<script type="text/javascript">
		$(document).ready(function(){
			$("#modelDiv-0").show();
			$("#selectedModel0").attr("name", "mdlCode");
			$("#trimDiv-00 select").attr("name", "trim");
			$("#trimDiv-00").show();
			  selectedVehicleId = '${selectedVehical.id}';
			 $('#selector'+selectedVehicleId).addClass("active");
		});
		  $(".getQuotesFormSHOW").click(function(e){
   			e.preventDefault();
    		e.stopPropagation();
    		$(".getQuotesForm").show();
  		});
        
		
		var yearIndex=0;
		/* function showModel(){
			$("#selectedModel"+yearIndex).attr("name", "");
			$("#trimDiv-"+yearIndex+modelIndex+" select").attr("name", "");
			yearIndex = $("#selectedYear")[0].selectedIndex;
			$("#selectedModel"+yearIndex).attr("name", "mdlCode");
			$("#YearModelTripDropdown .modelCl").hide();
			$("#modelDiv-"+yearIndex).show();
			$("#trimDiv-"+yearIndex+0).show();
			$("#trimDiv-"+yearIndex+0+" select").attr("name","trim");
		} */
		
		var modelIndex=0;
		function showTrim(){
			$("#trimDiv-"+yearIndex+modelIndex+" select").attr("name", "");
			modelIndex = $("#selectedModel"+yearIndex)[0].selectedIndex;
			$("#trimDiv-"+yearIndex+modelIndex+" select").attr("name","trim");
			$("#YearModelTripDropdown .trimCl").hide();
			$("#trimDiv-"+yearIndex+modelIndex).show();
		}
		var year;
		var modelName;
		var trimName;
		var result;
	
	function howToVehicleOperation(val){}
	function showYearModelTripDropdown(){
		$("#YearModelTripDropdown").show();
	}
	function showFirst(){
		$("#firstLevelDiv").show();
		$("#firstlev").show();
		$("#secondDiv").hide();
		$("#secondlev").hide();
		$("#thirdDiv").hide();
		$("#thirdlev").hide();
	}
	
	
	
	
	function showSecond(index, category){
		if(category == "Popular"){
		var url = "callLookUpContentPopular.action";
			$.ajax({
				type : "POST",
				url : url,
				data : {},
				beforeSend : function() {
					showAjaxLoadMsg("Loading...");
				},success : function(result) {
					$("#pageContainDiv").html(result);
					$("#pageContainDiv").show();
				},complete : function() {
					hideAjaxLoadMsg();
					$("#YearModelTripDropdown").hide();
					 window.scrollTo(0, 20);
				}
			});
		return false;
		}
		else{
		year = $("[name='year']").val();
		modelName = $("#selectedModel"+yearIndex).val();
		trimName = $("#trimDiv-"+yearIndex+modelIndex+" select").val();
		var url = "howToVehicalSecondLevel.action";
			$.ajax({
				type : "POST",
				url : url,
				data : {"year":year,"mdlCode":modelName,"trim":trimName,"index":index},
				beforeSend : function() {
					showAjaxLoadMsg("Loading...");
				},success : function(result) {
						$("#HowToVehicleDetailId").html(result);
						$("#HowToVehicleDetailId").show();
						$("#firstDivSelector").hide();
						$("#firstDiv").hide();
						$("#showSecond").show();
						$("#levelOne").hide();
						
				},complete : function() {
					hideAjaxLoadMsg();
					$("#YearModelTripDropdown").hide();
					 window.scrollTo(0, 650);
				}
			});
			return false;
			}
			
	
			}
	
	function showThird(index, title, contentType, contentId){
		if(contentType != null && contentType !=""  ){
			var url = "callLookUpContent.action";
			$.ajax({
				type : "POST",
				url : url,
				data : {"contantType":contentType,"contentId":contentId},
				beforeSend : function() {
					
				},success : function(result) {
					$("#pageContainDiv").html(result);
					$("#pageContainDiv").show();
					alert("2");
					window.scrollTo(0, 800);
				},complete : function() {
					
					$("#YearModelTripDropdown").hide();					 
				}
			});
			return null;
		}
	else{
		year = $("[name='year']").val();
		modelName = $("#selectedModel"+yearIndex).val();
		trimName = $("#trimDiv-"+yearIndex+modelIndex+" select").val();
		var url = "howToVehicalThirdLevel.action";
			$.ajax({
				type : "POST",
				url : url,
				data : {"year":year,"mdlCode":modelName,"trim":trimName,"index":index,"title":title},
				beforeSend : function() {
					showAjaxLoadMsg("Loading...");
				},success : function(result) {
						$("#HowToVehicleDetailId").html(result);
						$("#HowToVehicleDetailId").show();
						$("#firstDivSelector").hide();
						$("#firstDiv").hide();
						$("#secondDiv").hide();
						
						
				},complete : function() {
					hideAjaxLoadMsg();
					$("#YearModelTripDropdown").hide();
					window.scrollTo(0, 0);
				}
			});
			return false;
	}
	
		
	}
	function howToSearch(){
		var url = "openHowToSearch.action";
			$.ajax({
				type : "POST",
				url : url,
				data : {},
				beforeSend : function() {
					showAjaxLoadMsg("Loading...");
				},success : function(result) {
					$("#pageContainDiv").html(result);
					$("#pageContainDiv").show();
					$("#searchBoxDiv").show();
					$("#searchContent").hide();
						
				},complete : function() {
					hideAjaxLoadMsg();
					$("#YearModelTripDropdown").hide();
				 	window.scrollTo(0, 0);	
				}
			});
		return false;
		}
	
	
		
		
	function audioNaviPhone(){
		$("#HowToVehicleDetailId").hide();
		$("#firstDivSelector").hide();
		$("#popularDetails").hide();
		$("#audioNaviPhone").show();
	}	
	
	var oldId;
		function UpdateSelCarOfHowToVehicle(id){
		 var url = "howToVehicalOption.action";
					$.ajax({
						type : "POST",
						url : url,
						data : {"id":id},
						beforeSend : function() {
							showAjaxLoadMsg("Loading...");
						},success : function(result) {
							$("#pageContainDiv").html(result);
							$("#pageContainDiv").show();
							$("#searchBoxDiv").show();
							$("#searchContent").hide();
						},complete : function() {
							hideAjaxLoadMsg();
							$("#YearModelTripDropdown").hide();
				 			window.scrollTo(0, 0);	
						}
					}); 	
			}
			
		/* function seatDetail(){
		 var url = "seatDetail";
					$.ajax({
						type : "POST",
						url : url,
						data : {"id":8},
						beforeSend : function() {
						
						},success : function(result) {
						$("#seatDetaild").html(result);
						},complete : function() {
							oldId = id;
						}
					}); 	
			} */
		function seatRelatedData(){
		 var url = "seatRelatedData.action";
					$.ajax({
						type : "POST",
						url : url,
						beforeSend : function() {
						
						},success : function(result) {
						$("#SeatDetailList").html(result);
						},complete : function() {
							oldId = id;
						}
					}); 	
			}
		function showMyFirstDiv(){
				$("#firstDivSelector").show();
				$("#firstDiv").show();
				$("#secondDiv").hide();
				$("#levelOne").show();
				$("#levelTwo").hide();
				return false;
		}
		
		function showMySecondDiv(){
			$("#firstDivSelector").hide();
			$("#firstDiv").hide();
			$("#secondDiv").show();
			return false;
		}
</script>

<div class="top_line" id="myHeaderSelector">
	<div class="container">
		<div class="row">
		<div style="float:left;margin-left:45px;">
						<ul class="social-links">
							<li><a href="myMazda.action" class=""><img title="MyMazda" src="view/vhelper/images/home_m.png" class="logoutb">
							</a>
							</li>
						</ul>
					</div>
			<div class="col-lg-10 col-md-10 col-ms-12 pull-right ">
				<s:if test="#session.loginUser != null">
					<div class="col-lg-10 col-md-10 col-ms-12 pull-right ">
						<ul class="social-links">
							<li><h2 class="heading" style="margin: 8px 15px 0px 0px;">Vehicle How To</h2>
							</li>
							<li><a href="mymazdaHome.action" class=""><img title="Logout"  src="view/vhelper/images/logout1.png" class="logoutb"></a></li>
						</ul>
					</div>
				</s:if>
				<s:else>
					<div class="col-lg-10 col-md-10 col-ms-12 pull-right ">
						<ul class="social-links">
							<li><h2 class="heading" style="margin: 8px 15px 0px 0px;">Vehicle How To</h2>
							</li>
							<li><a href="javaScript:;" class="browseCategory getQuotesFormSHOW">Login</a>
							</li>
							<li><a href="javaScript:;" onclick="return registerNew();" class="browseCategory">Sign Up</a>
							</li>
						</ul>
					</div>
				</s:else>
			</div>
		</div>
	</div>
</div>
<div class="section1 padd50" id="bodySelector">
	<div class="container">
		<div class="row" id="firstDivSelector">
			<div class="col-lg-8 col-md-8 col-sm-12">
				<s:if test="#session.loginUser != null">
					<div class="tabs">
						<ul class="nav nav-tabs">
							<s:iterator value="#session.vehicalsList" status="count">
								<li id="selector<s:property value="id"/>"><a href="javaScript:;" onclick="return UpdateSelCarOfHowToVehicle(<s:property value="%{#count.index}"/>);">
									<s:property value="carlineDesc" /> </a> <s:hidden name="id" cssClass="vehical" /></li>
							</s:iterator>
							<!-- <li>
								<input type="button" id="addCarSubmitBtn" onclick="window.history.back();return false;" class="Submit_btn" value="Back" name="" style="position: absolute; margin: 59px 0px 0px 895px;">
							</li> -->
						</ul>
					</div>
				</s:if>
				<s:else>
					<span class="welcome">Select Car YEAR/MODEL/TRIM</span>
						<div class="innerContent">
							<form class="myaccount">
								<div id="YearModelTripDropdown">
									<div class="col-lg-3 col-md-3 col-sm-12">
									<span><label>SELECT A YEAR</label>
										<s:select cssClass="selectBoxSmall" id="selectedYear"
											list="#session.yearModelTrim" listKey="year" listValue="year"
											onchange="showModel();" name="year" cssStyle="width: 166px" />
									</span>
								</div>
								<s:iterator value="#session.yearModelTrim" status="yearStatus" begin="0">
									<div class="col-lg-3 col-md-3 col-sm-12"showPopularContent
										style=" margin: 0 0 0 154px;">
										<div id="modelDiv-<s:property value="%{#yearStatus.index}"/>"
											style="display: none;margin: 0 0 0 -139px;" class="modelCl">
											<span><label>SELECT A MODEL </label>
											<s:select cssClass="selectBoxSmall" id="selectedModel%{#yearStatus.index}" list="models"
													listKey="modelCode" listValue="modelName" onchange="showTrim();" cssStyle="width: 166px" />
											</span>
											<s:iterator value="models" status="modelStatus" begin="0">
												<div class="col-lg-3 col-md-3 col-sm-12" style="margin: -76px 0 0 188px; display: block;">
													<s:if test="trims.size()>0">
														<div id="trimDiv-<s:property value="%{#yearStatus.index}"/><s:property value="%{#modelStatus.index}"/>" style="display: none;margin-top: 5px;" class="trimCl">
															<span><label>SELECT A TRIM </label>
																	<s:select cssClass="selectBoxSmall" list="trims" style="width: 128px" />
															</span>
														</div>
													</s:if>
												</div>
											</s:iterator>
										</div>
									</div>
								</s:iterator>
						<!-- <div class="clear-x" style="padding-left: 206px;">	
			</div>	 -->
						<div class="col-lg-3 col-md-3 col-sm-12" style="position: absolute; margin: 4px 0 0 572px;width: 78px;">
							<span><label>&nbsp;</label>
							<s:submit type="button" key="Send" cssClass="Submit_btn"
									onclick="return howToVehicalOption();" />
							</span>
						</div>
					</div>
				</form>
			</div>
		</s:else>
		</div>
	</div>
	<div id="HowToVehicleDetailId">
		<s:include value="HowToVehicleDetail.jsp"></s:include>
	</div>
	<%-- </s:if>
	<s:else>
		<div id="HowToVehicleDetailId" style="display: none;">
			<s:include value="HowToVehicleDetail.jsp"></s:include>
		</div>
	</s:else>
	<div id="howToVehicleSubDetailId" style="display: none;">
		<s:include value="howToVehicleSubDetail.jsp"></s:include>
	</div> --%>
</div>
    </div>
