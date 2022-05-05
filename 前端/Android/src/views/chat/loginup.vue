<template>

<div id="hahahha">

<el-form label-width="40px" ref="form">

  <el-form-item>
      <!-- <div class="dashboard-text">id:<span v-for="role in roles" :key="role">{{ role }}</span></div> -->
      <el-card body-style="{ padding: '5px' }">
        <img src="https://guli-eric.oss-cn-beijing.aliyuncs.com/2022/03/11/04c108e7d83647989f818a98b1d2d2bb下载.jpg" class="image">
        <div style="padding: 5px;">
          <span>墩墩啦</span>
        </div>
      </el-card>
  </el-form-item>

  <el-form-item label="昵称">
    （必填）
    <el-input
      type="textarea"
      autosize
      placeholder="张三"
      v-model="username">
    </el-input>
  </el-form-item>

   <el-form-item label="账号">
     （必填）
    <el-input
      type="textarea"
      autosize
      placeholder="12345678"
      v-model="account">
    </el-input>
  </el-form-item>

   <el-form-item label="密码">
     （必填）
    <el-input
      type="textarea"
      autosize
      placeholder="12345678"
      v-model="password">
    </el-input>
  </el-form-item>

  <el-form-item label="头像">
    （必填）
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
      placeholder="变态？"
      v-model="sex">
    </el-input>
  </el-form-item>

  <el-form-item label="email">
    <el-input
      type="textarea"
      autosize
      placeholder="110@119.com"
      v-model="email">
    </el-input>
  </el-form-item>

  <el-form-item label="电话">
    <el-input
      type="textarea"
      autosize
      placeholder="110119120"
      v-model="telephone">
    </el-input>
  </el-form-item>

  <el-form-item label="简介">
    <el-input
      type="textarea"
      autosize
      placeholder="说点什么吧
                    
                    "
      v-model="introduction">
    </el-input>
    <!-- <div class="text">{{ content }}</div> -->
  </el-form-item>

  <el-form-item>
    <el-button type="primary" @click="addUser(
        username,
        account,
        password,
        photo,
        introduction,
        sex,
        email,
        telephone)">
        注册</el-button>

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
        photo:''




      }
  },
  
  created() {
      
  },

  methods:{

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
    
    
    //添加说说
        addUser(username,account,password,photo,introduction,sex,email,telephone){

            messageapi.addUser(username,account,password,photo,introduction,sex,email,telephone)
                  .then(response => {
                    //提示
                    this.$message({
                        type: 'success',
                        message: '添加用户成功!'
                    });
                    //跳转到第二步
                    this.$router.push({path:'/chatlist/table'})
                })
        },

        backlist(){
          this.$router.push({path:'/chatlist/table'});
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