<script setup>
import { useI18n } from 'vue-i18n'

const { t } = useI18n()
</script>

<template>
  <div class="p-6 bg-gray-100 min-h-screen">
    
      <!-- Título del Dashboard -->
      <h1 class="text-3xl font-bold text-gray-800 mb-6">{{ t('Dashboard') }}</h1>
  
      <!-- Sección de estadísticas -->
      <div class="grid grid-cols-1 md:grid-cols-3 gap-6 mb-10">
        <StatsCard
            title="Porcentaje de Respuestas"
            value="85%"
            icon="📊"
            bgColor="bg-green-500"
        />
        <StatsCard
            title="Total de Encuestas"
            value="120"
            icon="📋"
            bgColor="bg-blue-500"
        />
        <StatsCard
            title="Usuarios Activos"
            value="350"
            icon="👥"
            bgColor="bg-purple-500"
        />
      </div>
  
      <!-- Tabla de encuestas -->
      <h1 class="text-xl font-bold text-gray-800 my-6">{{ t('Forms To Complete') }}</h1>
      <DataTableToComplete :data="dataToComplete" />
      <br>
      <h1 class="text-xl font-bold text-gray-800 my-6">{{ t('Completed Forms') }}</h1>
      <DataTable :data="surveys" />
      

    
  </div>

  <AddForm />
  <AddUser />
  </template>
  
  <script>
import StatsCard from "../components/Dashboard/StatsCard.vue";
import DataTable from "../components/Dashboard/DataTable.vue";
import AddForm from '../components/Dashboard/AddForm.vue';
import AddUser from '../components/Dashboard/AddUser.vue';
import DataTableToComplete from '../components/Dashboard/DataTableToComplete.vue';


  export default {
  components: { StatsCard, DataTable, AddForm },
  data() {
      return {
      surveys: [
          { id: 1, title: "Encuesta de Satisfacción", responses: 150, created: "2024-11-01" },
          { id: 2, title: "Opinión sobre Productos", responses: 200, created: "2024-11-05" },
      ],
      dataToComplete: [
          //{ id: 1, title: "Enquête sur le crous", responses: 150, created: "2024-11-01" },
          //{ id: 2, title: "Opinion sur les locaux", responses: 200, created: "2024-11-05" },
      ],
      };
  },

  };
  </script>
  
  <style scoped>
  </style>
    