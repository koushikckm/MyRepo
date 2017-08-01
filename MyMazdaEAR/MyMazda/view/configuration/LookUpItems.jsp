
<%@ taglib uri="/struts-tags" prefix="s"%>


<div class="col-xs-12 col-sm-12 padding-0">

	<div class="row margin-0">
		<s:if
			test="#session.howtoFilterList == null || #session.howtoFilterList.size==0 || #session.howtoFilterList.streemURL==null">
			<div class="video-div col-xs-12 col-sm-6"><div class="No-Videostext">Sorry, no videos found for this vehicle.</div>
			</div>
		</s:if>
		
		<s:if test="#session.howtoFilterList != null">
			<s:iterator value="#session.howtoFilterList" status="liCount">

				<s:if test="streemURL != null">
					<div class="col-xs-12 col-sm-6">
					  <div class="video-div-box">
						<a href="#" onclick="return myFunctionVideo('<s:property value="streemURL"/>','<s:property value="title"/>')" data-analytics-link-component-name="table" data-analytics-link-type="button" data-analytics-link-description="<s:property value="title"/>"><img
							class="img-responsive" src="view/vhelper/images/findmyvin_image.png"
							alt=""></a>
						<h3>
							<s:property value="title" />
						</h3>
					</div>
					</div>
					
				</s:if>
				
			</s:iterator>
		</s:if>
		
	</div>

</div>



