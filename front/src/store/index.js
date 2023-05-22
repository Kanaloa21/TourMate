import Vue from "vue";
import Vuex from "vuex";
// import createPersistedState from "vuex-persistedstate";

import attractionStore from "./modules/attractionStore";
import itemStore from "./modules/itemStore";
import planStore from "./modules/planStore";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: { attractionStore, itemStore, planStore },
  // plugins: [
  //   createPersistedState({
  //     // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
  //     storage: sessionStorage,
  //   }),
  // ],
});
