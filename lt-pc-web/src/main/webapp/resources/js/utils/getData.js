/**
 * 	获取重庆时时彩 数据
 *	http://f.apiplus.cn/cqssc.json
 *	返回json
 *	需要引入jQuery
 */

//获取重庆时时彩数据 
var expect ;
var opencode ;
var opentime ;
var opentimestamp;

$(function(){
	cache: false,
	getsscData();
}); 
	
function getsscData(){
	$.ajax({
			cache: false,
			url : "http://f.apiplus.cn/cqssc-1.json", 
			type : "get",
			dataType : "JSONP", 
			data : "",
			success :function(data){
				
	//		alert(" "+data.data[0].expect+"  "+data.data[0].opencode+"  "+data.data[0].opentime+"   "+data.data[0].opentimestamp);	
			expect = data.data[0].expect;
			opencode = data.data[0].opencode;
			opentime = data.data[0].opentime;
			opentimestamp = data.data[0].opentimestamp;
			
			var str =data.data[0].opentime;
			str = str.replace(/-/g,"/");
			var date = new Date(str);
			var seconds = parseInt(900-((new Date().getTime()-date.getTime())/1000));
			timer(seconds);
			}
		});
	}
	
	function getResult(){
	$(".lottery_progress").hide();
	$(".progressbar").show();
	$(".buttonToTime").removeAttr("href"); 
	$(".buttonToTime").html("正在计算结果...");
	increase();
	
}
	var n = 0;
	function increase(){
		if (n < 100) {
			$(".progressbar .green").attr("style","width: "+ (n+1)+"%;");
			$(".progressbar span").html((n+1)+"%");
			n++;
		} 
		setTimeout(function() {
			if (n < 100) {
				increase(); 
			} 
		},60)
		if (n == 100) {
		//	alert("计算完毕"+expect+"  "+opencode+"  "+opentime+"  "+opentimestamp);
			calculateResult();
		}
	}
	
	
	
