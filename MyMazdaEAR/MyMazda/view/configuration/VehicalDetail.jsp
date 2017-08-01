<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:iterator value="vehicalDetailsList">
		<div style="background-color: #CBCBBF; float: left; padding: 10px 30px; font-size: 18px; width: 600px;">
				<s:hidden name="id"/>
				<s:label>Vin :-<s:property value="vin"/></s:label>
				<s:label>Year :-<s:property value="mdlYear"/></s:label>
				<s:label>Model :-<s:property value="modelName"/></s:label>
				<s:label>Current Mileage :-<s:property value="currentMileage"/></s:label>
				<s:label>Miles Per Day :-<s:property value="milesPerDay"/></s:label>
			
		</div>
</s:iterator>