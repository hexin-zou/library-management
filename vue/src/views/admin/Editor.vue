<template>
  <div style="padding: 20px">
    <div style="font-size: 30px;margin-bottom: 20px">编辑管理员</div>
    <el-form :inline="true" :model="form" style="width:80% ; margin-left: 100px;margin-top: 20px">
      <el-form-item label="用户名" style="margin-left: 100px;margin-top: 20px">
        <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="邮箱" style="margin-left: 100px;margin-top: 20px">
        <el-input v-model="form.email" placeholder="请输入邮箱"></el-input>
      </el-form-item>
      <el-form-item label="联系方式" style="margin-left: 100px;margin-top: 20px">
        <el-input v-model="form.phone" placeholder="请输入联系方式"></el-input>
      </el-form-item>
    </el-form>
    <div style="text-align: center ;margin-top: 30px">
      <el-button round type="info" @click="update">更新</el-button>
    </div>
  </div>

</template>

<script>
import request from "@/utils/request";

export default {
  name: 'Editor',
  data() {
    return {
      form: {}
    }
  },
  created() {
    const id = this.$route.query.id
    request.get("/admin/" + id).then(res => {
      this.form = res.data
    })
  },
  methods: {
    update() {
      request.put("/admin/update", this.form).then(res => {
        if (res.code === '200') {
          this.$notify.success("更新成功")
          this.$router.push("/adminlist")
        } else {
          this.$notify.error(res.msg)
        }
      })
    }
  }
}

</script>
