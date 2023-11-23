<script setup>
import {ref} from "vue";
import dayjs from "dayjs";

const dateFormat = "YYYY-MM-DD";

const titleAndDate = ref({
  "title": "",
  "dateDiff": "",
  "startDate": "",
  "endDate": ""
})

let today = new Date();

const myCalendar = ref([
  dayjs(today.toLocaleDateString(), dateFormat),
  dayjs(today.toLocaleDateString(), dateFormat),
]);

const emit = defineEmits(["setTripdate"]);

const createDate = () => {
  titleAndDate.value.dateDiff = (myCalendar.value[1] - myCalendar.value[0]) / (24 * 60 * 60 * 1000) + 1;
  titleAndDate.value.startDate = dayjs(myCalendar.value[0]).format(dateFormat);
  titleAndDate.value.endDate = dayjs(myCalendar.value[1]).format(dateFormat);

  emit("setTripdate", titleAndDate.value);
};
</script>

<template>
  <div style="height: 700px; background-color: white; width:70%">
    <a-input
        v-model:value="titleAndDate.title"
        size="large"
        placeholder="여행 제목 입력"
        style="margin-bottom: 5px"
    />
    <a-range-picker v-model:value="myCalendar" :format="dateFormat"/>
    <div style="margin-top: 20px">
      <a-button type="primary" @click="createDate"
      >날짜 설정 완료
      </a-button
      >
    </div>
  </div>
</template>

<style scoped></style>
