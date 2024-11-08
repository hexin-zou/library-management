import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'  //直接引入HomeView.vue，所以这里导入它

Vue.use(VueRouter)

const routes = [

    // ============================User=================================
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
    component: () => import(/* webpackChunkName: "about" */ '../views/user/AboutView.vue')
  }
  ,
  {
    path: '/User',
    name: 'UserView',
    component: () => import ('../views/user/User.vue')
  }
  ,
  {
    path: '/Add',
    name: 'AddUser',
    component: () => import('../views/user/AddUser.vue')
  }
  ,
  {
    path: '/Editor',
    name: 'EditUser',
    component: () => import('../views/user/EditorUser.vue')
  },
  // =================Admin===========================
  {
    path: '/admin',
    name: 'Admin',
    component: () => import ('../views/admin/Admin.vue')
  }
  ,
  {
    path: '/addAdmin',
    name: 'Add',
    component: () => import('../views/admin/Add.vue')
  }
  ,
  {
    path: '/edit',
    name: 'Editor',
    component: () => import('../views/admin/Editor.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
