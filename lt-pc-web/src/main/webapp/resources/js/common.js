/**
 * swal插件通用确认框函数
 * @param title
 * @param text
 * @param type
 * @param confimBtnText
 * @param collback
 */
function confimSwal(title, text, type, confimBtnText, collback){
	swal({
		title: title,
		text: text,
		type: type,
		showCancelButton: true,
		confirmButtonColor: "#DD6B55",
		confirmButtonText: confimBtnText,
		cancelButtonText: "取消",
		closeOnConfirm: false
	}, function () {
		collback();
	});
}

var saveCollback = function(d){
	var iconName = "error";
	if(d.success){
		location.reload();
		iconName = "success";
	} 
	swal(d.errorMsg, '', iconName);
}


var refurbishCollback = function(d){
	var iconName = "error";
	if(d.success){
		swal({
			  title: "确定继续吗？",
			  text: d.errorMsg,
			  type: "success",
			  showCancelButton: true,
			  confirmButtonColor: "#1ab394",
			  confirmButtonText: "继续",
			  cancelButtonText: "完成",
			  closeOnConfirm: true,
			  closeOnCancel: false
			},
			function(isConfirm){
			  if (isConfirm) {
				  parent.resertAdd(); 
				  var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			      parent.layer.close(index); //再执行关闭 
			  } else {
				  parent.refurbish();//刷新
			      var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			      parent.layer.close(index); //再执行关闭 
			  }
			});
		
	}else{
		swal(d.errorMsg, '', iconName);
	}
}
//wz 返回成功窗口，但是只有一个完成按钮
var refurbishCollbackSuccess = function(d){
	var iconName = "error";
	if(d.success){
		swal({
			  title: "恭喜操作成功！",
			  text: d.errorMsg,
			  type: "success",
			  showCancelButton: true,
			  confirmButtonColor: "#1ab394",
		//	  confirmButtonText: "继续",
			  cancelButtonText: "完成",
		//	  closeOnConfirm: true,
		//	  closeOnCancel: false
			  closeOnCancel: true
			},
			function(isConfirm){
			 if (isConfirm) {
		 /* 	  parent.resertAdd(); 
				  var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			      parent.layer.close(index); //再执行关闭 
			  } else {*/
				
				  parent.refurbish();//刷新
			      var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			      parent.layer.close(index); //再执行关闭 
			     
			  }
			});
		
	}else{
		swal(d.errorMsg, '', iconName);
	}
}

$.fn.ajaxSubmitForm = function(collback){
	var param = $(this).serialize();
	var url = $(this).attr('action');
	var method = $(this).attr('method');
	if(method == 'get')
		fnAjaxGet(url, param, collback);
	else
		fnAjaxPost(url, param, collback);
}

/**
 * 通用ajax，get提交方式
 * @param url 提交地址
 * @param param 提交参数
 * @param collback 成功回调函数
 */
function fnAjaxGet(url, param, collback){
	fnAjax(url, 'get', 'json', param, collback);
}
/**
 * 通用ajax，post提交方式
 * @param url 提交地址
 * @param param 提交参数
 * @param collback 成功回调函数
 */
function fnAjaxPost(url, param, collback){
	fnAjax(url, 'post', 'json', param, collback);
}
/**
 * 通用ajax，delete提交方式
 * @param url 提交地址
 * @param param 提交参数
 * @param collback 成功回调函数
 */
function fnAjaxDel(url, param, collback){
	var delCollback = function(d){
		swal(d.errorMsg, '', "success");
		if(d.success){
			collback();
		}
	}
	confimSwal("您确定要删除吗？", "删除后将无法恢复，请谨慎操作！", "warning" , "确定", function(){
		fnAjax(url, 'delete', 'json', '', delCollback);
	});
}
/**
 * 通用ajax，change提交方式
 * @param url 提交地址
 * @param param 提交参数
 * @param collback 成功回调函数
 */
function fnAjaxChange(url, param, collback){
	var delCollback = function(d){
		swal(d.errorMsg, '', "success");
		if(d.success){
			collback();
		}
	}
	confimSwal("请确认操作？", "屏蔽后用户将无法登陆和进行任何操作！", "warning" , "确定", function(){
		fnAjax(url, 'delete', 'json', '', delCollback);
	});
}

