<script setup>
import { ref } from "vue";

import { StarOutlined, LikeOutlined, MessageOutlined, HeartFilled } from "@ant-design/icons-vue";

defineProps({
  plan: Object,
  infoList: Array,
});

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
  // {
  //   icon: LikeOutlined,
  //   text: "156",
  // },
  // {
  //   icon: MessageOutlined,
  //   text: "2",
  // },
];

const keyword = ref("");

// 키워드명으로 검색
const onSearch = (searchValue) => {
  console.log("검색", searchValue);
  //console.log("or use this.value", value.value);
};

const activeKey = ref("1");

// O일차에 관광지 데이터가 있는 지 체크하는 변수
const checkAttractions = ref([]);

const modalVisible = ref(false);

const setModal = ref(false);

const options = ref([]);

const handleChange = (value) => {
  console.log(`selected ${value}`);
};

const filterOption = (input, option) => {
  return option.value.toLowerCase().indexOf(input.toLowerCase()) >= 0;
};
const selectValue = ref(undefined);

// 모달 만든 적 없으면 select 옵션 만들고 모달 띄우기
const openModal = (plan, item) => {
  if (!setModal.value) {
    for (let i = 1; i <= plan.dateDiff; i++) {
      options.value.push({ value: i, label: i + "일차" });
      checkAttractions.value[i - 1] = false; // O일차 데이터가 있는 지 확인하는 배열 객체 false로 초기화
    }
    setModal.value = true;
  }

  itemToAdd.value = item;
  console.log("itemToAdd in openModal");
  console.log(itemToAdd);
  modalVisible.value = true;
};

// 선택된 관광지 데이터
const itemToAdd = ref({});

const stations = ref([
  {
    day: Number,
    latitude: String,
    longitude: String,
  },
]);

// 관광지 담기
const select = (plan) => {
  // 마커 정보 배열에 정보 추가
  stations.value.push({
    day: selectValue.value,
    latitude: itemToAdd.value.latitude,
    longitude: itemToAdd.value.longitude,
  });

  console.log("관광지 카카오에 담기");
  console.log(stations.value);

  // O일차에 기존에 데이터가 하나도 없으면 객체 생성
  if (checkAttractions.value[selectValue.value - 1] == false) {
    let attraction = [];

    itemToAdd.value["sequence"] = 1;

    attraction.push(itemToAdd.value);

    plan.days.push({
      day: selectValue.value,
      attractions: attraction,
      lodging: null,
    });

    console.log(plan.days);

    checkAttractions.value[selectValue.value - 1] = true;
  } else {
    // O일차에 기존에 데이터가 있으면 관광지 추가
    for (let i = 0; i < plan.days.length; i++) {
      if (plan.days[i].day == selectValue.value) {
        itemToAdd.value["sequence"] = plan.days[i].attractions.length + 1; // 몇 개 있는 지
        console.log("기존에 " + plan.days[i].day + "일차에 데이터 있음");
        plan.days[i].attractions.push(itemToAdd.value);
      }
    }
  }

  console.log("--- 담은 결과: plan ----");
  console.log(plan);
  modalVisible.value = false;
};

// 중간에 리스트에서 빼기 버튼 눌렀을 때,
//마커 배열에서도 빼줘야하고,
// plan에서도 빼줘야함

const extractPlan = (attraction, plan, day) => {
  console.log("추출버튼: ");
  // 계획에서 빼주기
  console.log("plan 하나 빼기 전");
  console.log(plan);

  plan.days.forEach((item) => {
    if (item.day === day) {
      item.attractions.forEach((target, index) => {
        if (target.latitude == attraction.latitude && target.longitude == attraction.longitude) {
          item.attractions.splice(index, 1);
          console.log("plan에서 하나 빼기");
          console.log(plan);
        }
      });
    }
  });

  console.log("카카오 하나 빼기 전");
  console.log(stations.value);

  stations.value.forEach((item, index) => {
    if (
      item.day === day &&
      item.latitude == attraction.latitude &&
      item.longitude == attraction.longitude
    ) {
      stations.value.splice(index, 1);
      console.log("카카오 마커 배열에서 하나 빼기");
      console.log(stations.value);
    }
  });
};
</script>

<template>
  <a-modal
    v-model:open="modalVisible"
    title="이 항목을 며칠 차에 추가할까요?"
    centered
    :item="itemToAdd"
    @ok="select(plan)"
  >
    <!--모달창 안에 select창-->
    <a-select
      v-model:value="selectValue"
      show-search
      placeholder="Select a person"
      style="width: 200px"
      :options="options"
      :filter-option="filterOption"
      @change="handleChange"
    ></a-select>
  </a-modal>
  <div style="display: flex; flex-direction: row; height: 1000px; background-color: white">
    <div style="width: 450px">
      <h1>장소 선택</h1>
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
        :data-source="infoList"
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
              <div style="display: flex; flex-direction: column; align-items: center">
                <img width="182" alt="no Image" :src="item.image" />
                <a-button
                  type="primary"
                  style="width: 60px; margin-top: 5px"
                  @click="openModal(plan, item)"
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

    <div style="margin-left: 5px; min-width: 350px">
      <h3 v-show="plan.endDate != ''">
        {{ plan.startDate }} ~ {{ plan.endDate }} ({{ plan.dateDiff - 1 }}박 {{ plan.dateDiff }}일)
      </h3>
      <div class="card-container">
        <a-tabs v-model:activeKey="activeKey" type="card">
          <a-tab-pane
            v-for="(item, index) in plan.dateDiff"
            :key="index + 1"
            :tab="`${index + 1}일차`"
          >
            <p>{{ index + 1 }} 일차</p>
            <div v-for="planDay in plan.days">
              <div v-show="planDay.day == index + 1">
                <div
                  v-for="attraction in planDay.attractions"
                  :key="attraction.contentId"
                  style="margin-top: 10px"
                >
                  <div>{{ attraction.sequence }} 번</div>
                  <div>{{ attraction.contentId }}</div>
                  <div>{{ attraction.title }}</div>
                  <div>{{ attraction.latitude }}</div>
                  <div>{{ attraction.longitude }}</div>
                  <a-button type="primary" danger @click="extractPlan(attraction, plan, index + 1)"
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
[data-theme="dark"] .card-container > .ant-tabs-card .ant-tabs-content > .ant-tabs-tabpane {
  background: #141414;
}
[data-theme="dark"] .card-container > .ant-tabs-card .ant-tabs-tab-active {
  background: #141414;
  border-color: #141414;
}
</style>
