$(function(){
//¶¥²¿
		var int;
		var int1;
		var int2,int3;
		$(".navRight li").children('span').css('transform','rotate(135deg)');
				$(".fenlei").hide();
				$('.navRight li').mouseenter(function(){
					$(this).children('span').css('transform','rotate(135deg)');
				}).mouseleave(function(){
					$(this).children('span').css('transform','rotate(-45deg)');
				})
				$('.fenlei ul li').mouseenter(function(){
					//$(this).stop().animate({'height':'289px'},300).siblings().stop().animate({'height':'44px'},300);
					//for(var i=0;i<1;i++){
						//$(".fenleiright").append("<dl></dl>").append("<dt>ÉÏÒÂ</dt>").append("<dd></dd>").append("<a href='javascript:;'>°×³ÄÉÀ </a>");;
					//}
					var this_text = $(this).children().children().attr("id");
					if(this_text!="fenleiLeft_wl"){
						$(this).css('background','#FFFFFF');
						$(this).children().children().css('color','#0f5489');
						$(this).children().children().children().css('color','#0f5489');
					}
					if(this_text=="fenleiLeft_kc"){
						$('.fenleiright').stop().animate({'width':'0px'},300);
						$('#fenleiright_kc').fadeTo(0,1).stop().animate({'width':'740px'},300);
					}else if(this_text=="fenleiLeft_jc"){
						$('.fenleiright').stop().animate({'width':'0px'},300);
						$('#fenleiright_jc').fadeTo(0,1).stop().animate({'width':'740px'},300);
					}else if(this_text=="fenleiLeft_pj"){
						$('.fenleiright').stop().animate({'width':'0px'},300);
						$('#fenleiright_pj').fadeTo(0,1).stop().animate({'width':'740px'},300);
					}else{
						$('.fenleiright').stop().animate({'width':'0px'},300);
					}
				}).mouseleave(function(){
					//$('.fenlei ul li').stop().animate({'height':'79px'},300)
					$(this).children().children().css('color','#FFFFFF');
					$(this).children().children().children().css('color','#FFFFFF');
					$(this).css('background','#0f5489');
				});
				$('.navLeft').mouseleave(function(){
					$('.fenleiright').stop().animate({'width':'0px'},300);
				})
				$('.navLeft p').mouseenter(function(){
					$(this).children('span').css('transform','rotate(-45deg)');
					$('.fenlei').fadeIn(100);
				}).mouseleave(function(){
					int1 =setTimeout("$('.navLeft p').children('span').css('transform','rotate(135deg)')",500);
					int = setTimeout("$('.fenlei').fadeOut(0)",500);
				});
				$('.fenlei').mouseenter(function(){
					//alert(int);
					window.clearInterval(int);
					window.clearInterval(int1);
				}).mouseleave(function(){
					int2 = setTimeout("$('.fenlei').fadeOut(0)",500);
					int3 = setTimeout("$('.navLeft p').children('span').css('transform','rotate(135deg)')",500);
				});
				$(".fenleiright").mouseenter(function(){
					window.clearInterval(int2);
					window.clearInterval(int3);
				}).mouseleave(function(){
					$('.fenlei').fadeOut(0);
					$('.navLeft p').children('span').css('transform','rotate(135deg)');
				});
});