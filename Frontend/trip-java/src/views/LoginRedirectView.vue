<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
//import tokenStore from "@/stores/tokenStore.js";

console.log("돌아와");
const route = useRoute();
const router = useRouter();

const token = route.query.token;

console.log("token: " + token);
if (token) {
  tokenStore.commit("setToken", token);
  sessionStorage.setItem("accessToken", token);
  window.alert("로그인하였습니다.");
  router.push({ name: "home" });
} else {
  window.alert("로그인에 실패하였습니다.");
  router.push({ name: "home" });
}
</script>

<template>
  <div>이건가?</div>
</template>

<style scoped></style>
