<template>
  <div>
    <!-- Sidebar -->
    <nav id="sidebarMenu" class="d-lg-block sidebar">
      <div class="list-group list-group-flush">
        <router-link
          to="/"
          class="px-0 py-4 list-group-item list-group-item-action ripple"
          aria-current="true"
          ><b-icon icon="house" class="h2"></b-icon><br />홈</router-link
        >
        <router-link to="/maps" class="px-0 py-4 list-group-item list-group-item-action ripple"
          ><b-icon icon="map" class="h2"></b-icon><br />지도</router-link
        >
        <router-link to="/plans" class="px-0 py-4 list-group-item list-group-item-action ripple"
          ><b-icon icon="bookmarks" class="h2"></b-icon><br />플랜</router-link
        >
        <router-link to="/community" class="px-0 py-4 list-group-item list-group-item-action ripple"
          ><b-icon icon="people" class="h2"></b-icon><br />커뮤니티</router-link
        >
        <router-link to="/mypage" class="px-0 py-4 list-group-item list-group-item-action ripple"
          ><b-icon icon="person-circle" class="h2"></b-icon><br />내 정보</router-link
        >
        <router-link
          to="/notice"
          class="px-0 py-4 list-group-item list-group-item-action py-2 ripple"
          ><b-icon icon="megaphone" class="h2"></b-icon><br />공지</router-link
        >
      </div>
    </nav>
    <!-- Sidebar -->
  </div>
</template>

<script>
export default {
  name: "SidebarNav",
  components: {},
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped>
/* Sidebar */
.sidebar {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  padding: 100px 0 0;
  box-shadow: 0 2px 5px 0 rgb(0 0 0 / 5%), 0 2px 10px 0 rgb(0 0 0 / 5%);
  width: 80px;
  z-index: 600;
  padding: 0px;
  background-color: rgb(59, 59, 59);
}

/* 크기가 변할때 유동적인 사이즈  */
/* @media (max-width: 991.98px) {
  .sidebar {
    width: 100%;
  }
} */

.ripple {
  background-color: rgb(59, 59, 59);
  color: white;
  font-family: NanumBarunGothic, sans-serif;
  font-size: smaller;
  transition: background-color 0.5s ease-out 10ms, border-radius 0.3s ease-out 10ms;
}

.ripple:hover {
  color: #42b983;
  border-radius: 5px;
  box-shadow: 0 2px 5px 0 rgb(0 0 0 / 16%), 0 2px 10px 0 rgb(0 0 0 / 12%);
  background-color: white;
  border-radius: 20px;
}

nav a.router-link-exact-active {
  color: #42b983;
  border-radius: 5px;
  box-shadow: 0 2px 5px 0 rgb(0 0 0 / 16%), 0 2px 10px 0 rgb(0 0 0 / 12%);
  background-color: white;
  border-radius: 20px;
  padding: 200px;
}

nav a {
  font-weight: bold;
  color: white;
}
</style>
