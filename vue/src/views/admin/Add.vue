<template>
  <div style="padding: 20px">
    <div style="font-size: 30px;margin-bottom: 20px">新增管理员</div>
    <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm" style="width:80% ; margin-left: 100px;margin-top: 20px">
      <el-form-item label="用户名" style="margin-left: 100px;margin-top: 20px">
        <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="联系方式" style="margin-left: 100px;margin-top: 20px" prop="phone">
        <el-input v-model="form.phone" placeholder="请输入联系方式"></el-input>
      </el-form-item>
      <el-form-item label="邮箱" style="margin-left: 100px ;margin-top: 20px" prop="email">
        <el-input v-model="form.email" placeholder="请输入邮箱"></el-input>
      </el-form-item>

    </el-form>
    <div style="text-align: center ;margin-top: 20px">
      <el-button type="primary" @click="save">提交</el-button>
    </div>
  </div>
</template>


<script>
import request from '@/utils/request'

export default {
  name: 'Add',
  data() {
    const check = (rules, value, callback) => {
      if (!value) {
        return callback(new Error('用户名不能为空'));
      }
      if (parseInt(value)>3||parseInt(value)<=15){
        return callback(new Error('请输入合理的用户名'));
      }
      callback();
      // setTimeout(() => {
      //   if (!Number.isInteger(value)) {
      //     callback(new Error('请输入数字值'));
      //   }
      // }, 0);
    };
    const checkPhone = (rules, value, callback) => {
      if (!value) {
        return callback(new Error('联系方式不能为空'));
      }
      if (!/^[1][3,4,5,6,7,8,9][0-9]{9}$/.test(value)) {
        return callback(new Error('请输入合法的手机号'));
      }
      callback();
    };
    return {
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'}
        ],

        phone: [
          {validator: checkPhone, trigger: 'blur'}
        ]
      }
    }
  },
  methods:
      {
        save() {
          this.$refs.ruleForm.validate((valid)=> {
            request.post('/admin/save', this.form).then(res => {
              if (res.code === '200') {
                this.$notify.success('新增成功')
                this.$res['ruleFrom'].resetFields()
              } else {
                this.$notify.error(res.msg)
              }
              this.$router.push("/admin")
            })
          })

        }
      }
}
</script>
