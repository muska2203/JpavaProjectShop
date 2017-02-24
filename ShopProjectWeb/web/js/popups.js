$('.btn-account').on("click", function(){
    $('.popup-account').addClass('open');
});
$('.btn-login').on('click', function(){
    $('.popup-login').addClass('open');
});
$('.popup-close').on("click", function(){
    $('.popup-window-template').removeClass('open');
});
