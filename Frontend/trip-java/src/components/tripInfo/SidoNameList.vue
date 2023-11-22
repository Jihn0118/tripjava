<script setup>
import {ref, onMounted} from "vue";
import {listSidoName} from "@/api/attractionInfo"
import {Pagination, Navigation} from "swiper/modules";
import {Swiper, SwiperSlide} from 'swiper/vue';
import 'swiper/css';
import 'swiper/css/navigation';

const emit = defineEmits(['sidoClickEvent'])
const modules = [Pagination, Navigation];


const sidoList = ref([]);
const selectSido = ref('');

const getSidoList = () => {
  listSidoName(
      ({data}) => {
        sidoList.value = data;
        console.log(data);
      },
      (error) => {
        console.log(error);
      }
  )
}

const click = (sido) => {
  selectSido.value = sido;
  console.log(sido)
  emit('sidoClickEvent', selectSido.value);
}

const getImageSource = (sidoCode) => {
  console.log("123");
  return `src/assets/city/${sidoCode}.jpg`
}


onMounted(() => {
  getSidoList();
});
</script>

<template>
  <swiper
      :modules="modules"
      :slides-per-view="5"
      :space-between="10"
      :pagination="{ clickable: true }"
      :navigation="true"
  >
    <swiper-slide v-for="sido in sidoList">
      <div class="image-zoom" @click="click(sido.sidoCode)" style="text-align: center">
        <div style="border-radius: 70%; width: 150px; height: 150px; overflow: hidden; margin-bottom: 10px">
          <img :src="getImageSource(sido.sidoCode)" alt="" style="width: 100%; height: 100%; object-fit: cover"/>
        </div>
        <span>{{ sido.sidoName }}</span>
      </div>
    </swiper-slide>
  </swiper>
</template>

<style scoped>
.swiper {
  width: 80%
}

.swiper-slide {
  text-align: center;
  font-size: 18px;
  /* Center slide text vertically */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 50px;
}

.image-zoom {
  position: relative;
  text-align: center;
}

.image-zoom img {
  width: 100px;
  height: 100px;
  object-fit: cover;
  transition: transform 0.3s ease; /* 변환 효과를 부드럽게 만들기 위한 transition */
}

.image-zoom img:hover {
  transform: scale(1.2); /* 마우스 호버 시 이미지 확대 */
}

</style>