<script setup>
import { ref } from "vue";
import { message } from "ant-design-vue";
import { listInfo } from "@/api/attractionInfo";

import { HeartFilled } from "@ant-design/icons-vue";

const props = defineProps({
  plan: Object,
  infoList: Array,
});

const emit = defineEmits(["setStations", "updateInfoList","save"]);

const setStations = function (val) {
  emit("setStations", val);
};

const updateInfoList = function (val) {
  emit("updateInfoList", val);
};

const click = () =>{
  emit("save");
}


const pagination = {
  onChange: (page) => {
    console.log(page);
  },
  pageSize: 4,
  style: "text-align: center;",
};

const actions = [
  {
    icon: HeartFilled,
    text: "",
  },
];

const keyword = ref("");

// 키워드명으로 검색
const onSearch = () => {
  const searchCondition = {
    contentType: attractionSelectValue.value,
    keyword: keyword.value,
    sidoCode: sidoSelectValue.value,
  };
  listInfo(
    searchCondition,
    ({ data }) => {
      updateInfoList(data);
    },
    (error) => {
      console.log(error);
    }
  );
};

const activeKey = ref("1");

// O일차에 관광지 데이터가 있는 지 체크하는 변수
const checkAttractions = ref([]);

// 모달 띄우는 상태 변수
const modalVisible = ref(false);

const setModal = ref(false);

// O 일차 select option
const options = ref([]);

const attractionTypeOptions = [
  { value: "12", label: "관광지" },
  { value: "14", label: "문화시설" },
  { value: "15", label: "축제공연행사" },
  { value: "25", label: "여행코스" },
  { value: "28", label: "레포츠" },
  { value: "32", label: "숙박" },
  { value: "38", label: "쇼핑" },
  { value: "39", label: "음식점" },
];

const attractionSelectValue = ref(undefined);
const sidoSelectValue = ref(undefined);

const sidoCodeOptions = [
  {
    value: "1",
    label: "서울",
  },
  {
    value: "2",
    label: "인천",
  },
  {
    value: "3",
    label: "대전",
  },
  {
    value: "4",
    label: "대구",
  },
  {
    value: "5",
    label: "광주",
  },
  {
    value: "6",
    label: "부산",
  },
  {
    value: "7",
    label: "울산",
  },
  {
    value: "8",
    label: "세종특별자치시",
  },
  {
    value: "31",
    label: "경기도",
  },
  {
    value: "32",
    label: "강원도",
  },
  {
    value: "33",
    label: "충청북도",
  },
  {
    value: "34",
    label: "충청남도",
  },
  {
    value: "35",
    label: "경상북도",
  },
  {
    value: "36",
    label: "경상남도",
  },
  {
    value: "37",
    label: "전라북도",
  },
  {
    value: "38",
    label: "전라남도",
  },
  {
    value: "39",
    label: "제주도",
  },
];

const handleChange = (value) => {
  console.log(`selected ${value}`);
};

const filterOption = (input, option) => {
  return option.value.toLowerCase().indexOf(input.toLowerCase()) >= 0;
};
const selectValue = ref(undefined);

// 모달 만든 적 없으면 select 옵션 만들고 모달 띄우기
const openModal = (item) => {
  if (!setModal.value) {
    for (let i = 1; i <= props.plan.dateDiff; i++) {
      options.value.push({ value: i, label: i + "일차" });
      checkAttractions.value[i - 1] = false; // O일차 데이터가 있는 지 확인하는 배열 객체 false로 초기화
    }
    setModal.value = true;
  }

  itemToAdd.value = item;

  modalVisible.value = true;
};

// 선택된 관광지 데이터
const itemToAdd = ref({});

// 카카오 마커 배열
const stations = ref([]);

