<template>
  <div>
    <sidebar-nav></sidebar-nav>
    <h1>This is an MyPageView page</h1>
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
