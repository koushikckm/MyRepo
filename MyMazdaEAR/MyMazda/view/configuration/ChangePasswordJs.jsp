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
	
function submitPasswordChange() {
	
	var currentPassword = $.trim($("[id='currentPassword']").val());
	var newPassword 	= $.trim($("[id='newPassword']").val());
	var confirmPassword = $.trim($("[id='confirmPassword']").val());
	var regEx = /^[a-zA-Z0-9@*$.!%\\n\\t]{4,15}$/g;
	var dataString =  "currentPassword=" + currentPassword 
					+ "&newPassword=" + newPassword 
					+ "&confirmPassword=" + confirmPassword;
	
		if(currentPassword == ""){
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter your current password.");
			
		}  else if(newPassword == ""){
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter your new password.");
			
		} else if(confirmPassword == ""){
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter your confirm password.");
			
		} else if(newPassword != confirmPassword){
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Your new password and confirm password entries do not match. Please try again.");
		
		} else if(currentPassword == newPassword){
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Your current password and new password should not be the same. Please try again.");
		
		} else if(!newPassword.match(regEx)){
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Password must be 4-15 characters long. Can contain alphanumeric characters and the following symbols @ * $ . ! % ");
		} 
		else{
			var url = "submitPasswordChange.action";
			$.ajax({
				type : "POST",
				url : url,
				data : dataString,
				beforeSend : function() {
					$("#loadingmessage").show();
				},
				complete : function() {
					$("#loadingmessage").hide();
				},
				success : function(result) {
					//alert('result: '+result);
					if (result == "success") {
						$("#success-msg").modal("show");
						$("#successMsg").html("Password changed successfully.");
						$('.modal-backdrop').hide();
						setTimeout(function() {
							homeClick(); 
							$("#success-msg").modal("hide");
						},3000); 
					} else {
						$("#error-sucess").modal("show");
						$("#errorMsg").html("Password not changed. Please try again.");
					}
				},
				
			});
			
		}
		return false;
	}
	
	function cancelChangePassword(){
		window.location="myMazda.action";
		return false;
	}
	
</script>
