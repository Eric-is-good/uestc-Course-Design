<template>
<div class="haha">

  <!-- <div>{{"主人 Message 的 id:  " + fromMessage}}</div>
  <div>{{"回复者本人的 id:  " + userId}}</div>
  <div>{{message}}</div>
  <div>{{list}}</div>

  <div>---------------------------------------------------------------------------</div>
  <br>
  <br>
  <br> -->


<div class="title">说说详情</div>
<br><br><br><br>

<el-row >
  <el-col>
    <el-card body-style="{ padding: '5px' }" class="pics">

      <el-image v-show="message.photo != ''"
        style="width: 70%"
        :src="message.photo"
        fit="fill">
      </el-image>

    <!-- <el-image v-show="message.photo == ''" style="width: 300px; height: 300px">
      <div slot="error" class="image-slot">
        <i class="el-icon-picture-outline"></i>
      </div>
    </el-image> -->
      <br>
      <br>
      <div style="padding: 5px;" class="contents">
        <div id="first">{{message.content}}</div>
        <br>
        <div id="first" class="textbox">  
          <el-image 
            style="width: 70px; height: 70px"
            :src="message.userPhoto"
            fit="fill">
          </el-image>

            &#12288 {{message.username}}

            <br><br>

            <!-- 弹窗添加回复 -->
            <el-button type="primary" @click="dialogTableVisible = true">添加回复</el-button>

            <el-dialog title="添加回复" :visible.sync="dialogTableVisible" width="70%">
                        <el-form label-width="20px" ref="form">
                          只能一张哦

                        <el-form-item>
                            <br>
                            <el-upload
                                class="avatar-uploader"
                                :action="BASE_API+'/chatservice/message/upPic'"
                                :show-file-list="true"
                                :on-success="handleAvatarSuccess"
                                :before-upload="beforeAvatarUpload">
                                <img v-if="photo" :src="photo" class="avatar" width="70%">
                                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                            </el-upload>
                        </el-form-item>

                        <el-form-item>
                          <el-input
                            type="textarea"
                            autosize
                            placeholder="说点什么吧
                                          
                                          "
                            v-model="content">
                          </el-input>
                          <!-- <div class="text">{{ content }}</div> -->
                        </el-form-item>

                        <el-form-item>
                          <el-button type="primary" @click="addReply(userId,content,photo,fromMessage)">发表</el-button>
                        </el-form-item>

                      </el-form>



            </el-dialog>
        </div>

      
      </div>
    </el-card>
  </el-col>
</el-row>


<br><br><br><br>

<br><br><br><br>


<div class="title">回复列表</div>
<br><br><br><br>


<el-row v-for="message in list" :key="message.id">
  <el-col>
    <el-card body-style="{ padding: '5px' }" class="pics">

      <el-image v-show="message.photo != ''"
        style="width: 70%"
        :src="message.photo"
        fit="fill">
      </el-image>

    <!-- <el-image v-show="message.photo == ''" style="width: 50px; height: 50px">
      <div slot="error" class="image-slot">
        <i class="el-icon-picture-outline"></i>
      </div>
    </el-image> -->
      
      <br>
      <div style="padding: 5px;" class="contents2">
        <div id="first" class="contents2">{{message.content}}</div>
        <br>
        <br>
        <br>
        

        <div id="first" class="textbox2">  
          <el-image 
            style="width: 40px; height: 40px"
            :src="message.userPhoto"
            fit="fill">
          </el-image>

            &#12288 {{message.username}}
            <br><br>
            <el-button v-show="userId==message.userId" type="danger"  @click="del(message.id)" size='mini'>删除</el-button>
        
        </div>

      
      </div>
    </el-card>
  </el-col>
</el-row>

</div>



</template>

<script>

import messageapi from '@/api/chat/messageapi'

export default {
    data() {
      return {
        BASE_API: process.env.BASE_API, // 接口API地址
        fromMessage:'',
        userId:'',
        message:'',
        list:[],
        dialogTableVisible: false,
        content:'',
        photo:'',

      }
  },
  
  created() {
      this.fromMessage = this.$route.query.messageId;
      console.log(this.fromMessage);

      this.userId = sessionStorage['UserID'];
      console.log(this.userId);

      this.getMessageDetail(this.fromMessage);
      console.log(this.message);
      console.log(this.list);
  },

  methods:{
    // 获取消息详情
    getMessageDetail(fromMessage){
        messageapi.getMessageDetail(fromMessage)
        .then(response => {
            this.message = response.data.message;
            this.list = response.data.list;
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

    // 添加回复
    addReply(userId,content,photo,fromMessage){

      console.log(content);
      console.log(photo);
      console.log(fromMessage);
      userId = sessionStorage['UserID'];
      console.log(userId);
      this.dialogTableVisible = false
      messageapi.addMessageReply(userId,content,photo,fromMessage)
            .then(response => {
                    //提示
                    this.$message({
                        type: 'success',
                        message: '添加信息成功!\n 请返回页面'
                    });
                    //跳转到第二步
                    this.$router.go(0);
            })
    },

    //上传封面成功调用的方法
        handleAvatarSuccess(res, file) {
            this.photo = res.data.url
        },


    //上传之前调用的方法
        beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg'
            const isLt2M = file.size / 1024 / 1024 < 10

            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!')
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 10MB!')
            }
            return isJPG && isLt2M
        },


  }
    
}
</script>



<style>

.textbox {
     text-align: center;
     font-weight :bold;
     font-size:200%;
     word-break: break-all;
      word-wrap: break-word;
  }

.contents{
  text-align: center;
  font-size:200%;
  word-break: break-all;
  word-wrap: break-word;
}
  
.title{
  text-align: center;
  font-weight :bold;
  font-size:400%;
}

.pics{
  text-align: center;
}

.textbox2 {
     text-align: center;
     font-weight :bold;
     font-size:150%;
     word-break: break-all;
     word-wrap: break-word;
  }

.contents2{
  text-align: center;
  font-size:150%;
  word-break: break-all;
  word-wrap: break-word;
}


.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>