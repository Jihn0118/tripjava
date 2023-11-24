<script setup>
import { onMounted, ref } from "vue";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";
import { getTravelPlans } from "@/api/plan";
import dayjs from "dayjs";

const memberStore = useMemberStore();
const plans = ref([]);
const { userInfo } = storeToRefs(memberStore);

const { deleteUser, modifyUser } = memberStore;

const withdraw = async () => {
  console.log("회원탈퇴 버튼!!!");
  console.log(userInfo.value.memberId);
  await deleteUser(userInfo.value.memberId);
};

const modifyMe = async () => {
  console.log("회원정보수정 버튼!!!");
  await modifyUser(userInfo.value);
};

const getTravelPlan = () => {
  getTravelPlans(
    userInfo.value.memberId,
    ({ data }) => {
      plans.value = data;
      for (var i = 0; i < plans.value.length; i++) {
        plans.value[i].startDate = dayjs(plans.value[i].startDate).format("YYYY-MM-DD");
        plans.value[i].endDate = dayjs(plans.value[i].endDate).format("YYYY-MM-DD");
      }
    },
    (error) => {
      console.log(error);
    }
  );
};

onMounted(() => {
  getTravelPlan();
});
</script>

<template>
  <div style="width: 100%; display: flex; justify-content: space-around; margin-top: 30px">
    <div style="width: 30%; height: 250px">
      <a-descriptions :title="userInfo.user_name" bordered :column="1">
        <a-descriptions-item label="닉네임" style="text-align: center"
          >{{ userInfo.name }}
        </a-descriptions-item>
        <a-descriptions-item label="아이디" style="text-align: center"
          >{{ userInfo.memberId }}
        </a-descriptions-item>
        <a-descriptions-item label="생년월일" style="text-align: center"
          >{{ userInfo.birthday }}
        </a-descriptions-item>

        <a-descriptions-item label="성별" style="text-align: center">
          <div v-if="userInfo.gender === 'M'">남성</div>
          <div v-else>여성</div>
        </a-descriptions-item>
      </a-descriptions>
      <div style="text-align: right; margin-top: 20px">
        <a-popconfirm
          title="정보 수정 페이지로 이동하시겠습니까?"
          ok-text="이동"
          cancel-text="취소"
          @confirm="modifyMe"
        >
          <template #icon>
            <question-circle-outlined style="color: red" />
          </template>
          <a-button type="primary" class="btn">정보 수정</a-button>
        </a-popconfirm>
        <a-popconfirm
          title="정말 회원탈퇴를 하시겠습니까?"
          ok-text="탈퇴"
          cancel-text="취소"
          @confirm="withdraw"
        >
          <template #icon>
            <question-circle-outlined style="color: red" />
          </template>
          <a-button type="primary" danger class="btn">회원탈퇴</a-button>
        </a-popconfirm>
      </div>
    </div>
    <div style="width: 60%; height: 700px; display: flex; flex-flow: wrap">
      <div style="height: 200px; margin: 30px" v-for="plan in plans" :key="plan.travelId">
        <router-link
          :to="{
            name: 'plan-detail',
            params: {
              travelId: plan.travelId,
            },
            query: {
              travelName: plan.travelName,
              startDate: plan.startDate,
              endDate: plan.endDate,
            },
          }"
        >
          <a-card hoverable style="width: 240px">
            <template #cover>
              <img alt="example" src="../../assets/background/backgroundImage3.jpg" />
            </template>
            <a-card-meta :title="plan.travelName">
              <template #description>
                <div>{{ plan.startDate }} - {{ plan.endDate }}</div>
              </template>
            </a-card-meta>
          </a-card>
        </router-link>
      </div>
    </div>
  </div>
</template>

<style scoped>
.btn {
  margin: 5px;
}
</style>
