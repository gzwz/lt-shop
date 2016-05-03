$(function(){
	var validate = window.validate = window.validate || {};
	
	/**
	 * 手机校验
	 * @param inputId 输入框Id
	 * @param fieldName 字段名称
	 * @param permitEmpty 允许空值
	 * 					"true"：代表允许
	 * 					"false": 代表不允许
	 * @author ZhangHui 2016/1/30
	 */
	validate.phoneTest = function (inputId, fieldName, permitEmpty)
	{
		var val = $("#" + inputId).val().trim();
		if(val == "")
		{
			if(permitEmpty == "false")
			{
				return validate.nonEmptyTest(inputId, fieldName);
			}
			
			return true;
		}
		else
		{
			var reg = /^((13[0-9])|(14[5,7])|(15[^4,\D])|(18[0-3,5-9])|(17[0]))\d{8}$/;
			if(!reg.test(val))
			{
				$("#" + inputId + "_sp").text("请输入有效的手机格式！");
				return false;
			}
			else
			{
				$("#" + inputId + "_sp").text("");
				return true;
			}
		}
	}
	
	/**
	 * 身份证校验
	 * @param inputId 输入框Id
	 * @param fieldName 字段名称
	 * @param permitEmpty 允许空值
	 * 					"true"：代表允许
	 * 					"false": 代表不允许
	 * @author ZhangHui 2016/1/30
	 */
	validate.peopleIDTest = function (inputId, fieldName, permitEmpty)
	{
		var val = $("#" + inputId).val().trim();
		if(val = "")
		{
			if(permitEmpty == "false")
			{
				return validate.nonEmptyTest(inputId, fieldName);
			}
			
			return true;
		}
		else
		{
			$.post("yanz/getRequest1",
				{carNo:val},function(data){
					var json = (new Function("return " + data))(); 
					if(json.error_code==0)
					{
						$("#" + inputId + "_sp").text("");
						return true;
					}
					else
					{
						$("#" + inputId + "_sp").text("请输入有效的身份证！");
						return false;
					} 
				}); 
		}
	}
	
	/**
	 * 校验当前输入框还能输入多长的字符
	 * @param total 字符串总长度
	 * @param inputId 输入框Id
	 * @author ZhangHui 2016/1/30
	 */
	validate.keypressInputLength = function (total, inputId)
	{ 
		var value = $("#" + inputId).val().trim(); 
		var len = total - value.length;
	 	$("#" + inputId + "_sp").html("还能输入<font color='red'>" + len + "</font>个字");
	}
	
	/**
	 * 输入框非空检查
	 * @param inputId 输入框Id
	 * @param fieldName 字段名称
	 * @author ZhangHui 2016/1/30
	 */
	validate.nonEmptyTest = function (inputId, fieldName){
		if($("#" + inputId).val().trim() == ""){
			$("#" + inputId + "_sp").html("<font color='red'>" + fieldName + "不能为空</font>");
			return false;
		}else{
			$("#" + inputId + "_sp").text("");
			return true;
		}
	}
	
	/**
	 * 邮箱地址校验
	 * @param inputId 输入框Id
	 * @param fieldName 字段名称
	 * @param permitEmpty 允许空值
	 * 					"true"：代表允许
	 * 					"false": 代表不允许
	 * @author ZhangHui 2016/1/30
	 */
	validate.emailTest = function (inputId, fieldName, permitEmpty)
	{
		var val = $("#" + inputId).val().trim();
		if(val == "")
		{
			if(permitEmpty == "false")
			{
				return validate.nonEmptyTest(inputId, fieldName);
			}
			
			return true;
		}
		else
		{
			var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
			if(!reg.test(val))
			{
				$("#" + inputId + "_sp").text("请输入有效的邮箱格式！");
				return false;
			}
			else
			{
				$("#" + inputId + "_sp").text("");
				return true;
			}
		}
	}

});