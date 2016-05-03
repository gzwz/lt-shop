//加数

(function ($) {
  $.fn.spinner = function (opts) {
    return this.each(function () {
      var defaults = {value:1, min:0}
      var options = $.extend(defaults, opts)
      var keyCodes = {up:38, down:40}
      var container = $('<div></div>')
      container.addClass('spinner')
      var textField = $(this).addClass('value').attr('maxlength', '2').val(options.value)
        .bind('keyup paste change', function (e) {
          var field = $(this)
          if (e.keyCode == keyCodes.up) changeValue(1)
          else if (e.keyCode == keyCodes.down) changeValue(-1)
          else if (getValue(field) != container.data('lastValidValue')) validateAndTrigger(field)
        })
      textField.wrap(container)

      var increaseButton = $('<button class="increase">+</button>').click(function () { changeValue(1) })
      var decreaseButton = $('<button class="decrease">-</button>').click(function () { changeValue(-1) })

      validate(textField)
      container.data('lastValidValue', options.value)
      textField.before(decreaseButton)
      textField.after(increaseButton)

      function changeValue(delta) {
        textField.val(getValue() + delta)
        validateAndTrigger(textField)
      }

      function validateAndTrigger(field) {
        clearTimeout(container.data('timeout'))
        var value = validate(field)
        if (!isInvalid(value)) {
          textField.trigger('update', [field, value])
        }
      }

      function validate(field) {
        var value = getValue()
//        alert(value);
        if (value <= options.min) decreaseButton.attr('disabled', 'disabled')
        else decreaseButton.removeAttr('disabled')
        field.toggleClass('invalid', isInvalid(value)).toggleClass('passive', value === 0)

        if (isInvalid(value)) {
          var timeout = setTimeout(function () {
            textField.val(container.data('lastValidValue'))
            validate(field)
          }, 500)
          container.data('timeout', timeout)
        } else {
          container.data('lastValidValue', value)
        }
        return value
      }        
      function isInvalid(value) { return isNaN(+value) || value < options.min; }

      function getValue(field) {
        field = field || textField;
        return parseInt(field.val() || 0, 10)
      }
    })
  }
})(jQuery)



$('.spinner').spinner();


//  end 加数

//技付方式
var oPay = document.getElementById('pay');
var aLi = oPay.getElementsByTagName('li');
var aIa = oPay.getElementsByTagName('i');
for (var i=0; i<aLi.length; i++){
    aLi[i].index = i;
    aLi[i].onclick = function () {
        for(var i=0; i<aLi.length; i++){
            aLi[i].id = '';
            aIa[i].className = '';
        }
        aLi[this.index].id = 'curricon';
        aIa[this.index].className = 'currs';
    }
}
// end 技付方式

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
}


$('.curr6').find('a').click(function (){
    $('.curr6').find($(this),'a').parents('li').hide(500);
})

$('ul#mainbodybox').children('li:odd').css('background','#f2f2f2')

var $t=$('.product');

$('#delall').click(function (){
    $("input:checkbox:checked").parents('li').hide(500);
    oCheckall.checked=false;
})


// end 多选择




































