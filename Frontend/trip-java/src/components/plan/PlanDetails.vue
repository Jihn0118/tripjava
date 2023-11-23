<script setup>
import { ref, onMounted } from "vue";
import { getPlan } from "@/api/plan";
import { useRoute } from "vue-router";

const route = useRoute();

const { travelId } = route.params;

const days = ref([]);
const details = ref([]);

const getPlanInfo = () => {
  getPlan(
    // props.travelId,
    // travelId,
    9,
    ({ data }) => {
      console.log(data);
      //details.value.push();
      //days.value.push();
    },
    (error) => {
      console.log(error);
    }
  );
};

onMounted(() => {
  console.log("화면 로딩 완료");
  getPlanInfo();
});
</script>

<template>
  <!--주소로 travel ID 받아서 오면 이제 getList로 여행 일정 데이터를 받아와
  FOR문 DAYS의 개수(TravelPlan의 days의 개수)
	FOR문 DETAILS의 개수(Day의 details 의 개수)
  -->

  <a-timeline>
    <a-timeline-item v-for="(item, index) in items" :key="index"
      >1일차 내용</a-timeline-item
    >
    <a-timeline-item>2일차 내용</a-timeline-item>
    <a-timeline-item>3일차 내용</a-timeline-item>
  </a-timeline>
</template>

<style scoped></style>
