$('.btn--shop_cart_fixed').on("click", function(){
    $('.shop_cart_fixed').toggleClass('open');
});

var cartItems = document.getElementsByClassName('cart_items');
var cartMoney = document.getElementsByClassName('cart_price');
var btnAddCart = document.getElementsByClassName('btn-add-card');

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
function setCookie(cname,cvalue,exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires=" + d.toGMTString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}
function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
function removeCookie(cname){
    setCookie(cname,0,0);
}
function removeAllCookies(){
    var decodedCookie = decodeURIComponent(document.cookie);
    var cookieValues = decodedCookie.split(';');
    for (var i = 0; i < cookieValues.length; i++){
        cookieValues[i] = cookieValues[i].split('=')[0];
        removeCookie(cookieValues[i]);
    }
}
function addToCart(money){
    if (money)
        money = parseFloat(money);
    else
        money = 0; 
    for (var i = 0; i < cartItems.length; i++)
        cartItems[i].innerHTML = +cartItems[i].innerHTML + 1;
    for (var i = 0; i < cartMoney.length; i++)
        cartMoney[i].innerHTML = +cartItems[i].innerHTML + money;
}
function setCart(items, money){
    items = parseInt(items);
    money = parseFloat(money);
    if (isNaN(items) || !isFinite(items) || items == "")
        items = 0;
    if (isNaN(money) || !isFinite(money) || money == "")
        money = 0;  
    for (var i = 0; i < cartItems.length; i++)
        cartItems[i].innerHTML = items;
    for (var i = 0; i < cartMoney.length; i++)
        cartMoney[i].innerHTML = money;
}
function setUser(login, password){
    setCookie('login',login,30);
    setCookie('password',password,30);
}

/* init user */
if(document.cookie){
    var userLogin = getCookie('login');
    var userPassword = getCookie('password');
    if(userLogin != "" && userPassword != ""){
        var req = new newXMLHttpRequest();
        req.open("POST", "UserLogin", true);
        req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        req.send("login="+userLogin+"&password="+userPassword);
        req.onreadystatechange = function() { 
             if(this.readyState == 4 && this.status == 200){
                var jsonData = JSON.parse(req.responseText);
                setCart(jsonData["count"],jsonData["price"]);
            }
        }
    }
}
else{
    setCart(0,0);
}
/* init user */

$('.btn-checkout').on('click', function(){
    removeAllCookies();
    setCart(0,0);
});

var formSignIn = document.getElementsByClassName('account_form-sign-in');
for( var i = 0; i < formSignIn.length; i++){
    formSignIn[i].addEventListener("submit", function(event){
        $('.popup-sign-in').removeClass('open');
        event.preventDefault();
        var login = this.elements.login.value;
        this.elements.login.value = "";
        var password = this.elements.password.value;
        this.elements.password.value = "";
        var req = new newXMLHttpRequest();
        req.open("POST", "UserLogin", true);
        req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        req.send("login="+login+"&password="+password);
        req.onreadystatechange = function() { 
            if(this.readyState == 4 && this.status == 200){
                var jsonData = JSON.parse(req.responseText);
                if (jsonData["result"]){
                    setCart(jsonData["count"],jsonData["price"]);
                    setUser(login, password);
                }
            }
        }
    });
}
var formSignUp = document.getElementsByClassName('account_form-sign-up');
for(var i = 0; i < formSignUp.length; i++){
    formSignUp[i].addEventListener("submit", function(event){
        event.preventDefault();
        var login = this.elements.login.value;
        var email = this.elements.email.value;
        var password = this.elements.password.value;
        var passwordAgain = this.elements.passwordAgain.value;
        if (password == passwordAgain){
        }
        else{
            alert("Bad matched passwords")
        }
        $('.popup-sign-up').removeClass('open');
        this.elements.login.value = "";
        this.elements.email.value = "";
        this.elements.password.value = "";
        this.elements.passwordAgain.value = "";
        var req = new newXMLHttpRequest();
        req.open("POST", "UserRegistration", true);
        req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        req.send("login="+login+"&password="+password+"&email="+email);
        req.onreadystatechange = function() { 
            if(this.readyState == 4 && this.status == 200){
                var jsonData = JSON.parse(req.responseText);
                if (jsonData["result"]){
                    setCart(jsonData["count"],jsonData["price"]);
                    setUser(login, password);
                }
            }
        }
    });
}

for(var i = 0; i < btnAddCart.length; i++){
    btnAddCart[i].addEventListener("click", function(){
        $(this).addClass('clicked');
        var req = new newXMLHttpRequest();
        req.open("POST", "UserLogin", true);
        req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        req.send();
        req.onreadystatechange = function() { 
            if(this.readyState == 4 && this.status == 200){
                var jsonData = JSON.parse(req.responseText);
                str = +req.responseText;
                addToCart(str);
            }
        }
    });
}
