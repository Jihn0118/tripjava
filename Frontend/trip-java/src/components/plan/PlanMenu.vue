<script>
import { ref, watch } from "vue";
const selectedKeys = ref(["1"]);
const openKeys = ref(["sub1"]);

const getItem = (label, key, children, type) => {
  return {
    key,
    children,
    label,
    type,
  };
};

const items = ref([
  getItem("날짜 선택", "sub1", [
    getItem(
      "Item 1",
      "g1",
      [getItem("Option 1", "1"), getItem("Option 2", "2")],
      "group"
    ),
    getItem(
      "Item 2",
      "g2",
      [getItem("Option 3", "3"), getItem("Option 4", "4")],
      "group"
    ),
  ]),
  getItem("장소 선택", "sub2", [
    getItem("Option 5", "5"),
    getItem("Option 6", "6"),
    getItem("Submenu", "sub3", [
      getItem("Option 7", "7"),
      getItem("Option 8", "8"),
    ]),
  ]),
  {
    type: "divider",
  },
  getItem("숙소 선택", "sub4", [
    getItem("Option 9", "9"),
    getItem("Option 10", "10"),
    getItem("Option 11", "11"),
    getItem("Option 12", "12"),
  ]),
  getItem(
    "Group",
    "grp",

    [getItem("Option 13", "13"), getItem("Option 14", "14")],
    "group"
  ),
]);
const handleClick = (e) => {
  console.log("click", e);
};
watch(openKeys, (val) => {
  console.log("openKeys", val);
});
</script>
<template>
  <a-menu
    id="dddddd"
    v-model:openKeys="openKeys"
    v-model:selectedKeys="selectedKeys"
    style="width: 256px"
    mode="inline"
    :items="items"
    @click="handleClick"
  ></a-menu>
</template>
