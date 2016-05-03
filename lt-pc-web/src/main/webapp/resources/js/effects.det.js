	
	//选择商品数量
	var shengyu = $("#shengyushuliang").val();
	var numValue = $("#num").val(1);
	//加减数量
	$("#decrease").click(function(){
		numValue = parseInt($("#num").val());
		if (numValue >1) {
			$("#num").val(parseInt(numValue-1));
		}else{
		$("#num").val(parseInt(1));
		layer.msg("对不起！最少只能选择1人次");
		}
	});
	$("#increase").click(function(){
		numValue = parseInt($("#num").val());
			$("#num").val(parseInt(numValue+1));
			if ($("#num").val() > parseInt(shengyu)) {
			layer.msg("对不起！最多只能选择"+shengyu+"人次");
			$("#num").val(parseInt(shengyu));
		}
	});
	//监听最多能选择数量
	$(function(){
		$("#num").bind('input propertychange', function(){
		if (parseInt($(this).val()) > parseInt(shengyu)) {
			layer.msg("对不起！最多只能选择"+shengyu+"人次");
			$("#num").val(parseInt(shengyu));
		}else if (parseInt($(this).val()) < parseInt(1) ) {
			$("#num").val(parseInt(1));
			layer.msg("对不起！最少只能选择1人次");
		}
		});
	});
////////////////////////////////////////////////////////////////////////////
	var oDesc = document.getElementById('description');
	var aLi = oDesc.getElementsByTagName('li');
	var aTsh = oDesc.getElementsByClassName('tshop-psm');

	for(var i=0; i<aLi.length; i++){
	    aLi[i].index=i;
	    aLi[i].onclick = function (){
//	        alert(aTsh.length)
	        for(var i=0; i<aLi.length; i++){
	            aLi[i].className='';
	            aTsh[i].style.display ='none';
	        }
	        aLi[this.index].className='curr';
	        aTsh[this.index].style.display='block';
	    }
	}
	
	
	var a=true;

	$('.navlist-top').click(function (){
	    if(a){
	        $('.navlist-top').find("ul").slideDown();
	        a=false;
	    }else{
	        $('.navlist-top').find("ul").slideUp();
	        a=true;
	    }
	})

	var oThumb = document.getElementById('thumb');
	var oThumb2 = document.getElementById('thumb2');
	var aImg = oThumb.getElementsByTagName('img');

	for (var i=0; i<aImg.length; i++){
	    aImg[i].index =i;
	    aImg[i].onmouseover = function (){
	       oThumb2.src = aImg[this.index].src;
	    }
	}