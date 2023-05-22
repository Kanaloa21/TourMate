import http from "@/api/index.js";

const itemStore = {
  namespaced: true,
  state: {
    sidos: [{ value: 0, text: "선택하세요" }],
    guguns: [{ value: 0, text: "선택하세요" }],
  },
  getters: {},
  mutations: {
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: 0, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: 0, text: "선택하세요" }];
    },
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
  },
  actions: {
    getSido: ({ commit }) => {
      http.get("/tour/sido").then(({ data }) => {
        console.log("data", data);
        commit("SET_SIDO_LIST", data);
      });
    },
    getGugun: ({ commit }, sidoCode) => {
      http.get("/tour/gugun?sidoCode=" + sidoCode).then(({ data }) => {
        console.log("data", data);
        commit("SET_GUGUN_LIST", data);
      });
    },
  },
};

export default itemStore;
