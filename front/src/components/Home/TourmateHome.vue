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
            caption="First slide"
            text="Nulla vitae elit libero, a pharetra augue mollis interdum."
            img-src="https://picsum.photos/512/512/?image=52"
          ></b-carousel-slide>

          <!-- Slides with custom text -->
          <b-carousel-slide img-src="https://picsum.photos/512/512/?image=54">
            <h1>Hello world!</h1>
          </b-carousel-slide>

          <!-- Slides with image only -->
          <b-carousel-slide img-src="https://picsum.photos/512/512/?image=58"></b-carousel-slide>

          <!-- Slides with img slot -->
          <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
          <b-carousel-slide>
            <template #img>
              <img
                class="d-block img-fluid w-100"
                width="512"
                height="512"
                src="https://picsum.photos/512/512/?image=55"
                alt="image slot"
              />
            </template>
          </b-carousel-slide>

          <!-- Slide with blank fluid image to maintain slide aspect ratio -->
          <b-carousel-slide caption="Blank Image" img-blank img-alt="Blank image">
            <p>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eros felis,
              tincidunt a tincidunt eget, convallis vel est. Ut pellentesque ut lacus vel interdum.
            </p>
          </b-carousel-slide>
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
        <div class="sub">
          <p>"<b>TOUR</b><b class="head-word"> MATE</b>"는 대한민국의 숨은 관광지를 발굴하고</p>
          <p>국내 여행 문화와 산업의 발전을 위해</p>
          <p>2023년 5월 서비스를 시작한 여행 플랫폼입니다.</p>
        </div>
      </b-col>
    </b-row>
    <b-row class="justify-content-center m-5 sub">
      <button class="goto btn-lg">당장 보러가기</button>
    </b-row>
    <b-row class="infographic mt-5">
      <b-col><div class="counter"></div></b-col>
      <b-col><div class="counter"></div></b-col>
      <b-col><div class="counter"></div></b-col>
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
  margin: 120px 0 0 50px;
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
.counter {
  animation: counter 5s forwards ease-in-out;
  counter-reset: num var(--num);
  font: 800 40px system-ui;
}
.counter::after {
  content: counter(num);
}
@keyframes counter {
  100% {
    --num: 36181;
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
</style>
