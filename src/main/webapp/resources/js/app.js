var currentDate = new Date().toString();
var selectedChargeUnit;
var coupons=[];
var titles=[];
var couCount=[];
var cupMax=[];
var customers=[];
var dtrNum=[];
var goods='';
$(document).ready(function() {
	var contextPath = $("#contextPath").val();
	var itemImageEditor = new SortableImageEditor({id:"itemImagePanel",imageSelectorId:"itemImageSelector",maxImageCount:5, imageInputName:"itemImage"});
	var itemDetailImageEditor = new SortableImageEditor({id:"itemDetailImagePanel",imageSelectorId:"itemDetailImageSelector",maxImageCount:15,imageInputName: "itemDetailImage"});
	var recipeStemsImageEditor = new SortableImageEditor({id:"recipeStepsImagePanel",imageSelectorId:"recipeStepImageSelector",maxImageCount:15,imageInputName: "stepsFile", imagePathInputName: "stepsFileUrl"});
	//优惠券模态框隐藏id加载
	var len = $("input[name='CouponId']").length;
	if(len > 0){
		$("input[name='CouponId']").each(function(){
			coupons.push($(this).attr("id"));
			titles.push($(this).attr("data"))
			couCount.push($(this).val().split("*")[1]);
		})
		$("input[name='maxReceiveNum']").each(function(){
			cupMax.push($(this).val());
		})
	}
	//客户模态框隐藏id加载
	var len = $("input[name='customerIds']").length;
	if(len > 0){
		$("input[name='customerIds']").each(function(){
			customers.push($(this).val());
		})
	}
	//goods linkedItemId
	//商品模态框隐藏id加载
	var len = $("input[name='linkedItemId']").length;
	if(len > 0){
			goods=$("input[name='linkedItemId']").val();
	}
	//选项 点击X删除
	 $(".coupon-tag-close").each(function(e) {
			$(this).click(function(e){
					var itemId = $(this).attr('data');
					$("#couponsContainer").find("#"+itemId).detach();
					$("#itemsContainer").find("#"+itemId).detach();
					$("#customersContainer").html('');
					var divName=$(this).parent().parent().attr("id");
					if(divName=='customerDiv'){
						customers.splice(0);
					}
					if(divName=='couponItems'){
						$.each(coupons,function(i,item){
							if(coupons.indexOf(itemId) > -1){
								coupons.splice(i,1);
								titles.splice(i,1);
								couCount.splice(i,1);
							}
						})
					}
					if(divName=='itemsDiv'){
						goods='';
					}
					$(this).parent().detach();
			})
		});
	 //仓库下拉查询
	 $('#storeId').searchableSelect();
	//供应商下拉查询
	 $('#supplierIdSearch').searchableSelect();
	 //订单模块分页
	 orderLimitPage($("#currentPage").val(),$("#total").val());
	 //库存模块分页
	 inventoryLimitPage($("#currentPage").val(),$("#total").val());
	 //搜索  分页初始化页数
	 $("#orderForm").click(function(){
		 $("#currentPage").val(1);
	 })
	  $("#inventoryForm").click(function(){
		 $("#currentPage").val(1);
	 })
	//$.datetimepicker.setLocale('ch');
	$(':input[type=number]').on('mousewheel',function(e){ $(this).blur(); });
	//时间控件 汉化
	$.datetimepicker.setLocale('ch');
	//时间控件清空
	$(".time-clear").click(function(){
		$(this).parent().find("input").val('');
	})
	
	$('#nextDayLastOrderTime').datetimepicker({
	  datepicker:false,
	  format:'H:i'
	});
	
	$("#nextDayDeliveryTimeFrom").datetimepicker({
		  datepicker:false,
		  format:'H:i'
		});
	
	$("#nextDayDeliveryTimeEnd").datetimepicker({
		  datepicker:false,
		  format:'H:i'
		});
	
	$("#presellLastOrderDate").datetimepicker({
		  timepicker:false,
		  formatDate:'Y-m-d',
		  format:'Y-m-d',
		  lang:'zh',
		  scrollInput:false,
		  minDate: currentDate
		});
	
	$("#flashSaleEndTime").datetimepicker({
		datepicker:true,
		  timepicker:true,
		  format:'Y-m-d H:i',
		  lang:'zh',
		  scrollInput:false,
		  minDate: currentDate,
		  minTime :currentDate
		});
	$("#lyInCreationDate").datetimepicker({
		datepicker:true,
		  timepicker:true,
		  format:'Y-m-d H:i',
		  lang:'zh',
		  scrollInput:false,
		});
	
	$("#lyOutCreationDate").datetimepicker({
		 datepicker:true,
		  timepicker:true,
		  format:'Y-m-d H:i',
		  lang:'zh',
		  scrollInput:false,
		});
	
	$("#promotionTime").datetimepicker({
		 datepicker:true,
		  timepicker:false,
		  format:'Y-m-d',
		  lang:'zh',
		  scrollInput:false,
		});
	
	$("#orderDate").datetimepicker({
		datepicker:true,
		  timepicker:false,
		  format:'Y-m-d',
		  lang:'zh',
		  scrollInput:false,
		});
	
	$("#refundAppliedTime").datetimepicker({
		datepicker:true,
		  timepicker:false,
		  format:'Y-m-d',
		  lang:'zh',
		  scrollInput:false,
		});
	
	$("#orderStartTime").datetimepicker({
		datepicker:true,
		  timepicker:true,
		  format:'Y-m-d H:i',
		  lang:'zh',
		  scrollInput:false,
		  minDate: currentDate
		});
	$("#appCreationDate").datetimepicker({
		  datepicker:true,
		  timepicker:true,
		  format:'Y-m-d H:i',
		  lang:'zh',
		  scrollInput:false,
		  minDate: currentDate
		});
	
	$("#orderEndTime").datetimepicker({
		datepicker:true,
		  timepicker:true,
		  format:'Y-m-d H:i',
		  lang:'zh',
		  scrollInput:false,
		  minDate: currentDate,
		  minTime :currentDate
		});
	
	$("#balanceStartTime").datetimepicker({
		datepicker:true,
		  timepicker:true,
		  format:'Y-m-d H:i',
		  lang:'zh',
		  scrollInput:false,
		  minDate: currentDate
		});
	
	$("#balanceEndTime").datetimepicker({
		datepicker:true,
		  timepicker:true,
		  format:'Y-m-d H:i',
		  lang:'zh',
		  scrollInput:false,
		  minDate: currentDate,
		  minTime :currentDate
		});
	
	$("#promStartTime").datetimepicker({
		datepicker:true,
		  timepicker:true,
		  format:'Y-m-d H:i',
		  lang:'zh',
		  scrollInput:false,
		  minDate: currentDate
		});
	
	$("#promEndTime").datetimepicker({
		datepicker:true,
		  timepicker:true,
		  format:'Y-m-d H:i',
		  lang:'zh',
		  scrollInput:false,
		  minDate: currentDate
		});
	
	$("#flashSaleStartTime").datetimepicker({
		  datepicker:true,
		  timepicker:true,
		  format:'Y-m-d H:i',
		  lang:'zh',
		  scrollInput:false,
		  minDate: currentDate
		});
	//会员时间min
	$("#vipStartDateMin").datetimepicker({
		  datepicker:true,
		  timepicker:true,
		  format:'Y-m-d H:i',
		  lang:'zh',
		  scrollInput:false,
		  //minDate: currentDate
		});
	
	//会员时间max
	$("#vipStartDateMax").datetimepicker({
		  datepicker:true,
		  timepicker:true,
		  format:'Y-m-d H:i',
		  lang:'zh',
		  scrollInput:false,
		  //minDate: currentDate
		});
	
	//最近消费时间min
	$("#orderDateMin").datetimepicker({
		  datepicker:true,
		  timepicker:true,
		  format:'Y-m-d H:i',
		  lang:'zh',
		  scrollInput:false,
		  //minDate: currentDate
		});
	
	//最近消费时间max
	$("#orderDateMax").datetimepicker({
		  datepicker:true,
		  timepicker:true,
		  format:'Y-m-d H:i',
		  lang:'zh',
		  scrollInput:false,
		  //minDate: currentDate
		});
	
	//结束时间
	$("#endTime").datetimepicker({
		  datepicker:true,
		  timepicker:true,
		  format:'Y-m-d H:i',
		  lang:'zh',
		  scrollInput:false,
		});
	
	//开始时间
	$("#startTime").datetimepicker({
		  datepicker:true,
		  timepicker:true,
		  format:'Y-m-d H:i',
		  lang:'zh',
		  scrollInput:false,
		});
	$("#flashSaleSearchStartTime").datetimepicker({
		  datepicker:true,
		  timepicker:false,
		  format:'Y-m-d',
		  lang:'zh',
		  scrollInput:false,
		});
	
	$("#couponEndDate").datetimepicker({
		  datepicker:true,
		  timepicker:false,
		  format:'Y-m-d',
		  lang:'zh',
		  scrollInput:false,
		});
	
	$("#pocketStartTime").datetimepicker({
		  datepicker:true,
		  timepicker:true,
		  format:'Y-m-d H:i',
		  lang:'zh',
		  scrollInput:false,
		});
	
	$("#pocketEndTime").datetimepicker({
		  datepicker:true,
		  timepicker:true,
		  format:'Y-m-d H:i',
		  lang:'zh',
		  scrollInput:false,
		});
	
	$("#activityEndTime").datetimepicker({
		  datepicker:true,
		  timepicker:false,
		  format:'Y',
		  lang:'zh',
		  scrollInput:false,
		  minView: "month",
		});
	
	$("#birthdayStartTime").datetimepicker({
		  datepicker:true,
		  timepicker:false,
		  format:'m-d',
		  lang:'zh',
		  scrollInput:false,
		  minView: "day",
		  validateOnBlur:false
		});
	
	$("#birthdayEndTime").datetimepicker({
		  datepicker:true,
		  timepicker:false,
		  format:'m-d',
		  lang:'zh',
		  scrollInput:false,
		  minView: "month",
		  validateOnBlur:false
		});
	
	$("#activeTime").datetimepicker({
		  datepicker:false,
		  timepicker:true,
		  format:'H:i',
		  lang:'zh',
		  scrollInput:false,
		  minView: "month",
		});
	
	$("#presellLastOrderTime").datetimepicker({
		  datepicker:false,
		  format:'H:i'
		});
	
	$("#presellDeliveryDate").datetimepicker({
		  timepicker:false,
		  format:'Y-m-d',
		  formatDate:'Y-m-d',
		  lang:'zh',
		  scrollInput:false,
		  minDate: currentDate
		});
	
	$("#workingTimeFrom").datetimepicker({
		  datepicker:false,
		  timepicker:true,
		  format:'H:i',
		});
	
	$("#workingTimeEnd").datetimepicker({
		  datepicker:false,
		  timepicker:true,
		  format:'H:i',
		});
	
	
	$("#deliveryTimeStart").datetimepicker({
		  datepicker:false,
		  timepicker:true,
		  format:'H:i',
		});
	
	$("#deliveryTimeEnd").datetimepicker({
		  datepicker:false,
		  timepicker:true,
		  format:'H:i',
		});
	
	
	$("#presellDeliveryTimeFrom").datetimepicker({
		  datepicker:false,
		  format:'H:i'
		});
	
	$("#presellDeliveryTimeEnd").datetimepicker({
		  datepicker:false,
		  format:'H:i'
		});
	
	
	$("#specialOfferCheck").click(function(e){
		var checked = $(this).prop("checked");
		if(checked) {
			$("#inputPrice").prop("disabled",false);
		} else {
			$("#inputPrice").prop("disabled",true);
			$("#inputPrice").val("");
		}
	});
	
	$("#selectPresellDateType").change(function(e){
		var val = $(this).val();
		if(val == 4) {
			$("#presellDeadlineDatePanel").show();
		} else {
			$("#inputPresellDeadlineDate").val("");
			$("#presellDeadlineDatePanel").hide();
		}
	});
	
	$("#selectPresellDeliveryDateType").change(function(e){
		var val = $(this).val();
		if(val == 4) {
			$("#presellDeliveryDatePanel").show();
		} else {
			$("#inputPresellDeliveryDate").val("");
			$("#presellDeliveryDatePanel").hide();
		}
	});
	
	$('input[type=radio][name="specType"]').change(function(e){
		if($(this).val() == 1) { //标品
			// disable unit 'g', enable all the other units
			$("#inputUnit > option").each(function() {
				if($(this).text() == 'g') {
					$(this).prop("disabled",true);
					$(this).prop("selected",false);
				} else {
					$(this).prop("disabled",false);
				}
			});
			
			// hide & disable charge unit
			$("#chargeUnitContainer").hide();
			$("#inputChargeUnit").prop("disabled",true);
		} else { // 非标品
			// enable unit 'g', disable all the other units
			$("#inputUnit > option").each(function() {
				if($(this).text() == 'g') {
					$(this).prop("disabled",false);
					$(this).prop("selected",true);
					updateChargeUnitLabel($("#inputChargeUnit").val()+'g');
				} else {
					$(this).prop("disabled",true);
				}
			});

			// show & enable charge unit
			$("#chargeUnitContainer").show();
			$("#inputChargeUnit").prop("disabled", false);
		}
	});
	
	$("#inputChargeUnit").change(function(){
		updateChargeUnitLabel($(this).val()+'g');
	});
	
	$("#inputUnit").change(function(){
		$("#inputUnit > option").each(function() {
			if($(this).prop("selected")) {
				if($(this).text() != 'g') {
					updateChargeUnitLabel($(this).text());
				} else {
					updateChargeUnitLabel($("#inputChargeUnit").val()+'g');
				}
			}
		});
	});
	
	function updateChargeUnitLabel(txt) {
		$(".chargeUnitLabel").each(function(){
			$(this).text(txt);
		});
	}
	
	$('input[type=radio][data-toggle="tab-collapse"]').change(function(e) {
		$('input[type=radio][data-toggle="tab-collapse"]').each(function(e){
			var checked = $(this).prop("checked");
			var selectedTab = $(this).data("tab");
			if(checked) {
				$(this).parent().siblings(".triangle-up").addClass("active");
				if(selectedTab) {
					$("#"+selectedTab).show();
				}
			} else {
				$(this).parent().siblings(".triangle-up").removeClass("active");
				if(selectedTab) {
					$("#"+selectedTab).hide();
				}
			}
		});
    });

	$("#itemImageSelector").click(function(e) {
		if (itemImageEditor.imageCount >= itemImageEditor.maxImageCount) {
			alert("最多只能选择"+itemImageEditor.maxImageCount+"张图片");
			return false;
		}
		itemImageEditor.addImage();
	});
	
	$("#itemDetailImageSelector").click(function(e) {
		if (itemDetailImageEditor.imageCount >= itemDetailImageEditor.maxImageCount) {
			alert("最多只能选择"+itemDetailImageEditor.maxImageCount+"张图片");
			return false;
		}
		itemDetailImageEditor.addImage();
	});
	$("#balanceImageSelector").click(function(e) {
		if (itemDetailImageEditor.imageCount >= itemDetailImageEditor.maxImageCount) {
			alert("最多只能选择"+itemDetailImageEditor.maxImageCount+"张图片");
			return false;
		}
		itemDetailImageEditor.addImage();
	});
	$("#recipeStepImageSelector").click(function(e) {
		if (recipeStemsImageEditor.imageCount >= recipeStemsImageEditor.maxImageCount) {
			alert("最多只能选择"+recipeStemsImageEditor.maxImageCount+"张图片");
			return false;
		}
		recipeStemsImageEditor.addImage();
	});
	
	if($("#productPromotionItemBox").length>0) {
		$("#productPromotionItemBox").autocomplete({
			source: contextPath+"/items/autocomplete/search-promotion-items",
			minLength: 1,
			select: function( event, ui ) {
				this.value = "["+ ui.item.id+"] - "+ ui.item.name + " ￥" + ui.item.listPrice;
				$("#promotionItemId").val(ui.item.id);
		        return false;
		    }
		}).autocomplete( "instance" )._renderItem = function( ul, item ) {
        return $( "<li>" )
        	.append( "<div>[" + item.id + "]&nbsp;-&nbsp;" + item.name + "&nbsp;￥" +item.listPrice + "</div>" )
        	.appendTo( ul );
      };
	}
	
//     $("#dotImgBtn").click(function(e){
//    	 $("#itemSearchTableOverlay").show();
//     });
//     
//     $(".item-search-modal-close").click(function(e){
//    	 $("#itemSearchTableOverlay").hide();
//     });
     $("#promotionStartDate").datetimepicker({
		  format:'Y-m-d H:i',
		  minDate: currentDate
		});
     
     $("#promotionEndDate").datetimepicker({
		  format:'Y-m-d H:i',
		  minDate: currentDate,
		  minTime :currentDate
		});
     
     $("#promotionStartNowCheck").click(function(e){
    	 var checked = $(this).prop("checked");
    	 if(checked) {
    		 $("#promotionStartDate").prop("disabled", true);
    	 } else {
    		 $("#promotionStartDate").prop("disabled", false);
    	 }
     });
     
     $(".condition-input").change(function(e) {
    	 $("#conditionValue").val($(this).val());
     });
     
     $(".cash-discount-val").change(function(e){
    	 $("#discount").val($(this).val());
     });
     
    if($("#onpackItemInput").length>0) {
    	$("#onpackItemInput").autocomplete({
    		source: contextPath+"/items/autocomplete/search-items",
    		minLength: 1,
    		select: function( event, ui ) {
    			this.value = "["+ ui.item.id+"] - "+ ui.item.name;
    	        $("#onpackItemId").val(ui.item.id );
    	        return false;
    	    }
    	}).autocomplete( "instance" )._renderItem = function( ul, item ) {
        return $( "<li>" )
        	.append( "<div>[" + item.id + "]&nbsp;-&nbsp;" + item.name + "</div>" )
        	.appendTo( ul );
        };
    }
     
     $('input[type=radio][data-toggle="ppromo"]').change(function(e){
    	 var selectedTab = $(this).data("tab");
    	 if(selectedTab == 'onpack') {
			$("#promotionType").val(2);
			$("#maxPromotionMixItemCountDiv").hide();
			$("#maxCashDiscountValueDiv").hide();
		 } else if(selectedTab == 'promotion-mix') {
			 $("#promotionType").val(3);
			 $("#maxCashDiscountValueDiv").hide();
			 $("#maxPromotionMixItemCountDiv").show();
		 } else {
			$("#promotionType").val(1);
			$("#maxPromotionMixItemCountDiv").hide();
			$("#maxCashDiscountValueDiv").show();
		 }
    	 
    	 $("input[type=radio][data-toggle='ppromo']").each(function(e){
 			var checked = $(this).prop("checked");
 			var tab = $(this).data("tab");
 			
 			if(checked) {
 				$("#"+tab).show();
 			} else {
 				$("#"+tab).hide();
 			}
 		});
     });
     
     $("#inputStoreCodition").change(function(e){
    	 $("#inputStoreCodition > option").each(function() {
				if($(this).val() == 'all') {
					$("#allStore").val(1);
				} else {
					$("#allStore").val(0);
				}
			});
     });
     
     if($("#combinationPromotionItemBox").length >0) {
    	 var autoinputbox = $("#combinationPromotionItemBox");
    	 $("#combinationPromotionItemBox").autocomplete({
			source: contextPath+"/items/autocomplete/search-promotion-items",
			minLength: 1,
			select: function( event, ui ) {
				this.value = "["+ ui.item.id+"] - "+ ui.item.name + " ￥" + ui.item.listPrice;
				autoinputbox.siblings(":last").val(ui.item.id);
		        return false;
		    }
		}).autocomplete( "instance" )._renderItem = function( ul, item ) {
        return $( "<li>" )
        	.append( "<div>[" + item.id + "]&nbsp;-&nbsp;" + item.name + "&nbsp;￥" +item.listPrice + "</div>" )
        	.appendTo( ul );
        };
     }
     
     $(".addNewItemAutoInput").click(function(e){
    	var appendContainer = $(this).parent();
    	var newEle = $("#input-box-daynamic-mock").clone(true).removeAttr("id").removeAttr("style").removeClass("input-box-daynamic-mock").addClass("input-box-daynamic");
    	newEle.find("#promotionItemId").attr("name", "promotionItemId").removeAttr("id");
    	newEle.children(":first").autocomplete({
			source: contextPath+"/items/autocomplete/search-promotion-items",
			minLength: 1,
			select: function( event, ui ) {
				this.value = "["+ ui.item.id+"] - "+ ui.item.name + " ￥" + ui.item.listPrice;
				newEle.children(":last").val(ui.item.id);
		        return false;
		    }
		}).autocomplete( "instance" )._renderItem = function( ul, item ) {
        return $( "<li>" )
        	.append( "<div>[" + item.id + "]&nbsp;-&nbsp;" + item.name + "&nbsp;￥" +item.listPrice + "</div>" )
        	.appendTo( ul );
      };
    	appendContainer.after(newEle);
    	
     });
     
     $(".removeItemAutoInput").click(function(e){
    	 var containerClass = $(this).parent().attr("class");
    	 if($("."+containerClass).size() > 1) {
    		 $(this).parent().detach();
    	 } else {
    		 var url=window.location.href.split("?")[0];
    		 $("#itemAutoInputBox").val('');
    		 $("input[name='itemId']").val('');
    		 if(url.indexOf("flashsale")>=0){
    			 $("#price").val('');
    		 }
    	 }
     });
     $(".removeRecipesAutoInput").click(function(e){
    	 if($(".removeRecipesAutoInput").length ==2) {
    		 alert("无法删除当前步骤，需要至少一个步骤。");
    		 return false;
    	 }
    	 var containerClass = $(this).parent().parent().attr("class");
    	 $(this).parent().parent().detach();
    	 var removedId = $(this).parent().find("input[name=recipeStepId]").val();
    	 $("#removedStepIds").val($("#removedStepIds").val() == "" ? removedId : $("#removedStepIds").val()+","+ removedId);
     });
     
     if($("#promotionMixItemAutoInput").length > 0) {
         var parent = $("#promotionMixItemAutoInput").parent().parent(); 
         $("#promotionMixItemAutoInput").autocomplete({
   			source: contextPath+"/items/autocomplete/search-items",
   			minLength: 1,
   			select: function( event, ui ) {
   				this.value = "["+ ui.item.id+"] - "+ ui.item.name + " ￥" + ui.item.listPrice;
   				parent.children("input:last").val(ui.item.id);
   				parent.find("input[name='promotionMixPrice']").attr("data",ui.item.listPrice);
   		        return false;
   		    }
   		 }).autocomplete( "instance" )._renderItem = function( ul, item ) {
           return $( "<li>" )
           	.append( "<div>[" + item.id + "]&nbsp;-&nbsp;" + item.name + "&nbsp;￥" +item.listPrice + "</div>" )
           	.appendTo( ul );
          };
       }
     
     $(".add-row").click(function(e){
    	 var container = $(this).parent().parent();
    	 var mockEle = $("#mock-row").clone(true).removeAttr("id").removeAttr("style").removeClass("input-box-daynamic-mock").addClass("input-box-daynamic");
    	 mockEle.find("#promotionMixPrice").removeAttr("id").attr("name", "promotionMixPrice");
    	 mockEle.children("input:last").removeAttr("id").attr("name", "promotionMixItemId");
    	 mockEle.find(".auto-input").autocomplete({
			source: contextPath+"/items/autocomplete/search-items",
			minLength: 1,
			select: function( event, ui ) {
				this.value = "["+ ui.item.id+"] - "+ ui.item.name + " ￥" + ui.item.listPrice;
				mockEle.children("input:last").val(ui.item.id);
		        return false;
		    }
		}).autocomplete( "instance" )._renderItem = function( ul, item ) {
        return $( "<li>" )
        	.append( "<div>[" + item.id + "]&nbsp;-&nbsp;" + item.name + "&nbsp;￥" +item.listPrice + "</div>" )
        	.appendTo( ul );
      };
      container.after(mockEle);
     });
     
     $(".remove-row").click(function(e){
    	 var container = $(this).parent().parent().parent();
    	 if(container.find(".input-box-daynamic").size() > 1) {
    		 $(this).parent().parent().detach();
    	 } else {
    		 return false;
    	 }
     });
     
     $("#couponType").change(function(e){
    	 $("#couponType > option").each(function() {
 			if($(this).prop("selected")) {
 				var val = $(this).val();
 				if(val == 1 || val == 2) {
 					if($("#conditionValue").val() == 0) {
 						$("#conditionValue").val("");
 					}
 				} else {
 					$("#conditionValue").val(0);
 				}
 			}
 		});
     });
     
     $("input[type=radio][name='itemLimitationType']").change(function(e){
    	 $("input[type=radio][name='itemLimitationType']").each(function(e){
  			var checked = $(this).prop("checked");
  			var tab = $(this).data("tab");
  			if(checked) {
  				$("#"+tab).show();
  			} else {
  				$("#"+tab).hide();
  			}
  		});
     });
     
     if($("#couponAvailableItemBox").length >0) {
    	 var autoinputbox = $("#couponAvailableItemBox");
    	 $("#couponAvailableItemBox").autocomplete({
			source: contextPath+"/items/autocomplete/search-items",
			minLength: 1,
			select: function( event, ui ) {
				this.value = "["+ ui.item.id+"] - "+ ui.item.name + " ￥" + ui.item.listPrice;
				autoinputbox.siblings(":last").val(ui.item.id);
		        return false;
		    }
		}).autocomplete( "instance" )._renderItem = function( ul, item ) {
        return $( "<li>" )
        	.append( "<div>[" + item.id + "]&nbsp;-&nbsp;" + item.name + "&nbsp;￥" +item.listPrice + "</div>" )
        	.appendTo( ul );
        };
     }
     
     $(".addNewCouponItemAutoInput").click(function(e){
    	var appendContainer = $(this).parent();
    	var newEle = $("#coupon-input-box-daynamic-mock").clone(true).removeAttr("id").removeAttr("style").removeClass("input-box-daynamic-mock").addClass("input-box-daynamic");
    	newEle.find("#counponAvailableItemId").attr("name", "counponAvailableItemId").removeAttr("id");
    	newEle.children(":first").autocomplete({
			source: contextPath+"/items/autocomplete/search-items",
			minLength: 1,
			select: function( event, ui ) {
				this.value = "["+ ui.item.id+"] - "+ ui.item.name + " ￥" + ui.item.listPrice;
				newEle.children(":last").val(ui.item.id);
		        return false;
		    }
		}).autocomplete( "instance" )._renderItem = function( ul, item ) {
        return $( "<li>" )
        	.append( "<div>[" + item.id + "]&nbsp;-&nbsp;" + item.name + "&nbsp;￥" +item.listPrice + "</div>" )
        	.appendTo( ul );
      };
    	appendContainer.after(newEle);
     });
     
     if($("#inventoryItemAutoInput").length >0) {
    	 var autoinputbox = $("#inventoryItemAutoInput");
    	 $("#inventoryItemAutoInput").autocomplete({
			source: contextPath+"/items/autocomplete/search-items",
			minLength: 1,
			select: function( event, ui ) {
				var unit = "";
				if(ui.item.specType == 1) {
					unit =  ui.item.unit.name;
				} else {
					unit = ui.item.chargeUnit + ui.item.unit.name;
				}
				this.value = "["+ ui.item.id+"] - "+ ui.item.name + " ￥" + ui.item.listPrice + '/' + unit;
				autoinputbox.parent().siblings(":last").val(ui.item.id);
				autoinputbox.parent().parent().find(".item-unit").text('('+unit+')');
		        return false;
		    }
		}).autocomplete( "instance" )._renderItem = function( ul, item ) {
	        var displayText = "";
			if(item.specType == 1) {
				displayText = "<div>[" + item.id + "]&nbsp;-&nbsp;" + item.name + "&nbsp;￥" +item.listPrice + '/' + item.unit.name + "</div>";
			} else {
				displayText = "<div>[" + item.id + "]&nbsp;-&nbsp;" + item.name + "&nbsp;￥" +item.listPrice + '/' + item.chargeUnit + item.unit.name + "</div>";
			}
			return $( "<li>" )
	    	.append(displayText)
	    	.appendTo( ul );
        };
     }
     
     $(".add-inventory-row").click(function(e){
    	var appendContainer = $(this).parent().parent();
    	var newEle = $("#inventory-mock-row").clone(true).removeAttr("id").removeAttr("style").removeClass("input-box-daynamic-mock").addClass("input-box-daynamic");
    	newEle.find("#itemId").attr("name", "itemId").removeAttr("id");
    	newEle.find("#itemQuantity").attr("name", "itemQuantity").removeAttr("id");
    	newEle.find(".auto-input").autocomplete({
			source: contextPath+"/items/autocomplete/search-items",
			minLength: 1,
			select: function( event, ui ) {
				var unit = "";
				if(ui.item.specType == 1) {
					unit = ui.item.unit.name;
				} else {
					unit = ui.item.chargeUnit + ui.item.unit.name;
				}
				this.value = "["+ ui.item.id+"] - "+ ui.item.name + " ￥" + ui.item.listPrice + '/'  + unit;
				newEle.find(".item-unit").text('('+unit+')');
				newEle.children(":last").val(ui.item.id);
		        return false;
		    }
		}).autocomplete( "instance" )._renderItem = function( ul, item ) {
    		var displayText = "";
    		if(item.specType == 1) {
    			displayText = "<div>[" + item.id + "]&nbsp;-&nbsp;" + item.name + "&nbsp;￥" +item.listPrice + '/' + item.unit.name + "</div>";
    		} else {
    			displayText = "<div>[" + item.id + "]&nbsp;-&nbsp;" + item.name + "&nbsp;￥" +item.listPrice + '/' + item.chargeUnit + item.unit.name + "</div>";
    		}
    		return $( "<li>" )
        	.append(displayText)
        	.appendTo( ul );
      };
    	appendContainer.after(newEle);
     });
     
     $("#sortableCategory").sortable({
         placeholder: "ui-state-highlight"
     });
     
     $(".sub-category-list").sortable({
         placeholder: "ui-state-highlight"
     });
     
//     $(".sub-recipes-list").each(function(){
//    	 $(this).sortable({
//             placeholder: "ui-state-highlight"
//         });
//     })
     
     
     
     $(".primary-check").change(function(){
    	 if($(this).prop("checked")) {
    		 $(this).siblings("input[name='primaryCategory']").val(1);
    	 } else {
    		 $(this).siblings("input[name='primaryCategory']").val(0);
    	 }
     });
     
     $(".top-category-item").click(function(e){
    	 // update active style
    	 $(".top-category-item").each(function(e){
    		 if($(this).hasClass("cat-active")) {
    			 $(this).removeClass("cat-active");
    		 }
    	 });
    	 $(this).addClass("cat-active");
    	 
    	 //display corresponding tab
    	 var toggletab = $(this).attr("data");
    	 $(".sub-category-panel").each(function(){
    		if($(this).attr("id") == toggletab) {
    			$(this).show();
    		} else {
    			$(this).hide();
    		}
    	 });
     });
     
     $(".add-new-link").click(function(e){
    	 var modal = $(this).siblings(".sub-category-modal").clone().removeClass("sub-category-modal");
    	 modal.find("#subCategoryName").attr("name", "subCategoryName").removeAttr("id");
    	 modal.find("#parentId").attr("name", "parentId").removeAttr("id");
    	 modal.find("#subCategoryId").attr("name", "subCategoryId").removeAttr("id");
    	 modal.find("#delete").click(function(e){
    		 modal.detach();
    	 });
    	 $(this).before(modal);
     });
     
     $(".add-image").click(function(e){
    	 var previewImage = $(this);
    	 $(this).siblings("input[type='file']").change(function(e){
    		var file = $(this)[0].files[0];
    		 
    		// check if file is a image
 			var fileType = file["type"];
 			var validImageTypes = ["image/jpg", "image/jpeg", "image/png", "image/gif"];
 			if ($.inArray(fileType, validImageTypes) < 0) {
 				$("#imageTypeError").show();
 				return false;
 			} else {
 				$("#imageTypeError").hide();
 			}
 			
 			//check file size
 			fileSize = (file.size/1024)/1000;
 			if(fileSize > 2) {
 				$("#imageSizeError").show();
 				return false;
 			} else {
 				$("#imageSizeError").hide();
 			}
 			
 			var objUrl = getObjectURL(file);
 			if(objUrl) {
 				previewImage.attr("src",objUrl);
 			}
    	 });
    	 
    	 $(this).siblings("input[type='file']").click();
     });
     
     $("#imageButton").click(function(e){
    	 var previewImage = $(".balance-add-image");
    	 $("#itemImage").change(function(e){
    		var file = $(this)[0].files[0];
    		 
    		// check if file is a image
 			var fileType = file["type"];
 			var validImageTypes = ["image/jpg", "image/jpeg", "image/png", "image/gif"];
 			if ($.inArray(fileType, validImageTypes) < 0) {
 				$("#imageTypeError").show();
 				return false;
 			} else {
 				$("#imageTypeError").hide();
 			}
 			
 			//check file size
 			fileSize = (file.size/1024)/1000;
 			if(fileSize > 2) {
 				$("#imageSizeError").show();
 				return false;
 			} else {
 				$("#imageSizeError").hide();
 			}
 			
 			var objUrl = getObjectURL(file);
 			if(objUrl) {
 				previewImage.attr("src",objUrl);
 			}
    	 });
    	 
    	 $("#itemImage").click();
     });
     //----------
     $("#imageButtonUrl").click(function(e){
    	 var previewImage = $(".url-add-image");
    	 $("#itemImageUrl").change(function(e){
    		var file = $(this)[0].files[0];
    		 
    		// check if file is a image
 			var fileType = file["type"];
 			var validImageTypes = ["image/jpg", "image/jpeg", "image/png", "image/gif"];
 			if ($.inArray(fileType, validImageTypes) < 0) {
 				$("#imageTypeError").show();
 				return false;
 			} else {
 				$("#imageTypeError").hide();
 			}
 			
 			//check file size
 			fileSize = (file.size/1024)/1000;
 			if(fileSize > 2) {
 				$("#imageSizeError").show();
 				return false;
 			} else {
 				$("#imageSizeError").hide();
 			}
 			
 			var objUrl = getObjectURL(file);
 			if(objUrl) {
 				previewImage.attr("src",objUrl);
 			}
    	 });
    	 
    	 $("#itemImageUrl").click();
     });
     
     $("#AdsImageButton").click(function(e){
    	 var previewImage = $(".ads-add-image");
    	 var curtainImage = $(".curtain-add-image");
    	 $("#adsiIemImage").change(function(e){
    		var file = $(this)[0].files[0];
    		 
    		// check if file is a image
 			var fileType = file["type"];
 			var validImageTypes = ["image/jpg", "image/jpeg", "image/png", "image/gif"];
 			if ($.inArray(fileType, validImageTypes) < 0) {
 				$("#imageTypeError").show();
 				return false;
 			} else {
 				$("#imageTypeError").hide();
 			}
 			
 			//check file size
 			fileSize = (file.size/1024)/1000;
 			if(fileSize > 2) {
 				$("#imageSizeError").show();
 				return false;
 			} else {
 				$("#imageSizeError").hide();
 			}
 			
 			var objUrl = getObjectURL(file);
 			if(objUrl) {
 				previewImage.attr("src",objUrl);
 				curtainImage.attr("src",objUrl);
 			}
    	 });
    	 
    	 $("#adsiIemImage").click();
     });
     
     $("#addAPP").click(function(e){
    	 var previewImage = $(".app-add-image");
    	 $("#addApk").change(function(e){
    		var file = $(this)[0].files[0];
    		 
    		// check if file is a image
 			var fileType = file["type"];
 			var validImageTypes = ["application/vnd.android.package-archive"];
 			if ($.inArray(fileType, validImageTypes) < 0) {
 				alert("文件类型有误，请重新选择!")
 				return false;
 			}
 			
 			
 			var objUrl = getObjectURL(file);
 			if(objUrl) {
 				//download = [[@{'/resources/images/download_info.png'}]];
 				//获取文件的value值
 	            file = $("#addApk").val()
 	            //获取文件名+扩展名
 	            fileName = file.split("\\").pop();
 	            //获取文件名
 	            fileName = fileName.substring(0, fileName.lastIndexOf("."));
 	            //获取文件的扩展名
 	            fileExt = file.substr(file.lastIndexOf("."));
 	            //清空DIV容器内容
 	           $("#appName").text(fileName+fileExt)
 	            $("#name").val(fileName+fileExt)
 	           $("#appPic").attr("src",getRootPath()+"/resources/images/ic-logo.android.png")
 			}
    	 });
    	 
    	 $("#addApk").click();
     });
     
     $("#promImageButton").click(function(e){
    	 var previewImage = $(".prom-add-image");
    	 var curtainImage = $(".curtain-add-image");
    	 $("#itemImage").change(function(e){
    		var file = $(this)[0].files[0];
    		 
    		// check if file is a image
 			var fileType = file["type"];
 			var validImageTypes = ["image/jpg", "image/jpeg", "image/png", "image/gif"];
 			if ($.inArray(fileType, validImageTypes) < 0) {
 				$("#imageTypeError").show();
 				return false;
 			} else {
 				$("#imageTypeError").hide();
 			}
 			
 			//check file size
 			fileSize = (file.size/1024)/1000;
 			if(fileSize > 2) {
 				$("#imageSizeError").show();
 				return false;
 			} else {
 				$("#imageSizeError").hide();
 			}
 			
 			var objUrl = getObjectURL(file);
 			if(objUrl) {
 				previewImage.attr("src",objUrl);
 				curtainImage.attr("src",objUrl);
 			}
    	 });
    	 
    	 $("#itemImage").click();
     });
     
     $(".add-third-class-link").click(function(e){
    	 var modal = $(this).siblings(".third-class-category-modal").clone(true).removeClass("third-class-category-modal").removeAttr("style");
    	 modal.find("#thirdClassCategoryName").attr("name", "thirdClassCategoryName").removeAttr("id");
    	 modal.find("#subCategoryParentId").attr("name", "subCategoryParentId").removeAttr("id");
    	 modal.find("#thridClassCategoryId").attr("name", "thridClassCategoryId").removeAttr("id");
    	 modal.find("#delete").click(function(e){
    		 modal.detach();
    	 });
    	 $(this).before(modal);
     });
     
     $( "#category-menu" ).menu({
    	 select: function(event, ui) {
    		 var $checkbox = $(event.currentTarget).children().children("input[name='itemCategoryId']");
    		 //console.log($checkbox)
    		/* if($checkbox) {
    			 $checkbox.trigger("click");
    		 }*/
    	 }
     });
    
     $("input[name='itemCategoryId']").change(function(e){
    	 var val = $("#selectedCategory").text();
    	 var selectedVal = $(this).attr("data");
    	 if($(this).prop("checked")) {
    		 if(val == "") {
    			 $("#selectedCategory").text(selectedVal); 
    		 } else {
    			 $("#selectedCategory").text(val + ","+ selectedVal);
    		 }
    	 } else {
    		 if(val.includes(","+selectedVal)) {
    			 val = val.replace(","+selectedVal,"");
    		 } else if(val.includes(selectedVal+",")) {
    			 val = val.replace(selectedVal+",","");
    		 } else {
    			 val = val.replace(selectedVal,"");
    		 }
    		 $("#selectedCategory").text(val);
    	 }
     });
     $(".select-box").click(function(e){
    	 e.stopPropagation();
    	 if($("#category-menu").is(":visible")) {
    		 $("#category-menu").hide();
    		 $(this).find("i").addClass("lnr-chevron-down").removeClass("lnr-chevron-up");
    	 } else {
    		 $("#category-menu").show();
    		 $(this).find("i").addClass("lnr-chevron-up").removeClass("lnr-chevron-down");
    	 }
     });
     $("#category-menu").blur(function(){
			$("#category-menu").css("display","none")
	  })
     $(document).click(function(e){
//    	 if($("#category-menu").is(":visible")) {	
//    		 $("#category-menu").hide();
//    		 $(".select-box").find("i").addClass("lnr-chevron-down").removeClass("lnr-chevron-up");
//    	 } 
     });
     
     $('select[multiple]').multiselect({ search   : true,
    	    selectAll: true, texts: { placeholder: '选择仓库',search: '搜索', selectAll: '全选', unselectAll: '取消全选' } });
     
     $("tr[data]").click(function(e){
    	 var url = $(this).attr("data");
    	 window.location.href = url;
     });
     
     if($("#itemAutoInputBox").length >0) {
    	 var autoinputbox = $("#itemAutoInputBox");
    	 autoinputbox.autocomplete({
			source: contextPath+"/items/autocomplete/search-items",
			minLength: 1,
			select: function( event, ui ) {
				var duplicated = false; 
				$("input[name='itemId']").each(function(e){
					if($(this).val() == ui.item.id) {
						duplicated = true;
					}
				})

				if(!duplicated) {
					this.value = "["+ ui.item.id+"] - "+ ui.item.name + " ￥" + ui.item.listPrice;
					autoinputbox.siblings(":last").val(ui.item.id);
				} else {
					alert("该商品已选择过，请重新选择。");
				}
				
		        return false;
		    }
		}).autocomplete( "instance" )._renderItem = function( ul, item ) {
        return $( "<li>" )
        	.append( "<div>[" + item.id + "]&nbsp;-&nbsp;" + item.name + "&nbsp;￥" +item.listPrice + "</div>" )
        	.appendTo( ul );
        };
     }
     
     $(".addAutoInputBtn").click(function(e){
    	var appendContainer = $(this).parent();
    	var newEle = $("#item-auto-inputbox-mock").clone(true).removeAttr("id").removeAttr("style").removeClass("input-box-daynamic-mock").addClass("input-box-daynamic");
    	newEle.find("#itemId").attr("name", "itemId").removeAttr("id");
    	newEle.children(":first").autocomplete({
			source: contextPath+"/items/autocomplete/search-items",
			minLength: 1,
			select: function( event, ui ) {
				var duplicated = false; 
				$("input[name='itemId']").each(function(e){
					if($(this).val() == ui.item.id) {
						duplicated = true;
					}
				})

				if(!duplicated) {
					newEle.children(":last").val(ui.item.id);
					this.value = "["+ ui.item.id+"] - "+ ui.item.name + " ￥" + ui.item.listPrice;
				} else {
					alert("该商品已选择过，请重新选择。");
				}
				
		        return false;
		    }
		}).autocomplete( "instance" )._renderItem = function( ul, item ) {
			return $( "<li>" )
        	.append( "<div>[" + item.id + "]&nbsp;-&nbsp;" + item.name + "&nbsp;￥" +item.listPrice + "</div>" )
        	.appendTo( ul );
        
      };
    	appendContainer.after(newEle);
     });
     
     //菜谱添加
     $(".addAutoInputRecipesBtn").click(function(e){
     	var appendContainer = $(this).parent().parent();
     	var newEle = $("#item-auto-inputbox-mock").clone(true).removeAttr("id").removeAttr("style").removeClass("input-box-daynamic-mock").addClass("input-box-daynamic");
     	appendContainer.after(newEle);
      });
     
     
     $("input[name='pageType']").change(function(e){
    	 if($(this).val() == 1) {
    		 $("#miniprogram-page").show();
    		 $("#webpageurl").hide();
    	 } else {
    		 $("#miniprogram-page").hide();
    		 $("#webpageurl").show();
    	 }
     });
     
     $(".promotion-stat").click(function(e){
    	 e.stopPropagation();
    	 var promotionId = $(this).attr("data");
    	 var stat = $(this).data("stat");
        	 $.ajax({
          		  type: "POST",
          		  url: contextPath + "/campaign/promotion/status-update",
          		  data: {promotionId: promotionId, stat: stat},
          		  success: function() {
          			  location.reload();
          		  }
          		});
     });
     
     $("#assignDelivery").click(function(){
    	 var packageId = $(this).attr("name");
    	 $.ajax({
      		  url: contextPath + "/orders/delivery/suggestions",
      		  data: {packageId: packageId},
      		  success: function(res) {
      			$("#delivery-table tbody").empty();
      			for(var i=0; i<res.length; i++) {
      				var staff = res[i];
      				var distance = staff.shortestDistance > 0 ? (staff.shortestDistance/1000) + '公里' : '--';
      				var routeLink = staff.assignedOrderCount > 0 ? "<a href='javascript:void(0)'>查看</a>" : "无订单";
      				$("#delivery-table tbody").append("<tr><td>"+staff.name+"</td><td>"+staff.phone+"</td><td>"+staff.assignedOrderCount+"件</td><td>"+distance+"</td><td>"+
      						routeLink+"</td><td><a class='assignlink' href='javascript:void(0)' onclick='assignOrder("+packageId+","+staff.id+",\""+contextPath+"\")'>派单</a></td></tr>");
      			}
      			
      			$("#dialog").dialog({
   	            modal: true,
   	            autoOpen: true,
   	            title: "分派配送员",
   	            width: '80%',
   	            height: 500,
   	            buttons: [
   	            {
   	                id: "n",
   	                text: "取消",
   	                click: function () {
   	                    $(this).dialog('close');
   	                }
   	            }
   	            ]
   	        });
      		  },
      		  fail: function(res) {
      			  alert("获取配送员状态失败，请刷新页面重新尝试。");
      		  }
      		});
     });
     $(".process-state").click(function(){
    	 var id=$(this).attr("data");
    	 zeroModal.confirm({
    	        title: "提示",
    	        content:"是否已经核实并送达？",
    	        width: '300px',
    	        height: '260px',
    	        overlay: false,
    	        ok: true,
    	        okFn :function() {
    	        	var url=getRootPath();
    	        	$.ajax({
    	                url : url+"/orders/deliverying/state",
    	                type : "POST",
    	               	data : {
    	               		'id':id
    	               	},success : function(data) {
    	               		location.reload();
    	               	}
    	           })
    	        }
    	    });
    })
     $("#completePacking").click(function(){
		var packageId = $(this).attr("name");
		// check order items
		// 1) load items, open it in a pop-up modal window
		// 2) input actual weight and calculate refund money
		// 3) after all items are checked, then can go
		$.ajax({
   		  url: contextPath + "/orders/package/items",
   		  data: {packageId: packageId},
   		  success: function(res) {
   			$("#order-item-table tbody").empty();
   			for(var i=0; i<res.length; i++) {
   				var item = res[i];
   				var quantity = "";
   				if(item.specType == 1) {  // 标品
   					quantity = item.quantity + '' + item.unit.name;
   				} else {
   					quantity = (item.quantity * item.chargeUnit) + '' + item.unit.name;
   				}
   				var imageUrl = 'https://images.fline.com'+item.imagePath;
   				$("#order-item-table tbody").append("<tr><td><img style='width:70px' src='"+imageUrl+"'/></td><td>"+item.id+''+"</td><td>"+item.barcode+"</td><td>"+item.name+"</td><td>"+item.spec+"</td><td>"+quantity+"</td><td>"+item.actualPayed+"</td></tr>");
   			}
   			$("#dialog").dialog({
	            modal: true,
	            autoOpen: true,
	            title: "核验订单内容",
	            width: '80%',
	            height: 500,
	            buttons: [
	            {
	                id: "y",
	                text: "确认",
	                click: function () {
	                	$.ajax({
	                	  method: "POST",
	              		  url: contextPath + "/orders/packing/complete",
	              		  data: {packageId: packageId},
	              		  complete: function(res) {
	              			  alert("分拣完成")
	              			$("#dialog").dialog('close');
              				location.reload();
	              		  },
	              		  fail: function(res) {
	              			alert("系统错误，请重新尝试");
	              			$("#dialog").dialog('close');
	              		  }
	              		});
	                }
	            },
	            {
	                id: "n",
	                text: "取消",
	                click: function () {
	                    $(this).dialog('close');
	                }
	            }
	            ]
	        });
   		  },
   		  fail: function(res) {
   			  alert("获取订单详情失败，请刷新页面重新尝试。");
   		  }
   		});
	});
     
     $("#refundLink").click(function(e) {
			var rfid = $("#refundLink").attr('name');
			$("#dialog").dialog({
	            modal: true,
	            autoOpen: true,
	            title: "请确认",
	            width: 350,
	            height: 160,
	            buttons: [
	            {
	                id: "y",
	                text: "确认",
	                click: function () {
	                	$.ajax({
	              		  type: "POST",
	              		  url: contextPath + "/orders/refund/process",
	              		  data: {refundId: rfid},
	              		  complete: function(res) {
	              			  $("#dialog").dialog('close');
	              			  location.reload();
	              		  }
	              		});
	                }
	            },
	            {
	                id: "n",
	                text: "取消",
	                click: function () {
	                    $(this).dialog('close');
	                }
	            }
	            ]
	        });
		});
     
     $("#timeSlotGen").click(function(e){
    	 var start = $("#settingStart").val();
    	 var startInt = convertTime(start);
    	 var end = $("#settingEnd").val();
    	 var endInt = convertTime(end);
    	 
    	 // remove
    	 $("#slot-list").empty();
    	 
    	 // add new
    	 var slots = (endInt - startInt) * 2;
    	 for(var i=0; i<slots; i++) {
    		 var slotStart = convertToTimeSlot(startInt);
    		 startInt += 0.5;
 			 var slotEnd = convertToTimeSlot(startInt);
    		 
 			 var mock = $("#tsMock").clone(true);
 			 mock.removeAttr("id").removeAttr("style");
 			 mock.find("#timeSlotStart").attr("id", "timeSlotStart"+i).val(slotStart);
 			 mock.find("#timeSlotEnd").attr("id", "timeSlotEnd"+i).val(slotEnd);
 			 mock.find(".sub-group").each(function(){
 				var $ck = $(this).find("input:checkbox");
 				$ck.attr("id",$ck.attr("name")+i);
 				$ck.removeAttr("name");
 				$ck.click(function(){
 					if($(this).is(':checked')) {
 						$(this).siblings("input:hidden").val(1);
 					} else {
 						$(this).siblings("input:hidden").val(0);
 					}
 				});
 				$(this).find("label").attr("for",$ck.attr("id"));
 			 });
 			 $("#slot-list").append(mock);
    	 }
     });
     
     $(".time-slot-check").each(function(){
    	 $(this).click(function(){
    		 if($(this).is(':checked')) {
 				$(this).siblings("input:hidden").val(1);
 			} else {
 				$(this).siblings("input:hidden").val(0);
 			}
    	 });
     });
     
     function convertTime(time) {
    	 var hh = time.split(":")[0];
    	 var mm = time.split(":")[1];
    	 if(mm == '30') {
    		 return parseInt(hh)+0.5;
    	 } else {
    		 return parseInt(hh);
    	 }
     }
     
     function convertToTimeSlot(val) {
    	var slotStr = val.toString();
 		var fractionIndex = slotStr.indexOf(".5");
 		if(fractionIndex>0) {
 			return (parseInt(slotStr.substring(0, slotStr.indexOf("."))) >= 10 ? slotStr.substring(0, slotStr.indexOf(".")) : "0" + slotStr.substring(0, slotStr.indexOf("."))) +":"+"30";
 		} else {
 			return (parseInt(slotStr) >= 10 ? slotStr : "0" + slotStr)  + ":"+"00";
 		}
     }
     
     // 以下是营销工具相关方法
     
     // 添加falshSale 开始活动和停止活动事件
     $(".startActivityLinkButton").each(function(e){
    	 $(this).click(function(e){
        	 e.preventDefault();
        	 var startActivityUrl = $(this).attr("data");
        	 var r = confirm("确定开始活动?");
        	 if (r == true) {
        		 location.href = startActivityUrl;
        		 return false;
        	 } else {
        		 return false;
        	 }
         });
     })
     
     $(".stopActivityLinkButton").each(function(e){
    	 $(this).click(function(e){
        	 e.preventDefault();
        	 var stopActivityUrl = $(this).attr("data");
        	 var r = confirm("确定结束活动?");
        	 if (r == true) {
        		 location.href = stopActivityUrl;
        		 return false;
        	 } else {
        		 return false;
        	 }
         });
     })
     
     $(".deleteActivityLinkButton").each(function(e){
    	 $(this).click(function(e){
        	 e.preventDefault();
        	 var stopActivityUrl = $(this).attr("data");
        	 var r = confirm("确定删除活动?");
        	 if (r == true) {
        		 location.href = stopActivityUrl;
        		 return false;
        	 } else {
        		 return false;
        	 }
         });
     })
      $(".deleteAccountLinkButton").each(function(e){
    	 $(this).click(function(e){
        	 e.preventDefault();
        	 var stopActivityUrl = $(this).attr("data");
        	 var r = confirm("确定删除账号?");
        	 if (r == true) {
        		 location.href = stopActivityUrl;
        		 return false;
        	 } else {
        		 return false;
        	 }
         });
     })
     $(".deleteNoNumActivityLinkButton").each(function(e){
    	 $(this).click(function(e){
        	 e.preventDefault();
        	 var stopActivityUrl = $(this).attr("data");
        	 var r = confirm("确定删除活动?");
        	 if (r == true) {
        		 location.href = stopActivityUrl;
        		 return false;
        	 } else {
        		 return false;
        	 }
         });
     })
     
     $(".deletePubItemButton").each(function(index,element){
    	 $(this).click(function(e){
        	 var deletePubItemUrl = $(this).attr("data");
        	 var r = confirm("确定删除商品?");
        	 if (r == true) {
        		 location.href = deletePubItemUrl;
        		 return false;
        	 } else {
        		 return false;
        	 }
         });
     })
    //供应商
     $(".auditButton").each(function(index,element){
    	 $(this).click(function(e){
    		 var id = $(this).attr("data");
    		 $("#supplierId").val(id)
         });
     })
     
});

