<script type="text/javascript">

	$('.required-input').on('blur', function() {
		if($(this).val().trim() == '') {
			$(this).addClass('required-alert');
			$(this).attr("title", "This is required");
		} else {
			$(this).removeClass('required-alert');
			$(this).attr("title", "");
		}
	});
	
	$(function(){
		 $("#addVehicle").show();
		 $("#myprofile").show();
		// $("#phoneprofile").show();
		 $("#signin").hide();
		 $('#vehicleList').show();
		 var userdetails="<%=session.getAttribute("data")%>";
		 var arry=userdetails.split(',');
		  $('#userloginmail').text(arry[0]);
		 $('#userloginpass').text(arry[1]);
		 
		 $('.vehicle-menu-home').click(function(){			
			//$(this).next('ul').slideToggle('500');
			$(this).find('i').toggleClass('fa-plus-circle fa-minus-circle');
			if ( $(window).width() < 768 ) {
				var i_class = $(this).find('i').attr("class");
				if(i_class == 'fa fa-minus-circle') {
					$(".submenu-class").addClass('submenu-expand');
				} else if(i_class == 'fa fa-plus-circle') {
					$(".submenu-class").removeClass('submenu-expand');
				}
			}
		});
});
$(document).on("keypress", ".searchkey3", function() {
	if (event.keyCode == 13) {
		mazdasearchDealers();
		return false;
	}
});

 
function mazdasearchDealers() {
	add='';
	edit='';
	registersearch='';
	var data = {};
	var dealerSearch = $("#postlogindealersearch").val();
	if (dealerSearch.length === 0) {
		$("#error-sucess").modal("show");
		$("#errorMsg").html("This field cannot be blank. Please enter a valid zip code or name.");
	} else {
		if ($.isNumeric(dealerSearch)) {
			data["zip"] = dealerSearch;
		} else {
			data["dealerSearch"] = dealerSearch;
		}
		$.ajax({
			url : "locateDelear.action",
			data : data,
			type : "GET",
			cache : false,
			beforeSend : function() {
				$("#loadingmessage").show();
			},
			complete : function() {
				$("#loadingmessage").hide();
			},
			success : function(result) {
				$("#container-fluid").html(result);
				$("#result").show();
				$("#submit").hide();
				$("#backbutton").hide();
				$(".radio").addClass("radioClass");
				//$(".LocateDealer-radio").hide();
			},
			error : function(error) {
				$("#error-sucess").modal("show");
				$("#errorMsg").html("Error");

			}
		});
	}
	return false;
}
function myServiceOffer(){
	$.ajax({ 
	 	url:"myServiceOffer.action",
	   beforeSend:function(){
		   $("#loadingmessage").show(); 
			    }, 
			    complete:function(){
			    	$("#loadingmessage").hide();
			    },
	 	success:function(result){
	 		
				 $("#container-fluid").html(result);
				 window.scrollTo(0, 0);
			 	 }
			    });
	return false;
}


function SerachDelar() {
	var zip = $("#zip").val();

	if (isNaN(zip) || zip == "") {
		alert("You must enter valid ZIP Code.");
	} else if (zip.length > 5 || zip.length < 5) {
		alert("Maximum '5' Character allowed for Zip.");
	} else {
		$.ajax({
			url : "searchByZip.action",
			data : {
				"zip" : zip
			},
			type : "GET",

			complete : function() {
			},
			success : function(result) {
				$("#dealers").html(result);
			},
			error : function(error) {

			}
		});

		return false;

	}
}

function zipcode(){
	$.ajax({
		url : "searchByZip.action",
		data : {
			"zip" : dealerSearch 
		},
		type : "GET",
		
		 beforeSend : function() {
				$("#loadingmessage").show();
			},
		complete : function() {
			$("#loadingmessage").hide();
		},
		success : function(result) {
			
			$("#container-fluid").html(result);
			$("#features").show();
			$("#how-to").hide();
			 $("#radiovalues").hide();
			 $("#anotherSearch").show();
			 $("#anotherSearch").html('<div class="radio"><input type="radio" value="${dealer.name};${dealer.street};${dealer.phone}" name="optradio"><label>' 
						+''+ searchname+'<br>'
						+'<span>'+searchstreet+'<br>'
						+''+searchphone+'</span></label></div>');
			
			 
		},
		error : function(error) {
			alert("error");
		}
	});
	
}
//Service History code statrts here

