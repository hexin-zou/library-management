<template>
  <div>
    <!--头部区域-->
    <div style="height:60px; line-height:60px;margin-top: 0; background-color:lightslategrey">
      <div>
        <img src="@/assets/logo.png" alt="" style="width: 40px; position: relative; top: 10px; left: 20px">
        <span style="margin-left:25px;color:white"><strong>图书管理系统</strong></span>
      </div>
      <div style="flex: 1; text-align: right; padding-right: 20px">
        <el-dropdown size="medium">
          <span class="el-dropdown-link">
            {{ admin.username }}<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown" style="margin-top: -5px">
            <el-dropdown-item>
              <div style="width: 50px; text-align: center;" @click="logout">退出</div>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>

    </div>

    <!--    侧边栏和主体-->
    <div style="display:flex">  <!--弹性盒子-->
      <!--侧边栏导航-->                  <!--页面高度-->
      <div style="width:200px; overflow:hidden; margin-right: 1px;margin-left: 0; background-color:white">
        <!--        <router-link to="/" style="color:white;font-size: 15px;font-weight: bold; margin-left:75px">Home</router-link>-->
        <el-menu :default-active="$route.path" router class="el-menu-demo">
          <el-menu-item index="/home">
            <i class="el-icon-house"></i>
            <span slot="title">首页</span>
          </el-menu-item>

          <el-submenu index="admin">
            <template slot="title">
              <i class="el-icon-more"></i>
              <span>管理员管理</span>
            </template>
            <el-menu-item index="/addAdmin">
              <i class="el-icon-tickets"></i>
              <span>管理员添加</span>
            </el-menu-item>
            <el-menu-item index="/adminlist">
              <i class="el-icon-tickets"></i>
              <span>管理员列表</span>
            </el-menu-item>
          </el-submenu>

          <el-submenu index="user">
            <template slot="title">
              <i class="el-icon-more"></i>
              <span>会员管理</span>
            </template>
            <el-menu-item index="/Add">
              <i class="el-icon-tickets"></i>
              <span>会员添加</span>
            </el-menu-item>
            <el-menu-item index="/userlist">
              <i class="el-icon-tickets"></i>
              <span>会员列表</span>
            </el-menu-item>
          </el-submenu>
          <!--      图书分类管理      -->
          <el-submenu index="category">
            <template slot="title">
              <i class="el-icon-s-operation"></i>
              <span>图书分类管理</span>
            </template>
            <el-menu-item index="/addCategory">图书分类添加</el-menu-item>
            <el-menu-item index="/categoryList">图书分类列表</el-menu-item>
          </el-submenu>
          <el-submenu index="book">
            <template slot="title">
              <i class="el-icon-notebook-1"></i>
              <span>图书管理</span>
            </template>
            <el-menu-item index="/addBook">图书添加</el-menu-item>
            <el-menu-item index="/bookList">图书列表</el-menu-item>
          </el-submenu>
          <el-submenu index="borrow">
            <template slot="title">
              <i class="el-icon-document-copy"></i>
              <span>借书管理</span>
            </template>
            <el-menu-item index="/addBorrow">借书添加</el-menu-item>
            <el-menu-item index="/borrowList">借书列表</el-menu-item>
          </el-submenu>
          <el-submenu index="retur">
            <template slot="title">
              <i class="el-icon-document"></i>
              <span>还书管理</span>
            </template>
            <el-menu-item index="/returList">还书列表</el-menu-item>
          </el-submenu>
        </el-menu>
      </div>

      <!--主体数据-->
      <div style="flex:1; min-height: calc(100vh - 40px);margin-right:2px;background-color: white;">
        <router-view/>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import Cookies from "js-cookie";

export default {
  name: 'Layout',
  data() {
    return {
      admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {},
      tableData: [],
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 10,
        name: '',
        phone: ''
      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      request.get('/user/page', {
        params: this.params
      }).then(res => {
        this.tableData = res.data.list
        this.total = res.data.total
      })
    },
    reset() {
      this.params = {
        pageNum: 1,
        pageSize: 10,
        name: '',
        phone: ''
      }
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.params.pageNum = pageNum
      this.load()
    },
    logout() {
      this.$router.push('/login')
      Cookies.remove('admin')

    }
  }
}
</script>



