function assignOrder(pkgId, staffId, contextPath) {
	$.ajax({
		  url: contextPath + "/orders/delivery/assign/"+pkgId,
		  data: {deliveryStaffId: staffId},
		  complete: function(res) {
			  $("#dialog").dialog('close');
			  location.reload();
		  }
	});
}
//空之境界
function verification(id){
	if($("#"+id).val()==''|| $("#"+id).val() <= 0){
		$("#"+id).addClass("input-error");
		return true;
	}else{
		$("#"+id).removeClass("input-error");
		return false;
	}
}
function verificationNull(id){
	if($("#"+id).val()==''|| $("#"+id).val() ==null){
		$("#"+id).addClass("input-error");
		return true;
	}else{
		$("#"+id).removeClass("input-error");
		return false;
	}
}
function verificationObj(Obj){
	if($(Obj).val()==''|| $(Obj).val() ==null|| parseFloat($(Obj).val()) <= 0){
		$(Obj).addClass("input-error");
		return true;
	}else{
		$(Obj).removeClass("input-error");
		return false;
	}
}
function verificationObjNull(Obj){
	if($(Obj).val()==''|| $(Obj).val() ==null){
		$(Obj).addClass("input-error");
		return true;
	}else{
		$(Obj).removeClass("input-error");
		return false;
	}
}
function calendarChange(type,month,dataDay){
	var calendarMonth = [];
	var optionMonth = '';
	var optionDay = '';
	var calendarDay = [];
	if(type==1){
		calendarMonth = ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
		var count =	[31,0,31,30,31,30,31,31,30,31,30,31];
		var year = new Date().getFullYear(); 
		count[1] = year % 4 == 0?29:28;
		var len=0;
		if(month=='0'){
			len=count[0];
			for(var i = 0;i < calendarMonth.length;i++){
				optionMonth += '<option value="'+calendarMonth[i]+'">'+calendarMonth[i]+'</option>';
			}
		}else{
			for(var i = 0;i < calendarMonth.length;i++){
				
				if(calendarMonth[i]==month){
					optionMonth += '<option selected value="'+calendarMonth[i]+'">'+calendarMonth[i]+'</option>';
				}else{
					optionMonth += '<option value="'+calendarMonth[i]+'">'+calendarMonth[i]+'</option>';
				}
			}
			for(var i = 0;i < calendarMonth.length;i++){
				if(calendarMonth[i].indexOf(month) != -1){
					len=count[i];
					break;
				}
			}
		}
		
		$("#calendarMonth").html(optionMonth);//日期初始化
		for(var i = 0;i < len;i++){
			var day=(i+1)+"日";
			
			if(dataDay==day){
				optionDay += '<option selected value="'+day+'">'+day+'</option>';
			}else{
				optionDay += '<option value="'+day+'">'+day+'</option>';
			}
			
		}
		$("#calendarDay").html(optionDay);//天数初始化
	}else{
		calendarMonth = ['正月','二月','三月','四月','五月','六月','七月','八月','九月','十月','冬月','腊月']
		calendarDay = ['初一','初二','初三','初四','初五','初六','初七','初八','初九','初十',
			'十一','十二','十三','十四','十五','十六','十七','十八','十九','廿十',
			'廿一','廿二','廿三','廿四','廿五','廿六','廿七','廿八','廿九','三十'];
		for(var i = 0;i < calendarMonth.length;i++){
			if(calendarMonth[i]==month){
				optionMonth += '<option selected value="'+calendarMonth[i]+'">'+calendarMonth[i]+'</option>';
			}else{
				optionMonth += '<option value="'+calendarMonth[i]+'">'+calendarMonth[i]+'</option>';
			}
		}
		$("#calendarMonth").html(optionMonth);//日期初始化
		for(var i = 0;i < calendarDay.length;i++){
			if(calendarDay[i]==dataDay){
				optionDay += '<option selected value="'+calendarDay[i]+'">'+calendarDay[i]+'</option>';
			}else{
				optionDay += '<option value="'+calendarDay[i]+'">'+calendarDay[i]+'</option>';
			}
		}
		$("#calendarDay").html(optionDay);//天数初始化
		
	}
}
//模态框加载优惠券 初始数据
function loadCoupons(currentPage){
	var url=window.location.href.split("?")[0];
	 $("#queryCoupons").html('');
	 $.ajax({
            url : url+"/coupons/search",
            type : "POST",
           	data : {
           		'keyWords':$("#keyWords").val(),
           		'currentPage':currentPage,
           	},
           	success : function(data) {
				data=eval('(' + data + ')');
				$.each(data.couponList, function(i, item){//th:value="${coupon.id}
				    var checkBoxHtml = '<input id="'+item.id+'" type="checkbox" name="couponName" data="'+item.title+'" value="'+item.id+'"/>';
				    var inputBoxHtml = '<input disabled class="col-sm-3" name="couponCount" type="text" value=""/>';
				    var type='';
						if(item.type==1){
							type="满减";
						}else if(item.type==2){
							type="满折";
						}else if(item.type==3){
							type="立减";
						}else if(item.type==4){
							type="立折";
						}
						var isMore=false;
						if(coupons.length > 0 && coupons[0]!=null){
							$.each(coupons,function(i,val){
								var coupontCount = $("#"+val).val().split('*')[1];
								if(val.indexOf(item.id) != -1){
									checkBoxHtml = '<input id="'+item.id+'" checked="checked" type="checkbox" name="couponName" data="'+item.title+'" value="'+item.id+'"/>';
						    		inputBoxHtml = '<input class="col-sm-3" name="couponCount" type="text" value="'+coupontCount+'"/>';
						    		if(item.maxReceiveNum < couCount[i] && item.maxReceiveNum > 0) {
						    			isMore = true;
									} 
								}
							})
						}
					var expDate='';
					if(item.expDate!='' && item.expDate!=null){
						expDate=new Date(item.expDate).Format("yyyy-MM-dd HH:mm:ss");
					}
					var maxrnm = "";
					var appendHtml='<span style="margin-left:5px;font-size:12px;color:#838383">发放数量不可大于限领数</span>';
					if(item.maxReceiveNum > 0) {
						maxrnm = item.maxReceiveNum+"张";
					} else {
						maxrnm = "/";
					}
				    var row = $('<tr style="cursor:default"><td>'+checkBoxHtml+'</td><td ><label style="cursor:pointer">'+item.title+'</lable></td>'+
					'<td>'+type+'</td>'+
					'<td>'+expDate+'</td>'+
					'<td>'+maxrnm+'<input type="hidden" value="'+item.maxReceiveNum+'"></td>'+
					'<td width="30%">'+inputBoxHtml+'</td></tr>');
				    if(isMore){
				    	row.find("input[type='text']").addClass('input-error');
				    	row.find("input[type='text']").parent().append(appendHtml);
				    }
				    row.find("input[type='text']").click(function(e){
				    	e.preventDefault();
				    	return false;
				    });
				    row.find("input[type='checkbox']").click(function(e){
						if($(this).is(':checked')) {
							row.find("input[type='text']").attr('disabled',false);
						} else {
							row.find("input[type='text']").attr('disabled',true);
							row.find("input[type='text']").val('');
							row.find("input[type='text']").removeClass('input-error');
							row.find("span").remove();
						}
					});
				    row.find("label").click(function(e){
						var ck = $(this).parent().parent().find("input[type='checkbox']");
						ck.prop("checked",!ck.is(':checked'));
						if(ck.is(':checked')) {
							row.find("input[type='text']").attr('disabled',false);
						} else {
							row.find("input[type='text']").attr('disabled',true);
							row.find("input[type='text']").val('');
							row.find("input[type='text']").removeClass('input-error');
							row.find("span").remove();
						}
					});
				    
				    $("#queryCoupons").append(row);
				    row.find("input[type='text']").change(function(e){
				    	var maxRecieveNum = $(this).parent().parent().find("input[type='hidden']").val();
				    	if(parseInt($(this).val()) > maxRecieveNum && maxRecieveNum >0) {
				    		$(this).parent().find('span').remove();
				    		$(this).addClass('input-error');
				    		$(this).parent().append(appendHtml);
				    	} else {
				    		$(this).parent().find('span').remove();
				    		$(this).removeClass('input-error');
				    	}
				    });
				})
				limitPage(data.currentPage,data.total,1)
           }
      });
}
//将所选优惠券加入隐藏区域  点击分页或 点击模态框确定按钮执行
function addCoupons(){
	$("#couponItems").html('');
	var tagHtml = $("#couponItems");
	var inputHtml = $("<div>");
	var arrItem=[];
	$("input[name='couponName']").each(function(){
		 var item=$(this).val();
		 var title=$(this).attr("data");
		 var index=coupons.indexOf(item);
		 var tlt =titles.indexOf(title);
		 var row=$(this).parent("td").parent("tr");
		 var couponCount=row.find("[name='couponCount']").val();//注意html()和val()
		 var maxNum=row.find("input[type='hidden']").val();//注意html()和val()
		 
		 if($(this).prop('checked')){
			 if(index > -1){
				 coupons.splice(index,1);
				 titles.splice(index,1);
				 couCount.splice(index,1);
				 cupMax.splice(index,1);
			 }
			/* if(tlt > -1){
				 titles.splice(index,1);
				 couCount.splice(index,1);
				 cupMax.splice(index,1);
				 //titles.splice(index,1);
			 }*/
			 
			 arrItem.push(item);
			 titles.push(title);
			 couCount.push(couponCount);
			 cupMax.push(maxNum);
		 }
	}); 
	$.each(arrItem,function(i,item){
		coupons.push(item)
	})
	 var result=0;
	 $("#couponsContainer").html(inputHtml);
	 $.each(couCount,function(i,item){
		    // 输入值验证
		 	if(item =='' || item ==null){
		 		result=1;
		 	}
		 	if(item <= 0){
		 		result=3;
		 	}
		 	// 与max值比较
		 	if(item > parseInt(cupMax[i]) && parseInt(cupMax[i]) > 0){
		 		result=2;
		 	}
		 	if(result!=0){
		 		if(coupons.indexOf(coupons[i]) > -1){
					coupons.splice(i,1);
					titles.splice(i,1);
					couCount.splice(i,1);
					cupMax.splice(i,1);
				}
		 		return false;
		 	}
	})
	if(result==1){
		alert("发放数量不能为空")
	}
	if(result==2){
		alert("发放数量已超过最大限额")
	}
	if(result==3){
		alert("发放数量必须大于0")
	}
	 if(result==0){
	 $.each(coupons,function(i,item){
		 	 var coupon=$("#"+item);
			 var couponId = item;
			 var couponCount=couCount[i];
			 var hiddenField = $("<input>").attr({
				 'type': 'hidden',
				 'id': couponId,
				 'data':titles[i],
				 'name': 'CouponId',
				 'value': couponId+'*'+couponCount
			 });
			
				 inputHtml.append(hiddenField);//隐藏id
				// create tags
				 var tag = $("<div class='couponTag'>").text(titles[i]+"("+couponCount+")");
				 var img = $("<img class='coupon-tag-close'>").attr('src',closeImgPath).attr('data',couponId);
				 img.click(function(e){
					 inputHtml.children("#"+couponId).detach();
					 var res = coupons.indexOf(couponId);
					/* $.each(coupons,function(i,item){
							if(coupons.indexOf(couponId) > -1){
								coupons.splice(i,1);
								titles.splice(i,1);
								couCount.splice(i,1);
								cupMax.splice(i,1);
							}
						})*/
					 if(res > -1){
							coupons.splice(res,1);
							titles.splice(res,1);
							couCount.splice(res,1);
							cupMax.splice(res,1);
						}
					 $(this).parent().detach();
				 })
				 tag.append(img);
				 tagHtml.append(tag);
			
	});
}
	return result;
}

