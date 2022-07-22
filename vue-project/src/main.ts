import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"
import {StorageType} from "vue3-storage";
import Vue3Storage from "vue3-storage";

createApp(App)
    .use(store)
    .use(router)
    .use(Vue3Storage, { namespace: "pro_", storage: StorageType.Session })
    .mount('#app')
