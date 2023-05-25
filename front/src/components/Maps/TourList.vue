<template>
  <!-- <div> -->
  <div class="px-4 list scroll" style="overflow-y: scroll" @scroll="handleNotificationListScroll">
    <!-- <b-button class="position-absolute sidebar-toggle" style="z-index: 3">&#60;</b-button> -->
    <div>
      <b-row class="mt-3">
        <select-sido @select-sido="selectSido"></select-sido>
        <select-gugun :sidoCode="sidoCode" @select-gugun="selectGugun"></select-gugun>
      </b-row>
      <div class="mt-3">
        <b-input-group class="mb-3" prepend="검색어">
          <b-form-input
            v-model="keyword"
            placeholder="Enter로 검색"
            @keypress.enter="submit"
            style="width: 220px"
          ></b-form-input>
          <b-input-group-append>
            <b-button text="Button" variant="success" @click="submit"
              ><b-icon icon="search"
            /></b-button>
          </b-input-group-append>
        </b-input-group>
      </div>
    </div>
    <div
      id="ww_ad2a25e65daab"
      v="1.3"
      loc="id"
      a='{"t":"ticker","lang":"ko","sl_lpl":1,"ids":["wl2308"],"font":"Arial","sl_ics":"one_a","sl_sot":"celsius","cl_bkg":"image","cl_font":"#FFFFFF","cl_cloud":"#FFFFFF","cl_persp":"#81D4FA","cl_sun":"#FFC107","cl_moon":"#FFC107","cl_thund":"#FF5722"}'
    >
      Weather Data Source:
      <a href="https://wetterlabs.de/wetter_daejeon/woche/" id="ww_ad2a25e65daab_u" target="_blank"
        >wetterlabs.de/wetter_daejeon/woche/</a
      >
    </div>
    <hr />
    <div>
      <div v-for="attraction in top10Attractions" :key="attraction.contentId" class="list-item">
        <b-card
          :title="attraction.title"
          :img-src="attraction.imageURL"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 20rem; color: black"
          class="m-4"
        >
          <b-row>
            <col class="mx-3" />
            <b-button href="#" variant="warning" class="mx-1" @click="addPlan(attraction)">
              담기
            </b-button>
            <b-button href="#" variant="success" class="mx-1" @click="showDetail(attraction)">
              위치 보기
            </b-button>
            <b-button href="#" variant="info" class="mx-1" @click="clickLike(attraction)"
              >Like {{ attraction.likeCount }}
            </b-button>
          </b-row>
        </b-card>
      </div>
    </div>
  </div>
  <!-- <div class="notification-container" id="notification-container">
      <p>You have already entered the letter</p>
    </div> -->
  <!-- </div> -->
</template>

<script>
import { mapActions, mapState, mapGetters, mapMutations } from "vuex";
import SelectSido from "@/components/item/SelectSido.vue";
import SelectGugun from "@/components/item/SelectGugun.vue";

const attractionStore = "attractionStore";
const itemStore = "itemStore";
const planStore = "planStore";
const userStore = "userStore";

