<script setup>
import { h, ref } from "vue";
import {
  HomeOutlined,
  CalendarOutlined,
  AimOutlined,
  StarOutlined,
  LikeOutlined,
  MessageOutlined,
} from "@ant-design/icons-vue";
import { useRouter } from "vue-router";
import dayjs from "dayjs";

const dateFormat = "YYYY-MM-DD";

const router = useRouter();

const subPage = ref("1");

const selectedKeys = ref([]);
const openKeys = ref([]);
const items = ref([
  {
    key: "1",
    icon: () => h(CalendarOutlined),
    label: "STEP 1 날짜 확인",
    title: "STEP 1 날짜 확인",
  },
  {
    key: "2",
    icon: () => h(AimOutlined),
    label: "STEP 2 장소 선택",
    title: "STEP 2 장소 선택",
  },
  {
    key: "3",
    icon: () => h(HomeOutlined),
    label: "STEP 3 숙소 설정",
    title: "STEP 3 숙소 설정",
  },
]);
const handleClick = (menuInfo) => {
  //router.replace("/plan/" + menuInfo.key);
  console.log("click ", menuInfo.key);
};

const plan = ref({
  title: "",
  date: Object,
});

const zz = ref([dayjs("2023-11-13", dateFormat), dayjs("2023-11-15", dateFormat)]);

const showDate = () => {
  console.log(zz.value);
};

const listData = [];
for (let i = 0; i < 23; i++) {
  listData.push({
    href: "https://www.antdv.com/",
    title: `ant design vue part ${i}`,
    avatar: "https://joeschmoe.io/api/v1/random",
    description: "Ant Design, s refine.",
    content: "We supply a seritotypes beautifully and efficiently.",
  });
}
const pagination = {
  onChange: (page) => {
    console.log(page);
  },
  pageSize: 3,
  style: "text-align: center;",
};
const actions = [
  {
    icon: StarOutlined,
    text: "156",
  },
  {
    icon: LikeOutlined,
    text: "156",
  },
  {
    icon: MessageOutlined,
    text: "2",
  },
];
</script>

<template>
  <div style="display: flex; flex-direction: row">
    <div style="height: 700px">
      <a-menu
        v-model:openKeys="openKeys"
        v-model:selectedKeys="selectedKeys"
        class="menu"
        mode="vertical"
        :items="items"
        @click="handleClick"
      />
    </div>
    <div style="height: 700px; background-color: white">
      <!--이 자리에 넣어야 됨!!!-->
      <div>
        <a-input v-model:value="plan.title" size="large" placeholder="여행 제목 입력" />
        <div>{{ plan.title }}</div>
        <a-range-picker v-model:value="zz.value" :format="dateFormat" />
        <div>{{ zz[0] }}</div>
        <div>{{ zz[1] }}</div>
        <a-button type="primary">시간 설정 완료</a-button>
      </div>
    </div>
  </div>
</template>

<!--날짜랑 제목 정하기-->
<!-- <div>
  <a-input v-model:value="plan.title" size="large" placeholder="여행 제목 입력" />
  <a-range-picker v-model:value="zz.value" :format="dateFormat" />
  <div>{{ zz[0] }}</div>
  <div>{{ zz[1] }}</div>
  <a-button type="primary">시간 설정 완료</a-button>
</div> -->

<!--관광지리스트 일정에 담기-->
<!-- <a-list
        item-layout="vertical"
        size="small"
        :pagination="pagination"
        :data-source="listData"
        style="text-align: center"
      >
        <template #renderItem="{ item }">
          <a-list-item key="item.title">
            <template #actions>
              <span v-for="{ icon, text } in actions" :key="icon">
                <component :is="icon" style="margin-right: 8px" />
                {{ text }}
              </span>
            </template>
            <template #extra>
              <img
                width="182"
                alt="logo"
                src="https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png"
              />
            </template>
            <a-list-item-meta :description="item.description">
              <template #title>
                <a :href="item.href">{{ item.title }}</a>
              </template>
              <template #avatar><a-avatar :src="item.avatar" /></template>
            </a-list-item-meta>
            {{ item.content }}
            <div>
              <a-button type="primary">담기</a-button>
            </div>
          </a-list-item>
        </template>
      </a-list> -->
<style scoped>
.menu {
  width: 200px;
  height: 200px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
</style>
