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
	
$(document).ready(function() {
	
	$(".locatesearch").keyup(function (e) {
	    if (e.keyCode == 13) {
	    	
	    	searchDealers();
			return false;
	    }
	});
	});
function showExpressMess(){
	$("#Locatedealer").show();
}

function hideExpressMess(){
	$("#Locatedealer").hide();
}

</script>