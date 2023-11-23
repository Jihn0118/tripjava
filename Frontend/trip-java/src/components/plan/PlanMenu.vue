<script setup>
import { h, ref, onMounted } from "vue";

import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

import { message } from "ant-design-vue";
import { CalendarOutlined, AimOutlined } from "@ant-design/icons-vue";

import PlanLocation from "./PlanLocation.vue";
import PlanCalendar from "./PlanCalendar.vue";

import VKakaoMap from "@/components/common/VKakaoMap.vue";

import { listInfo } from "@/api/attractionInfo";
import { registerTravelPlans } from "@/api/plan";

const memberStore = useMemberStore();

const { userInfo } = storeToRefs(memberStore);

const menuNumber = ref("1");

const selectedKeys = ref([]);
const openKeys = ref([]);
const items = ref([
  {
    key: "1",
    icon: () => h(CalendarOutlined),
    label: "STEP 1 날짜 설정",
    title: "STEP 1 날짜 설정",
  },
  {
    key: "2",
    icon: () => h(AimOutlined),
    label: "STEP 2 장소 선택",
    title: "STEP 2 장소 선택",
  },
]);

const handleClick = (menuInfo) => {
  menuNumber.value = menuInfo.key;
};

const emit = defineEmits(["setTripdate", "setStations", "updateInfoList"]);

const setTripdate = (val) => {
  plan.title = val.title;
  plan.startDate = val.startDate;
  plan.endDate = val.endDate;
  plan.dateDiff = val.dateDiff;

  message.success("날짜가 성공적으로 저장되었습니다.");

  // // 기존에 선택된 메뉴의 클래스를 제거
  // items.value.forEach((item) => {
  //   item.class = "ant-menu-item";
  // });

  menuNumber.value = "2";

  // items.value[1].class = "ant-menu-item-selected";
};

// 카카오 마커 배열
const stations = ref([]);

// 카카오 마커 배열 업데이트 함수
const setStations = function (val) {
  stations.value = val;
  console.log("부모");
  console.log(stations.value);
};

// 여행 일정 객체
// 제목, 여행 시작 날짜, 여행 종료 날짜, 몇 일 간의 여행인지
const plan = ref({
  title: "",
  startDate: "",
  endDate: "",
  dateDiff: 0,
  days: [
    // {
    //   day: Number,
    //   attractions: Array,
    //
    // },
    // [
    //   {
    //     attractions: [
    //       {
    //         contentId: Number,
    //          sequence: Number,   // 방문하는 순서 (일차 아님)
    //         title: String,
    //         image: String,
    //         latitude: Number,
    //         longitude: Number,
    //         love: Number,
    //         description: String,
    //       },
    //     ],
    //     lodging: {
    //       contentId: Number,
    //       title: String,
    //       image: String,
    //       latitude: Number,
    //       longitude: Number,
    //       love: Number,
    //       description: String,
    //     },
    //   },
    // ],
  ],
});

// 관광지 정보
const infoList = ref([]);

const filteredLocationList = ref([]);
const filteredLodgingList = ref([]);

const getInfoList = () => {
  listInfo(
    searchCondition.value,
    ({ data }) => {
      infoList.value = data;
      for (let i = 0; i < infoList.value.length; i++) {
        if (infoList.value[i].contentTypeId == 32) {
          filteredLodgingList.value = [
            ...filteredLodgingList.value,
            infoList.value[i],
          ];
        } else {
          filteredLocationList.value = [
            ...filteredLocationList.value,
            infoList.value[i],
          ];
        }
      }
    },
    (err) => {
      console.log(err);
    }
  );
};

onMounted(() => {
  getInfoList();
});

const searchCondition = ref({
  contentType: "",
  keyword: "",
  sidoCode: "",
});

const updateInfoList = (newList) => {
  filteredLocationList.value = newList;
  console.log("부모 emit");
  console.log(filteredLocationList.value);
};

// 일정 저장
const savePlan = () => {
  let addStartDate = plan.value.startDate;
  let addEndDate = plan.value.endDate;
  let addTravelName = plan.value.title;
  let addDays = [];

  plan.value.days.forEach((day) => {
    let dayNum = day.day; // dayNumber 세팅
    let addToDetails = []; // 해당 날짜의 details
    day.attractions.forEach((attraction, index2) => {
      addToDetails.push({
        sequence: index2 + 1,
        contentId: attraction.contentId,
      });
    });
    addDays.push({ dayNumber: dayNum, details: addToDetails }); // days 배열에 추가
  });
  let addPlan = {
    userId: userInfo.value.memberId,
    startDate: addStartDate,
    endDate: addEndDate,
    travelName: addTravelName,
    days: addDays,
  };

  console.log("서버로 보낼 plan 데이터: ");
  console.log(addPlan);

  registerTravelPlans(addPlan);
};
</script>

<template>
  <div style="margin-top: 100px">
    <div style="display: flex; flex-direction: row; background-color: red">
      <div>
        <a-menu
          v-model:openKeys="openKeys"
          v-model:selectedKeys="selectedKeys"
          class="menu"
          mode="vertical"
          :items="items"
          @click="handleClick"
        />
        <a-button type="primary" @click="savePlan">일정 저장</a-button>
      </div>

      <div>
        <!--자식 컴포넌트들 넣고 props emits으로 메뉴 부모한테 보내고 메뉴 부모에서 버튼 누르면 모든 여행 계획 데이터가 저장-->
        <PlanCalendar
          v-show="menuNumber === '1'"
          :plan="plan"
          @set-tripdate="setTripdate"
        />
        <PlanLocation
          v-show="menuNumber === '2'"
          :plan="plan"
          :infoList="filteredLocationList"
          @set-stations="setStations"
          @updateInfoList="updateInfoList"
        />
      </div>
      <!-- 카카오맵 컴포넌트한테 Props로 :stations = "stations" 넘겨줘-->
    </div>
  </div>
  <VKakaoMap :stations="stations" />
</template>

<style scoped>
.menu {
  width: 200px;
  height: 200px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
</style>
