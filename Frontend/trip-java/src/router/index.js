import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import LoginRedirectView from "../views/LoginRedirectView.vue";
import Planview from "@/views/PlanView.vue";
import InformationView from "@/views/InformationView.vue";
import PlanDetails from "@/components/plan/PlanDetails.vue";

import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

import { message } from "ant-design-vue";
const error = () => {
  message.error("로그인이 필요합니다");
};

const onlyAuthUser = async (to, from, next) => {
  const memberStore = useMemberStore();
  const { userInfo, isValidToken } = storeToRefs(memberStore);

  if (userInfo.value === null) {
    error();
    next({ name: "user-login" });
  } else {
    next();
  }
};

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/oauth2/redirect",
      name: "oauth2_redirect",
      component: LoginRedirectView,
    },
    {
      path: "/plan",
      name: "plan",
      beforeEnter: onlyAuthUser,
      component: Planview,
      // children: [
      //   {
      //     path: "/1",
      //     name: "plan-menu-calendar",
      //     component: PlanCalendar,
      //   },
      //   {
      //     path: "/2",
      //     name: "plan-menu-location",
      //     component: PlanLocation,
      //   },{
      //     path: "/3",
      //     name: "plan-menu-calendar",
      //     component: PlanLodging,
      //   },
      // ]
    },
    {
      path: "/plan/:travelId",
      name: "plan-detail",
      component: PlanDetails,
    },
    {
      path: "/information",
      name: "information",
      component: InformationView,
    },
    {
      path: "/information/:infoId",
      name: "information-detail",
      beforeEnter: onlyAuthUser,
      component: () => import("@/components/tripInfo/InfoDetail.vue"),
    },
    {
      path: "/user",
      name: "user",
      component: () => import("@/views/TheUserView.vue"),
      children: [
        {
          path: "login",
          name: "user-login",
          component: () => import("@/components/users/UserLogin.vue"),
        },
        {
          path: "join",
          name: "user-join",
          component: () => import("@/components/users/UserRegister.vue"),
        },
        {
          path: "logout",
          name: "user-logout",
          component: () => import("@/components/users/UserLogin.vue"),
        },
        {
          path: "mypage",
          name: "user-mypage",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/users/UserMyPage.vue"),
        },
        {
          path: "myplan",
          name: "user-plan",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/plan/PlanDetails.vue"),
          //component: () => import("@/components/users/UserPlans.vue"),
        },
        // {
        //   path: "modify/:userid",
        //   name: "user-modify",
        //   component: () => import("@/components/users/UserModify.vue"),
        // },
      ],
    },
  ],
});

export default router;
