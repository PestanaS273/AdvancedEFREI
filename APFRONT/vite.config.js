import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue({
    reactivityTransform: true,
    
  })],
  server: {
    host: true, // Permite aceptar conexiones externas
    port: 4173, // Puerto usado en tu Dockerfile
  },
})
