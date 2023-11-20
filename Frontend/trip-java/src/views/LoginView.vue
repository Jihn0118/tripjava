<script setup></script>

<template>
  <div>
    <h1>로그인</h1>
    <div>
      <a href="http://localhost:8080/oauth2/authorization/kakao">
        <img src="@/assets/kakao_login_large_narrow.png" alt="이미지없음" />
      </a>
    </div>
    <div>
      <a href="http://localhost:8080/oauth2/authorization/naver">
        <img src="@/assets/naver_login_btn.png" alt="이미지없음" />
      </a>
    </div>
    <router-view></router-view>
  </div>
</template>

<style scoped>
div {
  text-align: center;
  margin: 10px;
}
img {
  width: 300px;
  height: 70px;
}
</style>
