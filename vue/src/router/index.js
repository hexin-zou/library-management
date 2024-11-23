import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from '@/views/Layout.vue'
import Login from '@/views/login/Login.vue'
import Cookies from "js-cookie";
// import HomeView from '../views/home/HomeView.vue'  //直接引入HomeView.vue，所以这里导入它

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  //  ====== 主页  =====
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: '/home',
    children: [  // 子路由
      {
        path: 'home',
        name: 'HomeView',
        component: ()=>import('../views/home/HomeView.vue')
      },
      {
        path: 'about',
        name: 'about',

        component: () => import('../views/user/AboutView.vue')
      }
      ,
      {
        path: 'userlist',
        name: 'UserView',
        component: () => import ('../views/user/User.vue')
      }
      ,
      {
        path: 'Add',
        name: 'AddUser',
        component: () => import('../views/user/AddUser.vue')
      }
      ,
      {
        path: 'Editor',
        name: 'EditUser',
        component: () => import('../views/user/EditorUser.vue')
      },
      // =================Admin===========================
      {
        path: 'adminlist',
        name: 'Admin',
        component: () => import ('../views/admin/List.vue')
      }
      ,
      {
        path: 'addAdmin',
        name: 'Add',
        component: () => import('../views/admin/Add.vue')
      }
      ,
      {
        path: 'edit',
        name: 'Editor',
        component: () => import('../views/admin/Editor.vue')
      },
      //  ====  Category  ====
      { path: 'categoryList', name: 'CategoryList', component: () => import('@/views/category/List.vue') },
      { path: 'addCategory', name: 'AddCategory', component: () => import('@/views/category/Add.vue') },
      { path: 'editCategory', name: 'EditCategory', component: () => import('@/views/category/Edit.vue') },
      //  ====  Book  ====
      { path: 'bookList', name: 'BookList', component: () => import('@/views/book/List.vue') },
      { path: 'addBook', name: 'AddBook', component: () => import('@/views/book/Add.vue') },
      { path: 'editBook', name: 'EditBook', component: () => import('@/views/book/Edit.vue') },
    ]
  },
  {
    path: '*',
    component: ()=>import('../views/user/404.vue')
  }

]


const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});

router.beforeEach((to, from, next) => {
  if (to.path === '/login') next()
  const admin = Cookies.get("admin")
  if (!admin && to.path !== '/login') return next("/login")  // 强制退回到登录页面
  // 访问 /home 的时候，并且cookie里面存在数据，这个时候我就直接放行
  next()
})

export default router
