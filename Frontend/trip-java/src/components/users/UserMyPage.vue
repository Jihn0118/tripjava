<script setup>
import { ref } from "vue";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";
import { useMenuStore } from "@/stores/menu";

const memberStore = useMemberStore();

const { userInfo } = storeToRefs(memberStore);

const { deleteUser, modifyUser } = memberStore;

const withdraw = async () => {
  console.log("회원탈퇴 버튼!!!");
  console.log(userInfo.value.memberId);
  await deleteUser(userInfo.value.memberId);
};

const modifyMe = async () => {
  console.log("회원정보수정 버튼!!!");
  await modifyUser(jinho.value);
};
</script>

<template>
  <a-descriptions :title="userInfo.user_name" bordered :column="1">
    <a-descriptions-item label="아이디">{{
      userInfo.memberId
    }}</a-descriptions-item>
    <a-descriptions-item label="생년월일">{{
      userInfo.birthday
    }}</a-descriptions-item>

    <a-descriptions-item label="성별">
      <div v-if="userInfo.gender === 'M'">남성</div>
      <div v-else>여성</div>
    </a-descriptions-item>

    <a-descriptions-item label="등급">
      <a-badge status="processing" :text="userInfo.member_type" />
    </a-descriptions-item>
    <a-descriptions-item label="프로필 이미지"
      ><img src="@/assets/logo.png" alt="프로필 이미지 없음"
    /></a-descriptions-item>
  </a-descriptions>
  <router-link :to="{ name: 'user-plan' }">
    <font-awesome-icon icon="route" style="width: 50px; height: 50px" /> 나의
    여행 계획
  </router-link>
  <a-popconfirm
    title="정보 수정 페이지로 이동하시겠습니까?"
    ok-text="이동"
    cancel-text="취소"
    @confirm="modifyMe"
  >
    <template #icon><question-circle-outlined style="color: red" /></template>
    <a-button type="primary" class="btn">정보 수정</a-button>
  </a-popconfirm>
  <a-popconfirm
    title="정말 회원탈퇴를 하시겠습니까?"
    ok-text="탈퇴"
    cancel-text="취소"
    @confirm="withdraw"
  >
    <template #icon><question-circle-outlined style="color: red" /></template>
    <a-button type="primary" danger class="btn">회원탈퇴</a-button>
  </a-popconfirm>
</template>

<style scoped>
.btn {
  margin: 5px;
}
</style>
