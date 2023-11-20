<script setup>
import { ref } from "vue";
import { useMenuStore } from "@/stores/menu";
import { storeToRefs } from "pinia";

const menuStore = useMenuStore();

const { menuList, menuList2 } = storeToRefs(menuStore);
const { changeMenuState } = menuStore;

const selectedKeys = ref(null);
// import { useMenuStore } from "@/stores/menu";
// import { storeToRefs } from "pinia";

// const { menuList } = storeToRefs(menuStore);
const logout = () => {
  console.log("로그아웃!!!");
  changeMenuState();
};
</script>

<template>
  <a-layout-header>
    <div class="logo"></div>
    <a-menu
      v-model:selectedKeys="selectedKeys"
      theme="dark"
      mode="horizontal"
      :style="{ lineHeight: '64px' }"
      display="flex"
      justify-content="space-between"
    >
      <div class="header-space-between">
        <div>
          <a-menu-item v-for="menu in menuList" :key="menu.key">
            <div v-if="menu.show">
              <router-link :to="{ name: menu.routeName }">{{
                menu.name
              }}</router-link>
            </div>
          </a-menu-item>

          <!-- <a-menu-item key="2">
            <router-link :to="{ name: 'home' }">Home</router-link>
          </a-menu-item>
          <div v-for="(menu, index) in menuList" :key="menu.routeName">
            <div v-if="menu.show">
              <a-menu-item :key="index">
                <router-link :to="{ name: menu.routeName }">{{
                  menu.name
                }}</router-link>
              </a-menu-item>
            </div>
          </div> -->
          <!-- <a-menu-item key="1">
            <router-link :to="{ name: 'plan' }">Plan</router-link>
          </a-menu-item>
          <a-menu-item key="3">
            <router-link :to="{ name: 'information' }">Info</router-link>
          </a-menu-item> -->
        </div>
        <div>
          <div v-for="menu2 in menuList2" :key="menu2.routeName">
            <div v-if="menu2.show">
              <div v-if="menu2.routeName === 'user-logout'">
                <router-link to="/" @click.prevent="logout">{{
                  menu2.name
                }}</router-link>
              </div>
              <div v-else>
                <router-link :to="{ name: menu2.routeName }">{{
                  menu2.name
                }}</router-link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </a-menu>
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