// 관광지 담기
const select = () => {
  // O일차에 기존에 데이터가 하나도 없으면 객체 생성
  if (checkAttractions.value[selectValue.value - 1] == false) {
    let attraction = [];

    itemToAdd.value["sequence"] = 1;

    attraction.push(itemToAdd.value);

    props.plan.days.push({
      day: selectValue.value,
      attractions: attraction,
      lodging: null,
    });

    checkAttractions.value[selectValue.value - 1] = true;

    // 마커 정보 배열에 정보 추가
    stations.value.push({
      day: selectValue.value,
      lat: itemToAdd.value.latitude,
      lng: itemToAdd.value.longitude,
    });
  } else {
    let checkInPlan = false;
    // O일차에 기존에 데이터가 있으면 관광지 추가

    // O일차에 기존에 똑같은 관광지 데이터가 있으면 추가 못함
    for (let i = 0; i < props.plan.days.length; i++) {
      if (props.plan.days[i].day == selectValue.value) {
        for (let j = 0; j < props.plan.days[i].attractions.length; j++) {
          if (
            props.plan.days[i].attractions[j].contentId ==
            itemToAdd.value.contentId
          ) {
            message.error(
              "이미 " +
                (i + 1) +
                "일차에 " +
                itemToAdd.value.title +
                "이(가) 있습니다."
            );
            checkInPlan = true;
          }
        }
        break;
      }
    }

    // 없으면 추가
    if (checkInPlan == false) {
      for (let i = 0; i < props.plan.days.length; i++) {
        if (props.plan.days[i].day == selectValue.value) {
          itemToAdd.value["sequence"] =
            props.plan.days[i].attractions.length + 1; // 몇 개 있는 지
          props.plan.days[i].attractions.push(itemToAdd.value);

          // 마커 정보 배열에 정보 추가
          stations.value.push({
            day: selectValue.value,
            lat: itemToAdd.value.latitude,
            lng: itemToAdd.value.longitude,
          });
        }
      }
    }
  }

  setStations(stations.value); // 카카오 마커 업데이트

  modalVisible.value = false;
};

// 중간에 리스트에서 빼기 버튼 눌렀을 때,
//마커 배열에서도 빼줘야하고,
// plan에서도 빼줘야함

const extractPlan = (attraction, day) => {
  // 계획에서 빼주기
  props.plan.days.forEach((item) => {
    if (item.day === day) {
      item.attractions.forEach((target, index) => {
        if (
          target.latitude == attraction.latitude &&
          target.longitude == attraction.longitude
        ) {
          item.attractions.splice(index, 1);
        }
      });
    }
  });

  stations.value.forEach((item, index) => {
    if (
      item.day === day &&
      item.lat == attraction.latitude &&
      item.lng == attraction.longitude
    ) {
      stations.value.splice(index, 1);
    }
  });

  setStations(stations.value); // 카카오 마커 업데이트
};
</script>

