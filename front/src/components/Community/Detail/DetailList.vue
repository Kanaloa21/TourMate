<template>
  <div class="px-4 list bb scroll" style="overflow-y: scroll">
    <div>
      <h3 class="mt-3" style="color: white; font-weight: bold">{{ userId }}의 북마크</h3>
    </div>
    <hr />
    <div>
      <div
        class="list-group-item"
        v-for="element in planDetail.planAttractions"
        :key="element.contentId"
        style="color: black"
      >
        {{ element.title }}
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const planStore = "planStore";

export default {
  name: "DetailList",
  components: {},
  props: {
    userId: String,
  },
  data() {
    return {
      enabled: true,
    };
  },
  created() {},
  mounted() {},
  methods: {},
  computed: {
    ...mapState(planStore, ["planDetail"]),
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
  box-shadow: 0 2px 5px 0 rgb(0 0 0 / 5%), 0 2px 10px 0 rgb(0 0 0 / 5%);
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
