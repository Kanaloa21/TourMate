import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import MapsView from "../views/MapsView.vue";
import MyPageView from "../views/MyPageView.vue";
import NoticeView from "../views/NoticeView.vue";
import PlansView from "../views/PlansView.vue";
import CommunityView from "../views/CommunityView.vue";

Vue.use(VueRouter);

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
        // beforeEnter: onlyAuthUser,
        // component: () =>
        //   import(/* webpackChunkName: "community" */ "@/components/community/BoardDetail"),
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
