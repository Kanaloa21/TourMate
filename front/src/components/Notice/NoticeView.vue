<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="moveList">목록</b-button>
      </b-col>
      <b-col class="text-right" v-if="userId === article.writer">
        <b-button variant="outline-info" size="sm" @click="moveModifyArticle" class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="deleteArticle">글삭제</b-button>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${article.title}</h3><div><h6>${article.writer}</div><div>${article.date}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import http from "@/api/index";

const userStore = "userStore";

export default {
  name: "NoticeView",
  data() {
    return {
      article: {},
    };
  },
  computed: {
    ...mapState(userStore, ["userId"]),
    message() {
      if (this.article.content) return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    let param = this.$route.params.articleno;
    http
      .get(`/notice/${param}`)
      .then(({ data }) => {
        this.article = data;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    moveModifyArticle() {
      this.$router.replace({
        name: "noticeModify",
        params: { articleno: this.article.id },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        http
          .delete(`/notice/admin/${this.article.id}`)
          .then(({ data }) => {
            if (data === "success") {
              alert("해당 공지사항을 삭제하였습니다");
              this.$router.push({ name: "noticeList" });
            } else {
              alert("글을 삭제할 수 없습니다!");
            }
          })
          .catch((error) => {
            console.log(error);
            alert("서버 에러 발생!");
          });
      }
    },
    moveList() {
      this.$router.push({ name: "noticeList" });
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style scoped></style>