function myServiceOffer(vin,flag){
	var car=$("#selectVehicle").text();
	var data={};
    data ["vin"] = vin;
	if(flag == 'undefined' || flag == null)
	{
	flag = 'true';
	}
if(flag == 'true')
	{

var state = { 'url': 'myServiceOffer.action',data:data};
history.pushState(state ,'mymazda', '#myServiceOffer');
}
	$.ajax({
		type : "POST",
		url:"myServiceOffer.action",
		data : data,
	   beforeSend:function(){
		   $("#loadingmessage").show(); 
			    }, 
			    complete:function(){
			    	$("#loadingmessage").hide();
			    },
	 	success:function(result){
	 		
	 		$("#container-fluid").html(result);
	 		$("#servicecarName").text(car);
			$('#selector'+oldId).removeClass("active");
			$('#selector'+vin).addClass("active");
			
			if($("#selector"+vin).attr("data-id")){
			
				$('.tab-dropdown').addClass("active");	
			} 
			$("#"+vin).addClass(" fa-minus-circle");
			 $("#"+vin).attr("data-id", "2");
			oldId = vin;
			 window.scrollTo(0, 0);
		},
		error:function(error) {
				//alert("error");
		}
		});
		return false;
	}

//var oldId='${selectedVehical.vin}';
function serviceHistory(vin,flag){
	var car=$("#selectVehicle").text();
	if(car==""){
		car = $("#servicecarNameDisp2").text();
	}if(car=="") {
		car = $("#servicecarName").text(); 
	}if(car==""){
		car = $("#maintenance").text();
	}
	if(flag == 'undefined' || flag == null)
	{
	flag = 'true';
	}
if(flag == 'true')
	{
var data = {};
data["vin"] = vin;
var state = { 'url': 'serviceHistory.action',data:data};
history.pushState(state ,'mymazda', '#servicehistory');
	}
	$.ajax({
	url:"serviceHistory.action",    
	type : "GET",
	cache : false,
	complete:function(){
		$("#loadingmessage").hide();
	},
	beforeSend : function() {
		$("#loadingmessage").show();
	},
	success:function(result){
		//alert("success");
		$("#container-fluid").html(result);
		$('#selector'+oldId).removeClass("active");
		$('#selector'+vin).addClass("active");
		$('#datetimepicker1').datetimepicker({
		      //format: 'YYYY-MM-DD'
		        format: 'MM/DD/YYYY'
		    });
		if($("#selector"+vin).attr("data-id")){
		
			$('.tab-dropdown').addClass("active");	
		} 
		$("#"+vin).addClass(" fa-minus-circle");
		$("#"+vin).attr("data-id", "2");		
		$("#servicecarNameDisp").text(car);
		$("#servicecarNameDisp2").text(car);
		oldId = vin;
		
	},
	error:function(error) {
			//alert("error");
	}
	});
	return false;
}


//var oldId='${selectedVehical.vin}';
function showUpdateSelectedVehicle(vin){

			$.ajax({
				type : "POST",
				url : "showUpdateSelectedVehicle.action",
				data : "vin ="+vin,
				 beforeSend : function() {
					$("#loadingmessage").show();
				}, 
				success : function(result) {
					//hideAjaxLoadMsg();
					//alert("hi");
					$("#container-fluid").html(result);
					//$('#selector'+selectedVehicleId).removeClass("active");
					$('#selector'+oldId).removeClass("active");
					$('#selector'+vin).addClass("active");
					$('#car'+oldId).removeClass("active");
					$('#car'+vin).addClass("active");
					//$("#AddIconId").removeClass("active");
					oldId = vin;
					$('#datetimepicker1').datetimepicker({
					      //format: 'YYYY-MM-DD'
					        format: 'MM/DD/YYYY'
					    });
				 
				},complete : function() {
					$("#loadingmessage").hide();
				}
			});
	return false;
}
function addServiceHistory(){
	$("#ServiceHistory-Page").hide();
	$("#serviceHistoryFormId").show();	
	
	
}

