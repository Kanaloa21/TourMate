<template>
  <div class="home">
    <sidebar-nav></sidebar-nav>
    <div class="bb main-home">
      <avatar-dropdown></avatar-dropdown>
      <tourmate-home />
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from "@/components/HelloWorld.vue";
import TourmateHome from "@/components/Home/TourmateHome.vue";
import SidebarNav from "@/components/SidebarNav.vue";
import AvatarDropdown from "@/components/User/AvatarDropdown.vue";

export default {
  name: "HomeView",
  components: {
    // HelloWorld,
    SidebarNav,
    TourmateHome,
    AvatarDropdown,
  },
};
</script>

<style scoped>
.home {
  background-color: rgb(43, 43, 43);
  width: 100vw;
  height: 100vh;
}
</style>
