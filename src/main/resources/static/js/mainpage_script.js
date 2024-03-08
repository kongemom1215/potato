/*!
* Start Bootstrap - Simple Sidebar v6.0.6 (https://startbootstrap.com/template/simple-sidebar)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-simple-sidebar/blob/master/LICENSE)
*/
//
// Scripts
//

window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});

$(document).ready(function(){
    insertPieChart();
    //insertKakaoMap();
    testGamzaBread();
});

function insertKakaoMap(){
    var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
    var options = { //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(37.929988, 127.784419), //지도의 중심좌표.
        level: 3 //지도의 레벨(확대, 축소 정도)
    };

    var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
}

function testGamzaBread(){
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
      mapOption = {
            center: new kakao.maps.LatLng(36.706832, 127.965814), // 지도의 중심좌표
            level: 13 // 지도의 확대 레벨
        };

    var map = new kakao.maps.Map(mapContainer, mapOption);

    /* 감자밭 카페 */
    var imageSrc = '/assets/img/potato_tmp.png', // 마커이미지의 주소입니다
        imageSize = new kakao.maps.Size(40, 50), // 마커이미지의 크기입니다
        imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

    // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
        markerPosition = new kakao.maps.LatLng(37.929659, 127.784477); // 마커가 표시될 위치입니다

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
      position: markerPosition,
      image: markerImage // 마커이미지 설정
    });

    // 마커가 지도 위에 표시되도록 설정합니다
    marker.setMap(map);

    // 커스텀 오버레이에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    var content = '<div class="customoverlay">' +
        '  <a href="https://kko.to/NqHRLLQG4R" target="_blank">' +
        '    <span class="title">감자빵 맛집</span>' +
        '  </a>' +
        '</div>';

    // 커스텀 오버레이가 표시될 위치입니다
    var position = new kakao.maps.LatLng(37.929659, 127.784477);

    // 커스텀 오버레이를 생성합니다
    var customOverlay = new kakao.maps.CustomOverlay({
        map: map,
        position: position,
        content: content,
        yAnchor: 1
    });
    /* 감자밭 END */

    /* 뇨끼 */
    var imageSrc2 = '/assets/img/gnocchi_tmp.png', // 마커이미지의 주소입니다
            imageSize2 = new kakao.maps.Size(60, 60), // 마커이미지의 크기입니다
            imageOption2 = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

    var markerImage2 = new kakao.maps.MarkerImage(imageSrc2, imageSize2, imageOption2),
            markerPosition2 = new kakao.maps.LatLng(37.548984, 126.921150); // 마커가 표시될 위치입니다

    var marker2 = new kakao.maps.Marker({
          position: markerPosition2,
          image: markerImage2 // 마커이미지 설정
        });

    marker2.setMap(map);

    var content2 = '<div class="customoverlay">' +
            '  <a href="https://kko.to/AUupIdsPx4" target="_blank">' +
            '    <span class="title">뇨끼 맛집</span>' +
            '  </a>' +
            '</div>';

    var position2 = new kakao.maps.LatLng(37.548984, 126.921150);

    var customOverlay2 = new kakao.maps.CustomOverlay({
        map: map,
        position: position2,
        content: content2,
        yAnchor: 1
    });
    /* 뇨끼 END */
}

function insertPieChart(){
    const ctx = document.querySelector('#myChart').getContext('2d');
    const data = {
        labels: ['버거킹','맘스터치','맥도날드'],
        datasets: [{
            label: '투표수',
            data: [300, 50, 100],
            backgroundColor: [
                'rgb(255, 99, 132)',
                'rgb(54, 162, 235)',
                'rgb(255, 205, 86)'
            ],
            hoverOffset: 4
        }]
    };

    var myDoughnutChart = new Chart(ctx,{
        type : 'pie',
        data: data,
        options:{
            plugins:{
                title:{
                    display:true,
                    text:'감튀 대장은 누구인가'
                },
                legend:{
                    display:true,
                    position:'top'
                }
            }
        }
    });
}