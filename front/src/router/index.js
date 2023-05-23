import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import MapsView from "../views/MapsView.vue";
import MyPageView from "../views/MyPageView.vue";
import NoticeView from "../views/NoticeView.vue";
import PlansView from "../views/PlansView.vue";
import CommunityView from "../views/CommunityView.vue";
import UserView from "../views/UserView";

Vue.use(VueRouter);

import store from "@/store";

const onlyAuthUser = async (to, from, next) => {
  console.log("로그인 전 토큰 체크");
  const isLogin = store.getters["userStore/checkIsLogin"];

  if (isLogin) {
    console.log("토큰 검증 요청");
    await store.dispatch("userStore/checkUserAuth", store.state.accessToken);
  }
  console.log("토큰 검증 완료");

  const isValidToken = store.getters["userStore/checkIsValidToken"];

  if (!isLogin || !isValidToken) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "login" });
    router.push({ name: "login" });
  } else {
    console.log("로그인 인증됨");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/Maps",
    name: "maps",
    component: MapsView,
  },
  {
    path: "/mypage",
    name: "mypage",
    component: MyPageView,
  },
  {
    path: "/notice",
    name: "notice",
    component: NoticeView,
  },
  {
    path: "/plans",
    name: "plans",
    component: PlansView,
  },
  {
    path: "/community",
    name: "community",
    component: CommunityView,
    children: [
      {
        path: "view/:planId",
        name: "planview",
        beforeEnter: onlyAuthUser,
        // component: () =>
        //   import(/* webpackChunkName: "community" */ "@/components/community/BoardDetail"),
      },
    ],
  },
  {
    path: "/user",
    name: "user",
    component: UserView,
    children: [
      {
        path: "login",
        name: "login",
        component: () => import(/* webpackChunkName: "login" */ "@/components/User/UserLogin"),
      },
      {
        path: "regist",
        name: "regist",
        component: () => import(/* webpackChunkName: "regist" */ "@/components/User/UserRegister"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
