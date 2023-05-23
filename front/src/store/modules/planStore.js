import http from "@/api/index.js";

const planStore = {
  namespaced: true,
  state: {
    wishList: [],
    planList: [],
  },
  getters: {},
  mutations: {
    ADD_WISH_LIST(state, data) {
      let isAlreadyExist = state.wishList.some(({ contentId }) => {
        return contentId == data.contentId;
      });
      if (isAlreadyExist) console.log("This attration already exists");
      else state.wishList.push(data);
    },
    CLEAR_WISH_LIST(state) {
      state.wishList = [];
    },
    DELETE_WISH(state, index) {
      // state.planList.splice(index, 1);
      state.wishList = state.wishList.filter(({ contentId }) => contentId != index);
    },
    GET_PLAN_LIST(state, data) {
      state.planList = data;
    },
  },
  actions: {
    getWishList({ commit }, payload) {
      let uri = "/plan?sortType=" + payload.sortType;

      console.log(uri);
      http.get(uri).then(({ data }) => {
        console.log("data", data);
        commit("GET_PLAN_LIST", data);
      });
    },
  },
};

export default planStore;
