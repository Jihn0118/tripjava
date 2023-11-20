<script setup></script>

<template>
  <div style="background-color: #ececec; padding: 20px">
    <h1>나의 여행 계획</h1>
    <a-row :gutter="16">
      <a-col :span="8">
        <a-card title="서울 여행" :bordered="false">
          <p>23-11-21 ~ 23-11-24</p>
          <p>card content</p>
          <img src="@/assets/zxcogo.png" alt="이미지 없음" />
        </a-card>
      </a-col>
      <a-col :span="8">
        <a-card title="인천 여행" :bordered="false">
          <p>23-11-21 ~ 23-11-24</p>
          <p>card content</p>
          <img src="@/assets/logo.png" alt="이미지 없음" />
        </a-card>
      </a-col>
      <a-col :span="8">
        <a-card title="부산 여행" :bordered="false">
          <p>23-11-21 ~ 23-11-24</p>
          <p>card content</p>
          <img src="@/assets/logo.png" alt="이미지 없음" />
        </a-card>
      </a-col>
    </a-row>
  </div>
</template>

<style scoped></style>
