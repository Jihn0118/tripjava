<script setup>
import {watch} from "vue";
const props = defineProps({item: Object})
console.log(props.item.contendId);
</script>

<template>
  <div class="card-wrapper">
    <router-link :to="{name:'information-detail' , params: { infoId: item.contendId }}">
      <a-card hoverable style="width: 400px; height: 100%; display: flex; flex-direction: column">
        <template #cover class="img_wrapper">
          <img
              alt="example"
              :src=item.image
              style="height: 300px"
          />
        </template>
        <div class="card-content">
          <div class="card-content-wrapper">
            <div class="card-content-left">
              <div class="text-title">{{ item.title }}</div>
            </div>
            <div class="card-content-right">
              <div>마감 2023.11.23</div>
            </div>
          </div>
          <hr/>
          <div class="text-align-left">
            모집 인원 0/3
          </div>

        </div>
      </a-card>
    </router-link>
  </div>
</template>

<style scoped>

</style>