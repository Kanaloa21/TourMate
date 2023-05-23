<template>
  <div>
    <h3>Your ID: {{ userId }}</h3>
    <h3>Plan ID: {{ planId }}</h3>
  </div>
</template>

<script>
// import http from "@/api/index";
import { mapState } from "vuex";
import userStore from "@/store/modules/userStore";

export default {
  name: "",
  components: {},
  data() {
    return {
      message: "",
      planId: 0,
    };
  },
  created() {
    this.planId = this.$route.params.planId;
  },
  methods: {},
  computed: {
    ...mapState(userStore, ["userId"]),
  },
};
</script>

<style scoped></style>
