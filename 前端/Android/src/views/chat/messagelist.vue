<template>
<div class="haha">
<el-row >
  <el-col span="24">
    <el-card body-style="{ padding: '5px' }">
      <img src="https://guli-eric.oss-cn-beijing.aliyuncs.com/2022/03/25/df9a7392a83c4df7a1fe0798d1b3d44b5b6fe13411cf7489e4addbb974787779.jpeg" class="image">
      <div style="padding: 5px;">
      </div>
    </el-card>
  </el-col>
</el-row>

<el-row v-for="oneMessage in MessageList" :key="oneMessage.id" offset="1">
  <el-col span="24">
    <el-card body-style="{ padding: '20px' }">

      <el-image v-show="oneMessage.photo != ''"
        style="width: 70%"
        :src="oneMessage.photo"
        fit="fill">
      </el-image>

    <el-image v-show="oneMessage.photo == ''" style="width: 300px; height: 300px">
      <div slot="error" class="image-slot">
        <i class="el-icon-picture-outline"></i>
      </div>
    </el-image>
      <br>
      <br>
      <div style="padding: 5px;">
        <div id="first" v-show="strlen(oneMessage.content) < 20">{{oneMessage.content}}</div>
        <div id="first" v-show="strlen(oneMessage.content) > 20">{{oneMessage.content.slice(0,20) + '...'}}</div>
        <br>
        <div id="first" class="textbox">  
          <el-image 
            style="width: 40px; height: 40px"
            :src="oneMessage.userPhoto"
            fit="fill">
          </el-image>

            {{oneMessage.username}}
        </div>

        <br>
        <div id="first" class="timeclass">{{oneMessage.time}}</div>
        <br>
        <el-button type="primary" @click="detile(oneMessage.id)" size='mini' class="bottom">详情</el-button>
        <!-- <el-button type="primary"  size='mini' class="bottom">点赞</el-button>
        <el-button type="primary" icon="el-icon-share" size='mini'></el-button> -->
        <el-button v-show="userId==oneMessage.userId" type="danger"  size='mini' @click="del(oneMessage.id)">删除</el-button>
        <br>
      
      </div>
    </el-card>
        <br><br><br><br><br>

  </el-col>
</el-row>



<!-- 分页 -->
    <el-pagination
      background
      :current-page="page"
      page-size=6
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="getMessage"
    />



</div>



</template>

<script>

import messageapi from '@/api/chat/messageapi'

export default {
    data() {
      return {
        BASE_API: process.env.BASE_API, // 接口API地址
        MessageList:[],
        total:'',
        text:'',
        page:1,
        userId:''


      }
  },
  
  created() {
      this.userId = sessionStorage['UserID'];;
      this.getMessage(0);
      console.log(this.total)
      console.log(this.MessageList)
  },

  methods:{
    getMessage(current){
        messageapi.getMessage(current)
        .then(response => {
                    this.total = response.data.total;
                    this.MessageList = response.data.list;
        })
    },

    del(id){
        messageapi.del(id)
        .then(response => {
                    //提示
                    this.$message({
                        type: 'success',
                        message: '删除信息成功!'
                    });

                    //跳转到第二步
                   this.$router.go(0);
        })
    },

    strlen(str){
        var len = 0;
        for (var i=0; i<str.length; i++) { 
        var c = str.charCodeAt(i); 
        //单字节加1 
        if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) { 
          len++; 
        } 
        else { 
          len+=2; 
        } 
        } 
      return len;
      },

      detile(id){
          this.$router.push({path:'/chatlist/detail/', query: { messageId: id }});
      }

  }
}
</script>



<style>
  .textbox {
     font-weight :bold;
     font-size:xx-large;
  }

  .timeclass{
    font-size:70%
  }


  
</style>