<#assign contextPath=springMacroRequestContext.getContextPath() />
<#macro costPagination pagination>
	
	<div id="paginationDiv" class="paginationDiv" >
		<div style="display: none;">
			<input type = "hidden" value = "${pageData.totalCount}" name = "dataTotal" id = "dataTotal">
			<input type = "hidden" value = "${pageData.pageSize}" name = "pageSize" id = "pageSize">
			<input type = "hidden" value = ${pageData.pageNo} name = "currentPage" id = "currentPage">
			<input type = "hidden" value = "${(pageData.totalCount/pageData.pageSize)?ceiling}" name = "pageTotal" id = "pageTotal">
			
		</div>
	</div>
	<div>
	 	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/pagination/page.css" />
     <div class="bbox">
          <a class="page_fist" href="javascript:void(0);">首页</a>
          <a class="page_prev" href="javascript:void(0);">上一页</a>
          <div class="page">
              <a class="curr" href="javascript:void(0);" value="1">1</a>
          </div>
          <a class="page_next" href="javascript:void(0);">下一页</a>
          <a class="page_last" href="javascript:void(0);"  value="1">尾页</a>
     </div>
	</div>
	<div>
<script type="text/javascript">
$(function(){
    pagination($("#currentPage").val(),$("#dataTotal").val(),$("#pageSize").val());
    $('.page_fist').click(function(){
        $('.page a').eq(0).addClass('curr').siblings().removeClass('curr');
        show( $('.page a').eq(0).attr('value'));
        $('.page_prev').click(function(){return false;});
    });
    $('.page').on('click','a',function(){
        $(this).addClass('curr').siblings().removeClass('curr');
        show($(this).attr('value'));
    });

    $('.page_prev').click(function(){
        var a=$('.page a.curr').index();
        if(a>0){
            $('.page a').eq(a-1).addClass('curr').siblings().removeClass('curr');
            show( $('.page a').eq(a-1).attr('value'))
        }else{
            $('.page_prev').click(function(){return false;});
        }
    })
    $('.page_next').click(function(){
        var a=$('.page a.curr').index();
        var b=$('.page a').length;
        if(a<(b-1)){
            $('.page a').eq(a+1).addClass('curr').siblings().removeClass('curr');
            show($('.page a').eq(a+1).attr('value'))
        }else{
            $('.page_next').click(function(){return false;});
        }
    })
    $('.page_last').click(function(){
        $('.page a:last').addClass('curr').siblings().removeClass('curr');
        show($('.page a:last').attr('value'))
        $('.page_next').click(function(){return false;});
    })
})
function pagination(indexPage,tatolData,pageSize){
 	if(tatolData==0){
        $('.bbox').css('display','none').after('<div class="page_text">查无数据</div>');
    }
    var d=indexPage-1;
    var num=Math.ceil(tatolData/pageSize)-1;
    for(var i=0; i<num; i++){
        var pageling = $('<a href="javascript:void(0);">'+(i+2)+'</a>')
        pageling.appendTo(".page").attr('value',(i+2));
    }
    $('.page a').eq(d).addClass('curr').siblings().removeClass('curr');
}

function show(c){
    $("#currentPage").val(c);
     sendData(c);
};
//想后台传送数据
	function sendData(page){
	var url = window.location.href;
	if( parseInt(url.search("&page"))>0){
		var url2 = url.substring(0, parseInt(url.search("&page")))+"&page="+page
		window.location.href = url2;	
	}else{
		var url3 = window.location.href+"&page="+page
		window.location.href = url3;	
	}
	}

</script>
	</div>
</#macro>
