$('.btn-account').on("click", function(){
    $('.popup-account').addClass('open');
});
$('.btn-sign-in').on('click', function(){
    $('.popup-sign-in').addClass('open');
});
$('.btn-sign-up').on('click', function(){
    $('.popup-sign-up').addClass('open');
});
$('.popup-close').on("click", function(){
    $('.popup').removeClass('open');
});
