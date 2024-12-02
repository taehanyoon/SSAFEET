import SignupView from '@/views/SignupView.vue'
import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import CourseView from '@/views/CourseView.vue'
import CourseDetail from '@/components/course/CourseDetail.vue'
import UserLogin from '@/components/user/UserLogin.vue'
import MypageView from '@/views/MypageView.vue';
import UserInfoView from '@/views/UserInfoView.vue';
import ServiceInfoView from '@/views/ServiceInfoView.vue';
import CouponsView from '@/views/CouponsView.vue'
import BrandMap from '@/components/brand/BrandMap.vue'
import BrandView from '@/views/BrandView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/signup',
      name: 'signup',
      component: SignupView,
    },
    {
      path: '/course',
      name: 'course',
      component: CourseView,
      children: [
        {
          path: ':id',
          name: 'course-detail',
          component: CourseDetail,
        }
      ]
    },
    {
      path: "/login",
      name: "login",
      component: UserLogin,
    },
    {
      path: "/brand",
      name: "brand",
      component: BrandView,
      children: [
        {
          path: "map",
          name: "brand-map",
          component: BrandMap,
        },
      ]
    },
    {
      path: '/mypage',
      name: 'mypage',
      component: MypageView,
    },
    {
      path: '/mypage/userinfo',
      name: 'userInfo',
      component: UserInfoView,
    },
    {
      path: '/mypage/coupons',
      name: 'coupons',
      component: CouponsView,
    },
    {
      path: '/mypage/serviceinfo',
      name: 'serviceInfo',
      component: ServiceInfoView,
    }
  ],
})

export default router