function confirmSelectedCoupons() {//点击模态框  确定按钮执行 事件
	 /*$.each(coupons,function(i,item){
		 
	 })*/
	var res= addCoupons();
	if(res!=0){
		return false;
	}
	 $('#myModal').modal('hide');
}
//模态框 分页
function limitPage(page,total,type){
	if(type==1){
		$("#myPage").sPage({
		    page:page,//当前页码，必填 写死1则选中光标一直在1
		    total:total,//数据总条数，必填
		    pageSize:5,//每页显示多少条数据，默认10条
		    totalTxt:"共{total}条",//数据总条数文字描述，{total}为占位符，默认"共{total}条"
		    showTotal:false,//是否显示总条数，默认关闭：false
		    showSkip:true,//是否显示跳页，默认关闭：false
		    showPN:true,//是否显示上下翻页，默认开启：true
		    prevPage:"上一页",//上翻页文字描述，默认“上一页”
		    nextPage:"下一页",//下翻页文字描述，默认“下一页”
		    backFun:function(page){
		        //点击分页按钮回调函数，返回当前页码
		    	addCoupons();
		        loadCoupons(page);
		    }
		});
	}
	if(type==2){
		$("#cusPage").sPage({
		    page:page,//当前页码，必填 写死1则选中光标一直在1
		    total:total,//数据总条数，必填
		    pageSize:5,//每页显示多少条数据，默认10条
		    totalTxt:"共{total}条",//数据总条数文字描述，{total}为占位符，默认"共{total}条"
		    showTotal:false,//是否显示总条数，默认关闭：false
		    showSkip:true,//是否显示跳页，默认关闭：false
		    showPN:true,//是否显示上下翻页，默认开启：true
		    prevPage:"上一页",//上翻页文字描述，默认“上一页”
		    nextPage:"下一页",//下翻页文字描述，默认“下一页”
		    backFun:function(page){
		        //点击分页按钮回调函数，返回当前页码
		    	addCustomers();
		        loadCustomers(page)
		    }
		});
	}
	if(type==3){
		$("#itemPage").sPage({
		    page:page,//当前页码，必填 写死1则选中光标一直在1
		    total:total,//数据总条数，必填
		    pageSize:5,//每页显示多少条数据，默认10条
		    totalTxt:"共{total}条",//数据总条数文字描述，{total}为占位符，默认"共{total}条"
		    showTotal:false,//是否显示总条数，默认关闭：false
		    showSkip:true,//是否显示跳页，默认关闭：false
		    showPN:true,//是否显示上下翻页，默认开启：true
		    prevPage:"上一页",//上翻页文字描述，默认“上一页”
		    nextPage:"下一页",//下翻页文字描述，默认“下一页”
		    backFun:function(page){
		        //点击分页按钮回调函数，返回当前页码
		    	addItems()
		    	loadItems(page)
		    }
		});
	}
	if(type==4){
		$("#myPage").sPage({
		    page:page,//当前页码，必填 写死1则选中光标一直在1
		    total:total,//数据总条数，必填
		    pageSize:5,//每页显示多少条数据，默认10条
		    totalTxt:"共{total}条",//数据总条数文字描述，{total}为占位符，默认"共{total}条"
		    showTotal:false,//是否显示总条数，默认关闭：false
		    showSkip:true,//是否显示跳页，默认关闭：false
		    showPN:true,//是否显示上下翻页，默认开启：true
		    prevPage:"上一页",//上翻页文字描述，默认“上一页”
		    nextPage:"下一页",//下翻页文字描述，默认“下一页”
		    backFun:function(page){
		        //点击分页按钮回调函数，返回当前页码
		        couponType = $("#couponType").val();
		        addInvitCoupons();
		        invitLoadCoupons(couponType,page);
		    }
		});
	}
	if(type==5){
		$("#cusPage").sPage({
		    page:page,//当前页码，必填 写死1则选中光标一直在1
		    total:total,//数据总条数，必填
		    pageSize:5,//每页显示多少条数据，默认10条
		    totalTxt:"共{total}条",//数据总条数文字描述，{total}为占位符，默认"共{total}条"
		    showTotal:false,//是否显示总条数，默认关闭：false
		    showSkip:true,//是否显示跳页，默认关闭：false
		    showPN:true,//是否显示上下翻页，默认开启：true
		    prevPage:"上一页",//上翻页文字描述，默认“上一页”
		    nextPage:"下一页",//下翻页文字描述，默认“下一页”
		    backFun:function(page){
		        //点击分页按钮回调函数，返回当前页码
		    	addDistributeCustomers();
		    	loadDistributeCoupons(page)
		    }
		});
	}
}

