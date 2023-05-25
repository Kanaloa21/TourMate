<template>
  <div>
    <sidebar-nav></sidebar-nav>
    <b-container class="mt-5 userInfo" v-if="userInfo">
      <b-row class="mt-5">
        <b-col> </b-col>
        <b-col cols="12">
          <div>
            <b-card title="Card Title" no-body>
              <b-card-header header-tag="nav">
                <b-nav card-header tabs>
                  <b-nav-item active><router-link to="/mypage">내 정보</router-link></b-nav-item>
                  <b-nav-item><router-link to="/mybookmarks">내 북마크</router-link></b-nav-item>
                  <b-nav-item><router-link to="/myplans">내 플랜</router-link></b-nav-item>
                </b-nav>
              </b-card-header>
              <b-card-body class="text-center">
                <b-jumbotron>
                  <template #header>My Page</template>
                  <template #lead> 내 정보 확인페이지입니다. </template>
                  <hr class="my-4" />
                  <b-container class="mt-4">
                    <b-row>
                      <b-col cols="2"></b-col>
                      <b-col cols="2" align-self="end">아이디</b-col
                      ><b-col cols="4" align-self="start">{{ userInfo.userId }}</b-col>
                      <b-col cols="2"></b-col>
                    </b-row>
                    <b-row>
                      <b-col cols="2"></b-col>
                      <b-col cols="2" align-self="end">이름</b-col
                      ><b-col cols="4" align-self="start">{{ userInfo.userName }}</b-col>
                      <b-col cols="2"></b-col>
                    </b-row>
                    <b-row>
                      <b-col cols="2"></b-col>
                      <b-col cols="2" align-self="end">전화번호</b-col
                      ><b-col cols="4" align-self="start">{{ userInfo.phoneNumber }}</b-col>
                      <b-col cols="2"></b-col>
                    </b-row>
                    <b-row>
                      <b-col cols="2"></b-col>
                      <b-col cols="2" align-self="end">성별</b-col
                      ><b-col cols="4" align-self="start">{{ gender }}</b-col>
                      <b-col cols="2"></b-col>
                    </b-row>
                  </b-container>
                  <hr class="my-4" />
                  <b-button variant="primary" href="#" class="mr-1">정보수정</b-button>
                  <b-button variant="danger" href="#">회원탈퇴</b-button>
                </b-jumbotron>
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
import { mapActions, mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "MyInfo",
  components: { SidebarNav },
  data() {
    return {
      gender: "male",
    };
  },
  created() {
    this.getUserInfo();
    if (this.userInfo != null && this.userInfo.gender == 1) {
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
