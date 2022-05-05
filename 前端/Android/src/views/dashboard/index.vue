<template>
  <div class="dashboard-container">
    <div class="dashboard-text">name:{{ name }}</div>
    <div class="dashboard-text">id:<span v-for="role in roles" :key="role">{{ role }}</span></div>
    <el-button type="primary" @click="setid(roles)">确定登录</el-button>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name',
      'roles'
    ])
  },
  created() {
      
  },

  methods:{
     setid(id){
        this.$store.state.myid = id;
        sessionStorage.setItem("UserID", id);
        this.$router.push({path:'/chatlist/table'})
     }
  }
  
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
