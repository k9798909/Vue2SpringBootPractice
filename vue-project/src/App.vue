<template>
  <div>
    <Header class="mb-3"></Header>
    <router-view></router-view>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import Header from "@/components/Header.vue"; // @ is an alias to /src


export default defineComponent({
  name: "app",
  components: {
    Header,
  },

});
</script>

<style lang="scss">
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+TC&family=Roboto&display=swap");

img {
  display: block;
}

*,
*::before,
*::after {
  font-family: "Roboto", "Noto Sans TC", sans-serif;
}

html,
body,
#app,
#app > div {
  height: 100%;
}
</style>
