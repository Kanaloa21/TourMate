<template>
  <div>
    <div class="position-relative bb">
      <!-- <h3 class="position-absolute">Your ID: {{ userId }}</h3>
      <h3 class="position-absolute">Plan ID: {{ planId }}</h3> -->
      <detail-map-vue class="position-absolute anime-map" />
      <detail-list :userId="userId" class="position-absolute side-list" style="z-index: 3" />
      <detail-content class="position-absolute anime-content" style="z-index: 2" />
    </div>
  </div>
</template>

<script>
// import http from "@/api/index";
import { mapState, mapActions } from "vuex";
import DetailMapVue from "./Detail/DetailMap.vue";
import DetailList from "./Detail/DetailList.vue";
import DetailContent from "./Detail/DetailContent.vue";

const planStore = "planStore";
const userStore = "userStore";

export default {
  name: "PlanView",
  components: { DetailMapVue, DetailList, DetailContent },
  data() {
    return {
      message: "asd",
      planId: 0,
    };
  },
  created() {
    this.planId = this.$route.params.planId;
    let params = {
      planId: this.planId,
    };
    if (this.userId != null) {
      params = {
        userId: this.userId,
        planId: this.planId,
      };
    }
    this.getPlanDetail(params);
  },
  methods: {
    ...mapActions(planStore, ["getPlanDetail"]),
  },
  computed: {
    ...mapState(userStore, ["userId"]),
  },
  updated() {},
};
</script>

<style scoped>
.bb {
  --list-load-time: 500ms;
}
.side-list {
  animation: list-load var(--list-load-time) cubic-bezier(0.13, 0.63, 0.62, 1.37);
}

@keyframes list-load {
  0% {
    transform: translateX(-150%);
    opacity: 0;
  }
  100% {
    opacity: 1;
    transform: translateX(0%);
  }
}

.anime-map {
  animation: fadein 2s;
}

@keyframes fadein {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

.anime-content {
  animation: anime-load var(--list-load-time) cubic-bezier(0.13, 0.63, 0.62, 1.37);
}

@keyframes anime-load {
  from {
    opacity: 0;
    transform: translateY(-150%);
  }
  to {
    opacity: 1;
    transform: translateY(0%);
  }
}
</style>
