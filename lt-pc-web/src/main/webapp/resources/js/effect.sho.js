
$(function(){
    
// 服务协议

var oCheck = document.getElementById('check');
var oConfirm = document.getElementById('confirmbut');

if( oCheck.checked != true){
        oConfirm.style.backgroundColor='#aaa';
        oConfirm.disabled="disabled"
        
    }else{
        oConfirm.style.backgroundColor='#c8002e';
        oConfirm.disabled=""
    }



oCheck.onclick = function (){
    if( oCheck.checked != true){
        oConfirm.style.backgroundColor='#aaa';
        oConfirm.disabled="disabled"
        
    }else{
        oConfirm.style.backgroundColor='#c8002e';
        oConfirm.disabled=""
    }
}


// end 服务协议

// 多选择
/*
var oDelall = document.getElementById('delall');
var oCheckall = document.getElementById('checkall');
var oMainbox = document.getElementById('mainbodybox');
var aLide = oMainbox.getElementsByTagName('li'); 
var aProduct = oMainbox.getElementsByClassName('product');
var b=true;


oCheckall.onclick = function (){
    if(oCheckall.checked==true){
        for(var i=0; i<aProduct.length; i++){
            aProduct[i].checked=true;
        }
    }else{
        for(var i=0; i<aProduct.length; i++){
            aProduct[i].checked=false;
        }
    }
}

for(var i=0; i<aProduct.length; i++){
    aProduct[i].onclick = function(){
        for(var i=0; i<aProduct.length; i++){
            if(aProduct[i].checked==true){
                b=true;
            }else{
                b=false;
                break; 
            }
        }
         if(b){
             oCheckall.checked=true;
         }else{
             oCheckall.checked=false;
         }
    }
}*/

/*
$('.curr6').find('a').click(function (){
    $('.curr6').find($(this),'a').parents('li').hide(500);
})

$('ul#mainbodybox').children('li:odd').css('background','#f2f2f2')

var $t=$('.product');

$('.delall').click(function (){
    $("input:checkbox:checked").parents('li').hide(500);
    oCheckall.checked=false;
})
*/

// end 多选择

var a = ($(window).width()-$('.suspen').width())/2-1+'px';
$('.suspen').css('left',a);
    
    
})


window.onscroll = function(){
    var h = parseInt(($('#mainbodybox').height()+287)-$(window).height());
    if($(window).scrollTop()<h){
    $('.suspen').css('display','block')
}else if($(window).scrollTop()>h){
    $('.suspen').css('display','none')
}
} 























