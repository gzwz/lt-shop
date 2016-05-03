var oPag = document.getElementById('pagingbox');
var aLi = oPag.getElementsByTagName('li');


for(var i=0; i<aLi.length; i++){
    aLi[i].index=i;
    aLi[i].onclick = function (){
        for(var i=0; i<aLi.length; i++){
            aLi[i].className ="";
        }
        aLi[this.index].className="curr";
    }
}


var oBrc = document.getElementById('brandalls');
var oBr = document.getElementById('brandall');
var aBrand = oBr.getElementsByClassName('brand');
var aDd = oBrc.getElementsByTagName('dd');


for(var i=0; i<aDd.length; i++){
    aDd[i].index=i;
    aDd[i].onclick = function (){
        for(var i=0; i<aDd.length; i++){
            aDd[i].className ="";
            aBrand[i].style.display="none";
        }
        aDd[this.index].className="curr";
        aBrand[this.index].style.display="block";
    }
}



var oMarq1 = document.getElementById('marquee1');
var aDds1 = oMarq1.getElementsByTagName('dd');

byname(aDds1);

var oMarq2 = document.getElementById('marquee2');
var aDds2 = oMarq2.getElementsByTagName('dd');

byname(aDds2);

var oMarq3 = document.getElementById('marquee3');
var aDds3 = oMarq3.getElementsByTagName('dd');

byname(aDds3);

var oMarq4 = document.getElementById('marquee4');
var aDds4 = oMarq4.getElementsByTagName('dd');

byname(aDds4);

var oMarq5 = document.getElementById('marquee5');
var aDds5 = oMarq5.getElementsByTagName('dd');

byname(aDds5);

var oMarq6 = document.getElementById('marquee6');
var aDds6 = oMarq6.getElementsByTagName('dd');

byname(aDds6);

var oMarq7 = document.getElementById('marquee7');
var aDds7 = oMarq7.getElementsByTagName('dd');

byname(aDds7);

var oMarq8 = document.getElementById('marquee8');
var aDds8 = oMarq8.getElementsByTagName('dd');

byname(aDds8);

var oMarq9 = document.getElementById('marquee9');
var aDds9 = oMarq9.getElementsByTagName('dd');

byname(aDds9);

var oMarq10 = document.getElementById('marquee10');
var aDds10 = oMarq10.getElementsByTagName('dd');

byname(aDds10);

var oMarq11 = document.getElementById('marquee11');
var aDds11 = oMarq11.getElementsByTagName('dd');

byname(aDds11);


function byname(obj){
    for(var i=0; i<obj.length; i++){
    obj[i].index =i;
    obj[i].onclick = function (){
        for(var i=0; i<obj.length; i++){
            obj[i].className ='';
        }
        obj[this.index].className = "curr";
    }
}
}
            

$('#trate').find('span').click(function (){
    $('#trate').find($(this),'span').parents('li').hide(500);
})


$('#tratea').click(function (){
    $('#trate').find('.tratecurr').hide(500);
})

















