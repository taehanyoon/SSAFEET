<template>
  <div class="brand-view">
    <!-- Header Section -->
    <header class="header">
      <button style="margin-left: -194px; padding-top: 17px; font-size: 2rem; color: #85878b; border: none;" @click="toggleMenu">☰</button>
      <h1>제휴 브랜드</h1>
    </header>

    <TheSidebar :isMenuOpen="isMenuOpen" @closeSidebar="toggleMenu" />

    <!-- BrandMap Section -->
    <div class="map-container" :class="{ 'map-overlay': isPopupOpen }">
      <BrandMap />
    </div>

    <!-- Cards List Section -->
    <div class="cards-container">
      <div
        class="card"
        v-for="place in couponStore.places"
        :key="place.id"
        @click="openPopup(place)"
      >
        <div class="card-info" style="display: inline-flex; align-items: center;">
          <i
            class="bi bi-geo-alt"
            style="color: #59c1ac; font-size: 2rem; margin-right: 10px;"
          ></i>
          <span style="font-weight: 700;">{{ place.name }}</span>
        </div>
      </div>
    </div>

    <BrandDetail v-if="isPopupOpen" :id="selectedPlace.id" @close="closePopup" style="font-weight: 700;">
      <h2>{{ selectedPlace.name }}</h2>
      <p>{{ selectedPlace.url }}</p>
      <p>{{ selectedPlace.address }}</p>
    </BrandDetail>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import { useCouponStore } from "@/stores/coupon";
import BrandMap from "@/components/brand/BrandMap.vue";
import BrandDetail from "@/components/brand/BrandDetail.vue";
import TheSidebar from "@/components/common/TheSidebar.vue";

export default {
  name: "BrandView",
  components: {
    BrandMap,
    BrandDetail,
    TheSidebar,
  },
  setup() {
    const couponStore = useCouponStore();
    const isPopupOpen = ref(false);
    const selectedPlace = ref(null);
    const isMenuOpen = ref(false);

    const toggleMenu = () => {
      isMenuOpen.value = !isMenuOpen.value;
    };

    const openPopup = (place) => {
      selectedPlace.value = place;
      isPopupOpen.value = true;
    };

    const closePopup = () => {
      isPopupOpen.value = false;
    };

    onMounted(() => {
      couponStore.getPlaces();
    });

    return {
      couponStore,
      isPopupOpen,
      isMenuOpen,
      selectedPlace,
      toggleMenu,
      openPopup,
      closePopup,
    };
  },
};
</script>

<style scoped>
.brand-view {
  display: flex;
  flex-direction: column;
  align-items: center;
  font-family: "Nanum Gothic", sans-serif;
  background-color: #f2f2f2;
  min-height: 100vh;
}

h1 {
  font-size: 16px;  /* Reduced font size */
  font-weight: bold;
  color: #85878b;
  margin-left: -133px;
  margin-top: -33px;
}

.title {
  font-size: 2rem;
  color: #333;
}

.map-container {
  width: 90%;
  max-width: 1200px;
  height: 30vh; /* Adjust the size of the map */
  margin: 20px 0;
  border: 2px solid #ddd;
  border-radius: 20px;
  overflow: hidden;
  transition: background-color 0.3s ease;
}

.map-overlay {
  background: rgba(0, 0, 0, 0.5);
  pointer-events: none;
  z-index: 1;
}

.cards-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 20px;
  margin-top: 20px;
}

.card {
  width: 350px;
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  cursor: pointer;
}

.card-info {
  padding: 15px;
}

.brand-detail-overlay {
  z-index: 1000; /* Make sure the popup is always on top */
}
</style>
