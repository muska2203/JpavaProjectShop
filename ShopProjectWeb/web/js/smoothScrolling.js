//using jQuery
var linksForSmoothScroll = [
	'.btn-up',
	'.btn-shop-cart',
	'.nav a'
];
$(document).ready(function(){
	for(var i = 0; i < linksForSmoothScroll.length; i++){
		$(linksForSmoothScroll[i]).on("click", function (event) {
			//отменяем стандартную обработку нажатия по ссылке
			event.preventDefault();
			//забираем идентификатор бока с атрибута href
			var id  = $(this).attr('href'),
			//узнаем высоту от начала страницы до блока на который ссылается якорь
				top = $(id).offset().top;
			//анимируем переход на расстояние - top за 700 мс
			$('body,html').animate({scrollTop: top}, 700);
		});
	}
});