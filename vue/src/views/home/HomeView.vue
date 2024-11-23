<template>
  <div style="margin-left: 10px;font-size: 15px">
    <div class="item"> 用户名: {{admin.username}}</div>
    <div class="item"> 手机号: {{admin.phone}}</div>
    <div class="item"> 邮箱地址: {{admin.email}}</div>
  </div>
</template>

<script>
import Cookies from "js-cookie";
import request from "@/utils/request";

export default {
  name: 'HomeView',
  data() {
    return {
      admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {}
    }
  }
  // created() {
  //   if (!this.admin) {
  //     this.load()
  //   }
  // },
  // methods: {
  //   load() {
  //     request.get('/admin/' + this.admin.id).then(res => {
  //       this.admin = res.data
  //     })
  //   },
  // }
}
</script>
<style>
.item {
  margin: 10px 0;
}
</style>

