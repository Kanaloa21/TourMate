<template>
  <div class="px-4 list bb scroll" style="overflow-y: scroll">
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
            style="width: 240px"
          ></b-form-input>
          <b-input-group-append>
            <b-button text="Button" variant="success" @click="submit"
              ><b-icon icon="search"
            /></b-button>
          </b-input-group-append>
        </b-input-group>
      </div>
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
              북마크
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
  methods: {
    addPlan(attraction) {
      this.ADD_WISH_LIST(attraction);
    },

    showDetail(attraction) {
      let latlng = { lat: attraction.latitude, lng: attraction.longitude };
      console.log(latlng);
      this.SET_MAP_CENTER_POS(latlng);
      this.SET_MAP_FOCUS_ATTRACTION_INFO(attraction);
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
      this.submit();
    },
    submit() {
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

    ...mapActions(attractionStore, ["searchAttractionList", "updateLiked"]),
    ...mapMutations(attractionStore, ["SET_MAP_CENTER_POS", "SET_MAP_FOCUS_ATTRACTION_INFO"]),
    ...mapActions(itemStore, ["getGugun"]),
    ...mapMutations(itemStore, ["CLEAR_GUGUN_LIST"]),
    ...mapMutations(planStore, ["ADD_WISH_LIST"]),
  },

  computed: {
    ...mapState(attractionStore, ["attractionList"]),
    ...mapState(userStore, ["userId"]),
    ...mapGetters(attractionStore, ["top10Attractions"]),
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
  box-shadow: 0 2px 5px 0 rgb(0 0 0 / 5%), 0 2px 10px 0 rgb(0 0 0 / 5%);
  /* 최소 창 크기 500px (크롬기준) */
  width: 420px;
  height: 100vh;
  z-index: 500;
  background-color: rgb(133, 133, 132);
}

.button {
  border-width: 2px;
  border-radius: 15px;
  transition: background-color 0.5s ease-out 10ms;
  background-color: #42b983;
}
.dropdown {
  width: 160px;
  border-width: 2px;
  border-radius: 15px;
  transition: background-color 0.5s ease-out 10ms;
  background-color: #42b983;
}
/* 
.button:hover {
  background-color: rgb(133, 133, 132);
  border-width: 2px;
} */

.list-item {
  font-family: Jeju Gothic;
}

.sidebar-toggle {
  top: 20vh;
  left: 420px;
  padding: 100px 0 0;
  box-shadow: 0 2px 5px 0 rgb(0 0 0 / 5%), 0 2px 10px 0 rgb(0 0 0 / 5%);
  width: 30px;
  height: 70px;
  padding: 0px;
  background-color: rgb(59, 59, 59);
}

.bb {
  --list-load-time: 300ms;
}

.side-list {
  animation: list-load var(--list-load-time) ease-in;
}

@keyframes list-load {
  0% {
    transform: translateX(-100%);
  }
  100% {
    transform: translateX(0%);
  }
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
</style>
