<template>
  <div>
    <div class="overlay" @click="closePopup">
      <div class="popup" @click.stop>
        <h3>상품 목록</h3>

        <!-- Display the fetched data as a list of cards -->
        <div v-if="products.length > 0" class="cards-container">
          <div
            class="card"
            v-for="(product, index) in products"
            :key="index"
            :class="{ selected: selectedCardIndex === index }"
            @click="selectCard(index)"
          >
            <img
              :src="`/images/products/${product.description}`"
              alt="Product Image"
              class="card-image"
            />
            <div class="card-info">
              <h3>{{ product.name }}</h3>
              <p>{{ product.price }}</p>
            </div>
          </div>
        </div>

        <!-- Show loading message if the data is not yet loaded -->
        <div v-else>
          <p>Loading products...</p>
        </div>

        <!-- Button Container -->
        <div class="button-container">
          <button class="action-btn" @click="showBuyPopup" style="font-weight: 700;">구매하기</button>
          <button class="close-btn" @click="showTemporaryPopup" style="font-weight: 700;">목표 설정하기</button>
        </div>
      </div>
    </div>

    <!-- Buy Screen Popup -->
    <div v-if="isBuyPopupVisible" class="buy-popup">
      <p style="margin-top: 15px;">맛있게 드세요!</p>
    </div>

    <!-- Temporary Screen Popup -->
    <div v-if="isTemporaryPopupVisible" class="temporary-popup">
      <p style="margin-top: 15px;">목표가 설정되었습니다!</p>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "BrandDetail",
  props: {
    id: {
      type: Number,
      required: true, // Expecting the id to be passed as a prop
    },
  },
  data() {
    return {
      products: [],
      selectedCardIndex: null, // Track the selected card index
      isBuyPopupVisible: false, // Track visibility of the buy popup
      isTemporaryPopupVisible: false, // Track visibility of the temporary popup
    };
  },
  methods: {
    closePopup() {
      this.$emit("close");
    },
    fetchProducts() {
      axios
        .get(`http://localhost:8080/api-coupon/products?id=${this.id}`)
        .then((response) => {
          this.products = response.data;
        })
        .catch((error) => {
          console.error("Error fetching products:", error);
        });
    },
    selectCard(index) {
      this.selectedCardIndex = index; // Set the selected card index
    },
    showBuyPopup() {
      this.isBuyPopupVisible = true; // Show the popup
      setTimeout(() => {
        this.isBuyPopupVisible = false; // Hide the popup after 3 seconds
      }, 2000);
    },
    showTemporaryPopup() {
      this.isTemporaryPopupVisible = true; // Show the popup
      setTimeout(() => {
        this.isTemporaryPopupVisible = false; // Hide the popup after 3 seconds
      }, 2000);
    },
  },
  watch: {
    id(newId) {
      this.fetchProducts();
    },
  },
  mounted() {
    this.fetchProducts();
  },
};
</script>

<style scoped>
.overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.popup {
  background: white;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
  width: 80%;
  max-width: 500px;
  height: 70vh; /* Adjust height to fit the content */
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  z-index: 1001;
}

.buy-popup {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #3c93e8;
  color: white;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
  z-index: 1100;
  text-align: center;
  font-size: 24px;
  width: 300px;
}

.temporary-popup {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #59c1ac;
  color: white;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
  z-index: 1100;
  text-align: center;
  font-size: 24px;
  width: 300px;
}

.cards-container {
  flex: 1; /* Take up all remaining space */
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  justify-content: center;
  overflow-y: auto; /* Make it scrollable if there are too many cards */
  max-height: calc(70vh - 70px); /* Restrict cards section height */
}

.card {
  width: 200px;
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  cursor: pointer;
}

.card.selected {
  border: 3px solid #59c1ac; /* Highlight border for selected card */
  box-shadow: 0 4px 10px rgba(89, 193, 172, 0.5);
}

.card-image {
  width: 100%;
  height: 150px;
  object-fit: cover;
}

.card-info {
  padding: 10px;
  text-align: center;
}

.button-container {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.action-btn,
.close-btn {
  background-color: #3c93e8;
  border: none;
  padding: 10px;
  color: white;
  cursor: pointer;
  flex: 1;
  margin: 0 5px;
  text-align: center;
  font-size: 16px;
  border-radius: 5px;
}

.close-btn {
  background-color: #59c1ac;
}
</style>
