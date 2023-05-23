<template>
  <div>
    <sidebar-nav></sidebar-nav>
    <!-- <h1>This is an PlansView page</h1> -->
    <div class="position-relative bb">
      <plan-map class="position-absolute anime-map" />
      <wish-list class="position-absolute side-list" style="z-index: 2" />
    </div>
  </div>
</template>

<script>
import SidebarNav from "@/components/SidebarNav.vue";
import WishList from "@/components/Plans/WishList.vue";
import PlanMap from "@/components/Plans/PlanMap.vue";

export default {
  name: "PlansView",
  components: { SidebarNav, WishList, PlanMap },
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
</style>
