<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div style="color:white;" >
		<s:iterator value="lookupItems">
			<div>
					<s:iterator value="subItemList">
					<a href="javaScript:;" onclick="seatRelatedData();"><s:property value="name" /></a><br>
					</s:iterator>
			</div>	
		</s:iterator>
	</div>	