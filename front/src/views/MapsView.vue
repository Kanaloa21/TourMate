<template>
  <div>
    <sidebar-nav />
    <div class="position-relative bb">
      <background-map class="position-absolute anime-map" />
      <tour-list class="position-absolute side-list" style="z-index: 2" />
      <tour-detail class="position-absolute side-list-reverse" style="z-index: 3"></tour-detail>
    </div>
  </div>
</template>

<script>
import SidebarNav from "@/components/SidebarNav.vue";
import BackgroundMap from "@/components/Maps/BackgroundMap.vue";
import TourList from "@/components/Maps/TourList.vue";
import TourDetail from "@/components/Maps/TourDetail.vue";

export default {
  name: "MapsView",
  components: { BackgroundMap, SidebarNav, TourList, TourDetail },
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
h1 {
  margin: 0px;
}

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
    transform: translateX(0%);
    opacity: 1;
  }
}

.side-list-reverse {
  animation: list-load-reverse var(--list-load-time) cubic-bezier(0.13, 0.63, 0.62, 1.37);
}

@keyframes list-load-reverse {
  0% {
    transform: translateX(150%);
    opacity: 0;
  }
  100% {
    transform: translateX(0%);
    opacity: 1;
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
