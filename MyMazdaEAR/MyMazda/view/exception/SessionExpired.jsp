<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="Travel Front Office, Online Travel Booking, Online Booking Engine
	,Internet Booking Engine ">
	<meta http-equiv="description" content="Travel Front Office : Home Page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- <script src="//assets.adobedtm.com/0b1a9905a3bfe56ba99f4bcd3560048b3d9faf5f/satelliteLib-4332df72469fbed611c92423a310658ec4352e71.js"></script>  -->
	<script src="//assets.adobedtm.com/0b1a9905a3bfe56ba99f4bcd3560048b3d9faf5f/satelliteLib-9dac536f1ca76627327a770a7af6d1ac98184c84-staging.js"></script>
  </head>
  
  <body>
    This is Session Expired Page. <br>
  <script type="text/javascript">_satellite.pageBottom();</script>
  </body>
</html>