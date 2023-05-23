import http from "@/api/index.js";

const attractionStore = {
  namespaced: true,
  state: {
    attractionList: [],
    mapCenterPos: { lat: 36.355, lng: 127.2991 },
    mapFocusAttractionInfo: null,
    attractionNodeList: [],
    filteredAttractionList: [],
    selected: [12, 14, 15, 25, 28, 32, 38, 39],
  },
  getters: {
    top10Attractions(state) {
      return state.filteredAttractionList;
    },
  },
  mutations: {
    CLEAR_ATTRACTION_LIST(state) {
      state.attractionList = null;
    },
    SEARCH_ATTRACTION_LIST(state, data) {
      state.attractionList = [...data];
      state.filteredAttractionList = [...data];
    },
    SET_FILTERED_ATTRACTION_LIST(state) {
      console.log(state.selected);
      state.filteredAttractionList = state.attractionList.filter((data) =>
        state.selected.includes(data.contentType)
      );
    },
    SET_SELECTED(state, data) {
      state.selected = data;
    },
    SET_MAP_CENTER_POS(state, data) {
      state.mapCenterPos = data;
    },
    SET_MAP_FOCUS_ATTRACTION_INFO(state, data) {
      state.mapFocusAttractionInfo = data;
    },
  },
  actions: {
    searchAttractionList({ commit }, payload) {
      let uri = "/tour/list?sidoCode=" + payload.sidoCode + "&gugunCode=" + payload.gugunCode;
      if (payload.keyword != null) {
        uri = uri + "&keyword=" + payload.keyword;
      }
      if (payload.userId != null) {
        uri = uri + "&userId=" + payload.userId;
      }

      console.log(uri);
      http.get(uri).then(({ data }) => {
        console.log("data", data);
        commit("SEARCH_ATTRACTION_LIST", data);
      });
    },

    async updateLiked({ commit }, payload) {
      console.log("좋아요 갱신 데이터", payload);
      await http
        .put(`/tour/like`, payload)
        .then(({ data }) => {
          if (data === "success") {
            console.log("좋아요 갱신 성공!");
          } else {
            console.log(commit);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};

export default attractionStore;
