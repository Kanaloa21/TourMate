<template>
  <div>
    <sidebar-nav></sidebar-nav>
    <router-view></router-view>
  </div>
</template>

<script>
import SidebarNav from "@/components/SidebarNav.vue";

export default {
  name: "CommunityView",
  components: { SidebarNav },
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped></style>
