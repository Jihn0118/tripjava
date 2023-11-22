<script setup>
</script>

<template>
  <video src="https://traversymedia.com/downloads/videos/explore.mp4" muted loop autoplay></video>
  <div class="overlay"></div>
  <div class="text">
    <h2>Never Stop To </h2>
    <h3>Exploring The World</h3>
    <h4>"TAKE ONLY MEMORIES, LEAVE ONLY FOOTPRINTS." – CHIEF SEATTLE</h4>
    <h4>"THERE IS A WORLD ELSEWHERE." – SHAKESPEARE</h4>
    <h4>"TO TRAVEL IS TO LIVE." – HANS CHRISTIAN ANDERSEN</h4>
    <a href="#">Plan your trip</a>
  </div>
  <ul class="social">
    <li><a href="#"><img src="https://i.ibb.co/x7P24fL/facebook.png"></a></li>
    <li><a href="#"><img src="https://i.ibb.co/Wnxq2Nq/twitter.png"></a></li>
    <li><a href="#"><img src="https://i.ibb.co/ySwtH4B/instagram.png"></a></li>
  </ul>

  <img id="plane" src="src/assets/plane.png">
</template>

<style scoped>

#plane{
  position:relative;
  animation: move 5s;
  width: 800px;
  animation-direction: alternate;
}

@keyframes move {
  0%   {top: 150px; left: -200px;}
  100% {top: 0px; left: 0px;}
}

</style>
