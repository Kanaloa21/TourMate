<template>
  <div>
    <b-dropdown
      size="lg"
      variant="link"
      toggle-class="text-decoration-none"
      no-caret
      class="avatar"
      v-if="isLogin"
    >
      <template #button-content>
        <b-icon icon="person-circle" class="h1 mb-0 icon"></b-icon>
      </template>
      <b-dropdown-item href="#" @click="logout">로그아웃</b-dropdown-item>
      <b-dropdown-item href="#" @click="mypage">마이페이지</b-dropdown-item>
    </b-dropdown>

    <b-dropdown
      size="lg"
      variant="link"
      toggle-class="text-decoration-none"
      no-caret
      class="avatar"
      v-if="!isLogin"
    >
      <template #button-content>
        <b-icon icon="person-circle" class="h1 mb-0 icon"></b-icon>
      </template>
      <b-dropdown-item href="#" @click="login">로그인</b-dropdown-item>
      <b-dropdown-item href="#" @click="regist">회원가입</b-dropdown-item>
    </b-dropdown>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  name: "AvatarDropdown",
  components: {},
  data() {
    return {
      message: "",
    };
  },
  computed: { ...mapState("userStore", ["isLogin", "userId", "userName"]) },
  created() {},
  methods: {
    ...mapActions("userStore", ["userLogout"]),
    login() {
      this.$router.push({ name: "login" });
    },
    async logout() {
      await this.userLogout();
      if (!this.isLogin) {
        this.$router.go(this.$router.currentRoute);
      } else {
        alert("로그아웃에 실패하였습니다");
      }
    },
    mypage() {
      this.$router.push({ name: "mypage" });
    },
    regist() {
      this.$router.push({ name: "regist" });
    },
  },
};
</script>

<style scoped>
.avatar {
  position: absolute;
  top: 0;
  right: 0;
  padding: 20px 20px 0 150px;
}
.icon {
  width: 40px;
  height: 40px;
}
</style>
