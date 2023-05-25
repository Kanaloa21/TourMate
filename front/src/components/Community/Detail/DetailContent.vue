<template>
  <div class="write-area">
    <b-row class="m-1" v-if="planDetail != null">
      <b-col style="text-align: left">
        <b-form>
          <b-row class="mt-0">
            <b-col class="col-lg-10 pt-3 px-0">
              <h3 class="m-0" style="text-align: center">{{ planDetail.subject }}</h3>
            </b-col>
            <b-col class="mt-3 mx-0 col-lg-1 p-0">
              <b-button
                @click="putBookmark"
                variant="warning"
                class="m-1"
                v-if="this.isLogin == true"
                ><b-icon :icon="bookmark" class=""></b-icon>
              </b-button>
            </b-col>
            <b-col class="mt-3 mx-0 col-lg-1 pb-2 pl-0 pr-2"
              ><b-button @click="putLike" variant="info" class="m-1" v-if="this.isLogin == true"
                ><b-icon :icon="like" class=""></b-icon
              ></b-button>
            </b-col>
          </b-row>
          <hr class="mt-0" />
          <b-row>
            <b-col>
              <b-form-textarea
                id="textarea-plaintext"
                plaintext
                rows="3"
                max-rows="3"
                debounce="500"
                :value="planDetail.summary"
              ></b-form-textarea>
            </b-col>
          </b-row>
        </b-form>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import http from "@/api/index";
import { mapActions, mapState } from "vuex";

const planStore = "planStore";
const userStore = "userStore";

export default {
  name: "PlanWrite",
  components: {},
  data() {
    return {
      bookmark: "bookmark-plus",
      like: "hand-thumbs-up",
    };
  },
  created() {},
  methods: {
    async putBookmark() {
      let tmp = {
        id: this.planDetail.planId,
        userId: this.userId,
      };
      http
        .put("/plan/bookmark", JSON.stringify(tmp))
        .then(() => console.log(`success [${tmp}]`))
        .catch(() => console.log("fail"));

      let params = {
        planId: this.planDetail.planId,
      };
      if (this.userId != null) {
        params = {
          userId: this.userId,
          planId: this.planDetail.planId,
        };
      }
      await this.getPlanDetail(params);
      if (this.planDetail.bookmarked == true) {
        this.bookmark = "bookmark-plus-fill";
      } else {
        this.bookmark = "bookmark-plus";
      }
    },

    async putLike() {
      let tmp = {
        id: this.planDetail.planId,
        userId: this.userId,
      };
      http
        .put("/plan/like", JSON.stringify(tmp))
        .then(() => console.log(`success [${tmp}]`))
        .catch(() => console.log("fail"));

      let params = {
        planId: this.planDetail.planId,
      };
      if (this.userId != null) {
        params = {
          userId: this.userId,
          planId: this.planDetail.planId,
        };
      }
      await this.getPlanDetail(params);
      if (this.planDetail.liked == true) {
        this.like = "hand-thumbs-up-fill";
      } else {
        this.like = "hand-thumbs-up";
      }
    },
    ...mapActions(planStore, ["getPlanDetail"]),
  },
  computed: {
    ...mapState(planStore, ["planDetail"]),
    ...mapState(userStore, ["userId", "isLogin"]),
  },
  watch: {
    planDetail() {
      if (this.planDetail.liked == true) {
        this.like = "hand-thumbs-up-fill";
      } else {
        this.like = "hand-thumbs-up";
      }
      if (this.planDetail.bookmarked == true) {
        this.bookmark = "bookmark-plus-fill";
      } else {
        this.bookmark = "bookmark-plus";
      }
    },
  },
};
</script>

<style scoped>
.write-area {
  background-color: white;
  color: black;
  top: 0vh;
  /* bottom: 0vh; */
  left: 600px;
  padding: 0px 0 0;
  box-shadow: 0 2px 5px 0 rgb(0 0 0 / 25%), 0 2px 10px 0 rgb(0 0 0 / 25%);
  /* 최소 창 크기 500px (크롬기준) */
  /* width: 100%; */
  height: 20vh;
  width: 700px;
  /* background-color: rgb(133, 133, 132); */
  border-bottom-left-radius: 30px;
  border-bottom-right-radius: 30px;
}
</style>
