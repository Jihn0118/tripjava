<script setup>

import {onMounted, ref, watch} from "vue";
import {useRoute, useRouter} from "vue-router";
import {infoDetail} from "@/api/attractionInfo";
import {getHeartStatement, doHeart, cancelHeart} from "@/api/heart";
import {useMemberStore} from "@/stores/member";
import {storeToRefs} from "pinia";

const memberStore = useMemberStore();
const {userInfo} = storeToRefs(memberStore);


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

const memberIdAndContentId = ref({
  "memberId": '',
  "contentId": 0
})


const isLiked = ref(false);
const loveClick = () => {
  if (isLiked.value) {
    cancelHeart(
        memberIdAndContentId.value,
        () => {
          getHeartStatement();
        },
        (error) => {
          console.error(error);
        }
    )
  } else {
    doHeart(
        memberIdAndContentId.value,
        ({data}) => {
          getHeartStatement();
        },
        (error) => {
          console.error(error);
        }
    )
  }
  heartController.value.color = isLiked.value ? '#f10606' : '';
}

watch(isLiked, () => {
  heartController.value.color = isLiked.value ? '#ff0000' : '';
})

const heartController = ref({
  color: ''
});

const getHeartState = () => {
  getHeartStatement(
      memberIdAndContentId.value,
      ({data}) => {
        isLiked.value = data;
        console.log("값은" + isLiked.value)
      },
      (error) => {
        console.log(error)
      }
  )
}

onMounted(() => {

  // 현재 사용자의 아이디와 관광지 정보 넣어주기
  memberIdAndContentId.value.memberId = userInfo.value.memberId;
  memberIdAndContentId.value.contentId = infoId;

  // 좋아요 상태 가져오기
  getHeartState();

  // 카카오 지도
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
  <button class="custom-button" @click="loveClick" :class="{ active: isLiked }">
    <font-awesome-icon icon="heart" size="4x" :style="{ color: heartController.color }"/>
  </button>

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

.custom-button {
  background-color: transparent;
  border: none;
  padding: 0;
  cursor: pointer;
}

.custom-button.active {
  background-color: transparent;
}
</style>