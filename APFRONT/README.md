# APFRONT: Vue.js with Vite

## Overview
The frontend of the application is developed using Vue.js and built with Vite for faster development and optimized builds. It provides an intuitive and responsive user interface for students, professors, and administrators to interact with the survey system.

## Folder Structure
The typical structure of the frontend project might include:

```
APFRONT/
├── public/         # Static assets (e.g., images, icons)
├── src/
│   ├── assets/     # Application-specific assets (e.g., styles, images)
│   ├── components/ # Reusable Vue components
│   ├── router/     # Vue Router configuration
│   ├── store/      # Vuex for state management
│   ├── views/      # Page-level components
│   ├── App.vue     # Root component
│   ├── main.js     # Application entry point
├── vite.config.js  # Vite configuration file
└── package.json    # Project metadata and dependencies
```

## Key Features
1. **Survey Interaction**:
   - Students and professors can respond to surveys assigned to them.
   - View the list of completed surveys.

2. **User-Friendly Interface**:
   - Intuitive design for seamless navigation.
   - Multilingual support for Spanish, English, French, and Japanese.

3. **Real-Time Updates**:
   - Dynamic updates to the survey list and responses without page reloads.

## Configuration
The frontend is configured with the following environment variables defined in a `.env` 

## Language Support
The application supports multilingual features using `vue-i18n`. Languages are stored in JSON files within `src/lang/`. Below is an example setup:

```javascript
import { createI18n } from 'vue-i18n'
import en from './lang/en/en.json'
import esp from './lang/esp/esp.json'
import fr from './lang/fr/fr.json'
import jap from './lang/jap/jap.json'

const i18n = createI18n({
    legacy: false,
    locale: localStorage.getItem('lang') || 'en',
    fallbackLocale: 'en',
    messages: {
        en,
        esp,
        fr,
        jap
    }
})

export default i18n
```

## State Management
The application uses Vuex for centralized state management. The store modules include:

```javascript
import { createStore } from 'vuex';
import user from './user.store';
import form from './form.store';
import collectionForms from './collectionForms.store';

export default createStore({
    modules: {
        user,
        form,
        collectionForms,
    },
});
```

### Key States:
- **User Authentication**: Tracks user login status and roles.
- **Survey Data**: Manages the list of surveys and responses.

## Router
The routing logic, implemented with Vue Router, includes role-based navigation and guards to ensure authenticated access:

```javascript
import { createRouter, createWebHistory } from 'vue-router'
import store from '../store'

const router = createRouter({
  history: createWebHistory(),
  routes: [
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
      path: '/',
      name: 'dashboard',
      meta: { requiresAuth: true },
      beforeEnter: (to, from, next) => {
        const user = store.getters['user/getUser'];
        if (user && user.roles.includes('admin')) next({ name: 'admin-dashboard' });
        else if (user && user.roles.includes('etudiant')) next({ name: 'student-dashboard' });
        else if (user && user.roles.includes('teacher')) next({ name: 'teacher-dashboard' });
        else next({ name: 'login' });
      }
    },
    {
      path: '/admin-dashboard',
      name: 'admin-dashboard',
      component: () => import('../views/admin/DashboardView.vue'),
      meta: { requiresAuth: true, role: 'admin' },
    },
    {
      path: '/student-dashboard',
      name: 'student-dashboard',
      component: () => import('../views/student/DashboardView.vue'),
      meta: { requiresAuth: true, role: 'student' },
    },
    {
      path: '/teacher-dashboard',
      name: 'teacher-dashboard',
      component: () => import('../views/teacher/DashboardView.vue'),
      meta: { requiresAuth: true, role: 'teacher' },
    },
  ]
})

export default router
```

## API Client
The frontend uses Axios for HTTP requests. An API client is configured as follows:

```javascript
import axios from 'axios';
const baseURL = import.meta.env.VITE_API_BASE_URL;

const apiClient = axios.create({
    baseURL: baseURL,
    withCredentials: false,
    headers: {
      'Content-Type': 'application/json'
    }
});

apiClient.interceptors.request.use(config => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
}, error => {
    return Promise.reject(error);
});

export default apiClient;
```

## Running the Frontend
To run the frontend locally:

1. Navigate to the `APFRONT` directory:
   ```bash
   cd APFRONT
   ```

2. Install the dependencies:
   ```bash
   npm install
   ```

3. Start the development server:
   ```bash
   npm run dev
   ```

4. Open your browser and navigate to `http://localhost:4173`.

## Building for Production
To create an optimized build for production:

```bash
npm run build
```

The build output will be located in the `dist/` directory, which can be served using any static file server or deployed using Docker.

### Dockerfile for Frontend Deployment
```dockerfile
FROM node:18-alpine
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build
EXPOSE 4173
CMD ["npm", "run", "preview"]
```

This ensures a production-ready environment for serving the frontend.

