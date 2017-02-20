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
						<span class="card-items"><span class="red-color"><%=items.getMiniBasketCount()%></span> items</span>
						<span class="card-money"><span class="red-color"><%=items.getMiniBasketPrice()%></span> $</span>
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
	</div>
	<div class="wrapper items">
		<div class="items-container">
			<div class="items-interface">
				<form action="#" class="items-filter" name="items-filter" target="">
					<legend>Filter:</legend>
					<fieldset>
						<label for="id">id:</label><!--
						--><input id="id" type="text">
					</fieldset>
					<fieldset>
						<label for="tags">tags:</label><!--
						--><input id="tags" type="text">
					</fieldset>
					<fieldset>
						<label for="cost">price:</label><!--
						<div class="cost-interface"></div>
						--><div class="cost-range">
							<input class="cost" type="text" placeholder="min">
							<input class="cost" type="text" placeholder="max">
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
	<div class="wrapper footer">
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

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</body>
</html>
