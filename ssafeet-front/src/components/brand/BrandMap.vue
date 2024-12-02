<script setup>
import { ref, onMounted } from 'vue';
import { useCouponStore } from '@/stores/coupon';
import { KakaoMap, KakaoMapMarker } from 'vue3-kakao-maps';
import { jwtDecode } from 'jwt-decode';

const campuses = {
  서울: { lat: 37.501299, lng: 127.039609 },
  대전: { lat: 36.355496, lng: 127.298458 },
  구미: { lat: 36.107129, lng: 128.416221 },
  광주: { lat: 35.204191, lng: 126.807166 },
  부울경: { lat: 35.096288, lng: 128.853833 },
};

const deflat = ref(37.501299);
const deflng = ref(127.039609);

const couponStore = useCouponStore();

const decodeAndSetCampus = async () => {
  const token = localStorage.getItem('access-token');
  
  if (token) {
    try {
      const decoded = jwtDecode(token); 
      const userCampus = decoded.campus;
      
      if (campuses[userCampus]) {
        deflat.value = campuses[userCampus].lat;
        deflng.value = campuses[userCampus].lng;
        couponStore.getPlaces(userCampus);
      }
    } catch (error) {
      console.error('Error decoding JWT:', error);
    }
  }
};

onMounted(() => {
  decodeAndSetCampus();
});
</script>

<template>
  <KakaoMap :lat="deflat" :lng="deflng">
    <KakaoMapMarker v-for="place in couponStore.places" :key="place.id" :lat="place.lat" :lng="place.lng">
      <template v-slot:infoWindow>
        <div style="padding: 10px">{{ place.name }}</div>
      </template>
    </KakaoMapMarker>
  </KakaoMap>
</template>
