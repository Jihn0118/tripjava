<script setup>

import {ref, watch} from "vue";
import {useRoute, useRouter} from "vue-router";
import {infoDetail} from "@/api/attractionInfo";


const route = useRoute();
const Information = ref({
  "contendId": "",
  "title": "",
  "image": "",
  "latitude": 0,
  "longitude": 0,
  "love": 0
})
console.log("1234123412341234213")
let {infoId} = route.params;
console.log(infoId);
infoDetail(
    infoId,
    ({data}) => {
      Information.value = data;
      console.log(Information.value)
    },
    (error) => {
      console.log(error);
    }
)

</script>

<template>
  <div>
    <h1>{{Information.title}}</h1>
    <h2>{{Information.addr}}</h2>
    <a-image
        :width="700"
        :src= Information.image
    />
    <h1>설명</h1>
    <p>{{Information.description}}</p>
  </div>
</template>

<style scoped>
</style>