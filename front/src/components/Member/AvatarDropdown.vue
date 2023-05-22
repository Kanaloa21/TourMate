<template>
  <div>
    <b-dropdown
      size="lg"
      variant="link"
      toggle-class="text-decoration-none"
      no-caret
      class="avatar"
    >
      <template #button-content>
        <b-icon icon="person-circle" class="h1 mb-0 icon"></b-icon>
      </template>
      <b-dropdown-item href="#" @click="onClick">로그인</b-dropdown-item>
      <b-dropdown-item href="#">회원가입</b-dropdown-item>
    </b-dropdown>
  </div>
</template>

<script>
export default {
  name: "AvatarDropdown",
  components: {},
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {
    onClick() {
      const h = this.$createElement;
      const titleVNode = h("h3", { domProps: { innerHTML: "로그인" } });
      const messageVNode = h("div", { class: ["foobar"] }, [
        h(
          "b-input-group",
          {
            class: ["text-center"],
            props: {
              prepend: "EMAIL",
            },
          },
          [
            h("b-form-input", {
              props: {
                type: "email",
                id: "email",
              },
            }),
          ]
        ),
        h(
          "b-input-group",
          {
            class: ["text-center"],
            props: {
              prepend: "PASSWORD",
            },
          },
          [
            h("b-form-input", {
              props: {
                type: "password",
                id: "password",
              },
            }),
          ]
        ),
        // h("p", { class: ["text-center"] }, [" Flashy ", h("strong", "msgBoxOk"), " message "]),
        // h("p", { class: ["text-center"] }, [h("b-spinner")]),
        // h("b-img", {
        //   props: {
        //     src: "https://picsum.photos/id/20/250/250",
        //     thumbnail: true,
        //     center: true,
        //     fluid: true,
        //     rounded: "circle",
        //   },
        // }),
      ]);
      this.$bvModal
        .msgBoxOk([messageVNode], {
          title: [titleVNode],
          size: "",
          buttonSize: "",
          okVariant: "success",
          okTitle: "Login",
          centered: true,
        })
        .then(() => {
          let email = document.querySelector("#email").value;
          let password = document.querySelector("#password").value;
          console.log("onClick Test, This is Login API Callback Function");
          console.log("your email is " + email + ", password is " + password);
        });
    },
  },
};
</script>

<style scoped>
.avatar {
  position: absolute;
  top: 0;
  right: 0;
  padding: 20px 20px 0 150px;
}
.icon {
  width: 40px;
  height: 40px;
}
</style>
