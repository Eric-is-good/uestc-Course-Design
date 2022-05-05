<template>
<div class="title">
    AI 正在开发
</div>
</template>



<script>



import ai from '@/api/chat/ai'

export default {
    data() {
      return {

      }
  },
  
  created() {
      
  },

  methods:{

     
  }
}
</script>



<style>
  

  .title{
  text-align: center;
  font-weight :bold;
  font-size:400%;
}

.textbox {
     text-align: center;
     font-weight :bold;
     font-size:200%;
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