/**
 * 
 */

var contextPath=$("#contextPath").val();

 $(function() {
		$(".add-form").validate({
			rules : {
				brandName : {
					required : !0,
					minlength : 2
				},
				productCategoryId : {
							required : true
						}
			},
			messages : {
				brandName : {
					required : e + "请输入正确的品牌名称",
					minlength : e + "资源名称必须两个字符以上"
				}
			},
			submitHandler : function() {
				$(".add-form").ajaxSubmitForm(saveCollback);
			}
		});
		productCategory();
		
		
	});
	
 function productCategory(){
     $.ajax({
			type : "post",
			url : contextPath+"/productCategory/getProductCategory",
			dataType : "json",
			async : true,
			success : function(data) {
				var result = "";
				if (data.length > 0) {
					result =  "<select onchange='twoLevelCategory(this.value)' id='productCategoryId' class='form-control select2me' name='productCategoryId'>";
					for (var i = 0; i < data.length; i++) {
						if (i == 0) {
							result += "<option value=''>请选择</option>";
						}
						result += "<option value='" + data[i].id+ "'>"+ data[i].name+ "</option>";
					}
					result= result+"</select>";
				}
				
				$("#productCategory1").html("");
				$("#productCategory1").append(result);
			}
		});
}
	 
	 
	 
    
	 

     function twoLevelCategory(param){
                 $.ajax({
						type : "post",
						url :contextPath+"/twoLevelCategory/getTwoLevelCategory",
						dataType : "json",
						data:{productCategoryId:param},
						async : true,
						success : function(data) {
							var result = "";
							if (data.length > 0) {
								result =  "<select onchange='threeLevelCatetory(this.value)' id='twoLevelCategoryId' class='form-control select2me' name='twoLevelCategoryId'>";
								for (var i = 0; i < data.length; i++) {
									if (i == 0) {
										result += "<option value=''>请选择</option>";
									}
									result += "<option value='" + data[i].id+ "'>"+ data[i].name+ "</option>";
								}
								result= result+"</select>";
							}
							$("#productCategory2").html("");
							$("#productCategory2").append(result);
						}
					});
			}
			
			
			
	 function threeLevelCatetory(param){
                 $.ajax({
						type : "post",
						url : contextPath+"/threeLevelCatetory/getThreeLevelCatetory",
						dataType : "json",
						data:{twoLevelCategoryId:param},
						async : true,
						success : function(data) {
							var result = "";
							if (data.length > 0) {
								result =  "<select id='threeLevelCatetoryId' class='form-control select2me' name='threeLevelCatetoryId'>";
								for (var i = 0; i < data.length; i++) {
									if (i == 0) {
										result += "<option value=''>请选择</option>";
									}
									result += "<option value='" + data[i].id+ "'>"
											+ data[i].name
											+ "</option>";
								}
								result= result+"</select>";
							}
							$("#productCategory3").html("");
							$("#productCategory3").append(result);
						}
					});
			}
			
			