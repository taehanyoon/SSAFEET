import { ref } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

const REST_API_URL = `http://localhost:8080/api-user`

export const useUserStore = defineStore('user', () => {

  const createUser = function (user) {
    axios({
      url: `${REST_API_URL}/signup`,
      method: 'POST',
      data: user
    })
      .then(() => {
        console.log("완료")
      })
      .catch(() => {
        console.log("실패")
      })
  }
  
  const user = ref({})

  const loginUser = ref(null)

  const userLogin = function(phone, password){
    axios.post(`${REST_API_URL}/login`, {
      phone,
      password,
    })
    .then((res)=>{
      console.log(res)
      localStorage.setItem('access-token', res.data['access-token'])

      const token = res.data['access-token'].split('.')
      const name = JSON.parse(atob(token[1]))['name']

      loginUser.value = name

      // router.push({name: 'boardList'})

    })
    .catch((err)=>{
      console.log(err)
      // router.push({name: 'home'})
    })
  }

  return { createUser, user, loginUser, userLogin }
})
