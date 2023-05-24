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
    paging: 1,
    showDetail: false,
  },
  getters: {
    top10Attractions(state) {
      return state.filteredAttractionList;
    },
    showDetail(state) {
      return state.showDetail;
    },
  },
  mutations: {
    CLEAR_ATTRACTION_LIST(state) {
      state.attractionList = null;
    },
    SEARCH_ATTRACTION_LIST(state, data) {
      state.attractionList = [...data];
    },
    SET_FILTERED_ATTRACTION_LIST(state) {
      console.log(state.selected);
      const temp = state.attractionList.filter((data) => state.selected.includes(data.contentType));

      const showListNum = state.paging * 10;
      const maxListNum = Math.min(showListNum, temp.length);

      state.filteredAttractionList = temp.slice(0, maxListNum);
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
    SET_PAGING(state, data) {
      state.paging = data;
    },

    SET_SHOW_DETAIL(state, data) {
      state.showDetail = data;
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
        commit("SET_FILTERED_ATTRACTION_LIST");
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
