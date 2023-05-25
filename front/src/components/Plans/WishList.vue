<template>
  <div class="px-4 list bb scroll" style="overflow-y: scroll">
    <!-- <div
      id="ww_7bcd871882a83"
      v="1.3"
      loc="id"
      a='{"t":"ticker","lang":"ko","sl_lpl":1,"ids":[],"font":"Arial","sl_ics":"one_a","sl_sot":"celsius","cl_bkg":"image","cl_font":"#FFFFFF","cl_cloud":"#FFFFFF","cl_persp":"#81D4FA","cl_sun":"#FFC107","cl_moon":"#FFC107","cl_thund":"#FF5722"}'
    >
      Weather Data Source:
      <a href="https://wetterlabs.de/wetter_suwon/woche/" id="ww_7bcd871882a83_u" target="_blank"
        >das wetter Suwon 7 tage</a
      >
    </div> -->
    <div>
      <b-row>
        <b-col></b-col>
        <b-col cols="6"
          ><h1 class="mt-5" style="color: white; font-family: 'GangwonEduPowerExtraBoldA'">
            나의 플랜
          </h1></b-col
        >
        <b-col cols="4">
          <b-button href="#" variant="danger" class="mx-2 mt-5" @click="clearPlan()">
            비우기
          </b-button></b-col
        >
      </b-row>
    </div>
    <hr />
    <div>
      <draggable
        :list="wishList"
        :disabled="!enabled"
        class="list-group"
        ghost-class="ghost"
        :move="checkMove"
        @start="dragging = true"
        @end="dragging = false"
      >
        <div
          class="list-group-item"
          v-for="element in wishList"
          :key="element.contentId"
          style="color: black"
        >
          {{ element.title }}
          <b-button href="#" variant="warning" class="mx-1" @click="deletePlan(element.contentId)">
            삭제
          </b-button>
        </div>
      </draggable>
    </div>
    <!-- <rawDisplayer class="col-3" :value="top10Attractions" title="List" /> -->
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import draggable from "vuedraggable";

const planStore = "planStore";

export default {
  name: "WishList",
  components: { draggable },
  data() {
    return {
      enabled: true,
      dragging: false,
    };
  },
  created() {},
  mounted() {
    // const script = document.createElement("script");
    // script.setAttribute("src", "https://app1.weatherwidget.org/js/?id=ww_7bcd871882a83");
    // document.head.appendChild(script);
  },
  methods: {
    checkMove: function (e) {
      window.console.log("Future index: " + e.draggedContext.futureIndex);
    },

    clearPlan() {
      this.CLEAR_WISH_LIST();
    },

    deletePlan(index) {
      this.DELETE_WISH(index);
    },

    ...mapMutations(planStore, ["ADD_WISH_LIST", "CLEAR_WISH_LIST", "DELETE_WISH"]),
  },

  computed: {
    draggingInfo() {
      return this.dragging ? "under drag" : "";
    },
    ...mapState(planStore, ["wishList"]),
  },
};
</script>

<style scoped>
hr {
  height: 3px;
  background-color: rgb(200, 200, 200);
  border-width: 2px;
  border-radius: 15px;
}

.list {
  /* position: fixed; */
  top: 0vh;
  bottom: 0;
  left: 80px;
  padding: 0px 0 0;
  box-shadow: 0 2px 5px 0 rgb(0 0 0 / 25%), 0 2px 10px 0 rgb(0 0 0 / 25%);
  /* 최소 창 크기 500px (크롬기준) */
  width: 420px;
  height: 100vh;
  z-index: 500;
  background-color: rgb(133, 133, 132);
}

.button {
  border-width: 2px;
  border-radius: 15px;
  transition: background-color 0.5s ease-out 10ms;
  background-color: #42b983;
}

.dropdown {
  width: 160px;
  border-width: 2px;
  border-radius: 15px;
  transition: background-color 0.5s ease-out 10ms;
  background-color: #42b983;
}

.list-item {
  font-family: Jeju Gothic;
}

.sidebar-toggle {
  top: 20vh;
  left: 420px;
  padding: 100px 0 0;
  box-shadow: 0 2px 5px 0 rgb(0 0 0 / 5%), 0 2px 10px 0 rgb(0 0 0 / 5%);
  width: 30px;
  height: 70px;
  padding: 0px;
  background-color: rgb(59, 59, 59);
}

.bb {
  --list-load-time: 300ms;
}

.side-list {
  animation: list-load var(--list-load-time) ease-in;
}

@keyframes list-load {
  0% {
    transform: translateX(-100%);
  }

  100% {
    transform: translateX(0%);
  }
}

.scroll::-webkit-scrollbar {
  width: 12px;
}

.scroll::-webkit-scrollbar-track {
  background: #ddd;
}

.scroll::-webkit-scrollbar-thumb {
  background: #42b983;
}
</style>
