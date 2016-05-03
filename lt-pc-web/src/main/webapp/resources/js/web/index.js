$(function() {
	var Accordion = function(el, multiple) {
		this.el = el || {};
		this.multiple = multiple || false;

		// Variables privadas
		var links = this.el.find('.link');
		// Evento
		links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
	}

	Accordion.prototype.dropdown = function(e) {
		var $el = e.data.el;
			$this = $(this),
			$next = $this.next();

		$next.slideToggle();
		$this.parent().toggleClass('open');

		if (!e.data.multiple) {
			$el.find('.submenu').not($next).slideUp().parent().removeClass('open');
		};
	}	

	var accordion = new Accordion($('#accordion'), false);
});

$(function(){
				$("#tchy").click(function(){
					$(".wlz2 .wlz2_right .right_bottom_w dd div span").text("同城货源");
				});
				$("#qghy").click(function(){
					$(".wlz2 .wlz2_right .right_bottom_w dd div span").text("全国货源");
				});
				$("#kclb").click(function(){
					$(".wlz2 .wlz2_right .right_bottom_w dd div span").text("有空车");
				});
				$("#ydcx").click(function(){
					$(".wlz2 .wlz2_right .right_bottom_w dd div span").text("运单查询");
				});
				$(".wlz2 .wlz2_right .right_top_w .xian").click(function(){
					$(".wlz2 .wlz2_right .right_top_w .xian").css({"background":"#fff","color":"#000"});
					$(this).css({"background":"#f2f2f2","color":"#C71523"});
				});
				$('.navRight li').mouseenter(function(){
					$(this).children('span').css('transform','rotate(135deg)');
				}).mouseleave(function(){
					$(this).children('span').css('transform','rotate(-45deg)');
				})
				$('.fenlei ul li').mouseenter(function(){
					//$(this).stop().animate({'height':'289px'},300).siblings().stop().animate({'height':'44px'},300);
					$(this).css('background','#FFFFFF');
					$(this).children().children().css('color','#0f5489');
					$(this).children().children().children().css('color','#0f5489');
					//for(var i=0;i<1;i++){
						//$(".fenleiright").append("<dl></dl>").append("<dt>上衣</dt>").append("<dd></dd>").append("<a href='javascript:;'>白衬衫 </a>");;
					//}
					$('.fenleiright').fadeTo(0,0.8).stop().animate({'width':'289px'},300);
				}).mouseleave(function(){
					//$('.fenlei ul li').stop().animate({'height':'79px'},300)
					$(this).children().children().css('color','#FFFFFF');
					$(this).children().children().children().css('color','#FFFFFF');
					$(this).css('background','#0f5489');
				});
				$('.left').mouseleave(function(){
					$('.fenleiright').stop().animate({'width':'0px'},300);
				})
				/**$('.navLeft p').mouseenter(function(){
				$(this).children('span').css('transform','rotate(-45deg)');
					
					$('.fenlei').fadeOut(500); 
				}).mouseleave(function(){
					$(this).children('span').css('transform','rotate(135deg)');
					$('.fenlei').fadeIn(500);
				});**/
				//图片轮播
				$('.flicker-example').flicker;
			})
			
			/**卡车div层跳转**/
	$(function(){
		$("#shop").css({"display":"block"});
		$("#shop2").css({"display":"none"});
		$("#shop3").css({"display":"block"});
		$("#shop4").css({"display":"none"});
		$(".newcar").css({"border-width":"3px","border-color":"#C81622","border-bottom-color":"#FFF"})
		$(".newcar1").css({"border-width":"3px","border-color":"#C81622","border-bottom-color":"#FFF"})
		$(".d").css({"border-width":"3px","border-color":"#C81622","border-bottom-color":"#FFF"})
		});
		
	function open1(){
		$("#shop").css({"display":"block"});
		$("#shop2").css({"display":"none"});
		$(".newcar").css({"border-width":"3px","border-color":"#C81622","border-bottom-color":"#FFF"})
		$(".oldcar").css({"border-width":"0px","border-color":"#C81622","border-bottom-color":"#FFF"})
	}
	function open2(){
		$("#shop").css({"display":"none"});
		$("#shop2").css({"display":"block"});
		$(".oldcar").css({"border-width":"3px","border-color":"#C81622","border-bottom-color":"#FFF"})
		$(".newcar").css({"border-width":"0px","border-color":"#C81622","border-bottom-color":"#FFF"})
	}
	function open3(){
		$("#shop3").css({"display":"block"});
		$("#shop4").css({"display":"none"});
		$(".newcar1").css({"border-width":"3px","border-color":"#C81622","border-bottom-color":"#FFF"})
		$(".oldcar1").css({"border-width":"0px","border-color":"#C81622","border-bottom-color":"#FFF"})
	}
	function open4(){
		$("#shop3").css({"display":"none"});
		$("#shop4").css({"display":"block"});
		$(".oldcar1").css({"border-width":"3px","border-color":"#C81622","border-bottom-color":"#FFF"})
		$(".newcar1").css({"border-width":"0px","border-color":"#C81622","border-bottom-color":"#FFF"})
	}
	function open5(){
		$("#shop5").css({"display":"block"});
		$("#shop6").css({"display":"none"});
		$("#shop7").css({"display":"none"});
		$("#shop8").css({"display":"none"});
		$("#shop9").css({"display":"none"});
		$(".d").css({"border-width":"3px","border-color":"#C81622","border-bottom-color":"#FFF"})
		$(".e").css({"border-width":"0px","border-color":"#C81622","border-bottom-color":"#FFF"})
		$(".f").css({"border-width":"0px","border-color":"#C81622","border-bottom-color":"#FFF"})
		$(".g").css({"border-width":"0px","border-color":"#C81622","border-bottom-color":"#FFF"})
		$(".h").css({"border-width":"0px","border-color":"#C81622","border-bottom-color":"#FFF"})
	}
	function open6(){
		$("#shop6").css({"display":"block"});
		$("#shop5").css({"display":"none"});
		$("#shop7").css({"display":"none"});
		$("#shop8").css({"display":"none"});
		$("#shop9").css({"display":"none"});
		$(".e").css({"border-width":"3px","border-color":"#C81622","border-bottom-color":"#FFF"})
		$(".f").css({"border-width":"0px","border-color":"#C81622","border-bottom-color":"#FFF"})
		$(".g").css({"border-width":"0px","border-color":"#C81622","border-bottom-color":"#FFF"})
		$(".h").css({"border-width":"0px","border-color":"#C81622","border-bottom-color":"#FFF"})
		$(".d").css({"border-width":"0px","border-color":"#C81622","border-bottom-color":"#FFF"})
	}
	function open7(){
		$("#shop5").css({"display":"none"});
		$("#shop6").css({"display":"none"});
		$("#shop7").css({"display":"block"});
		$("#shop8").css({"display":"none"});
		$("#shop9").css({"display":"none"});
		$(".f").css({"border-width":"3px","border-color":"#C81622","border-bottom-color":"#FFF"})
		$(".g").css({"border-width":"0px","border-color":"#C81622","border-bottom-color":"#FFF"})
		$(".h").css({"border-width":"0px","border-color":"#C81622","border-bottom-color":"#FFF"})
		$(".d").css({"border-width":"0px","border-color":"#C81622","border-bottom-color":"#FFF"})
		$(".e").css({"border-width":"0px","border-color":"#C81622","border-bottom-color":"#FFF"})
	}
	function open8(){
		$("#shop8").css({"display":"block"});
		$("#shop5").css({"display":"none"});
		$("#shop6").css({"display":"none"});
		$("#shop7").css({"display":"none"});
		$("#shop9").css({"display":"none"});
		$(".g").css({"border-width":"3px","border-color":"#C81622","border-bottom-color":"#FFF"})
		$(".h").css({"border-width":"0px","border-color":"#C81622","border-bottom-color":"#FFF"})
		$(".d").css({"border-width":"0px","border-color":"#C81622","border-bottom-color":"#FFF"})
		$(".e").css({"border-width":"0px","border-color":"#C81622","border-bottom-color":"#FFF"})
		$(".f").css({"border-width":"0px","border-color":"#C81622","border-bottom-color":"#FFF"})
	}
	function open9(){
		$("#shop9").css({"display":"block"});
		$("#shop8").css({"display":"none"});
		$("#shop5").css({"display":"none"});
		$("#shop6").css({"display":"none"});
		$("#shop7").css({"display":"none"});
		$(".h").css({"border-width":"3px","border-color":"#C81622","border-bottom-color":"#FFF"})
		$(".d").css({"border-width":"0px","border-color":"#C81622","border-bottom-color":"#FFF"})
		$(".e").css({"border-width":"0px","border-color":"#C81622","border-bottom-color":"#FFF"})
		$(".f").css({"border-width":"0px","border-color":"#C81622","border-bottom-color":"#FFF"})
		$(".g").css({"border-width":"0px","border-color":"#C81622","border-bottom-color":"#FFF"})
	}
	
	
	/** 头部表单**/
	$(function(){
		$("#ss").focus(function(){
			$(this).val("");
			$(this).css("color","#000");
		});
		$("#ss").blur(function(){
			$(this).val("行车记录仪");
			$(this).css("color","#ccc");
		});
	});	
	
	/** 打开购物车**/
	$(function(){
		$(".ceng").hide();
	})
	function opencar(){
		$(".ceng").show();
	}
	function closecar(){
		$(".ceng").hide();
	}

