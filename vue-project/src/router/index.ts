import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import store from '@/store'
import { checkLogin } from '../axios/axios';

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'login',
    component: () => import('@/views/Login.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/Login.vue')
  },
  {
    path: '/product',
    name: 'product',
    meta: { requireLogin: true },
    component: () => import('@/views/Product.vue')
  },
  {
    path: '/userdata',
    name: 'userdata',
    meta: { requireLogin: true },
    component: () => import('@/views/Userdata.vue')
  },
  {
    path: '/testVuex',
    name: 'testVuex',
    component: () => import('@/views/TestVuex.vue')
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach(async (to, from, next) => {
  try {
    if (!sessionStorage.getItem("checkLogin")) {
      sessionStorage.setItem("checkLogin", "check");
      const res = (await checkLogin()).data;
      console.log(res.data);
      if (res.data) {
        store.dispatch('loginAction');
        store.dispatch('loginDataAction', res.data);
      }
    }
    
  } catch (e) {
    console.log(e);
  }

  if (to.matched.some(record => record.meta.requireLogin) && !store.state.isLogin) {
    next({
      path: '/login',
      query: { redirect: to.fullPath }
    });
  } else {
    next()
  }
})

export default router
