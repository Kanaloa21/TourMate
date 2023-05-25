<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <h1 class="mt-3" style="color: black; font-family: 'GangwonEduPowerExtraBoldA'">
          <b-icon icon="megaphone"></b-icon> 공지사항
        </h1>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button v-if="authorizeLevel > 1" variant="outline-primary" @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table
          head-variant="light"
          hover
          :items="articles"
          :fields="fields"
          @row-clicked="viewArticle"
        >
        </b-table>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/api/index";
import { mapState } from "vuex";

export default {
  name: "NoticeList",
  data() {
    return {
      articles: [],
      fields: [
        { key: "id", label: "글번호", tdClass: "tdClass" },
        { key: "title", label: "제목", tdClass: "tdSubject" },
        { key: "writer", label: "작성자", tdClass: "tdClass" },
        { key: "date", label: "작성일", tdClass: "tdClass" },
      ],
    };
  },
  computed: {
    ...mapState("userStore", ["authorizeLevel"]),
  },
  created() {
    http
      .get("/notice")
      .then(({ data }) => {
        this.articles = data;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "noticeWrite" });
    },
    viewArticle(article) {
      this.$router.push({
        name: "noticeView",
        params: { articleno: article.id },
      });
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
