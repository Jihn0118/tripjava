<script setup>
import { RouterView } from "vue-router";
import UserLogin from "./components/users/UserLogin.vue";
import HomeView from "./views/HomeView.vue";
import UserRegister from "@/components/users/UserRegister.vue";
</script>

<template>
  <div>홈이요</div>
  <div>
    <router-view></router-view>
  </div>
</template>

<style scoped></style>
