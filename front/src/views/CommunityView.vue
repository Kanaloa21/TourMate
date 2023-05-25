<template>
  <div>
    <sidebar-nav></sidebar-nav>
    <avatar-dropdown></avatar-dropdown>
    <router-view></router-view>
  </div>
</template>

<script>
import SidebarNav from "@/components/SidebarNav.vue";
import AvatarDropdown from "@/components/User/AvatarDropdown.vue";

export default {
  name: "CommunityView",
  components: { SidebarNav, AvatarDropdown },
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
