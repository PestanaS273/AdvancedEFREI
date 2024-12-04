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
      path: '/login',
      name: 'login',
      component: () => import('../views/LoginVue.vue'),
      meta: { requiresAuth: false },
      beforeEnter: (to, from, next) => {
        if (store.getters['user/isAuthenticated']) {
          next({ name: 'dashboard' });
        } else {
          next();
        }
      }
    },
    {
      path: '/profile',
      name: 'profile',
      component: () => import('../views/profileView.vue'),
      meta: { requiersAuth: true },
      props: true
  },
  {
    path: '/dashboard',
    name: 'dashboard',
    meta: { requiresAuth: true },
    beforeEnter: (to, from, next) => {
      if (store.getters['user/isAuthenticated']) {
        console.log("User is authenticated");
        const user = store.getters['user/getUser'];
        if (user && user.roles && user.roles.length > 0) {
          const userRole = user.roles[0];
          console.log(userRole);
          if (userRole === 'admin') next({ name: 'admin-dashboard' });
          else if (userRole === 'etudiant') next({ name: 'student-dashboard' });
          else if (userRole === 'teacher') next({ name: 'teacher-dashboard' });
          else next({ name: 'login' });
        } else {
          next({ name: 'login' });
        }
      } else {
        console.log("User is not authenticated");
        next({ name: 'login' });
      }
    }
  },
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
  },
  {
    path: '/student-feedback-review/:id',
    name: 'student-feedback-review',
    component: () => import('../views/student/FormsReviewView.vue'),

  },
  {
    path: '/teacher-feedback-answer/:id',
    name: 'teacher-feedback-answer',
    component: () => import('../views/teacher/FeedbackAnswerView.vue'),
  },
  {
    path: '/student-feedback-answer/:id',
    name: 'student-feedback-answer',
    component: () => import('../views/student/FeedbackAnswerView.vue'),
    
  },
  {
    path: '/collection-feedback/:id',
    name: 'collection-feedback',
    meta: { requiresAuth: true },
    beforeEnter: (to, from, next) => {
      if (store.getters['user/isAuthenticated']) {
        const user = store.getters['user/getUser'];
        console.log(user);
        if (user && user.roles && user.roles.length > 0) {
          const userRole = user.roles[0];
          console.log(userRole);
          if (userRole === 'admin') next({ name: 'admin-collection-feedback-review', params: { id: to.params.id } });
          else if (userRole === 'etudiant') next({ name: 'student-feedback-review', params: { id: to.params.id } });
          else if (userRole === 'teacher') next({ name: 'teacher-collection-feedback', params: { id: to.params.id } });
          else next({ name: 'login' });
        } else {
          next({ name: 'login' });
        }
      }
    }
  },
  {
    path: '/teacher-collection-feedback-review/:id',
    name: 'teacher-collection-feedback-review',
    component: () => import('../views/teacher/CollectionFeedbackView.vue'),
  },
  {
    path: '/admin-collection-feedback-review/:id',
    name: 'admin-collection-feedback-review',
    component: () => import('../views/admin/CollectionFeedbackView.vue'),
  },
  {
    path: '/admin-feedback-review/:id',
    name: 'admin-feedback-review',
    component: () => import('../views/admin/FormsReviewView.vue'),
  },
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
