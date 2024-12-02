<template>
  <div>
    <router-view></router-view>
  </div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue';
import { useRouter } from 'vue-router';
import { useGpsStore } from "@/stores/gps";

const router = useRouter();
const store = useGpsStore();

const location = ref(null);
const studentId = 1211111;

const createGps = () => {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(
      (position) => {
        location.value = {
          lat: position.coords.latitude,
          lng: position.coords.longitude,
        };
        const gps = {
          id: studentId,
          lat: location.value.lat,
          lng: location.value.lng,
        };
        console.log(gps);
        store.createGps(gps);
      }
    );
  } else {
    console.log("브라우저 지원 X");
  }
};

let gpsInterval;

onMounted(() => {
  const accessToken = localStorage.getItem('access-token');
  if (!accessToken) {
    router.push('/signup');
    return;
  }

  createGps();
  gpsInterval = setInterval(createGps, 10000);
});

onBeforeUnmount(() => {
  clearInterval(gpsInterval);
});
</script>

<style>
body {
  font-family: "Nanum Gothic", sans-serif;
  font-weight: 400;
  font-style: normal;
}
</style>
