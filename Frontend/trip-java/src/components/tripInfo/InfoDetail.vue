<script setup>

import {onMounted, ref, watch} from "vue";
import {useRoute, useRouter} from "vue-router";
import {infoDetail} from "@/api/attractionInfo";
import {getHeartStatement, doHeart, cancelHeart, getTotalHeartCount} from "@/api/heart";
import {useMemberStore} from "@/stores/member";
import {storeToRefs} from "pinia";
import {listComment, enrollComment, deleteCommentById} from "@/api/comment";


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
  <div>
    <h1>{{ Information.title }}</h1>
    <h2>{{ Information.addr }}</h2>
    <a-image
        :width="700"
        :src=Information.image
    />
    <h1>설명</h1>
    <p>{{ Information.description }}</p>
  </div>
  <div class="map-wrap">
    <div id="map"></div>
  </div>
  <button class="custom-button" @click="loveClick" :class="{ active: isLiked }">
    <font-awesome-icon icon="heart" size="4x" :style="{ color: heartController.color }"/>
  </button>
  <h2>{{ totalHeartCount }}</h2>

  <div>
    <h1>댓글 화면</h1>

    <div id="commentContainer">
      <div v-for="comment in comments" :key="comment.commentId">
        <strong>{{ comment.memberId }}</strong>: {{ comment.commentContent }}
        <button @click="deleteComment(comment.commentId)">지우기</button>
      </div>
    </div>

    <form @submit.prevent="submitComment">
      <textarea v-model="content.commentContent" placeholder="댓글 내용"></textarea>
      <button type="submit">댓글 작성</button>
    </form>
  </div>

</template>

<style scoped>
.map-wrap {
  display: flex;
  justify-content: center;
  align-items: center;
}

#map {
  width: 60%;
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