function updateServiceRecord(serviceId){
	addServiceHistory();
	$("#titleAdd").hide();
	$("#titleEdit").show();
	$("#editBtn").show();
	$("#addBtn").hide();
	var date=$("#date"+serviceId).text();
	var mileage = $("#mileage"+serviceId).text();
	var opCode = $("#opCode"+serviceId).text().trim();
	var opCodeArr = opCode.split(",");
	opCode = opCodeArr[0];
	var amount = $("#amount"+serviceId).text();
	var location = $("#location"+serviceId).text();
	var comment = $("#comments"+serviceId).text().trim();
	var serviceType = $("#serviceType"+serviceId).text();
	//$("[name='serviceDate']").val(date);
	$("#popupDatepicker").val(date);
	$("#serviceTypeSelector").val(opCode);
	$("[name='mileage']").val(mileage);
	$("[name='amount']").val(amount);
	$("#commentsFieldId").val(comment);
	$("[name='serviceLocation']").val(location);
	$("[name='musaRefId']").val(serviceId);
}	

function eGift(vin,flag)
{
	var car=$("#selectVehicle").text();
	  if(car==""){
		car = $("#servicecarNameDisp").text();
	}if(car=="") {
		car = $("#servicecarName").text(); 
	}if(car==""){
		car = $("#maintenance").text();
	}
	if(flag == 'undefined' || flag == null)
	{
	flag = 'true';
	}
if(flag == 'true')
	{ 
	var data = {};
	data["vin"] = vin;
	var state = { 'url': 'eGift.action',data:data};
	history.pushState(state ,'mymazda', '#eGift');
	}
	$.ajax({
		url:"eGift.action",    
		type : "GET",
		cache : false,
		complete:function(){
			$("#loadingmessage").hide();
		},
		beforeSend : function() {
			$("#loadingmessage").show();
		},
		success:function(result){
			$("#container-fluid").html(result);		
			$('#selector'+oldId).removeClass("active");
		$('#selector'+vin).addClass("active");
		$('#datetimepicker1').datetimepicker({
		      //format: 'YYYY-MM-DD'
		        format: 'MM/DD/YYYY'
		    });
		if($("#selector"+vin).attr("data-id")){
		
			$('.tab-dropdown').addClass("active");	
		} 
		$("#"+vin).addClass(" fa-minus-circle");
		$("#"+vin).attr("data-id", "2");		
		$("#servicecarNameDisp").text(car);
		oldId = vin;
		},
		error:function(error) {
				alert("error");
		}
		});
	return false;
}

    
  function showUpdateSelectedVehicleEgift(vin){
			$.ajax({
				type : "POST",
				url : "showUpdateSelectedVehicleEgift.action",
				data : "vin ="+vin,
				 beforeSend : function() {
					$("#loadingmessage").show();
				}, 
				success : function(result) {
					//hideAjaxLoadMsg();
					//alert("hi");
					$("#container-fluid").html(result);
					//$('#selector'+selectedVehicleId).removeClass("active");
					$('#selector'+oldId).removeClass("active");
					$('#selector'+vin).addClass("active");
					$('#car'+oldId).removeClass("active");
					$('#car'+vin).addClass("active");
					//$("#AddIconId").removeClass("active");
					oldId = vin;
					$('#datetimepicker1').datetimepicker({
					      //format: 'YYYY-MM-DD'
					        format: 'MM/DD/YYYY'
					    });
				 
				},complete : function() {
					$("#loadingmessage").hide();
				}
			});
	return false;
} 
</script>