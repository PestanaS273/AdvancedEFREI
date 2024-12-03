import { createRouter, createWebHistory } from 'vue-router'
import store from '../store'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views//HomeView.vue'),
      meta: { requiersAuth: true },
      props: true
    },
    {
        path: '/test',
        name: 'test',
        component: () => import('../views/TestPythonVue.vue'),
        
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/LoginVue.vue'),
      meta: { requiersAuth: false },
    },
    {
      path: '/loginTest',
      name: 'logintest',
      component: ()=>import('../views/LoginTest.vue'),
      meta: {requiersAuth: false}
    },
    {
      path: '/profile',
      name: 'profile',
      component: () => import('../views/profileView.vue'),
      meta: { requiersAuth: true },
      props: true
  },
  // {
  //   path: '/dashboard',
  //   name: 'dashboard',
  //   meta: { requiersAuth: true },
  //   beforeEnter: (to, from, next) => {
  //     const userRole = store.user.getters.getUser.role;
  //     if (userRole === 'admin') next({ name: 'admin-dashboard' });
  //     else if (userRole === 'student') next({name: 'student-dashboard'});
  //     else if (userRole ==='teacher') next({name: 'teacher-dashboard'});
  //     else next({name: 'login'});
  //   }
  // },
  {
    path: '/admin-dashboard',
    name: 'admin-dashboard',
    component: () => import('../views/admin/DashboardView.vue'),
    meta: { requiersAuth: true, role: 'admin' },
  },
  {
    path: '/student-dashboard',
    name: 'student-dashboard',
    component: () => import('../views/student/DashboardView.vue'),
    meta: { requiersAuth: true, role: 'student' },
  },
  {
    path: '/teacher-dashboard',
    name: 'teacher-dashboard',
    component: () => import('../views/teacher/DashboardView.vue'),
    meta: { requiersAuth: true, role: 'teacher' },
  }
  ]
})

// router.beforeEach((to , from, next) => {
//   const isAuthenticated = store.user.getters.isAuthenticated;
//   if (to.meta.requiresAuth && !isAuthenticated) {
//     next ({ name: 'login' });
//   } else {
//     next();
//   }
// })

export default router
