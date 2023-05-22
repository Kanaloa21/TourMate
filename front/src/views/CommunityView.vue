<template>
  <div>
    <sidebar-nav></sidebar-nav>
    <h1>This is an CommunityView page</h1>
    <plan-list></plan-list>
  </div>
</template>

<script>
import SidebarNav from "@/components/SidebarNav.vue";
import PlanList from "@/components/Community/PlanList.vue";

export default {
  name: "CommunityView",
  components: { SidebarNav, PlanList },
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
