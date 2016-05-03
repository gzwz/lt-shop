var Ice = function() {
	return {
		
		var handleValidation1 = function() {
            
   		 }
		
		/**
		 * 表单验证
		 * @param {Object} options
		 */
		validation: function(options){
			jQuery.extend(jQuery.validator.messages, {
			  required: "必选字段",
			  remote: "请修正该字段",
			  email: "请输入正确格式的电子邮件",
			  url: "请输入合法的网址",
			  date: "请输入合法的日期",
			  dateISO: "请输入合法的日期 (ISO).",
			  number: "请输入合法的数字",
			  digits: "只能输入整数",
			  creditcard: "请输入合法的信用卡号",
			  equalTo: "请再次输入相同的值",
			  accept: "请输入拥有合法后缀名的字符串",
			  maxlength: jQuery.validator.format("请输入一个 长度最多是 {0} 的字符串"),
			  minlength: jQuery.validator.format("请输入一个 长度最少是 {0} 的字符串"),
			  rangelength: jQuery.validator.format("请输入 一个长度介于 {0} 和 {1} 之间的字符串"),
			  range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
			  max: jQuery.validator.format("请输入一个最大为{0} 的值"),
			  min: jQuery.validator.format("请输入一个最小为{0} 的值")
			});
			
           options = $.extend(true, {
                id: "add_form",
                messages: null,
                rules: null
            }, options); 
            
            var form1 = $(options.id);
            var error1 = $('.alert-danger', form1);
            var success1 = $('.alert-success', form1);

            form1.validate({
                errorElement: 'span', //定义错误显示容器
                errorClass: 'help-block help-block-error', // 定义错误消息显示的class
                focusInvalid: false, 
                ignore: "",  
                messages: options.messages,
                rules: options.rules,
                invalidHandler: function (event, validator) { //display error alert on form submit              
                    success1.hide();
                    error1.show();
                    Metronic.scrollTo(error1, -200);
                },
                highlight: function (element) { // hightlight error inputs
                    $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
                },
                unhighlight: function (element) { // revert the change done by hightlight
                    $(element).closest('.form-group').removeClass('has-error'); // set error class to the control group
                },
                success: function (label) {
                    label.closest('.form-group').removeClass('has-error'); // set success class to the control group
                },
                submitHandler: function (form) {
                    success1.show();
                    error1.hide();
                }
            });
            
            
		},
		
		
		
		
	};

    
}();