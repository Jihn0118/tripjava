import { ref } from "vue";
import { defineStore } from "pinia";

export const useMenuStore = defineStore("menuStore", () => {
  const menuList = ref([
    { name: "home", show: true, routeName: "home", key: "2" },
    { name: "plan", show: false, routeName: "plan", key: "1" },
    { name: "information", show: false, routeName: "information", key: "3" },
    { name: "mypage", show: false, routeName: "user-mypage", key: "4" },
  ]);

  const menuList2 = ref([
    { name: "logout", show: true, routeName: "user-logout" },
    { name: "login", show: true, routeName: "user-login" },
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
