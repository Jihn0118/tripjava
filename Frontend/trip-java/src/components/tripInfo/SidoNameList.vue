<script setup>
import {ref, onMounted} from "vue";
import {listSidoName} from "@/api/attractionInfo"
import {Pagination, Navigation, Autoplay} from "swiper/modules";
import {Swiper, SwiperSlide} from 'swiper/vue';
import 'swiper/css';
import 'swiper/css/navigation';

const emit = defineEmits(['sidoClickEvent'])
const modules = [Pagination, Navigation, Autoplay];



const sidoList = ref([]);
const selectSido = ref('');

const getSidoList = () => {
  console.log("들어왔따")
  listSidoName(
      ({data}) => {
        console.log("123123");
        sidoList.value = data;
      },
      (error) => {
        console.log(error);
      }
  )
}

const click = (sido) =>{
  selectSido.value = sido;
  emit('sidoClickEvent',selectSido.value);
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
    <swiper-slide v-for="sido in sidoList">
      <button @click="click(sido.sidoCode)">{{sido.sidoName}}</button>
    </swiper-slide>
  </swiper>
</template>

<style scoped>

</style>