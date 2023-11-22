<script setup>
import {ref, onMounted} from "vue";
import {listSidoName} from "@/api/attractionInfo"
import {Pagination,Navigation} from "swiper/modules";
import {Swiper, SwiperSlide} from 'swiper/vue';
import 'swiper/css';
import 'swiper/css/navigation';

const emit = defineEmits(['sidoClickEvent'])
const modules = [Pagination,Navigation];


const sidoList = ref([]);
const selectSido = ref('');

const getSidoList = () => {
  listSidoName(
      ({data}) => {
        sidoList.value = data;
      },
      (error) => {
        console.log(error);
      }
  )
}

const click = (sido) => {
  selectSido.value = sido;
  emit('sidoClickEvent', selectSido.value);
}


onMounted(() => {
  getSidoList();
});
</script>

<template>
  <swiper
      :modules="modules"
      :slides-per-view="4"
      :space-between="30"
      :pagination="{ clickable: true }"
      :navigation="true"
  >
    <swiper-slide v-for="sido in sidoList">
      <button @click="click(sido.sidoCode)" class="sidoButton">Seoul</button>
    </swiper-slide>
  </swiper>

</template>

<style scoped>
.swiper{
  width:80%
}

.swiper-slide {
  text-align: center;
  font-size: 18px;
  /* Center slide text vertically */
  display: flex;
  justify-content: center;
  align-items: center;
}


</style>