import request from '@/utils/request'

export function login(account, password) {
  return request({
    url: 'chatservice/user/login',
    method: 'post',
    data: {
      account,
      password
    }
  })
}

export function getInfo(token) {
  return request({
    url: 'chatservice/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: 'chatservice/user/logout',
    method: 'get'
  })
}
