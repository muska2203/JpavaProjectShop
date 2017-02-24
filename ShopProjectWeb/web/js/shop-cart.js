$('.btn-cart-fixed').on("click", function(){
    $('.shop-cart-fixed').toggleClass('open');
});

var cartItems = document.getElementsByClassName('card-items-value');
var cartMoney = document.getElementsByClassName('card-money-value');
var btnAddCart = document.getElementsByClassName('btn-add-card');
cartItems.innerHTML = "1";
/*
 * Возвращает новый XMLHttpRequest объект или false, если браузер его не поддерживает
 */
function newXMLHttpRequest() {
  var xmlreq = false;
  if (window.XMLHttpRequest) {
    // Создадим XMLHttpRequest объект для не-Microsoft браузеров
    xmlreq = new XMLHttpRequest();
  } else if (window.ActiveXObject) {
    // Создадим XMLHttpRequest с помощью MS ActiveX
    try {
      // Попробуем создать XMLHttpRequest для поздних версий
      // Internet Explorer
      xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e1) {
      // Не удалось создать требуемый ActiveXObject
      try {
        // Пробуем вариант, который поддержат более старые версии
        //  Internet Explorer
        xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
      } catch (e2) {
        // Не в состоянии создать XMLHttpRequest с помощью ActiveX
      }
    }
  }
  return xmlreq;
}
var str = "начальное знач";
function addToCart(item){
    var req = new newXMLHttpRequest();
    req.open("POST", "TestServlet", false);
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send();
    /*
    req.onreadystatechange = function() { 
        if (req.readyState != 4) return;
          console.log('Finish');
        if (req.status != 200) {
          console.log('Error!');
        } else {
          str = req.responseText;
        }
    
    }*/
    str = req.responseText;
}

/*
function getReadyStateHandler(req) {
    // Возвращает неопределенную функцию, которая считывает 
    // данные XMLHttpRequest return function () {
    // Если требуется статус "закончен"
    if (req.readyState != 4) return;

    console.log('Готово!');

  if (req.status != 200) {
    alert(req.status + ': ' + xhr.statusText);
  } else {
    alert(req.responseText);
    }
}*/
for (var i = 0; i < cartItems.length; i++){
    cartItems[i].innerHTML = 0;
}
for (var i = 0; i < cartMoney.length; i++){
    cartMoney[i].innerHTML = 0;
}
for(var i = 0; i < btnAddCart.length; i++){
    btnAddCart[i].addEventListener("click", function(){
        $(this).addClass('clicked');
        var req = new newXMLHttpRequest();
        req.open("POST", "TestServlet", true);
        req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        req.send();
        req.onreadystatechange = function() { 
            if (req.readyState != 4) return;
              console.log('Finish');
            if (req.status != 200) {
              console.log('Error!');
            } else {
              var jsonData = JSON.parse(req.responseText);
              str = +req.responseText;
              for (var i = 0; i < cartItems.length; i++){
                console.log("+||");
                cartItems[i].innerHTML = +(cartItems[i].innerHTML) + 1;
              }
              console.log("\n");
              for (var i = 0; i < cartMoney.length; i++){
                console.log("-||");
                cartMoney[i].innerHTML = +(cartMoney[i].innerHTML) + (+str);
                
              }
              for (var datum in jsonData) console.log(jsonData[datum]);
            }
        }
    });
}