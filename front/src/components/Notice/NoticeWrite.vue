<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글작성</h3></b-alert>
      </b-col>
    </b-row>
    <notice-input-item type="register" />
  </b-container>
</template>

<script>
import NoticeInputItem from "@/components/Notice/item/NoticeInputItem";

export default {
  name: "NoticeWrite",
  components: {
    NoticeInputItem,
  },
};
</script>

<style></style>
