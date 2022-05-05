import request from '@/utils/request'
export default {
    //1 添加说说
    addMessage(userId,content,photo) {
        return request({
            url: '/chatservice/message/addMessage',
            method: 'post',
            data:{
                userId,content,photo
            }
          })
    },

    //2 查询说说
    getMessage(current) {
        return request({
            url: '/chatservice/message/getPageMessageList/'+ current,
            method: 'get'
          })
    },

    //3 查询说说详情
    getMessageDetail(messageId) {
        return request({
            url: '/chatservice/message/getMessageDetail/'+ messageId,
            method: 'get'
          })
    },

    //4 添加说说回复
    addMessageReply(userId,content,photo,fromMessage) {
        return request({
            url: '/chatservice/message/addReply',
            method: 'post',
            data:{
                userId,content,photo,fromMessage
            }
          })
    },

    //5 添加用户
    addUser(username,account,password,photo,introduction,sex,email,telephone){
        return request({
            url: '/chatservice/user/addUser',
            method: 'post',
            data:{
                username,account,password,photo,introduction,sex,email,telephone
            }
          })
    },

    //6 修改用户
    upUser(id,username,account,password,photo,introduction,sex,email,telephone){
        return request({
            url: '/chatservice/user/updateUser',
            method: 'post',
            data:{
                id,username,account,password,photo,introduction,sex,email,telephone
            }
          })
    },

    //7 获取用户信息
    getUser(id){
        return request({
            url: '/chatservice/user/getUser/'+ id,
            method: 'get'
          })
    },

    //8 删除说说
    del(messageId){
        return request({
            url: '/chatservice/message/delMessage/' + messageId,
            method: 'delete'
          })
    }
}