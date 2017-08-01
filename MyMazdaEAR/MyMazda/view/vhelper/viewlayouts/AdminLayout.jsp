<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head profile="http://gmpg.org/xfn/11">


<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><tiles:getAsString name="title" /></title>

<link href='<s:text name="style-css.path"><s:param >${contextPath}</s:param></s:text>' rel='stylesheet' type='text/css' />

<script type="text/javascript"	src="<s:text name="jquery-1.9.1.js.path" />"></script> 
<script type="text/javascript" src="<s:text name="jquery-ui.js.path" />"></script>
<script type="text/javascript" src="<s:text name="functions.js.path" />"></script>
<script type="text/javascript" src="view/vhelper/js/common/commom.js"></script>

<!--[if IE 6]>
 <link
	href='<s:text name="style-css2.path"><s:param >${contextPath}</s:param></s:text>'
	rel='stylesheet' type='text/css' />
 <![endif]-->

<!--[if IE 7]>
<link
	href='<s:text name="style-css2.path"><s:param >${contextPath}</s:param></s:text>'
	rel='stylesheet' type='text/css' />
<![endif]-->

<!--[if IE 8]>
 <link
	href='<s:text name="style-css2.path"><s:param >${contextPath}</s:param></s:text>'
	rel='stylesheet' type='text/css' />

<![endif]-->

<!--[if IE 9]>
<link
	href='<s:text name="style-css2.path"><s:param >${contextPath}</s:param></s:text>'
	rel='stylesheet' type='text/css' />
<![endif]-->

<!--[if IE 10]>
<link
	href='<s:text name="style-css2.path"><s:param >${contextPath}</s:param></s:text>'
	rel='stylesheet' type='text/css' />
<![endif]-->
<!-- <script src="//assets.adobedtm.com/0b1a9905a3bfe56ba99f4bcd3560048b3d9faf5f/satelliteLib-4332df72469fbed611c92423a310658ec4352e71.js"></script>  -->
<script src="//assets.adobedtm.com/0b1a9905a3bfe56ba99f4bcd3560048b3d9faf5f/satelliteLib-9dac536f1ca76627327a770a7af6d1ac98184c84-staging.js"></script>
</head>
<body>
	<div id="pageShader">&nbsp;</div>
	<div id="popUpContainer">
		<div id="popUpHeader">
			<div id="headerTitle">&nbsp;</div>
			<div id="crossModalBtn">&nbsp;</div>
		</div>
		<div id="ajaxResult">&nbsp;</div>
	</div>
	<div class="mainheader">
		<div class="header">
			<!-- Starts :: Control Panel Header -->
			<tiles:insertAttribute name="header" />
			<!-- Ends :: Control Panel Header -->
		</div>

	</div>
	<div id="wrapper" style="clear: both;">
		<div style="clear: both;position: relative;">
			<!-- Starts :: Control Panel main Body Contents -->
			<tiles:insertAttribute name="body" />
			<!-- Ends :: Control Panel main Body Contents -->
		</div>
		<div style="clear: both;">&nbsp;</div>
	</div>	
	<script type="text/javascript">_satellite.pageBottom();</script>
	</body>
</html>
	