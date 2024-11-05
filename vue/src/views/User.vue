<template>
<div>
  <div style="margin-bottom:20px;margin-top:5px;margin-left: 5px">
    <el-input style="width:240px" placeholder="请输入名称" v-model="params.name"></el-input>
    <el-input style="width:240px; margin-left: 5px" placeholder="请输入联系方式" v-model="params.phone"></el-input>
    <el-button style="margin-left: 5px" type="info" @click="load"><i class=" el-icon-search"></i>搜索</el-button>
    <el-button style="margin-left: 5px" type="info"  @click="reset"><i class=" el-icon-refresh"></i>重置</el-button>
  </div>

  <el-table :data="tableData" stripe style="margin-top:0">
    <!--stripe:隔行变色-->
    <el-table-column type="selection" width="60"></el-table-column>
    <el-table-column prop="id" label="编号"></el-table-column>
    <el-table-column prop="name" label="名称"></el-table-column>
    <el-table-column prop="username" label="会员卡号"></el-table-column>
    <el-table-column prop="age" label="年龄"></el-table-column>
    <el-table-column prop="address" label="地址"></el-table-column>
    <el-table-column prop="phone" label="联系方式"></el-table-column>
    <el-table-column prop="sex" label="性别"></el-table-column>
    <el-table-column prop="createtime" label="创建时间"></el-table-column>
    <el-table-column prop="updatetime" label="更新时间"></el-table-column>
    <el-table-column fixed="right" label="操作" width="100">
      <template slot-scope ="scope">
        <el-button round type="info" @click="$router.push('/edit?id=' + scope.row.id)">编辑</el-button>
      </template>
      </el-table-column>
    <el-table-column fixed="right" label="操作" width="100">
      <template slot-scope ="scope">
          <el-popconfirm
              confirm-button-text='Yes'
              cancel-button-text='No'
              icon="el-icon-info"
              icon-color="black"
              title="Sure？"
              @confirm="del(scope.row.id)"
          >
            <el-button round slot="reference">删除</el-button>
          </el-popconfirm>
        </template>
    </el-table-column>
  </el-table>

  <!--  分页-->

  <div style="margin-top: 20px">
    <el-pagination
        :current-page="params.pageNum"
        :page-size="params.pageSize"
        layout="prev, pager, next"
        @current-change="handleCurrentChange"
        :total="total">
    </el-pagination>
  </div>
</div>
</template>

<script>

import request from "@/utils/request";

export default {
  name: 'UserView',
  data() {
    return {
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
      this.$notify.success('重置成功')
    },
    handleCurrentChange(pageNum) {
      this.params.pageNum = pageNum
      this.load()
    },
    del(id) {
      request.delete('/user/delete/'+ id).then(res => {
        if (res.code === '200') {
          this.$notify.success('再见')
          this.load()
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
  }
}
</script>




<!--    <div style="margin-top: 20px">-->
<!--      <el-pagination-->
<!--          background-->
<!--          :page-size="3"-->
<!--          layout="prev, pager, next"-->
<!--          :total="30">-->
<!--      </el-pagination>-->
<!--    </div>-->
<!--  </div>-->

<!--</template>-->

<!--<script>-->

<!--export default {-->
<!--  name: 'HomeView',-->
<!--  data() {-->
<!--    return {-->
<!--      tableData: []-->
<!--    }-->
<!--  },-->
<!--    created() {-->
<!--      this.load()-->
<!--    },-->
<!--    methods: {-->
<!--      load() {-->
<!--      fetch('http://localhost:9090/user/list').then(res =>res.json()).then(res =>{-->
<!--        console.log(res)-->
<!--        this.tableData = res-->
<!--      })-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->
