import { createApp } from 'vue'
import piniaPluginPersistedstate from "pinia-plugin-persistedstate";
import { createPinia } from 'pinia'

import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/reset.css'

import App from './App.vue'
import router from './router'
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';

const app = createApp(App)
const pinia = createPinia();

pinia.use(piniaPluginPersistedstate);

app.use(pinia)
app.use(router)
app.use(Antd)


router.isReady().then(() => {
    app.mount("#app");
});
  

<<<<<<< HEAD
=======
app.use(Antd).mount('#app')
>>>>>>> 9264976cafb90eb78e22c4da7936f631d8700128
