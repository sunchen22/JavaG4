<%@page import="com.dinerratingcomment.entity.DinerRatingComment"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.dinerratingcomment.dao.*"%>
<%@ page import="com.dinerinfo.dao.*"%>
<%@ page import="com.dinerinfo.entity.*"%>
<html> 

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>大樓建立地圖</title>

  
  <link rel="stylesheet"
    href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  
  <link rel="stylesheet" href="<%=request.getContextPath()%>/cproject/plugins/fontawesome-free/css/all.min.css">
  
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  
  <link rel="stylesheet" href="<%=request.getContextPath()%>/cproject/dist/css/adminlte.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">



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
                        radius: 3000, // 距離
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

                            results = results.slice(0, 5); // 顯示的大樓

                            const customNames = ["中華大樓", "岷發大樓", "天祥大樓", "宇宙大樓", "復興大樓"];

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
                                    
                                    const contentString = '<div><strong id = "placeName">' + customName + '</strong><br>' +"台北市" + place.vicinity +
                                        '<br><button id ="click" onclick="handleButtonClick()">點擊我新增</button></div>';

                                    const infowindow = new google.maps.InfoWindow({
                                        content: contentString
                                    });

                                    infowindow.open(map, placeMarker);
                                    
                                    
                                
                                    
                                    
                                });
                                
                                
                            }
                        }
                    });
                } else {
                    console.error("失敗: " + status);
                }
            });
        }
        
        
        
        function handleButtonClick() {
        	
       	
        	
        	const infowindowContent = document.querySelector(".gm-style-iw");
            

            const nameElement = document.getElementById("placeName");
            const customName = nameElement ? nameElement.textContent : "未找到名稱";
            
            
            const addressElement = infowindowContent.querySelector("br").nextSibling;
            const address = addressElement ? addressElement.textContent : "未找到地址";
            
            console.log("名稱: " + customName);
            console.log("地址: " + address);
            alert("新增成功");
           
            var xhr = new XMLHttpRequest();
            var servletUrl = 'bis.do'; 
            xhr.open('POST', servletUrl, true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            var params = 'name=' + customName + '&address=' + address;
            
            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    
                    console.log(xhr.responseText);
                    
                }
            };
            
            xhr.send(params);
            
            
            
        }

    </script>
    <style>
        #map {
            height: 100%;
        }

        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }
        
        #click {
    background-color: #007BFF; 
    color: #fff; 
    border: 2px solid #0056b3; 
    padding: 5px 10px; 
    border-radius: 8px; 
    cursor: pointer; 
	}

	
	#click:hover {
    background-color: #0056b3; 
	}
        
        
        
    </style>

</head>


<body class="hold-transition sidebar-mini">
  <div class="wrapper">
    
    <nav class="main-header navbar navbar-expand navbar-white navbar-light">
      
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>

        </li>

       
      </ul>

     
      <ul class="navbar-nav ml-auto">

        <li class="nav-item">
          <a class="nav-link" href="<%=request.getContextPath()%>/index.jsp" role="button">
            <i class="fas fa-home"></i>
          </a>
        </li>

        <li class="nav-item">
          <a class="nav-link" data-widget="fullscreen" href="#" role="button">
            <i class="fas fa-expand-arrows-alt"></i>
          </a>
        </li>

      </ul>
    </nav>
    
   
            

<%@ include file="../../background/pages/pageaside.file" %>
              
        
     </nav> 
              
      
      </div>
      
    </aside>
     
   

    <div class="content-wrapper">
    
 
    
    <div id="map" ></div>
    
    
  
    
 </div>

  
    <aside class="control-sidebar control-sidebar-warning">
        
      </aside>
      
      <footer class="main-footer">
        <strong>Copyright &copy; 2023</strong>
        樓頂揪樓咖團隊 All rights reserved.
        <div class="float-right d-none d-sm-inline-block">
          <b>Version</b> 1.1.0
        </div>
      </footer>
      </div>
     
      <script src="<%=request.getContextPath()%>/cproject/plugins/jquery/jquery.min.js"></script>
      
      <script src="<%=request.getContextPath()%>/cproject/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
      
      <script src="<%=request.getContextPath()%>/cproject/dist/js/adminlte.js"></script>
      
      <script src="<%=request.getContextPath()%>/cproject/plugins/chart.js/Chart.min.js"></script>
      
      <script src="<%=request.getContextPath()%>/cproject/dist/js/pages/dashboard3.js"></script>
      
      

    <%@ include  file="../../background/pages/pagejs.file" %>
  
      <%@ include file="included-fragment.file" %>
		<script src="https://code.jquery.com/jquery-3.5.1.js"></script>                                    <!-- ●●js  for jquery datatables 用 -->
		<script	src="https://cdn.datatables.net/1.13.5/js/jquery.dataTables.min.js"></script>              <!-- ●●js  for jquery datatables 用 -->
		<link rel="stylesheet" href="https://cdn.datatables.net/1.13.5/css/dataTables.jqueryui.min.css" /> <!-- ●●css for jquery datatables 用 -->
		
      
</body>

</html>