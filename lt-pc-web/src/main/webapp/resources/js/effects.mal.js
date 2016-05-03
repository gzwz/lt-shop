

$(function(){
	//图片切换
	var oThumb = document.getElementById('thumb');
	var oThumb2 = document.getElementById('thumb2');
	var aImg = oThumb.getElementsByTagName('img');

	for (var i=0; i<aImg.length; i++){
	    aImg[i].index =i;
	    aImg[i].onmouseover = function (){
	       oThumb2.src = aImg[this.index].src;
	    }
	}

      
	    
	                                                                                                         
	    /* $('#description li').click(function(){    
	        $(this).addClass('curr').siblings().removeClass('curr')
	        $('#descriptions').find('.tshop-psm').css('display','none')
	        $('#descriptions').find('.tshop-psm').eq($('#description li').index(this)).css('display','block')
	       
	    })*/




	    $('.model').find('option:even').css('backgroundColor','#e9e9e9');


	    $('#colourbox').find('li').click(function (){
	        $('#colourbox i').removeClass('currt');
	        $(this).addClass('curr').siblings().removeClass('curr');
	        $(this).find('i').addClass('currt');
	    })
	
	
});