export default {
  name: "TourList",
  components: { SelectSido, SelectGugun },
  data() {
    return {
      sidoCode: 0,
      gugunCode: 0,
      keyword: null,
    };
  },
  created() {},
  mounted() {
    this.sidoCode = this.getSidoCode;
    this.gugunCode = this.getGugunCode;
    this.keyword = this.getKeyword;
    const script = document.createElement("script");
    script.setAttribute("src", "https://app1.weatherwidget.org/js/?id=ww_ad2a25e65daab");
    document.head.appendChild(script);
  },
  methods: {
    showNotification() {
      const notification = document.getElementById("notification-container");
      notification.classList.add("show");
      setTimeout(() => {
        notification.classList.remove("show");
      }, 2000);
    },

    handleNotificationListScroll(e) {
      const { scrollHeight, scrollTop, clientHeight } = e.target;
      const isAtTheBottom = scrollHeight === scrollTop + clientHeight;
      // 일정 이상 밑으로 내려오면 함수 실행 / 반복된 호출을 막기위해 1초마다 스크롤 감지 후 실행
      if (isAtTheBottom) {
        setTimeout(() => this.handleLoadMore(), 1000);
      }
    },

    // 내려오면 api를 호출하여 아래에 더 추가,
    handleLoadMore() {
      console.log("리스트 추가");
      this.SET_PAGING(this.paging + 1);
      this.SET_FILTERED_ATTRACTION_LIST();
      // api를 호출하여 리스트 추가하면 됨, 현재는 pushList에 1개의 index 추가
    },
    addPlan(attraction) {
      this.ADD_WISH_LIST(attraction);
      this.showNotification();
    },

    showDetail(attraction) {
      console.log(attraction, this.mapFocusAttractionInfo);
      if (attraction == this.mapFocusAttractionInfo) {
        this.SET_SHOW_DETAIL(false);
        this.SET_MAP_FOCUS_ATTRACTION_INFO(null);
        return;
      }

      let latlng = { lat: attraction.latitude, lng: attraction.longitude };
      console.log(latlng);
      this.SET_MAP_CENTER_POS(latlng);
      this.SET_MAP_FOCUS_ATTRACTION_INFO(attraction);
      let param = {
        userId: this.userId,
        contentId: attraction.contentId,
      };
      this.attractionDetail(param);
    },
    async clickLike(attraction) {
      if (!this.userId) {
        alert("로그인 후 좋아요가 가능합니다");
        return;
      }

      const param = {
        id: attraction.contentId,
        userId: this.userId,
      };
      await this.updateLiked(param);

      let params = {
        sidoCode: this.sidoCode,
        gugunCode: this.gugunCode,
        keyword: this.keyword,
        userId: this.userId,
      };
      this.searchAttractionList(params);
    },
    submit() {
      if (!this.sidoCode && !this.gugunCode && !this.keyword) {
        alert("시도, 구군, 키워드 중 최소 하나는 선택 또는 입력해야 합니다!");
        return;
      }

      this.SET_PAGING(1);
      this.SET_SHOW_DETAIL(false);
      this.SET_MAP_FOCUS_ATTRACTION_INFO(null);
      let params = {
        sidoCode: this.sidoCode,
        gugunCode: this.gugunCode,
        keyword: this.keyword,
        userId: this.userId,
      };
      this.searchAttractionList(params);
    },

    selectSido(sidoCode) {
      this.CLEAR_GUGUN_LIST();
      this.sidoCode = sidoCode;
      this.gugunCode = 0;
    },
    selectGugun(gugunCode) {
      this.gugunCode = gugunCode;
    },

    ...mapActions(attractionStore, ["searchAttractionList", "updateLiked", "attractionDetail"]),
    ...mapMutations(attractionStore, [
      "SET_MAP_CENTER_POS",
      "SET_MAP_FOCUS_ATTRACTION_INFO",
      "SET_PAGING",
      "SET_FILTERED_ATTRACTION_LIST",
      "SET_SHOW_DETAIL",
    ]),
    ...mapActions(itemStore, ["getGugun"]),
    ...mapMutations(itemStore, [
      "CLEAR_GUGUN_LIST",
      "SET_SIDO_CODE",
      "SET_GUGUN_CODE",
      "SET_KEYWORD",
    ]),
    ...mapMutations(planStore, ["ADD_WISH_LIST"]),
  },

  computed: {
    ...mapState(attractionStore, ["attractionList", "paging", "mapFocusAttractionInfo"]),
    ...mapState(userStore, ["userId"]),
    ...mapGetters(attractionStore, ["top10Attractions"]),
    ...mapGetters(itemStore, ["getSidoCode", "getGugunCode", "getKeyword"]),
  },
};
</script>

<style scoped>
hr {
  height: 3px;
  background-color: rgb(200, 200, 200);
  border-width: 2px;
  border-radius: 15px;
}
.list {
  /* position: fixed; */
  top: 0vh;
  bottom: 0;
  left: 80px;
  padding: 0px 0 0;
  box-shadow: 0 2px 5px 0 rgb(0 0 0 / 25%), 0 2px 10px 0 rgb(0 0 0 / 25%);
  /* 최소 창 크기 500px (크롬기준) */
  width: 400px;
  height: 100vh;
  z-index: 500;
  background-color: rgb(200, 200, 200);
}

.list-item {
  font-family: Jeju Gothic;
}

.scroll::-webkit-scrollbar {
  width: 12px;
}

.scroll::-webkit-scrollbar-track {
  background: #ddd;
}

.scroll::-webkit-scrollbar-thumb {
  background: #42b983;
}

.notification-container {
  background: rgba(0, 0, 0, 0.3);
  border-radius: 10px;
  padding: 15px 20px;
  bottom: 50px;
  display: none;
}

.notification-container.show {
  display: block;
}
</style>
