import { ref } from "vue";
import { useRouter } from "vue-router";
import { defineStore } from "pinia";
import { jwtDecode } from "jwt-decode";

import {
  userConfirm,
  findById,
  tokenRegeneration,
  logout,
  regist,
  duplicateIdCheck,
  withdraw,
  modifyMe,
} from "@/api/user";
import { httpStatusCode } from "@/util/http-status";

export const useMemberStore = defineStore(
  "memberStore",
  () => {
    const router = useRouter();

    const isLogin = ref(false);
    const isLoginError = ref(false);
    const userInfo = ref(null);
    const isValidToken = ref(false);
    const isChecked = ref(false);

    const userLogin = async (loginUser) => {
      await userConfirm(
        loginUser,
        (response) => {
          if (response.status === httpStatusCode.CREATE) {
            let { data } = response;
            let accessToken = data["access-token"];
            let refreshToken = data["refresh-token"];
            isLogin.value = true;
            isLoginError.value = false;
            isValidToken.value = true;
            userInfo.value = loginUser.value;
            sessionStorage.setItem("accessToken", accessToken);
            sessionStorage.setItem("refreshToken", refreshToken);
          } else {
            isLogin.value = false;
            isLoginError.value = true;
            isValidToken.value = false;
          }
        },
        (error) => {
          console.error(error);
          isLogin.value = false;
          isLoginError.value = true;
          isValidToken.value = false;
          console.log("아이디와 비밀번호 틀림!!!");
          window.alert("아이디와 비밀번호 틀림!!!");
        }
      );
    };

    const getUserInfo = (token) => {
      let decodeToken = jwtDecode(token);
      findById(
        decodeToken.memberId,
        (response) => {
          if (response.status === httpStatusCode.OK) {
            userInfo.value = response.data.userInfo;
            console.log(response.data.userInfo);
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        async (error) => {
          console.error(
            "getUserInfo() error code [토큰 만료되어 사용 불가능.] ::: ",
            error.response.status
          );
          isValidToken.value = false;

          await tokenRegenerate();
        }
      );
    };

    const tokenRegenerate = async () => {
      await tokenRegeneration(
        JSON.stringify(userInfo.value),
        (response) => {
          if (response.status === httpStatusCode.CREATE) {
            let accessToken = response.data["access-token"];
            sessionStorage.setItem("accessToken", accessToken);
            isValidToken.value = true;
          }
        },
        async (error) => {
          // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
          if (error.response.status === httpStatusCode.UNAUTHORIZED) {
            // 다시 로그인 전 DB에 저장된 RefreshToken 제거.
            await logout(
              userInfo.value.userid,
              (response) => {
                if (response.status === httpStatusCode.OK) {
                  console.log("리프레시 토큰 제거 성공");
                } else {
                  console.log("리프레시 토큰 제거 실패");
                }
                alert("RefreshToken 기간 만료!!! 다시 로그인해 주세요.");
                isLogin.value = false;
                userInfo.value = null;
                isValidToken.value = false;
                router.push({ name: "user-login" });
              },
              (error) => {
                console.error(error);
                isLogin.value = false;
                userInfo.value = null;
              }
            );
          }
        }
      );
    };

    const userLogout = async (userid) => {
      console.log("userLogout 호출!");
      await logout(
        userid,
        (response) => {
          if (response.status === httpStatusCode.OK) {
            isLogin.value = false;
            userInfo.value = null;
            isValidToken.value = false;
          } else {
            console.error("유저 정보 없음!!!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    };

    const userRegist = async (registUser) => {
      await regist(
        registUser,
        (response) => {
          if (response.status === httpStatusCode.OK) {
            window.alert("회원가입이 완료되었습니다.");
            router.push({ name: "user-login" });
          }
        },
        (error) => {
          console.log(error);
        }
      );
    };

    const checkId = async (id) => {
      await duplicateIdCheck(
        id,
        (response) => {
          if (response.status === httpStatusCode.OK) {
            let { data } = response;
            let result = data["result"];
            console.log("result: " + result + " ");
            if (result == 0) {
              isChecked.value = true;
            }
          }
        },
        (error) => {
          console.log(error);
        }
      );
    };

    const deleteUser = async (userid) => {
      await withdraw(
        userid,
        (response) => {
          if (response.status === httpStatusCode.OK) {
            userLogout(userid);
            console.log("회원탈퇴 완료");
            router.push({ name: "home" });
          }
        },
        (error) => {
          console.log(error);
          console.log("회원탈퇴 실패");
        }
      );
    };

    const modifyUser = async (modifyingUser) => {
      await modifyMe(
        modifyingUser,
        (response) => {
          if (response.status === httpStatusCode.OK) {
            console.log("회원정보 수정 완료");
            router.push("/user/mypage");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    };

    return {
      isLogin,
      isLoginError,
      userInfo,
      isValidToken,
      isChecked,
      userLogin,
      getUserInfo,
      tokenRegenerate,
      userLogout,
      userRegist,
      checkId,
      deleteUser,
      modifyUser,
    };
  },
  { persist: true }
);
