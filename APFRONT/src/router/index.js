import { createRouter, createWebHistory } from 'vue-router'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views//HomeView.vue'),
      props: true
    },
    {
        path: '/test',
        name: 'test',
        component: () => import('../views/TestPythonVue.vue'),
    },
    {
      path: '/profile',
      name: 'profile',
      component: () => import('../views/profileView.vue'),
      props: true
  }
  ]
})

export default router
