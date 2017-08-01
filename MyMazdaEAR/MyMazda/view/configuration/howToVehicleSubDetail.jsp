<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">

<div class="row">
	<div class="col-lg-12 col-md-12 col-sm-12">

		<s:if test="#session.loginUser == null">
			<span class="welcome"><a data-tooltip="Change your Car"
				class="brdnone" href="javaScript:;" onclick="return howTOMazda();">Select
					Car Year/Model/Trim</a></span>
		</s:if>
		
		<div class="icons-bg1 innerContent"
			style="overflow: scroll; overflow-x: hidden; height: 550px;">
			<h2 class="heading3">Popular</h2>
			
			<s:iterator value="howToVehicalSubList">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-3 col-md-3 col-sm-3">
						<div class="icons2">
							<a href="#" data-tooltip="<s:property value="name"/>"> <span
								class="icon1"><img src="view/vhelper/images/warm.png"></span>
								<p class="txt">
									<s:property value="name" />
								</p>
							</a>
						</div>
					</div>
				</div>
			</s:iterator>

			<s:iterator value="lookupItems">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<div class="col-lg-3 col-md-3 col-sm-3">
						<div class="icons2">
							<a href="#" data-tooltip="<s:property value="name"/>"> <span
								class="icon1"><img src="view/vhelper/images/warm.png"></span>
								<p class="txt">
									<s:property value="name" />
								</p>
							</a>
						</div>
					</div>
				</div>
			</s:iterator>

		</div>
	</div>

</div>