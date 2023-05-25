<template>
  <div>
    <sidebar-nav></sidebar-nav>
    <b-container class="mt-5 userInfo">
      <b-row class="mt-5">
        <b-col> </b-col>
        <b-col cols="12">
          <div>
            <b-card title="Card Title" no-body>
              <b-card-header header-tag="nav">
                <b-nav card-header tabs>
                  <b-nav-item><router-link to="/mypage">내 정보</router-link></b-nav-item>
                  <b-nav-item active
                    ><router-link to="/mybookmarks">내 북마크</router-link></b-nav-item
                  >
                  <b-nav-item><router-link to="/myplans">내 플랜</router-link></b-nav-item>
                </b-nav>
              </b-card-header>
              <b-card-body class="text-center">
                <h2><b-icon icon="bookmark-plus-fill"></b-icon> 내 북마크</h2>
                <hr class="my-1" />
                <b-container class="mt-4">
                  <b-table
                    hover
                    :items="bookmarkList"
                    :fields="fields"
                    @row-clicked="viewPlanDetail"
                    head-variant="light"
                  >
                  </b-table>
                </b-container>
                <hr class="my-4" />
              </b-card-body>
            </b-card>
          </div>
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import SidebarNav from "@/components/SidebarNav.vue";
import { mapState } from "vuex";
import http from "@/api/index";

const userStore = "userStore";

export default {
  name: "MyBookmarks",
  components: { SidebarNav },
  data() {
    return {
      gender: "male",
      fields: [
        { key: "planId", label: "글번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "userId", label: "작성자", tdClass: "tdClass" },
        { key: "createTime", label: "작성일", tdClass: "tdDate" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
      ],
      bookmarkList: [],
    };
  },
  created() {
    let uri = `/plan/bookmarkList?userId=${this.userId}&sortType=${0}`;
    http.get(uri).then(({ data }) => {
      console.log("bookmarkList: " + data);
      this.bookmarkList = data;
    });
  },
  methods: {
    viewPlanDetail(plan) {
      this.$router.push({
        name: "planView",
        params: { planId: plan.planId },
      });
    },
  },
  computed: {
    ...mapState(userStore, ["userId"]),
  },
};
</script>

<style scoped>
.userInfo {
  color: black;
}

.tdDate {
  width: 100px;
}
</style>
