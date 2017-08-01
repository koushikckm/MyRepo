<script type="text/javascript">
jQuery(document).ready(function(){
				if('${scheduleListSize}'>0)	{
					$("#scheduleDrivingTypeId").show();
					$("#checkMaintenanceScheduleDetailId").show();
				}else{
					$("#scheduleDrivingTypeId").hide();
					$("#checkMaintenanceScheduleDetailId").hide();
				}
		});
</script>
<%@page import="com.mazda.configuration.common.transferobject.RegistrationTO"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="col-md-12">
		<s:if test="hasActionMessages()">
		   <div id="msgBox" class="vinDivClass" style="background: #F4F4F4; min-height:100px;">
		      <s:actionmessage/>
		      <input type="submit" onclick="return scheduleContaint();" class="Submit_btn" value="OK">
		   </div>
		</s:if>
		<s:else>
			<div class="tabs00">
				<div class="tab-pane active" id="act">
					<div class="tabbedwidget">
						<div id="accordion" class="panel-group">
						<s:if test="scheduleTOs.size()>0">
							<s:iterator value="scheduleTOs">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a href="javaScript:;"
												id="showHeading-<s:property value='interval'/>"
												data-parent="#accordion"
												onclick="return shownSearchMaintenance(<s:property value="interval"/>);"
												class="collapsed" data-toggle="collapse"><s:property
													value="interval" />
											</a>
										</h4>
									</div>
								</div>
								<div id="showDiv-<s:property value='interval'/>"
									style="display: none;" class="hideTabContent">
									<div class="panel-body">
										<div class="paymentRow largeWidth">
											<s:if test="replaceItemList.size()>0">
											<ul class="listing margintop1">
											<li><a class="marginm8p">
												<span>Replacement </span>
													<s:iterator value="replaceItemList" status="status">
														<s:if test="itemName != null ">
															<span class="marginm8p"><s:property
																		value="itemName" /><s:if test="#status.last == false">,</s:if>
															</span>
														</s:if>
													</s:iterator>
													</a></li></ul>
											</s:if>
											
											<s:if test="lubrItemList.size()>0">
											<ul class="listing margintop1">
											<li><a>
												<span>Lubricant </span>
													<s:iterator value="lubrItemList" status="status">
														<span class="marginm8p"><s:property
																	value="itemName" /><s:if test="#status.last == false">,</s:if>
														</span>
													</s:iterator>
													</a></li></ul>
											</s:if>
											<s:if test="inspecItemList.size()>0">
											<ul class="listing margintop1">
											<li><a>
												<span >Inspect </span>
													<s:iterator value="inspecItemList" status="status">
														<span class="marginm8p"><s:property
																	value="itemName" /><s:if test="#status.last == false">,</s:if>
														</span>
													</s:iterator>
													</a></li></ul>
											</s:if>
											<s:if test="cleanItemList.size()>0">
											<ul class="listing margintop1">
											<li><a>
												<span >Clean </span>
													<s:iterator value="cleanItemList" status="status">
														<span class="marginm8p"> <s:property
																	value="itemName" /><s:if test="#status.last == false">,</s:if>
														</span>
														
													</s:iterator>
													</a></li></ul>
											</s:if>
											<s:if test="tightenItemList.size()>0">
											<ul class="listing margintop1">
											<li><a >
												<span>Tighten </span>
													<s:iterator value="tightenItemList" status="status">
														<span class="marginm8p"><s:property
																	value="itemName" /><s:if test="#status.last == false">,</s:if>
														</span>
														
													</s:iterator>
													</a></li></ul>
											</s:if>
											<s:if test="itemFootNoteList.size()>0">
											<ul class="listing margintop1">
											<li><a>
													<s:iterator value="itemFootNoteList" status="status">
															<span class="marginm8p"><s:property
																		value="itemDescription" /> 
																		<s:property value="nonIntervalDescription" />
																	 </span>
													</s:iterator>
													</a></li></ul>
											</s:if>
											<ul class="listing margintop1">
											<li><a>
												<span>Please refer to owner manual for details.</span>
											</a></li></ul>
										</div>
									</div>
								</div>
							</s:iterator>
						</s:if>
					</div>
					</div>
				</div>
			</div>
</s:else>
		</div>
	
<s:if test="maintenanceNotes.size()>0">
	<div id="disclaimerDivId" class="vinDivClass signpop" style=" display: none; height: 435px; overflow: scroll;">
		<s:iterator value="maintenanceNotes">
			<s:property/><br><br>
		</s:iterator>
		<div class="s-btn">
			<input type="submit" onclick="return scheduleContaint();" class="Submit_btn" value="OK">
		</div>
	</div>
</s:if>

<s:else>
	<div id="disclaimerDivId" class="vinDivClass signpop" style="height: 435px; overflow: scroll;">
		<p class="brand-text">Please help us to accurately calculate your maintenance
			schedule based on your driving habits and environmental
			conditions:<br><br>For Schedule<br><br> 1 driving conditions, a 7,500-mile
			service interval will apply. For Schedule<br><br> 2 conditions, a 5,000-mile
			interval will apply. If any of the following conditions apply, select
			Schedule 2 Duty. <br><br>Repeated short-distance driving<br><br>Driving in
			dusty conditions <br><br>Driving with an extended use of brakes
			<br><br>Driving in areas where salt or other corrosive materials are
			being used <br><br>Driving on rough or muddy roads <br><br>Extended periods
			of idling or low-speed operation <br><br>Driving for a prolonged period
			in cold temperatures or extremely humid climates</p>
			<div style="text-align:center; width:100%;">
		<input type="submit" onclick="return scheduleContaint();" class="Submit_btn" value="OK">
		</div>
	</div>
</s:else>