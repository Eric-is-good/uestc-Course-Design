<template>


<el-form label-width="20px" ref="form">

  <el-form-item>
      <!-- <div class="dashboard-text">id:<span v-for="role in roles" :key="role">{{ role }}</span></div> -->
      <el-card body-style="{ padding: '0px' }">
        <img width="250" src="https://guli-eric.oss-cn-beijing.aliyuncs.com/2022/03/25/42d0edc088ae4dae932ddf0fdbd00d1b截图未命名.jpg" class="image">
        <div style="padding: 5px;">
          
        </div>
      </el-card>
  </el-form-item>

  <el-form-item>
      <br>
      只能一张哦, 图片出现后才上传成功
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
    <el-button type="primary" @click="addMessage(userId,content,photo)">发表</el-button>
    <el-button type="primary" @click="backlist()">取消</el-button>
  </el-form-item>

</el-form>


</template>



<script>



import messageapi from '@/api/chat/messageapi'

export default {
    data() {
      return {
        BASE_API: process.env.BASE_API, // 接口API地址
        userId:'',
        content: '',
        photo: '',
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
        addMessage(userId,content,photo){
            userId = sessionStorage['UserID'];;
            console.log(userId);
            messageapi.addMessage(userId,content,photo)
            .then(response => {
                    //提示
                    this.$message({
                        type: 'success',
                        message: '添加信息成功!'
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