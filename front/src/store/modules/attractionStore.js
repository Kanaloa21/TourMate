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
    attractionDetail: null,
    attractionComments: [],
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

    SET_ATTRACTION_DETAIL(state, data) {
      state.attractionDetail = data;
    },

    SET_SHOW_DETAIL(state, data) {
      state.showDetail = data;
    },

    SET_ATTRACTION_COMMENTS(state, data) {
      state.attractionComments = data;
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
    async attractionDetail({ commit, dispatch }, payload) {
      let url = "/tour/detail?contentId=" + payload.contentId;
      if (payload.userId != null) {
        url += "&userId=" + payload.userId;
      }

      await http
        .get(url)
        .then(({ data }) => {
          if (data) {
            commit("SET_ATTRACTION_DETAIL", data);
            commit("SET_SHOW_DETAIL", true);
            dispatch("attractionComments", payload.contentId);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },

    async attractionComments({ commit }, payload) {
      await http
        .get("/tour/comment/" + payload)
        .then(({ data }) => {
          if (data.message === "success") {
            commit("SET_ATTRACTION_COMMENTS", data.commentList);
          } else {
            console.log("댓글을 불러오지 못했습니다.");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },

    async writeComment({ dispatch }, payload) {
      await http
        .post("/tour/comment", payload)
        .then(({ data }) => {
          if (data.message === "success") {
            dispatch("attractionComments", payload.contentId);
          } else {
            console.log("댓글을 불러오지 못했습니다.");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },

    async deleteComment({ dispatch }, payload) {
      await http
        .delete(`/tour/comment/${payload.id}`)
        .then(({ data }) => {
          if (data.message === "success") {
            dispatch("attractionComments", payload.contentId);
          } else {
            console.log("댓글을 불러오지 못했습니다.");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};

export default attractionStore;
