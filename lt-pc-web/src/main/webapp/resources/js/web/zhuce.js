$(function(){
		$("input").focus(function(){
			$(this).css("color","#000");
		}).blur(function(){
			if($(this).val()=='请输入汉字,字母,数字及_组合'){
				$(this).css("color","#ccc");
			}
		});
	});