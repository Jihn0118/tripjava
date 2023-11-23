<script setup>
import {ref, watch, onMounted} from "vue";

var map;
const positions = ref([]);
const markers = ref([]);
const totalDay = ref(0);
const lines = ref([]);
const createLines = ref([]);

const props = defineProps({stations: Array, selectStation: Object});

// watch(
//     () => props.selectStation.value,
//     () => {
//       // 이동할 위도 경도 위치를 생성합니다
//       var moveLatLon = new kakao.maps.LatLng(props.selectStation.lat, props.selectStation.lng);
//
//       // 지도 중심을 부드럽게 이동시킵니다
//       // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
//       map.panTo(moveLatLon);
//     },
//     {deep: true}
// );

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
        import.meta.env.VITE_KAKAO_MAP_SERVICE_KEY
    }&libraries=services,clusterer`;
    /* global kakao */
    script.onload = () => kakao.maps.load(() => initMap());
    document.head.appendChild(script);
  }
});

watch(
    () => props.stations,
    () => {
      positions.value = [];
      if (props.stations.length === 0) {
        deleteMarkers();
        return;
      }

      const newLocation = props.stations.slice(props.stations.length - 1, props.stations.length)[0];
      moveLocation(newLocation)

      props.stations.forEach((station) => {

        if (totalDay.value < station.day) {
          totalDay.value = station.day;
        }

        let obj = {};
        obj.latlng = new kakao.maps.LatLng(station.lat, station.lng);
        obj.title = station.statNm;
        obj.day = station.day;
        //
        positions.value.push(obj);
      });
      loadMarkers();
    },
    {deep: true}
);

const moveLocation = (moveValue) => {
  var moveLatLon = new kakao.maps.LatLng(moveValue.lat, moveValue.lng);
  map.panTo(moveLatLon);
}

const initMap = () => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 3,
  };
  map = new kakao.maps.Map(container, options);

  // loadMarkers();
};

const createImage = (day) => {
  // 마커 이미지를 생성합니다

  let imgSrc = '';
  if (day === 1) {
    imgSrc = "src/assets/day/1.png";
  } else if (day === 2) {
    imgSrc = "src/assets/day/2.png";
  } else {
    imgSrc = "src/assets/day/3.png";
  }


  // 마커 이미지의 이미지 크기 입니다
  const imgSize = new kakao.maps.Size(15, 15);
  return new kakao.maps.MarkerImage(imgSrc, imgSize);
}

const createLine = () => {
  for (var i = 0; i < lines.value.length; i++) {

    var polyline = new kakao.maps.Polyline({
      path: lines.value[i], // 선을 구성하는 좌표배열 입니다
      strokeWeight: 2, // 선의 두께 입니다
      strokeColor: '#000000', // 선의 색깔입니다
      strokeOpacity: 0.5, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
      strokeStyle: 'solid' // 선의 스타일입니다
    });
    createLines.value.push(polyline);
    polyline.setMap(map);
  }
}

const loadMarkers = () => {
      // 현재 표시되어있는 marker들이 있다면 map에 등록된 marker를 제거한다.
      deleteLines();
      deleteMarkers();
      lines.value = [];

      for (var i = 0; i < totalDay.value; i++) {
        lines.value.push([]);
      }
      // 마커를 생성합니다
      markers.value = [];


      positions.value.forEach((position) => {

        lines.value[position.day - 1].push(position.latlng)


        const marker = new kakao.maps.Marker({
          map: map, // 마커를 표시할 지도
          position: position.latlng, // 마커를 표시할 위치
          // title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됨.
          clickable: true, // // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
          image: createImage(position.day), // 마커의 이미지
        });
        markers.value.push(marker);
      });

// 4. 지도를 이동시켜주기
// 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
      const bounds = positions.value.reduce(
          (bounds, position) => bounds.extend(position.latlng),
          new kakao.maps.LatLngBounds()
      );

      map.setBounds(bounds);
      createLine();
    }
;

const deleteMarkers = () => {
  if (markers.value.length > 0) {
    markers.value.forEach((marker) => marker.setMap(null));
  }
};

const deleteLines = () => {
  if (lines.value.length > 0) {
    createLines.value.forEach((line) => line.setMap(null));
  }
  createLine.value = [];
}
</script>

<template>
  <div id="map"></div>
</template>

<style>
#map {
  width: 100%;
  height: 900px;
}
</style>
