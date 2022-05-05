<template>
<div id="haha">
<div class="title">五言律诗生成器</div>

<br>
<br>
<el-input
      type="textarea"
      autosize
      placeholder="                             给个标题吧（不能为空，最好为传统题材，例如 战争）
                    "
      class="textbox"
      v-model="title">
</el-input>
<br><br>
<div class="textbox">
<el-button  :loading="loading" type="primary" @click="getFive(title)" class="button">AI 创作五言律诗</el-button>
</div>

<br><br>
<!-- <div class="contents">{{reply}}</div> -->
<br><br>
<el-card class="contents">
  <!-- {{reply}}<br><br> -->
  {{reply.slice(0,6)}} <br><br>
  {{reply.slice(7,12)}} <br><br>
  {{reply.slice(13,18)}} <br><br>
  {{reply.slice(19,25)}} <br><br>
</el-card>

<br><br>
<el-row >
  <el-col span="24">
    <el-card body-style="{ padding: '5px' }">
      <img src="https://guli-eric.oss-cn-beijing.aliyuncs.com/2022/03/25/11a3f53b43bf41fd83edd6945a2fc9564f41ed17d6e3e618943ed5c68681b4c3.jpeg" class="image" >
      <div style="padding: 5px;">
      </div>
    </el-card>
  </el-col>
</el-row>
</div>
</template>



<script>



import ai from '@/api/chat/ai'

export default {
    data() {
      return {
        BASE_API: '', // 接口API地址
        title:'',
        reply:'',
        loading: false,

      }
  },
  
  created() {
      
  },

  methods:{

     getFive(title){
        this.loading = true;
        ai.getFive(title)
            .then(response => {
                    //提示
                    this.loading = false;
                    this.$message({
                        type: 'success',
                        message: '成功!'
                    });
                    this.reply = response.data.peom;
                    console.log(this.reply);
                })
      },
  }
}
</script>



<style>
  

  .title{
  text-align: center;
  font-weight :bold;
  font-size:300%;
}

.textbox {
     text-align: center;
     font-weight :bold;
     font-size:150%;
     word-break: break-all;
      word-wrap: break-word;
  }

.contents{
  text-align: center;
  font-size:150%;
  word-break: break-all;
  word-wrap: break-word;
}

.button{
  text-align: center;
  font-size:100%;
  word-break: break-all;
  word-wrap: break-word;
}

</style>