<script setup>
import {ref} from 'vue';

const selectedKeys = ref(null);
</script>

<template>
  <a-layout-header>
  <div class="logo" />
  <a-menu
      v-model:selectedKeys="selectedKeys"
      theme="dark"
      mode="horizontal"
      :style="{ lineHeight: '64px' }"
      display="flex"
      jusitfy="center"
  >
    <a-menu-item key="2">
      <router-link :to="{name:'home'}">Home</router-link>
    </a-menu-item>
    <a-menu-item key="1">
      <router-link :to="{name:'plan'}">Plan</router-link>
    </a-menu-item>
    <a-menu-item key="3">
      <router-link :to="{name:'information'}">Info</router-link>
    </a-menu-item>
  </a-menu>
  </a-layout-header>
</template>


<style scoped>
.ant-anchor-link {
  width: 10px;
}

.ant-affix, .css-dev-only-do-not-override-1qb1s0s {

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



