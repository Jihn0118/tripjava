<script setup>
import {ref, onMounted} from "vue";
import {listSidoName} from "@/api/attractionInfo"
import {Pagination, Navigation, Autoplay} from "swiper/modules";
import {Swiper, SwiperSlide} from 'swiper/vue';
import 'swiper/css';
import 'swiper/css/navigation';

const modules = [Pagination, Navigation, Autoplay];


const sidoNames = ref([]);

const getSidoList = () => {
  console.log("들어왔따")
  listSidoName(
      ({data}) => {
        console.log("123123");
        sidoNames.value = data;
      },
      (error) => {
        console.log(error);
      }
  )
}

onMounted(() => {
  getSidoList();
});
</script>

<template>
  <swiper
      :modules="modules"
      :slides-per-view="8"
      :space-between="30"
      :pagination="{ clickable: true }"
      :navigation="true"
  >
    <swiper-slide v-for="sidoName in sidoNames">{{ sidoName }}</swiper-slide>
  </swiper>
</template>

<style scoped>

</style>