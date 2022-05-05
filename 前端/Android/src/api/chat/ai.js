import request from '@/utils/requestai'
export default {
    //1 调用AI续写
    getFive(title) {
        return request({
            url: '/chatservice/ai/getFivePoem',
            method: 'post',
            data:{
                title
            }
          })
    },
}