import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Home',
      component: HomeView,
    },
    // lazy-loaded when the route is visited.
    {
      path: '/waveboard',
      name: 'Waveboard',
      // route level code-splitting
      component: () => import('../views/WaveboardView.vue'),
    },
  ],
})

export default router

