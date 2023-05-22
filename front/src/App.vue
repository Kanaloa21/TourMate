<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: "App",
  components: {},
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif, Cafe24Ohsquare, Jeju Gothic, NanumBarunGothic;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: lightgray;
  padding: 0px;
}

@font-face {
  font-family: "Cafe24Ohsquare";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/Cafe24Ohsquare.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

@import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);

.jejugothic * {
  font-family: "Jeju Gothic", sans-serif;
}

@font-face {
  font-family: "GangwonEduPowerExtraBoldA";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/GangwonEduPowerExtraBoldA.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
</style>
