<script setup>
import {ref} from 'vue';

const selectedKeys = ref(null);
</script>

<template>
  <div style="{padding: 20px;}">
    <a-anchor
        direction="horizontal"
        :items="[
        {
          key: 'horizontally-part-1',
          href: '#horizontally-part-1',
          title: 'Part 1',
        },
        {
          key: 'horizontally-part-2',
          href: '#horizontally-part-2',
          title: 'Part 2',
        },
        {
          key: 'horizontally-part-3',
          href: '#horizontally-part-3',
          title: 'Part 3',
        },
        {
          key: 'horizontally-part-4',
          href: '#horizontally-part-4',
          title: 'Part 4',
        },
        {
          key: 'horizontally-part-5',
          href: '#horizontally-part-5',
          title: 'Part 5',
        },
        {
          key: 'horizontally-part-6',
          href: '#horizontally-part-6',
          title: 'Part 6',
        },
      ]"
    />
  </div>
</template>

<style scoped>
.ant-anchor-link{
  width: 10px;
}
.ant-affix, .css-dev-only-do-not-override-1qb1s0s{

}
</style>
<style>
.ant-anchor-wrapper{
  height: 30px;
}
.ant-anchor-wrapper::before{
  border-bottom: none;
}
.ant-anchor-link{
  height: 100%;
  font-size: 20px;
}
</style>

