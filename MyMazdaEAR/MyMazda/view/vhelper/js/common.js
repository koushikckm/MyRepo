$('.required-input').on('blur', function() {
	if($(this).val().trim() == '') {
		$(this).addClass('required-alert');
		$(this).attr("title", "This is required");
	} else {
		$(this).removeClass('required-alert');
		$(this).attr("title", "");
	}
});

$(document).ready(function () {
/*common files*/	 
	/*--JoinMazda--*/
	$('.vehicle-menu').click(function(){					    
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

$(window).load(function(){
		function toggleChevron(e) {			  
						$(e.target)
			.prev('.panel-heading')
			.find("i.indicator")
			.toggleClass('fa-angle-down fa-angle-up');
		}
		$('#accordion').on('hidden.bs.collapse', toggleChevron);
		$('#accordion').on('shown.bs.collapse', toggleChevron);
	 $('#accordion1').on('hidden.bs.collapse', toggleChevron);
		$('#accordion1').on('shown.bs.collapse', toggleChevron);
	 $('#accordion2').on('hidden.bs.collapse', toggleChevron);
		$('#accordion2').on('shown.bs.collapse', toggleChevron);
		});

function mobilecollapse(){
$(function(){
function toggleChevron(p) {			   
	$(p.target)
	.prev('.panel-heading')
	.find("i")
	.toggleClass('fa-plus-circle fa-minus-circle');
}
$('#myTab-accordion').on('hidden.bs.collapse', toggleChevron);
$('#myTab-accordion').on('shown.bs.collapse', toggleChevron);

});
}

