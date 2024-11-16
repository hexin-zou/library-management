<template>
  <div style="width: 500px; height: 400px; background-color: white; border-radius: 10px; margin: 150px auto; padding:50px">
    <div style="margin: 30px; text-align: center; font-size: 30px; font-weight: bold; color: darkslategrey">登录</div>
    <el-form :model="admin" :rules="rules" ref="loginForm" style="margin-right:70px" label-width="80px">
      <el-form-item prop="username">
        <el-input placeholder="请输入账号" prefix-icon="el-icon-user" size="medium" v-model="admin.username"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input placeholder="请输入密码" show-password prefix-icon="el-icon-lock" size="medium" v-model="admin.password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button style="width: 100%" size="medium" type="info" @click="login">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import request from "@/utils/request";
import Cookies from "js-cookie";

export default {
  name: 'Login',
  data() {
    return {
      admin: {},
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'}
        ],
        password: [
          {required: true, trigger: 'blur'},
          {min: 3, max: 10, message: '长度在3-10个字符', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    login() {
      this.$refs['loginForm'].validate((valid) => {
        if (valid) {
          request.post('/admin/login', this.admin).then(res => {
            if (res.code === '200') {
              this.$notify.success("登录成功")

                Cookies.set('admin',JSON.stringify(res.data))  //将后台返回的JSON对象转成字符串存到cookie中

              this.$router.push('/')
            } else {
              this.$notify.error(res.msg)
            }
          })
        }
      })
    }
  }
}

</script>