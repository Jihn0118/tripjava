<script setup>

import {ref, onMounted} from "vue";

const bgImage = ref('');

onMounted(() => {
  const randomNumber = Math.floor(Math.random() * 4) + 1; // 1~4 사이의 랜덤 숫자 생성
  bgImage.value =`src/assets/background/backgroundImage${randomNumber}.jpg`;
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
</template>

<style scoped>
  .bg{
    height: 695px;
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
  }
</style>