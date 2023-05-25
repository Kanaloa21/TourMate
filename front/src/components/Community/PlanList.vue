<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <h1 class="mt-3" style="color: black; font-family: 'GangwonEduPowerExtraBoldA'">
          <b-icon icon="people"></b-icon> 커뮤니티
        </h1>
      </b-col>
    </b-row>
    <b-row class="mb-1"> </b-row>
    <b-row>
      <b-col>
        <b-table
          hover
          :items="planList"
          :fields="fields"
          @row-clicked="viewPlanDetail"
          head-variant="light"
        >
        </b-table>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapState } from "vuex";

const planStore = "planStore";
const userStore = "userStore";

export default {
  name: "BoardList",
  data() {
    return {
      fields: [
        { key: "planId", label: "글번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "userId", label: "작성자", tdClass: "tdClass" },
        { key: "createTime", label: "작성일", tdClass: "tdDate" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
        { key: "likeCount", label: "좋아요", tdClass: "tdClass" },
      ],
    };
  },
  created() {
    const tmp = { userId: null, sortType: 0 };
    this.getPlanList(tmp);
  },
  mounted() {},
  methods: {
    viewPlanDetail(plan) {
      this.$router.push({
        name: "planView",
        params: { planId: plan.planId },
      });
    },
    ...mapActions(planStore, ["getPlanList"]),
  },
  computed: {
    ...mapState(planStore, ["planList"]),
    ...mapState(userStore, ["userId"]),
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
.tdDate {
  width: 100px;
}
</style>
