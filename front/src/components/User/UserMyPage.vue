<template>
  <b-container class="mt-5 userInfo" v-if="userInfo">
    <b-row class="mt-5">
      <b-col> </b-col>
      <b-col cols="8">
        <div>
          <b-card title="Card Title" no-body>
            <b-card-header header-tag="nav">
              <b-nav card-header tabs>
                <b-nav-item active>
                  <router-link to="/mypage/myinfo"></router-link> 내 정보</b-nav-item
                >
                <b-nav-item>내 북마크</b-nav-item>
                <b-nav-item>내 플랜</b-nav-item>
              </b-nav>
            </b-card-header>
            <b-card-body class="text-center">
              <router-view></router-view>
            </b-card-body>
          </b-card>
        </div>
      </b-col>
      <b-col></b-col>
    </b-row>
    <!-- <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>내정보</h3></b-alert>
      </b-col>
    </b-row> -->
  </b-container>
</template>

<script>
import { mapActions, mapState } from "vuex";
// import MyInfo from "./MyPage/MyInfo.vue";
// import MyBookmarks from "./MyPage/MyBookmarks.vue";
// import MyPlans from "./MyPage/MyPlans.vue";

const userStore = "userStore";

export default {
  name: "UserMyPage",
  components: {},
  data() {
    return {
      gender: "male",
    };
  },
  created() {
    this.getUserInfo();
    if (this.userInfo.gender == 1) {
      this.gender = "male";
    } else {
      this.gender = "female";
    }
  },
  methods: {
    ...mapActions(userStore, ["getUserInfo"]),
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
};
</script>

<style scoped>
.userInfo {
  color: black;
}
</style>
