<template>
  <div>
    <sidebar-nav></sidebar-nav>
    <user-my-page></user-my-page>
  </div>
</template>

<script>
import SidebarNav from "@/components/SidebarNav.vue";
import UserMyPage from "@/components/User/UserMyPage.vue";

export default {
  name: "MyPageView",
  components: { SidebarNav, UserMyPage },
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
