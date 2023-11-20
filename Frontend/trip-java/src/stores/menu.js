import { ref } from "vue";
import { defineStore } from "pinia";

export const useMenuStore = defineStore("menuStore", () => {
  const menuList = ref([
    { name: "홈으로", show: true, routeName: "home", key: "2" },
    { name: "여행계획", show: true, routeName: "plan", key: "1" },
    { name: "여행지정보", show: true, routeName: "information", key: "3" },
    { name: "마이페이지", show: true, routeName: "user-mypage", key: "4" },
  ]);

  const menuList2 = ref([
    { name: "로그아웃", show: false, routeName: "user-logout" },
    { name: "로그인", show: true, routeName: "user-login" },
  ]);

  const changeMenuState = () => {
    console.log("changeMenuState되니?");
    menuList.value = menuList.value.map((item) => ({
      ...item,
      show: !item.show,
    }));
    menuList2.value = menuList2.value.map((item) => ({
      ...item,
      show: !item.show,
    }));
    menuList[0].value.show = true;
    console.log("메뉴1: " + menuList.value);
    console.log("메뉴2 :" + menuList2);
  };
  return {
    menuList,
    menuList2,
    changeMenuState,
  };
});
