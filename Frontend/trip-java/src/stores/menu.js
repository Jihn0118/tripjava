import { ref } from "vue";
import { defineStore } from "pinia";

export const useMenuStore = defineStore("menuStore", () => {
  const menuList = ref([
    { name: "홈으로", show: true, routeName: "home", key: "2" },
    { name: "여행계획", show: false, routeName: "plan", key: "1" },
    { name: "여행지정보", show: false, routeName: "information", key: "3" },
    { name: "마이페이지", show: false, routeName: "user-mypage", key: "4" },
  ]);

  const changeMenuState = () => {
    console.log("changeMenuState 함수 안 호출");
    menuList.value = menuList.value.map((item) => ({
      ...item,
      show: !item.show,
    }));
    menuList.value[0].show = true;
  };
  return {
    menuList,
    changeMenuState,
  };
});
