<template>
  <b-container
    v-if="showDetail"
    class="px-3 list bb scroll"
    style="overflow-y: scroll"
    @scroll="handleNotificationListScroll"
  >
    <div><b-img class="mt-2 rounded" :src="attractionDetail.imageURL" width="260px"></b-img></div>
    <div class="mt-4 font">
      <h3>{{ attractionDetail.title }}</h3>
    </div>
    <div>{{ attractionDetail.addr1 }}</div>

    <b-row class="mt-3">
      <b-col class="px-0"
        ><b-button size="md" variant="warning" @click="addPlan()">담기</b-button></b-col
      >
      <b-col class="px-1"
        ><b-button size="md" variant="success"
          >평가 {{ attractionComments.length }}</b-button
        ></b-col
      >
      <b-col
        ><b-button size="sm" variant="danger" @click="clickLike"
          >좋아요<br />
          {{ attractionDetail.likeCount }}</b-button
        ></b-col
      >
    </b-row>
    <div class="p-2 mt-3 bg-light rounded rowb">
      <b-input-group v-if="userId" class="mt-3 mb-3">
        <b-form-input
          placeholder="한줄평을 남겨주세요!"
          v-model="comment"
          @keypress.enter="writeAttractionComment"
        ></b-form-input>
        <b-input-group-append>
          <b-button variant="info" @click="writeAttractionComment">작성</b-button>
        </b-input-group-append>
      </b-input-group>
      <tour-comment-item v-for="item in commentList" :key="item.id" v-bind="item" />
    </div>
  </b-container>
</template>

<script>
import { mapState, mapMutations, mapActions, mapGetters } from "vuex";
import TourCommentItem from "./TourCommentItem.vue";

const attractionStore = "attractionStore";
const userStore = "userStore";
const planStore = "planStore";
const itemStore = "itemStore";

export default {
  name: "TourDetail",
  components: { TourCommentItem },
  data() {
    return {
      comment: "",
      commentPage: 10,
    };
  },
  created() {},
  methods: {
    ...mapActions(attractionStore, [
      "writeComment",
      "updateLiked",
      "searchAttractionList",
      "getAttractionDetail",
    ]),
    ...mapMutations(planStore, ["ADD_WISH_LIST"]),
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
      if (this.commentPage < this.attractionComments.length) {
        this.commentPage = this.commentPage + 10;
        this.commentPage = Math.min(this.commentPage, this.attractionComments.length);
      }

      // api를 호출하여 리스트 추가하면 됨, 현재는 pushList에 1개의 index 추가
    },
    writeAttractionComment() {
      const param = {
        contentId: this.attractionDetail.contentId,
        comment: this.comment,
        writer: this.userId,
      };

      this.writeComment(param);
      this.comment = "";
    },
    addPlan() {
      this.ADD_WISH_LIST(this.attractionDetail);
    },

    async clickLike() {
      if (!this.userId) {
        alert("로그인 후 좋아요가 가능합니다");
        return;
      }

      let param = {
        id: this.attractionDetail.contentId,
        userId: this.userId,
      };
      await this.updateLiked(param);

      param = {
        sidoCode: this.sidoCode,
        gugunCode: this.gugunCode,
        keyword: this.keyword,
        userId: this.userId,
      };
      await this.searchAttractionList(param);

      param = {
        contentId: this.attractionDetail.contentId,
        userId: this.userId,
      };

      await this.getAttractionDetail(param);
    },
  },

  computed: {
    ...mapState(attractionStore, ["showDetail", "attractionDetail", "attractionComments"]),
    ...mapState(userStore, ["userId"]),
    ...mapGetters(itemStore, ["sidoCode", "gugunCode", "keyword"]),
    commentList() {
      return this.attractionComments.slice(0, this.commentPage);
    },
  },
  watch: {
    attractionDetail() {
      this.commentPage = 10;
    },
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
  top: 10vh;
  bottom: 0;
  /* left: 80px; */
  right: 0vw;
  padding: 0px 0 0;
  box-shadow: 0 2px 5px 0 rgb(0 0 0 / 25%), 0 2px 10px 0 rgb(0 0 0 / 25%);
  /* 최소 창 크기 500px (크롬기준) */
  width: 300px;
  height: 80vh;
  z-index: 500;
  background-color: rgb(255, 255, 255);
  color: black;
  border-top-left-radius: 30px;
  border-bottom-left-radius: 30px;
}

.rowb {
  background-color: rgb(200, 200, 200);
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
