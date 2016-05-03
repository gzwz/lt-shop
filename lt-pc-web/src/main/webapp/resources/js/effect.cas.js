$('.aggr li').click(function(){
   $(this).siblings().removeClass('currt');
   $('.aggr li').children('i').removeClass('curr');
   $(this).addClass('currt');
   $(this).children('i').addClass('curr');
   getPayMent($(this).attr('value'));
})

$('.pitch').click(function(){
    $('.aggr li').siblings().removeClass('currt');
    $('.aggr li').children('i').removeClass('curr');
    $('.network li').children('i').removeClass('curr');
    $('.ba3').removeClass('ba4');
    $('.below').css('visibility','hidden');
    $(this).addClass('currt').siblings().removeClass('currt');
    $(this).children('i').addClass('curr');
    getPayMent($(this).find('input').attr('value'));
    return false;
})

$('.ba3').click(function(){
    $('.network li').children('i').removeClass('curr');
    $(this).addClass('ba4').siblings().removeClass('currt');
    $('.below').css('visibility','visible');
})