<script setup>
import PlanMenu from "@/components/plan/PlanMenu.vue";
import { ref } from "vue";
</script>

<template>
  <div>
    <a-row>
      <!-- 10 14 -->
      <a-col :span="20">
        <PlanMenu></PlanMenu>
      </a-col>
      <a-col :span="1">col</a-col>
    </a-row>
  </div>
</template>

<style scoped></style>
