<template>
  <div class="box">
    <b-row
      ><b-col class="text-break" style="font-weight: bold">{{ writer }}</b-col>
    </b-row>
    <b-row
      ><b-col class="text-break">{{ comment }}</b-col></b-row
    >
    <b-row
      ><b-col style="font-size: 14px">{{ writeDate }}</b-col
      ><b-col
        style="font-size: 14px; text-align: end"
        v-if="writer === userId"
        @click="removeComment"
        >삭제</b-col
      ></b-row
    >
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  name: "TourCommentItem",
  components: {},
  props: {
    id: Number,
    contentId: Number,
    writer: String,
    comment: String,
    writeDate: String,
  },
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {
    ...mapActions("attractionStore", ["deleteComment"]),
    removeComment() {
      this.deleteComment({
        id: this.id,
        contentId: this.contentId,
      });
    },
  },
  computed: {
    ...mapState("userStore", ["userId"]),
  },
};
</script>

<style scoped>
.box {
  color: black;
  border-radius: 5px;
  text-align: start;
  padding: 10px;
}
</style>
