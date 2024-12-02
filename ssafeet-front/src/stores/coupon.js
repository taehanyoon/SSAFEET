import { ref } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

const REST_API_URL = `http://localhost:8080/api-coupon`

export const useCouponStore = defineStore('coupon', () => {

  const createCoupon = function (coupon) {
    axios({
      url: `${REST_API_URL}/redeem`,
      method: 'POST',
      data: coupon
    })
      .then(() => {
        console.log("완료")
      })
      .catch(() => {
        console.log("실패")
      })
  }
  
  const coupons = ref({})

  const getCoupons = async () => {
    const token = localStorage.getItem('access-token');
    
    if (!token) {
      console.log('No token found');
      return;
    }
  
    try {
      const response = await axios.get(`${REST_API_URL}/coupons`, {
        headers: {
          Authorization: `Bearer ${token}`,
        }
      });
      coupons.value = response.data;
    } catch (error) {
      console.log(token)
      console.error('Error fetching coupons:', error);
    }

  }

  const useCoupon = async (coupon) => {
    if (!coupon || !coupon.id || !coupon.user_id) {
      console.error('Invalid coupon data');
      return;
    }

    const redeemTime = formatDate(coupon.redeem_time);

    try {
      const response = await axios.put(
        `${REST_API_URL}/use`,
        {
          productId: coupon.id,
          userId: coupon.user_id,
          redeemTime: redeemTime,
        },
      );

      alert(response.data);
      await getCoupons();
    } catch (error) {
      console.error('Error using coupon:', error);
      alert('Failed to use coupon');
    }
  };

  const formatDate = (date) => {
    const d = new Date(date);
    const day = ("0" + d.getDate()).slice(-2); // Zero pad day
    const month = ("0" + (d.getMonth() + 1)).slice(-2); // Zero pad month
    const year = d.getFullYear();
    const hours = ("0" + d.getHours()).slice(-2); // Zero pad hours
    const minutes = ("0" + d.getMinutes()).slice(-2); // Zero pad minutes
    const seconds = ("0" + d.getSeconds()).slice(-2); // Zero pad seconds
  
    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
  };

  const places = ref([])

  const getPlaces = async (campus) => {

    try {
      const response = await axios.get(`${REST_API_URL}/places`, {
        params: { campus },
      });
      places.value = response.data;
    } catch (error) {
      console.error('Error fetching places:', error);
    }
  }

  return { createCoupon, coupons, getCoupons, places, getPlaces, useCoupon }
})
