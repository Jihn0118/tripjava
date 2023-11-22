<script setup>
import { ref } from "vue";
import dayjs from "dayjs";

const dateFormat = "YYYY-MM-DD";

defineProps({
  plan: Object,
});

let today = new Date();

const myCalendar = ref([
  dayjs(today.toLocaleDateString(), dateFormat),
  dayjs(today.toLocaleDateString(), dateFormat),
]);

const emit = defineEmits(["setTripdate"]);

const setTripdate = (val) => {
  val.dateDiff =
    (myCalendar.value[1] - myCalendar.value[0]) / (24 * 60 * 60 * 1000) + 1;

  val.startDate = dayjs(myCalendar.value[0]).format(dateFormat);
  val.endDate = dayjs(myCalendar.value[1]).format(dateFormat);

  emit("setTripdate", val);
};
</script>

<template>
  <div name="tripDate" style="height: 700px; background-color: white">
    <a-input
      v-model:value="plan.title"
      size="large"
      placeholder="여행 제목 입력"
    />
    <a-range-picker v-model:value="myCalendar" :format="dateFormat" />
    <div>
      <a-button type="primary" @click="setTripdate(plan)"
        >날짜 설정 완료</a-button
      >
    </div>
  </div>
</template>

<style scoped></style>