function fnWithdraw(url, param, collback){
	var delCollback = function(d){
		swal(d.errorMsg, '', "success");
		if(d.success){
			collback();
		}
	}
	confimSwal("您同意提现吗？", "请谨慎操作！", "warning" , "确定", function(){
		fnAjax(url, 'delete', 'json', '', delCollback);
	});
}
/**
 * 通用ajax，put提交方式
 * @param url 提交地址
 * @param param 提交参数
 * @param collback 成功回调函数
 */
function fnAjaxPut(url, param, collback){
	fnAjax(url, 'put', 'json', param, collback);
}
/**
 * 通用ajax函数
 * @param url 提交地址
 * @param method 提交方式
 * @param dataType 返回结果格式类型
 * @param param 提交参数
 * @param collback 成功回调函数
 */
function fnAjax(url, method, dataType, param, collback){
	$.ajax({
		url : url, 
		type : method,
		dataType : dataType, 
		data : param,
		success :collback
	});
	
	
}
/**
 * 序列化表单为json对象
 */
$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};
/**
 * 合并两个对象属性为一个对象
 * 
 * @param obj1
 * @param obj2
 * @returns resultObj
 */
$.mergeObject = function(obj1, obj2) {
	var resultObj = {};
	for ( var attr in obj1) {
		resultObj[attr] = obj1[attr];
	}
	for ( var attr in obj2) {
		resultObj[attr] = obj2[attr];
	}
	return resultObj;
};
/**
 * jsonReader : {
 *			root 数据集
 *			total : 总页码,
 *			page : 页码,
 *			records : 总记录数,
 *			repeatitems : false
 *		},
 *		prmNames : {
 *			page : 页码,
 *			rows : 每页记录数,
 *			sort : 排序字段名,
 *			order : 排序规则asc,desc,
 *		},
 */
$.fn.myJqGrid = function(pin) {
	var defaults = {
		jsonReader : {
			root : "list",
			total : "totalPage",
			page : "pageNo",
			records : "totalCount",
			repeatitems : false
		},
		prmNames : {
			page : "pageNo",
			rows : "pageSize",
			sort : "orderField",
			order : "orderDirection",
		},
		scrollrows:true,
		scrollOffset:30,
		paramsList : {},
		rowNum : 30,
		rowList : [ 30, 50, 100 ],
		viewrecords : true,
		altRows : true,
		multiselect : true,
		multiboxonly : true,
		autowidth : true,
		datatype : "json",
		mtype : 'POST',
		height : 300,
		loadComplete:function(){
			changeTableDiv();
		}
	};
	defaults = $.extend(true, defaults, pin);
	return this.jqGrid(defaults);
};

$(function(){
	$(window).resize(function(){
		 setTimeout('changeTableDiv()',500);
	});
});
function changeTableDiv(){
	 //$('#lt-partner').jqGrid("setGridWidth",$(".layout-middle div:first-child").width(),true);
	 //$('#lt-partner').jqGrid("setGridHeight",calc_datagrid_height(),true);
}

function calc_datagrid_height(){
	var gridH = 368;
	var prevHeight = 0;
	prevHeight += $(".layout-bottom").height()+$(".layout-top").height()+$(".layout-middle ol").height()+$(".layout-middle .search").height();
	gridH = $(window).height() - prevHeight -180-180;
	return gridH;
}

/**
 * jqgrid时间类型格式
 * @param cellvalue
 * @param options
 * @param cell
 */
function pickDate( cellvalue, options, cell ) {
	setTimeout(function(){
		$(cell) .find('input[type=text]')
				.datepicker({format:'yyyy-MM-dd' , autoclose:true}); 
	}, 0);
}
/**
 * jqgrid时间类型格式
 * @param cellvalue
 * @param options
 * @param cell
 */
function pickDate1( cellvalue, options, cell ) {
	setTimeout(function(){
		$(cell) .find('input[type=text]')
				.datepicker({format:'yyyy-MM-dd hh:mm:ss' , autoclose:true}); 
	}, 0);
}
/**
 * jQuery
 * layer
 *
 * @param tipContent 提示内容
 * @param id		选择器id
 * @param color		配置颜色
 * 所以参数必须穿
 */
function tips(tipContent,id,color){
	layer.tips(""+tipContent, ""+id, {
			tips: [1, ""+color],
			time: 4000
	});
}