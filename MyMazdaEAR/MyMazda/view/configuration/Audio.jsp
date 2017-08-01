<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">

<script type="text/javascript"> 
function toggle2(showHideDiv, switchTextDiv) {
	var ele = document.getElementById(showHideDiv);
	var text = document.getElementById(switchTextDiv);
	if(ele.style.display == "block") {
    		ele.style.display = "none";
		text.innerHTML = ">";
  	}
	else {
		ele.style.display = "block";
		text.innerHTML = "<";
	}
} 
</script>
<style type="text/css">
	   #main{
	    width: 965px;
		border:1px solid #A80000;
		height: 513px;
		margin-top: 112px;
	    }
	    #headerDiv, #contentDiv {
		float: left;
		width: 962px;
		border: 1px solid #686868;
		}
		#titleText {
		float: left;
		font-size: 14px;
		font-weight: bold;
		margin: 5px;
		}
		#myHeader {
		font-size: 1.1em;
		font-weight: bold;
		margin: 5px;
		}
		#headerDiv {
		background-color: #383838;
		color: #FFFFFF;
		}
		#contentDiv {
		background-color: #787878;
		}
		#myContent {
		margin: 5px 10px;
		}
		#headerDiv a {
		float: right;
		margin: 10px 10px 5px 5px;
		}
		#headerDiv a:hover {
		color: #FFFFFF;
		} 
	   
	    
</style>
<h1 style="float: right; color: white; margin-top: -52px; font-style: italic;">VEHICLE HOW TO</h1>
<div id="main">
	<h2 style="color: #E0E0E0; margin-left: 6px;">AUDIO/NAVI/PHONE</h2><br/>
	<div id="headerDiv">
	     <div id="titleText">AUDIO CONTROLS </div><a id="myHeader" href="javascript:toggle2('myContent','myHeader');" ><</a>
	</div>
	<div id="contentDiv">
	     <div id="myContent" style="display: block;">
	     <h5><strong>----------------------</strong></h5>
	     <h5>-------------------------------------<br/>
	     ------------------------------------------------------</h5>
	     <h5>-------------------------------<br/>
	     ----------------------------------------</h5>
	     </div>
	</div>
	<div id="headerDiv">
	     <div id="titleText">AUX/USB</div><a href="#"><b>></b></a>
	</div>
	
	<div id="headerDiv">
	     <div id="titleText">BLUETOOTH</div><a href="#"><b>></b></a>
	</div>
	<div id="headerDiv">
	     <div id="titleText">COMMANDER SWITCH</div><a href="#"><b>></b></a>
	</div>
	<div id="headerDiv">
	     <div id="titleText">INFOTAINMENT</div><a href="#"><b>></b></a>
	</div>
	<div id="headerDiv">
	     <div id="titleText">NAVIGATION</div><a href="#"><b>></b></a>
	</div>
	<div id="headerDiv">
	     <div id="titleText">VOICE COMMAND</div><a href="#"><b>></b></a>
	</div>
		
</div>