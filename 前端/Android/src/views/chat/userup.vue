<template>

<div id="hahahha">

<el-form label-width="40px" ref="form">

  <el-form-item>
      <!-- <div class="dashboard-text">id:<span v-for="role in roles" :key="role">{{ role }}</span></div> -->
      <el-card body-style="{ padding: '5px' }">
        <img width="250" src="https://guli-eric.oss-cn-beijing.aliyuncs.com/2022/03/25/8091b8b7dc3c4780b7a76f6f1dddc667240432e79ae635c325edbb331fb513d4.jpeg" class="image">
        <div style="padding: 5px;">
          <span>墩墩啦</span>
        </div>
      </el-card>
  </el-form-item>

  <el-form-item label="账号">
    {{account}}
  </el-form-item>

  <el-form-item label="昵称 ">
    <el-input
      type="textarea"
      autosize
      
      v-model="username">
    </el-input>
  </el-form-item>

   <el-form-item label="密码 ">
    <el-input
      type="textarea"
      autosize
      
      v-model="password">
    </el-input>
  </el-form-item>

  <el-form-item label="头像">
    (点击更改)
      <br>
      <el-upload
          class="avatar-uploader"
          :action="BASE_API+'/chatservice/message/upPic'"
          :show-file-list="true"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload">
          <img v-if="photo" :src="photo" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
  </el-form-item>

  <el-form-item label="性别">
    <el-input
      type="textarea"
      autosize
      
      v-model="sex">
    </el-input>
  </el-form-item>

  <el-form-item label="email">
    <el-input
      type="textarea"
      autosize
      
      v-model="email">
    </el-input>
  </el-form-item>

  <el-form-item label="电话">
    <el-input
      type="textarea"
      autosize
      
      v-model="telephone">
    </el-input>
  </el-form-item>

  <el-form-item label="简介">
    <el-input
      type="textarea"
      autosize
      
      v-model="introduction">
    </el-input>
    <!-- <div class="text">{{ content }}</div> -->
  </el-form-item>

  <el-form-item>
    <el-button type="primary" @click="upUser(
        id,
        username,
        account,
        password,
        photo,
        introduction,
        sex,
        email,
        telephone)">
        修改</el-button>

    <el-button type="primary" @click="backlist()">取消</el-button>
  </el-form-item>

</el-form>

</div>

</template>



<script>



import messageapi from '@/api/chat/messageapi'

export default {
    data() {
      return {
        BASE_API: process.env.BASE_API, // 接口API地址
        username:'',
        account:'',
        password:'',
        introduction:'',
        sex:'',
        email:'',
        telephone:'',
        photo:'',
        id:'',




      }
  },
  
  created() {
      this.id = sessionStorage['UserID'];
      this.getUser(this.id);
  },

  methods:{

    //上传封面成功调用的方法
        handleAvatarSuccess(res, file) {
            this.photo = res.data.url;
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
    
    
    //修改个人信息
        upUser(id,username,account,password,photo,introduction,sex,email,telephone){

            messageapi.upUser(id,username,account,password,photo,introduction,sex,email,telephone)
                  .then(response => {
                    //提示
                    this.$message({
                        type: 'success',
                        message: '修改用户成功!'
                    });
                    //跳转到第二步
                    this.$router.push({path:'/chatlist/table'})
                })
        },

        backlist(){
          this.$router.push({path:'/chatlist/table'});
        },

        // 获取用户信息，根据 id
        getUser(userId){
              messageapi.getUser(userId)
              .then(response => {
                        this.username = response.data.user.username;
                        this.account = response.data.user.account;
                        this.password = response.data.user.password;
                        this.introduction = response.data.user.introduction;
                        this.sex = response.data.user.sex;
                        this.email = response.data.user.email;
                        this.telephone = response.data.user.telephone;
                        this.photo = response.data.user.photo;

                        
            })
        }

  }
}
</script>



<style>
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