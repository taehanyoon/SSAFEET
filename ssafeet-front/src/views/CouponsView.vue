<template>
  <div>
    <h1 class="title">쿠폰/기프티콘</h1>
    <div v-if="couponStore.coupons.length" class="coupon-list">
      <div v-for="(coupon, index) in couponStore.coupons" :key="index" class="coupon-card">
        <h3 class="coupon-name">{{ coupon.name }}</h3>
        <img
              :src="`/images/products/${coupon.description}`"
              alt="Product Image"
              class="card-image"
            />
        <button class="redeem-btn" @click="redeemCoupon(coupon)">Redeem</button>
      </div>
    </div>
    <p v-else>보유하고 있는 쿠폰이 없습니다.</p>
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useCouponStore } from '@/stores/coupon';

const couponStore = useCouponStore();

onMounted(async () => {
  await couponStore.getCoupons();
});

const redeemCoupon = async (coupon) => {
  await couponStore.useCoupon(coupon);
};
</script>

<style scoped>
.title {
  text-align: center;
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px;
  color: #333;
}

.coupon-list {
  display: flex;
  flex-direction: column;
  gap: 20px; /* Vertical gap between cards */
  padding: 10px;
}

.coupon-card {
  background-color: #fff;
  border-top: 1px solid #ddd; /* Border only at the top */
  border-bottom: 1px solid #ddd; /* Border only at the bottom */
  padding: 20px;
  width: 100%; /* Full width for each card */
  box-sizing: border-box; /* Ensures padding is included in the width */
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.coupon-name {
  font-size: 18px;
  font-weight: bold;
  margin: 0 0 10px 0;
  color: #333;
}

.coupon-description {
  font-size: 14px;
  color: #555;
  margin-bottom: 20px;
}

.redeem-btn {
  padding: 10px;
  background-color: #007bff;
  color: white;
  border: none;
  font-size: 14px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.redeem-btn:hover {
  background-color: #0056b3;
}

@media (max-width: 768px) {
  .coupon-card {
    padding: 15px;
  }

  .redeem-btn {
    padding: 8px;
  }
}
</style>
