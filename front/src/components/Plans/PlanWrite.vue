<template>
  <div class="write-area">
    <b-row class="m-1">
      <b-col style="text-align: left">
        <b-form>
          <b-row class="mt-3">
            <b-col class="col-lg-8 pr-0">
              <b-form-group id="subject-group" label="제목" label-for="subject">
                <b-form-input
                  id="subject"
                  v-model="subject"
                  type="text"
                  required
                  placeholder="제목 입력..."
                ></b-form-input>
              </b-form-group>
            </b-col>
            <b-col class="mt-4 mx-0 col-lg-2 p-0 pl-4">
              <b-button @click="mySubmit" variant="primary" class="m-1" v-if="this.isLogin == true"
                >글작성</b-button
              >
            </b-col>
            <b-col class="mt-4 mx-0 col-lg-2 p-0"
              ><b-button @click="myReset" variant="danger" class="m-1">초기화</b-button>
            </b-col>
          </b-row>
          <b-row>
            <b-col>
              <b-form-group id="summary-group" label="내용" label-for="summary">
                <b-form-textarea
                  id="summary"
                  v-model="summary"
                  placeholder="내용 입력..."
                  required
                  rows="4"
                  max-rows="4"
                ></b-form-textarea>
              </b-form-group>
            </b-col>
          </b-row>
        </b-form>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import http from "@/api/index";
import { mapState } from "vuex";

const userStore = "userStore";
const planStore = "planStore";

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
    mySubmit() {
      let plan = {
        userId: this.userId,
        subject: this.subject,
        summary: this.summary,
        planAttractions: [],
      };
      plan.planAttractions = this.wishList;
      http
        .post("/plan", JSON.stringify(plan))
        .then(() => console.log(`success [${plan}]`))
        .catch(() => console.log("fail"));
    },
    myReset() {
      this.subject = "";
      this.summary = "";
    },
  },
  computed: {
    ...mapState(planStore, ["wishList"]),
    ...mapState(userStore, ["userId", "isLogin"]),
  },
};
</script>

<style scoped>
.write-area {
  background-color: white;
  color: black;
  top: 70vh;
  /* bottom: 0vh; */
  left: 600px;
  padding: 0px 0 0;
  box-shadow: 0 2px 5px 0 rgb(0 0 0 / 5%), 0 2px 10px 0 rgb(0 0 0 / 5%);
  /* 최소 창 크기 500px (크롬기준) */
  /* width: 100%; */
  height: 30vh;
  width: 700px;
  /* background-color: rgb(133, 133, 132); */
  border-top-left-radius: 30px;
  border-top-right-radius: 30px;
}
</style>
