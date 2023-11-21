<script setup>

import {onMounted, ref, watch} from "vue";
import {useRoute, useRouter} from "vue-router";
import {infoDetail} from "@/api/attractionInfo";


const route = useRoute();
const Information = ref({})
let {infoId} = route.params;
let map;

infoDetail(
    infoId,
    ({data}) => {
      Information.value = data;
      console.log(Information.value)
    },
    (error) => {
      console.log(error);
    }
);

onMounted(() => {
  const script = document.createElement("script");
  script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${import.meta.env.VITE_KAKAO_MAP_SERVICE_KEY}&libraries=services,clusterer`;
  /* global kakao */
  script.onload = () => kakao.maps.load(() => initMap());
  document.head.appendChild(script);
});

const initMap = () => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(Information.value.latitude, Information.value.longitude),
    level: 3,
  };

  map = new kakao.maps.Map(container, options);
  loadMarker()
  console.log(map);
};

const loadMarker = () => {
  var markPosition = new kakao.maps.LatLng(Information.value.latitude, Information.value.longitude);
  var marker = new kakao.maps.Marker({
    position: markPosition
  });
  marker.setMap(map);
}

</script>

<template>
  <div>
    <h1>{{ Information.title }}</h1>
    <h2>{{ Information.addr }}</h2>
    <a-image
        :width="700"
        :src=Information.image
    />
    <h1>설명</h1>
    <p>{{ Information.description }}</p>
  </div>
  <div class="map-wrap">
    <div id="map"></div>
  </div>
</template>

<style scoped>
.map-wrap {
  display: flex;
  justify-content: center;
  align-items: center;
}

#map {
  width: 60%;
  height: 300px;
}
</style>