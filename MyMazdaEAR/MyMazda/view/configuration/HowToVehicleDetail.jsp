<%@ taglib uri="/struts-tags" prefix="s"%>

<div id="firstDiv" class="vertical-menu">
	<div class="list-group panel">
		<s:if test="#session.vehicleHowTo != null">

			<s:iterator value="#session.vehicleHowTo" status="firstPage">
			
				<s:if test="subItemList.size>0">				
				<a href="#showSecond<s:property value="%{#firstPage.index}"/>"
					class="list-group-item list-group-item-success strong"
					data-toggle="collapse" data-parent="#firstDiv"
					onclick="return showSecond(<s:property value="%{#firstPage.index}"/>,'<s:property value="name" />');" data-analytics-link-component-name="topic_nav" data-analytics-link-type="expand" data-analytics-link-description="<s:property value="name"/>">

					
						<div><span class="brand-underline brand-link-height"><span class="brand-link"><s:property value="name" /></span></span></div>
					
				</a>
				</s:if>
				<s:else>							
				<a href="#showSecond<s:property value="%{#firstPage.index}"/>"
					class="list-group-item list-group-item-success strong"
					data-toggle="collapse" data-parent="#firstDiv"					
					onclick="return showOne(<s:property value="%{#firstPage.index}"/>,'<s:property value="name" />','<s:property value="contantType"/>','<s:property value="contentId"/>');" data-analytics-link-component-name="topic_nav" data-analytics-link-type="expand" data-analytics-link-description="<s:property value="name"/>">
					
						<div><span class="brand-underline brand-link-height"><span class="brand-link"><s:property value="name" /></span></span></div>
					

				</a>				
				</s:else>

				<div class="collapse second-level"
					id="showSecond<s:property value="%{#firstPage.index}"/>">

					<s:if test="subItemList != null">
						
						<s:iterator value="subItemList" status="secondPage">

							<a href="#showThird<s:property value="%{#secondPage.index}"/>"
								data-toggle="collapse" class="list-group-item list-group-item-success strong" 
								data-parent="#showThird<s:property value="%{#secondPage.index}"/>"
								onclick="return showThird(<s:property value="%{#secondPage.index}"/>,'<s:property value="name" />','<s:property value="contantType"/>','<s:property value="contentId"/>');" data-analytics-link-component-name="subtopic_nav" data-analytics-link-type="button" data-analytics-link-description="<s:property value="name"/>">
;
								
									<div><span class="brand-underline brand-link-height"><span class="brand-link"><s:property value="name" /></span></span></div>
								

							</a>
							
							<s:if test="subItemList != null">
							
								<div class="collapse list-group-submenu"
									id="#showThird<s:property value="%{#secondPage.index}"/>">
									
									<s:iterator value="subItemList" status="thirdPage">

										<a href="#" class="list-group-item list-group-item-success strong"
											onclick="return showThird(<s:property value="%{#thirdPage.index}"/>,'<s:property value="name" />','<s:property value="contantType"/>','<s:property value="contentId"/>');" data-analytics-link-component-name="subtopic_nav" data-analytics-link-type="button" data-analytics-link-description="<s:property value="name"/>">

											
												<div><span class="brand-underline brand-link-height"><span class="brand-link"><s:property value="name" /></span></span></div>
											

										</a>

									</s:iterator>
								</div>
							</s:if>

						</s:iterator>
					</s:if>
				</div>

			</s:iterator>

		</s:if>

	</div>
</div>