//客户模态框 加载初始数据
function loadCustomers(currentPage){
	 $("#queryCustomers").html('');
	var url=window.location.href.split("?")[0];
	$.ajax({
		url:url+"/customers/search",
		type:"POST",
		data:{
			'keyWords':$("#cusKeyWords").val(),
			'currentPage':currentPage
		},success : function(data) {
			data=eval('(' + data + ')');
			$.each(data.customerList, function(i, item){//th:value="${coupon.id}
			var checkBoxHtml = '<input type="checkbox" name="customerName"  value="'+item.id+'"/>';
			if(customers.length > 0){
				$.each(customers,function(i,val){
					if(item.id==val){
						checkBoxHtml = '<input type="checkbox" checked name="customerName"  value="'+item.id+'"/>';
					}
				})
			}
			var memberType=item.memberType==0?'否':'是';
			var vipLevel='';
			if(item.vipLevel==1){
				vipLevel='普通会员';
			}else if(item.vipLevel==2){
				vipLevel='金卡会员';
			}else if(item.vipLevel==3){
				vipLevel='钻石会员';
			}
			var row = $('<tr><td>'+checkBoxHtml+'</td><td>'+item.id+'</td>'+
			'<td>'+item.phone+'</td>'+
			'<td>'+memberType+'</td>'+
			'<td>'+vipLevel+'</td></tr>');
		    row.click(function(e){
				var ck = $(this).find("input[type='checkbox']");
				ck.prop("checked",!ck.is(':checked'));
			}); 
		    $("#queryCustomers").append(row);
		})
		limitPage(data.cusCurrentPage,data.cusTotal,2)	
	}
  })
}
function addCustomers(){//添加客户 到 隐藏区域
	var tagHtml = $("<div>").attr('style','display: flex;flex-direction: row;align-items: center;');
	var inputHtml = $("<div>");
	var arrItem=[];
	$("input[name='customerName']").each(function(){
		 var item=$(this).val();
		 var index=customers.indexOf(item);
		 if(index > -1){
			 customers.splice(index,1);
		 }
		 if($(this).prop('checked')){
			 arrItem.push(item);
		 }
	}); 
	$.each(arrItem,function(i,item){
		customers.push(item)
	})
	$.each(customers,function(i,item){
			 var hiddenField = $("<input>").attr({
				 'type': 'hidden',
				 'id': item,
				 'name': 'customerIds',
				 'value': item
			 });
			 inputHtml.append(hiddenField);//隐藏id
	})
	 var tag = $("<div class='couponTag'>").text("已选择("+customers.length+")个客户");
	 var img = $("<img class='coupon-tag-close'>").attr('src',closeImgPath);
	 img.click(function(e){
		 customers.splice(0);
		 $('#customersContainer').html('');
		 $(this).parent().detach();
	 })
	 tag.append(img);
	 tagHtml.append(tag);
	$('#customerDiv').html(tagHtml)
	$('#customersContainer').html(inputHtml)
	$("#cusCount").html(customers.length)
	
}
function confirmSelectedCustomers(){//模态框确定
	addCustomers();
	//$('#').modal('hide');	
	$("#myCustomersModal").on("hidden.bs.modal", function() { 
		$(this).removeData("bs.modal"); 
	});
	$('#myCustomersModal').modal('hide');	
}
//加载商品初始数据
function loadItems(currentPage){
	$("#queryItems").html('');
	var url=window.location.href.split("?")[0];
	$.ajax({
		url:url+"/item/search",
		type:"POST",
		data:{
			'keyWords':$("#itemKeyWords").val(),
			'currentPage':currentPage
		},success : function(data) {
			data=eval('(' + data + ')');
			$.each(data.itemList, function(i, item){//th:value="${coupon.id}
			var checkBoxHtml = '<input type="radio" name="ItemName"  value="'+item.id+'"/>';
			if(item.id==goods){
				checkBoxHtml = '<input type="radio" checked name="ItemName"  value="'+item.id+'"/>';
			}
			var row = $('<tr><td>'+checkBoxHtml+'</td><td>'+item.name+'</td>'+
			'<td>'+item.id+'</td>'+
			'<td>'+item.barcode+'</td>'+
			'<td>'+item.listPrice+'</td>'+
			'<td>'+item.price+'</td></tr>');
		    row.click(function(e){
				var ck = $(this).find("input[type='radio']");
				ck.prop("checked",!ck.is(':checked'));
			});  
		    $("#queryItems").append(row);
		}) 
		limitPage(data.itemCurrentPage,data.itemTotal,3)	
	}
  })	
}
function addItems(){//添加商品到隐藏区域
	var itemsDiv =$("#itemsDiv");
	var inputHtml = $("<div>");
	$("input[name='ItemName']").each(function(){
		 var item=$(this).val();
		 if($(this).prop('checked')){
			 goods=item;
		 }
	}); 
	if(goods != ''){
		 var hiddenField = $("<input>").attr({
			 'type': 'hidden',
			 'id': goods,
			 'name': 'linkedItemId',
			 'value': goods
		 });
		 inputHtml.append(hiddenField);//隐藏id
		 var tag = $("<div class='couponTag'>").text("商品编号："+goods);
		 var img = $("<img class='coupon-tag-close'>").attr('src',closeImgPath);
		 img.click(function(e){
			 inputHtml.children("#"+goods).detach();
			 goods='';
			 $(this).parent().detach();
		 })
		 tag.append(img);
		 itemsDiv.html(tag);
		 $('#itemsContainer').html(inputHtml)
	}
		
}
//模态框 商品 点击确定
function confirmSelectedItems(){
	addItems();
	$('#myItemModal').modal('hide');	
}
function clearModal(){
	customers.splice(0);
	$("#queryCustomers").html('');
}
//客户 投放 模态框 加载初始数据
function loadDistributeCoupons(currentPage){
	 $("#queryCustomers").html('');
	var url=window.location.href.split("?")[0];
	$.ajax({
		url:url+"/customers/search",
		type:"POST",
		data:{
			'keyWords':$("#cusKeyWords").val(),
			'currentPage':currentPage,
			'num':$("#maxReceiveNum").val(),
			'couponId':$("#couponId").val()
		},success : function(data) {
			data=eval('(' + data + ')');
			var couponId = $("#couponId").val();
	 		var maxReceiveNum = $("#maxReceiveNum").val();
			$.each(data.customerList, function(i, item){//th:value="${coupon.id}
			var checkBoxHtml = '<input type="checkbox" name="customerName"  value="'+item.id+'"/>';
			var inputBoxHtml = '<input  disabled class="col-sm-3" name="cusCouponCount" type="text" value=""/>';
			var isMore=false;
			if(customers.length > 0){
				$.each(customers,function(i,val){
					if(item.id==val){
						checkBoxHtml = '<input type="checkbox" checked name="customerName"  value="'+item.id+'"/>';
						inputBoxHtml = '<input class="col-sm-3" name="cusCouponCount" type="text" value="'+dtrNum[i]+'"/>';
			    		if(parseInt(maxReceiveNum) < dtrNum[i] && parseInt(maxReceiveNum) > 0) {
			    			isMore = true;
						} 
					}
				})
			}
			//var memberType=item.memberType==0?'否':'是';
			var vipLevel='';
			if(item.vipLevel==1){
				vipLevel='普通会员';
			}else if(item.vipLevel==2){
				vipLevel='金卡会员';
			}else if(item.vipLevel==3){
				vipLevel='钻石会员';
			}
			var receivedDate='暂无';
			if(item.receivedDate!='' && item.receivedDate!=null){
				receivedDate=new Date(item.receivedDate).Format("yyyy-MM-dd HH:mm:ss");
			}
			var appendHtml='<span style="margin-left:5px;font-size:12px;color:#838383">发放数量不可大于限领数</span>';
			//maxReceiveNum
			var row = $('<tr style="cursor:default"><td>'+checkBoxHtml+'</td><td><label style="cursor:pointer">'+item.id+'</lable></td>'+
			'<td>'+item.phone+'</td>'+
			'<td>'+item.name+'</td>'+
			'<td>'+vipLevel+'</td>'+
			'<td>'+receivedDate+'</td>'+
			'<td>'+maxReceiveNum+'</td>'+
			'<td style="width: 30%;">'+inputBoxHtml+'</td></tr>');
			row.find("input[type='text']").click(function(e){
		    	e.preventDefault();
		    	return false;
		    });
			 if(isMore){
			    	row.find("input[type='text']").addClass('input-error');
			    	row.find("input[type='text']").parent().append(appendHtml);
			    }
			 row.find("input[type='checkbox']").click(function(e){
				 if($(this).is(':checked')) {
						row.find("input[type='text']").attr('disabled',false);
					} else {
						row.find("input[type='text']").attr('disabled',true);
						row.find("input[type='text']").val('');
						row.find("input[type='text']").removeClass('input-error');
						row.find("span").remove();
					}
				});
		    row.find("label").click(function(e){
				var ck = $(this).parent().parent().find("input[type='checkbox']");
				ck.prop("checked",!ck.is(':checked'));
				if(ck.is(':checked')) {
					row.find("input[type='text']").attr('disabled',false);
				} else {
					row.find("input[type='text']").attr('disabled',true);
					row.find("input[type='text']").val('');
					row.find("input[type='text']").removeClass('input-error');
					row.find("span").remove();
				}
			});
		    $("#queryCustomers").append(row);
		    row.find("input[type='text']").change(function(e){
		    	if($(this).val() > parseInt(maxReceiveNum) && (maxReceiveNum) >0) {
		    		$(this).parent().find('span').remove();
		    		$(this).addClass('input-error');
		    		$(this).parent().append(appendHtml);
		    	} else {
		    		$(this).parent().find('span').remove();
		    		$(this).removeClass('input-error');
		    	}
		    });
		})
		limitPage(data.cusCurrentPage,data.cusTotal,5)	
	}
  })
}
function addDistributeCustomers(){//添加客户 到 隐藏区域
	var arrItem=[];
	var couponId=$("#couponId").val();
	var maxReceiveNum=$("#maxReceiveNum").val();
	$("input[name='customerName']").each(function(){
		 var item=$(this).val();
		 var num=$(this).parent().parent().find("[name='cusCouponCount']").val();
		 var index=customers.indexOf(item);
		 if(index > -1){
			 customers.splice(index,1);
			 dtrNum.splice(index,1);
		 }
		 if($(this).prop('checked')){
			 arrItem.push(item);
			 dtrNum.push(num)
		 }
	}); 
	$.each(arrItem,function(i,item){
		customers.push(item)
	})
	var result=0;
	$.each(dtrNum,function(i,item){
		if(item=='' || item ==null){
			result=1;
			return false;
		}
		if(item > parseInt(maxReceiveNum) && parseInt(maxReceiveNum) > 0){
			result=2;
			return false;
		}
		// 与max值比较
		if(item <= 0){
			result=3;
			return false;
		}
	})
	return result;
}
function confirmCustomers(){//模态框确定
	var res=addDistributeCustomers();
	var couponId=$("#couponId").val();
	//return false;
	if(customers.length==0){
		alert("请先选择客户")
		return false;
	}
	if(res ==1 ){
		alert("投放数量不能为空")
		return false;
	}else if(res ==2 ){
		alert("投放数量已超过最大限额")
		return false;
	}else if(res ==3 ){
		alert("投放数量必须大于0")
		return false;
	}else{
		var url=window.location.href.split("?")[0];
		$.ajax({
			url:url+"/customers/add",
			type:"POST",
			traditional: true,//防止深度序列化 数组传参
			data:{
				'couponId':couponId,
				'customerIds':customers,
				'distributeNum':dtrNum
			},success : function(data) {
				data = eval('('+data+')');
				//window.location.reload();
				 if(data.code==200){
					 zeroModal.alert({
					        title: "提示",
					        content:"投放成功",
					        width: '300px',
					        height: '260px',
					        overlay: false,
					        ok: true,
					        okFn: function() {
					        	window.location.reload();
					       }
					   });
				 }
			}
		})//ajax
	}//else
	$('#myCustomersModal').modal('hide');	
}
function showSupplierInfo(){
	 var url=window.location.href.split("?")[0];
	 var id =$("#supplierId").val()
	 $.ajax({
			url:url+"/audit",
			type:"POST",
			traditional: true,//防止深度序列化 数组传参
			data:{
				'id':id,
			},success : function(data) {
				data=eval('('+data+')');
				var supplier = data.supplier;
				$("#supplierCode").text(supplier.id);
				$("#companyName").text(supplier.companyName);
				$("#productCategory").text(supplier.productCategory);
				$("#productName").text(supplier.productName);
				$("#companyLicenceImage").attr("src",supplier.companyLicenceImage);
				var ceritifcationImages=supplier.ceritifcationImages;
				if(ceritifcationImages != '' && ceritifcationImages != null){
					if(ceritifcationImages.indexOf(",") > -1){
						var imageUrls=ceritifcationImages.split(",");
						var html='';
						$.each(imageUrls,function(i,item){
							if(i < 3){
								html+='<img src="'+item+'" class="imgwh" />';
							}
						})
						$("#ceritifcationImages").html(html);
					}else{
						$("#ceritifcationImages").html('<img src="'+ceritifcationImages+'" class="imgwh" />')
					}
					
				}//ceritifcationImages
				var productImages=supplier.productImages;
				if(productImages != '' && productImages != null){
					if(productImages.indexOf(",") > -1){
						var imageUrls=productImages.split(",");
						var html='';
						$.each(imageUrls,function(i,item){
							if(i < 3){
								html+='<img src="'+item+'" class="imgwh" />';
							}
						})
						$("#productImages").html(html);
					}else{
						$("#productImages").html('<img src="'+productImages+'" class="imgwh" />')
					}
				}//productImages
			}//function data
		})//ajax
}
function promotionValidate(){
	var url=getRootPath();
	var items=[];
	var itemName=null;
	$("input[name='promotionMixItemId']").each(function(){
		items.push($(this).val());
	})
	 $.ajax({
			url:url+"/campaign/promotion/combination/item/validate",
			type:"POST",
			async:false,//同步执行 就会重上往下了
			traditional: true,//防止深度序列化 数组传参
			data:{
				'itemIds':items,
				'id':$("#promotionId").val()
			},
			success : function(data) {
				data=eval('('+data+')');
				if(data.code == 201){
					itemName=data.itemName;
				}
			}//function
		 })//ajax 
		 if(itemName != null){
			 zeroModal.show({
			        title: "提示",
			        content:"【"+itemName+"】已在其他换购活动中,请重新选择",
			        width: '300px',
			        height: '160px',
			        overlay: false,
			        ok: true
			    });
			 return true;
		 }
	 return false; 
}
//订单模块 分页
function orderLimitPage(page,total){
		$("#orderPage").sPage({
		    page:page,//当前页码，必填 写死1则选中光标一直在1
		    total:total,//数据总条数，必填
		    pageSize:10,//每页显示多少条数据，默认10条
		    totalTxt:"共{total}条",//数据总条数文字描述，{total}为占位符，默认"共{total}条"
		    showTotal:true,//是否显示总条数，默认关闭：false
		    showSkip:true,//是否显示跳页，默认关闭：false
		    showPN:true,//是否显示上下翻页，默认开启：true
		    prevPage:"上一页",//上翻页文字描述，默认“上一页”
		    nextPage:"下一页",//下翻页文字描述，默认“下一页”
		    backFun:function(page){
		        //点击分页按钮回调函数，返回当前页码
		    	$("#currentPage").val(page);
		    	$("#orderForm").submit();
		    }
		});
	}
