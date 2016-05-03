var defaultBrandId="";	
 var jsonMyPagination;
 var contextPath = $("#contextPath").val();
		$(function() {
			
			var b = true;
			$(".clearfix dd").bind("click",function() {
			                $("#trate").show(200);
							var bb=$(this).parent().find(".parent-value").attr("accesskey");
							var cb=$(this).parent().find(".parent-value").text();
								if ($(this).text() != "无限") {
									$(this).siblings("dd").removeClass("curr");
									$(this).addClass("curr");
									var value = $(this).text();
									var innerHtml = "<li class='tratecurr' id='"+$(this).parent().find(".parent-value").attr("accesskey")+"'><input type='hidden' value='"
											+ $(this).attr("accesskey")+","+cb+ "' name='shaixuan'><div>"
											+ $(this).text()
											+ "</div><span>×</span></li>";

									$(".tratecurr").each(function() {
												if (value == $(this).find("div").text()) {
													b = false;
													return false;
												} else {
												  if($(this).attr("id")==bb){
												   $(this).remove();
												  }
													b = true;
												}
											});
									if (b) {
										$('#trate').find('li').eq(0).after(innerHtml);
									    shaixuanBrand();
									}
								} else {
									$(this).siblings("dd").removeClass("curr");
									$(this).addClass("curr");
									getProduct();
									$(".tratecurr").each(function() {
												  if($(this).attr("id")==bb){
												   $(this).remove();
												   return false;
												  }
											});
											
											
								}
								
								//品牌
								if(bb=="123456"){
								$(".brand-nav").html("");
								 $(".brand-nav").html($(this).text());
								 getProduct();
								}
							});

			$('body').on("click","#trate .tratecurr",function() {
								var value = $(this).find("div").text();
								if($("#trate li").length=="3"){
								   $("#trate").hide(200);
								}
								$("#shaixuan dl dd").each(function() {
								
								if ($(this).text() == value) {
									$(this).removeClass("curr");
									if ($(this).parent().find("#limite").text() == "无限"|| $(this).parent().find("#limite").text() == "A") {
										$(this).parent().find("#limite").addClass("curr");
										if($(this).parent().find("#limite").text() == "A"){
										$(".brand-nav").html("");
						                $(".brand-nav").html($(this).parent().find("#limite").text());
										}
										shaixuanBrand();
										getProduct();
									}
								}
							});
						$(this).remove();
							});

			$('#clean').click(function() {
				$("#shaixuan dl dd").each(function() {
				var bb=$(this).parent().find(".parent-value").attr("accesskey");
					if ($(this).text() == "无限" || $(this).text() == "A") {
						$(this).siblings("dd").removeClass("curr");
						$(this).addClass("curr");
						//品牌
						if(bb=="123456"){
						$(".brand-nav").html("");
						 $(".brand-nav").html($(this).text());
						}
						
					}
				});
				$(this).siblings('.tratecurr').remove();
				b = true;
				
				  window.location.reload();//刷新当前页面.
			})

     //切换
     $("#pagingbox li").bind("click",function() {
           $(this).siblings("li").removeClass("curr");
		   $(this).addClass("curr");
     });
     
	     //品牌切换
	    $('body').on("click",".borands li",function() {
	           $(this).siblings("li").removeClass("curr");
			   $(this).addClass("curr");
			   getProduct();
	     });
	     
	     //默认品牌
        $("#shaixuan dl dd").each(function() {
				  if($(this).attr("class")=="curr"||$(this).attr("class")=="curr getbrandId"||$(this).attr("class")=="getbrandId curr"){
					  $(".brand-nav").html("");
					 $(".brand-nav").html($(this).text());
				  }
			});
        
        //根据A品牌加载数据
        getProductBrand($(".getbrandId").attr("accesskey"));
        
        
       });
	function onReload(){
			jsonMyPagination.onReload();
			alert("重新加载");
		};
		
		function selectB(){
		var currId="";
	    $(".borands li").each(function(){
	    
		    if($(this).attr("class")=="curr"){
		    currId= $(this).attr("accesskey");
		    }
	    
	    });
	 return currId;
	}
	
	//筛选商品
	
	function shaixuanProduct(){
	       var selectedId="";
			 $("#shaixuan dl dd").each(function() {
				  if($(this).attr("class")=="curr"||$(this).attr("class")=="curr getbrandId"||$(this).attr("class")=="getbrandId curr"){
				    /*  alert($(this).attr("accesskey")); */
					  if("undefined" != typeof $(this).attr("accesskey")){
					    if($(this).parent().find("dt").text()!="品牌："){
					      selectedId+=$(this).attr("accesskey")+",";
					    }
					  }
				  }
			});
			
			return selectedId;
			
	}
	
	
	function shaixuanBrand(){
			 $("#shaixuan dl dd").each(function() {
				  if($(this).attr("class")=="curr"||$(this).attr("class")=="curr getbrandId"||$(this).attr("class")=="getbrandId curr"){
					  if("undefined" != typeof $(this).attr("accesskey")){
					    
					    if($(this).parent().find("dt").text()=="品牌："){
					        //品牌加载数据
		                    getProductBrand($(this).attr("accesskey"));
					    }
					    
					  }
				  }
			});
			
			
	}
		
	//加载品牌	
	function getProductBrand(selectBrandId){
	
		     $.ajax({
						type : "post",
						url : contextPath+"/product/getBrandDefault",
						dataType : "json",
						async : true,
						data : {brandId:selectBrandId,productCategoryIds:$("#productCategoryid").val()},
						success : function(data) {
						  var result="";
							if(data.length>0){
							 for(var i=0;i<data.length;i++){
							    if(i=="0"){
							     result+="<li accesskey='"+data[i].id+"' class='curr'><img style='width:114px;height:114px;' src='http://file.e-kcw.com/"+data[i].signImage+"' alt='"+data[i].brandName+"'><p>"+data[i].brandName+"</p></li>";
							    }else{
							     result+="<li accesskey='"+data[i].id+"' class=''><img width='114px' height='114px'  src='http://file.e-kcw.com/"+data[i].signImage+"' alt='"+data[i].brandName+"'><p>"+data[i].brandName+"</p></li>";
							    }
							 }
							}
							
							$(".borands").html("");
							$(".borands").html(result);
							 getProduct();
						}
					});
	
	}	
		
		
	 //加载产品
	function getProduct(){
	
	jsonMyPagination = $("#demo5").myPagination({
          panel: {
            tipInfo_on: true,
            tipInfo: '  跳{input}/{sumPage}页',
            tipInfo_css: {
              width: '25px',
              height: "20px",
              border: "2px solid #f0f0f0",
              padding: "0 0 0 5px",
              margin: "0 5px 0 5px",
              color: "#48b9ef"
            }
		},
          ajax: {
            on: true,
             url: contextPath+"/product/getBrandDefaultProduct?brandId="+selectB()+"&screeningConditionId="+shaixuanProduct()+"&productCategoryId="+$("#productCategoryid").val(), 
            dataType: 'json',
            ajaxStart: function() {
             ZENG.msgbox.show(" 正在加载中，请稍后...", 6, 500); 
            },
            callback: function(data) {
            var result="";
            if("undefined" !=typeof data.list){
               if(data.list.length>0){
					 for(var i=0;i<data.list.length;i++){
					     result+="<li><a href='"+contextPath+"/product/detaile?id="+data.list[i].id+"&productCatoryName="+$("#productCategoryName").val()+"'><p class='p1'>"+data.list[i].showInfo.name+"</p><div class='imges'><img src='http://file.e-kcw.com/"+data.list[i].showInfo.titleImage+"' alt=''></div></a><p class='p2'>商城价：<span>"+data.list[i].showInfo.lowestPrice+"</span></p></li>";
					 }
					
				}
            }
             
				$("#product-list").html("");
				$("#product-list").html(result);
            }
            ,onClick:function(page){
            }
          }
          
        });
	
	}
	