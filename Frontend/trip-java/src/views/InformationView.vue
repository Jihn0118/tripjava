<script setup>

import {ref, onMounted} from "vue";
import SidoNameList from "@/components/tripInfo/SidoNameList.vue";
import InfoList from "@/components/tripInfo/InfoList.vue";

const selectedSido = ref(1);
const sidoCilckEvent = (selectItem) => {
  selectedSido.value = selectItem;
}

</script>

<template>
  <div class="text" style="width: 100%; margin-top: 100px">
    <SidoNameList @sido-click-event="sidoCilckEvent"></SidoNameList>
    <InfoList :selectedSido="selectedSido"></InfoList>
  </div>



</template>

<style scoped>

.info {
  position: absolute;
  right: 0;
  width: 100%;
  min-height: 100vh;
  padding: 50px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: LightSkyBlue;
  transition: 0.5s;
  z-index: 2;
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