<template>
  <a-modal
    v-model:open="modalVisible"
    title="이 항목을 며칠 차에 추가할까요?"
    centered
    :item="itemToAdd"
    @ok="select()"
  >
    <!--모달창 안에 select창-->
    <a-select
      v-model:value="selectValue"
      show-search
      placeholder="O일차"
      style="width: 200px"
      :options="options"
      :filter-option="filterOption"
      @change="handleChange"
    ></a-select>
  </a-modal>
  <div
    style="
      display: flex;
      flex-direction: row;
      height: 1000px;
      background-color: white;
    "
  >
    <div style="width: 400px">
      <div style="display: flex; justify-content: space-between">
        <h1>장소 선택</h1>
        <a-button type="primary" @click="click">일정 저장</a-button>
      </div>

      <!--관광지 타입 구분 select-->
      <a-select
        v-model:value="attractionSelectValue"
        placeholder="관광지 종류"
        style="width: 200px; margin-top: 10px"
        :options="attractionTypeOptions"
        :filter-option="filterOption"
        @change="handleChange"
      ></a-select>

      <!--시도 구분 select-->
      <a-select
        v-model:value="sidoSelectValue"
        placeholder="지역 선택"
        style="width: 200px"
        :options="sidoCodeOptions"
        :filter-option="filterOption"
        @change="handleChange"
      ></a-select>

      <a-input-search
        v-model:value="keyword"
        placeholder="장소명을 입력하세요."
        enter-button
        @search="onSearch"
      />
      <!-- a-list 시작 -->
      <a-list
        item-layout="vertical"
        :pagination="pagination"
        :data-source="props.infoList"
        style="text-align: center"
      >
        <template #renderItem="{ item }">
          <a-list-item :key="item.contentId">
            <!--아이콘 부분-->
            <template #actions>
              <span v-for="{ icon } in actions" :key="icon">
                <component :is="icon" style="margin-right: 8px; color: red" />
                {{ item.love }}
              </span>
            </template>
            <template #extra>
              <div
                style="
                  display: flex;
                  flex-direction: column;
                  align-items: center;
                "
              >
                <img width="182" alt="no Image" :src="item.image" />
                <a-button
                  style="width: 60px; margin-top: 5px"
                  @click="openModal(item)"
                  >담기</a-button
                >
              </div>
            </template>
            <h3>{{ item.title }}</h3>
            <div>{{ item.addr }}</div>
            <!--모달창-->
          </a-list-item>
        </template>
      </a-list>
      <!--a-list 끝-->
    </div>

    <div style="margin-left: 10px; min-width: 350px">
      <h3 v-show="props.plan.endDate != ''">
        {{ props.plan.startDate }} ~ {{ props.plan.endDate }} ({{
          props.plan.dateDiff - 1
        }}박 {{ props.plan.dateDiff }}일)
      </h3>
      <div class="card-container">
        <a-tabs v-model:activeKey="activeKey" type="card">
          <a-tab-pane
            v-for="(item, index) in props.plan.dateDiff"
            :key="index + 1"
            :tab="`${index + 1}일차`"
          >
            <p>{{ index + 1 }} 일차</p>
            <div v-for="planDay in props.plan.days">
              <div v-show="planDay.day == index + 1">
                <div
                  v-for="(attraction, index2) in planDay.attractions"
                  :key="attraction.contentId"
                  style="margin-top: 10px"
                >
                  <div>{{ index2 + 1 }} 번</div>
                  <div>{{ attraction.contentId }}</div>
                  <div>{{ attraction.title }}</div>
                  <div>{{ attraction.latitude }}</div>
                  <div>{{ attraction.longitude }}</div>
                  <a-button
                    type="primary"
                    danger
                    @click="extractPlan(attraction, index + 1)"
                    >빼기</a-button
                  >
                </div>
              </div>
            </div>
          </a-tab-pane>
        </a-tabs>
      </div>
    </div>
  </div>
</template>

<style scoped>
h1 {
  margin-bottom: 0;
  padding: 0px !important;
}
.card-container p {
  margin: 0;
}
.card-container > .ant-tabs-card .ant-tabs-content {
  height: 120px;
  margin-top: -16px;
}
.card-container > .ant-tabs-card .ant-tabs-content > .ant-tabs-tabpane {
  padding: 16px;
  background: #fff;
}
.card-container > .ant-tabs-card > .ant-tabs-nav::before {
  display: none;
}
.card-container > .ant-tabs-card .ant-tabs-tab,
[data-theme="compact"] .card-container > .ant-tabs-card .ant-tabs-tab {
  background: transparent;
  border-color: transparent;
}
.card-container > .ant-tabs-card .ant-tabs-tab-active,
[data-theme="compact"] .card-container > .ant-tabs-card .ant-tabs-tab-active {
  background: #fff;
  border-color: #fff;
}
#components-tabs-demo-card-top .code-box-demo {
  padding: 24px;
  overflow: hidden;
  background: #f5f5f5;
}
[data-theme="compact"] .card-container > .ant-tabs-card .ant-tabs-content {
  height: 120px;
  margin-top: -8px;
}
[data-theme="dark"] .card-container > .ant-tabs-card .ant-tabs-tab {
  background: transparent;
  border-color: transparent;
}
[data-theme="dark"] #components-tabs-demo-card-top .code-box-demo {
  background: #000;
}
[data-theme="dark"]
  .card-container
  > .ant-tabs-card
  .ant-tabs-content
  > .ant-tabs-tabpane {
  background: #141414;
}
[data-theme="dark"] .card-container > .ant-tabs-card .ant-tabs-tab-active {
  background: #141414;
  border-color: #141414;
}
</style>
