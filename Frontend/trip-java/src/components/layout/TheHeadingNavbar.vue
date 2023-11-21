<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";

const router = useRouter();

const memberStore = useMemberStore();
const { userInfo, isLogin } = storeToRefs(memberStore);
const { userLogout } = memberStore;

const selectedKeys = ref(null);

const logout = () => {
  selectedKeys.value = [];
  console.log("로그아웃!!!");
  userLogout(userInfo.id);
  router.push("/user/login");
};
</script>

<template>
  <a-layout-header>
    <a-row>
      <a-col :flex="3">
        <a-menu
          v-model:selected-keys="selectedKeys"
          theme="dark"
          mode="horizontal"
          :style="{ lineHeight: '64px' }"
        >
          <a-menu-item>
            <router-link :to="{ name: 'home' }">
              <img
                src="@/assets/logo.png"
                alt="로고 사진 없음"
                style="width: 120px; height: 45px; margin: auto 5px"
              />
            </router-link>
          </a-menu-item>
          <a-menu-item key="2">
            <router-link :to="{ name: 'home' }"> 홈으로 </router-link>
          </a-menu-item>
          <a-menu-item key="1" v-if="isLogin">
            <router-link :to="{ name: 'plan' }"> 여행계획 </router-link>
          </a-menu-item>
          <a-menu-item key="3" v-if="isLogin">
            <router-link :to="{ name: 'information' }">
              여행지정보
            </router-link>
          </a-menu-item>
          <a-menu-item key="4" v-if="isLogin">
            <router-link :to="{ name: 'user-mypage' }">
              마이페이지
            </router-link>
          </a-menu-item>
        </a-menu>
      </a-col>
      <a-col :flex="2">
        <a-menu
          :selected-keys="[]"
          theme="dark"
          mode="horizontal"
          :style="{ lineHeight: '64px' }"
        >
          <a-menu-item>
            <router-link v-if="isLogin" to="/" @click.prevent="logout">
              로그아웃
            </router-link>
            <router-link v-else :to="{ name: 'user-login' }"
              >로그인</router-link
            >
          </a-menu-item>
        </a-menu>
      </a-col>
    </a-row>
  </a-layout-header>
</template>

<style scoped>
.header-space-between {
  width: 100%;
  display: flex;
  justify-content: space-between;
}
.ant-anchor-link {
  width: 10px;
}
</style>
<style>
.ant-anchor-wrapper {
  height: 30px;
}

.ant-anchor-wrapper::before {
  border-bottom: none;
}

.ant-anchor-link {
  height: 100%;
  font-size: 20px;
}
</style>
