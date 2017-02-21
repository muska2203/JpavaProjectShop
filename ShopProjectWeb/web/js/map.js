var mapContainer = document.getElementById('map-container');
var buttonOpen = document.getElementsByClassName('btn-map-open')[0];
var map;
function initMap() {
    // Create the map with no initial style specified.
    // It therefore has default styling.
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: -33.86, lng: 151.209},
        zoom: 13,
        mapTypeControl: false,
        scrollwheel: false
    });

    var styles = [
        {elementType: 'geometry', stylers: [{color: '#242f3e'}]},
        {elementType: 'labels.text.stroke', stylers: [{color: '#242f3e'}]},
        {elementType: 'labels.text.fill', stylers: [{color: '#746855'}]},
        {
        featureType: 'administrative.locality',
        elementType: 'labels.text.fill',
        stylers: [{color: '#d59563'}]
        },
        {
        featureType: 'poi',
        elementType: 'labels.text.fill',
        stylers: [{color: '#d59563'}]
        },
        {
        featureType: 'poi.park',
        elementType: 'geometry',
        stylers: [{color: '#263c3f'}]
        },
        {
        featureType: 'poi.park',
        elementType: 'labels.text.fill',
        stylers: [{color: '#6b9a76'}]
        },
        {
        featureType: 'road',
        elementType: 'geometry',
        stylers: [{color: '#38414e'}]
        },
        {
        featureType: 'road',
        elementType: 'geometry.stroke',
        stylers: [{color: '#212a37'}]
        },
        {
        featureType: 'road',
        elementType: 'labels.text.fill',
        stylers: [{color: '#9ca5b3'}]
        },
        {
        featureType: 'road.highway',
        elementType: 'geometry',
        stylers: [{color: '#746855'}]
        },
        {
        featureType: 'road.highway',
        elementType: 'geometry.stroke',
        stylers: [{color: '#1f2835'}]
        },
        {
        featureType: 'road.highway',
        elementType: 'labels.text.fill',
        stylers: [{color: '#f3d19c'}]
        },
        {
        featureType: 'transit',
        elementType: 'geometry',
        stylers: [{color: '#2f3948'}]
        },
        {
        featureType: 'transit.station',
        elementType: 'labels.text.fill',
        stylers: [{color: '#d59563'}]
        },
        {
        featureType: 'water',
        elementType: 'geometry',
        stylers: [{color: '#17263c'}]
        },
        {
        featureType: 'water',
        elementType: 'labels.text.fill',
        stylers: [{color: '#515c6d'}]
        },
        {
        featureType: 'water',
        elementType: 'labels.text.stroke',
        stylers: [{color: '#17263c'}]
        }
    ];
               
    map.setOptions({styles: styles});
}

$(function(){
    $(buttonOpen).on('click', function(){
        $('.map').toggleClass('open');
        var reloadMap = setInterval(function(){
            google.maps.event.trigger(map, 'resize');
            console.log("hehehe");
        }, 100);
        setTimeout(function(){
            clearInterval(reloadMap);
        },2000);
    });
});


          