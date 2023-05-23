<template>
  <div>
    <sidebar-nav></sidebar-nav>
    <avatar-dropdown></avatar-dropdown>
    <div>
      <router-view />
    </div>
  </div>
</template>

<script>
import SidebarNav from "@/components/SidebarNav.vue";
import AvatarDropdown from "@/components/User/AvatarDropdown.vue";

export default {
  name: "NoticeView",
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
