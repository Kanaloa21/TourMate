import { login, checkAuth, logout, tokenRegeneration, getInfo } from "@/api/user";

const userStore = {
  namespaced: true,
  state: {
    userInfo: null,
    isLogin: false,
    isValidToken: false,
    userId: null,
    userName: null,
    authorizeLevel: 0,
    accessToken: null,
    refreshToken: null,
  },
  getters: {
    checkIsLogin(state) {
      return state.isLogin;
    },
    checkIsValidToken(state) {
      return state.isValidToken;
    },
    checkAuthorizeLevel(state) {
      return state.authorizeLevel;
    },
  },
  actions: {
    // 로그인
    async userConfirm({ state }, user) {
      await login(
        user,
        ({ data }) => {
          if (data.message === "success") {
            state.accessToken = data["access-token"];
            state.refreshToken = data["refresh-token"];
            state.userId = data.loginInfo.userId;
            state.userName = data.loginInfo.userName;
            state.isLogin = true;
            state.authorizeLevel = data.loginInfo.authorizeLevel;
          } else {
            // 로그인 실패, 아이디 혹은 비밀번호가 틀림
          }
        },
        (error) => {
          // 서버 에러 발생
          console.log(error);
          alert("서버에 문제가 생겼습니다!");
        }
      );
    },

    // 토큰 검증
    async checkUserAuth({ state, dispatch }, token) {
      console.log("토큰 검증 시작");
      await checkAuth(
        token,
        ({ data }) => {
          if (data.message === "success") {
            console.log("토큰 무결성 검증 완료");
            state.isValidToken = true;
          }
        },
        async (error) => {
          console.log("엑세스 토큰을 사용할 수 없습니다", error);
          state.isValidToken = false;
          await dispatch("tokenRegeneration");
        }
      );
    },

    // 토큰 재발급
    async tokenRegeneration({ state }) {
      console.log("토큰 재발급 실행");
      await tokenRegeneration(
        { userId: state.userId },
        state.refreshToken,
        ({ data }) => {
          state.accessToken = data["access-token"];
          state.refreshToken = data["refresh-token"];
          state.isValidToken = true;
        },
        (error) => {
          console.log("서버 에러 발생", error);
        }
      );
    },

    // 로그아웃
    async userLogout({ state }) {
      if (state.isLogin) {
        await logout(
          state.userId,
          () => {
            state.accessToken = null;
            state.refreshToken = null;
            state.userId = null;
            state.userName = null;
            state.isLogin = false;
            state.authorizeLevel = 0;
            console.log("로그아웃 완료");
          },
          (error) => {
            console.log("서버 에러 발생", error);
          }
        );
      }
    },

    // 유저 정보
    async getUserInfo({ state }) {
      if (state.isLogin) {
        await getInfo(
          state.userId,
          ({ data }) => {
            state.userInfo = data.userInfo;

            console.log("내 정보 받기 완료: " + data.userInfo);
          },
          (error) => {
            console.log("서버 에러 발생", error);
          }
        );
      }
    },
  },
};

export default userStore;
