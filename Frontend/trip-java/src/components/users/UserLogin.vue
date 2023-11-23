<script setup>
import { ref } from "vue";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";

const router = useRouter();

const memberStore = useMemberStore();

const { isLogin } = storeToRefs(memberStore);
const { userLogin, getUserInfo } = memberStore;

const loginUser = ref({
  memberId: "",
  password: "",
});
//
const login = async () => {
  console.log(loginUser.value);
  await userLogin(loginUser.value);
  let token = sessionStorage.getItem("accessToken");
  if (isLogin.value) {
    getUserInfo(token);
    router.push("/");
  }
};

const labelCol = {
  span: 100,
};

const wrapperCol = {
  span: 100,
};
</script>

<template>
  <div class="text" style="width: 100%; margin-top: 100px; display: flex; justify-content: center">
    <div style="width: 40%">
      <a-form :model="loginUser" :label-col="labelCol" :wrapper-col="wrapperCol">
        <a-form-item label="아이디" name="id">
          <a-input v-model:value="loginUser.memberId" />
        </a-form-item>

        <!-- :rules="[{ required: true }]" -->
        <a-form-item label="비밀번호" name="password">
          <a-input-password v-model:value="loginUser.password" @keyup.enter="login" />
        </a-form-item>

        <!-- <a-form-item name="remember" :wrapper-col="{ offset: 8, span: 16 }">
          <a-checkbox>아이디 저장</a-checkbox>
        </a-form-item> -->

        <a-form-item :wrapper-col="{ offset: 10, span: 16 }">
          <a-button @click.prevent="login">로그인</a-button>
          <a-button style="margin-left: 5px">
            <router-link :to="{ name: 'user-join' }">회원가입</router-link>
          </a-button>
        </a-form-item>
      </a-form>
    </div>
  </div>

</template>
