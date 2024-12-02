
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { useKakao } from 'vue3-kakao-maps/@utils';

import App from './App.vue'
import router from './router'

import 'bootstrap/dist/css/bootstrap.min.css';

const app = createApp(App)

app.use(createPinia())
app.use(router)

useKakao(import.meta.env.VITE_KAKAO_API_KEY);

app.mount('#app')
