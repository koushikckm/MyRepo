<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div  >
		<s:iterator value="seatDetailList">
			<div style="margin-top: 20px;">
					<label>Title: </label><a><s:property value="titleName" /></a><br>
					<label>Content Type: </label><a><s:property value="contantType" /></a>
					
			</div>	
		</s:iterator>
	</div>	