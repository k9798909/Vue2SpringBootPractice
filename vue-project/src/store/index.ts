import { createStore } from 'vuex'


export default createStore({
  //用來獲取存在Store資料
  state: {
    myName : "asdasd",
    count:1,
    isLogin:  false,
    loginData: {
      id : '',
      username : '',
      name :  '',
      token :  '',
    }
  },
  getters: {
    getMyName:state => state.myName + "11111",
    isLogin:state => state.isLogin,
    loginData:state => state.loginData,
  },
  //改變store的資料 store.commit
  mutations: {
    updateMyName(state,myName) {
      state.myName = myName
    },
    login(state) {
      console.log('login');
      state.isLogin = true;
    },
    loginData(state,data) {
      console.log('loginData');
      state.loginData.id = data.id;
      state.loginData.username = data.username;
      state.loginData.name = data.name;
      state.loginData.token = data.token;
    },
  },
  actions: {
    updateMyNameAction(context,myName){
      context.commit('updateMyName',myName);
    },
    loginAction(context) {
      context.commit('login');
    },
    loginDataAction(context,data) {
      context.commit('loginData',data);
    }
  },
  modules: {
  }
})
