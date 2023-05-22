// import http from "@/api/index.js";

const planStore = {
  namespaced: true,
  state: {
    planList: [],
  },
  getters: {},
  mutations: {
    ADD_PLAN_LIST(state, data) {
      state.planList.push(data);
    },
    CLEAR_PLAN_LIST(state) {
      state.planList = [];
    },
    DELETE_PLAN(state, index) {
      // state.planList.splice(index, 1);
      state.planList = state.planList.filter(({ contentId }) => contentId != index);
    },
  },
  actions: {},
};

export default planStore;
