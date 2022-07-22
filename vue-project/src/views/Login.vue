<template>
  <div class="login pt-5">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-4">
          <form>
            <div class="mb-3">
              <label for="account" class="form-label">帳號</label>
              <input class="form-control" id="username" v-model="loginDto.username" required/>
            </div>
            <div class="mb-3">
              <label for="password" class="form-label">密碼</label>
              <input type="password" class="form-control" id="password" autocomplete="on" v-model="loginDto.password" required/>
            </div>
            <button type="button" class="btn btn-primary" @click="loginEvt()">登入</button>
          </form>
          
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { login } from "@/axios/axios"
import { mapActions } from "vuex";

interface LoginDto {
  username:string,
  password:string,
}

export default defineComponent({
  name: "Login",
  data() {
    return {
      loginDto : {} as LoginDto,
    };
  },
  methods: {
    ...mapActions(["loginAction","loginDataAction"]),
    async loginEvt() {
      try{
        const formData = new FormData();
        formData.append('username', this.loginDto.username);
        formData.append('password', this.loginDto.password);
        let data = (await login(formData));
        if(data.data) {
          alert("成功登入");
          this.loginAction();
          this.loginDataAction(data.data);
          let redirect = this.$route.query.redirect;
          if(redirect) {
            this.$router.push({ path: `${redirect}` });
          } else {
            this.$router.push("/testVuex");
          }
        } else {
          alert("登入失敗帳號或密碼錯誤!!");
        }
      } catch(e) {
        console.log(e)
        alert("登入失敗");
      }
    },
  }
});
</script>


<style lang="sass">
</style>