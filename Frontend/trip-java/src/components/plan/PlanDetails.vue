<script setup>
import { ref, onMounted } from "vue";
import { getPlan } from "@/api/plan";
import { useRoute } from "vue-router";

const route = useRoute();

const days = ref([]);
const details = ref([]);

const getPlanInfo = () => {
  getPlan(
    // props.travelId,
    // travelId,
    route.params.travelId,
    ({ data }) => {
      console.log(data);
      data.forEach((element) => {
        let dayNum = element.dayNumber;
        let detail = element.details;
        detail.forEach((det) => {
          details.value.push(det.contentId);
        });
        days.value.push({ dayNumber: dayNum, details: details.value });
      });

      console.log("데이터 가공");
      console.log(days.value);
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
  console.log(route.params.travelId);
  console.log(route.query.startDate);
  console.log(route.query.endDate);
  console.log(route.query.travelName);

  getPlanInfo();
});
</script>

<template>
  <!--주소로 travel ID 받아서 오면 이제 getList로 여행 일정 데이터를 받아와
  FOR문 DAYS의 개수(TravelPlan의 days의 개수)
	FOR문 DETAILS의 개수(Day의 details 의 개수)
  -->
  <div style="text-align: center; margin: 0 auto">
    <h1>{{ route.query.travelName }}</h1>
    <a-timeline>
      <a-timeline-item v-for="(item, index) in items" :key="index">1일차 내용</a-timeline-item>
      <a-timeline-item color="green" mode="left"
        >{{ route.query.startDate }} 여행 시작</a-timeline-item
      >
      <a-timeline-item mode="right">2일차 내용</a-timeline-item>
      <a-timeline-item mode="right">3일차 내용</a-timeline-item>
      <a-timeline-item color="red" mode="left">{{ route.query.endDate }} 여행 끝</a-timeline-item>
    </a-timeline>
  </div>
</template>

<style scoped></style>
