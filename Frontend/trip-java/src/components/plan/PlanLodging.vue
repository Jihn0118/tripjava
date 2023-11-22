<script setup>
import { ref } from "vue";

import {
  StarOutlined,
  LikeOutlined,
  MessageOutlined,
  HeartFilled,
} from "@ant-design/icons-vue";

// const listData = [];

// for (let i = 0; i < 23; i++) {
//   listData.push({
//     href: "https://www.antdv.com/",
//     title: `ant design vue part ${i}`,
//     avatar: "https://joeschmoe.io/api/v1/random",
//     description: "Ant Design, s refine.",
//     content: "We supply a seritotypes beautifully and efficiently.",
//   });
// }

defineProps({
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
  //   icon: StarOutlined,
  //   text: "156",
  // },
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

const onSearch = (searchValue) => {
  console.log("검색", searchValue);
  //console.log("or use this.value", value.value);
};
</script>

<template>
  <div style="height: 1000px; background-color: white; width: 450px">
    <h1>숙소 설정</h1>
    <a-input-search
      v-model:value="keyword"
      placeholder="숙소명을 입력하세요."
      enter-button
      @search="onSearch"
    />
    <a-list
      item-layout="vertical"
      size="small"
      :pagination="pagination"
      :data-source="infoList"
      style="text-align: center"
    >
      <template #renderItem="{ item }">
        <a-list-item :key="item.contentId">
          <template #actions>
            <span v-for="{ icon, text } in actions" :key="icon">
              <component :is="icon" style="margin-right: 8px; color: red" />
              {{ item.love }}
            </span>
          </template>
          <template #extra>
            <div
              style="display: flex; flex-direction: column; align-items: center"
            >
              <img width="182" alt="no Image" :src="item.image" />
              <a-button type="primary" style="width: 60px; margin-top: 5px"
                >담기</a-button
              >
            </div>
          </template>
          <a-list-item-meta :description="item.description">
            <template #title>
              <a :href="item.href">{{ item.title }}</a>
            </template>
          </a-list-item-meta>
          {{ item.addr }}
        </a-list-item>
      </template>
    </a-list>
  </div>
</template>

<style scoped>
h1 {
  margin-bottom: 0;
}
</style>
