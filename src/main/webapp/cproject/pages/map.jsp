<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Custom Info Windows</title>
    <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD9xCVRfjTJn4JaNOv0J9KKnySK0_Jc6D8&libraries=places,geometry&callback=initMap" async defer></script>
    <script>
      let map;

      function initMap() {
        map = new google.maps.Map(document.getElementById("map"), {
          zoom: 15
        });

        const geocoder = new google.maps.Geocoder();
        const infowindow = new google.maps.InfoWindow();

        const targetLocation = "104506台北市中山區南京東路三段219號"; // 目標位置

        geocoder.geocode({ address: targetLocation }, function (results, status) {
          if (status === google.maps.GeocoderStatus.OK) {
            const targetLatLng = results[0].geometry.location;
            map.setCenter(targetLatLng);

            const targetMarker = new google.maps.Marker({
              position: targetLatLng,
              map: map,
              title: "目標位置"
            });

            google.maps.event.addListener(targetMarker, "click", function () {
              infowindow.setContent('<div><strong>目標位置</strong><br>' + targetLocation + '</div>');
              infowindow.open(map, targetMarker);
            });

            const request = {
              location: targetLatLng,
              radius: 3000, // 3公里的
              types: ["convenience_store"]
            };

            const placesService = new google.maps.places.PlacesService(map);

            placesService.nearbySearch(request, function (results, status) {
              if (status === google.maps.places.PlacesServiceStatus.OK) {
                results.sort(function(a, b) {
                  const distanceA = google.maps.geometry.spherical.computeDistanceBetween(targetLatLng, a.geometry.location);
                  const distanceB = google.maps.geometry.spherical.computeDistanceBetween(targetLatLng, b.geometry.location);
                  return distanceA - distanceB;
                });

                results = results.slice(0, 5); // 僅顯示的大樓

                const customNames = ["中華大樓", "岷發大樓", "天祥大樓", "宇宙大樓", "復興大樨"];

                for (let i = 0; i < results.length; i++) {
                  const place = results[i];
                  const placeLatLng = place.geometry.location;

                  const customName = customNames[i];

                  const placeMarker = new google.maps.Marker({
                    position: placeLatLng,
                    map: map,
                    title: customName
                  });

                  google.maps.event.addListener(placeMarker, "click", function () {
                    infowindow.setContent('<div><strong>' + customName + '</strong><br>' + place.vicinity + '</div>');
                    infowindow.open(map, placeMarker);
                  });
                }
              }
            });
          } else {
            console.error("抓取失敗: " + status);
          }
        });
      }
    </script>
    <style>
      #map {
        height: 80%;
      }

      html, body {
        height: 80%;
        margin: 0;
        padding: 0;
      }
    </style>
  </head>
  <body>
    <div id="map"></div>
  </body>
</html>