/*首页浮窗*/
/*function openthis(num){
	$(".float"+num).show();
	$(".tab"+num).css({"background":"#F7F7F7","color":"#C40001"})	
	$(".con"+num).css({"color":"#C40001"})
	$(".lin"+num).css({"border-color":"#fff"});
}
function outthis(num){
	$(".float"+num).hide();
	$(".tab"+num).css({"background":"#DC2A36","color":"#fff"})	
	$(".con"+num).css({"color":"#fff"})
	$(".lin"+num).css({"border-color":"#C40001"});	
}*/

$(function(){
				$('.navRight li').mouseenter(function(){
					$(this).children('span').css('transform','rotate(135deg)');
				}).mouseleave(function(){
					$(this).children('span').css('transform','rotate(-45deg)');
				})
				$('.fenlei1 ul li').mouseenter(function(){
					//$(this).stop().animate({'height':'289px'},300).siblings().stop().animate({'height':'44px'},300);
				//}
					var this_text = $(this).children().children().attr("id");
					
						$(this).css('background','#FFFFFF');
						$(this).children().children().css('color','#0f5489');
						$(this).children().children().children().css('color','#0f5489');
					
					/*for(var i=0;i<1;i++){
						$(".fenleiright").append("<dl></dl>").append("<dt>上衣</dt>").append("<dd></dd>").append("<a href='javascript:;'>白衬衫 </a>");;
					}*/
					
				if(this_text=="fenleiLeft_kc"){
						$('.fenleiright').stop().animate({'width':'0px'},300);
						$('#fenleiright_kc').fadeTo(0,1).stop().animate({'width':'960px'},3000);
					}else if(this_text=="fenleiLeft_jc"){
						$('.fenleiright').stop().animate({'width':'0px'},300);
						$('#fenleiright_jc').fadeTo(0,1).stop().animate({'width':'960px'},3000);
					}else if(this_text=="fenleiLeft_pj"){
						$('.fenleiright').stop().animate({'width':'0px'},300);
						$('#fenleiright_pj').fadeTo(0,1).stop().animate({'width':'960px'},3000);
					}else{
						$('.fenleiright').stop().animate({'width':'0px'},300);
					}
					
					
					//$('.fenleiright').fadeTo(0,0.9).stop().animate({'width':'960px'},300);
				}).mouseleave(function(){
					//$('.fenlei ul li').stop().animate({'height':'79px'},300)
					$(this).children().children().css('color','#FFFFFF');
					$(this).children().children().children().css('color','#FFFFFF');
					$(this).css('background','#0f5489');
				});
				$('.left').mouseleave(function(){
					$('.fenleiright').stop().animate({'width':'0px'},300);
				})
				
			})