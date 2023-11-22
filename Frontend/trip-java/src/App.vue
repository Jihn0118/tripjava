<script setup>

import {ref} from 'vue'
import {useRouter} from "vue-router";
import {useMemberStore} from "@/stores/member";
import {storeToRefs} from "pinia";

const router = useRouter();

const memberStore = useMemberStore();
const {userInfo, isLogin} = storeToRefs(memberStore);
const {userLogout} = memberStore;

const logout = () => {
  clickEvent();
  console.log("로그아웃!!!");
  userLogout(userInfo.id);
  router.push("/");
};

const toggleValue = ref({
  "toggle": true,
  "active": false
})

const showcaseValue = ref({
  "showcase": true,
  "active": false
})

const isOpen = ref(false);

const clickEvent = () => {
  if (isOpen.value) {
    toggleValue.value.active = true;
    showcaseValue.value.active = true;
    isOpen.value = !isOpen.value;
  } else {
    toggleValue.value.active = false;
    showcaseValue.value.active = false;
    isOpen.value = !isOpen.value;
  }
}
</script>

<template>

  <section :class=showcaseValue>
    <header>
      <router-link :to="{ name: 'home' }" style="text-decoration: none;">
        <h2 class="logo">Trip Java</h2>
      </router-link>
      <div :class=toggleValue @click="clickEvent"></div>
    </header>
    <router-view></router-view>
  </section>
  <div class="menu">
    <ul>
      <li>
        <router-link :to="{ name: 'home' }" @click="clickEvent"> Home</router-link>
      </li>
      <li>
        <router-link :to="{ name: 'plan' }" @click="clickEvent"> Plan</router-link>
      </li>
      <li>
        <router-link :to="{ name: 'information' }" @click="clickEvent"> Attraction Info</router-link>
      </li>
      <li>
        <router-link :to="{ name: 'user-mypage' }" @click="clickEvent">My Page</router-link>
      </li>
      <li v-if="isLogin">
        <router-link to="/" @click.prevent="logout">Log Out</router-link>
      </li>
      <li v-else>
        <router-link :to="{ name: 'user-login' }" @click="clickEvent">Log In</router-link>
      </li>
    </ul>
  </div>


</template>

<style>
@import url('https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800,900&display=swap');

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Poppins', sans-serif;
}

header {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  padding: 40px 100px;
  z-index: 1000;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

header .logo {
  color: skyblue;
  text-transform: uppercase;
  cursor: pointer;
  font-size: 3em;
}

.toggle {
  position: relative;
  width: 60px;
  height: 60px;
  background: url(../src/assets/menu.png);
  background-repeat: no-repeat;
  background-size: 30px;
  background-position: center;
}

.toggle.active {
  background: url(../src/assets/x.png);
  background-repeat: no-repeat;
  background-size: 25px;
  background-position: center;
}

.showcase {
  position: absolute;
  right: 0;
  width: 100%;
  min-height: 100vh;
  padding: 50px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #fff;;
  transition: 0.5s;
  z-index: 2;
}

.showcase.active {
  right: 300px;
}

.showcase video {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  opacity: 0.8;
}

.overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: skyblue;
  mix-blend-mode: overlay;
}


.social {
  position: absolute;
  z-index: 10;
  bottom: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.social li {
  list-style: none;
}

.social li a {
  display: inline-block;
  margin-right: 20px;
  filter: invert(1);
  transform: scale(0.5);
  transition: 0.5s;
}

.social li a:hover {
  transform: scale(0.5) translateY(-15px);
}

.menu {
  position: absolute;
  top: 0;
  right: 0;
  width: 300px;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.menu ul {
  position: relative;
}

.menu ul li {
  list-style: none;
}

.menu ul li a {
  text-decoration: none;
  font-size: 24px;
  color: #111;
}

.menu ul li a:hover {
  color: #03a9f4;
}

@media (max-width: 991px) {
  .showcase,
  .showcase header {
    padding: 40px;
  }

  .text h2 {
    font-size: 3em;
  }

  .text h3 {
    font-size: 2em;
  }
}

</style>
