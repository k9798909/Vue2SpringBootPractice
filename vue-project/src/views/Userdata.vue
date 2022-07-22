<template>
  <div class="container-md h-100">
    <div class="row justify-content-center">
      <div class="col-6 mb-3">
        <h3 class="text-center">個人資料</h3>
      </div>
    </div>
    <div class="row justify-content-center">
      <div class="col-3">
        <p class="text-end">會員姓名:</p>
      </div>
      <div class="col-3">
        <p class="text-start">{{ userdata.name }}</p>
      </div>
    </div>
    <div class="row justify-content-center">
      <div class="col-3">
        <p class="text-end">性別:</p>
      </div>
      <div class="col-3">
        <p class="text-start">男女</p>
      </div>
    </div>
    <div class="row justify-content-center">
      <div class="col-3">
        <p class="text-end">出生日期:</p>
      </div>
      <div class="col-3">
        <p class="text-start">測試出生日期</p>
      </div>
    </div>
    <div class="row justify-content-center">
      <div class="col-3">
        <p class="text-end">身分證號:</p>
      </div>
      <div class="col-3">
        <p class="text-start">測試身分證號</p>
      </div>
    </div>

  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { getUserData } from "@/axios/axios";
import { mapGetters } from "vuex";

interface Userdata {
  id: number;
  name: string;
}

export default defineComponent({
  name: "product",
  computed: {
    ...mapGetters(["loginData"]),
  },
  async mounted() {
    try {
      this.userdata = (await getUserData(Number(this.loginData.id))).data.data;
    } catch (e) {
      console.log(e);
    }
  },
  data() {
    return {
      userdata: {} as Userdata,
    };
  },
});
</script>


<style lang="sass">
</style>