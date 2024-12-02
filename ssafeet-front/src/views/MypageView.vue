<template>
  <div class="mypage-home">
    <!-- Header Section with Sidebar Button -->
    <header class="header d-flex align-items-center px-3">
      <!-- Sidebar button without the specific button style -->
      <button style="padding-left: 11px; padding-top:33px; font-size: 2rem; color: #85878b; border: none;" @click="toggleMenu">☰</button>
      <h1>마이 페이지</h1>
    </header>

    <!-- Sidebar Component -->
    <TheSidebar :isMenuOpen="isMenuOpen" @closeSidebar="toggleMenu" />

    <!-- Main Content Section -->
    <div class="button-container">
      <!-- Add a class "list-button" to the buttons you want styled -->
      <button class="list-button" @click="goTo('userInfo')">회원정보</button>
      <button class="list-button" @click="goTo('coupons')">기프티콘/쿠폰</button>
      <button class="list-button" @click="goTo('serviceInfo')">서비스 소개</button>
    </div>
    <RouterView />
  </div>
</template>

<script>
import { ref } from 'vue';
import { useRouter } from 'vue-router';  // Import useRouter to access the router
import TheSidebar from "@/components/common/TheSidebar.vue";

export default {
  name: 'MypageHome',
  components: {
    TheSidebar,
  },
  setup() {
    const isMenuOpen = ref(false);
    const router = useRouter();  // Initialize the router

    // Function to toggle the sidebar visibility
    const toggleMenu = () => {
      isMenuOpen.value = !isMenuOpen.value;
    };

    // Navigate to different pages
    const goTo = (page) => {
      router.push({ name: page });  // Use router.push instead of this.$router.push
    };

    return {
      isMenuOpen,
      toggleMenu,
      goTo,
    };
  },
};
</script>

<style scoped>
/* 전체 배경색 설정 */
.mypage-home {
  background-color: #f2f2f2; /* 연한 회색 배경 */
  height: 100vh; /* 화면 전체 높이 */
  box-sizing: border-box; /* 패딩 포함 */
  position: relative;
  font-family: "Nanum Gothic", sans-serif;
}

/* Header */
.header {
  display: flex;
  padding: 5px 15px;  /* Reduced padding */
  height: 50px;  /* Reduced height */
}

h1 {
  font-size: 16px;  /* Reduced font size */
  font-weight: bold;
  color: #85878b;
  margin-left: 20px;
  margin-top: 43px;
}

/* 버튼 컨테이너 스타일 */
.button-container {
  margin-top: 150px; /* 버튼 그룹 전체의 상단 간격 */
  text-align: center; /* 버튼을 가운데 정렬 */
}

/* 스타일을 list-button 클래스에만 적용 */
.list-button {
  display: block;
  margin: 20px auto; /* 버튼 간의 간격을 늘리기 위해 margin-bottom을 조정 */
  padding: 12px 20px; /* 버튼의 내부 여백을 줄여서 크기 축소 */
  font-size: 17px; /* 버튼 텍스트 크기 */
  font-weight: bold; /* 글씨 굵게 */
  cursor: pointer;
  border: none; /* 테두리 제거 */
  border-radius: 8px; /* 둥근 모서리 */
  background-color: #D9D9D9; /* 회색 배경 */
  color: #757575; /* 흰색 텍스트 */
  transition: all 0.3s ease; /* 부드러운 애니메이션 */
  width: 200px; /* 버튼 너비를 줄임 */
  min-width: 200px; /* 최소 너비 설정 */
  max-width: 200px; /* 최대 너비 설정 */
}

/* 버튼 hover 및 active 효과 */
.list-button:hover {
  background-color: #4e4e4e; /* 더 진한 파란색 */
  transform: scale(1.05); /* 버튼 확대 */
}

.list-button:active {
  background-color: #3a3a3a; /* 눌렀을 때 더 진한 색 */
  transform: scale(0.95); /* 버튼 살짝 축소 */
}
</style>
