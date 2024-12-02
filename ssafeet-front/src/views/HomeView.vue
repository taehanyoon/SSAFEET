<template>
  <div class="app d-grid">
    <!-- Header -->
    <header class="header d-flex align-items-center justify-content-between text-white px-3">
      <button class="btn btn-lg" style="padding-left: 10px; padding-top:30px; font-size: 2rem; color: #85878b" @click="toggleMenu">☰</button>
      <div class="progress-container flex-grow-1 d-flex align-items-center ms-2" style="padding-top: 20px;">
        <div class="progress w-100" style="height: 1.5rem; border-radius: 50px;">
          <div
            class="progress-bar bg-info"
            role="progressbar"
            :style="{ width: progressBarWidth + '%' }"
          ></div>
          <!-- Text on Progress Bar -->
          <div class="progress-text">{{ points }} P</div>
        </div>
        <span class="progress-label ms-2">아메리카노</span>
      </div>
    </header>

    <!-- Steps Section -->
    <section class="steps-section">
      <h2 class="fw-bold mb-2" style="color: #85878b; text-indent: 40px;">오늘 걸음수</h2>
      <p class="steps text-primary">{{ steps }}</p>
    </section>

    <!-- Bottom Row (1 left and 2 stacked on right) -->
    <section class="bottom-row">
      <!-- Left Column (Calories Section) -->
      <div class="calories-section">
        <p class="mb-2">{{ caloriesBurned }} kcal</p>
        <p class="mb-2">{{ activeMinutes }} min</p>
        <p class="mb-0">{{ distance }} km</p>
      </div>

      <!-- Right Column (Speech Bubble and Character Section) -->
      <div class="right-column">
        <!-- Speech Bubble Section -->
        <div class="speech-section position-relative">
          <img
            :src="bubbleImgSrc"
            alt="Speech Bubble"
            class="speech-bubble-img"
            style="width: 120px;"/>
        </div>

        <!-- Character Section -->
        <div class="character-section">
          <img
            :src="characterImgSrc"
            alt="Character"
            class="character-img img-fluid"
            style="width: 150px;"/>
        </div>
      </div>
    </section>

    <TheSidebar
      :isMenuOpen="isMenuOpen"
      @closeSidebar="toggleMenu"
    />
  </div>
</template>

<script>
import { ref, computed, onMounted } from "vue";
import { useCouponStore } from '@/stores/coupon';
import { jwtDecode } from 'jwt-decode';
import axios from 'axios';
import TheSidebar from "@/components/common/TheSidebar.vue";

export default {
  components: {
    TheSidebar,
  },
  setup() {
    const isMenuOpen = ref(false);
    const activeMenu = ref(0);

    const steps = ref(0);
    const caloriesBurned = ref(153);
    const activeMinutes = ref(25);
    const distance = ref(1.7);
    const points = ref(0);

    const progressBarWidth = computed(() => {
      const stepGoal = 8000;
      return Math.min((steps.value / stepGoal) * 100, 100);
    });

    const toggleMenu = () => {
      isMenuOpen.value = !isMenuOpen.value;
    };

    const couponStore = useCouponStore();

    const decodeAndSetVariables = async () => {
      const token = localStorage.getItem('access-token');
      
      if (token) {
        try {
          const decoded = jwtDecode(token); 
          const studentId = decoded.id;
          const response = await axios.get('http://localhost:8080/api-user/user', {
            params: {
              id: studentId, // Pass the studentId as a query parameter
            },
          });
          steps.value = response.data.currWalk;
          points.value = response.data.point;
        } catch (error) {
          console.error('Error decoding JWT:', error);
        }
      }
    };

    const characterNumber = Math.random() < 0.5 ? 1 : 2; // 50% chance for each
    const characterImgSrc = `../src/assets/character_${characterNumber}.png`;

    const bubbleImgSrc = computed(() => {
      if (steps.value < 8000) {
        const randomBubbleNumber = Math.floor(Math.random() * 5) + 1; // Random number between 1 and 5
        return `../src/assets/bubble_${randomBubbleNumber}.png`;
      } else {
        return `../src/assets/bubble_finish.png`;
      }
    });

    onMounted(() => {
      couponStore.getPlaces();
      decodeAndSetVariables();
    });

    return {
      isMenuOpen,
      activeMenu,
      steps,
      points,
      caloriesBurned,
      activeMinutes,
      distance,
      progressBarWidth,
      toggleMenu,
      couponStore,
      characterNumber,
      characterImgSrc,
      bubbleImgSrc
    };
  },
};
</script>

<style scoped>
/* General Layout */
.app {
  font-family: "Nanum Gothic", sans-serif;
  font-style: normal;
  display: grid;
  grid-template-rows: auto 1fr 1fr; /* Header, Steps, and Bottom Row */
  min-height: 100vh; /* Ensures full height */
  height: 100vh;  /* Ensure no scrolling */
  gap: 10px;
  padding: 0;
  background-color: #f2f2f2;
  overflow: hidden;
}

/* Header */
.header {
  grid-row: 1;
  height: 60px;
  padding: 10px 15px;  /* Ensure proper padding */
}

/* Progress Bar with Wave Animation */
.progress-bar {
  position: relative;
  height: 40px;
  background-color: #ddd;  /* Default background */
  overflow: hidden;
  background: linear-gradient(90deg, #3c93e8 25%, #59c1ac 50%, #3c93e8 75%);
  background-size: 200% 100%;
  animation: wave 3s linear infinite;
  transition: width 0.3s ease-in-out;
}

@keyframes wave {
  0% {
    background-position: 200% 0;
  }
  100% {
    background-position: -200% 0;
  }
}

/* Progress Text */
.progress-text {
  position: absolute;
  top: 40px;
  left: 120px;
  transform: translate(-50%, -50%);
  color: white;
  font-size: 1rem;
  font-weight: bold;
  z-index: 1;  /* Ensure it's above the progress bar */
}

/* Progress Label */
.progress-label {
  display: inline-block;
  width: 75px;
  height: 60px;
  border: 2px solid #85878b;
  border-radius: 50%;
  color: #85878b;
  text-align: center;
  font-size: 1rem;
}

/* Steps Section */
.steps-section {
  grid-row: 2;
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-align: left;
  padding: 10px 20px;  /* Add padding to keep things neat */
}

/* Bottom Row */
.bottom-row {
  grid-row: 3;
  display: grid;
  grid-template-columns: 1fr 1.3fr; /* Left column takes 1fr, right column takes 1.5fr */
  gap: 10px;
  align-items: start;
  padding: 10px 20px;  /* Add padding */
}

/* Calories Section (Left Column) */
.calories-section {
  font-size: 1.8rem;
  font-weight: 700;
  text-align: right;
  padding-right: 20px;
  margin-top: -40px;
  color: #85878b;
}

/* Right Column */
.right-column {
  display: grid;
  grid-template-rows: 1fr 1fr; /* Two components stack vertically */
  gap: 10px;
  align-items: center;
}

/* Speech Section */
.speech-section {
  position: relative;
  left: 80px;
  bottom: 10px;
}

.speech-bubble-img {
  width: 120px;
  position: absolute;
  top: -20px; /* Slightly adjust the position */
}

.speech-text {
  font-size: 0.9rem;
  color: black;
}

/* Character Section */
.character-section {
  text-align: left;
}

.character-img {
  object-fit: contain;
  width: 80px;
}

/* Utility Classes */
.steps {
  font-size: 4rem;
  text-indent: 60px;
  font-weight: bold;
  color: #3c93e8 !important;
}
</style>
