<script setup>
import { useCourseStore } from '@/stores/course';
import { onMounted, watchEffect } from 'vue';
import { useRoute } from 'vue-router';
import { KakaoMap, KakaoMapPolyline } from 'vue3-kakao-maps';

const courseStore = useCourseStore();
const route = useRoute();
const courseId = route.params.id;

onMounted(() => {
  if (courseId) {
    courseStore.getCourse(courseId);
  } else {
    console.error('Course ID not found in route parameters');
  }
});

watchEffect(() => {
  console.log('Updated latLngList:', courseStore.latLngList);
});
</script>

<template>
  <KakaoMap :lat="37.501292" :lng="127.039604">
    <KakaoMapPolyline :latLngList="courseStore.latLngList" />
  </KakaoMap>
</template>
