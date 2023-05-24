<template>
  <div class="write-area">
    <b-row class="m-1">
      <b-col style="text-align: left">
        <b-form>
          <b-row class="mt-0">
            <b-col class="col-lg-10 pt-3 px-0">
              <h3 class="m-0" style="text-align: center">{{ planDetail.subject }}</h3>
            </b-col>
            <b-col class="mt-3 mx-0 col-lg-1 p-0">
              <b-button @click="mySubmit" variant="warning" class="m-1" v-if="this.isLogin == true"
                ><b-icon icon="bookmark-plus" class=""></b-icon
              ></b-button>
            </b-col>
            <b-col class="mt-3 mx-0 col-lg-1 pb-2 pl-0 pr-2"
              ><b-button @click="myReset" variant="info" class="m-1" v-if="this.isLogin == true"
                ><b-icon icon="hand-thumbs-up" class=""></b-icon
              ></b-button>
            </b-col>
          </b-row>
          <hr class="mt-0" />
          <b-row>
            <b-col>
              {{ planDetail.summary }}
            </b-col>
          </b-row>
        </b-form>
      </b-col>
    </b-row>
  </div>
</template>

<script>
// import http from "@/api/index";
import { mapState } from "vuex";

const planStore = "planStore";
const userStore = "userStore";

export default {
  name: "PlanWrite",
  components: {},
  data() {
    return {
      subject: "",
      summary: "",
    };
  },
  created() {},
  methods: {
    mySubmit() {},
    myReset() {},
  },
  computed: {
    ...mapState(planStore, ["planDetail"]),
    ...mapState(userStore, ["userId", "isLogin"]),
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
