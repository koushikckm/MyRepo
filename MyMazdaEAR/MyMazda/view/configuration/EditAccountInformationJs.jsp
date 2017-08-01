<script>
	$('.required-input').on('blur', function() {
		if($(this).val().trim() == '') {
			$(this).addClass('required-alert');
			$(this).attr("title", "This is required");
		} else {
			$(this).removeClass('required-alert');
			$(this).attr("title", "");
		}
	});
$(document).ready(function() {
	
	 $(".editaccountsearchkey").keyup(function (e) {
	    if (e.keyCode == 13) {
	    	editAccountsearchDealers();
			return false;
	    } 
	});
	
});
$(function(){
	 var stateName='${session.loginDetail.state}';
	 $("#state option").each(function(){
		if(stateName!=""){
		  $(this).siblings("[value="+ stateName+"]").remove();
    	  var newOption = $('<option selected="selected" value="'+stateName+'">'+stateName+'</option>');
		  $('#state').append(newOption);
		}
		
		/*  var newOption = $('<option selected="selected" value="'+stateName+'">'+stateName+'</option>');
		 $('#state').append(newOption); */
		
		}); 
	
});
function editAccountsearchDealers() {
	  add='';
	  edit='';
	  registersearch='';
	var data = {};
	var dealerSearch = $("#editsearchKey").val();
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
	


</script>
