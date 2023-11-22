<script setup>
import {watch} from "vue";

const props = defineProps({item: Object})
console.log(props.item.contendId);
</script>

<template>
  <div class="card-wrapper">
    <router-link :to="{name:'information-detail' , params: { infoId: item.contendId }}" style="background-color: white">
      <a-card hoverable style="width: 400px; height: 100%; display: flex; flex-direction: column">
        <template #cover class="img_wrapper">
          <div class="image-container">
            <img alt="example" :src="item.image" class="image"/>
            <div class="text">
              <div style="margin-right: 10px">
                <font-awesome-icon :icon="['fas', 'heart']" size="lg" />
                {{ item.love }}
              </div>
            </div>

          </div>
        </template>
        <div class="card-content">
          <div class="card-content-wrapper">
            <div class="card-content-left">
              <div class="text-title">{{ item.title }}</div>
            </div>
            <div class="card-content-right">
              <div>{{ item.addr }}</div>
            </div>
          </div>
        </div>
      </a-card>
    </router-link>
  </div>
</template>

<style scoped>

.image-container {
  position: relative;
  height: 300px;
}

.image-container::after {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  opacity: 0;
  transition: opacity 0.3s ease;
}

.image {
  width: 100%;
  height: 100%;
}

.text {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  display: none;
  color: white;
}

.image-container:hover::after {
  opacity: 1;
}

.image-container:hover .text {
  display: flex;
}

</style>