var cartItems = document.getElementsByClassName('card-items-value')[0];
var cartMoney = document.getElementsByClassName('card-money-value')[0];
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
function addToCart(itemId){
    var req = new newXMLHttpRequest();
    req.open("POST", "TestServlet", true);
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send();
    var str;
    req.onreadystatechange = function() { 
        if (req.readyState != 4) return;
          console.log('Finish');
        if (req.status != 200) {
          console.log('Error!');
        } else {
          str = req.responseText;
        }
    }
    return str;
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

cartItems.innerHTML = 0;
cartMoney.innerHTML = 0;
for(var i = 0; i < btnAddCart.length; i++){
    btnAddCart[i].addEventListener("click", function(){
        $(this).addClass('clicked');
        var value = +addToCart(i);
        cartMoney.innerHTML = value;
    });
}