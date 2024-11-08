<template>
  <div style="padding: 20px">
    <div style="font-size: 30px;margin-bottom: 20px">编辑用户</div>
    <el-form :inline="true" :model="form" style="width:80% ; margin-left: 100px;margin-top: 20px">
      <el-form-item label="姓名" style="margin-left: 100px;margin-top: 20px">
        <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
      </el-form-item>
      <el-form-item label="年龄" style="margin-left: 100px;margin-top: 20px">
        <el-input v-model="form.age" placeholder="请输入年龄"></el-input>
      </el-form-item>
      <el-form-item label="性别" style="margin-left: 100px;margin-top: 20px">
          <el-radio v-model="form.sex" label="男">男</el-radio>
          <el-radio v-model="form.sex" label="女">女</el-radio>
      </el-form-item>
      <el-form-item label="联系方式" style="margin-left: 100px;margin-top: 20px">
        <el-input v-model="form.phone" placeholder="请输入联系方式"></el-input>
      </el-form-item>
      <el-form-item label="地址" style="margin-left: 100px ;margin-top: 20px">
        <el-input v-model="form.address" placeholder="请输入地址"></el-input>
      </el-form-item>
    </el-form>
    <div style="text-align: center ;margin-top: 30px">
      <el-button round type="primary" @click="update">更新</el-button>
    </div>
  </div>

</template>

<script>
import request from "@/utils/request";

export default {
  name: 'EditUser',
  data() {
    return {
      form: {
        sex: '男'
      }
    }
  },
  created() {
    const id = this.$route.query.id
    request.get("/user/" + id).then(res => {
      this.form = res.data
    })
  },
  methods: {
    update() {
      request.put("/user/update", this.form).then(res => {
        if (res.code === '200') {
          this.$notify.success("更新成功")
          this.$router.push("/user")
        } else {
          this.$notify.error(res.msg)
        }
      })
    }
  }
}

</script>
