<%@ taglib prefix="s" uri="/struts-tags"%>
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
	
	$(function() {
		$("#HowToVehicleDetailId").hide();

	});
	$(document).ready(function() {
		//alert("sdd");
		$("#modelDiv-0").show();
		$("#selectedModel0").attr("name", "mdlCode");
		$("#trimDiv-00 select").attr("name", "trim");
		$("#trimDiv-00").show();
		$("#selectVideo").hide();
		<s:if test="#session.loginUser != null">
			$("#selectVideo").show();
		</s:if>
		$("#withOutTrims").hide();
		$("#withTrims").show();
		selectedVehicleId = '${selectedVehical.vin}';
		$('#selector' + selectedVehicleId).addClass("active");
	});
	$(".getQuotesFormSHOW").click(function(e) {
		e.preventDefault();
		e.stopPropagation();
		$(".getQuotesForm").show();
	});
	$(document).ready(function() {
		
		 $(".inputsearchkey").keyup(function (e) {
		    if (e.keyCode == 13) {
		    	findHowtoSearch();
				return false;
		    } 
		});
		
	});
	var yearIndex = 0;
	function showModel() {
		howtoyear = $("#selectedYear").val();
		dataLayer.vehicle.modelYear = howtoyear;		
		$("#selectedModel" + yearIndex).attr("name", "");
		$("#trimDiv-" + yearIndex + modelIndex + " select").attr("name", "");
		yearIndex = $("#selectedYear")[0].selectedIndex;
		$("#selectedModel" + yearIndex).attr("name", "mdlCode");
		$("#YearModelTripDropdown .model").hide();
		//alert($("#modelDiv-" + yearIndex).val());
		$("#modelDiv-" + yearIndex).show();
		//alert($("#trimDiv-" + yearIndex + 0).val());
		
		if($("#trimDiv-" + yearIndex + 0).val()==undefined){
			//alert("if");
			$("#withTrims").hide();		
			$("#withOutTrims").show();	
		}else{
			//alert("else if");
			$("#trimDiv-" + yearIndex + 0).show();
			$("#trimDiv-" + yearIndex + 0 + " select").attr("name", "trim");
			$("#withTrims").show();
			$("#withOutTrims").hide();	
		}
	}

	var modelIndex = 0;
	function showTrim() {
		$("#trimDiv-" + yearIndex + modelIndex + " select").attr("name", "");
		modelIndex = $("#selectedModel" + yearIndex)[0].selectedIndex;
		$("#trimDiv-" + yearIndex + modelIndex + " select")
				.attr("name", "trim");
		$("#YearModelTripDropdown .trim").hide();
		$("#trimDiv-" + yearIndex + modelIndex).show();
	}

	var year;
	var modelName;
	var trimName;
	var result;
	function howToVehicalOption() {
		year = $("[name='year']").val();
		modelName = $("#selectedModel" + yearIndex).val();
		trimName = $("#trimDiv-" + yearIndex + modelIndex + " select").val();		
		
		var howtoSearch = $("#squeryId").val();
		//dataLayer.zipCode = howtoSearch;
		
		var url = "howToVehicalOption.action";
		$
				.ajax({
					type : "POST",
					url : url,
					data : {
						"year" : year,
						"mdlCode" : modelName,
						"trim" : trimName
					},
					beforeSend : function() {
						$("#videoContent").html("");
						$("#loadingmessage").show();
					},
					success : function(result) {
						$("#searchMenu").hide();	
						if (result == "NO") {
							$("#firstDivSelector").show();
							$("#error-sucess").modal("show");
							$("#errorMsg").html("Currently no data available for this year and model. Please call 1-800-222-5500 if you have any questions.");
							$("#firstDiv").hide();	
							$("#searchBarDiv").hide();	
							$("#searchDiv").hide();
							return false;
						} else {
							$("#searchDiv").show();
							$("#searchBarDiv").show();
							$("#selectVideo").show();
							videosList();
							pdfDetails();
							$("#searchDiv").html("");
							$("[id='squeryId']").val("");
							$("#modelPDF").show();
							$("#firstDivSelector").show();
							$("#listingDiv").html(result);
							$("#listingDiv").show();

						}				
						window.scrollTo(0, 400);
					},
					complete : function() {
						$("#loadingmessage").hide();
					}
				});
		return false;
	}

	
	function howToVehicleOperation(val) {
	}
	function showYearModelTripDropdown() {
		//$("#YearModelTripDropdown").show();
	}
	function showFirst() {
		$("#firstLevelDiv").show();
		$("#firstlev").show();
		$("#secondDiv").hide();
		$("#secondlev").hide();
		$("#thirdDiv").hide();
		$("#thirdlev").hide();
	}

	function showSecond(index, category) {
		$("#searchDiv").html("");
		$("#searchedTitle").html("");
		$("[id='squeryId']").val("");
		//alert("showSecond  index-->"+index+" category ::"+category);
		var divid = $("#showSecond" + index);
		year = $("[name='year']").val();
		modelName = $("#selectedModel" + yearIndex).val();
		trimName = $("#trimDiv-" + yearIndex + modelIndex + " select").val();
		var url = "howToVehicalSecondLevel.action";
		$.ajax({
			type : "POST",
			url : url,
			data : {
				"year" : year,
				"mdlCode" : modelName,
				"trim" : trimName,
				"index" : index,
				"title" : category
			},
			beforeSend : function() {
				
				
				$("#videoContent").html("");
				$("#loadingmessage").show();
			},
			success : function(result) {
				
				var resultVal = result.substring(4, 8);
				if (resultVal == "DOCT") {
					$("#HowToVehicleDetailId").html(result);
					$("#HowToVehicleDetailId").show();
					$("#firstDivSelector").show();
					$("#titleDiv").hide();
					$("#levelOne").hide();
					$("#selector1").addClass("active");
					$("#howToTitle").text(category);
					$("#howToTitle").show();
					$("#videoTitle").hide();
					
					divid.html(result);
					divid.show();

				} else {
					//alert("else Second Show result success"+result);

					$("#HowToVehicleDetailId").show();
					$("#firstDivSelector").show();

				}

			},
			complete : function() {
				$("#loadingmessage").hide();

			}
		});
		return false;

	}

	function showOne(index, category) {
		$("#searchDiv").html("");
		$("#searchedTitle").html("");
		$("[id='squeryId']").val("");
		//alert("showSecond  index-->"+index+" category ::"+category);
		var divid = $("#showSecond" + index);
		year = $("[name='year']").val();
		modelName = $("#selectedModel" + yearIndex).val();
		trimName = $("#trimDiv-" + yearIndex + modelIndex + " select").val();
		var url = "howToVehicalSecondLevel.action";
		$.ajax({
			type : "POST",
			url : url,
			data : {
				"year" : year,
				"mdlCode" : modelName,
				"trim" : trimName,
				"index" : index,
				"title" : category
			},
			beforeSend : function() {
				
				
				$("#videoContent").html("");
				$("#loadingmessage").show();
			},
			success : function(result) {
				
				$("#firstDivSelector").show();
				$("#videoContent").html(result);
				$("#videoContent").show();
				$("#videoTitle").hide();
			},
			complete : function() {
				$("#loadingmessage").hide();

			}
		});
		return false;

	}
	
	function showThird(index, title, contentType, contentId) {
	
		window.scrollTo(0, 800);
		$("#searchDiv").html("");
		$("#searchedTitle").html("");
		$("[id='squeryId']").val("");
		//alert("showThird "+index+" :: "+ title+" :: "+contentType+" :: "+contentId);
		if (contentType != null && contentType != "") {
			var url = "callLookUpContent.action";
			$.ajax({
				type : "POST",
				url : url,
				data : {
					"contantType" : contentType,
					"contentId" : contentId
				},
				beforeSend : function() {
					//showAjaxLoadMsg("Loading...");
					$("#videoContent").html("");
					$("#loadingmessage").show();
				},
				success : function(result) {
					//alert("showThird "+result);
					$("#firstDivSelector").show();
					$("#videoTitle").hide();
					$("#videoContent").html(result);
					$("#videoContent").show();
				},
				complete : function() {
					$("#loadingmessage").hide();
					//hideAjaxLoadMsg();
					/* $("#YearModelTripDropdown").hide(); */
					window.scrollTo(0, 800);
				}
			});
			return null;
		} else {
			year = $("[name='year']").val();
			modelName = $("#selectedModel" + yearIndex).val();
			trimName = $("#trimDiv-" + yearIndex + modelIndex + " select")
					.val();
			var url = "howToVehicalThirdLevel.action";
			$.ajax({
				type : "POST",
				url : url,
				data : {
					"year" : year,
					"mdlCode" : modelName,
					"trim" : trimName,
					"index" : index,
					"title" : title
				},
				beforeSend : function() {
					$("#videoContent").html("");
					$("#loadingmessage").show();
				},
				success : function(result) {
					//alert("Third Show result success"+result);
					$("#videoContent").html(result);
					$("#videoContent").show();
					$("#HowToVehicleDetailId").show();
					$("#firstDivSelector").show();
					/* $('html, body').animate({
							scrollTop: $("#titleDiv").offset().top
							}, 2000); */

				},
				complete : function() {
					$("#loadingmessage").hide();
				}
			});
			return false;
		}

	}
	
	function howToSearch() {
		var url = "openHowToSearch.action";
		$.ajax({
			type : "POST",
			url : url,
			data : {},
			beforeSend : function() {
				//showAjaxLoadMsg("Loading...");
				$("#loadingmessage").show();
			},
			success : function(result) {
				$("#pageContainDiv").html(result);
				$("#pageContainDiv").show();
				$("#searchBoxDiv").show();
				$("#searchContent").hide();

			},
			complete : function() {
				$("#loadingmessage").show();
				//hideAjaxLoadMsg();
				//$("#YearModelTripDropdown").hide();
				window.scrollTo(0, 0);
			}
		});
		return false;
	}

	function videosList() {
		var url = "videoList.action";
		$.ajax({
			type : "POST",
			url : url,
			data : {},
			beforeSend : function() {
				
			},
			success : function(result) {
				$("#searchedTitle").html("");
				
				$("#videoContent").html(result);
				$("#videoContent").show();

			},
			complete : function() {
				
				$("#loadingmessage").hide();
				window.scrollTo(0, 0);
			}
		});
		return false;
	}

	function pdfDetails() {
		var url = "modelPDFdetails.action";
		$.ajax({
			type : "POST",
			url : url,
			data : {},
			beforeSend : function() {
				//showAjaxLoadMsg("Loading...");
			},
			success : function(result) {
				$("#modelPDF").html(result);
				$("#modelPDF").show();

			},
			complete : function() {
				//hideAjaxLoadMsg();
				//$("#YearModelTripDropdown").hide();
				window.scrollTo(0, 0);
			}
		});
		return false;
	}

	function findHowtoSearch() {
		var sQuery = $("#squeryId").val();
		if (sQuery == "") {
			$("#error-sucess").modal("show");
			$("#errorMsg").html("Please enter data for search.");
				return false;
		} else {
			var url = "findHowToSearch.action";
			$.ajax({
				type : "POST",
				url : url,
				data : {
					"squery" : sQuery
				},
				beforeSend : function() {
					$("#searchDiv").html("");
					$("#loadingmessage").show();
				},
				success : function(result) {
					$("#searchMenu").show();	
					$("#videoTitle").hide();
					$("#searchDiv").html(result);
					$("#searchDiv").show();
					$("#listingDiv").show();
					$("#firstDivSelector").show();
					//showYearModelTripDropdown();
				},
				complete : function() {
					$("#loadingmessage").hide();
					//$("#YearModelTripDropdown").hide();
					window.scrollTo(0, 400);
				}
			});
		}
		return false;
	}

	function audioNaviPhone() {
		$("#HowToVehicleDetailId").hide();
		$("#firstDivSelector").hide();
		$("#popularDetails").hide();
		$("#audioNaviPhone").show();
	}

	var oldId;
	function UpdateSelCarOfHowToVehicle(id, vin) {

		var url = "howToVehicalOptionLogin.action";
		$.ajax({
			type : "POST",
			url : url,
			data : {
				"id" : id,
				"vin" : vin
			},
			beforeSend : function() {
				//showAjaxLoadMsg("Loading...");
			},
			success : function(result) {
				$("#pageContainDiv").html(result);
				$("#pageContainDiv").show();
				$("#searchBoxDiv").show();
				$("#searchContent").hide();

				$("#selector0").removeClass("active");
				$("#selector" + id).addClass("active");

			},
			complete : function() {
				oldId = ("#selector" + id);
				////hideAjaxLoadMsg();
				$("#YearModelTripDropdown").hide();
				window.scrollTo(0, 0);
			}
		});
	}

	/* function seatDetail(){
	 var url = "seatDetail";
				$.ajax({
					type : "POST",
					url : url,
					data : {"id":8},
					beforeSend : function() {
					
					},success : function(result) {
					$("#seatDetaild").html(result);
					},complete : function() {
						oldId = id;
					}
				}); 	
		} */
	function seatRelatedData() {
		var url = "seatRelatedData.action";
		$.ajax({
			type : "POST",
			url : url,
			beforeSend : function() {

			},
			success : function(result) {
				$("#SeatDetailList").html(result);
			},
			complete : function() {
				oldId = id;
			}
		});
	}
	function showMyFirstDiv() {
		$("#firstDivSelector").hide();
		$("#firstDiv").show();
		$("#secondDiv").hide();
		$("#levelOne").show();
		$("#levelTwo").hide();
		$("#howToTitle").hide();
		$("#titleDiv").hide();
		return false;
	}

	function showMySecondDiv() {
		$("#firstDivSelector").hide();
		$("#firstDiv").hide();
		$("#secondDiv").show();
		return false;
	}
	function videoDemos() {
		$.ajax({
			url : "howTOMazdaNotLogin.action",
			beforeSend : function() {
				$("#loadingmessage").show();
			},
			success : function(result) {
				$("#container-fluid").html(result);
				window.scrollTo(0, 0);
			},
			complete : function() {
				$("#loadingmessage").hide();
			}
		});
		return false;
	}
	function videoDemosLogin(flag) {
		$(".demosform").hide();
		
		if(flag == 'undefined' || flag == null)
		{
		flag = 'true';
		}
	if(flag == 'true')
		{
	var data = {};
	var state = { 'url': 'howTOMazda.action',data:data};
	history.pushState(state ,'mymazda', '#videos');
		}
		$.ajax({
			url : "howTOMazda.action",
			beforeSend : function() {
				$("#loadingmessage").show();
			},
			success : function(result) {
				$("#container-fluid").html(result);
				$(".demosform").hide();
				$("#searchMenu").hide();
				window.scrollTo(0, 0);
			},
			complete : function() {
				$("#loadingmessage").hide();
			}
		});
		return false;
	}
	
</script>