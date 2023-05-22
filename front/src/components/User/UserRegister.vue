<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="msg">{{ msg }}</b-alert>
            <b-form-group label="아이디" label-for="userid">
              <b-form-input
                id="userid"
                v-model="user.userId"
                required
                placeholder="사용하실 아이디를 입력해주세요"
                @blur="checkIdDuple"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                v-model="user.userPw"
                required
                placeholder="사용하실 비밀번호를 입력해주세요"
                @keyup="checkPwValid"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호 확인" label-for="userpwdConfirm">
              <b-form-input
                type="password"
                id="userpwdConfirm"
                v-model="tempPw"
                required
                placeholder=""
                @blur="checkPwSame"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="닉네임" label-for="nickname">
              <b-form-input
                id="nickname"
                v-model="user.userName"
                required
                placeholder="사용하실 닉네임을 입력해주세요"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="전화번호" label-for="tel">
              <b-form-input
                id="tel"
                v-model="user.phoneNumber"
                required
                placeholder='전화번호를 입력해 주세요 ("-" 없이 입력)'
                @blur="checkTel"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="성별" label-for="gender">
              <b-form-radio-group
                id="gender"
                v-model="user.gender"
                :options="options"
                name="gender"
              ></b-form-radio-group>
            </b-form-group>
            <b-button type="button" variant="primary" class="m-1" @click="confirm"
              >회원가입</b-button
            >
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/api/index";

export default {
  name: "UserRegister",
  data() {
    return {
      user: {
        userId: null,
        userPw: null,
        userName: null,
        gender: 0,
        phoneNumber: null,
      },
      msg: null,
      tempPw: null,
      options: [
        { text: "여", value: 0 },
        { text: "남", value: 1 },
      ],

      idConfirm: false,
      pwConfirm: false,
      pwSameConfirm: false,
      telConfirm: false,
    };
  },
  methods: {
    checkIdDuple() {
      if (!this.user.userId) {
        this.msg = "아이디를 입력해 주세요";
        return;
      }
      http
        .get(`/user/check?userId=${this.user.userId}`)
        .then(({ data }) => {
          if (data === "success") {
            this.msg = null;
            this.idConfirm = true;
          } else {
            this.msg = "중복된 유저 아이디 입니다";
            this.idConfirm = false;
          }
        })
        .catch((error) => {
          console.log("서버 에러 발생", error);
          this.idConfirm = false;
        });
    },
    checkPwValid() {
      if (this.user.userPw.length >= 8) {
        this.msg = null;
        this.pwConfirm = true;
      } else {
        this.msg = "비밀번호는 8자리 이상으로 해주세요";
        this.pwConfirm = false;
      }
    },
    checkPwSame() {
      if (this.user.userPw == this.tempPw) {
        this.msg = null;
        this.pwSameConfirm = true;
      } else {
        this.msg = "비밀번호가 일치하지 않습니다!";
        this.pwSameConfirm = false;
      }
    },
    checkTel() {
      if (this.user.phoneNumber.length > 8 && this.user.phoneNumber.length < 12) {
        this.msg = null;
        this.telConfirm = true;
      } else {
        this.msg = "전화번호를 정확히 입력해주세요";
        this.telConfirm = false;
      }
    },
    async confirm() {
      if (this.idConfirm && this.pwConfirm && this.pwSameConfirm) {
        await http
          .post(`/user/regist`, this.user)
          .then(({ data }) => {
            if (data === "success") {
              alert("회원가입이 완료되었습니다. 다시 로그인 해주세요");
              this.$router.push({ name: "login" });
            }
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
  },
};
</script>

<style>
b-form-group {
  color: black;
}
</style>
