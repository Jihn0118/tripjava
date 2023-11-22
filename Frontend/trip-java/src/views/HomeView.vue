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

.text a {
  display: inline-block;
  font-size: 1em;
  background: lightblue;
  padding: 10px 30px;
  text-transform: uppercase;
  text-decoration: none;
  font-weight: 500;
  margin-top: 10px;
  color: #111;
  letter-spacing: 2px;
  transition: 0.2s;
}

.text a:hover {
  letter-spacing: 5px;
}

#plane {
  position: relative;
  animation: move 5s;
  width: 800px;
  animation-direction: alternate;
}

@keyframes move {
  from {
    top: 70px;
    left: -400px;
  }
  to {
    top: 0px;
    left: 0px;
  }
}

.text {
  position: relative;
  z-index: 10;
}

.text h2 {
  font-size: 5em;
  font-weight: 800;
  color: lightblue;
  line-height: 1em;
  text-transform: uppercase;
}

.text h3 {
  font-size: 4em;
  font-weight: 700;
  color: lightblue;
  line-height: 1em;
  text-transform: uppercase;
}

.text p {
  font-size: 1.1em;
  color: lightblue;
  margin: 20px 0;
  font-weight: 400;
  max-width: 700px;
}

</style>
