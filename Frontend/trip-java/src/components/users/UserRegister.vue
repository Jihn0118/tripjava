<script setup>
import { ref, reactive } from "vue";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";
import { useMenuStore } from "@/stores/menu";

const router = useRouter();

const memberStore = useMemberStore();

const { isChecked } = storeToRefs(memberStore);
const { userRegist, checkId } = memberStore;

const registUser = ref({
  id: "",
  password: "",
  userName: "",
  birthday: "",
  gender: "M",
  image: "",
});

const checkIdAnswer = ref("아이디 중복 검사를 해주세요");

const checkColor = ref("");

const regist = async () => {
  await userRegist(registUser.value);
  router.push("/");
};

const checkUserId = async () => {
  if (registUser.value.id == "") {
    checkIdAnswer.value = "아이디를 입력하세요";
    return;
  }
  await checkId(registUser.value.id);
  if (isChecked.value === true) {
    id_input.disabled = true;
    btn_checkId.disabled = true;
    checkIdAnswer.value = "사용 가능한 아이디입니다.";
    checkColor.value = "check-color-blue";
  } else {
    checkIdAnswer.value = "이미 존재하는 아이디입니다.";
    checkColor.value = "check-color-red";
  }
};
</script>

<template>
  <a-form layout="horizontal" style="max-width: 500px">
    <a-form-item label="아이디">
      <a-input style="max-width: 300px" v-model:value="registUser.id" id="id_input" />
      <a-button style="margin-left: 5px" @click="checkUserId" id="btn_checkId"
        >아이디 중복확인</a-button
      >
      <div v-text:value="checkIdAnswer" :class="checkColor"></div>
    </a-form-item>
    <a-form-item label="비밀번호">
      <a-input-password v-model:value="registUser.password" />
    </a-form-item>
    <a-form-item label="비밀번호 확인">
      <a-input-password />
    </a-form-item>
    <a-form-item label="닉네임">
      <a-input v-model:value="registUser.userName" />
    </a-form-item>
    <a-form-item label="생년월일" name="birthday">
      <a-date-picker v-model:value="registUser.birthday" format="YYYY-MM-DD" />
    </a-form-item>
    <a-form-item label="성별">
      <a-radio-group v-model:value="registUser.gender">
        <a-radio value="M">남성</a-radio>
        <a-radio value="W">여성</a-radio>
      </a-radio-group>
    </a-form-item>
    <a-form-item label="프로필 이미지">
      <a-upload action="/upload.do" list-type="picture-card">
        <div>
          <div style="margin-top: 8px">Upload</div>
        </div>
      </a-upload>
    </a-form-item>
    <a-button @click="regist">회원가입</a-button>
  </a-form>
</template>

<style scoped>
.avatar-uploader > .ant-upload {
  width: 64px;
  height: 64px;
}
.ant-upload-select-picture-card i {
  font-size: 32px;
  color: #999;
}

.ant-upload-select-picture-card .ant-upload-text {
  margin-top: 8px;
  color: #666;
}

.check-color-red {
  color: red;
}

.check-color-blue {
  color: blue;
}
</style>
