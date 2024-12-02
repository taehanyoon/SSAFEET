import { ref } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

const REST_API_URL = `http://localhost:8080/api-course`

export const useCourseStore = defineStore('course', () => {

  const latLngList = ref([])

  const getCourse = function (id) {
    axios.get(`${REST_API_URL}/${id}`)
      .then((response) => {
        if (response.data && Array.isArray(response.data)) {
          latLngList.value = response.data.map(gps => ({
            lat: gps.lat,
            lng: gps.lng
          }))
        } else {
          console.error('Invalid response data:', response.data)
        }
      })
  }

  return { getCourse, latLngList }
})
