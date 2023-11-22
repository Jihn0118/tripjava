<script setup>
import {computed, onMounted, ref, watch} from 'vue';
import {listInfo} from "@/api/attractionInfo";
import InfoListItem from "@/components/tripInfo/InfoListItem.vue";


const props = defineProps({
  selectedSido: String
})

const Cities = {
  "서울": 1,
  "인천": 2,
  "대전": 3,
  "대구": 4,
  "광주": 5,
  "부산": 6,
  "울산": 7,
  "세종특별자치시": 8,
  "경기도": 31,
  "강원도": 32,
  "충청북도": 33,
  "충청남도": 34,
  "경상북도": 35,
  "경상남도": 36,
  "전라북도": 37,
  "전라남도": 38,
  "제주도": 39
}


watch(() => props.selectedSido, (value) => {
  searchCondition.value.sidoCode = value;
  getInfoList();
})

const infoList = ref([])
const searchCondition = ref({
  contentType: "",
  keyword: "",
  sidoCode: "",
})
const getInfoList = () => {
  listInfo(
      searchCondition.value,
      ({data}) => {
        dataList.value = data;
        console.log(dataList.value);
      },
      (err) => {
        console.log(err);
      }
  )
}


//page
const current = ref(1);
const totalData = ref(120);
const pageSize = ref(12);
const dataList = ref([]);

const displayData = computed(() => {
  const startIndex = (current.value - 1) * pageSize.value;
  const endIndex = current.value * pageSize.value;
  return dataList.value.slice(startIndex, endIndex);
})


onMounted(() => {
  getInfoList();
  console.log(infoList.value);
})


</script>

<template>
  <div class="cardWrapper" style="margin-top: 30px">
    <InfoListItem v-for="item in displayData" :item="item"></InfoListItem>
  </div>
  <div style="text-align: center; height: 50px">
    <a-pagination v-model:current="current"
                  :total=totalData
                  :current=current
                  :page-size=pageSize
                  show-less-items
                  style="height: 50px"/>
  </div>
</template>

<style scoped>
.cardWrapper {
  grid-template-columns: repeat(auto-fit, minmax(350px, 1fr));
  padding: 20px;
  display: grid;
  gap: 1rem;
  row-gap: 50px;
  text-align: center;
}
</style>