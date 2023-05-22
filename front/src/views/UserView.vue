<template>
  <div>
    <sidebar-nav></sidebar-nav>
    <router-view />
  </div>
</template>

<script>
import SidebarNav from "@/components/SidebarNav.vue";

export default {
  name: "UserView",
  components: {
    SidebarNav,
  },
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
