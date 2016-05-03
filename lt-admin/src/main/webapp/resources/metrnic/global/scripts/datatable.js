/**
 * table 初始化js
 */
var Datatable = function() {
	/**
	 * 扩展datatables 表单查询
	 * @param {Object} oSettings
	 * @param {Object} sInput
	 */
	jQuery.fn.dataTableExt.oApi.fnFormFilter = function(oSettings, sInput) {
		this.oApi._fnReDraw(oSettings);
	};

	/**
	 * 根据字段名称从json中取到数据 
	 * @param {Object} data json对象
	 * @param {Object} name 要取到的字段名称
	 */
	var getDataFormJson = function(data, name) {
		for (var i = 0; i < data.length; i++) {
			if (data[i].name == name) {
				return data[i].value;
			}
		}
	};
	
	/**
	 * 将操作列的数据 转换为数据对象 并将大括号里面的参数替换
	 * @param {Object} data json对象
	 */
	var getOperateButton = function(columns, row){
		var array = new Array();
		for (var i = 0; i < columns.length; i++) {
			if (columns[i].operate != undefined){
				var buttons = columns[i].operate;
				for (var button in buttons){
					var re = /\{(\w+)\}?/ig;    
					var buttomstr = buttons[button];
					while(r = re.exec(buttomstr)) {   
						buttomstr = buttomstr.replace(r[0], row[r[1]])
					}  
					array.push(buttomstr);
				}
			}
		}
		return array;
	};
	
	var showModal = function($this, table){
		href= $this.attr("href"), //请求html地址
		url = $this.data("url"),  //请求form参数数据地址
		action = $this.data("action"); //form表单提交地址 如果没定义 将使用form的action地址
		target = $this.data("target");
		if (href == undefined || href == ""){
			$(target).modal();
		}else{
			$(target).modal({
				remote: href
			});
		}
		if (url != undefined && url != ""){//目前支持文本框 其他的后续支持 
			$.getJSON(url, "", function(data){
				for(var key in data){
					$(target).find(":input[name=" + key + "]").val(data[key]);
				}
			});
		}
		
		var modal = $(target), 
		saveBtn = modal.find(".modal-footer").find(".save"), 
		calcelBtn = modal.find(".modal-footer").find(".calcel"),
		resetBtn = modal.find(".modal-footer").find(".reset"),
		form = modal.find("form");
		modal.on('hidden.bs.modal', function (e) {//监听modal框的关闭事件
		  saveBtn.off("click"); //解绑click事件 避免提交多次
		})
		calcelBtn.on("click", function(){
			Ice.cleanForm(form); //清空表单
		});
		resetBtn.on("click", function(){
			Ice.cleanForm(form); //清空表单
		});
		saveBtn.on("click", function(){
			//表单验证
            var error3 = $('.alert-danger', form);
            var success3 = $('.alert-success', form);
			 form.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block help-block-error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "", // validate all fields including form hidden input
                
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
                },
                invalidHandler: function (event, validator) { //display error alert on form submit   
                    success3.hide();
                    error3.show();
                   // Metronic.scrollTo(error3, -200);
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
                    
                }
            });
			if (form.valid()){
				if (action == undefined || action == ""){
					action = form.attr("action")
				}
				$.ajax({
					type:"get",
					url:action,
					data: form.serialize(),
					async:true,
					dataType: "json",
					success: function(data){
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
							modal.modal('hide'); //隐藏
							Ice.cleanForm(form); //清空表单
							table.api().draw(false); //刷新表格
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
						
					}
				});
			}
			


		});
	}
	
	return {
		/**
		 * fnPreDrawCallback  每一次重绘完成之后后调用
		 * @param {Object} options
		 */
		init: function(options) {	
			//绑定参数
			 options = $.extend(true, {
			 	data: "data",
			 	container: "#table",
			 	searchContainer: "#search_form_box",
			 	fnClick: function(){
			 		alert("taregetdfs");
			 	},
			 	initComplete: function(){},
			 	format: {
					"render": function(data, type, row) {
						var btns = getOperateButton(options.columns, row);
						return btns.join("");
					},
					"targets": -1
				},
				formatter: [],
				buttons:[]
            }, options);
            options.formatter.push(options.format);
			//alert(getDataFormJson(options.columns, "operate"));
			//处理数据源属性 默认为data
			if (options.data == undefined){
				options.data = "data";
			}else if (options.data == null){
				options.data = "";
			}
			//处理工具栏按钮
			
			options.dom = "<'row search-box'<'col-md-12'>><'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'><'col-md-6 col-sm-12'>r><'table-scrollable't><'row'<'col-md-6 col-sm-12'li><'col-md-6 col-sm-12'p>>";
			if (options.buttons.length == 0){
				options.dom = "<'row search-box'<'col-md-12'>><'row' <'col-md-12'>><'row'<'col-md-6 col-sm-12'><'col-md-6 col-sm-12'>r><'table-scrollable't><'row'<'col-md-6 col-sm-12'li><'col-md-6 col-sm-12'p>>";
			}else{
				for (var i = 0; i < options.buttons.length; i++){
					var _object = options.buttons[i];
					for (var key in _object){
						if (key == "target"){//如果包含target参数绑定事件
							var target = _object["target"];
							var action = _object["action"];
							var submit = _object["submit"];
							var url = _object["url"];
							var toggle = _object["toggle"];
							
							var obj = {
								fnClick: function(nButton, oConfig, oFlash, sFlash){},
								fnInit: function(nButton, oConfig, oFlash, sFlash){
									$(nButton).attr("data-url", url);
			                    	$(nButton).attr("data-action", action);
			                    	$(nButton).attr("data-submit", submit);
			                    	$(nButton).attr("data-toggle", "iceModal");
			                    	$(nButton).attr("data-target", target);
			                    	$(nButton).addClass("tool_modal");
								}
							};
							options.buttons[i]["fnClick"] = obj.fnClick;
							options.buttons[i]["fnInit"] = obj.fnInit;
						}
					}
				}
			}
			var table = $(options.container).dataTable({
				"dom": options.dom,
				"sPaginationType": "full_numbers",
				"bAutoWidth": true,
				"bServerSide": true,
				"processing": true,
				"bSort": false,
				"ajax": {
					"dataSrc": options.data
				},
				"sAjaxSource": options.url,
				"fnServerData": function(sSource, aoData, fnCallback) {
					var pageSize = getDataFormJson(aoData, "iDisplayLength"),
						pageStart = getDataFormJson(aoData, "iDisplayStart"),
						draw = getDataFormJson(aoData, "sEcho");
					
					//计算pageNo
					var pageNo = Math.floor(pageStart/pageSize) + 1;
					
					//提取表单查询参数
					var reqParam = "";
					var serialize = $("#search_form").serialize();
					if (serialize != undefined && serialize != null && serialize != ""){
						reqParam = serialize + "&pageSize=" + pageSize + "&pageNo=" + pageNo + "&draw=" + draw + "&pageStart=" + pageStart;
					}else{
						reqParam = "pageSize=" + pageSize + "&pageNo=" + pageNo + "&draw=" + draw + "&pageStart=" + pageStart;
					}
					
					$.ajax({
						dataType: 'json',
						type: 'POST',
						url: sSource,
						async: false,
						data: reqParam,
						success: fnCallback,
						error: function(XMLHttpRequest, textStatus, errorThrown) {
							alert(XMLHttpRequest.status + "," + textStatus);
						}
					});
				},
				"aoColumns": options.columns,
				"columnDefs": options.formatter,
				"iDisplayLength": 10,
				"language": {
					"lengthMenu": "每页 _MENU_ 条&nbsp;&nbsp;&nbsp;&nbsp;",
					"zeroRecords": "没有找到记录",
					"info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
					"infoEmpty": "无记录",
					"infoFiltered": "(从 _MAX_ 条记录过滤)",
					'paginate': {
						'first': '第一页',
						'last': '最后一页',
						'next': '下一页',
						'previous': '上一页'
					}
				},
				"lengthMenu": [
	                [10, 20, 50, 100],
	                [10, 20, 50, 100] //分页
           		],
           		"oTableTools": {
           			"aButtons": options.buttons
           		},
           		"fnDrawCallback": function(){//表格绘制完成之后调用
           			//绑定点击事件
					var tag = $(options.container).find("td").children();
					tag.each(function(i, dom){
						$(dom).on("click", function(){
							var target = $(this).data("target");
							if (target == "ajaxExecute"){//如果按钮有ajaxExecute属性标识异步执行
								var href = $(this).attr("href");
								var title = $(this).attr("title");
								if (title == undefined){
									title = "提示信息"
								}
								Ice.alert({ //弹出一个提示框
			                		"message": title,   //提示框输出信息  
			                		"size": "small",   //提示框大小   默认为small   如需较大提示框 可改为: large 
			                		"successCallback":function(){//点击确定按钮时调用  可以不用实现
			                			if (undefined != href){
											$.ajax({
												type:"get",
												url:href,
												async:true,
												dataType: "json",
												success: function(data){
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
														table.api().draw(false); //刷新表格
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
												}
											});
			                			}
			                		},
			                		"cancelCallback": function(){
			                		}
		                		});
								
			                	return false;
							}else{
								if ($(this).data("toggle") === "iceModal"){
									showModal($(this), table);  //显示模态框
									return false;
								}
							}
							
							
						});
					});
           		},
           		"initComplete": function(){//初始化完成
           			//绑定查询表单
					var html = $(options.searchContainer).html();
					$(options.searchContainer).html("");
					$("div.search-box .col-md-12").html(html);
					//查询按钮点击事件
					$("#search_form").find("button.search-btn").on("click", function(){
						table.fnFormFilter();
					});
					$(".tool_modal").on("click", function(){
						showModal($(this), table);  //显示模态框
					});
					
			        options.initComplete();
           		}
			});
			
			
		},
		/**
		 * 不使用ajax方式加载数据时调用
		 * @param {Object} options
		 */
		pagination: function(options){
			options = $.extend(true, {
				id: "form"
			}, options);
			
			var $form = $("#"+options.id);
			var $searchBtn = $form.find(".search-btn"); 
			var $pageSize = $form.find(".page_size");
			var $pagination = $form.find(".pagination").children("li");
			var $pageNo = $form.find(".pageNo");
			$searchBtn.click(function(){
				$pageNo.val(1);
				$form.submit();
			});
			
			$pageSize.change(function(){
				$pageNo.val(1);
				$form.submit();
			});
			
			$pagination.click(function(){
				$pageNo.val($(this).attr("pageNo"));
				$form.submit();
			});
			
			var tag = $form.find("td").children();
			
			tag.each(function(i, dom){
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
		                			});
	                			}
	                			location.reload();
	                		},
	                		"cancelCallback": function(){
	                		}
                		});
						
	                	return false;
					}
				});
			});
			
			
			
			
			
			
			
		}
	};

}();