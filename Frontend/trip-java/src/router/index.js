import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import LoginRedirectView from "../views/LoginRedirectView.vue";
import Planview from "@/views/PlanView.vue";
import InformationView from "@/views/InformationView.vue";
import PlanCalendarView from "@/views/plan/PlanCalendarView.vue";
import PlanLocationView from "@/views/plan/PlanLocationView.vue";
import PlanLodgingView from "@/views/plan/PlanLodgingView.vue";

import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

const onlyAuthUser = async (to, from, next) => {
  const memberStore = useMemberStore();
  const { userInfo, isValidToken } = storeToRefs(memberStore);
  const { getUserInfo } = memberStore;

  let token = sessionStorage.getItem("accessToken");

  if (userInfo.value != null && token) {
    await getUserInfo(token);
  }
  if (!isValidToken.value || userInfo.value === null) {
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
      component: Planview,
      children: [
        {
          path: "/1",
          name: "plan-menu-calendar",
          component: PlanCalendarView,
        },
        {
          path: "/2",
          name: "plan-menu-location",
          component: PlanLocationView,
        },{
          path: "/3",
          name: "plan-menu-calendar",
          component: PlanLodgingView,
        },
      ]
    },
    {
      path: "/information",
      name: "information",
      component: InformationView,
    },
    {
      path: '/information/:infoId',
      name: 'information-detail',
      component: ()=>import("@/components/tripInfo/InfoDetail.vue")
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
          // beforeEnter: onlyAuthUser,
          component: () => import("@/components/users/UserMyPage.vue"),
        },
        {
          path: "myplan",
          name: "user-plan",
          component: () => import("@/components/users/UserPlans.vue"),
        }
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
