<template>
  <d-flex
    ><h1>測試{{ myName }}</h1>
    <button class="btn btn-primary m-4" @click="test">測試</button>
    <button class="btn btn-primary m-4" @click="() => count++">{{countChange}}</button>
    <h1>測試{{ getMyName }}</h1>
    <button class="btn btn-primary m-4">測試getter {{getMyName}}</button>
    <h1>測試{{ myName }}</h1>
    <button class="btn btn-primary m-4" @click="() => {updateMyName('mutations改動')}">測試mutations </button>
    <h1>測試action</h1>
    <button class="btn btn-primary m-4" @click="() => {updateMyNameAction('action改動')}">測試action </button>
  </d-flex>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { mapState,mapGetters, mapMutations,mapActions} from "vuex";

export default defineComponent({
  name: "testVuex",
  computed:  {
    countChange(){
      return "第" + this.count + "次";
    },
    ...mapState(["myName"]),
    ...mapGetters(["getMyName"]),

  },
  data: () => {
    return { count:1};
  },
  methods: {
      ...mapMutations(["updateMyName"]),
      ...mapActions(["updateMyNameAction"]),
      test(){
        console.log(this.myName);
        console.log(this.getMyName);
      }
  },
});
</script>

<style scoped lang="scss">
</style>