<template>
  <div>
    <div class="position-relative bb">
      <!-- <h3 class="position-absolute">Your ID: {{ userId }}</h3>
      <h3 class="position-absolute">Plan ID: {{ planId }}</h3> -->
      <detail-map-vue class="position-absolute anime-map" />
      <detail-list :userId="userId" class="position-absolute side-list" style="z-index: 3" />
      <!-- <plan-write class="position-absolute anime-write" style="z-index: 2" /> -->
    </div>
  </div>
</template>

<script>
// import http from "@/api/index";
import { mapState } from "vuex";
import DetailMapVue from "./Detail/DetailMap.vue";
import DetailList from "./Detail/DetailList.vue";

const userStore = "userStore";

export default {
  name: "PlanView",
  components: { DetailMapVue, DetailList },
  data() {
    return {
      message: "asd",
      planId: 0,
    };
  },
  created() {
    this.planId = this.$route.params.planId;
  },
  methods: {},
  computed: {
    ...mapState(userStore, ["userId"]),
  },
  updated() {},
};
</script>

<style scoped></style>
