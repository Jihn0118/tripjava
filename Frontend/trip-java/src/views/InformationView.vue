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
    <div class="overlay"></div>


    <SidoNameList @sido-click-event="sidoCilckEvent"></SidoNameList>
<!--    <InfoList :selectedSido="selectedSido"></InfoList>-->

</template>

<style scoped>
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