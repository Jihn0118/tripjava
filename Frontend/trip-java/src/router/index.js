import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Planview from "@/views/PlanView.vue";
import InformationView from "@/views/InformationView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/plan',
      name: 'plan',
      component: Planview
    },
    {
      path: '/information',
      name: 'information',
      component: InformationView
    },
  ]
})

export default router
