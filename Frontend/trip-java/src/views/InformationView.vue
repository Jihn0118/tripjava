<script setup>

import {ref, onMounted} from "vue";
import SidoNameList from "@/components/tripInfo/SidoNameList.vue";
import InfoList from "@/components/tripInfo/InfoList.vue";

const bgImage = ref('');
const selectedSido = ref(1);
const sidoCilckEvent = (selectItem) =>{
    selectedSido.value = selectItem;
}

onMounted(() => {
  const randomNumber = Math.floor(Math.random() * 4) + 1; // 1~4 사이의 랜덤 숫자 생성
  bgImage.value = `src/assets/background/backgroundImage${randomNumber}.jpg`;
});

</script>

<template>
  <div :style="{ backgroundImage: `url(${bgImage})` }" class="bg">
    <div>
      <h1>
        여행지 정보
      </h1>
      <div>
        <p>여러가지 여행 정보를 확인해 보세요</p>
      </div>
    </div>
  </div>

  <SidoNameList @sido-click-event="sidoCilckEvent"></SidoNameList>
  <InfoList :selectedSido="selectedSido"></InfoList>

  <div>
    <h3>인기 여행지</h3>
    <div>
      123
    </div>
  </div>
</template>

<style scoped>
.bg {
  height: 695px;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}

.box {
  width: 150px;
  height: 150px;
  overflow: hidden;
}

.sidoItem {
  border-radius: 50%;
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.li {
  float: left;
  margin-right: 20px;
}
</style>