<script setup>
import { ref, onMounted } from "vue";
import { getPlan } from "@/api/plan";
import { infoDetail } from "@/api/attractionInfo";
import { useRoute } from "vue-router";

const route = useRoute();

const days = ref([]);
const details = ref([]);
const contentIdList = ref([]);
const contentList = ref([]);

const getPlanInfo = () => {
  getPlan(
    route.params.travelId,
    ({ data }) => {
      data.forEach((element) => {
        let dayNum = element.dayNumber;
        let detail = element.details;
        detail.forEach((det) => {
          details.value.push(det.contentId);
          contentIdList.value.push(det.contentId);
        });
        days.value.push({ dayNumber: dayNum, details: details.value });
      });

      console.log("데이터 가공");
      console.log(days.value);
      console.log("콘텐트아이디 리스트");
      console.log(contentIdList.value);
      //details.value.push();
      //days.value.push();
    },
    (error) => {
      console.log(error);
    }
  );
};

const getContentList = () => {
  console.log("content 가져오기");
  console.log(contentIdList.value);
  if (contentIdList.value.length != 0) {
    for (let i = 0; i < contentIdList.value.length; i++) {
      let contentId = contentIdList.value[i];
      console.log("contentId는: ");
      console.log(contentId);
      infoDetail(contentId, ({ data }) => {
        console.log("함수 안?");
        console.log(data);
      });
    }
  }
};

onMounted(() => {
  console.log("화면 로딩 완료");
  console.log(route.params.travelId);
  console.log(route.query.startDate);
  console.log(route.query.endDate);
  console.log(route.query.travelName);

  getPlanInfo();

  getContentList();
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
      <a-timeline-item color="green" mode="left"
        >{{ route.query.startDate }} 여행 시작</a-timeline-item
      >
      <a-timeline-item v-for="(day, index) in days" :key="index"
        >{{ day.dayNumber }}일차 내용
      </a-timeline-item>

      <a-timeline-item color="red" mode="left">{{ route.query.endDate }} 여행 끝</a-timeline-item>
    </a-timeline>
  </div>
</template>

<style scoped></style>
