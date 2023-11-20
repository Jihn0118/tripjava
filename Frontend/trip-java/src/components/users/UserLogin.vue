<script setup>
import { ref } from "vue";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";
import { useMenuStore } from "@/stores/menu";

const router = useRouter();

const memberStore = useMemberStore();

const { isLogin } = storeToRefs(memberStore);
const { userLogin, getUserInfo } = memberStore;
const { changeMenuState } = useMenuStore();

const loginUser = ref({
  id: "",
  password: "",
});

const login = async () => {
  console.log(loginUser.value);
  await userLogin(loginUser.value);
  let token = sessionStorage.getItem("accessToken");
  if (isLogin) {
    getUserInfo(token);
    changeMenuState();
  }
  router.push("/");
};
</script>

<template>
  <a-form
    :model="loginUser"
    :label-col="{ span: 6 }"
    :wrapper-col="{ span: 6 }"
  >
    <a-form-item label="아이디" name="id">
      <a-input v-model:value="loginUser.id" />
    </a-form-item>

    <!-- :rules="[{ required: true }]" -->
    <a-form-item label="비밀번호" name="password">
      <a-input-password
        v-model:value="loginUser.password"
        @keyup.enter="login"
      />
    </a-form-item>

    <!-- <a-form-item name="remember" :wrapper-col="{ offset: 8, span: 16 }">
      <a-checkbox>아이디 저장</a-checkbox>
    </a-form-item> -->

    <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
      <a-button type="primary" @click="login">로그인</a-button>
    </a-form-item>
  </a-form>
</template>
