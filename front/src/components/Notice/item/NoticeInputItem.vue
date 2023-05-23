<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit">
        <b-form-group
          id="userid-group"
          label="작성자:"
          label-for="userid"
          description="작성자를 입력하세요."
        >
          <b-form-input
            id="userid"
            :disabled="isUserid"
            v-model="userId"
            readonly
            type="text"
            required
            placeholder="작성자 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="title-group"
          label="제목:"
          label-for="title"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="title"
            v-model="article.title"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="article.content"
            placeholder="내용 입력..."
            rows="10"
            required
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button type="submit" variant="primary" class="m-1" v-if="this.type === 'register'"
          >글작성</b-button
        >
        <b-button type="submit" variant="primary" class="m-1" v-else>글수정</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import http from "@/api/index";
import { mapState } from "vuex";

export default {
  name: "NoticeInputItem",
  data() {
    return {
      article: {
        id: 0,
        writer: "",
        title: "",
        content: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState("userStore", ["userId"]),
  },
  created() {
    if (this.type === "modify") {
      let param = this.$route.params.articleno;
      http
        .get(`/notice/${param}`)
        .then(({ data }) => {
          this.article = data;
        })
        .catch((error) => {
          console.log(error);
        });

      this.isUserid = true;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";

      if (!err) alert(msg);
      else this.type === "register" ? this.registArticle() : this.modifyArticle();
    },

    registArticle() {
      let param = {
        writer: this.userId,
        title: this.article.title,
        content: this.article.content,
      };

      http
        .post("/notice/admin", param)
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    modifyArticle() {
      let param = {
        id: this.article.id,
        writer: this.userId,
        title: this.article.title,
        content: this.article.content,
      };

      http
        .put("/notice/admin", param)
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.moveList();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    moveList() {
      this.$router.push({ name: "noticeList" });
    },
  },
};
</script>

<style></style>
