<%-- 
    Document   : index_test
    Created on : 20.02.2017, 20:23:50
    Author     : admin
--%>

<%@page import="java.util.Set"%>
<%@page import="folder.Item"%>
<%@page import="folder.Items"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Online Shop</title>
	<link rel="stylesheet" type="text/css" href="libs/font-awesome-4.7.0/css/font-awesome.css">
	<link rel="stylesheet" href="css/main.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<%
    Map<String,String[]> map = request.getParameterMap();
    Items items = new Items(map);
    items.findItems();
    Set<Item> set = items.getItems();
%>
<body>
	<div class="wrapper intro">
            <section id="intro"></section>
		<div class="info">
			<div class="info-support">
				<a href="tel:+375291555294">
					<span class="fa fa-mobile"></span>
					+375 29 155 52 94
				</a>
				<a href="mailto:maximwow70@gmail.com">
					<span class="fa fa-envelope"></span>
					maximwow70@gmail.com
				</a>
			</div><!--
			--><div class="info-account">
				<ul>
					<li><a href="#">my account</a></li><!--
					--><li><a href="#">login</a></li><!--
					--><li><a href="#">ckeckout</a></li><!--
					--><li><a href="#">sign up</a></li>
				</ul>
			</div>
		</div>
		<div class="header-container">
			<header>
				<div class="logo">
					<img src="img/logo.png" alt="">
				</div><!--
				--><div class="nav">
					<ul>
						<li><a href="#">home</a></li><!--
						--><li><a href="#">apparel</a></li><!--
						--><li><a href="#">fasion</a></li><!--
						--><li><a href="#">contact us</a></li>
					</ul>
				</div><!--
				--><div class="shop-card">
					<div class="shop-card-info">
						<span class="card-items"><span class="red-color card-items-value">1</span> items</span>
						<span class="card-money"><span class="red-color card-money-value">125</span> $</span>
					</div><!--
					--><div class="shop-card-icon">
						<a href="#"><img src="img/shop-card.png" alt=""></a>
					</div>
				</div>
			</header>
		</div>
		<div class="slider-container">
			<div class="slider">
				<div class="slide1" id="slide1">
					
				</div>
			</div>
		</div>
                <div class="intro-features">
			<div class="intro-features-box">
				<h3>free shiping</h3>
				<p>- on all orders above <span class="red-color">$99</span></p>
			</div><!--
			--><div class="intro-features-box">
				<h3>new shoes collection</h3>
				<p>- brand new collection of <span class="red-color">Bulls Shoes</span></p>
			</div><!--
			--><div class="intro-features-box">
				<h3>money back guarantee</h3>
				<p>- on all orders above <span class="red-color">$99</span></p>
			</div>
		</div>
	</div>
	<div class="wrapper items">
		<div class="items-container">
			<div class="items-interface">
				<form action="index_test.jsp" class="items-filter" name="items-filter" target="">
					<legend>Filter:</legend>
					<fieldset>
						<label for="id">id:</label><!--
                                                --><input id="id" type="text" placeholder="<%=items.getId() %>" name="id">
					</fieldset>
					<fieldset>
						<label for="tags">tags:</label><!--
						--><input id="tags" type="text" value="<%=items.getName()%>" name="name">
					</fieldset>
					<fieldset>
						<label for="cost">price:</label><!--
						<div class="cost-interface"></div>
						--><div class="cost-range">
							<input class="cost" type="text" value="<%=items.getCostMin() %>" name="costMin">
                                                        <input class="cost" type="text" value="<%=items.getCostMax() %>" name="costMax">
						</div>
					</fieldset>
                                                <fieldset>
						<label for="size">size:</label>
						<div class="item-sizes">
                                                        <input type="checkbox" id="size1" name="size" value="20"><label for="size1">20</label><!--
							--><input type="checkbox" id="size2" name="size" value="21"><label for="size2">21</label><!--
							--><input type="checkbox" id="size3" name="size" value="22"><label for="size3">22</label><!--
							--><input type="checkbox" id="size4" name="size" value="23"><label for="size4">23</label><!--
							--><input type="checkbox" id="size5" name="size" value="24"><label for="size5">24</label><!--
							--><input type="checkbox" id="size6" name="size" value="25"><label for="size6">25</label><!--
							--><input type="checkbox" id="size7" name="size" value="26"><label for="size7">26</label><!--
							--><input type="checkbox" id="size8" name="size" value="27"><label for="size8">27</label><!--
							--><input type="checkbox" id="size9" name="size" value="28"><label for="size9">28</label><!--
							--><input type="checkbox" id="size10" name="size" value="29"><label for="size10">29</label><!--
							--><input type="checkbox" id="size11" name="size" value="30"><label for="size11">30</label><!--
							--><input type="checkbox" id="size12" name="size" value="31"><label for="size12">31</label><!--
							--><input type="checkbox" id="size13" name="size" value="32"><label for="size13">32</label>
						</div>
					</fieldset>
					<fieldset>
						<label for="size">color:</label>
						<div class="item-colors">
							<input type="checkbox" id="black-color" name="color" value="black"><label for="black-color"></label><!--
							--><input type="checkbox" id="white-color" name="color" value="white"><label for="white-color"></label><!--
							--><input type="checkbox" id="red-color" name="color" value="red"><label for="red-color"></label><!--
							--><input type="checkbox" id="yellow-color" name="color" value="yellow"><label for="yellow-color"></label><!--
							--><input type="checkbox" id="blue-color" name="color" value="blue"><label for="blue-color"></label><!--
							--><input type="checkbox" id="green-color" name="color" value="black"><label for="green-color"></label><!--
							--><input type="checkbox" id="blue-color"><label for="blue-color"></label><!--
							--><input type="checkbox" id="red-color"><label for="red-color"></label><!--
							--><input type="checkbox" id="yellow-color"><label for="yellow-color"></label><!--
							--><input type="checkbox" id="blue-color"><label for="blue-color"></label><!--
							--><input type="checkbox" id="yellow-color"><label for="yellow-color"></label><!--
							--><input type="checkbox" id="blue-color"><label for="blue-color"></label><!--
							--><input type="checkbox" id="yellow-color"><label for="yellow-color"></label><!--
							--><input type="checkbox" id="blue-color"><label for="blue-color"></label><!--
							--><input type="checkbox" id="red-color"><label for="red-color"></label><!--
							--><input type="checkbox" id="yellow-color"><label for="yellow-color"></label><!--
							--><input type="checkbox" id="blue-color"><label for="blue-color"></label><!--
							--><input type="checkbox" id="yellow-color"><label for="yellow-color"></label><!--
							--><input type="checkbox" id="blue-color"><label for="blue-color"></label>
						</div>
					</fieldset>
					<input type="submit" value="search">
				</form>
			</div><!--
			--><div class="items-list">
                            
                            <%
                            for(Item it : set){
                            %>
                            <div class="item-container">
					<div class="item">
						<img src="img/items/item<%=it.getId()%>.png" alt="">
						<span class="description">
							<a href="#"><%=it.getName()%></a>
						</span>
						<span class="price"><span class="item-price"><%=it.getCost()%></span> $</span>
						<button class="button btn-add-card">add to card</button>
					</div>
				</div>
                            <%}%>
                            
			</div>
		</div>
	</div>
	<div class="wrapper items-template latest-items">
		<div><h2><span>our latest items</h2></span></div>
		<div class="items-container">
			<div class="items-list">
				<div class="item-container">
					<div class="item">
						<img src="img/items/item3.png" alt="">
						<span class="description">
							<a href="#">Bullsy Snickers that bring you a lot of pleasure</a>
						</span>
						<span class="price"><span class="item-price">145</span> $</span>
						<button class="button btn-add-card">add to card</button>
					</div>
				</div><!--
				--><div class="item-container">
					<div class="item">
						<img src="img/items/item4.png" alt="">
						<span class="description">
							<a href="#">Bullsy grey Snickers / Shoes</a>
						</span>
						<span class="price"><span class="item-price">145</span> $</span>
						<button class="button btn-add-card">add to card</button>
					</div>
				</div><!--
				--><div class="item-container">
					<div class="item">
						<img src="img/items/item1.png" alt="">
						<span class="description">
							<a href="#">Bullsy grey Snickers / Shoes</a>
						</span>
						<span class="price"><span class="item-price">145</span> $</span>
						<button class="button btn-add-card">add to card</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="wrapper items-template featured-items">
		<div><h2><span>featured items</h2></span></div>
		<div class="items-container">
			<div class="items-list">
				<div class="item-container">
					<div class="item">
						<img src="img/items/item1.png" alt="">
						<span class="description">
							<a href="#">Bullsy grey Snickers / Shoes</a>
						</span>
						<span class="price"><span class="item-price">145</span> $</span>
						<button class="button btn-add-card">add to card</button>
					</div>
				</div><!--
				--><div class="item-container">
					<div class="item">
						<img src="img/items/item2.png" alt="">
						<span class="description">
							<a href="#">Rough Bullsy Shoes for Winter</a>
						</span>
						<span class="price"><span class="item-price">99.9</span> $</span>
						<button class="button btn-add-card">add to card</button>
					</div>
				</div><!--
				--><div class="item-container">
					<div class="item">
						<img src="img/items/item3.png" alt="">
						<span class="description">
							<a href="#">Bullsy Snickers that bring you a lot of pleasure</a>
						</span>
						<span class="price"><span class="item-price">145</span> $</span>
						<button class="button btn-add-card">add to card</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="wrapper quote">
		<h2>Bullsy - a rugged store for rugged people</h2>
		<p>- read more about our store regulations and <a href="" hfer="#"><span class="red-color">policy</span></a> here. Lorem ipsum dolor sit amet</p>
	</div>
	<div class="wrapper map" id="map-container">
		<div id="map"></div>
		<div class="map-cover"></div>
		<button class="btn-map-open"><span class="fa fa-chevron-down"></span></button>
	</div>
	<div class="wrapper footer">
		<div class="main-footer">
			<div class="main-footer-logo">
				<img src="img/logo-light.png" alt="">
			</div><!--
			--><div class="main-footer-info">
				© 2013 copyright PREMIUMCODING // All rights reserved
			</div><!--
			--><div class="main-footer-button">
				<button class="btn-up"><a href="#intro"><span class="fa fa-chevron-up"></span></a></button>
			</div>
		</div>
		<div class="footer-info">
			<div class="footer-info-icons">
				<a href="#"><div class="fa fa-github"></div></a>
				<a href="#"><div class="fa fa-facebook"></div></a>
				<a href="#"><div class="fa fa-instagram"></div></a>
				<a href="#"><div class="fa fa-google"></div></a>
				<a href="#"><div class="fa fa-twitter-square"></div></a>
			</div><!--
			--><div class="footer-info-developers">
				Designed by <a href="#">PremiumCoding.com</a> Web Design
			</div>
		</div>
	</div>

	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/map.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAoSTLEwKV2euYcXzQoBKUimW4KdBGGPm0&callback=initMap" async defer></script>
	<script src="js/smoothScrolling.js"></script>
	<script src="js/shop-cart.js"></script>
</body>
</html>
