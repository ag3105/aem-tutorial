var latitude = 0;
var longitude = 0;

function getLocation(){
    var responseData = $('#zipCode').val();
    var json = JSON.parse(responseData);

    for (index = 0; index < json.locations.length; index++ ) {
        latitude = parseFloat(json.locations[index].coordinates.latitude);
        longitude = parseFloat(json.locations[index].coordinates.longitude);

        initMap(latitude,longitude);
    }
};

function initMap(latitude,longitude) {

        var city = {lat: latitude, lng: longitude};
        var map = new google.maps.Map(document.getElementById('map-canvas'), {
                      zoom: 3,
                      mapMaker: true,
                      rotateControl: true,
                      center: city
                  });
        var marker = new google.maps.Marker({
                    position: city,
                    map: map
                 });
}