<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글목록</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1"> </b-row>
    <b-row>
      <b-col>
        <b-table striped hover :items="planList" :fields="fields" @row-clicked="viewPlanDetail">
        </b-table>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapState } from "vuex";

const planStore = "planStore";

export default {
  name: "BoardList",
  data() {
    return {
      fields: [
        { key: "planId", label: "글번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "userId", label: "작성자", tdClass: "tdClass" },
        { key: "createTime", label: "작성일", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
      ],
    };
  },
  created() {},
  mounted() {
    const tmp = { sortType: 0 };
    this.getWishList(tmp);
  },
  methods: {
    viewPlanDetail(plan) {
      this.$router.push({
        name: "planView",
        params: { planId: plan.planId },
      });
    },
    ...mapActions(planStore, ["getWishList"]),
  },
  computed: {
    ...mapState(planStore, ["planList"]),
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: center;
}
</style>
