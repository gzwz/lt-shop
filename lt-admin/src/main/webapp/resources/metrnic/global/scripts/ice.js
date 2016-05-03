var Ice = function() {
	var format = function(d){
		return d>9?d:'0'+d;
	};
	var cleanForm = function(form){
		form[0].reset();
		form.find(":radio, :checked").removeAttr('checked').removeAttr('selected');
		var formGroup = form.find(".form-group");
		formGroup.find(".checker").children("span").removeClass("checked");
		formGroup.find(".radio").children("span").removeClass("checked");
	}
	return {
		cleanForm : function(form){
			form[0].reset();
			form.find(":radio, :checked").removeAttr('checked').removeAttr('selected');
			var formGroup = form.find(".form-group");
			formGroup.find(".checker").children("span").removeClass("checked");
			formGroup.find(".radio").children("span").removeClass("checked");
		},
		/**
		 * 表单日期控件
		 */
		datepicker: function(){
	    	$('.datepicker').datepicker({
	            rtl: Metronic.isRTL(),
	            orientation: "right",
	            language: "zh-CN",
	            format: 'yyyy-mm-dd',
	            todayBtn: true,
	            autoclose: true
	        });
	        
	        $('.date-picker').datepicker({
	            rtl: Metronic.isRTL(),
	            language: "zh-CN",
	            format: 'yyyy-mm-dd',
	            autoclose: true
	        });
   		},
		/**
		 * 日期时间选择
		 * @param {Object} options
		 */
		datetimepicker: function(options){
            options = $.extend(true, {
            	container: '.form_time',
		        todayBtn:  true,
				controlType: 'date'
            }, options);
            var today = 0, format = 'yyyy-mm-dd';
            if (options.todayBtn){
            	today = 1
            }
            if(options.controlType == 'datetime'){
            	if (options.format == undefined || options.format == null || options.format == ""){
            		format = 'yyyy-mm-dd hh:ii';
           		}
            	$(options.container).datetimepicker({
			        language:  'zh-CN',
			        weekStart: 1,
			        todayBtn:  today,
					autoclose: 1,
					todayHighlight: 1,
					startView: 2,
					forceParse: 0,
					format: format,
			        showMeridian: 0
			    });
            }else if(options.controlType == 'time'){
            	if (options.format == undefined || options.format == null || options.format == ""){
            		format = 'hh:ii';
           		}
            	format = 'hh:ii';
            	$(options.container).datetimepicker({
			        language:  'zh-CN',
			        weekStart: 1,
			        todayBtn:  today,
					autoclose: 1,
					todayHighlight: 1,
					startView: 1,
					minView: 0,
					maxView: 1,
					format: format,
					forceParse: 0
			    });
            }else{
            	$(options.container).datetimepicker({
			        language:  'zh-CN',
			        weekStart: 1,
			        todayBtn:  today,
					autoclose: 1,
					todayHighlight: 1,
					startView: 2,
					minView: 2,
					format: format,
					forceParse: 0
			    });
            }
            
		},
		/**
		 * 模态框
		 * @param {Object} options
		 */
//		modal: function(options){
//          options = $.extend(true, {
//              message: "",
//              size: "small", 
//              cancelCallback: function(){
//              	return false;
//              },
//              successCallback:function(){}
//          }, options);
//          
//          $modal.load('ui_extended_modals_ajax_sample.html', '', function(){
//            	$modal.modal();
//          });
//         	
//		},
		/**
		 * 弹出框
		 * @param {Object} options
		 */
		alert: function(options){
            options = $.extend(true, {
                message: "",
                size: "small", 
                showCancel: true,
                cancelCallback: function(){
                	return false;
                },
                successCallback:function(){}
            }, options);
            if (options.showCancel){
            	bootbox.dialog({
	           		size: options.size,
	                message: options.message,
	                buttons: {
	                  success: {
	                    label: "确定",
	                    className: "green",
	                    callback: options.successCallback
	                  },
	                  cancel: {
	                    label: "取消",
	                    className: "red",
	                    callback: options.cancelCallback
	                  }
	                }
	            });
            }else{
            	bootbox.dialog({
	           		size: options.size,
	                message: options.message,
	                buttons: {
	                  success: {
	                    label: "确定",
	                    className: "green",
	                    callback: options.successCallback
	                  }
	                }
	            });
            }
            
		},
		
		/**
		 * 信息提示框
		 * @param {Object} options
		 */
		info: function(options){
			options = $.extend(true, {
                message: "",
                title: "",
                shortCutFunction: "info",  //success info warning  error
                showDuration: "1000",
                hideDuration: "1000",
                timeOut: "5000",
                extendedTimeOut: "1000",
                showEasing:"swing",
                hideEasing:"linear",
                showMethod: "fadeIn",
                hideMethod: "fadeOut",
                closeButton: true,
                debug: true,
                positionClass: 'toast-top-right', //toast-top-left toast-top-right toast-bottom-left toast-bottom-right 
                onclick: function(){
                }
            }, options);
            
            toastr.options = options;
            
            toastr[options.shortCutFunction](options.message, options.title); 
		},
		
		/**
		 * 百度编辑器 
		 * @param {Object} options
		 */
		editor: function (options){
			options = $.extend(true, {
                id: "umEditor",
                name:"editorValue"
            }, options);
            
            //实例化编辑器
		    var ue = UE.getEditor(options.id,{
		    	textarea:options.name
		    });
            
		},
		/**
		 * 将毫秒数转换为时间
		 * @param {Object} options
		 */
		formatDate: function (milliseconds){
			var date = new Date(milliseconds);
			return date.getFullYear() + "-" + format(date.getMonth() + 1) + "-" + format(date.getDate()) + " " + format(date.getHours()) + ":" + format(date.getMinutes()) + ":" + format(date.getSeconds());
		},
		modal: function(options){
			options = $.extend(true, {
				modalContainer: "#MyModal",
				type: "html" //html json
            }, options);
            
			var modal = $(options.modalContainer), 
			savaBtn = modal.find(".modal-footer").find(".save"), 
			calcelBtn = modal.find(".modal-footer").find(".calcel"),
			resetBtn = modal.find(".modal-footer").find(".reset");
			
			resetBtn.on("click", function(){
				form[0].reset();
				form.find(":radio, :checked").removeAttr('checked').removeAttr('selected');
				var formGroup = form.find(".form-group");
				formGroup.find(".checker").children("span").removeClass("checked");
				formGroup.find(".radio").children("span").removeClass("checked");
			});
			savaBtn.on("click", function(){
				form = modal.find("form");
				$.ajax({
					type:form.attr("method"),
					url:form.attr("action"),
					data: form.serialize(),
					async:true,
					dataType: "json",
					success: function(data){
						if (options.type === "json"){
							if (data.statusCode == 200){
		    					Ice.info({
									message: data.message,  //必填
									title: "提示信息",   //必填
					                shortCutFunction: "success",  //显示类型  success info warning  error 默认值 info
					                timeOut: "5000",  //显示时间
					                positionClass: 'toast-top-right', //显示位置 toast-top-left toast-top-right toast-bottom-left toast-bottom-right 
					                onclick: function(){
					                }
								});
								modal.modal('hide')
								cleanForm(form);
							}else{
								Ice.info({
									message: data.message,  //必填
									title: "提示信息",   //必填
					                shortCutFunction: "error",  //显示类型  success info warning  error 默认值 info
					                timeOut: "5000",  //显示时间
					                positionClass: 'toast-top-right', //显示位置 toast-top-left toast-top-right toast-bottom-left toast-bottom-right 
					                onclick: function(){
					                }
								});
							}
						}else{//
							if (data.statusCode == 200){//添加成功
								modal.modal('hide');
								Ice.alert({"message": data.message, showCancel: false, successCallback:function(){location.reload();}});
							}else{
								Ice.alert({"message": data.message, showCancel: false});
							}
							
						}
						
					}
				});
			});
			

            
//          var modal = $(options.modalContainer), 
//			savaBtn = modal.find(".modal-footer").find(".save"), 
//			calcelBtn = modal.find(".modal-footer").find(".calcel"),
//			resetBtn = modal.find(".modal-footer").find(".reset"), 
//			form = modal.find("form");
//			
//			resetBtn.on("click", function(){
//				form[0].reset();
//				form.find(":radio, :checked").removeAttr('checked').removeAttr('selected');
//				var formGroup = form.find(".form-group");
//				formGroup.find(".checker").children("span").removeClass("checked");
//				formGroup.find(".radio").children("span").removeClass("checked");
//			});
//			
//			savaBtn.on("click", function(){
//				$.ajax({
//					type:"get",
//					url:form.attr("action"),
//					data: form.serialize(),
//					async:true,
//					dataType: "json",
//					success: function(data){
//						if (data.statusCode == 200){
//	    					Ice.info({
//								message: data.message,  //必填
//								title: "提示信息",   //必填
//				                shortCutFunction: "success",  //显示类型  success info warning  error 默认值 info
//				                timeOut: "5000",  //显示时间
//				                positionClass: 'toast-top-right', //显示位置 toast-top-left toast-top-right toast-bottom-left toast-bottom-right 
//				                onclick: function(){
//				                }
//							});
//							//modal.modal('hide')
//							cleanForm(form);
//						}else{
//							Ice.info({
//								message: data.message,  //必填
//								title: "提示信息",   //必填
//				                shortCutFunction: "error",  //显示类型  success info warning  error 默认值 info
//				                timeOut: "5000",  //显示时间
//				                positionClass: 'toast-top-right', //显示位置 toast-top-left toast-top-right toast-bottom-left toast-bottom-right 
//				                onclick: function(){
//				                }
//							});
//						}
//					}
//				});
//			});
            
		},
		treeTable: function(options){
			options = $.extend(true, {
                id: "#treeTable",
                expandLevel: 3
            }, options);
            $(options.id).treeTable({expandLevel : options.expandLevel});
            
            $.each($(options.id).find("td").children(), function(i, dom){
            	$(dom).on("click", function(){
					if ($(this).attr("target") == "ajaxExecute"){
						var href = $(this).attr("href");
						var title = $(this).attr("title");
						if (title == undefined){
							title = "提示信息"
						}
						Ice.alert({ //弹出一个提示框
	                		"message": title,   //提示框输出信息  
	                		"size": "small",   //提示框大小   默认为small   如需较大提示框 可改为: large 
	                		"successCallback":function(){//点击确定按钮时调用  可以不用实现
	                			Metronic.blockUI({animate: true});
					            window.setTimeout(function() {
					                Metronic.unblockUI();
					            }, 30000);
	                			if (undefined != href){
		                			$.getJSON(href,"", function(data){
		                				if (data.statusCode == 200){
		                					Ice.info({
												message: data.message,  //必填
												title: "提示信息",   //必填
								                shortCutFunction: "success",  //显示类型  success info warning  error 默认值 info
								                timeOut: "5000",  //显示时间
								                positionClass: 'toast-top-right', //显示位置 toast-top-left toast-top-right toast-bottom-left toast-bottom-right 
								                onclick: function(){
								                }
											});
											window.setTimeout(function() {
								                location.reload();
								            }, 100);
	                					}else{
	                						Ice.info({
												message: data.message,  //必填
												title: "提示信息",   //必填
								                shortCutFunction: "error",  //显示类型  success info warning  error 默认值 info
								                timeOut: "5000",  //显示时间
								                positionClass: 'toast-top-right', //显示位置 toast-top-left toast-top-right toast-bottom-left toast-bottom-right 
								                onclick: function(){
								                }
											});
											Metronic.unblockUI();
	                					}
		                			});
	                			}
	                			
	                		},
	                		"cancelCallback": function(){
	                		}
                		});
						
	                	return false;
					}
				});
            });
            
		},
		/**
		 * 表单验证
		 * @param {Object} options
		 */
		validation: function(options){			
           options = $.extend(true, {
                formContainer: "#add_form",
                messages: null,
                rules: null,
                ajaxSubmit: false,
                ajaxSubmitSuccessCallback: null
            }, options); 
            
            var form3 = $(options.formContainer);
            var error3 = $('.alert-danger', form3);
            var success3 = $('.alert-success', form3);

            form3.on('submit', function() {

            })

            form3.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "", // validate all fields including form hidden input
                rules: options.rules,
                messages: options.messages,
                
                errorPlacement: function (error, element) { // render error placement for each input type
                    if (element.parent(".input-group").size() > 0) {
                        error.insertAfter(element.parent(".input-group"));
                    } else if (element.attr("data-error-container")) { 
                        error.appendTo(element.attr("data-error-container"));
                    } else if (element.parents('.radio-list').size() > 0) { 
                        error.appendTo(element.parents('.radio-list').attr("data-error-container"));
                    } else if (element.parents('.radio-inline').size() > 0) { 
                        error.appendTo(element.parents('.radio-inline').attr("data-error-container"));
                    } else if (element.parents('.checkbox-list').size() > 0) {
                        error.appendTo(element.parents('.checkbox-list').attr("data-error-container"));
                    } else if (element.parents('.checkbox-inline').size() > 0) { 
                        error.appendTo(element.parents('.checkbox-inline').attr("data-error-container"));
                    } else {
                        error.insertAfter(element); // for other inputs, just perform default behavior
                    }
//                    Metronic.alert({
//		                container: "#show_error_info", // alerts parent container(by default placed after the page breadcrumbs)
//		                place: "prepent", // append or prepent in container 
//		                type: "danger",  // success danger warning info
//		                message: "请将表单填写完整再提交",  // alert's message
//		                close: "true", // make alert closable
//		                reset: "true", // close all previouse alerts first
//		                focus: "true", // auto scroll to the alert after shown
//		                closeInSeconds: 20, // auto close after defined seconds
//		                icon:  "warning" // warning check user
//		            });
                    //Metronic.scrollTo(error3, 0);
                },

                invalidHandler: function (event, validator) { //display error alert on form submit   
                    success3.hide();
                    error3.show();
                },

                highlight: function (element) { // hightlight error inputs
                   $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change done by hightlight
                    $(element)
                        .closest('.form-group').removeClass('has-error'); // set error class to the control group
                },

                success: function (label) {
                    label
                        .closest('.form-group').removeClass('has-error'); // set success class to the control group
                },

                submitHandler: function (form) {
                    success3.show();
                    error3.hide();
                    Metronic.blockUI({animate: true});
		            window.setTimeout(function() {
		                Metronic.unblockUI();
		            }, 30000);
                    if (options.ajaxSubmit || $(form).attr("target") == "AJAX" || $(form).data("ajax")){
                    	if (options.ajaxSubmitSuccessCallback != undefined && options.ajaxSubmitSuccessCallback != null){
                    		$.ajax({
	                            type: $(form).attr("method"),
	                            url: $(form).attr("action"),
	                            data: $(form).serialize(),
	                            dataType:"json",
	                            success: function(data){
	                            	if (data != undefined){
	                            		 Metronic.unblockUI();
	                            	}
	                                options.ajaxSubmitSuccessCallback(data);
	                            }
	                        });
                    	}else{
                    		$.ajax({
	                            type: $(form).attr("method"),
	                            url: $(form).attr("action"),
	                            data: $(form).serialize(),
	                            dataType:"json",
	                            success: function(data){
	                            	if (data != undefined){
	                            		 Metronic.unblockUI();
	                            	}
	                                Ice.alert({ //弹出一个提示框
	                                    "message": data.message,   //提示框输出信息
	                                    "size": "small",   //提示框大小   默认为small   如需较大提示框 可改为: large
	                                    "successCallback":function(){//点击确定按钮时调用  可以不用实现
	                                    	if ($(form).data("callback") == undefined){
	                                    		window.location.href = $(form).attr("callback");
	                                    	}else{
	                                    		window.location.href = $(form).data("callback");
	                                    	}
	                                        
	                                    }
	                                });
	                            }
	                        });
                    	}
                    }else{
                    	form[0].submit(); // submit the form
                    }
                    
                }

            });

             //apply validation on select2 dropdown value change, this only needed for chosen dropdown integration.
            $('.select2me', form3).change(function () {
                form3.validate().element($(this)); //revalidate the chosen dropdown value and show error or success message for the input
            });

            // initialize select2 tags
            $("#select2_tags").change(function() {
                form3.validate().element($(this)); //revalidate the chosen dropdown value and show error or success message for the input 
            }).select2({
                tags: ["red", "green", "blue", "yellow", "pink"]
            });
            
            $('.date-picker .form-control').change(function() {
                form3.validate().element($(this)); //revalidate the chosen dropdown value and show error or success message for the input 
            })

		}
		
		
	};

    
}();