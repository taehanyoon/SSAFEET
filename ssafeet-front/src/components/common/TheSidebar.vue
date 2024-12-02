<template>
  <div>
    <div class="sidebar" :class="{ open: isMenuOpen }">
      <div class="sidebar-content">
        <div class="profile-section text-center py-4" style="margin-left: -150px;">
          <i class="bi bi-person-circle" style="font-size: 60px;"></i>
          <p class="fw-bold mb-0" style="color: black; font-size: 20px;">{{ username }}</p>
        </div>
        <ul class="menu-list px-3">
          <li
            v-for="(item, index) in menuItems"
            :key="index"
            :class="{ active: activeMenu === index, 'border-top': item.name === '마이페이지' || item.name === '설정' }"
            @click="selectMenu(index, item.link)"
            class="menu-item py-2 px-3"
            style="display: inline-flex; align-items: center; padding: 10px 0; width: 100%;"
          >
            <i :class="item.icon" style="padding-left: 15px; padding-right: 20px;"></i>
            <span style="font-size: 1rem;">{{ item.name }}</span>
          </li>
        </ul>
      </div>
    </div>
    <div class="backdrop" v-if="isMenuOpen" @click="toggleSidebar"></div>
  </div>
</template>

<script>
import { ref, onMounted, watch } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { jwtDecode } from 'jwt-decode';

export default {
  name: 'TheSidebar',
  props: {
    isMenuOpen: {
      type: Boolean,
      required: true,
    },
  },
  emits: ['menuSelect', 'closeSidebar'],
  setup(props, { emit }) {
    const activeMenu = ref(0);
    const router = useRouter();
    const route = useRoute();
    const username = ref('김싸피');
    const menuItems = ref([
      { name: "홈", icon: "bi bi-house", link: "/" },
      { name: "마이페이지", icon: "bi bi-card-list", link: "/mypage" },
      { name: "제휴 브랜드", icon: "bi bi-cup-hot", link: "/brand" },
      { name: "산책 코스", icon: "bi bi-person-walking", link: "/course" },
      { name: "설정", icon: "bi bi-gear", link: "/settings" },
    ]);

    const toggleSidebar = () => {
      emit('closeSidebar', false);
    };

    const selectMenu = (index, link) => {
      activeMenu.value = index;
      toggleSidebar();
      router.push(link);
      emit('menuSelect', index);
    };

    const decodeAndSetName = async () => {
      const token = localStorage.getItem('access-token');
      if (token) {
        try {
          const decoded = jwtDecode(token);
          username.value = decoded.name || '김싸피'; // Set username from token, default to '김싸피'
        } catch (error) {
          console.error('Error decoding JWT:', error);
        }
      }
    };

    // Set active menu based on route
    const updateActiveMenu = () => {
      const currentRoute = route.path;
      const activeIndex = menuItems.value.findIndex(item => item.link === currentRoute);
      activeMenu.value = activeIndex !== -1 ? activeIndex : 0;
    };

    // Watch for route changes and update active menu
    watch(route, updateActiveMenu);

    onMounted(() => {
      decodeAndSetName();
      updateActiveMenu(); // Initial check on mounted
    });

    return {
      activeMenu,
      menuItems,
      toggleSidebar,
      selectMenu,
      username,
    };
  },
};
</script>

<style scoped>
.sidebar {
  position: fixed;
  top: 0;
  left: 0;
  width: 250px;
  height: 100%;
  background-color: #f2f2f2;
  transform: translateX(-100%);
  transition: transform 0.3s ease-in-out;
  z-index: 1000;
  overflow-y: auto;
}

.sidebar.open {
  transform: translateX(0);
}

.sidebar-content {
  display: flex;
  flex-direction: column;
  height: 100%;
}

.menu-list {
  list-style: none;
  margin: 0;
  padding: 0 !important;
}

.menu-item {
  cursor: pointer;
  font-size: 2rem;
  color: #85878b;
  padding: 15px 10px !important;
  box-sizing: border-box;
}

.menu-item:hover,
.menu-item.active {
  background-color: #ddd;
  font-weight: bold;
}

.border-top {
  border-top: 1px solid #ddd;
}

.backdrop {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  z-index: 999;
}
</style>