//库存模块 分页
function inventoryLimitPage(page,total){
		$("#myPage").sPage({
		    page:page,//当前页码，必填 写死1则选中光标一直在1
		    total:total,//数据总条数，必填
		    pageSize:10,//每页显示多少条数据，默认10条
		    totalTxt:"共{total}条",//数据总条数文字描述，{total}为占位符，默认"共{total}条"
		    showTotal:true,//是否显示总条数，默认关闭：false
		    showSkip:true,//是否显示跳页，默认关闭：false
		    showPN:true,//是否显示上下翻页，默认开启：true
		    prevPage:"上一页",//上翻页文字描述，默认“上一页”
		    nextPage:"下一页",//下翻页文字描述，默认“下一页”
		    backFun:function(page){
		        //点击分页按钮回调函数，返回当前页码
		    	$("#currentPage").val(page);
		    	$("#inventoryForm").submit();
		    }
		});
	}
function dateCompareTo(startTime,endTime){
	var start =$("#"+startTime).val();
	var end =$("#"+endTime).val();
	var forStart= new Date(start).Format("yyyyMMddHHmmss");
	var forEnd= new Date(end).Format("yyyyMMddHHmmss")
	if(forStart < forEnd ){
		return true;
	}else{
		return false;
	}
}

//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
Date.prototype.Format = function (fmt) { //author: meizz 
	var o = {
	"M+": this.getMonth() + 1, //月份 
	"d+": this.getDate(), //日 
	"H+": this.getHours(), //小时 
	"m+": this.getMinutes(), //分 
	"s+": this.getSeconds(), //秒 
	"q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	"S": this.getMilliseconds() //毫秒 
	};
	if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
	if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}