import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'  //直接引入HomeView.vue，所以这里导入它

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  }
  ,
  {
    path: '/User',
    name: 'UserView',
    component: () => import ('../views/User.vue')
  }
  ,
  {
    path: '/Add',
    name: 'AddUser',
    component: () => import('../views/AddUser.vue')
  }
  ,
  {
    path: '/edit',
    name: 'EditUser',
    component: () => import('../views/EditorUser.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
