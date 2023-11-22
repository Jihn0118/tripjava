<script setup>

import {onMounted, ref, watch} from "vue";
import {useRoute, useRouter} from "vue-router";
import {infoDetail} from "@/api/attractionInfo";
import {getHeartStatement, doHeart, cancelHeart, getTotalHeartCount} from "@/api/heart";
import {useMemberStore} from "@/stores/member";
import {storeToRefs} from "pinia";
import {listComment, enrollComment, deleteCommentById} from "@/api/comment";
import dayjs from 'dayjs';
import relativeTime from 'dayjs/plugin/relativeTime';

dayjs.extend(relativeTime);


const memberStore = useMemberStore();
const {userInfo} = storeToRefs(memberStore);
const totalHeartCount = ref(0);
const route = useRoute();
const Information = ref({})
let {infoId} = route.params;
let map;

// 댓글
const comments = ref([]);
const content = ref({
  contentId: "",
  commentContent: "",
  memberId: ""
});


function submitComment() {
  if (content.value) {
    enrollComment(
        content.value,
        () => {
          getListComment()
        },
        (error) => {
          console.error(error);
        }
    )
    content.value.commentContent = '';
  }
}

const getListComment = () => {
  listComment(
      infoId,
      ({data}) => {
        comments.value = data;
        console.log(data);
      },
      (error) => {
        console.error(error);
      }
  )
}

const deleteComment = (commentId) => {
  deleteCommentById(
      commentId,
      () => {
        getListComment();
      },
      (error) => {
        console.error(error);
      }
  )
}

// data
// private int commentId;
// private int contentId;
// private String commentContent;
// private String memberId;
// private Date createDate


infoDetail(
    infoId,
    ({data}) => {
      Information.value = data;
      console.log(Information.value)
    },
    (error) => {
      console.log(error);
    }
);

const memberIdAndContentId = ref({
  "memberId": '',
  "contentId": 0
})


const isLiked = ref(false);
const loveClick = () => {
  if (isLiked.value) {
    cancelHeart(
        memberIdAndContentId.value,
        () => {
          getHeartState();
          getHeartCount();
        },
        (error) => {
          console.error(error);
        }
    )
  } else {
    doHeart(
        memberIdAndContentId.value,
        () => {
          getHeartState();
          getHeartCount();
        },
        (error) => {
          console.error(error);
        }
    )
  }
  heartController.value.color = isLiked.value ? '#f10606' : '';
}

watch(isLiked, () => {
  heartController.value.color = isLiked.value ? '#ff0000' : '';
})

const heartController = ref({
  color: ''
});

const getHeartState = () => {
  getHeartStatement(
      memberIdAndContentId.value,
      ({data}) => {
        isLiked.value = data;
      },
      (error) => {
        console.log(error)
      }
  )
}

const getHeartCount = () => {
  getTotalHeartCount(
      infoId,
      ({data}) => {
        totalHeartCount.value = data;
      },
      (error) => {
        console.error(error);
      }
  )
}


onMounted(() => {
  // 현재 사용자의 아이디와 관광지 정보 넣어주기 => 좋아요
  memberIdAndContentId.value.memberId = userInfo.value.memberId;
  memberIdAndContentId.value.contentId = infoId;

  // 현재 사용자의 아이디와 관광지 정보 넣어주기 => 댓글
  content.value.contentId = infoId;
  content.value.memberId = userInfo.value.memberId;

  // 좋아요 상태 가져오기
  getHeartState();
  getHeartCount();
  getListComment();
  // 카카오 지도
  const script = document.createElement("script");
  script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${import.meta.env.VITE_KAKAO_MAP_SERVICE_KEY}&libraries=services,clusterer`;
  /* global kakao */
  script.onload = () => kakao.maps.load(() => initMap());
  document.head.appendChild(script);
});

const initMap = () => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(Information.value.latitude, Information.value.longitude),
    level: 3,
  };

  map = new kakao.maps.Map(container, options);
  loadMarker()
  console.log(map);
};

const loadMarker = () => {
  var markPosition = new kakao.maps.LatLng(Information.value.latitude, Information.value.longitude);
  var marker = new kakao.maps.Marker({
    position: markPosition
  });
  marker.setMap(map);
}

</script>

<template>

  <div style="margin-top: 100px; text-align: center">
    <h1 style="font-size: 30px; font-weight: bold ">{{ Information.title }}</h1>
    <h4>{{ Information.addr }}</h4>
    <div style="display: flex; align-items: center; justify-content: center; margin-top: 20px">

      <div style="width: 50%; text-align: center">
        <div style="margin-bottom: 30px">
          <a-image
              :width="700"
              :src=Information.image
          />
        </div>

        <hr style="height: 2px; background-color: black">
        <h1 style="margin: 30px">상세정보</h1>
        <p>{{ Information.description }}</p>

        <div class="map-wrap" style="margin-bottom: 20px">
          <div id="map"></div>
        </div>
        <button class="custom-button" @click="loveClick" :class="{ active: isLiked }">
          <font-awesome-icon icon="heart" size="4x" :style="{ color: heartController.color }"/>
        </button>
        <h2>{{ totalHeartCount }}</h2>

        <div style="align-items: center">
          <div style="text-align: left">
            <h1>여행톡</h1>
          </div>

          <form @submit.prevent="submitComment" style="margin-top: 20px; height: 40%">
            <textarea v-model="content.commentContent" placeholder="댓글 내용"
                      style="
              width: 100%;
              height: 100px;
              padding: 10px;
              box-sizing: border-box;
              border: solid 2px;
              border-radius: 5px;
              font-size: 16px;
              resize: both;
                    "
            ></textarea>
            <div style="text-align: right; padding-right: 10px; margin-top: 5px">
              <button type="submit" style="margin-bottom: 5px; font-size: 20px">댓글 작성</button>
            </div>
          </form>
          <a-comment v-for="comment in comments" :key="comment.commentId"
                     style="border-bottom: 0.3px solid black; margin-bottom: 1px; ">
            <template #author>{{ comment.memberId }}</template>
            <template #avatar>
              <a-avatar src="https://joeschmoe.io/api/v1/random" alt="Han Solo"/>
            </template>
            <template #content>
              <div style="display: flex; justify-content: space-between ">
                <p style="text-align: left">
                  {{ comment.commentContent }}
                </p>
                <a-button v-if="comment.memberId == userInfo.memberId" @click="deleteComment(comment.commentId)" type="dashed">지우기</a-button>
              </div>

            </template>
            <template #datetime>
              <a-tooltip :title="dayjs().format('YYYY-MM-DD HH:mm:ss')">
                <span>{{ dayjs().fromNow() }}</span>
              </a-tooltip>
            </template>
          </a-comment>
        </div>
      </div>
    </div>
  </div>


</template>

<style scoped>
.map-wrap {
  display: flex;
  justify-content: center;
  align-items: center;
}

#map {
  width: 100%;
  height: 300px;
}

.custom-button {
  background-color: transparent;
  border: none;
  padding: 0;
  cursor: pointer;
}

.custom-button.active {
  background-color: transparent;
}
</style>