<template>
  <div style="padding: 20px">
    <div style="font-size: 30px;margin-bottom: 20px">新增用户</div>
    <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm" style="width:80% ; margin-left: 100px;margin-top: 20px">
      <el-form-item label="姓名" style="margin-left: 100px;margin-top: 20px" prop="name">
        <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
      </el-form-item>
      <el-form-item label="用户名" style="margin-left: 100px;margin-top: 20px">
        <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="年龄" style="margin-left: 100px;margin-top: 20px" prop="age">
        <el-input v-model="form.age" placeholder="请输入年龄"></el-input>
      </el-form-item>
      <el-form-item label="性别" style="margin-left: 100px;margin-top: 20px">
        <el-radio v-model="form.sex" label="男">男</el-radio>
        <el-radio v-model="form.sex" label="女">女</el-radio>
      </el-form-item>
      <el-form-item label="联系方式" style="margin-left: 100px;margin-top: 20px" prop="phone">
        <el-input v-model="form.phone" placeholder="请输入联系方式"></el-input>
      </el-form-item>
      <el-form-item label="地址" style="margin-left: 100px ;margin-top: 20px">
        <el-input v-model="form.address" placeholder="请输入地址"></el-input>
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
  name: 'AddUser',
  data() {
    const checkAge = (rules, value, callback) => {
      if (!value) {
        return callback(new Error('年龄不能为空'));
      }
      if (!/^[0-9]+$/.test(value)){
        return callback(new Error('请输入数字值'));
      }
      if (parseInt(value)>150||parseInt(value)<=0){
        return callback(new Error('请输入合理的年龄'));
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
        return callback(new Error('请输入合的手机号'));
      }
      callback();
    };
    return {
      form: {
        sex: '男'
      },
      rules: {
        name: [
          {required: true, message: '请输入姓名', trigger: 'blur'}
        ],
        age:
            [
              {validator: checkAge, trigger: 'blur'}
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
            request.post('/user/save', this.form).then(res => {
              if (res.code === '200') {
                this.$notify.success('新增成功')
              } else {
                this.$notify.error(res.msg)
              }
              this.$router.push("/user")
            })
          })

        }
      }
}
</script>
