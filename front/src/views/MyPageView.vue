<template>
  <div>
    <sidebar-nav></sidebar-nav>
  </div>
</template>

<script>
import SidebarNav from "@/components/SidebarNav.vue";

export default {
  name: "MyPageView",
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
