<template>
  <b-container fluid class="container">
    <b-row></b-row>
    <b-row class="center">
      <b-col cols="6" class="carousel">
        <b-carousel
          id="carousel-1"
          :interval="4000"
          controls
          indicators
          background="#ababab"
          img-width="512"
          img-height="512"
          style="text-shadow: 1px 1px 2px #333"
        >
          <!-- Text slides with image -->
          <b-carousel-slide
            img-src="https://cdn.eyesmag.com/content/uploads/posts/2022/03/21/gyeongbokgung-palace-night-01-1408c7c5-b5f5-4554-b3dc-7f3be6f75acc.jpg"
          ></b-carousel-slide>

          <!-- Slides with custom text -->
          <b-carousel-slide
            img-src="https://cdn.eyesmag.com/content/uploads/posts/2021/04/02/main-8435a3ed-7191-4696-b28d-aebf9cf0124a.jpg"
          >
          </b-carousel-slide>

          <!-- Slides with image only -->
          <b-carousel-slide
            img-src="https://cdn.eyesmag.com/content/uploads/sliderImages/2022/11/23/01-1385c655-5c0a-4bad-81f0-cbc1dc6fb707.jpg"
          ></b-carousel-slide>

          <!-- Slides with img slot -->
          <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
          <b-carousel-slide
            img-src="https://cdn.eyesmag.com/wp-content/uploads/2016/02/2019-seoul-international-fireworks-festival-will-take-place-in-October-01.jpg"
          ></b-carousel-slide>
        </b-carousel>
      </b-col>
      <b-col cols="6">
        <div class="catch">
          <h1><b class="head-word">M</b>ake</h1>
          <h1><b class="head-word">A</b>mazing</h1>
          <h1><b class="head-word">T</b>our</h1>
          <h1><b class="head-word">E</b>xperiences</h1>
        </div>
        <br />
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="3"></b-col>
      <b-col cols="8">
        <div class="sub">
          <p>"<b>TOUR</b><b class="head-word"> MATE</b>"는 대한민국의 숨은 관광지를 발굴하고</p>
          <p>국내 여행 문화와 산업의 발전을 위해</p>
          <p>2023년 5월 서비스를 시작한 여행 플랫폼입니다.</p>
        </div>
      </b-col>
      <b-col cols="1"></b-col>
    </b-row>
    <b-row class="justify-content-center m-5 sub">
      <button class="goto btn-lg">
        <router-link to="/maps" class="link">보러가기</router-link>
      </button>
    </b-row>
    <b-row class="infographic mt-5">
      <b-col
        ><span class="counter-place h2"></span><span class="h2"> 곳의</span>
        <h2>관광지</h2></b-col
      >
      <b-col
        ><span class="counter-people h2"></span><span class="h2"> 명의</span>
        <h2>사용자</h2></b-col
      >
      <b-col
        ><span class="counter-plans h2"></span><span class="h2"> 가지의</span>
        <h2>플랜</h2></b-col
      >
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "TourmateHome",
  components: {},
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped>
.container {
  --list-load-time: 700ms;
}

.center {
  margin: 50px 0 0 50px;
}
.carousel {
  animation: carousel-load var(--list-load-time) ease-in;
}
.catch {
  animation: catch-load var(--list-load-time) ease-in;
}
.sub {
  text-align: left;
  font-size: 20px;
  font-family: "Jeju Gothic";
  animation: sub-load var(--list-load-time) ease-in;
}

@property --num {
  syntax: "<integer>";
  initial-value: 0;
  inherits: false;
}

.counter-place {
  animation: counter-place 1s forwards ease-in-out;
  counter-reset: num var(--num);
  font: 800 40px system-ui;
}
.counter-place::after {
  content: counter(num);
}
@keyframes counter-place {
  100% {
    --num: 36181;
  }
}

.counter-people {
  animation: counter-people 1s forwards ease-in-out;
  counter-reset: num var(--num);
  font: 800 40px system-ui;
}
.counter-people::after {
  content: counter(num);
}
@keyframes counter-people {
  100% {
    --num: 10233;
  }
}

.counter-plans {
  animation: counter-plans 1s forwards ease-in-out;
  counter-reset: num var(--num);
  font: 800 40px system-ui;
}
.counter-plans::after {
  content: counter(num);
}
@keyframes counter-plans {
  100% {
    --num: 5942;
  }
}

@keyframes carousel-load {
  0% {
    /* transform: translateX(-100%); */
    opacity: 0;
  }
  100% {
    /* transform: translateX(0%); */
    opacity: 1;
  }
}

@keyframes catch-load {
  0% {
    transform: translateY(-10%);
    opacity: 0;
  }
  100% {
    transform: translateY(0%);
    opacity: 1;
  }
}

@keyframes sub-load {
  0% {
    transform: translateY(+10%);
    opacity: 0;
  }
  100% {
    transform: translateY(0%);
    opacity: 1;
  }
}
h1 {
  text-align: left;
  font-size: 69px;
  font-family: "GangwonEduPowerExtraBoldA";
}
h2 {
  text-align: center;
  font-size: 45px;
  font-family: "GangwonEduPowerExtraBoldA";
}
.h2 {
  text-align: center;
  font-size: 45px;
  font-family: "GangwonEduPowerExtraBoldA";
}
.head-word {
  color: #42b983;
}

.goto {
  width: 200px;
  height: 70px;
  background-color: #42b983;
  color: white;
  font-family: "Jeju Gothic";
  font-size: 20px;
  border-radius: 35px;
  border-width: 0;
  transition: background-color 0.5s ease-out 10ms, font-size 0.5s ease-out 10ms,
    border-radius 0.3s ease-out 10ms;
}

.goto:hover {
  color: #42b983;
  background-color: white;
  font-size: 28px;
  border-radius: 0;
  border-width: 0;
}

.link {
  color: white;
  transition: background-color 0.5s ease-out 10ms, font-size 0.5s ease-out 10ms,
    border-radius 0.3s ease-out 10ms;
}

.link:hover {
  color: #42b983;
  font-size: 28px;
  border-radius: 0;
  border-width: 0;
}
</style>
