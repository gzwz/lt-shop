$(document).ready(function() {

	var contextPath = $("#contextPath").val();
	load();
	$("#provinceId").live("change", function() {
		$("#cityEditId").val("");
		city($(this).val());
	});

	$("#cityId").live("change", function() {
		area($(this).val());
	});

});

function city(ct) {
	// 市
	$.ajax({
		type : "post",
		url : contextPath + "/query_city",
		dataType : "json",
		async : true,
		data : {
			code : ct
		},
		success : function(data) {
			var result = "";
			if (data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					if (i == 0) {
						result += "<option value=''>=请选择=</option>";
					}
					if($("#cityEditId").val()==data[i].id){
						result += "<option selected='selected' value='" + data[i].code + "'>"
						+ data[i].name + "</option>";
					}else{
						result += "<option value='" + data[i].code + "'>"
						+ data[i].name + "</option>";
					}
				}
			}
			if($("#cityEditId").val()!=""){
				 area($("#cityEditId").val());
			}
			$("#cityId").html("");
			$("#areaId").html("");
			$("#cityId").html(result);
			
			
		}
	});
}

function area(ar) {
	// 区
	$.ajax({
		type : "post",
		url : contextPath + "/query_area",
		dataType : "json",
		async : true,
		data : {
			code :ar
		},
		success : function(data) {
			var result = "";
			if (data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					if (i == 0) {
						result += "<option value=''>==请选择==</option>";
					}
					if($("#areaEditId").val()==data[i].id){
						result += "<option selected='selected' value='" + data[i].code + "'>"
						+ data[i].name + "</option>";
					}else{
						result += "<option value='" + data[i].code + "'>"
						+ data[i].name + "</option>";
					}
				}
			}
			$("#areaId").html("");
			$("#areaId").html(result);
		}
	});
}

function load() {
	// 省加载
	$.ajax({
		type : "post",
		url : contextPath + "/query_province",
		dataType : "json",
		async : true,
		success : function(data) {
			var result = "";
			if (data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					if (i == 0) {
						result += "<option value=''>==请选择==</option>";
					}
					if($("#provinceEditId").val()==data[i].id){
						result += "<option selected='selected' value='" + data[i].code + "'>"
						+ data[i].name + "</option>";
					}else{
						result += "<option value='" + data[i].code + "'>"
						+ data[i].name + "</option>";
					}
					
				}
			}
       if($("#provinceEditId").val()!=""){
				
				city($("#provinceEditId").val());
				
			}
			$("#provinceId").html("");
			$("#cityId").html("");
			$("#areaId").html("");
			$("#provinceId").html(result);
			
			
		}
	});
}
