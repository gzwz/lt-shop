function getStyle (obj,attr){
           if(obj.currentStyle){
               return obj.currentStyle[attr]
           }
           else{
               return getComputedStyle(obj, false)[attr]
           }
       }
       function css(obj,attr,value){
          if (arguments.length==2){
              return getStyle(obj,attr)
          }
          else if(arguments.length==3){
              obj.style[attr] = value;
          }
       }
function stopPropagation(e) {  
    e = e || window.event;  
    if(e.stopPropagation) { //W3C阻止冒泡方法  
        e.stopPropagation();  
    } else {  
        e.cancelBubble = true; //IE阻止冒泡方法  
    }  
}  



//轮播图

    var oCont = document.getElementById('container');
    var oList = document.getElementById('list');
    var oBut = document.getElementById('buttons').getElementsByTagName('span');
    var oPrev = document.getElementById('prev');
    var oNext = document.getElementById('next');
    var index = 1;
    var animated = false;       
    var timer ="";
    
    oNext.onclick = function () { 
        if (index==4){ 
            index=1;
        }else{
            index += 1;
        }
        showBut();
        if(!animated){                                  
            animatee(-730);  
        }
    }
    
    oPrev.onclick = function () {                               
        if (index==1){     
            index=4;
        }else{
            index -= 1;
        }
        showBut();
        if(!animated){                         
            animatee(730);  
        }
    }
    
    for (var i=0; i<oBut.length; i++){            
        oBut[i].onclick = function (){
            if (this.className == 'on'){            
            return;
        }
            var myIndex = parseInt(this.getAttribute('index'));
            var offset = -730 * (myIndex - index);
            animatee(offset);   
            index = myIndex;                     
            showBut();                            
        }
    }
    
    oCont.onmouseover = stop;
    oCont.onmouseout = play;
    
    play();
    
    
    function play(){
        timer = setInterval(function(){
            oNext.onclick();
        },3500);
    }
    
    function stop(){
        clearInterval(timer);
    }
    
    
    function showBut(){      
        for (var i=0; i<oBut.length; i++ )
            {
                oBut[i].className = '';
            }
        oBut[index-1].className = 'on';
    }
    
    function animatee(offset){      
        animated =true;                            
        var newlift = parseInt(oList.style.left) + offset;
        var time = 300; 
        var interval = 10; 
        var speed = offset/(time/interval); 
        
        function go(){
            if((speed < 0 && parseInt(oList.style.left) >newlift) || (speed > 0 &&      parseInt(oList.style.left) < newlift)){                      
                oList.style.left = parseInt(oList.style.left) + speed + 'px';   
                setTimeout(go,interval);                   
                
            }else{
                animated = false;                   
                oList.style.left = newlift +"px"; 
        
                if( parseInt(oList.style.left)>-730){   
                    oList.style.left = -2920 + "px";
                }
                if( parseInt(oList.style.left)<-2920){
                    oList.style.left = -730 + "px";
                }
            }
        }
        go();
        
    }
//  end 轮播图

//banner nav


    var supnav = document.getElementById("supnav");
	var nav = document.getElementById("nav");
	var aLi = supnav.getElementsByTagName("li");
	var aUl = nav.getElementsByTagName("div");
	var paddingbottom = 20;
	var defaultHeight = 0;
	function drop(obj, ivalue) {
		var a = obj.offsetHeight;
//        alert(a)
		var speed = (ivalue - obj.offsetHeight) / 8;
		a += Math.floor(speed);
		obj.style.height = a + "px";
	}
	window.onload = function() {
		for (var i = 0; i < aLi.length; i++) {
			aLi[i].index = i;
			aLi[i].onmouseover = function() {
				var aUlx = aUl[this.index];
				var aUlxa = aUlx.getElementsByTagName("a");
				if (aUlx.firstChild.tagName == undefined) {
//					var itarheight = parseInt(aUlx.childNodes[1].offsetHeight) * aUlxa.length + paddingbottom;
					var itarheight = 417;
				} else {
//					var itarheight = parseInt(aUlx.firstChild.offsetHeight) * aUlxa.length + paddingbottom;
					var itarheight = 417;
				}
				clearInterval(this.itimer);
				this.itimer = setInterval(function() {
					drop(aUlx, itarheight);
				},
				30);
			}
			aLi[i].onmouseout = function() {
				var aUlx = aUl[this.index];
				clearInterval(this.itimer);
				this.itimer = setInterval(function() {
					drop(aUlx, defaultHeight);
				},
				30);
			}
		}
	}
//end banner nav



































