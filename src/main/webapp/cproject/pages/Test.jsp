<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
  <head>
    <title>Info Windows</title>
    <style>
      /* CSS 保持不變... */
      #map {
        height: 100%;
      }
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
    </style>
    
    

    
    
  </head>
  <body>
    <div id="map"></div>

    <script>
    function initMap() {
    	  if ("geolocation" in navigator) {
    	    navigator.geolocation.getCurrentPosition(function (position) {
    	      const userLocation = {
    	        lat: position.coords.latitude,
    	        lng: position.coords.longitude
    	      };

    	      const map = new google.maps.Map(document.getElementById("map"), {
    	        zoom: 15, // 設定初始縮放級別為較高的數值以顯示附近的大樓
    	        center: userLocation
    	      });

    	      const marker = new google.maps.Marker({
    	        position: userLocation,
    	        map: map,
    	        title: "Your Location"
    	      });

    	      // 使用 PlacesService 來搜索附近的大樓
    	      const placesService = new google.maps.places.PlacesService(map);
    	      const request = {
    	        location: userLocation,
    	        radius: 3000, // 3公里的搜索範圍
    	        types: ["building"] // 填入您想要搜索的類型
    	      };

    	      placesService.nearbySearch(request, function (results, status) {
    	        if (status === google.maps.places.PlacesServiceStatus.OK) {
    	          for (let i = 0; i < results.length; i++) {
    	            const place = results[i];
    	            const placeMarker = new google.maps.Marker({
    	              position: place.geometry.location,
    	              map: map,
    	              title: place.name
    	            });
    	          }
    	        }
    	      });
    	    });
    	  } else {
    	    // 如果瀏覽器不支援地理定位，您可以提供一個預設位置
    	    const defaultLocation = { lat: 0, lng: 0 }; // 預設位置 (對峙)
    	    const map = new google.maps.Map(document.getElementById("map"), {
    	      zoom: 4,
    	      center: defaultLocation
    	    });
    	  }
    	}

    </script>
	
	
    
  </body>
</html>
