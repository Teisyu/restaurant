// GoogleMapを生成して表示
google.maps.event.addDomListener(window, "load", function(){
    var map = document.getElementById("gmap");
    var options = {
        zoom: 14,
        center: new google.maps.LatLng(34.702485, 135.495951)
    };
    mapObj = new google.maps.Map(map, options);
});


var currentInfoWin = null;

// 店舗情報を取得
function getRamenShop() {
    console.log( "getRamenShop()" );

    // ホットペッパーAPI
    var url ="http://webservice.recruit.co.jp/hotpepper/gourmet/v1/?key=0a4aef2b441e2911&middle_area=Y300,Y305,Y310&genre=G013&format=jsonp&callback=?";
    // ホットペッパーAPIを呼び出す
    $.getJSON(url, {"url":url}).then(
        //成功時の処理
        function(data){
            disp(data);
        },
        //失敗時の処理
        function(){
            alert("Error");
        }
    );
}

// GoogleMapに表示
function disp(resultJSON) {
    // マーカー画像を作成
    var markerImg = new google.maps.MarkerImage(
        "http://画像ファイルURL.png"
    );
    // JSONの店舗情報分だけ繰り返し処理
    $(resultJSON.results.shop).each(function() {

        // 緯度経度情報を取得
        var lating = new google.maps.LatLng(this.lat, this.lng);

        // マーカーを設定
        var markerObj = new google.maps.Marker({
            position: lating,
            map: mapObj,
            icon: markerImg
        });

        // 情報ウィンドウのHTML作成
        var html = "<div class='infoWindow'>"
                    + "<div class='infoWindow-logo'>"
                    + "<img src='" + this.logo_image + "'>"
                    + "</div>"
                    + "<div class='infoWindow-txt'>"
                    + "<p>" + this.name + "</p>"
                    + "<p>" + this.address + "</p>"
                    +"</div>"

        // 情報ウィンドウを作成
        var infoWin = new google.maps.InfoWindow({maxWidth:300});
        infoWin.setContent(html);

        // マーカーをクリックしたら情報ウィンドウを表示
        google.maps.event.addListener(markerObj, 'click', function(){
            if(currentInfoWin){
                currentInfoWin.close();
            }
            infoWin.open(mapObj, markerObj);
            currentInfoWin = infoWin;
        });
    });

}

window.onload = function() {
  getRamenShop();
}