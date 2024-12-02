import { ref } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

const REST_API_URL = `http://localhost:8080/api-gps`

export const useGpsStore = defineStore('gps', () => {

  const createGps = function (gps) {
    axios({
      url: `${REST_API_URL}/track-steps`,
      method: 'POST',
      data: gps
    })
      .then(() => {
        console.log("완료")
      })
      .catch(() => {
        console.log("실패")
      })
  }
  
  const gps = ref({})

  return { createGps, gps }
})
